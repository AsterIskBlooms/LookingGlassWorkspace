package team.lookingglass.lavaswim.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    @ModifyExpressionValue(method = "submit", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/state/level/PlayerRenderState;isOnFire:Z"))
    private boolean lavaswim$hideFireWhenResistant(boolean original) {
        return original && !(Minecraft.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasEffect(MobEffects.FIRE_RESISTANCE));
    }
}
