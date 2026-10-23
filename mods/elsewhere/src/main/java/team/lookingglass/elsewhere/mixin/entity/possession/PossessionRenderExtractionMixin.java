package team.lookingglass.elsewhere.mixin.entity.possession;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.entity.renderer.states.PossessedRenderState;
import team.lookingglass.elsewhere.entity.util.PossessionAccess;

@Mixin(LivingEntityRenderer.class)
public abstract class PossessionRenderExtractionMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("TAIL"))
    private void elsewhere$extractPossession(final T entity, final S state, final float partialTicks, final CallbackInfo ci) {
        ((PossessedRenderState) state).elsewhere$setPossessed(((PossessionAccess) entity).elsewhere$isPossessed());
    }
}
