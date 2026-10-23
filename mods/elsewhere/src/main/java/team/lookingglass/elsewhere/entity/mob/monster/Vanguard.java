package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import team.lookingglass.elsewhere.registry.sound.ESounds;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Vanguard extends Monster {
    public enum CombatState {
        APPROACH, WINDUP, SINGLE_SWING, DOUBLE_SWING, BLOCKING, CHARGING, STUNNED
    }
    private static final CombatState[] STATES = CombatState.values();
    private static final EntityDataAccessor<Integer> DATA_COMBAT_STATE = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.INT);
    private static final int SWINGS_BEFORE_CHARGE = 4;
    private static final double CHARGE_SPEED = 0.45;
    private static final int STUN_DURATION = 60;
    private static final int MELEE_BLOCK_COOLDOWN_MIN = 100;
    private static final int MELEE_BLOCK_COOLDOWN_MAX = 200;
    private static final int MELEE_BLOCK_DURATION = 40;
    private static final int ARROW_BLOCK_HOLD = 30;
    private static final int DOUBLE_SWING_COOLDOWN_MIN = 100;
    private static final int DOUBLE_SWING_COOLDOWN_MAX = 160;
    private static final int BLOCK_INTRO_TICKS = 9;
    private static final int BLOCK_FADE_TICKS = 8;
    private static final float CHARGE_BLEND_STEP = 0.34F;
    private static final float WALK_BLOCKS_PER_CYCLE = 1.4F;
    private static final float WALK_FULL_WEIGHT_SPEED = 0.05F;
    private static final float WALK_PHASE_WRAP = 64.0F;
    private int stunTicks;
    private int arrowThreatTicks;
    private int totalSwings;
    private int meleeBlockCooldown;
    private int meleeBlockTicks;
    private int doubleSwingCooldown;
    private Vec3 chargeDirection = Vec3.ZERO;
    private int blockingTicks;
    private boolean blockLoopActive;
    private float blockWeight;
    private float blockWeightO;
    private float chargeWeight;
    private float chargeWeightO;
    private float walkPhase;
    private float walkPhaseO;
    private float walkSpeed;
    private float walkWeight;
    private float walkWeightO;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState blockStartAnimationState = new AnimationState();
    public final AnimationState blockAnimationState = new AnimationState();
    public final AnimationState singleSwingAnimationState = new AnimationState();
    public final AnimationState doubleSwingAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState stunAnimationState = new AnimationState();

    public Vanguard(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        setCanPickUpLoot(false);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            setDropChance(slot, 0.0F);
        }
        meleeBlockCooldown = MELEE_BLOCK_COOLDOWN_MIN + random.nextInt(MELEE_BLOCK_COOLDOWN_MAX - MELEE_BLOCK_COOLDOWN_MIN);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_COMBAT_STATE, CombatState.APPROACH.ordinal());
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new VanguardCombatGoal(this, 1.0));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.75));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.9)
                .add(Attributes.ATTACK_DAMAGE, VanguardCombatGoal.ATTACK_DAMAGE)
                ;
    }

    public CombatState getCombatState() {
        return STATES[entityData.get(DATA_COMBAT_STATE)];
    }

    public void setCombatState(CombatState state) {
        CombatState previous = getCombatState();
        entityData.set(DATA_COMBAT_STATE, state.ordinal());
        if (previous == state) {
            return;
        }
        switch (state) {
            case SINGLE_SWING -> playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 1.0F + random.nextFloat() * 0.2F);
            case DOUBLE_SWING -> playSound(ESounds.VANGUARD_DOUBLE_SWING, 1.0F, 1.0F + random.nextFloat() * 0.2F);
            case CHARGING -> playSound(SoundEvents.BREEZE_CHARGE, 1.0F, 1.0F);
            case STUNNED -> playSound(SoundEvents.CREAKING_DEATH, 1.0F, 1.0F);
            default -> {
            }
        }
    }

    public boolean isBlocking() {
        return getCombatState() == CombatState.BLOCKING;
    }

    public boolean isStunned() {
        return getCombatState() == CombatState.STUNNED;
    }

    public boolean hasIncomingArrowThreat() {
        return arrowThreatTicks > 0;
    }

    public boolean isMeleeBlockReady() {
        return meleeBlockCooldown <= 0;
    }

    public void triggerMeleeBlock() {
        meleeBlockTicks = MELEE_BLOCK_DURATION;
        meleeBlockCooldown = MELEE_BLOCK_COOLDOWN_MIN + random.nextInt(MELEE_BLOCK_COOLDOWN_MAX - MELEE_BLOCK_COOLDOWN_MIN);
    }

    public boolean isMeleeBlockActive() {
        return meleeBlockTicks > 0;
    }

    public boolean isDoubleSwingReady() {
        return doubleSwingCooldown <= 0;
    }

    public void startDoubleSwingCooldown() {
        doubleSwingCooldown = DOUBLE_SWING_COOLDOWN_MIN + random.nextInt(DOUBLE_SWING_COOLDOWN_MAX - DOUBLE_SWING_COOLDOWN_MIN);
    }

    public boolean shouldCharge() {
        return totalSwings >= SWINGS_BEFORE_CHARGE;
    }

    public void onSwingLanded() {
        totalSwings++;
    }

    public void beginCharge(LivingEntity target) {
        totalSwings = 0;
        setCombatState(CombatState.CHARGING);
        Vec3 dir = target.position().subtract(position());
        if (dir.lengthSqr() < 1.0E-4) {
            dir = new Vec3(0.0, 0.0, 1.0);
        }
        chargeDirection = new Vec3(dir.x, 0.0, dir.z).normalize();
    }

    public Vec3 getChargeDirection() {
        return chargeDirection;
    }

    public double getChargeSpeed() {
        return CHARGE_SPEED;
    }

    public void beginStun() {
        setCombatState(CombatState.STUNNED);
        stunTicks = STUN_DURATION;
        setDeltaMovement(0.0, getDeltaMovement().y, 0.0);
    }

    public void faceAim(double dx, double dz, float maxStep) {
        if (dx * dx + dz * dz < 1.0E-6) {
            return;
        }
        float targetYaw = (float) (Mth.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;
        float yaw = getYRot();
        float next = yaw + Mth.clampedLerp(Mth.wrapDegrees(targetYaw - yaw), -maxStep, maxStep);
        setYRot(next);
        setYBodyRot(next);
        setYHeadRot(next);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            CombatState state = getCombatState();

            boolean isFrontalAttack = frontalAttack(source);

            if (state == CombatState.BLOCKING) {
                if (isFrontalAttack) {
                    provoke(source);
                    playBlockSound();
                    return false;
                }
            }

            if (state == CombatState.APPROACH || state == CombatState.WINDUP) {
                if (source.getDirectEntity() instanceof AbstractArrow && hasIncomingArrowThreat()) {
                    if (isFrontalAttack) {
                        provoke(source);
                        getNavigation().stop();
                        setCombatState(CombatState.BLOCKING);
                        playBlockSound();
                        return false;
                    }
                }
                if (source.getDirectEntity() instanceof LivingEntity && isMeleeBlockReady()) {
                    if (isFrontalAttack) {
                        provoke(source);
                        getNavigation().stop();
                        triggerMeleeBlock();
                        setCombatState(CombatState.BLOCKING);
                        playBlockSound();
                        return false;
                    }
                }
            }
        }
        return super.hurtServer(level, source, damage);
    }

    private boolean frontalAttack(DamageSource source) {
        boolean frontalAttack = false;
        Entity attacker = source.getDirectEntity() != null ? source.getDirectEntity() : source.getEntity();

        if (attacker != null) {
            float bodyYaw = getYRot() * Mth.DEG_TO_RAD;
            Vec3 facing = new Vec3(-Mth.sin(bodyYaw), 0.0, Mth.cos(bodyYaw));

            double dx = getX() - attacker.getX();
            double dz = getZ() - attacker.getZ();
            double distSq = dx * dx + dz * dz;

            if (distSq > 1.0E-5) {
                double len = Math.sqrt(distSq);
                Vec3 incomingAttack = new Vec3(dx / len, 0.0, dz / len);
                double dot = facing.x * incomingAttack.x + facing.z * incomingAttack.z;
                frontalAttack = dot < -0.707;
            }
        }
        return frontalAttack;
    }

    private void provoke(DamageSource source) {
        if (source.getEntity() instanceof LivingEntity attacker) {
            setLastHurtByMob(attacker);
        }
    }

    private void playBlockSound() {
        playSound(SoundEvents.SHIELD_BLOCK.value(), 1.0F, 1.0F + random.nextFloat() * 0.2F);
    }

    private void scanForArrowThreats() {
        AABB scanBox = getBoundingBox().inflate(24.0, 8.0, 24.0);
        Vec3 center = getBoundingBox().getCenter();
        boolean threatFound = false;
        for (AbstractArrow arrow : level().getEntitiesOfClass(AbstractArrow.class, scanBox)) {
            Vec3 vel = arrow.getDeltaMovement();
            double speedSqr = vel.lengthSqr();
            if (speedSqr < 0.04) continue;
            Vec3 toSelf = center.subtract(arrow.position());
            double along = toSelf.dot(vel) / Math.sqrt(speedSqr);
            if (along <= 0.0) continue;
            double perpSqr = toSelf.lengthSqr() - along * along;
            if (perpSqr < 2.25) {
                threatFound = true;
                break;
            }
        }
        if (threatFound) {
            arrowThreatTicks = ARROW_BLOCK_HOLD;
        } else if (arrowThreatTicks > 0) {
            arrowThreatTicks--;
        }
    }

    public static boolean checkVanguardSpawnRules(EntityType<Vanguard> type, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        return pos.getY() < 0 && checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ESounds.VANGUARD_BLABBER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.COPPER_GOLEM_OXIDIZED_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COPPER_GOLEM_OXIDIZED_DEATH;
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide()) {
            tickClientAnimations();
            return;
        }

        scanForArrowThreats();

        if (stunTicks > 0) {
            stunTicks--;
            if (stunTicks == 0) {
                setCombatState(CombatState.APPROACH);
            }
        }
        if (meleeBlockCooldown > 0) meleeBlockCooldown--;
        if (meleeBlockTicks > 0) meleeBlockTicks--;
        if (doubleSwingCooldown > 0) doubleSwingCooldown--;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        super.onSyncedDataUpdated(accessor);
        if (level().isClientSide() && DATA_COMBAT_STATE.equals(accessor)) {
            CombatState state = getCombatState();

            singleSwingAnimationState.stop();
            doubleSwingAnimationState.stop();
            stunAnimationState.stop();

            if (state == CombatState.SINGLE_SWING) {
                singleSwingAnimationState.start(tickCount);
            } else if (state == CombatState.DOUBLE_SWING) {
                doubleSwingAnimationState.start(tickCount);
            } else if (state == CombatState.STUNNED) {
                stunAnimationState.start(tickCount);
            }
        }
    }

    private void tickClientAnimations() {
        CombatState state = getCombatState();
        boolean blocking = state == CombatState.BLOCKING;
        boolean charging = state == CombatState.CHARGING;

        blockingTicks = blocking ? blockingTicks + 1 : 0;
        blockWeightO = blockWeight;
        blockWeight = blocking
                ? Math.min(1.0F, blockWeight + 0.5F)
                : Math.max(0.0F, blockWeight - 1.0F / BLOCK_FADE_TICKS);

        if (blockWeight <= 0.0F) {
            blockLoopActive = false;
        } else if (blocking && blockingTicks >= BLOCK_INTRO_TICKS) {
            blockLoopActive = true;
        }

        boolean blockVisible = blockWeight > 0.0F;
        blockStartAnimationState.animateWhen(blockVisible && !blockLoopActive, tickCount);
        blockAnimationState.animateWhen(blockLoopActive, tickCount);

        chargeWeightO = chargeWeight;
        chargeWeight = charging ? Math.min(1.0F, chargeWeight + CHARGE_BLEND_STEP) : 0.0F;
        chargeAnimationState.animateWhen(charging, tickCount);

        idleAnimationState.animateWhen(
                state == CombatState.APPROACH || state == CombatState.WINDUP,
                tickCount);

        double dx = getX() - xo;
        double dz = getZ() - zo;
        float moved = (float) Math.sqrt(dx * dx + dz * dz);
        walkSpeed += (moved - walkSpeed) * 0.5F;
        walkPhaseO = walkPhase;
        walkPhase += moved / WALK_BLOCKS_PER_CYCLE;
        if (walkPhase >= WALK_PHASE_WRAP) {
            walkPhase -= WALK_PHASE_WRAP;
            walkPhaseO -= WALK_PHASE_WRAP;
        }
        walkWeightO = walkWeight;
        walkWeight = Mth.clampedLerp(walkSpeed / WALK_FULL_WEIGHT_SPEED, 0.0F, 1.0F);
    }

    public float getBlockWeight(float partialTick) {
        return Mth.lerp(partialTick, blockWeightO, blockWeight);
    }

    public float getChargeWeight(float partialTick) {
        return Mth.lerp(partialTick, chargeWeightO, chargeWeight);
    }

    public float getWalkPhase(float partialTick) {
        return Mth.lerp(partialTick, walkPhaseO, walkPhase);
    }

    public float getWalkWeight(float partialTick) {
        return Mth.lerp(partialTick, walkWeightO, walkWeight);
    }

    public static class VanguardCombatGoal extends Goal {
        private static final int SINGLE_WINDUP_TICKS = 3;
        private static final int DOUBLE_WINDUP_TICKS = 6;
        private static final int SINGLE_SWING_TICKS = 15;
        private static final int DOUBLE_SWING_TICKS = 40;
        private static final int[] SINGLE_HIT_TICKS = { 2 };
        private static final int[] DOUBLE_HIT_TICKS = { 10, 23 };
        private static final int[] DOUBLE_LUNGE_TICKS = { 8, 21 };

        private static final double SINGLE_START_RANGE = 2.8;
        private static final double DOUBLE_MIN_RANGE = 3.5;
        private static final double DOUBLE_MAX_RANGE = 5.5;
        private static final double SINGLE_REACH = 2.7;
        private static final double DOUBLE_REACH = 2.8;
        private static final double LUNGE_SPEED = 1.2;
        private static final double SWEEP_MIN_DOT = 0.2;

        private static final float WINDUP_TURN_SPEED = 45.0F;
        private static final float ATTACK_TURN_SPEED = 20.0F;
        private static final float BLOCK_TURN_SPEED = 1.0F;

        private static final int MAX_CHARGE_DURATION = 40;
        private static final float ATTACK_DAMAGE = 6.0F;
        private static final float CHARGE_DAMAGE = 4.0F;

        private final Vanguard vanguard;
        private final double speed;
        private int windupTicks;
        private int attackTick;
        private int chargeTicks;
        private boolean attackIsDouble;
        private double aimDx;
        private double aimDz;
        private final Set<Integer> chargeHitIds = new HashSet<>();

        public VanguardCombatGoal(Vanguard vanguard, double speed) {
            this.vanguard = vanguard;
            this.speed = speed;
            setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        private boolean hasLiveTarget() {
            LivingEntity target = vanguard.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public boolean canUse() {
            return hasLiveTarget() || vanguard.getCombatState() != CombatState.APPROACH;
        }

        @Override
        public boolean canContinueToUse() {
            return canUse();
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void stop() {
            vanguard.getNavigation().stop();
        }

        @Override
        public void tick() {
            LivingEntity target = vanguard.getTarget();
            boolean tracking = target != null && target.isAlive();
            if (tracking) {
                aimDx = target.getX() - vanguard.getX();
                aimDz = target.getZ() - vanguard.getZ();
            }

            CombatState state = vanguard.getCombatState();
            if (!tracking && state == CombatState.APPROACH) {
                return;
            }

            if (tracking && state != CombatState.CHARGING && state != CombatState.STUNNED) {
                vanguard.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }

            double dist = Math.sqrt(aimDx * aimDx + aimDz * aimDz);

            switch (state) {
                case APPROACH -> {
                    if (vanguard.hasIncomingArrowThreat()) {
                        vanguard.getNavigation().stop();
                        vanguard.setCombatState(CombatState.BLOCKING);
                        return;
                    }
                    if (dist <= SINGLE_START_RANGE) {
                        beginWindup(false);
                    } else if (dist >= DOUBLE_MIN_RANGE && dist <= DOUBLE_MAX_RANGE && vanguard.isDoubleSwingReady()) {
                        beginWindup(true);
                    } else {
                        vanguard.getNavigation().moveTo(target, speed);
                    }
                }
                case BLOCKING -> {
                    vanguard.getNavigation().stop();
                    vanguard.faceAim(aimDx, aimDz, BLOCK_TURN_SPEED);
                    boolean stillNeedsBlock = vanguard.hasIncomingArrowThreat() || vanguard.isMeleeBlockActive();
                    if (!stillNeedsBlock) {
                        vanguard.setCombatState(CombatState.APPROACH);
                    }
                }
                case WINDUP -> {
                    vanguard.getNavigation().stop();
                    vanguard.faceAim(aimDx, aimDz, WINDUP_TURN_SPEED);
                    if (vanguard.hasIncomingArrowThreat()) {
                        vanguard.setCombatState(CombatState.BLOCKING);
                        return;
                    }
                    if (--windupTicks <= 0) {
                        attackTick = 0;
                        vanguard.setCombatState(attackIsDouble ? CombatState.DOUBLE_SWING : CombatState.SINGLE_SWING);
                    }
                }
                case SINGLE_SWING, DOUBLE_SWING -> {
                    boolean isDouble = state == CombatState.DOUBLE_SWING;
                    vanguard.getNavigation().stop();
                    vanguard.faceAim(aimDx, aimDz, ATTACK_TURN_SPEED);
                    attackTick++;

                    if (isDouble) {
                        for (int t : DOUBLE_LUNGE_TICKS) {
                            if (attackTick == t) lunge(tracking, dist);
                        }
                    }
                    for (int t : isDouble ? DOUBLE_HIT_TICKS : SINGLE_HIT_TICKS) {
                        if (attackTick == t) sweep(tracking ? target : null, isDouble ? DOUBLE_REACH : SINGLE_REACH);
                    }
                    if (attackTick >= (isDouble ? DOUBLE_SWING_TICKS : SINGLE_SWING_TICKS)) {
                        if (tracking && vanguard.shouldCharge()) {
                            vanguard.beginCharge(target);
                            chargeTicks = 0;
                            chargeHitIds.clear();
                        } else {
                            vanguard.setCombatState(CombatState.APPROACH);
                        }
                    }
                }
                case CHARGING -> tickCharge();
                case STUNNED -> vanguard.getNavigation().stop();
            }
        }

        private void beginWindup(boolean doubleSwing) {
            attackIsDouble = doubleSwing;
            windupTicks = doubleSwing ? DOUBLE_WINDUP_TICKS : SINGLE_WINDUP_TICKS;
            vanguard.getNavigation().stop();
            if (doubleSwing) {
                vanguard.startDoubleSwingCooldown();
            }
            vanguard.setCombatState(CombatState.WINDUP);
        }

        private Vec3 forward() {
            float yaw = vanguard.getYRot() * Mth.DEG_TO_RAD;
            return new Vec3(-Mth.sin(yaw), 0.0, Mth.cos(yaw));
        }

        private void lunge(boolean tracking, double dist) {
            if (!tracking || dist <= 1.5) {
                return;
            }
            Vec3 f = forward();
            vanguard.push(f.x * LUNGE_SPEED, 0.0, f.z * LUNGE_SPEED);
        }

        private void sweep(LivingEntity aimTarget, double reach) {
            Vec3 f = forward();
            AABB box = vanguard.getBoundingBox().inflate(reach + 1.0, 1.5, reach + 1.0);
            boolean hitTarget = false;
            for (LivingEntity e : vanguard.level().getEntitiesOfClass(LivingEntity.class, box,
                    x -> x != vanguard && x.isAlive() && !(x instanceof Monster))) {
                double ex = e.getX() - vanguard.getX();
                double ez = e.getZ() - vanguard.getZ();
                double d = Math.sqrt(ex * ex + ez * ez);
                if (d > reach + e.getBbWidth() * 0.5) continue;
                if (d > 1.0E-4 && (ex * f.x + ez * f.z) / d < SWEEP_MIN_DOT) continue;

                e.hurtServer(getServerLevel(vanguard), vanguard.damageSources().mobAttack(vanguard), ATTACK_DAMAGE);
                if (e == aimTarget) hitTarget = true;
            }
            if (hitTarget) {
                vanguard.onSwingLanded();
            }
        }

        private void tickCharge() {
            chargeTicks++;
            Vec3 dir = vanguard.getChargeDirection();

            float targetYRot = (float) (Mth.atan2(-dir.x, dir.z) * (180D / Math.PI));
            float currentYRot = vanguard.getYRot();
            float delta = Mth.wrapDegrees(targetYRot - currentYRot);
            float turnStep = Mth.clampedLerp(delta, -6.0F, 6.0F);
            vanguard.setYRot(currentYRot + turnStep);
            vanguard.setYHeadRot(vanguard.getYRot());

            vanguard.setDeltaMovement(dir.x * vanguard.getChargeSpeed(), vanguard.getDeltaMovement().y, dir.z * vanguard.getChargeSpeed());

            AABB hitBox = vanguard.getBoundingBox().expandTowards(dir.x * 0.6, 0.0, dir.z * 0.6).inflate(0.3);
            for (LivingEntity hit : vanguard.level().getEntitiesOfClass(LivingEntity.class, hitBox, e -> e != vanguard && e.isAlive())) {
                if (chargeHitIds.add(hit.getId())) {
                    hit.hurtServer(getServerLevel(vanguard), vanguard.damageSources().mobAttack(vanguard), CHARGE_DAMAGE);
                    hit.push(dir.x * 0.6, 0.4, dir.z * 0.6);
                }
            }

            if (vanguard.horizontalCollision || chargeTicks >= MAX_CHARGE_DURATION) {
                vanguard.setDeltaMovement(0.0, vanguard.getDeltaMovement().y, 0.0);
                vanguard.beginStun();
            }
        }
    }
}