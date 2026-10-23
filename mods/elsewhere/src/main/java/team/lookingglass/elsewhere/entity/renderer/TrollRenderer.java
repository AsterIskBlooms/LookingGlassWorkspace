package team.lookingglass.elsewhere.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.Troll;
import team.lookingglass.elsewhere.entity.model.TrollModel;
import team.lookingglass.elsewhere.entity.renderer.layer.StolenItemLayer;
import team.lookingglass.elsewhere.entity.renderer.states.TrollRenderState;

public class TrollRenderer extends MobRenderer<Troll, TrollRenderState, TrollModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/troll/troll.png");
    private static final Identifier ANGRY_TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/troll/troll_angry.png");

    public TrollRenderer(final EntityRendererProvider.Context context) {
        super(context, new TrollModel(context.bakeLayer(TrollModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new StolenItemLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(final TrollRenderState state) {
        return state.angry ? ANGRY_TEXTURE : TEXTURE;
    }

    @Override
    public TrollRenderState createRenderState() {
        return new TrollRenderState();
    }

    @Override
    public void extractRenderState(final Troll entity, final TrollRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.angry = entity.isAngry();
        state.attackTime = entity.getSwingAnimation(partialTicks);

        ItemStack stolen = entity.getStolenItem();
        if (!stolen.isEmpty()) {
            this.itemModelResolver.updateForLiving(state.stolenItem, stolen, ItemDisplayContext.GROUND, entity);
        } else {
            state.stolenItem.clear();
        }
    }
}