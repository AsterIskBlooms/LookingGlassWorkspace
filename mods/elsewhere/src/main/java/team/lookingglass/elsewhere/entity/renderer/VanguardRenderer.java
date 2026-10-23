package team.lookingglass.elsewhere.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.monster.Vanguard;
import team.lookingglass.elsewhere.entity.model.VanguardModel;
import team.lookingglass.elsewhere.entity.renderer.states.VanguardRenderState;

public class VanguardRenderer extends MobRenderer<Vanguard, VanguardRenderState, VanguardModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "textures/entity/vanguard/vanguard.png");
    private final ItemModelResolver itemModelResolver;

    public VanguardRenderer(EntityRendererProvider.Context context) {
        super(context, new VanguardModel(context.bakeLayer(VanguardModel.LAYER_LOCATION)), 0.5F);
        this.itemModelResolver = context.getItemModelResolver();
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public VanguardRenderState createRenderState() {
        return new VanguardRenderState();
    }

    @Override
    public void extractRenderState(Vanguard entity, VanguardRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        VanguardRenderState.extractArmedEntityRenderState(entity, state, itemModelResolver, partialTick);
        state.walkPhase = entity.getWalkPhase(partialTick);
        state.walkWeight = entity.getWalkWeight(partialTick);
        state.blockWeight = entity.getBlockWeight(partialTick);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.blockStartAnimationState.copyFrom(entity.blockStartAnimationState);
        state.blockAnimationState.copyFrom(entity.blockAnimationState);
        state.singleSwingAnimationState.copyFrom(entity.singleSwingAnimationState);
        state.doubleSwingAnimationState.copyFrom(entity.doubleSwingAnimationState);
        state.chargeAnimationState.copyFrom(entity.chargeAnimationState);
        state.stunAnimationState.copyFrom(entity.stunAnimationState);
    }

    @Override
    public Identifier getTextureLocation(VanguardRenderState state) {
        return TEXTURE;
    }
}