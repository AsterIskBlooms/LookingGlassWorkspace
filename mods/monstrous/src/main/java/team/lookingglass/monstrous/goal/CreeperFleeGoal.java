
package team.lookingglass.monstrous.goal;

import java.util.EnumSet;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.Vec3;

public class CreeperFleeGoal extends Goal {
    private static final double DETECTION_RANGE = 8.0D;
    private static final double FLEE_DISTANCE = 10.0D;

    private final Mob mob;
    private Creeper creeper;
    private int repathTicks;

    public CreeperFleeGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.creeper = this.findCreeper();
        return this.creeper != null;
    }

    @Override
    public boolean canContinueToUse() {
        this.creeper = this.findCreeper();
        return this.creeper != null;
    }

    @Override
    public void start() {
        this.repathTicks = 0;
        this.flee();
    }

    @Override
    public void tick() {
        if (--this.repathTicks <= 0) {
            this.repathTicks = 10;
            this.flee();
        }
    }

    private Creeper findCreeper() {
        return this.mob.level().getEntitiesOfClass(
                Creeper.class,
                this.mob.getBoundingBox().inflate(DETECTION_RANGE),
                creeper -> creeper.isAlive()
                        && creeper.getSwellDir() > 0
        ).stream().min((a, b) ->
                Double.compare(
                        this.mob.distanceToSqr(a),
                        this.mob.distanceToSqr(b)
                )
        ).orElse(null);
    }

    private void flee() {
        if (this.creeper == null) return;

        Vec3 away = this.mob.position().subtract(this.creeper.position());
        away = new Vec3(away.x, 0.0D, away.z);

        if (away.lengthSqr() < 1.0E-4D) {
            double angle = this.mob.getRandom().nextDouble() * Math.PI * 2.0D;
            away = new Vec3(Math.cos(angle), 0.0D, Math.sin(angle));
        }

        away = away.normalize();

        double x = this.mob.getX() + away.x * FLEE_DISTANCE;
        double z = this.mob.getZ() + away.z * FLEE_DISTANCE;

        this.mob.getNavigation().moveTo(x, this.mob.getY(), z, 1.25D);
    }

    @Override
    public void stop() {
        this.creeper = null;
        this.mob.getNavigation().stop();
    }
}
