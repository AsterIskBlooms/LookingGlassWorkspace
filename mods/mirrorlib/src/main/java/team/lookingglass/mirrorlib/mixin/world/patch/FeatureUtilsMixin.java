package team.lookingglass.mirrorlib.mixin.world.patch;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.mirrorlib.MirrorLib;
import team.lookingglass.mirrorlib.world.WorldPatch;

@Mixin(FeatureUtils.class)
public abstract class FeatureUtilsMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void elsewhere$addConfiguredFeatures(BootstrapContext<Feature> context, CallbackInfo ci) {
        if (MirrorLib.DATAGEN) return;
        WorldPatch.applyFeatures(context);
    }
}