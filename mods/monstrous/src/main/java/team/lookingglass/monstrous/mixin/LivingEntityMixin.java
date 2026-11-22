package team.lookingglass.monstrous.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.render.Crawler;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void monstrous$tickSurface(CallbackInfo ci) {
        if ((Object) this instanceof Crawler clinger) {
            clinger.monstrous$tickSurface();
        }
    }
}