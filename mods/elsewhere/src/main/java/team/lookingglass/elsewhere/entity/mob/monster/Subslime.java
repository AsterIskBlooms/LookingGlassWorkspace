package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import team.lookingglass.elsewhere.registry.sound.ESounds;
import team.lookingglass.elsewhere.registry.render.EParticles;

import java.util.EnumSet;

public class Subslime extends AbstractCubeMob {
    private static final EntityDataAccessor<Integer> ATTACK_ANIMATION_TICKS = SynchedEntityData.defineId(Subslime.class, EntityDataSerializers.INT);
    public static final int MIN_SIZE = 2;
    private static final double ATTACK_DAMAGE = 5.0D;
    private static final int ATTACK_ANIMATION_LENGTH = 20;
    private double attackMomentumX;
    private double attackMomentumZ;

    public Subslime(EntityType<? extends AbstractCubeMob> type, Level level) {
        super(type, level);
        setSize(MIN_SIZE, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.5);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACK_ANIMATION_TICKS, 0);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
        setSize(MIN_SIZE, true);
        return data;
    }

    @Override
    protected void setSpawnSize(ServerLevelAccessor level, DifficultyInstance difficulty) {
        setSize(MIN_SIZE, true);
    }

    @Override
    protected void addBehaviourGoals() {
        goalSelector.addGoal(1, new SubslimeAttackGoal(this));
    }

    @Override
    protected void addTargetingGoals() {
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (target, level) -> Math.abs(target.getY() - this.getY()) <= (double)4.0F));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public void setSize(final int size, final boolean updateHealth) {
        super.setSize(MIN_SIZE, updateHealth);
    }

    @Override
    protected void setCubeMobHealth(final int actualSize) {
        this.getAttribute(Attributes.MAX_HEALTH);
    }

    @Override
    protected int getSplitCount() {
        return 0;
    }

    @Override
    protected float getJumpPower() {
        return getJumpPower(1.15F);
    }

    @Override
    public void jumpFromGround() {
        LivingEntity target = getTarget();

        if (target != null) {
            Vec3 direction = target.position()
                    .add(target.getDeltaMovement().scale(4.0D))
                    .subtract(position());

            direction = new Vec3(direction.x, 0.0D, direction.z);

            if (direction.lengthSqr() > 1.0E-7D) {
                direction = direction.normalize();

                double speed = attackAnimationTicks() > 0 ? 1.4D : 0.8D;
                double jumpPower = attackAnimationTicks() > 0 ? 0.45D : getJumpPower();

                setDeltaMovement(direction.x * speed, jumpPower, direction.z * speed);
                needsSync = true;
                return;
            }
        }
        super.jumpFromGround();
    }

    public void startAttackAnimation() {
        entityData.set(ATTACK_ANIMATION_TICKS, ATTACK_ANIMATION_LENGTH);
        playSound(ESounds.SUBSLIME_ATTACK, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
    }

    public float getAttackAnimationProgress(float partialTicks) {
        return Mth.clampedLerp((entityData.get(ATTACK_ANIMATION_TICKS) - partialTicks) / (float) ATTACK_ANIMATION_LENGTH, 0.0F, 1.0F);
    }

    private int attackAnimationTicks() {
        return entityData.get(ATTACK_ANIMATION_TICKS);
    }

    @Override
    public void tick() {
        super.tick();
        int attackTicks = entityData.get(ATTACK_ANIMATION_TICKS);
        if (attackTicks > 0) {
            entityData.set(ATTACK_ANIMATION_TICKS, attackTicks - 1);
            maintainAttackMomentum();
        }
    }

    private void maintainAttackMomentum() {
        Vec3 motion = getDeltaMovement();
        setDeltaMovement(attackMomentumX, motion.y, attackMomentumZ);
        needsSync = true;
    }

    private void performAttackLunge(LivingEntity target) {
        Vec3 direction = target.position().subtract(position());
        direction = new Vec3(direction.x, 0.0D, direction.z);

        if (direction.lengthSqr() < 1.0E-7D) {
            return;
        }
        direction = direction.normalize();

        attackMomentumX = direction.x * 0.35D;
        attackMomentumZ = direction.z * 0.35D;

        setDeltaMovement(direction.x * 1.4D, 0.55D, direction.z * 1.4D);
        startAttackAnimation();
        needsSync = true;
    }

    @Override
    protected void dealDamage(LivingEntity target) {
        if (attackAnimationTicks() <= 0) { return; }
        Level currentLevel = level();
        if (currentLevel instanceof ServerLevel level) {
            if (isAlive() && isWithinMeleeAttackRange(target) && hasLineOfSight(target)) {
                DamageSource damageSource = damageSources().mobAttack(this);
                if (target.hurtServer(level, damageSource, getAttackDamage())) {
                    EnchantmentHelper.doPostAttackEffects(level, target, damageSource);
                }
            }
        }
    }

    @Override
    protected ParticleOptions getParticleType() {
        return EParticles.SUBSLIME_PARTICLE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ESounds.SUBSLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ESounds.SUBSLIME_DEATH;
    }

    @Override
    protected SoundEvent getJumpSound() {
        return ESounds.SUBSLIME_JUMP;
    }

    @Override
    protected SoundEvent getSquishSound() {
        return ESounds.SUBSLIME_SQUISH;
    }

    public static boolean checkSubslimeSpawnRules(EntityType<Subslime> type, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        return Monster.checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }

    public static class SubslimeAttackGoal extends Goal {
        private final Subslime cube;
        private int cooldown;

        public SubslimeAttackGoal(Subslime cube) {
            this.cube = cube;
            this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = cube.getTarget();

            if (target == null || !cube.canAttack(target)) {
                return false;
            }

            return cube.distanceToSqr(target) <= 144.0D;
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = cube.getTarget();

            return target != null
                    && cube.canAttack(target)
                    && cube.distanceToSqr(target) <= 144.0D;
        }

        @Override
        public void start() {
            cooldown = 0;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity target = cube.getTarget();

            if (target == null) { return; }
            cube.lookAt(target, 10.0F, 10.0F);

            if (cube.getMoveControl() instanceof CubeMobMoveControl cubeMoveControl) {
                cubeMoveControl.setDirection(cube.getYRot(), true);
            }

            if (cooldown > 0) {
                cooldown--;
                return;
            }

            if (!cube.onGround()) { return; }
            cube.performAttackLunge(target);
            cooldown = 30;
        }
    }
}