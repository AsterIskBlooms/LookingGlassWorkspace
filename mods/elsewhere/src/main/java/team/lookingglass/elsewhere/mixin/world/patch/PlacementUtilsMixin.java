package team.lookingglass.elsewhere.mixin.world.patch;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.worldgen.EEnv;
import team.lookingglass.elsewhere.worldgen.features.EPlacements;

@Mixin(PlacementUtils.class)
public abstract class PlacementUtilsMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void elsewhere$addPlacedFeatures(BootstrapContext<PlacedFeature> context, CallbackInfo ci) {
        if (EEnv.DATAGEN) return;
        EPlacements.bootstrap(context);
    }
}