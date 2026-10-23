package team.lookingglass.elsewhere.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.MudGolem;
import team.lookingglass.elsewhere.entity.model.MudGolemModel;
import team.lookingglass.elsewhere.entity.renderer.states.MudGolemRenderState;

@Environment(EnvType.CLIENT)
public class MudGolemRenderer extends MobRenderer<MudGolem, MudGolemRenderState, MudGolemModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/mud_golem/mud_golem.png");

    public MudGolemRenderer(final EntityRendererProvider.Context context) {
        super(context, new MudGolemModel(context.bakeLayer(MudGolemModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public Identifier getTextureLocation(final MudGolemRenderState state) {
        return TEXTURE;
    }

    @Override
    public MudGolemRenderState createRenderState() {
        return new MudGolemRenderState();
    }

    @Override
    public void extractRenderState(final MudGolem entity, final MudGolemRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.attackAnim = entity.getSwingProgress(partialTicks);
    }
}