package team.lookingglass.elsewhere.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.Werewolf;
import team.lookingglass.elsewhere.entity.model.WerewolfModel;
import team.lookingglass.elsewhere.entity.renderer.states.WerewolfRenderState;

@Environment(EnvType.CLIENT)
public class WerewolfRenderer extends MobRenderer<Werewolf, WerewolfRenderState, WerewolfModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/werewolf/werewolf.png");

    public WerewolfRenderer(final EntityRendererProvider.Context context) {
        super(context, new WerewolfModel(context.bakeLayer(WerewolfModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public Identifier getTextureLocation(final WerewolfRenderState state) {
        return TEXTURE;
    }

    @Override
    public WerewolfRenderState createRenderState() {
        return new WerewolfRenderState();
    }

    @Override
    public void extractRenderState(final Werewolf entity, final WerewolfRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.preparingCharge = entity.isPreparingCharge();
        state.charging = entity.isCharging();
        state.chargeWindupProgress = entity.getChargeWindupProgress();
    }
}