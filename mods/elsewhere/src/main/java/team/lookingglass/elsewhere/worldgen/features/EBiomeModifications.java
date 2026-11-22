package team.lookingglass.elsewhere.worldgen.features;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.worldgen.EBiomes;

import java.util.List;
import java.util.function.Predicate;

public class EBiomeModifications {
    public static void initialize() {
        // Basic Features
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD)
                        .and(BiomeSelectors.tag(BiomeTags.IS_OCEAN).negate())
                        .and(BiomeSelectors.excludeByKey(EBiomes.SALT_FLATS)),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.PEBBLE);

        // Ores
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_SILVER);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_SILVER_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_BEJEWELED_CALCITE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_SHALE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SULFUR_CAVES), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.SULFUR_CAVE_ORE_REDSTONE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SULFUR_CAVES), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.SULFUR_CAVE_ORE_SILVER);

        // Hibiscus
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.HIBISCUS_JUNGLE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.HIBISCUS_SPARSE_JUNGLE);

        // Bluebonnets
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU,
                        Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BLUEBONNET);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WINDSWEPT_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BLUEBONNET_DENSE);

        // Hot Savannas
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DRY_GRASS_CLUMP_SPARSE);

        // Desert
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.DESERT_ROCKS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_DIRT_PATCH);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_SHORT_CACTUS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_TALL_CACTUS);

        // Beach
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.SNOWY_BEACH),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BEACH_DRY_GRASS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.SNOWY_BEACH),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.BEACHSTONE_ROCKS);


        // Oceans
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.LUKEWARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_EXTRA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.LUKEWARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_MID_EXTRA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.LUKEWARM_OCEAN),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.UNDERWATER_BEACHSTONE_ROCKS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.LUKEWARM_OCEAN),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.SHALLOW_LUKEWARM_CRAG);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_EXTRA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_MID_EXTRA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.KELP_CLUSTER);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.DEEP_LUKEWARM_CRAG);

        // Jungles
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.JUNGLE_TREES);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BAMBOO_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BAMBOO_JUNGLE_TREES);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SPARSE_JUNGLE_TREES);

        // Forests
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_FOREST);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_DARK_FOREST);

        // Flower Forest
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_FLOWER_FOREST);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.FLOWER_FOREST_WILDFLOWERS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.CHERRY_GROVE),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CHERRY_GROVE_BAMBOO);

        // Dappled Forest
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DAPPLED_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DAPPLED_OCHRE_MOSS_PATCH);

        // Taigas
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.SNOWY_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_TAIGA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.SNOWY_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.RUSTY_MOSS_PATCH);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_PINE_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_OLD_GROWTH_PINE_TAIGA);

        // Birch Forests
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_BIRCH);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.GROVE),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_GROVE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_OLD_GROWTH_BIRCH);

        // Windswept Savanna
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WINDSWEPT_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.WINDSWEPT_MAHOGANY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WINDSWEPT_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.WINDSWEPT_SHRUB);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WINDSWEPT_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CHERRY_GROVE_BAMBOO);

        // Swamps
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.SWAMP_POND);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TREES_SWAMP);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_CATTAILS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_GRASS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_EXTRA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_MID_EXTRA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_CATTAILS);

        // Rivers
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.RIVER),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.MUD_DISK_CLAY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.RIVER),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.MUD_DISK_GRAVEL);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.RIVER),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.MUD_DISK_SAND);

        // Ancient Debris
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_NETHER),
                GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_ANCIENT_DEBRIS_LAVA);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.BASALT_VENT);

        // Soul Sand Valley
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.ORE_SOUL_SILVER);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.ORE_SOUL_QUARTZ);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.SOUL_SPROUTS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.SOULGLASS_PILLAR);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.SOUL_CRYSTAL);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.WITHER_ROSE);


        // Removals
        removeVegetal("remove_vanilla_jungle_trees", VegetationPlacements.TREES_JUNGLE, Biomes.JUNGLE);
        removeVegetal("remove_vanilla_bamboo_jungle_trees", VegetationPlacements.BAMBOO_VEGETATION, Biomes.BAMBOO_JUNGLE);
        removeVegetal("remove_vanilla_sparse_jungle_trees", VegetationPlacements.TREES_SPARSE_JUNGLE, Biomes.SPARSE_JUNGLE);
        removeVegetal("remove_vanilla_flower_forest_trees", VegetationPlacements.TREES_FLOWER_FOREST, Biomes.FLOWER_FOREST);
        removeVegetal("remove_vanilla_forest_trees", VegetationPlacements.TREES_BIRCH_AND_OAK_LEAF_LITTER, Biomes.FOREST);
        removeVegetal("remove_vanilla_dark_forest_trees", VegetationPlacements.DARK_FOREST_VEGETATION, Biomes.DARK_FOREST);
        removeVegetal("remove_vanilla_birch_forest_trees", VegetationPlacements.TREES_BIRCH, Biomes.BIRCH_FOREST);
        removeVegetal("remove_vanilla_grove_trees", VegetationPlacements.TREES_GROVE, Biomes.GROVE);
        removeVegetal("remove_vanilla_old_growth_birch_forest_trees", VegetationPlacements.BIRCH_TALL, Biomes.OLD_GROWTH_BIRCH_FOREST);
        removeVegetal("remove_vanilla_taiga_trees", VegetationPlacements.TREES_TAIGA, Biomes.TAIGA, Biomes.SNOWY_TAIGA);
        removeVegetal("remove_vanilla_old_growth_spruce_taiga_trees", VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA);
        removeVegetal("remove_vanilla_old_growth_pine_taiga_trees", VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA);
        removeVegetal("remove_vanilla_swamp_trees", VegetationPlacements.TREES_SWAMP, Biomes.SWAMP);

        removeFeature("remove_ocean_grasses", GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL,
                BiomeSelectors.includeByKey(Biomes.BEACH).and(BiomeSelectors.tag(BiomeTags.IS_OCEAN)).and(BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN)));
        removeFeature("remove_beach_grasses", GenerationStep.Decoration.VEGETAL_DECORATION,
                VegetationPlacements.PATCH_GRASS_BADLANDS, BiomeSelectors.tag(BiomeTags.IS_BEACH));

        removeFeature("remove_soul_sand_valley_crimson_roots", GenerationStep.Decoration.UNDERGROUND_DECORATION,
                NetherPlacements.PATCH_CRIMSON_ROOTS, BiomeSelectors.tag(BiomeTags.IS_BEACH));
    }

    @SafeVarargs
    private static void removeFeature(String name, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature, ResourceKey<Biome>... biomes) {
        removeFeature(name, step, feature, BiomeSelectors.includeByKey(biomes));
    }

    private static void removeFeature(String name, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature, Predicate<BiomeSelectionContext> selector) {
        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, name))
                .add(ModificationPhase.REMOVALS, selector, context -> context.getGenerationSettings().removeFeature(step, feature));
    }

    @SafeVarargs
    private static void removeVegetal(String name, ResourceKey<PlacedFeature> feature, ResourceKey<Biome>... biomes) {
        removeFeature(name, GenerationStep.Decoration.VEGETAL_DECORATION, feature, biomes);
    }
}
