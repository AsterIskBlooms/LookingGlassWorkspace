package team.lookingglass.mirrorlib.mixin.world;

import com.mojang.datafixers.util.Pair;
import java.util.function.Consumer;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.mirrorlib.world.region.RegionBiomeBuilder;
import team.lookingglass.mirrorlib.world.region.RegionRegistry;

@Mixin(OverworldBiomeBuilder.class)
public abstract class OverworldBiomeBuilderMixin {
    @Inject(method = "addBiomes", at = @At("HEAD"), cancellable = true)
    private void mirrorlib$addRegisteredBiomes(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, final CallbackInfo ci) {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise || RegionBiomeBuilder.isBuildingLayout()) {
            return;
        }
        RegionRegistry.RegionInfo vanilla = RegionRegistry.freezeAndGet().vanilla();
        if (vanilla.provider() != null) {
            vanilla.provider().addBiomes(biomes);
            ci.cancel();
        }
    }
}