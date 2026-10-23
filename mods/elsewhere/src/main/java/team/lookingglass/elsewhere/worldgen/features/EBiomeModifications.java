package team.lookingglass.elsewhere.worldgen.features;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
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
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.worldgen.EBiomes;

import java.util.List;

public class EBiomeModifications {
    public static void initialize() {
        List<ResourceKey<Biome>> forestRockBiomes = List.of(
                Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.WINDSWEPT_FOREST, Biomes.DARK_FOREST,
                Biomes.BIRCH_FOREST, Biomes.GROVE, Biomes.OLD_GROWTH_BIRCH_FOREST,
                Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE,
                Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                EBiomes.CEDAR_FOREST, Biomes.DAPPLED_FOREST
        );
        List<ResourceKey<Biome>> meadowRockBiomes = List.of(
                Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.MEADOW, EBiomes.SHRUBLAND,
                Biomes.SPARSE_JUNGLE, EBiomes.SHRUBLAND, EBiomes.STEPPE
        );

        // Basic Features
        BiomeModifications.addFeature(ctx -> ctx.hasTag(BiomeTags.IS_OVERWORLD) && !ctx.hasTag(BiomeTags.IS_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.PEBBLE);
        BiomeModifications.addFeature(ctx -> forestRockBiomes.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.FOREST_ROCK_SPARSE);
        BiomeModifications.addFeature(ctx -> meadowRockBiomes.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.MEADOW_ROCK);

        // Ores
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_TIN);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_TIN_LARGE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_TIN_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_SILVER);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_SILVER_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_BEJEWELED_CALCITE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_SHALE);

        // Hibiscus
        List<ResourceKey<Biome>> hibiscusBiomes = List.of(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE);
        BiomeModifications.addFeature(ctx -> hibiscusBiomes.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.HIBISCUS_JUNGLE);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SPARSE_JUNGLE,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.HIBISCUS_SPARSE_JUNGLE);

        // Bluebonnets
        List<ResourceKey<Biome>> bluebonnetBiomes = List.of(
                Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU,
                Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_FOREST
        );
        BiomeModifications.addFeature(ctx -> bluebonnetBiomes.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BLUEBONNET);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.WINDSWEPT_SAVANNA,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BLUEBONNET_DENSE);

        // Savannas
        List<ResourceKey<Biome>> hotSavannas = List.of(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
        BiomeModifications.addFeature(ctx -> hotSavannas.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DRY_GRASS_CLUMP_SPARSE);

        // Desert
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DESERT,
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.DESERT_ROCKS);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DESERT,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_DIRT_PATCH);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DESERT,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_SHORT_CACTUS);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DESERT,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_TALL_CACTUS);

        // Beach
        List<ResourceKey<Biome>> beaches = List.of(Biomes.BEACH, Biomes.SNOWY_BEACH);
        BiomeModifications.addFeature(ctx -> beaches.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DENSE_DRY_GRASS);
        BiomeModifications.addFeature(ctx -> beaches.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.BEACHSTONE_ROCKS);


        // Oceans
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.LUKEWARM_OCEAN,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_EXTRA);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.LUKEWARM_OCEAN,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_MID_EXTRA);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.LUKEWARM_OCEAN,
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.UNDERWATER_BEACHSTONE_ROCKS);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.LUKEWARM_OCEAN,
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.SHALLOW_LUKEWARM_CRAG);

        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DEEP_LUKEWARM_OCEAN,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_EXTRA);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DEEP_LUKEWARM_OCEAN,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SEAGRASS_MID_EXTRA);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DEEP_LUKEWARM_OCEAN,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.KELP_CLUSTER);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DEEP_LUKEWARM_OCEAN,
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.DEEP_LUKEWARM_CRAG);

        // Jungles
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.JUNGLE,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.JUNGLE_TREES);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.BAMBOO_JUNGLE,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BAMBOO_JUNGLE_TREES);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SPARSE_JUNGLE,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SPARSE_JUNGLE_TREES);

        // Flower Forests
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.FLOWER_FOREST,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.FLOWER_FOREST_TREES);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.FLOWER_FOREST,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.FLOWER_FOREST_WILDFLOWERS);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.CHERRY_GROVE,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CHERRY_GROVE_BAMBOO);

        // Dappled Forest
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.DAPPLED_FOREST,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.RUSTY_MOSS_PATCH);

        // Birch Forests
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.BIRCH_FOREST,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.BIRCH_TREES);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.OLD_GROWTH_BIRCH_FOREST,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.OLD_GROWTH_BIRCH_TREES);

        // Windswept Savanna
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.WINDSWEPT_SAVANNA,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.WINDSWEPT_MAHOGANY);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.WINDSWEPT_SAVANNA,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.WINDSWEPT_SHRUB);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.WINDSWEPT_SAVANNA,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CHERRY_GROVE_BAMBOO);

        // Swamps
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SWAMP,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_CATTAILS);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SWAMP,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_GRASS);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.MANGROVE_SWAMP,
                GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_CATTAILS);

        // Flatlands
        List<ResourceKey<Biome>> flatlands = List.of(Biomes.PLAINS, Biomes.MEADOW);
        BiomeModifications.addFeature(ctx -> flatlands.contains(ctx.getBiomeKey()),
                GenerationStep.Decoration.RAW_GENERATION, EPlacements.PLAINS_CRAG);

        // Ancient Debris
        BiomeModifications.addFeature(ctx -> ctx.hasTag(BiomeTags.IS_NETHER),
                GenerationStep.Decoration.UNDERGROUND_ORES, EPlacements.ORE_ANCIENT_DEBRIS_LAVA);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.BASALT_DELTAS,
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.BASALT_VENT);

        // Soul Sand Valley
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.ORE_SOUL_SILVER);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.ORE_SOUL_QUARTZ);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.SOUL_SPROUTS);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.SOULGLASS_PILLAR);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.SOUL_CRYSTAL);
        BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.WITHER_ROSE);


        // Removals
        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_vanilla_jungle_trees")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.JUNGLE), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_JUNGLE));
        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_vanilla_bamboo_jungle_trees")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.BAMBOO_JUNGLE), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BAMBOO_VEGETATION));
        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_vanilla_sparse_jungle_trees")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_SPARSE_JUNGLE));

        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_vanilla_flower_forest_trees")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_FLOWER_FOREST));

        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_vanilla_birch_forest_trees")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_BIRCH));
        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_vanilla_old_growth_birch_forest_trees")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BIRCH_TALL));

        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_ocean_grasses")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.BEACH).and(BiomeSelectors.tag(BiomeTags.IS_OCEAN)).and(BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN)), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL));
        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_beach_grasses")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.tag(BiomeTags.IS_BEACH), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_BADLANDS));

        BiomeModifications.create(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "remove_soul_sand_valley_crimson_roots")).add(
                ModificationPhase.REMOVALS, BiomeSelectors.tag(BiomeTags.IS_BEACH), context -> context.getGenerationSettings()
                        .removeFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_CRIMSON_ROOTS));
    }
}
