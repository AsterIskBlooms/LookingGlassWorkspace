package team.lookingglass.monstrous.goal;

import java.util.EnumSet;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enderman;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

public class EndermanCombatGoal extends Goal {
    private static final Set<Block> PICKUP_BLOCKS = Set.of(
            Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT,
            Blocks.PODZOL, Blocks.MYCELIUM,
            Blocks.STONE, Blocks.COBBLESTONE,
            Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE,
            Blocks.DEEPSLATE, Blocks.TUFF,
            Blocks.SAND, Blocks.RED_SAND, Blocks.GRAVEL, Blocks.CLAY,
            Blocks.NETHERRACK, Blocks.END_STONE
    );
    private static final float ACQUIRE_RANGE = 8.0F;
    private static final float ACQUIRE_REACH = 1.5F;
    private static final float ACQUIRE_MIN_TARGET_DISTANCE = 6.0F;
    private static final float ACQUIRE_CHANCE = 0.3F;
    private static final int ACQUIRE_APPROACH_TIMEOUT = 100;
    private static final int ACQUIRE_WINDUP_TICKS = 8;
    private static final int ACQUIRE_RECOVERY_TICKS = 10;
    private static final SwingAnimation BLOCK_SWING = new SwingAnimation(SwingAnimationType.WHACK, 14);
    private static final SwingAnimation BARE_SWING = new SwingAnimation(SwingAnimationType.WHACK, 11);
    private static final int BLOCK_HIT_TICKS = 10;
    private static final int BARE_HIT_TICKS = 8;
    private static final float BLOCK_DAMAGE_MULTIPLIER = 1.5F;
    private static final int BLOCK_HITS_MIN = 2;
    private static final int BLOCK_HITS_MAX = 4;

    private final Enderman enderman;
    private Phase phase = Phase.STALK;
    private BlockPos pickupPos;
    private BlockState pickupState;
    private int phaseTicks;
    private int acquisitionTicks;
    private int acquisitionCooldown;
    private int searchCooldown;
    private boolean teleportAttack;
    private int blockHitsLeft;

    private enum Phase {
        ACQUIRE,
        ACQUIRE_WINDUP,
        ACQUIRE_PICKUP,
        ACQUIRE_RECOVERY,
        STALK,
        JUKE,
        WINDUP,
        TELEPORT,
        SLAM,
        RECOVERY
    }

    public EndermanCombatGoal(Enderman enderman) {
        this.enderman = enderman;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.enderman.getTarget();

        return target != null
                && target.isAlive()
                && !this.enderman.isInWaterOrRain()
                && !this.enderman.isPassenger();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.enderman.getTarget();

        return target != null
                && target.isAlive()
                && !this.enderman.isInWaterOrRain();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void start() {
        this.enderman.getNavigation().stop();
        this.phase = Phase.STALK;
        this.phaseTicks = 15;
    }

    @Override
    public void tick() {
        LivingEntity target = this.enderman.getTarget();
        if (target == null) return;

        this.enderman.getLookControl().setLookAt(target, 30.0F, 30.0F);

        switch (this.phase) {
            case ACQUIRE -> this.tickAcquire();
            case ACQUIRE_WINDUP -> this.tickAcquireWindup();
            case ACQUIRE_PICKUP -> this.tickAcquirePickup();
            case ACQUIRE_RECOVERY -> this.tickAcquireRecovery();
            case STALK -> this.tickStalk(target);
            case JUKE -> this.tickJuke(target);
            case WINDUP -> this.tickWindup(target);
            case TELEPORT -> this.tickTeleport(target);
            case SLAM -> this.tickSlam(target);
            case RECOVERY -> this.tickRecovery();
        }
    }

    private boolean selectPickupTarget() {
        this.pickupPos = this.findPickupBlock();
        this.pickupState = this.pickupPos == null
                ? null
                : this.enderman.level().getBlockState(this.pickupPos);
        return this.pickupPos != null;
    }

    private BlockPos findPickupBlock() {
        Level level = this.enderman.level();
        BlockPos origin = this.enderman.blockPosition();
        BlockPos best = null;
        double bestDistance = ACQUIRE_RANGE * ACQUIRE_RANGE;

        for (int x = -8; x <= 8; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -8; z <= 8; z++) {
                    double horizontalDistance = (x * x) + (z * z);
                    if (horizontalDistance > ACQUIRE_RANGE * ACQUIRE_RANGE) continue;

                    BlockPos pos = origin.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    if (!this.isValidPickupBlock(pos, state)) continue;

                    double distance = this.enderman.distanceToSqr(
                            pos.getX() + 0.5D,
                            pos.getY() + 1.0D,
                            pos.getZ() + 0.5D
                    );

                    if (distance < bestDistance) {
                        bestDistance = distance;
                        best = pos;
                    }
                }
            }
        }

        return best;
    }

    private boolean isValidPickupBlock(BlockPos pos, BlockState state) {
        Level level = this.enderman.level();

        return PICKUP_BLOCKS.contains(state.getBlock())
                && !pos.equals(this.enderman.blockPosition().below())
                && level.getBlockState(pos.above()).isAir()
                && level.getBlockState(pos.above(2)).isAir()
                && level.getBlockState(pos.above(3)).isAir()
                && state.getFluidState().isEmpty()
                && !state.hasBlockEntity();
    }

    private boolean isPickupTargetValid() {
        if (this.pickupPos == null || this.pickupState == null) return false;

        BlockState current = this.enderman.level().getBlockState(this.pickupPos);
        return current.equals(this.pickupState)
                && this.isValidPickupBlock(this.pickupPos, current);
    }

    private void retryPickupTarget() {
        if (this.selectPickupTarget()) {
            this.phase = Phase.ACQUIRE;
            this.enderman.getNavigation().stop();
        } else {
            this.abortAcquisition();
        }
    }

    private void abortAcquisition() {
        this.enderman.getNavigation().stop();
        this.pickupPos = null;
        this.pickupState = null;
        this.phase = Phase.STALK;
        this.phaseTicks = 15;
        this.acquisitionCooldown = 40;
    }

    private Vec3 getPickupPoint() {
        return new Vec3(
                this.pickupPos.getX() + 0.5D,
                this.pickupPos.getY() + 1.0D,
                this.pickupPos.getZ() + 0.5D
        );
    }

    private void tickAcquire() {
        if (!this.isPickupTargetValid()) {
            this.retryPickupTarget();
            return;
        }

        if (++this.acquisitionTicks > ACQUIRE_APPROACH_TIMEOUT) {
            this.abortAcquisition();
            return;
        }

        Vec3 pickupPoint = this.getPickupPoint();
        this.enderman.getLookControl().setLookAt(
                pickupPoint.x, pickupPoint.y, pickupPoint.z, 30.0F, 30.0F
        );

        if (this.enderman.distanceToSqr(pickupPoint) > ACQUIRE_REACH * ACQUIRE_REACH) {
            if (this.enderman.getNavigation().isDone()) {
                this.enderman.getNavigation().moveTo(
                        pickupPoint.x, pickupPoint.y, pickupPoint.z, 1.0D
                );
            }
            return;
        }

        this.enderman.getNavigation().stop();
        this.enderman.swing(InteractionHand.MAIN_HAND, SwingAnimation.DEFAULT);
        this.phase = Phase.ACQUIRE_WINDUP;
        this.phaseTicks = ACQUIRE_WINDUP_TICKS;
    }

    private void tickAcquireWindup() {
        if (!this.isPickupTargetValid()) {
            this.retryPickupTarget();
            return;
        }

        Vec3 pickupPoint = this.getPickupPoint();
        this.enderman.getLookControl().setLookAt(
                pickupPoint.x, pickupPoint.y, pickupPoint.z, 30.0F, 30.0F
        );

        if (this.enderman.distanceToSqr(pickupPoint) > 4.0D) {
            this.phase = Phase.ACQUIRE;
            return;
        }

        if (--this.phaseTicks <= 0) {
            this.phase = Phase.ACQUIRE_PICKUP;
        }
    }

    private void tickAcquirePickup() {
        if (!this.isPickupTargetValid()) {
            this.retryPickupTarget();
            return;
        }

        Vec3 pickupPoint = this.getPickupPoint();
        if (this.enderman.distanceToSqr(pickupPoint) > 4.0D) {
            this.phase = Phase.ACQUIRE;
            return;
        }

        if (!(this.enderman.level() instanceof ServerLevel serverLevel)) return;

        BlockState carried = this.enderman.level().getBlockState(this.pickupPos);
        if (!this.enderman.level().removeBlock(this.pickupPos, false)) {
            this.retryPickupTarget();
            return;
        }

        this.enderman.setCarriedBlock(carried);
        serverLevel.sendParticles(
                new BlockParticleOption(ParticleTypes.BLOCK, carried),
                pickupPoint.x, pickupPoint.y, pickupPoint.z,
                8, 0.2D, 0.2D, 0.2D, 0.05D,
                ClientboundLevelParticlesPacket.RandomizationType.DEFAULT
        );
        serverLevel.playSound(
                null, this.enderman.blockPosition(),
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.HOSTILE, 0.6F, 1.4F
        );

        this.enderman.getNavigation().stop();
        this.pickupPos = null;
        this.pickupState = null;
        this.phase = Phase.ACQUIRE_RECOVERY;
        this.phaseTicks = ACQUIRE_RECOVERY_TICKS;
    }

    private void tickAcquireRecovery() {
        this.enderman.getNavigation().stop();

        if (--this.phaseTicks <= 0) {
            this.phase = Phase.STALK;
            this.phaseTicks = 15;
        }
    }

    private void tickStalk(LivingEntity target) {
        float distance = this.enderman.distanceTo(target);

        if (this.enderman.getCarriedBlock() == null && distance > ACQUIRE_MIN_TARGET_DISTANCE) {
            if (this.acquisitionCooldown > 0) {
                this.acquisitionCooldown--;
            } else if (this.searchCooldown > 0) {
                this.searchCooldown--;
            } else {
                this.searchCooldown = 20;

                if (this.enderman.getRandom().nextFloat() < ACQUIRE_CHANCE && this.selectPickupTarget()) {
                    this.acquisitionTicks = 0;
                    this.phase = Phase.ACQUIRE;
                    return;
                }
            }
        }

        if (distance > 5.0F) {
            this.enderman.getNavigation().moveTo(target, 1.15F);
            this.phaseTicks = 10;
            return;
        }

        this.enderman.getNavigation().stop();

        if (--this.phaseTicks > 0) return;

        if (distance > 3.5F && this.enderman.getRandom().nextFloat() < 0.35F) {
            this.phase = Phase.JUKE;
            this.phaseTicks = 10;
            return;
        }

        this.beginAttack(distance);
    }

    private void tickJuke(LivingEntity target) {
        float distance = this.enderman.distanceTo(target);

        if (this.enderman.getNavigation().isDone()) {
            this.enderman.getNavigation().moveTo(target, 1.5F);
        }

        if (--this.phaseTicks <= 0 || distance < 2.5F) {
            this.enderman.getNavigation().stop();
            this.phase = Phase.TELEPORT;
        }
    }

    private void beginWindup() {
        this.phase = Phase.WINDUP;
        this.phaseTicks = 10;

        if (this.enderman.getCarriedBlock() != null) {
            this.enderman.swing(InteractionHand.MAIN_HAND, BLOCK_SWING);
        }
    }

    private void beginAttack(float distance) {
        this.phase = Phase.WINDUP;
        this.teleportAttack = distance > 3.0F && this.enderman.getRandom().nextFloat() < 0.5F;

        if (this.teleportAttack) {
            this.phaseTicks = 8;
            return;
        }

        boolean carrying = this.enderman.getCarriedBlock() != null;
        this.enderman.swing(InteractionHand.MAIN_HAND, carrying ? BLOCK_SWING : BARE_SWING);
        this.phaseTicks = carrying ? BLOCK_HIT_TICKS : BARE_HIT_TICKS;
    }

    private void tickWindup(LivingEntity target) {
        float distance = this.enderman.distanceTo(target);

        if (distance > 3.0F) {
            this.enderman.getNavigation().moveTo(target, 0.85F);
        } else {
            this.enderman.getNavigation().stop();
        }

        if (--this.phaseTicks > 0) return;

        if (this.teleportAttack) {
            this.phase = Phase.TELEPORT;
        } else {
            this.performSlam(target);
        }
    }

    private void tickTeleport(LivingEntity target) {
        Vec3 look = Vec3.directionFromRotation(0.0F, target.getYRot());
        Vec3 offset = look.scale(-2.0D);

        double x = target.getX() + offset.x;
        double y = target.getY();
        double z = target.getZ() + offset.z;

        if (!this.enderman.teleport(x, y, z)) {
            this.phase = Phase.RECOVERY;
            this.phaseTicks = 15;
            return;
        }

        double dx = target.getX() - this.enderman.getX();
        double dz = target.getZ() - this.enderman.getZ();
        float yaw = (float) (Mth.atan2(dz, dx) * Mth.RAD_TO_DEG) - 90.0F;

        this.enderman.setYRot(yaw);
        this.enderman.setYBodyRot(yaw);
        this.enderman.setYHeadRot(yaw);

        boolean carrying = this.enderman.getCarriedBlock() != null;
        this.enderman.swing(InteractionHand.MAIN_HAND, carrying ? BLOCK_SWING : BARE_SWING);
        this.phaseTicks = carrying ? BLOCK_HIT_TICKS : BARE_HIT_TICKS;
        this.phase = Phase.SLAM;
    }

    private void tickSlam(LivingEntity target) {
        if (--this.phaseTicks > 0) return;

        this.performSlam(target);
    }

    private void performSlam(LivingEntity target) {
        if (!(this.enderman.level() instanceof ServerLevel serverLevel)) return;

        BlockState carried = this.enderman.getCarriedBlock();
        boolean hit = false;

        if (this.enderman.distanceToSqr(target) <= 9.0D) {
            AttributeInstance damage = this.enderman.getAttribute(Attributes.ATTACK_DAMAGE);
            double base = damage == null ? 0.0D : damage.getBaseValue();

            if (carried != null && damage != null) {
                damage.setBaseValue(base * BLOCK_DAMAGE_MULTIPLIER);
            }

            hit = this.enderman.doHurtTarget(serverLevel, target);

            if (carried != null && damage != null) {
                damage.setBaseValue(base);
            }
        }

        if (carried != null) {
            if (hit) {
                Vec3 push = target.position().subtract(this.enderman.position()).normalize();
                target.push(push.x * 0.65D, 0.35D, push.z * 0.65D);
                serverLevel.sendParticles(
                        new BlockParticleOption(ParticleTypes.BLOCK, carried),
                        target.getX(), target.getY() + 0.5D, target.getZ(),
                        12, 0.3D, 0.2D, 0.3D, 0.1D,
                        ClientboundLevelParticlesPacket.RandomizationType.DEFAULT
                );
            }

            serverLevel.playSound(
                    null, this.enderman.blockPosition(),
                    SoundEvents.PLAYER_ATTACK_STRONG,
                    SoundSource.HOSTILE, 1.0F, 0.65F
            );

            if (hit) {
                if (this.blockHitsLeft <= 0) {
                    this.blockHitsLeft = BLOCK_HITS_MIN
                            + this.enderman.getRandom().nextInt(BLOCK_HITS_MAX - BLOCK_HITS_MIN + 1);
                }

                if (--this.blockHitsLeft <= 0 && !this.placeCarriedBlock(serverLevel, carried)) {
                    this.blockHitsLeft = 1;
                }
            }
        }

        this.phase = Phase.RECOVERY;
        this.phaseTicks = carried != null ? 25 : 20;
    }

    private boolean placeCarriedBlock(ServerLevel level, BlockState carried) {
        BlockPos origin = this.enderman.blockPosition();
        int start = this.enderman.getRandom().nextInt(9);

        for (int i = 0; i < 9; i++) {
            int index = (start + i) % 9;
            int dx = index % 3 - 1;
            int dz = index / 3 - 1;
            if (dx == 0 && dz == 0) continue;

            BlockPos pos = origin.offset(dx, 0, dz);
            BlockPos below = pos.below();

            if (!level.getBlockState(pos).isAir()
                    || !level.getBlockState(below).isCollisionShapeFullBlock(level, below)
                    || !carried.canSurvive(level, pos)
                    || !level.isUnobstructed(carried, pos, CollisionContext.empty())) continue;

            level.setBlock(pos, Block.updateFromNeighbourShapes(carried, level, pos), 3);
            level.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, carried),
                    pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                    8, 0.2D, 0.2D, 0.2D, 0.05D,
                    ClientboundLevelParticlesPacket.RandomizationType.DEFAULT
            );
            level.playSound(
                    null, pos,
                    SoundEvents.ENDERMAN_TELEPORT,
                    SoundSource.HOSTILE, 0.6F, 0.8F
            );

            this.enderman.setCarriedBlock(null);
            this.acquisitionCooldown = 100;
            return true;
        }

        return false;
    }

    private void tickRecovery() {
        this.enderman.getNavigation().stop();

        if (--this.phaseTicks <= 0) {
            this.phase = Phase.STALK;
            this.phaseTicks = 15;
        }
    }

    @Override
    public void stop() {
        this.enderman.getNavigation().stop();

        if (this.enderman.getCarriedBlock() == null) {
            this.acquisitionCooldown = 40;
        }

        this.pickupPos = null;
        this.pickupState = null;
    }
}