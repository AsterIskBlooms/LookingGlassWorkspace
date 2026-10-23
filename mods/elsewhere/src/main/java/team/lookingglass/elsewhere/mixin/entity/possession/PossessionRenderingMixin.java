package team.lookingglass.elsewhere.mixin.entity.possession;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.elsewhere.entity.renderer.PossessionRenderTypes;
import team.lookingglass.elsewhere.entity.renderer.states.PossessedRenderState;

@Mixin(LivingEntityRenderer.class)
public abstract class PossessionRenderingMixin<T extends LivingEntity, S extends LivingEntityRenderState, M> {
    @Shadow public abstract Identifier getTextureLocation(S state);

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void elsewhere$getPossessedRenderType(
            S state,
            boolean isBodyVisible,
            boolean forceTransparent,
            boolean appearGlowing,
            CallbackInfoReturnable<RenderType> cir
    ) {
        if (((PossessedRenderState) state).elsewhere$isPossessed() && isBodyVisible && !forceTransparent) {
            cir.setReturnValue(PossessionRenderTypes.possessedEntity(this.getTextureLocation(state)));
        }
    }
}
