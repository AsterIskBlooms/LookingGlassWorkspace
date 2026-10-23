package team.lookingglass.elsewhere.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.Sweeper;
import team.lookingglass.elsewhere.entity.model.SweeperModel;
import team.lookingglass.elsewhere.entity.renderer.states.SweeperRenderState;

@Environment(EnvType.CLIENT)
public class SweeperRenderer extends MobRenderer<Sweeper, SweeperRenderState, SweeperModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/sweeper/sweeper.png");

    public SweeperRenderer(final EntityRendererProvider.Context context) {
        super(context, new SweeperModel(context.bakeLayer(SweeperModel.LAYER_LOCATION)), 0.5F);
    }

    protected void scale(final SweeperRenderState state, final PoseStack poseStack) {
        float g = state.swelling;
        float wobble = 1.0F + Mth.sin((double)(g * 100.0F)) * g * 0.01F;
        g = Mth.clampedLerp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float s = (1.0F + g * 0.4F) * wobble;
        float hs = (1.0F + g * 0.1F) / wobble;
        poseStack.scale(s, hs, s);
    }

    protected float getWhiteOverlayProgress(final SweeperRenderState state) {
        float step = state.swelling;
        return (int)(step * 10.0F) % 2 == 0 ? 0.0F : Mth.clampedLerp(step, 0.5F, 1.0F);
    }

    public Identifier getTextureLocation(final SweeperRenderState state) {
        return TEXTURE;
    }

    public SweeperRenderState createRenderState() {
        return new SweeperRenderState();
    }

    public void extractRenderState(final Sweeper entity, final SweeperRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.swelling = entity.getSwelling(partialTicks);
        state.chasing = entity.isChasing();
    }
}
