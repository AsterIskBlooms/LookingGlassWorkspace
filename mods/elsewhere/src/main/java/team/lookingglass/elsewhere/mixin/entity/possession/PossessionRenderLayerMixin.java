package team.lookingglass.elsewhere.mixin.entity.possession;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import team.lookingglass.elsewhere.entity.renderer.PossessionRenderTypes;
import team.lookingglass.elsewhere.entity.renderer.states.PossessedRenderState;

@Mixin(RenderLayer.class)
public abstract class PossessionRenderLayerMixin {
    /**
     * @author asterisk
     * @reason fucking souls dude.
     */
    @Overwrite
    protected static <S extends LivingEntityRenderState> void renderColoredCutoutModel(final Model<? super S> model, final Identifier texture, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final S state, final int color, final int order) {
        RenderType renderType = RenderTypes.entityCutout(texture);
        if (state instanceof PossessedRenderState possessed && possessed.elsewhere$isPossessed()) {
            renderType = PossessionRenderTypes.possessedEntity(texture);
        }

        submitNodeCollector.order(order).submitModel(model, state, poseStack, renderType, lightCoords, LivingEntityRenderer.getOverlayCoords(state, 0.0F), color, null, state.outlineColor);
    }
}