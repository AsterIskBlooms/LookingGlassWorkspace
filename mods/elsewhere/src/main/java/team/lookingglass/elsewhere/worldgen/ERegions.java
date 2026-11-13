package team.lookingglass.elsewhere.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.mirrorlib.world.cave.CaveRegistry;
import team.lookingglass.mirrorlib.world.region.Region;
import team.lookingglass.mirrorlib.world.region.RegionBiomeBuilder;
import team.lookingglass.mirrorlib.world.region.RegionRegistry;

import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public final class ERegions {
    public static final ResourceKey<Region> CHOCOLATE = Region.key(Elsewhere.MODID, "chocolate");

    public static void initialize() {
        RegionRegistry.replaceVanillaRegion(VanillaRegion::addBiomes);
        RegionRegistry.addRegion(CHOCOLATE, 100.0F, ChocolateRegion::addBiomes);

        CaveRegistry.add(u -> {
            Climate.Parameter full = u.fullRange();
            Climate.Parameter[] e = u.erosions();

            u.point(full, full, full, full, Climate.Parameter.span(0.2F, 1.1F), full, 0.1F, EBiomes.CAVES);
            u.underground(full,
                    Climate.Parameter.span(-1.0F, 0.7F),
                    Climate.Parameter.span(-1.05F, 0.8F),
                    Climate.Parameter.span(0.775F, 1.0F),
                    full,
                    0.0F, EBiomes.CRYSTAL_CAVERNS);
        });
    }

    public static final class VanillaRegion {
        private static final RegionBiomeBuilder BUILDER = create();
        private static RegionBiomeBuilder create() {
            RegionBiomeBuilder builder = new RegionBiomeBuilder();

            builder.middle().replace(new ResourceKey[][]{
                    { EBiomes.SNOWY_TUNDRA, EBiomes.SNOWY_TUNDRA, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA, Biomes.TAIGA },
                    { EBiomes.TUNDRA, EBiomes.TUNDRA, Biomes.TAIGA, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                    { EBiomes.DAPPLED_CLEARING, Biomes.PLAINS, Biomes.FOREST, EBiomes.OLD_GROWTH_FOREST, Biomes.DARK_FOREST },
                    { Biomes.SAVANNA, Biomes.SAVANNA, EBiomes.ARID_SHRUBLAND, Biomes.SPARSE_JUNGLE, Biomes.JUNGLE },
                    { EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.DUNES, EBiomes.ACACIA_FLATS, EBiomes.ACACIA_FLATS }
            });
            builder.middleVariant().replace(new ResourceKey[][]{
                    { Biomes.ICE_SPIKES, null, EBiomes.FIR_THICKET, EBiomes.FIR_THICKET, null },
                    { null, null, Biomes.DAPPLED_FOREST, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                    { Biomes.SUNFLOWER_PLAINS, null, null, null, null },
                    { null, null, null, null, Biomes.BAMBOO_JUNGLE },
                    { null, null, null, null, null }
            });

            builder.plateau().replace(new ResourceKey[][]{
                    { null, null, null, null, null },
                    { EBiomes.HIGHLANDS, EBiomes.HIGHLANDS, EBiomes.HIGHLANDS, null, null },
                    { Biomes.CHERRY_GROVE, Biomes.CHERRY_GROVE, null, null, Biomes.PALE_GARDEN },
                    { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, null, null, null },
                    { null, null, null, null, null }
            });
            builder.plateauVariant().replace(new ResourceKey[][]{
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null }
            });

            builder.rivers().byTemperature(new ResourceKey[]{
                    Biomes.FROZEN_RIVER, EBiomes.COLD_RIVER, Biomes.RIVER, EBiomes.WARM_RIVER, EBiomes.WARM_RIVER
            });
            builder.beaches().replace(new ResourceKey[][]{
                    { EBiomes.FROZEN_BEACH, EBiomes.FROZEN_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH },
                    { EBiomes.COLD_BEACH, EBiomes.COLD_BEACH, EBiomes.COLD_BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS },
                    { Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS },
                    { Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS },
                    { Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS }
            });
            builder.swamps().replace(new ResourceKey[][]{
                    { EBiomes.BOG, EBiomes.BOG, EBiomes.BOG, EBiomes.BOG, EBiomes.BOG },
                    { EBiomes.MARSH, EBiomes.MARSH, Biomes.SWAMP, Biomes.SWAMP, Biomes.SWAMP },
                    { EBiomes.MARSH, EBiomes.MARSH, Biomes.SWAMP, Biomes.SWAMP, Biomes.SWAMP },
                    { Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, EBiomes.TROPICAL_BASIN, EBiomes.TROPICAL_BASIN },
                    { EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND }
            });
            builder.badlands().replaceRow(new ResourceKey[]{null, null, null, Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS });

            return builder;
        }
        public static void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes) {
            BUILDER.addBiomes(biomes);
        }
    }

    public static final class ChocolateRegion {
        private static final RegionBiomeBuilder BUILDER = create();
        private static RegionBiomeBuilder create() {
            RegionBiomeBuilder builder = new RegionBiomeBuilder();

            builder.middle().replace(new ResourceKey[][] {
                    { Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, EBiomes.ALPINE_PLAINS, EBiomes.ALPINE_PLAINS, EBiomes.PINE_BARRENS },
                    { EBiomes.COLD_DESERT, EBiomes.STEPPE, EBiomes.STEPPE, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST },
                    { EBiomes.PRAIRIE, EBiomes.PRAIRIE, EBiomes.SHRUBLAND, EBiomes.OAK_WOODLAND, Biomes.DARK_FOREST },
                    { EBiomes.DRYLAND, EBiomes.DRYLAND, EBiomes.CEDAR_FOREST, EBiomes.CEDAR_FOREST, EBiomes.ROCKY_JUNGLE },
                    { EBiomes.DRYLAND, Biomes.DESERT, Biomes.DESERT, EBiomes.DUNES, EBiomes.LUSH_DUNES }
            });
            builder.middleVariant().replace(new ResourceKey[][] {
                    { EBiomes.GLACIAL_VALLEY, null, EBiomes.ALPINE_GROVE, EBiomes.ALPINE_GROVE, null },
                    { null, null, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST },
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null }
            });

            builder.plateau().replace(new ResourceKey[][] {
                    { null, null, null, null, null },
                    { EBiomes.HIGHLANDS, null, null, null, EBiomes.RAINFOREST },
                    { Biomes.FLOWER_FOREST, Biomes.MEADOW, null, null, Biomes.PALE_GARDEN },
                    { null, null, null, null, null },
                    { Biomes.BADLANDS, Biomes.BADLANDS, Biomes.BADLANDS, EBiomes.CLOUD_FOREST, EBiomes.CLOUD_FOREST }
            });
            builder.plateauVariant().replace(new ResourceKey[][] {
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, null, null }
            });
            builder.shattered().replace(new ResourceKey[][] {
                    { EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS },
                    { EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS },
                    { EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS, EBiomes.HOWLING_PEAKS },
                    { null, null, null, null, null },
                    { null, null, null, null, null }
            });

            builder.rivers().byTemperature(new ResourceKey[]{
                    Biomes.FROZEN_RIVER, EBiomes.COLD_RIVER, Biomes.RIVER, EBiomes.WARM_RIVER, EBiomes.WARM_RIVER
            });
            builder.beaches().replace(new ResourceKey[][]{
                    { EBiomes.FROZEN_BEACH, EBiomes.FROZEN_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH },
                    { EBiomes.COLD_BEACH, EBiomes.COLD_BEACH, EBiomes.COLD_BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS },
                    { Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS },
                    { Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS },
                    { Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, EBiomes.TIDEPOOLS, EBiomes.TIDEPOOLS }
            });
            builder.swamps().replace(new ResourceKey[][]{
                    { EBiomes.BOG, EBiomes.BOG, EBiomes.BOG, EBiomes.BOG, EBiomes.BOG },
                    { EBiomes.MARSH, EBiomes.MARSH, Biomes.SWAMP, Biomes.SWAMP, Biomes.SWAMP },
                    { EBiomes.MARSH, EBiomes.MARSH, Biomes.SWAMP, Biomes.SWAMP, Biomes.SWAMP },
                    { Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, EBiomes.TROPICAL_BASIN, EBiomes.TROPICAL_BASIN },
                    { EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND, EBiomes.DESERT_WETLAND }
            });
            builder.badlands().replaceRow(new ResourceKey[]{null, null, null, EBiomes.CLOUD_FOREST, EBiomes.CLOUD_FOREST });

            return builder;
        }
        public static void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes) {
            BUILDER.addBiomes(biomes);
        }
    }
}
