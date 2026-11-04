package team.lookingglass.mirrorlib.world;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.function.Consumer;

public class WorldOptions {
    public enum BiomeTable { MIDDLE, MIDDLE_VARIANT, PLATEAU, PLATEAU_VARIANT, SHATTERED }

    public record SurfaceEntry(Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<net.minecraft.world.level.biome.Biome> biome) {}

    @FunctionalInterface
    public interface GridModifier {
        void add(OverworldBiomeView view);
    }

    @FunctionalInterface
    public interface CaveModifier {
        void add(OverworldBiomeView view, Consumer<Pair<Climate.ParameterPoint, ResourceKey<net.minecraft.world.level.biome.Biome>>> biomes);
    }

    @FunctionalInterface
    public interface Surface {
        List<SurfaceEntry> rewrite(OverworldBiomeView view, SurfaceEntry entry);
    }

    @FunctionalInterface
    public interface Biome {
        ResourceKey<net.minecraft.world.level.biome.Biome> pick(OverworldBiomeView view, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness, ResourceKey<net.minecraft.world.level.biome.Biome> current);
    }
}
