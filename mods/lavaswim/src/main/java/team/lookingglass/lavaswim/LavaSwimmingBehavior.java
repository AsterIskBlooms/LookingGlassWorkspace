package team.lookingglass.lavaswim;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import team.lookingglass.lavaswim.mixin.LivingEntityInvoker;

public final class LavaSwimmingBehavior {
    private static final float LAVA_ACCEL_SCALE = 0.95F;
    private static final float LAVA_DRAG_MULTIPLIER  = 1.0F;
    private LavaSwimmingBehavior() {}

    public static boolean cantSwimInLava(Entity entity) {
        return !(entity instanceof Player player) || !player.hasEffect(MobEffects.FIRE_RESISTANCE);
    }

    public static boolean travelInLava(LivingEntity entity, Vec3 input, double baseGravity, boolean isFalling, double oldY) {
        if (cantSwimInLava(entity) || !entity.isSwimming()) {
            return false;
        }
        entity.resetFallDistance();
        float slowDown = entity.isSprinting() ? 0.9F : 0.8F;
        float speed = 0.02F + (entity.getSpeed() - 0.02F) * LAVA_ACCEL_SCALE;
        float waterWalker = (float) entity.getAttributeValue(Attributes.WATER_MOVEMENT_EFFICIENCY);
        if (!entity.onGround()) {
            waterWalker *= 0.5F;
        }
        if (waterWalker > 0.0F) {
            slowDown += (0.54600006F - slowDown) * waterWalker;
            speed += (entity.getSpeed() - speed) * waterWalker;
        }

        slowDown *= LAVA_DRAG_MULTIPLIER;
        entity.moveRelative(speed, input);
        entity.move(MoverType.SELF, entity.getDeltaMovement());
        Vec3 movement = entity.getDeltaMovement();
        if (entity.horizontalCollision && entity.onClimbable()) {
            movement = new Vec3(movement.x, 0.2, movement.z);
        }
        movement = movement.multiply(slowDown, 0.8, slowDown);
        double lookY = entity.getLookAngle().y;
        if (lookY > 0.0) {
            BlockPos above = BlockPos.containing(entity.getX(), entity.getY() + 0.9, entity.getZ());
            if (entity.level().getFluidState(above).isEmpty()) {
                movement = movement.add(0, (lookY - movement.y) * 0.06, 0);
            }
        }
        entity.setDeltaMovement(entity.getFluidFallingAdjustedMovement(baseGravity, isFalling, movement));
        ((LivingEntityInvoker) entity).lavaswim$jumpOutOfFluid(oldY);
        return true;
    }
}