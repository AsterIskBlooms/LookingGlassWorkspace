package team.lookingglass.monstrous.goal.spider;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import team.lookingglass.monstrous.render.WebAiming;

public class WebShotGoal extends Goal {
    private final Mob mob;
    private final float minDistSqr;
    private final float maxDistSqr;
    private final int windupTicks;
    private final int cooldownTicks;
    private int windupRemaining;
    private long nextShotTime;

    public WebShotGoal(Mob mob, float minDist, float maxDist, int windupTicks, int cooldownTicks) {
        this.mob = mob;
        this.minDistSqr = minDist * minDist;
        this.maxDistSqr = maxDist * maxDist;
        this.windupTicks = windupTicks;
        this.cooldownTicks = cooldownTicks;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    private boolean validTarget(LivingEntity target) {
        if (target == null || !target.isAlive()) return false;
        double distSqr = this.mob.distanceToSqr(target);
        return distSqr >= this.minDistSqr
                && distSqr <= this.maxDistSqr
                && this.mob.getSensing().hasLineOfSight(target);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        if (!this.validTarget(target)) return false;
        if (this.mob.level().getGameTime() < this.nextShotTime) return false;
        return !this.mob.level().getBlockState(target.blockPosition()).is(Blocks.COBWEB);
    }

    @Override
    public boolean canContinueToUse() {
        return this.windupRemaining > 0 && this.validTarget(this.mob.getTarget());
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target == null) return;

        this.mob.getNavigation().stop();
        this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (--this.windupRemaining <= 0) {
            this.shoot(target);
            this.windupRemaining = 0;
        }
    }

    @Override
    public void start() {
        this.windupRemaining = this.windupTicks;
        this.mob.getNavigation().stop();
        this.mob.playSound(SoundEvents.SPIDER_AMBIENT, 1.0F, 1.5F);

        if (this.mob instanceof WebAiming aiming) {
            aiming.monstrous$setAiming(true);
        }
    }

    @Override
    public void stop() {
        if (this.mob instanceof WebAiming aiming) {
            aiming.monstrous$setAiming(false);
        }
    }

    private void shoot(LivingEntity target) {
        if (!(this.mob.level() instanceof ServerLevel level)) return;

        this.nextShotTime = level.getGameTime() + this.cooldownTicks;

        Vec3 from = new Vec3(this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        Vec3 to = new Vec3(target.getX(), target.getY(0.2F), target.getZ());
        this.spawnTrail(level, from, to);

        this.placeWeb(level, target.blockPosition());
        this.mob.playSound(SoundEvents.COBWEB.placeSound().orElseThrow(), 1.2F, 1.0F);
    }

    private void placeWeb(ServerLevel level, BlockPos pos) {
        if (!level.getBlockState(pos).isAir()) return;
        level.setBlockAndUpdate(pos, Blocks.COBWEB.defaultBlockState());
    }

    private void spawnTrail(ServerLevel level, Vec3 from, Vec3 to) {
        ItemParticleOption web = new ItemParticleOption(
                ParticleTypes.ITEM,
                ItemStackTemplate.fromNonEmptyStack(new ItemStack(Items.COBWEB))
        );
        Vec3 line = to.subtract(from);
        int steps = Math.max(1, (int) (line.length() * 3.0F));

        for (int i = 0; i <= steps; i++) {
            Vec3 p = from.add(line.scale((float) i / steps));
            level.sendParticles(web, p.x, p.y, p.z, 1, 0.0F, 0.0F, 0.0F, 0.0F);
        }
    }
}