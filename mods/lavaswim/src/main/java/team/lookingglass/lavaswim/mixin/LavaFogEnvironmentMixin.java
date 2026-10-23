package team.lookingglass.lavaswim.mixin;

import net.minecraft.client.renderer.fog.environment.LavaFogEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LavaFogEnvironment.class)
public class LavaFogEnvironmentMixin {
    @ModifyConstant(method = "setupFog", constant = @Constant(floatValue = 5.0F))
    private float lavaswim$fireResistanceFogEnd(float original) {
        return 30.0F;
    }
}
