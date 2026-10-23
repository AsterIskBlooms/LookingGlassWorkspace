package team.lookingglass.elsewhere.entity.renderer.states;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class MudGolemRenderState extends LivingEntityRenderState {
    public float attackAnim;
}