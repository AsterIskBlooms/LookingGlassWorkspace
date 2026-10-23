package team.lookingglass.lavaswim.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.lavaswim.LavaSwimmingBehavior;

@Mixin(LivingEntity.class)
public abstract class LivingEntityLavaMixin {
    @Inject(method = "travelInLava", at = @At("HEAD"), cancellable = true)
    private void lavaswim$travelInLava(Vec3 input, double baseGravity, boolean isFalling, double oldY, CallbackInfo ci) {
        if (LavaSwimmingBehavior.travelInLava((LivingEntity) (Object) this, input, baseGravity, isFalling, oldY)) {
            ci.cancel();
        }
    }
}
