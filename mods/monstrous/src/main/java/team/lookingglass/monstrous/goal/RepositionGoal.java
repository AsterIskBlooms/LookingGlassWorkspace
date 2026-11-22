package team.lookingglass.monstrous.goal;

import java.util.EnumSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class RepositionGoal extends Goal {
    private static final float[] ANGLES = {0.0F, 0.785F, -0.785F, 1.571F, -1.571F};

    private final PathfinderMob mob;
    private final float speed;
    private final int runTicks;
    private final float fleeDist;

    private int lastHurtTimestamp;
    private int runRemaining;
    private LivingEntity from;

    public RepositionGoal(PathfinderMob mob, float speed, int runTicks, float fleeDist) {
        this.mob = mob;
        this.speed = speed;
        this.runTicks = runTicks;
        this.fleeDist = fleeDist;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    private boolean holdingBow() {
        return this.mob.getMainHandItem().is(Items.BOW);
    }

    private boolean flee() {
        Vec3 away = this.mob.position().subtract(this.from.position());
        away = new Vec3(away.x, 0.0D, away.z);
        if (away.lengthSqr() < 1.0E-6F) {
            away = new Vec3(1.0D, 0.0D, 0.0D);
        }
        away = away.normalize();

        for (float angle : ANGLES) {
            Vec3 dest = this.mob.position().add(away.yRot(angle).scale(this.fleeDist));
            if (this.mob.getNavigation().moveTo(dest.x, dest.y, dest.z, this.speed)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canUse() {
        int stamp = this.mob.getLastHurtByMobTimestamp();
        if (stamp == this.lastHurtTimestamp) return false;
        this.lastHurtTimestamp = stamp;

        if (!this.holdingBow()) return false;

        LivingEntity hitBy = this.mob.getLastHurtByMob();
        if (hitBy == null) hitBy = this.mob.getTarget();
        if (hitBy == null) return false;

        this.from = hitBy;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.runRemaining > 0 && this.holdingBow();
    }

    @Override
    public void start() {
        this.runRemaining = this.runTicks;
        this.flee();
    }

    @Override
    public void tick() {
        int stamp = this.mob.getLastHurtByMobTimestamp();
        if (stamp != this.lastHurtTimestamp) {
            this.lastHurtTimestamp = stamp;
            this.runRemaining = this.runTicks;

            LivingEntity hitBy = this.mob.getLastHurtByMob();
            if (hitBy != null) {
                this.from = hitBy;
            }
        }

        this.runRemaining--;

        if (this.mob.getNavigation().isDone() && !this.flee() && this.mob.onGround()) {
            this.runRemaining = 0;
        }
    }

    @Override
    public void stop() {
        this.from = null;
        this.mob.getNavigation().stop();
    }
}