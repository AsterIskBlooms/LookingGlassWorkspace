package team.lookingglass.elsewhere.entity.renderer.states;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class TrollRenderState extends LivingEntityRenderState {
    public boolean angry;
    public float attackTime;
    public final ItemStackRenderState stolenItem = new ItemStackRenderState();
}