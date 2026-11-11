package team.lookingglass.elsewhere.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.mirrorlib.world.region.Region;
import team.lookingglass.mirrorlib.world.region.RegionBiomeBuilder;
import team.lookingglass.mirrorlib.world.region.RegionRegistry;

import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public final class ERegions {
    public static final ResourceKey<Region> CHOCOLATE = Region.key(Elsewhere.MODID, "chocolate");
    public static final ResourceKey<Region> STRAWBERRY = Region.key(Elsewhere.MODID, "strawberry");

    public static void initialize() {
        RegionRegistry.replaceVanillaRegion(VanillaRegion::addBiomes);
        RegionRegistry.addRegion(CHOCOLATE, 100.0F, ChocolateRegion::addBiomes);
        RegionRegistry.addRegion(STRAWBERRY, 100.0F, BerryRegion::addBiomes);
    }

    public static final class VanillaRegion {
        private static final RegionBiomeBuilder BUILDER = create();
        private static RegionBiomeBuilder create() {
            RegionBiomeBuilder builder = new RegionBiomeBuilder();

            builder.middle().replace(new ResourceKey[][]{
                    { Biomes.SNOWY_PLAINS, EBiomes.SNOWY_TUNDRA, EBiomes.SNOWY_TUNDRA, Biomes.SNOWY_TAIGA, Biomes.TAIGA },
                    { EBiomes.TUNDRA, EBiomes.TUNDRA, Biomes.DAPPLED_FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                    { Biomes.PLAINS, Biomes.PLAINS, Biomes.FOREST, EBiomes.OLD_GROWTH_FOREST, Biomes.DARK_FOREST },
                    { Biomes.SAVANNA, EBiomes.ACACIA_FLATS, EBiomes.SHRUBLAND, Biomes.SPARSE_JUNGLE, Biomes.JUNGLE },
                    { EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.DUNES, EBiomes.ACACIA_FLATS, EBiomes.ACACIA_FLATS }
            });
            builder.middleVariant().replace(new ResourceKey[][]{
                    { null, null, null, null, null },
                    { null, null, null, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                    { Biomes.SUNFLOWER_PLAINS, null, null, EBiomes.OLD_GROWTH_FOREST, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null }
            });

            builder.plateau().replace(new ResourceKey[][]{
                    { null, null, EBiomes.SNOWY_TUNDRA, null, null },
                    { EBiomes.TUNDRA, EBiomes.TUNDRA, null, null, null },
                    { Biomes.MEADOW, Biomes.CHERRY_GROVE, Biomes.CHERRY_GROVE, null, Biomes.PALE_GARDEN },
                    { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, EBiomes.CEDAR_FOREST, null, null },
                    { null, null, null, null, null }
            });
            builder.plateauVariant().replace(new ResourceKey[][]{
                    { null, null, null, null, null },
                    { null, null, null, null, null },
                    { null, Biomes.MEADOW, Biomes.MEADOW, null, null },
                    { null, null, null, null, null },
                    { null, null, null, null, null }
            });

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
                    { Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA, Biomes.TAIGA },
                    { EBiomes.STEPPE, EBiomes.STEPPE, EBiomes.PINE_BARRENS, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST },
                    { Biomes.PLAINS, Biomes.PLAINS, EBiomes.OAK_WOODLAND, EBiomes.OAK_WOODLAND, Biomes.DARK_FOREST },
                    { Biomes.SAVANNA, EBiomes.ACACIA_FLATS, EBiomes.SHRUBLAND, EBiomes.CEDAR_FOREST, EBiomes.ROCKY_JUNGLE },
                    { Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, EBiomes.DUNES, EBiomes.LUSH_DUNES }
            });
            builder.middleVariant().replace(new ResourceKey[][] {
                    null,
                    { null, null, Biomes.DAPPLED_FOREST, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                    { Biomes.SUNFLOWER_PLAINS, null, Biomes.FOREST, null, null },
                    { null, null, EBiomes.CEDAR_FOREST, null, EBiomes.ROCKY_JUNGLE },
                    null
            });
            builder.plateau().replace(new ResourceKey[][] {
                    { EBiomes.SNOWY_TUNDRA, null, Biomes.SNOWY_PLAINS, null, null },
                    { null, null, Biomes.FLOWER_FOREST, null, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                    { EBiomes.CHERRY_FIELDS, Biomes.CHERRY_GROVE, null, null, Biomes.PALE_GARDEN },
                    { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, null, EBiomes.ROCKY_JUNGLE, null },
                    { Biomes.BADLANDS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS, EBiomes.CLOUD_FOREST, EBiomes.CLOUD_FOREST }
            });
            builder.plateauVariant().replace(new ResourceKey[][] {
                    null,
                    { null, null, Biomes.DAPPLED_FOREST, null, Biomes.OLD_GROWTH_PINE_TAIGA },
                    { Biomes.MEADOW, Biomes.MEADOW, null, null, null },
                    { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, null, null, null },
                    { Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, EBiomes.CLOUD_FOREST, EBiomes.CLOUD_FOREST }
            });

            return builder;
        }
        public static void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes) {
            BUILDER.addBiomes(biomes);
        }
    }

    public static final class BerryRegion {
        private static final RegionBiomeBuilder BUILDER = create();
        private static RegionBiomeBuilder create() {
            RegionBiomeBuilder builder = new RegionBiomeBuilder();

            builder.middle().replace(new ResourceKey[][]{
                    { Biomes.SNOWY_PLAINS, EBiomes.SNOWY_TUNDRA, EBiomes.SNOWY_TUNDRA, Biomes.SNOWY_TAIGA, Biomes.TAIGA },
                    { EBiomes.TUNDRA, EBiomes.TUNDRA, Biomes.DAPPLED_FOREST, Biomes.TAIGA, EBiomes.RAINFOREST },
                    { EBiomes.OAK_WOODLAND, Biomes.FOREST, EBiomes.OLD_GROWTH_FOREST, EBiomes.OLD_GROWTH_FOREST, Biomes.DARK_FOREST },
                    { Biomes.SAVANNA, EBiomes.ACACIA_FLATS, EBiomes.SHRUBLAND, Biomes.SPARSE_JUNGLE, Biomes.JUNGLE },
                    { EBiomes.OUTBACK, EBiomes.OUTBACK, EBiomes.DUNES, EBiomes.ACACIA_FLATS, EBiomes.ACACIA_FLATS }
            });

            return builder;
        }
        public static void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes) {
            BUILDER.addBiomes(biomes);
        }
    }
}
