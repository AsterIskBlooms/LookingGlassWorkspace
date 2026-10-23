package team.lookingglass.elsewhere.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.Whistler;
import team.lookingglass.elsewhere.entity.model.WhistlerModel;
import team.lookingglass.elsewhere.entity.renderer.states.WhistlerRenderState;

@Environment(EnvType.CLIENT)
public class WhistlerRenderer extends MobRenderer<Whistler, WhistlerRenderState, WhistlerModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/whistler/whistler.png");

    public WhistlerRenderer(final EntityRendererProvider.Context context) {
        super(context, new WhistlerModel(context.bakeLayer(WhistlerModel.LAYER_LOCATION)), 0.5F);
    }

    public Identifier getTextureLocation(final WhistlerRenderState state) {
        return TEXTURE;
    }

    public WhistlerRenderState createRenderState() {
        return new WhistlerRenderState();
    }

    public void extractRenderState(final Whistler entity, final WhistlerRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.headSpin = entity.getHeadSpin(partialTicks);
    }
}