package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Gravekeeper extends Monster {
    public static final double RETREAT_RANGE = 7.0;
    public static final double POKE_RANGE = 3.0;
    private static final int MAX_MINIONS = 8;
    private static final List<EntityType<? extends Mob>> SUMMON_BATCH = List.of(EntityTypes.SKELETON, EntityTypes.SKELETON, EntityTypes.ZOMBIE, EntityTypes.ZOMBIE);
    private static final int SUMMON_COOLDOWN_TICKS = 240;
    private static final EntityDataAccessor<Boolean> DATA_CASTING = SynchedEntityData.defineId(Gravekeeper.class, EntityDataSerializers.BOOLEAN);
    private final List<Mob> minions = new ArrayList<>();
    private int summonCooldown;

    public Gravekeeper(EntityType<? extends Gravekeeper> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.ARMOR, 2.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new GravekeeperSummonGoal(this));
        this.goalSelector.addGoal(2, new GravekeeperRetreatGoal(this, 1.1, RETREAT_RANGE, POKE_RANGE));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0, false) {
            @Override
            public boolean canUse() {
                LivingEntity target = Gravekeeper.this.getTarget();
                return target != null && Gravekeeper.this.distanceToSqr(target) <= POKE_RANGE * POKE_RANGE && super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_CASTING, false);
    }

    public boolean isCasting() {
        return this.entityData.get(DATA_CASTING);
    }

    public void setCasting(boolean casting) {
        this.entityData.set(DATA_CASTING, casting);
    }

    public boolean canSummon() {
        return this.summonCooldown <= 0 && this.countLivingMinions() < MAX_MINIONS;
    }
    public int countLivingMinions() {
        this.minions.removeIf(minion -> !minion.isAlive());
        return this.minions.size();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.summonCooldown > 0) {
            this.summonCooldown--;
        }
    }

    public void summonMinions(ServerLevel level, LivingEntity target) {
        for (EntityType<? extends Mob> type : SUMMON_BATCH) {
            if (this.countLivingMinions() >= MAX_MINIONS) {
                break;
            }
            this.spawnMinion(level, type, target);
        }
        this.summonCooldown = SUMMON_COOLDOWN_TICKS;
        this.playSound(SoundEvents.EVOKER_CAST_SPELL, 1.0F, 1.0F);
    }

    private void spawnMinion(ServerLevel level, EntityType<? extends Mob> type, LivingEntity target) {
        Mob minion = type.create(level, EntitySpawnReason.MOB_SUMMONED);
        if (minion == null) {
            return;
        }
        BlockPos pos = this.findSummonPos(level, minion);
        minion.snapTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, this.getRandom().nextFloat() * 360.0F, 0.0F);
        minion.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.MOB_SUMMONED, null);
        minion.setTarget(target);
        level.addFreshEntity(minion);
        level.sendParticles(ParticleTypes.SOUL, minion.getX(), minion.getY() + 1.0, minion.getZ(), 8, 0.3, 0.5, 0.3, 0.02);
        this.minions.add(minion);
    }

    private BlockPos findSummonPos(ServerLevel level, Mob minion) {
        for (int i = 0; i < 12; i++) {
            BlockPos pos = this.blockPosition().offset(this.getRandom().nextInt(7) - 3, 0, this.getRandom().nextInt(7) - 3);
            minion.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            if (level.noCollision(minion)) {
                return pos;
            }
        }
        return this.blockPosition();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
    }

    public static class GravekeeperSummonGoal extends Goal {
        private static final int WINDUP_TICKS = 40;
        private static final double TRIGGER_RANGE = 8.0;

        private final Gravekeeper keeper;
        private int windup;

        public GravekeeperSummonGoal(Gravekeeper keeper) {
            this.keeper = keeper;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.keeper.getTarget();
            return target != null
                    && target.isAlive()
                    && this.keeper.canSummon()
                    && this.keeper.distanceToSqr(target) <= TRIGGER_RANGE * TRIGGER_RANGE
                    && this.keeper.getSensing().hasLineOfSight(target);
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.keeper.getTarget();
            return target != null && target.isAlive() && this.windup > 0;
        }

        @Override
        public void start() {
            this.windup = WINDUP_TICKS;
            this.keeper.setCasting(true);
            this.keeper.getNavigation().stop();
            this.keeper.playSound(SoundEvents.EVOKER_PREPARE_SUMMON, 1.0F, 1.0F);
        }

        @Override
        public void tick() {
            LivingEntity target = this.keeper.getTarget();
            if (target == null) {
                return;
            }
            this.keeper.getLookControl().setLookAt(target, 30.0F, 30.0F);
            if (this.keeper.level() instanceof ServerLevel level) {
                if (this.windup % 4 == 0) {
                    level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, this.keeper.getX(), this.keeper.getY() + 1.0, this.keeper.getZ(), 3, 0.4, 0.5, 0.4, 0.01);
                }
                if (--this.windup <= 0) {
                    this.keeper.summonMinions(level, target);
                }
            }
        }

        @Override
        public void stop() {
            this.windup = 0;
            this.keeper.setCasting(false);
        }
    }

    public static class GravekeeperRetreatGoal extends Goal {
        private final Gravekeeper keeper;
        private final double speed;
        private final double retreatDistanceSqr;
        private final double pokeDistanceSqr;
        private Path path;

        public GravekeeperRetreatGoal(Gravekeeper keeper, double speed, double retreatDistance, double pokeDistance) {
            this.keeper = keeper;
            this.speed = speed;
            this.retreatDistanceSqr = retreatDistance * retreatDistance;
            this.pokeDistanceSqr = pokeDistance * pokeDistance;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.keeper.getTarget();
            if (target == null) {
                return false;
            }
            double distanceSqr = this.keeper.distanceToSqr(target);
            if (distanceSqr > this.retreatDistanceSqr || distanceSqr < this.pokeDistanceSqr) {
                return false;
            }
            Vec3 away = DefaultRandomPos.getPosAway(this.keeper, 16, 7, target.position());
            if (away == null || target.distanceToSqr(away) < distanceSqr) {
                return false;
            }
            this.path = this.keeper.getNavigation().createPath(away.x, away.y, away.z, 0);
            return this.path != null;
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.keeper.getTarget();
            return target != null && !this.keeper.getNavigation().isDone() && this.keeper.distanceToSqr(target) >= this.pokeDistanceSqr;
        }

        @Override
        public void start() {
            this.keeper.getNavigation().moveTo(this.path, this.speed);
        }

        @Override
        public void stop() {
            this.keeper.getNavigation().stop();
        }
    }
}