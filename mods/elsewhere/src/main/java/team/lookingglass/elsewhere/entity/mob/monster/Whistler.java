package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import team.lookingglass.elsewhere.registry.sound.ESounds;

public class Whistler extends Monster {
    private static final EntityDataAccessor<Integer> DATA_FUSE = SynchedEntityData.defineId(Whistler.class, EntityDataSerializers.INT);
    private static final int DETONATION_TICKS = 160;
    private static final float EXPLOSION_POWER = 6.0F;
    private static final float MIN_SPIN_DEGREES_PER_TICK = 4.0F;
    private static final float MAX_SPIN_DEGREES_PER_TICK = 60.0F;
    private static final double IDLE_SPEED = 0.15;
    private static final double PRIMED_SPEED = 0.38;
    private float headSpin;
    private float headSpinO;

    public Whistler(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FUSE, -1);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
        goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8));
        goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.MOVEMENT_SPEED, IDLE_SPEED)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.FOLLOW_RANGE, 64)
                ;
    }

    public static boolean checkWhistlerSpawnRules(EntityType<Whistler> type, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        return pos.getY() < 0 && checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }

    public boolean isPrimed() {
        return entityData.get(DATA_FUSE) >= 0;
    }

    public float getFuseProgress() {
        return Mth.clampedLerp(entityData.get(DATA_FUSE) / (float) DETONATION_TICKS, 0.0F, 1.0F);
    }

    public float getHeadSpin(float partialTick) {
        return Mth.lerp(partialTick, headSpinO, headSpin);
    }

    private boolean canBeHurtBy(DamageSource source) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || source.is(DamageTypeTags.IS_EXPLOSION)) {
            return true;
        }
        ItemStack weapon = source.getWeaponItem();
        return weapon != null && weapon.is(ItemTags.PICKAXES);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (!canBeHurtBy(source)) {
            return false;
        }
        boolean hurt = super.hurtServer(level, source, damage);
        if (hurt) {
            prime();
        }
        return hurt;
    }

    private void prime() {
        if (isPrimed()) {
            return;
        }
        entityData.set(DATA_FUSE, 0);
        AttributeInstance speed = getAttribute(Attributes.MOVEMENT_SPEED);
        if (speed != null) {
            speed.setBaseValue(PRIMED_SPEED);
        }
        playSound(ESounds.WHISTLER_DETONATE, 1.0F, 1.0F);
        gameEvent(GameEvent.PRIME_FUSE);
    }

    private void detonate() {
        if (level() instanceof ServerLevel serverLevel) {
            dead = true;
            serverLevel.explode(this, getX(), getY(), getZ(), EXPLOSION_POWER, Level.ExplosionInteraction.MOB);
            discard();
        }
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (isPrimed()) {
            return false;
        }
        return super.doHurtTarget(level, target);
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide()) {
            tickHeadSpin();
            return;
        }

        int fuse = entityData.get(DATA_FUSE);
        if (fuse >= 0 && isAlive()) {
            fuse++;
            if (fuse >= DETONATION_TICKS) {
                detonate();
            } else {
                entityData.set(DATA_FUSE, fuse);
            }
        }
    }

    private void tickHeadSpin() {
        headSpinO = headSpin;
        if (!isPrimed()) {
            headSpin = 0.0F;
            headSpinO = 0.0F;
            return;
        }
        float progress = getFuseProgress();
        headSpin += Mth.lerp(progress * progress, MIN_SPIN_DEGREES_PER_TICK, MAX_SPIN_DEGREES_PER_TICK);
        if (headSpin >= 360.0F) {
            headSpin -= 360.0F;
            headSpinO -= 360.0F;
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Fuse", entityData.get(DATA_FUSE));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        entityData.set(DATA_FUSE, input.getIntOr("Fuse", -1));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ESounds.WHISTLER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.DEEPSLATE.placeSound().orElseThrow().value();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.DEEPSLATE.breakSound().orElseThrow().value();
    }
}