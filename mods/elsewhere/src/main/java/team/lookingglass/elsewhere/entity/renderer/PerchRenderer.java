package team.lookingglass.elsewhere.entity.renderer;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.creature.Perch;
import team.lookingglass.elsewhere.entity.model.PerchModel;

public class PerchRenderer extends MobRenderer<Perch, LivingEntityRenderState, PerchModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/fish/perch.png");

    public PerchRenderer(EntityRendererProvider.Context context) {
        super(context, new PerchModel(context.bakeLayer(PerchModel.LAYER_LOCATION)), 0.15F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
