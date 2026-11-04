package team.lookingglass.mirrorlib.world;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import team.lookingglass.mirrorlib.mixin.world.OverworldBiomeGenAccessor;

import java.util.function.Consumer;

record AccessorView(OverworldBiomeGenAccessor accessor) implements OverworldBiomeView {
    @Override
    public Climate.Parameter[] temperatures() {
        return accessor.getTemperatures();
    }

    @Override
    public Climate.Parameter[] humidities() {
        return accessor.getHumidities();
    }

    @Override
    public Climate.Parameter[] erosions() {
        return accessor.getErosions();
    }

    @Override
    public Climate.Parameter unfrozenRange() {
        return accessor.getUnfrozenRange();
    }

    @Override
    public Climate.Parameter fullRange() {
        return accessor.getFULL_RANGE();
    }

    @Override
    public ResourceKey<Biome>[][] table(WorldOptions.BiomeTable table) {
        return switch (table) {
            case MIDDLE -> accessor.getMiddleBiomes();
            case MIDDLE_VARIANT -> accessor.getMiddleBiomesVariant();
            case PLATEAU -> accessor.getPlateauBiomes();
            case PLATEAU_VARIANT -> accessor.getPlateauBiomesVariant();
            case SHATTERED -> accessor.getShatteredBiomes();
        };
    }

    @Override
    public void addUnderground(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
        accessor.invokeAddUndergroundBiome(biomes, temperature, humidity, continentalness, erosion, weirdness, offset, biome);
    }

    @Override
    public void addDeepUnderground(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
        biomes.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.9F, 1.1F), weirdness, offset), biome));
    }
}
