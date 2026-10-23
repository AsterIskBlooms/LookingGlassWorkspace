package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class MudGolem extends Monster {
    private static final double THROW_UP_FORCE = 1.3;
    private static final double THROW_OUT_FORCE = 2.6;
    private static final int SWING_DURATION = 20;
    private int swingTicks = 0;

    public MudGolem(final EntityType<? extends MudGolem> type, final Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.ATTACK_DAMAGE, 9.0)
                .add(Attributes.ARMOR, 6.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    public static boolean checkMudGolemSpawnRules(final EntityType<MudGolem> type, final ServerLevelAccessor level, final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
        return checkMonsterSpawnRules(type, level, spawnReason, pos, random);
    }

    @Override
    public boolean doHurtTarget(final ServerLevel level, final Entity target) {
        if (!(target instanceof LivingEntity living)) {
            return false;
        }

        Vec3 diff = living.position().subtract(this.position());
        Vec3 push = new Vec3(diff.x, 0.0, diff.z).normalize().scale(THROW_OUT_FORCE).add(0.0, THROW_UP_FORCE, 0.0);
        living.setDeltaMovement(push);
        living.hurtServer(level, this.damageSources().mobAttack(this), (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));

        this.swingTicks = SWING_DURATION;
        level.broadcastEntityEvent(this, (byte)4);
        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return true;
    }

    @Override
    public void handleEntityEvent(final byte id) {
        if (id == 4) {
            this.swingTicks = SWING_DURATION;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.swingTicks > 0) {
            this.swingTicks--;
        }
    }

    public float getSwingProgress(final float partialTicks) {
        if (this.swingTicks <= 0) {
            return 0.0F;
        }
        float ticks = (float)this.swingTicks - partialTicks;
        return 1.0F - Mth.clampedLerp(ticks / (float)SWING_DURATION, 0.0F, 1.0F);
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.IRON_GOLEM_STEP;
    }

    @Override
    protected void playStepSound(final BlockPos pos, final BlockState blockState) {
        playSound(getStepSound(), 0.15F, 1.0F);
    }
}