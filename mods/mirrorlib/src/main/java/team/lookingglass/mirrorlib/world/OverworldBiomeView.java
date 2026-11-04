package team.lookingglass.mirrorlib.world;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import team.lookingglass.mirrorlib.mixin.world.OverworldBiomeGenAccessor;

import java.util.function.Consumer;

public interface OverworldBiomeView {
    Climate.Parameter[] temperatures();
    Climate.Parameter[] humidities();
    Climate.Parameter[] erosions();
    Climate.Parameter unfrozenRange();
    Climate.Parameter fullRange();

    ResourceKey<Biome>[][] table(WorldOptions.BiomeTable table);

    void addUnderground(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome);
    void addDeepUnderground(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome);

    static OverworldBiomeView of(OverworldBiomeGenAccessor accessor) {
        return new AccessorView(accessor);
    }
}
