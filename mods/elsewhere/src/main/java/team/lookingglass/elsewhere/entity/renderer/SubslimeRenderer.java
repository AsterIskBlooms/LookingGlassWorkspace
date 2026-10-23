package team.lookingglass.elsewhere.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.Subslime;
import team.lookingglass.elsewhere.entity.model.SubslimeModel;
import team.lookingglass.elsewhere.entity.renderer.states.SubslimeRenderState;

public class SubslimeRenderer extends MobRenderer<Subslime, SubslimeRenderState, SubslimeModel> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/subslime/subslime.png");

    public SubslimeRenderer(EntityRendererProvider.Context context) {
        super(context, new SubslimeModel(context.bakeLayer(SubslimeModel.LAYER_LOCATION)), 1.0F);
    }

    @Override
    public SubslimeRenderState createRenderState() {
        return new SubslimeRenderState();
    }

    @Override
    public Identifier getTextureLocation(SubslimeRenderState state) {
        return TEXTURE;
    }

    @Override
    public void extractRenderState(Subslime entity, SubslimeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.squish = Mth.lerp(partialTicks, entity.oSquish, entity.squish);
        state.size = entity.getSize();
        state.attackTime = entity.getAttackAnimationProgress(partialTicks);
    }

    @Override
    public void submit(SubslimeRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public RenderType getRenderType(SubslimeRenderState state, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderTypes.entityTranslucent(getTextureLocation(state));
    }
}