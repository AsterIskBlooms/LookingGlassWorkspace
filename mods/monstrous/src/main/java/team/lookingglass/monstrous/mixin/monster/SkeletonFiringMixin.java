package team.lookingglass.monstrous.mixin.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RangedBowAttackGoal.class)
public abstract class SkeletonFiringMixin {
    @Unique private static final float RETREAT_DIST_SQR = 6.0F * 6.0F;
    @Shadow @Final private Monster mob;

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/control/MoveControl;strafe(FF)V"))
    private void monstrous$retreatStrafe(MoveControl<Skeleton> control, float forward, float right) {
        LivingEntity target = this.mob.getTarget();
        boolean skeleton = this.mob instanceof AbstractSkeleton;

        if (skeleton && target != null && this.mob.distanceToSqr(target) < RETREAT_DIST_SQR) {
            forward = -1.0F;
        }

        control.strafe(forward, right);

        if (skeleton && target != null && forward < 0.0F) {
            this.monstrous$hopObstacle(target);
        }
    }

    @Unique
    private void monstrous$hopObstacle(LivingEntity target) {
        if (!this.mob.onGround()) return;

        Vec3 away = this.mob.position().subtract(target.position());
        Vec3 dir = new Vec3(away.x, 0.0D, away.z);
        if (dir.lengthSqr() < 1.0E-6D) return;
        dir = dir.normalize();

        Level level = this.mob.level();
        BlockPos feet = BlockPos.containing(
                this.mob.getX() + dir.x * 0.9D,
                this.mob.getY(),
                this.mob.getZ() + dir.z * 0.9D
        );
        BlockPos above = feet.above();
        BlockPos above2 = feet.above(2);

        boolean blocked = !level.getBlockState(feet).getCollisionShape(level, feet).isEmpty();
        boolean roomToHop = level.getBlockState(above).getCollisionShape(level, above).isEmpty()
                && level.getBlockState(above2).getCollisionShape(level, above2).isEmpty();

        if (blocked && roomToHop) {
            this.mob.getJumpControl().jump();
        }
    }
}