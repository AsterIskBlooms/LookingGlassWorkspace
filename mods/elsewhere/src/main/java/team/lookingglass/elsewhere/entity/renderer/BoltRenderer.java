package team.lookingglass.elsewhere.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.model.BoltModel;
import team.lookingglass.elsewhere.registry.util.bolt.Bolt;

@Environment(EnvType.CLIENT)
public class BoltRenderer extends EntityRenderer<Bolt, ArrowRenderState> {
    private final BoltModel model;
    private final Identifier texture;

    public BoltRenderer(EntityRendererProvider.Context context, String textureName) {
        super(context);
        this.model = new BoltModel(context.bakeLayer(BoltModel.LAYER));
        this.texture = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/projectiles/" + textureName + ".png");
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    public void submit(ArrowRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.rotateDegrees(Axis.YP, state.yRot - 90.0F);
        poseStack.rotateDegrees(Axis.ZP, state.xRot);
        collector.submitModel(this.model, state, poseStack, this.texture, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
        super.submit(state, poseStack, collector, camera);
    }

    @Override
    public void extractRenderState(Bolt entity, ArrowRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.xRot = entity.getXRot(partialTicks);
        state.yRot = entity.getYRot(partialTicks);
        state.shake = (float) entity.shakeTime - partialTicks;
    }
}
