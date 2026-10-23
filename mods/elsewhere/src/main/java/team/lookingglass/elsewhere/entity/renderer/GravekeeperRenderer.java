package team.lookingglass.elsewhere.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.Gravekeeper;
import team.lookingglass.elsewhere.entity.model.GravekeeperModel;
import team.lookingglass.elsewhere.entity.renderer.states.GravekeeperRenderState;

public class GravekeeperRenderer extends MobRenderer<Gravekeeper, GravekeeperRenderState, GravekeeperModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/gravekeeper/gravekeeper.png");

    public GravekeeperRenderer(EntityRendererProvider.Context context) {
        super(context, new GravekeeperModel(context.bakeLayer(GravekeeperModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(GravekeeperRenderState state) {
        return TEXTURE;
    }

    @Override
    public GravekeeperRenderState createRenderState() {
        return new GravekeeperRenderState();
    }

    @Override
    public void extractRenderState(Gravekeeper entity, GravekeeperRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.attackAnim = entity.getSwingAnimation(partialTick);
        state.casting = entity.isCasting();
    }
}