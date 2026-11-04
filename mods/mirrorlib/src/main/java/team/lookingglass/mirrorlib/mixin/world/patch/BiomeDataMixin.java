package team.lookingglass.mirrorlib.mixin.world.patch;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.BiomeData;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.mirrorlib.MirrorLib;
import team.lookingglass.mirrorlib.world.WorldPatch;

@Mixin(BiomeData.class)
public abstract class BiomeDataMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void elsewhere$addBiomes(BootstrapContext<Biome> context, CallbackInfo ci) {
        if (MirrorLib.DATAGEN) return;
        WorldPatch.applyBiomes(context);
    }
}