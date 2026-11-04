package team.lookingglass.mirrorlib.mixin.world.patch;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.mirrorlib.MirrorLib;
import team.lookingglass.mirrorlib.world.WorldPatch;

@Mixin(PlacementUtils.class)
public abstract class PlacementUtilsMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void elsewhere$addPlacedFeatures(BootstrapContext<PlacedFeature> context, CallbackInfo ci) {
        if (MirrorLib.DATAGEN) return;
        WorldPatch.applyPlacements(context);
    }
}