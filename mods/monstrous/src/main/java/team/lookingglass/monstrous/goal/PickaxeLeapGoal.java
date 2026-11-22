package team.lookingglass.monstrous.goal;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.phys.Vec3;

public class PickaxeLeapGoal extends LeapAtTargetGoal {
    private final Mob holder;
    private final float horizontalBoost;
    private final int windupTicks;
    private final int cooldownTicks;

    private int windupRemaining;
    private boolean leaped;
    private long nextLeapTime;

    public PickaxeLeapGoal(Mob mob, float yd, float horizontalBoost, int windupTicks, int cooldownTicks) {
        super(mob, yd);
        this.holder = mob;
        this.horizontalBoost = horizontalBoost;
        this.windupTicks = windupTicks;
        this.cooldownTicks = cooldownTicks;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canUse() {
        if (!this.holder.getMainHandItem().is(ItemTags.PICKAXES)) return false;
        if (this.holder.level().getGameTime() < this.nextLeapTime) return false;
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (!this.leaped) {
            return this.holder.getTarget() != null && this.holder.onGround();
        }
        return super.canContinueToUse();
    }

    @Override
    public void start() {
        this.windupRemaining = this.windupTicks;
        this.leaped = false;
        this.holder.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.leaped) return;

        LivingEntity target = this.holder.getTarget();
        if (target == null) return;

        this.holder.getNavigation().stop();
        this.holder.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (--this.windupRemaining <= 0) {
            super.start();
            Vec3 motion = this.holder.getDeltaMovement();
            this.holder.setDeltaMovement(motion.x * this.horizontalBoost, motion.y, motion.z * this.horizontalBoost);
            this.leaped = true;
            this.nextLeapTime = this.holder.level().getGameTime() + this.cooldownTicks;
        }
    }

    @Override
    public void stop() {
        this.leaped = false;
    }
}