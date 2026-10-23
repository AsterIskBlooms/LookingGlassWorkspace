package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.sounds.SoundEvent;

public class Werewolf extends Monster {
    private static final EntityDataAccessor<Boolean> PREPARING_CHARGE = SynchedEntityData.defineId(Werewolf.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CHARGING = SynchedEntityData.defineId(Werewolf.class, EntityDataSerializers.BOOLEAN);
    private static final double IDLE_SPEED = 0.20F;
    private static final double ATTACKING_SPEED = 0.35F;
    private static final double CHARGING_SPEED = 0.50F;
    private static final int CHARGE_COOLDOWN = 200;
    private static final float ATTACK_DAMAGE = 4.0F;
    private static final float CHARGE_DAMAGE = 10.0F;
    private static final int CHARGE_WINDUP = 40;
    private int chargeCooldown = 0;
    private boolean chargedAttack = false;
    private int chargeWindupTicks = 0;

    public Werewolf(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new WerewolfAttackGoal(this));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60F)
                .add(Attributes.MOVEMENT_SPEED, IDLE_SPEED)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                ;
    }

    @Override
    protected void defineSynchedData(final SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(PREPARING_CHARGE, false);
        builder.define(CHARGING, false);
    }

    public boolean isPreparingCharge() { return this.entityData.get(PREPARING_CHARGE); }
    public boolean isCharging() { return this.entityData.get(CHARGING); }
    public boolean canCharge() { return chargeCooldown <= 0; }
    public float getChargeWindupProgress() { return this.entityData.get(PREPARING_CHARGE) ? 1.0F - ((float) chargeWindupTicks / (float) CHARGE_WINDUP) : 0.0F; }

    public void startCharge() {
        if (canCharge()) {
            entityData.set(PREPARING_CHARGE, true);
            chargeWindupTicks = CHARGE_WINDUP;
            playSound(SoundEvents.ENDER_DRAGON_GROWL, 2.0F, 0.5F);
        }
    }

    public void endCharge() {
        entityData.set(CHARGING, false);
        chargeCooldown = CHARGE_COOLDOWN;
    }

    public void performChargeDamage(ServerLevel level, LivingEntity target) {
        target.hurtServer(level, damageSources().mobAttack(this), CHARGE_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        boolean result = super.doHurtTarget(level, target);
        if (result && chargedAttack && target instanceof LivingEntity living) {
            performChargeDamage(level, living);
            chargedAttack = false;
            endCharge();
        }
        return result;
    }

    public static boolean checkWerewolfSpawnRules(EntityType<Werewolf> type, ServerLevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return checkMonsterSpawnRules(type, level, spawnReason, pos, random);
    }

    @Override
    public void tick() {
        super.tick();
        if (chargeCooldown > 0) { chargeCooldown--; }

        if (isPreparingCharge()) {
            chargeWindupTicks--;
            getNavigation().stop();
            if (chargeWindupTicks <= 0) {
                entityData.set(PREPARING_CHARGE, false);
                entityData.set(CHARGING, true);
                chargedAttack = true;
            }
        }

        AttributeInstance speedAttribute = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttribute != null) {
            double desiredSpeed;
            if (this.getTarget() == null) {
                desiredSpeed = IDLE_SPEED;
            } else if (isCharging()) {
                desiredSpeed = CHARGING_SPEED;
            } else {
                desiredSpeed = ATTACKING_SPEED;
            }
            speedAttribute.setBaseValue(desiredSpeed);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.ENDER_DRAGON_AMBIENT; }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.ENDER_DRAGON_HURT; }

    @Override
    protected SoundEvent getDeathSound() { return SoundEvents.ENDER_DRAGON_AMBIENT; }

    @Override
    public boolean canFreeze() { return false; }

    public static class WerewolfAttackGoal extends MeleeAttackGoal {
        private final Werewolf werewolf;

        public WerewolfAttackGoal(Werewolf werewolf) {
            super(werewolf, 1.0, false);
            this.werewolf = werewolf;
        }

        @Override
        public void tick() {
            LivingEntity target = werewolf.getTarget();

            if (target != null
                    && !werewolf.isPreparingCharge()
                    && !werewolf.isCharging()
                    && werewolf.canCharge()
                    && werewolf.distanceTo(target) > 8.0) {
                werewolf.startCharge();
            }

            super.tick();
        }
    }
}