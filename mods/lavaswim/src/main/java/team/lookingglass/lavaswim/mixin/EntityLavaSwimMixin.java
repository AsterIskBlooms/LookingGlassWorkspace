package team.lookingglass.lavaswim.mixin;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.lavaswim.LavaSwimmingBehavior;

@SuppressWarnings("deprecation")
@Mixin(Entity.class)
public abstract class EntityLavaSwimMixin {
    @Inject(method = "updateSwimming", at = @At("HEAD"), cancellable = true)
    private void lavaswim$updateLavaSwimming(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (LavaSwimmingBehavior.cantSwimInLava(self)) {
            return;
        }
        if (self.isSwimming()) {
            boolean stay = self.isInLava() && !self.isPassenger();
            if (self.level().isClientSide()) {
                boolean forward = self instanceof LivingEntity l && l.zza > 1.0E-3F;
                stay &= forward || self.isSprinting();
            }
            self.setSwimming(stay);
        } else {
            self.setSwimming(self.isSprinting()
                    && self.isEyeInFluid(FluidTags.LAVA)
                    && !self.isPassenger()
                    && self.level().getFluidState(self.blockPosition()).is(FluidTags.LAVA));
        }
        ci.cancel();
    }
}
