package team.lookingglass.elsewhere.mixin.world;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.worldgen.EBiomes;
import team.lookingglass.mirrorlib.mixin.OverworldBiomeGenAccessor;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public abstract class OverworldBiomeGenMixin {

    @Shadow
    protected abstract ResourceKey<Biome> pickMiddleBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness);

    // Change Surface Biomes
    @SuppressWarnings("unchecked")
    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectCustomWorldgen(CallbackInfo ci) {
        OverworldBiomeGenAccessor accessor = (OverworldBiomeGenAccessor) this;

        // New Biome Spread
        accessor.setMiddleBiomes(new ResourceKey[][]{
                { EBiomes.TUNDRA, EBiomes.TUNDRA, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA },
                { EBiomes.CHERRY_FIELDS, Biomes.MEADOW, Biomes.TAIGA, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                { Biomes.PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.FOREST, Biomes.DARK_FOREST },
                { Biomes.SAVANNA, Biomes.SAVANNA, EBiomes.SHRUBLAND, Biomes.SPARSE_JUNGLE, Biomes.JUNGLE },
                { EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.LUSH_DESERT }
        });
        accessor.setMiddleBiomesVariant(new ResourceKey[][]{
                { Biomes.ICE_SPIKES, null, Biomes.SNOWY_TAIGA, null, null },
                { Biomes.MEADOW, null, Biomes.DAPPLED_FOREST, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                { Biomes.SUNFLOWER_PLAINS, null, null, Biomes.BIRCH_FOREST, null },
                { EBiomes.STEPPE, EBiomes.STEPPE, EBiomes.CEDAR_FOREST, null, Biomes.BAMBOO_JUNGLE },
                { Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, null }
        });
        accessor.setPlateauBiomes(new ResourceKey[][]{
                { Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA },
                { Biomes.CHERRY_GROVE, Biomes.CHERRY_GROVE, Biomes.FLOWER_FOREST, Biomes.FLOWER_FOREST, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                { Biomes.SUNFLOWER_PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.PALE_GARDEN },
                { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, EBiomes.CEDAR_FOREST, EBiomes.CEDAR_FOREST, Biomes.JUNGLE },
                { Biomes.BADLANDS, Biomes.BADLANDS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS}
        });
        accessor.setPlateauBiomesVariant(new ResourceKey[][]{
                { null, null, null, null, null },
                { Biomes.MEADOW, Biomes.MEADOW, Biomes.DAPPLED_FOREST, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                { null, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST, null },
                { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, null, null, Biomes.BAMBOO_JUNGLE },
                { Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, EBiomes.CLOUD_FOREST, EBiomes.CLOUD_FOREST }
        });
    }

    @Unique
    private ResourceKey<Biome> elsewhere$pickBeachBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        if (temperatureIndex == 4) {
            return this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness);
        }
        else if (temperatureIndex >= 2) { return humidityIndex >= 3 ? EBiomes.TIDEPOOLS : Biomes.BEACH; }
        else if (temperatureIndex == 1) { return humidityIndex >= 3 ? EBiomes.TIDEPOOLS : EBiomes.COLD_BEACH; }
        else if (temperatureIndex == 0) { return humidityIndex >= 3 ? Biomes.SNOWY_BEACH : EBiomes.FROZEN_BEACH; }
        else { return Biomes.BEACH; }
    }

    // Cave Biomes
    @Inject(method = "addUndergroundBiomes", at = @At("TAIL"))
    private void injectCustomCaveBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, CallbackInfo ci) {
        OverworldBiomeGenAccessor accessor = (OverworldBiomeGenAccessor) this;

        biomes.accept(Pair.of(Climate.parameters(
                accessor.getFULL_RANGE(), accessor.getFULL_RANGE(), accessor.getFULL_RANGE(), accessor.getFULL_RANGE(),
                        Climate.Parameter.span(0.2F, 1.1F), accessor.getFULL_RANGE(), 0.1F
                ),
                EBiomes.CAVES));

        accessor.invokeAddUndergroundBiome(biomes,
                accessor.getFULL_RANGE(),
                Climate.Parameter.span(-1.0F, 0.7F),
                Climate.Parameter.span(-1.05F, 0.8F),
                Climate.Parameter.span(0.775F, 1.0F),
                accessor.getFULL_RANGE(),
                0.0F,
                EBiomes.CRYSTAL_CAVERNS
        );

        addDeepUndergroundBiome(biomes,
                Climate.Parameter.span(0.2F, 1.0F),
                Climate.Parameter.span(0.1F, 1.0F),
                Climate.Parameter.span(-0.455F, 0.3F),
                Climate.Parameter.span(-0.375F, 1.0F),
                accessor.getFULL_RANGE(),
                0.0F,
                EBiomes.VOLCANIC_DEPTHS
        );
    }

    // River Biomes
    @SuppressWarnings("unchecked")
    @Redirect(method = "addValleys", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;addSurfaceBiome(Ljava/util/function/Consumer;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;FLnet/minecraft/resources/ResourceKey;)V"))
    private void elsewhere$splitRiverTemperatures(OverworldBiomeBuilder builder,
                                                  Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes,
                                                  Climate.Parameter temperature,
                                                  Climate.Parameter humidity,
                                                  Climate.Parameter continentalness,
                                                  Climate.Parameter erosion,
                                                  Climate.Parameter weirdness,
                                                  float offset,
                                                  ResourceKey<Biome> biome
    ) {
        OverworldBiomeGenAccessor accessor = (OverworldBiomeGenAccessor)(Object) builder;
        if (biome == Biomes.RIVER && temperature == accessor.getUnfrozenRange()) {
            Climate.Parameter[] temperatures = accessor.getTemperatures();
            ResourceKey<Biome>[] rivers = new ResourceKey[]{Biomes.FROZEN_RIVER, EBiomes.COLD_RIVER, Biomes.RIVER, EBiomes.LUKEWARM_RIVER, EBiomes.LUKEWARM_RIVER};
            for (int i = 1; i < temperatures.length; i++) {
                accessor.invokeAddSurfaceBiome(biomes, temperatures[i], humidity, continentalness, erosion, weirdness, offset, rivers[i]);
            }
        } else {
            accessor.invokeAddSurfaceBiome(biomes, temperature, humidity, continentalness, erosion, weirdness, offset, biome);
        }
    }

    @Unique
    private void addDeepUndergroundBiome(
            final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes,
            final Climate.Parameter temperature,
            final Climate.Parameter humidity,
            final Climate.Parameter continentalness,
            final Climate.Parameter erosion,
            final Climate.Parameter weirdness,
            final float offset,
            final ResourceKey<Biome> biome
    ) {
        biomes.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.9F, 1.1F), weirdness, offset), biome));
    }

    // Stop lower caves from generating as surface biomes
    @Redirect(method = "addSurfaceBiome", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 1))
    private void elsewhere$skipUndergroundSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Object pair) {}

    @Redirect(method = "addMidSlice", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;pickBeachBiome(II)Lnet/minecraft/resources/ResourceKey;"))
    private ResourceKey<Biome> elsewhere$redirectBeachMidSlice(OverworldBiomeBuilder instance, int temperatureIndex, int humidityIndex, @Local(argsOnly = true, name = "weirdness") Climate.Parameter weirdness) {
        return elsewhere$pickBeachBiome(temperatureIndex, humidityIndex, weirdness);
    }
    @Redirect(method = "addLowSlice", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;pickBeachBiome(II)Lnet/minecraft/resources/ResourceKey;"))
    private ResourceKey<Biome> elsewhere$redirectBeachLowSlice(OverworldBiomeBuilder instance, int temperatureIndex, int humidityIndex, @Local(argsOnly = true, name = "weirdness") Climate.Parameter weirdness) {
        return elsewhere$pickBeachBiome(temperatureIndex, humidityIndex, weirdness);
    }
    @Redirect(method = "pickShatteredCoastBiome", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;pickBeachBiome(II)Lnet/minecraft/resources/ResourceKey;"))
    private ResourceKey<Biome> elsewhere$redirectBeachShatteredCoast(OverworldBiomeBuilder instance, int temperatureIndex, int humidityIndex, @Local(argsOnly = true, name = "weirdness") Climate.Parameter weirdness) {
        return elsewhere$pickBeachBiome(temperatureIndex, humidityIndex, weirdness);
    }
}