package team.lookingglass.elsewhere.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.joml.Matrix4fc;
import team.lookingglass.elsewhere.entity.renderer.states.TrollRenderState;

public class StolenItemLayer<S extends TrollRenderState, M extends EntityModel<? super S>> extends RenderLayer<S, M> {
    public StolenItemLayer(final RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final S state, final float yRot, final float xRot) {
        if (state.stolenItem.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate(0.0F, -0.5F, 0.0F);
        poseStack.rotateDegrees(Axis.XP, 180.0F);
        poseStack.rotateDegrees(Axis.YP, 180.0F + state.ageInTicks * 4.0F);
        poseStack.scale(1.4F, 1.4F, 1.4F);
        state.stolenItem.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
    }
}
