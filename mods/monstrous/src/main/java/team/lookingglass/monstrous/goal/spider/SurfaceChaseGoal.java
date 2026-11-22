package team.lookingglass.monstrous.goal.spider;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import team.lookingglass.monstrous.render.Crawler;

/**
 * Chases a target over walls and ceilings when vanilla ground pathing can't reach it (or the spider is
 * already off the floor). Hands back to vanilla melee once it is close, or directly above the target.
 */
public class SurfaceChaseGoal extends Goal {
    private static final double SPEED = 0.24D; // blocks per tick along the path
    private static final double REACHED = 0.3D;
    private static final double HANDOFF_SQR = 6.25D; // 2.5 blocks

    private final net.minecraft.world.entity.monster.spider.Spider spider;
    private List<SurfacePathfinder.Step> path;
    private int index;
    private int cooldown;
    private int repathTimer;
    private int stuck;
    private int age;
    private Vec3 lastPos = Vec3.ZERO;
    private Vec3 pathGoal = Vec3.ZERO;

    public SurfaceChaseGoal(net.minecraft.world.entity.monster.spider.Spider spider) {
        this.spider = spider;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (!(this.spider instanceof Crawler)) return false;
        LivingEntity target = this.spider.getTarget();
        if (target == null || !target.isAlive() || this.spider.isVehicle() || this.spider.isInWater()) return false;
        if (this.closeEnough(target)) return false;

        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }
        this.cooldown = 5;

        if (!this.spider.onClimbable()) {
            Path ground = this.spider.getNavigation().createPath(target, 0);
            if (ground != null && ground.canReach()) return false; // vanilla can walk there
        }
        return this.plan(target);
    }

    private boolean plan(LivingEntity target) {
        List<SurfacePathfinder.Step> found = SurfacePathfinder.find(this.spider.level(), this.spider, target.position());
        if (found == null || found.size() < 2) return false;
        this.path = found;
        this.index = 1;
        this.pathGoal = target.position();
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.spider.getTarget();
        return this.path != null
                && this.index < this.path.size()
                && target != null
                && target.isAlive()
                && !this.spider.isInWater()
                && !this.closeEnough(target)
                && this.stuck < 20
                && this.age < 400;
    }

    private boolean closeEnough(LivingEntity target) {
        double dx = target.getX() - this.spider.getX();
        double dy = target.getY() - this.spider.getY();
        double dz = target.getZ() - this.spider.getZ();
        if (dx * dx + dy * dy + dz * dz < HANDOFF_SQR) return true;
        return dx * dx + dz * dz < 2.25D && dy < -0.5D;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void start() {
        this.stuck = 0;
        this.age = 0;
        this.repathTimer = 20;
        this.lastPos = this.spider.position();
        this.spider.getNavigation().stop();
        if (this.spider instanceof Crawler clinger) {
            clinger.monstrous$setSurface(this.path.get(this.index).face());
        }
    }

    @Override
    public void tick() {
        LivingEntity target = this.spider.getTarget();
        if (target == null || this.path == null || this.index >= this.path.size()) return;
        this.age++;

        if (--this.repathTimer <= 0) {
            this.repathTimer = 20;
            if (target.position().distanceToSqr(this.pathGoal) > 4.0D) {
                List<SurfacePathfinder.Step> fresh = SurfacePathfinder.find(this.spider.level(), this.spider, target.position());
                if (fresh != null && fresh.size() >= 2) {
                    this.path = fresh;
                    this.index = 1;
                    this.pathGoal = target.position();
                }
            }
        }

        Vec3 pos = this.spider.position();
        SurfacePathfinder.Step step = this.path.get(this.index);
        Vec3 to = step.rest().subtract(pos);
        double dist = to.length();
        while (dist < REACHED && this.index < this.path.size() - 1) {
            step = this.path.get(++this.index);
            to = step.rest().subtract(pos);
            dist = to.length();
        }
        if (dist < REACHED) {
            this.index = this.path.size(); // arrived; canContinueToUse ends the goal
            return;
        }

        // set the velocity travel() is about to apply; with this zeroing it every tick, gravity never accumulates
        Vec3 move = dist > SPEED ? to.scale(SPEED / dist) : to;
        this.spider.setDeltaMovement(move);

        if (move.x * move.x + move.z * move.z > 1.0E-4D) {
            float want = (float) (Mth.atan2(move.z, move.x) * 57.2957763671875D) - 90.0F;
            float yaw = Mth.rotLerp(0.35F, this.spider.getYRot(), want);
            this.spider.setYRot(yaw);
            this.spider.setYHeadRot(yaw);
            this.spider.setYBodyRot(yaw);
        }

        if (this.spider instanceof Crawler clinger) {
            clinger.monstrous$setSurface(step.face());
        }

        this.stuck = pos.distanceToSqr(this.lastPos) < 4.0E-4D ? this.stuck + 1 : 0;
        this.lastPos = pos;
    }

    @Override
    public void stop() {
        if (this.spider instanceof Crawler clinger) {
            clinger.monstrous$setSurface(null);
        }
        Vec3 dm = this.spider.getDeltaMovement();
        this.spider.setDeltaMovement(dm.x * 0.2D, Math.min(dm.y, 0.0D), dm.z * 0.2D);
        if (this.stuck >= 20) this.cooldown = 20;
        this.path = null;
    }
}