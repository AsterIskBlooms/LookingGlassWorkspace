package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;
import team.lookingglass.elsewhere.Elsewhere;

import java.util.EnumSet;

public class Sweeper extends Monster {
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR;
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED;
    private static final EntityDataAccessor<Boolean> DATA_IS_CHASING;
    private static final Identifier CHASE_SPEED_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "sweeper_chase_speed");
    private static final double CHASE_SPEED_BONUS = 0.6;
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 4;

    public Sweeper(final EntityType<? extends Sweeper> type, final Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SweeperSwellGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0F, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.MAX_HEALTH, 40F)
                ;
    }

    public int getMaxFallDistance() {
        return this.getTarget() == null ? this.getComfortableFallDistance(0.0F) : this.getComfortableFallDistance(this.getHealth() - 1.0F);
    }

    public boolean causeFallDamage(final double fallDistance, final float damageModifier, final DamageSource damageSource) {
        boolean damaged = super.causeFallDamage(fallDistance, damageModifier, damageSource);
        this.swell = (int) (this.swell + (fallDistance * 1.5F));
        if (this.swell > this.maxSwell - 5) {
            this.swell = this.maxSwell - 5;
        }

        return damaged;
    }

    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_SWELL_DIR, -1);
        entityData.define(DATA_IS_IGNITED, false);
        entityData.define(DATA_IS_CHASING, false);
    }

    protected void addAdditionalSaveData(final ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putShort("Fuse", (short)this.maxSwell);
        output.putByte("ExplosionRadius", (byte)this.explosionRadius);
        output.putBoolean("ignited", this.isIgnited());
    }

    protected void readAdditionalSaveData(final ValueInput input) {
        super.readAdditionalSaveData(input);
        this.maxSwell = input.getShortOr("Fuse", (short)30);
        this.explosionRadius = input.getByteOr("ExplosionRadius", (byte)3);
        if (input.getBooleanOr("ignited", false)) {
            this.ignite();
        }

    }

    public void tick() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int swellDir = this.getSwellDir();
            if (swellDir > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += swellDir;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.explodeSweeper();
            }
        }

        if (!this.level().isClientSide()) {
            AttributeInstance speedAttribute = this.getAttribute(Attributes.MOVEMENT_SPEED);
            if (speedAttribute != null) {
                boolean hasTarget = this.getTarget() != null;
                this.entityData.set(DATA_IS_CHASING, hasTarget);
                boolean hasModifier = speedAttribute.getModifier(CHASE_SPEED_ID) != null;
                if (hasTarget && !hasModifier) {
                    speedAttribute.addTransientModifier(new AttributeModifier(CHASE_SPEED_ID, CHASE_SPEED_BONUS, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                } else if (!hasTarget && hasModifier) {
                    speedAttribute.removeModifier(CHASE_SPEED_ID);
                }
            }
        }

        super.tick();
    }

    public void setTarget(final @Nullable LivingEntity target) {
        if (!(target instanceof Goat)) {
            super.setTarget(target);
        }
    }

    public static boolean checkSweeperSpawnRules(EntityType<Subslime> type, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        return pos.getY() < 52 && checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }

    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.CREEPER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    public boolean doHurtTarget(final ServerLevel level, final Entity target) {
        return true;
    }

    public float getSwelling(final float a) {
        return Mth.lerp(a, this.oldSwell, this.swell) / (this.maxSwell - 2);
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    public void setSwellDir(final int dir) {
        this.entityData.set(DATA_SWELL_DIR, dir);
    }

    public boolean isChasing() { return this.entityData.get(DATA_IS_CHASING); }

    protected InteractionResult mobInteract(final Player player, final InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(ItemTags.CREEPER_IGNITERS)) {
            SoundEvent soundEvent = itemStack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
            this.level().playSound(player, this.getX(), this.getY(), this.getZ(), soundEvent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level().isClientSide()) {
                this.ignite();
                if (!itemStack.isDamageableItem()) {
                    itemStack.shrink(1);
                } else {
                    itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
                }
            }
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void explodeSweeper() {
        Level var2 = this.level();
        if (var2 instanceof ServerLevel level) {
            this.dead = true;
            level.explode(this, this.getX(), this.getY(), this.getZ(), this.explosionRadius, ExplosionInteraction.MOB);
            this.blindNearbyEntities(level);
            this.spawnSmokeBurst(level);
            this.triggerOnDeathMobEffects(level, RemovalReason.KILLED);
            this.discard();
        }
    }

    private void spawnSmokeBurst(final ServerLevel level) {
        level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 60, 0.6D, 0.6D, 0.6D, 0.05D);
    }

    private void blindNearbyEntities(final ServerLevel level) {
        float radius = this.explosionRadius * 2.0F;
        AABB area = AABB.ofSize(this.position(), radius * 2.0, radius * 2.0, radius * 2.0);
        double radiusSq = radius * radius;

        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, area)) {
            if (target != this && target.distanceToSqr(this) <= radiusSq) {
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
            }
        }
    }

    public boolean isIgnited() {
        return this.entityData.get(DATA_IS_IGNITED);
    }

    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }

    static {
        DATA_SWELL_DIR = SynchedEntityData.defineId(Sweeper.class, EntityDataSerializers.INT);
        DATA_IS_IGNITED = SynchedEntityData.defineId(Sweeper.class, EntityDataSerializers.BOOLEAN);
        DATA_IS_CHASING = SynchedEntityData.defineId(Sweeper.class, EntityDataSerializers.BOOLEAN);
    }

    public static class SweeperSwellGoal extends Goal {
        @Nullable
        private LivingEntity target;
        private final Sweeper sweeper;

        public SweeperSwellGoal(final Sweeper sweeper) {
            this.sweeper = sweeper;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            LivingEntity livingEntity = this.sweeper.getTarget();
            return this.sweeper.getSwellDir() > 0 || livingEntity != null && this.sweeper.distanceToSqr(livingEntity) < 9.0D;
        }

        public void start() {
            this.sweeper.getNavigation().stop();
            this.target = this.sweeper.getTarget();
        }

        public void stop() {
            this.target = null;
        }

        public void tick() {
            if (this.target == null) {
                this.sweeper.setSwellDir(-1);
            } else if (this.sweeper.distanceToSqr(this.target) > 49.0D) {
                this.sweeper.setSwellDir(-1);
            } else if (!this.sweeper.getSensing().hasLineOfSight(this.target)) {
                this.sweeper.setSwellDir(-1);
            } else {
                this.sweeper.setSwellDir(1);
            }
        }
    }
}