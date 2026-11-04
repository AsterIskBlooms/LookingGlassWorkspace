package team.lookingglass.elsewhere.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import team.lookingglass.mirrorlib.world.OverworldBiomeEvents;
import team.lookingglass.mirrorlib.world.OverworldBiomeView;
import team.lookingglass.mirrorlib.world.WorldOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public final class EBiomePlacements {

    public static void register() {
        OverworldBiomeEvents.MODIFY_GRID.register(EBiomePlacements::modifyGrid);
        OverworldBiomeEvents.PICK_BEACH.register(EBiomePlacements::pickBeach);
        OverworldBiomeEvents.PICK_BADLANDS.register(EBiomePlacements::pickBadlands);
        OverworldBiomeEvents.REWRITE_SURFACE.register(EBiomePlacements::rewriteSurface);
        OverworldBiomeEvents.ADD_UNDERGROUND.register(EBiomePlacements::addUnderground);
    }

    private static List<WorldOptions.SurfaceEntry> rewriteSurface(OverworldBiomeView view, WorldOptions.SurfaceEntry entry) {
        Climate.Parameter[] temperatures = view.temperatures();
        Climate.Parameter[] erosions = view.erosions();
        Climate.Parameter[] humidities = view.humidities();
        boolean bogBox = entry.temperature() == temperatures[0]
                && entry.erosion().equals(erosions[6])
                && entry.continentalness().max() >= Climate.Parameter.point(1.0F).max();
        ResourceKey<Biome> biome = entry.biome();
        if (biome == Biomes.RIVER && entry.temperature() == view.unfrozenRange()) {
            ResourceKey<Biome>[] rivers = new ResourceKey[]{Biomes.FROZEN_RIVER, EBiomes.COLD_RIVER, Biomes.RIVER, EBiomes.LUKEWARM_RIVER, EBiomes.LUKEWARM_RIVER};
            List<WorldOptions.SurfaceEntry> out = new ArrayList<>();
            for (int i = 1; i < temperatures.length; i++) {
                out.add(new WorldOptions.SurfaceEntry(temperatures[i], entry.humidity(), entry.continentalness(), entry.erosion(), entry.weirdness(), entry.offset(), rivers[i]));
            }
            return out;
        }
        if (biome == Biomes.SWAMP) {
            List<WorldOptions.SurfaceEntry> out = new ArrayList<>();
            for (int t = 0; t < temperatures.length; t++) {
                for (Climate.Parameter humidity : humidities) {
                    out.add(new WorldOptions.SurfaceEntry(temperatures[t], humidity, entry.continentalness(), entry.erosion(), entry.weirdness(), entry.offset(), pickSwampBiome(t, entry.weirdness())));
                }
            }
            return out;
        }
        if (biome == Biomes.MANGROVE_SWAMP || bogBox) {
            return List.of();
        }
        return List.of(entry);
    }

    private static void modifyGrid(OverworldBiomeView view) {
        fillTable(view.table(WorldOptions.BiomeTable.MIDDLE), new ResourceKey[][]{
                null,
                { EBiomes.TUNDRA, EBiomes.TUNDRA, Biomes.TAIGA, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                { Biomes.PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.FOREST, Biomes.DARK_FOREST },
                { Biomes.SAVANNA, Biomes.SAVANNA, EBiomes.SHRUBLAND, Biomes.SPARSE_JUNGLE, Biomes.JUNGLE },
                { EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.LUSH_DESERT }
        });
        fillTable(view.table(WorldOptions.BiomeTable.MIDDLE_VARIANT), new ResourceKey[][]{
                null,
                { EBiomes.TUNDRA, null, Biomes.DAPPLED_FOREST, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                { Biomes.SUNFLOWER_PLAINS, null, null, Biomes.BIRCH_FOREST, null },
                { EBiomes.STEPPE, EBiomes.STEPPE, EBiomes.CEDAR_FOREST, null, Biomes.BAMBOO_JUNGLE },
                { Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, null }
        });
        fillTable(view.table(WorldOptions.BiomeTable.PLATEAU), new ResourceKey[][]{
                null,
                { Biomes.CHERRY_GROVE, Biomes.CHERRY_GROVE, Biomes.FLOWER_FOREST, Biomes.FLOWER_FOREST, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                { Biomes.SUNFLOWER_PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.PALE_GARDEN },
                { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, EBiomes.CEDAR_FOREST, EBiomes.CEDAR_FOREST, Biomes.JUNGLE },
                null
        });
        fillTable(view.table(WorldOptions.BiomeTable.PLATEAU_VARIANT), new ResourceKey[][]{
                { null, null, null, null, null },
                { Biomes.MEADOW, Biomes.MEADOW, Biomes.DAPPLED_FOREST, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                { null, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST, null },
                { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, null, null, Biomes.BAMBOO_JUNGLE },
                { Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, EBiomes.CLOUD_FOREST, EBiomes.CLOUD_FOREST }
        });
    }

    private static void fillTable(ResourceKey<Biome>[][] target, ResourceKey<Biome>[][] source) {
        for (int t = 0; t < target.length; t++) {
            if (source[t] == null) continue;
            System.arraycopy(source[t], 0, target[t], 0, target[t].length);
        }
    }

    private static ResourceKey<Biome> pickMiddleBiome(OverworldBiomeView view, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        ResourceKey<Biome> base = view.table(WorldOptions.BiomeTable.MIDDLE)[temperatureIndex][humidityIndex];
        if (weirdness.max() < 0L) return base;
        ResourceKey<Biome> variant = view.table(WorldOptions.BiomeTable.MIDDLE_VARIANT)[temperatureIndex][humidityIndex];
        return variant == null ? base : variant;
    }

    private static ResourceKey<Biome> pickBeach(OverworldBiomeView view, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness, ResourceKey<Biome> current) {
        if (temperatureIndex == 4) return pickMiddleBiome(view, temperatureIndex, humidityIndex, weirdness);
        if (temperatureIndex >= 2) return humidityIndex >= 3 ? EBiomes.TIDEPOOLS : Biomes.BEACH;
        if (temperatureIndex == 1) return humidityIndex >= 3 ? EBiomes.TIDEPOOLS : EBiomes.COLD_BEACH;
        return humidityIndex >= 3 ? Biomes.SNOWY_BEACH : EBiomes.FROZEN_BEACH;
    }

    private static ResourceKey<Biome> pickBadlands(OverworldBiomeView view, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness, ResourceKey<Biome> current) {
        return humidityIndex >= 3 && weirdness.max() >= 0L ? EBiomes.CLOUD_FOREST : current;
    }

    private static ResourceKey<Biome> pickSwampBiome(int temperatureIndex, Climate.Parameter weirdness) {
        if (temperatureIndex == 0) return EBiomes.BOG;
        if (temperatureIndex <= 2) return Biomes.SWAMP;
        return Biomes.MANGROVE_SWAMP;
    }

    private static void addUnderground(OverworldBiomeView view, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes) {
        Climate.Parameter full = view.fullRange();
        biomes.accept(Pair.of(Climate.parameters(full, full, full, full, Climate.Parameter.span(0.2F, 1.1F), full, 0.1F), EBiomes.CAVES));
        view.addUnderground(biomes, full, Climate.Parameter.span(-1.0F, 0.7F), Climate.Parameter.span(-1.05F, 0.8F), Climate.Parameter.span(0.775F, 1.0F), full, 0.0F, EBiomes.CRYSTAL_CAVERNS);
        view.addDeepUnderground(biomes, Climate.Parameter.span(0.2F, 1.0F), Climate.Parameter.span(0.1F, 1.0F), Climate.Parameter.span(-0.455F, 0.3F), Climate.Parameter.span(-0.375F, 1.0F), full, 0.0F, EBiomes.VOLCANIC_DEPTHS);
    }
}
