package team.lookingglass.elsewhere.entity.renderer.states;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class WerewolfRenderState extends LivingEntityRenderState {
    public boolean preparingCharge;
    public boolean charging;
    public float chargeWindupProgress;
}