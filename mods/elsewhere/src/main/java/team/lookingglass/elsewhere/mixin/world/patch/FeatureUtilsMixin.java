package team.lookingglass.elsewhere.mixin.world.patch;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.worldgen.EEnv;
import team.lookingglass.elsewhere.worldgen.features.EFeatures;

@Mixin(FeatureUtils.class)
public abstract class FeatureUtilsMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void elsewhere$addConfiguredFeatures(BootstrapContext<Feature> context, CallbackInfo ci) {
        if (EEnv.DATAGEN) return;
        EFeatures.bootstrap(context);
    }
}