package team.lookingglass.elsewhere.worldgen.features;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.AquaticFeatures;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.EBlocks;
import team.lookingglass.elsewhere.registry.tags.EBlockTags;
import team.lookingglass.elsewhere.worldgen.features.modifiers.SeaLevelFilter;

import java.util.List;

public class EPlacements {

    public static final ResourceKey<PlacedFeature> FOREST_ROCK_SPARSE = registerKey("forest_rock_sparse");
    public static final ResourceKey<PlacedFeature> MEADOW_ROCK = registerKey("meadow_rock");

    public static final ResourceKey<PlacedFeature> BLUEBONNET = registerKey("bluebonnet");
    public static final ResourceKey<PlacedFeature> BLUEBONNET_DENSE = registerKey("bluebonnet_dense");

    public static final ResourceKey<PlacedFeature> HIBISCUS_JUNGLE = registerKey("hibiscus_jungle");
    public static final ResourceKey<PlacedFeature> HIBISCUS_SPARSE_JUNGLE = registerKey("hibiscus_sparse_jungle");

    public static final ResourceKey<PlacedFeature> OUTBACK_ACACIA = registerKey("outback_acacia");
    public static final ResourceKey<PlacedFeature> OAK_SHRUBS = registerKey("oak_shrubs");
    public static final ResourceKey<PlacedFeature> JUNGLE_SHRUBS = registerKey("jungle_shrubs");
    public static final ResourceKey<PlacedFeature> CEDAR_FOREST_TREES = registerKey("cedar_forest_trees");
    public static final ResourceKey<PlacedFeature> CEDAR_TREES_SPARSE = registerKey("cedar_trees_sparse");

    public static final ResourceKey<PlacedFeature> WINDSWEPT_MAHOGANY = registerKey("windswept_mahogany");
    public static final ResourceKey<PlacedFeature> WINDSWEPT_SHRUB = registerKey("windswept_shrub");
    public static final ResourceKey<PlacedFeature> JUNGLE_TREES = registerKey("jungle_trees");
    public static final ResourceKey<PlacedFeature> SPARSE_JUNGLE_TREES = registerKey("sparse_jungle_trees");
    public static final ResourceKey<PlacedFeature> BAMBOO_JUNGLE_TREES = registerKey("bamboo_jungle_trees");

    public static final ResourceKey<PlacedFeature> FLOWER_FOREST_TREES = registerKey("flower_forest_trees");
    public static final ResourceKey<PlacedFeature> FLOWER_FOREST_WILDFLOWERS = registerKey("flower_forest_wildflowers");
    public static final ResourceKey<PlacedFeature> CHERRY_GROVE_BAMBOO = registerKey("cherry_grove_bamboo");
    public static final ResourceKey<PlacedFeature> SPARSE_CHERRY = registerKey("sparse_cherry");

    public static final ResourceKey<PlacedFeature> BIRCH_TREES = registerKey("birch_trees");
    public static final ResourceKey<PlacedFeature> OLD_GROWTH_BIRCH_TREES = registerKey("old_growth_birch_trees");

    public static final ResourceKey<PlacedFeature> TUNDRA_VEGETATION_PATCH = registerKey("tundra_vegetation_patch");
    public static final ResourceKey<PlacedFeature> TUNDRA_ROCK = registerKey("tundra_rock");
    public static final ResourceKey<PlacedFeature> SNOW_PATCH = registerKey("snow_patch");

    public static final ResourceKey<PlacedFeature> DRY_GRASS_CLUMP = registerKey("dry_grass_clump");
    public static final ResourceKey<PlacedFeature> DRY_GRASS_CLUMP_SPARSE = registerKey("dry_grass_clump_sparse");

    public static final ResourceKey<PlacedFeature> AMETHYST_NODE = registerKey("amethyst_node");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIRE_CLUSTER = registerKey("amethyst_spire_cluster");

    public static final ResourceKey<PlacedFeature> ORE_BEJEWELED_CALCITE = registerKey("ore_bejeweled_calcite");
    public static final ResourceKey<PlacedFeature> ORE_SILVER = registerKey("ore_silver");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_SMALL = registerKey("ore_silver_small");
    public static final ResourceKey<PlacedFeature> ORE_TIN = registerKey("ore_tin");
    public static final ResourceKey<PlacedFeature> ORE_TIN_LARGE = registerKey("ore_tin_large");
    public static final ResourceKey<PlacedFeature> ORE_TIN_SMALL = registerKey("ore_tin_small");
    public static final ResourceKey<PlacedFeature> ORE_SHALE = registerKey("ore_shale");
    public static final ResourceKey<PlacedFeature> ORE_ANCIENT_DEBRIS_LAVA = registerKey("ore_ancient_debris_lava");

    public static final ResourceKey<PlacedFeature> BASALT_VENT = registerKey("basalt_vent");

    public static final ResourceKey<PlacedFeature> WITHER_ROSE = registerKey("wither_rose");
    public static final ResourceKey<PlacedFeature> SOUL_CRYSTAL = registerKey("soul_crystal");
    public static final ResourceKey<PlacedFeature> SOUL_SPROUTS = registerKey("soul_sprouts");
    public static final ResourceKey<PlacedFeature> SOULGLASS_PILLAR = registerKey("soulgass_pillar");
    public static final ResourceKey<PlacedFeature> ORE_SOUL_SILVER = registerKey("ore_soul_silver");
    public static final ResourceKey<PlacedFeature> ORE_SOUL_QUARTZ = registerKey("ore_soul_quartz");

    public static final ResourceKey<PlacedFeature> PEBBLE = registerKey("pebble");
    public static final ResourceKey<PlacedFeature> RUSTY_MOSS_PATCH = registerKey("rusty_moss_patch");
    public static final ResourceKey<PlacedFeature> MOSSY_MUD_PATCH = registerKey("mossy_mud_patch");

    public static final ResourceKey<PlacedFeature> LUSH_SHORT_CACTUS = registerKey("lush_short_cactus");
    public static final ResourceKey<PlacedFeature> LUSH_TALL_CACTUS = registerKey("lush_tall_cactus");
    public static final ResourceKey<PlacedFeature> DESERT_SHORT_CACTUS = registerKey("desert_short_cactus");
    public static final ResourceKey<PlacedFeature> DESERT_TALL_CACTUS = registerKey("desert_tall_cactus");
    public static final ResourceKey<PlacedFeature> AZALEA_SHRUB = registerKey("azalea_shrub");
    public static final ResourceKey<PlacedFeature> DENSE_DRY_GRASS = registerKey("dense_dry_grass");
    public static final ResourceKey<PlacedFeature> LUSH_DIRT_PATCH = registerKey("lush_dirt_patch");
    public static final ResourceKey<PlacedFeature> DESERT_DIRT_PATCH = registerKey("desert_dirt_patch");

    public static final ResourceKey<PlacedFeature> DESERT_ROCKS = registerKey("desert_rocks");
    public static final ResourceKey<PlacedFeature> LUSH_DESERT_ROCKS = registerKey("lush_desert_rocks");
    public static final ResourceKey<PlacedFeature> OUTBACK_ROCKS = registerKey("outback_rocks");

    public static final ResourceKey<PlacedFeature> BEACHSTONE_ROCKS = registerKey("beachstone_rocks");
    public static final ResourceKey<PlacedFeature> EXTRA_BEACHSTONE_ROCKS = registerKey("extra_beachstone_rocks");
    public static final ResourceKey<PlacedFeature> UNDERWATER_BEACHSTONE_ROCKS = registerKey("underwater_beachstone_rocks");
    public static final ResourceKey<PlacedFeature> ROCKWEED_BASALT_PATCH = registerKey("rockweed_basalt_patch");
    public static final ResourceKey<PlacedFeature> TIDEPOOL = registerKey("tidepool");

    public static final ResourceKey<PlacedFeature> PLAINS_CRAG = registerKey("plains_crag");
    public static final ResourceKey<PlacedFeature> SHALLOW_LUKEWARM_CRAG = registerKey("shallow_lukewarm_crag");
    public static final ResourceKey<PlacedFeature> DEEP_LUKEWARM_CRAG = registerKey("deep_lukewarm_crag");

    public static final ResourceKey<PlacedFeature> SEAGRASS_EXTRA = registerKey("seagrass_extra");
    public static final ResourceKey<PlacedFeature> SEAGRASS_MID_EXTRA = registerKey("seagrass_mid_extra");
    public static final ResourceKey<PlacedFeature> KELP_CLUSTER = registerKey("kelp_cluster");

    public static final ResourceKey<PlacedFeature> CAVE_WEEDS = registerKey("cave_weeds");
    public static final ResourceKey<PlacedFeature> CAVE_ROOTS = registerKey("cave_roots");

    public static final ResourceKey<PlacedFeature> SWAMP_CATTAILS = registerKey("swamp_cattails");
    public static final ResourceKey<PlacedFeature> MANGROVE_CATTAILS = registerKey("mangrove_cattails");

    public static final ResourceKey<PlacedFeature> STEPPE_GRASS = registerKey("steppe_grass");
    public static final ResourceKey<PlacedFeature> STEPPE_PILLARS = registerKey("steppe_pillars");

    public static final ResourceKey<PlacedFeature> SWAMP_GRASS = registerKey("swamp_grass");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var feature = context.lookup(Registries.FEATURE);

        PlacementUtils.register(context, FOREST_ROCK_SPARSE,
                feature.getOrThrow(MiscOverworldFeatures.FOREST_ROCK),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, MEADOW_ROCK,
                feature.getOrThrow(MiscOverworldFeatures.FOREST_ROCK),
                List.of(
                        RarityFilter.onAverageOnceEvery(36),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        // Hibiscus
        PlacementUtils.register(context, HIBISCUS_JUNGLE,
                feature.getOrThrow(EFeatures.HIBISCUS_KEY),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, HIBISCUS_SPARSE_JUNGLE,
                feature.getOrThrow(EFeatures.HIBISCUS_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));


        // Bluebonnets :3
        PlacementUtils.register(context, BLUEBONNET,
                feature.getOrThrow(EFeatures.BLUEBONNET_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(6),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, BLUEBONNET_DENSE,
                feature.getOrThrow(EFeatures.BLUEBONNET_DENSE_KEY),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, TUNDRA_VEGETATION_PATCH,
                feature.getOrThrow(EFeatures.RED_VEGETATION_PATCH),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, TUNDRA_ROCK,
                feature.getOrThrow(EFeatures.TUNDRA_ROCK),
                List.of(
                        RarityFilter.onAverageOnceEvery(5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, SNOW_PATCH,
                feature.getOrThrow(EFeatures.SNOW_PATCH),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, DRY_GRASS_CLUMP,
                feature.getOrThrow(EFeatures.DRY_GRASS_CLUMP),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, DRY_GRASS_CLUMP_SPARSE,
                feature.getOrThrow(EFeatures.DRY_GRASS_CLUMP),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        // Amethyst Spires
        PlacementUtils.register(context, AMETHYST_NODE,
                feature.getOrThrow(EFeatures.AMETHYST_NODE_KEY),
                List.of(
                        CountPlacement.of(24),
                        InSquarePlacement.spread(),
                        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, AMETHYST_SPIRE_CLUSTER,
                feature.getOrThrow(EFeatures.AMETHYST_SPIRE_CLUSTER_KEY),
                List.of(
                        CountPlacement.of(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, ORE_BEJEWELED_CALCITE,
                feature.getOrThrow(EFeatures.ORE_BEJEWELED_CALCITE),
                List.of(
                        CountPlacement.of(218),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-24),
                                VerticalAnchor.absolute(128)
                        ),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, ORE_SILVER,
                feature.getOrThrow(EFeatures.ORE_SILVER),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(
                                TrapezoidHeight.of(
                                        VerticalAnchor.absolute(-24),
                                        VerticalAnchor.absolute(24),
                                        24
                                )
                        ),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, ORE_SILVER_SMALL,
                feature.getOrThrow(EFeatures.ORE_SILVER_SMALL),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-48),
                                VerticalAnchor.absolute(52)
                        ),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, ORE_TIN,
                feature.getOrThrow(EFeatures.ORE_TIN),
                List.of(
                        CountPlacement.of(11),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(
                                TrapezoidHeight.of(
                                        VerticalAnchor.absolute(-24),
                                        VerticalAnchor.absolute(72),
                                        38
                                )
                        ),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, ORE_TIN_LARGE,
                feature.getOrThrow(EFeatures.ORE_TIN_LARGE),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.aboveBottom(-80),
                                VerticalAnchor.absolute(-12)
                        ),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, ORE_TIN_SMALL,
                feature.getOrThrow(EFeatures.ORE_TIN_SMALL),
                List.of(
                        CountPlacement.of(9),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(48),
                                VerticalAnchor.absolute(180)
                        ),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, ORE_SHALE,
                feature.getOrThrow(EFeatures.ORE_SHALE),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.bottom(),
                                VerticalAnchor.absolute(0)
                        ),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, ORE_ANCIENT_DEBRIS_LAVA,
                feature.getOrThrow(EFeatures.ORE_ANCIENT_DEBRIS_LAVA),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.bottom(),
                                VerticalAnchor.absolute(30)
                        ),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, BASALT_VENT,
                feature.getOrThrow(EFeatures.BASALT_VENT),
                List.of(
                        CountPlacement.of(290),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.aboveBottom(24),
                                VerticalAnchor.top()
                        ),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.matchesBlocks(Direction.DOWN, Blocks.BASALT)
                        )),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, WITHER_ROSE,
                feature.getOrThrow(EFeatures.WITHER_ROSE),
                List.of(
                        CountPlacement.of(50),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(Blocks.WITHER_ROSE)
                        )),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, SOUL_CRYSTAL,
                feature.getOrThrow(EFeatures.SOUL_CRYSTAL),
                List.of(
                        CountPlacement.of(34),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.hasSturdyFace(Direction.DOWN)
                        )),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, SOUL_SPROUTS,
                feature.getOrThrow(EFeatures.SOUL_SPROUTS),
                List.of(
                        CountPlacement.of(240),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(EBlocks.SHORT_SOUL_SPROUT)
                        )),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, SOULGLASS_PILLAR,
                feature.getOrThrow(EFeatures.SOULGLASS_PILLAR),
                List.of(
                        CountPlacement.of(28),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(EBlocks.SOULGLASS_CLUSTER),
                                BlockPredicate.matchesTag(Direction.DOWN, EBlockTags.NETHER_SOIL)
                        )),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, ORE_SOUL_SILVER,
                feature.getOrThrow(EFeatures.ORE_SOUL_SILVER),
                List.of(
                        CountPlacement.of(18),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10)),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, ORE_SOUL_QUARTZ,
                feature.getOrThrow(EFeatures.ORE_SOUL_QUARTZ),
                List.of(
                        CountPlacement.of(26),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10)),
                        BiomeFilter.biome()
                ));


        PlacementUtils.register(context, PEBBLE,
                feature.getOrThrow(EFeatures.PEBBLE),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(EBlocks.PEBBLE)))
                ));

        PlacementUtils.register(context, OUTBACK_ACACIA,
                feature.getOrThrow(EFeatures.MEGA_ACACIA),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.wouldSurvive(Blocks.ACACIA_SAPLING)),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, RUSTY_MOSS_PATCH,
                feature.getOrThrow(EFeatures.RUSTY_MOSS_PATCH_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, MOSSY_MUD_PATCH,
                feature.getOrThrow(EFeatures.MOSSY_MUD_PATCH_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, LUSH_DIRT_PATCH,
                feature.getOrThrow(EFeatures.COARSE_DIRT_PATCH),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, DESERT_DIRT_PATCH,
                feature.getOrThrow(EFeatures.COARSE_DIRT_PATCH),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, LUSH_DESERT_ROCKS,
                feature.getOrThrow(EFeatures.LUSH_SANDSTONE_ROCKS),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, DESERT_ROCKS,
                feature.getOrThrow(EFeatures.SANDSTONE_ROCK),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, OUTBACK_ROCKS,
                feature.getOrThrow(EFeatures.RED_SANDSTONE_ROCK),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, LUSH_SHORT_CACTUS,
                feature.getOrThrow(EFeatures.SHORT_CACTUS),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.CACTUS)))
                ));
        PlacementUtils.register(context, LUSH_TALL_CACTUS,
                feature.getOrThrow(EFeatures.TALL_CACTUS),
                List.of(
                        RarityFilter.onAverageOnceEvery(6),
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.CACTUS)))
                ));

        PlacementUtils.register(context, DESERT_SHORT_CACTUS,
                feature.getOrThrow(EFeatures.SHORT_CACTUS),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.CACTUS)))
                ));
        PlacementUtils.register(context, DESERT_TALL_CACTUS,
                feature.getOrThrow(EFeatures.TALL_CACTUS),
                List.of(
                        RarityFilter.onAverageOnceEvery(7),
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.CACTUS)))
                ));

        PlacementUtils.register(context, AZALEA_SHRUB,
                feature.getOrThrow(EFeatures.AZALEA_SHRUB_KEY),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.CACTUS)))
                ));

        PlacementUtils.register(context, OAK_SHRUBS,
                feature.getOrThrow(EFeatures.OAK_SHRUB),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING)))
                ));
        PlacementUtils.register(context, JUNGLE_SHRUBS,
                feature.getOrThrow(EFeatures.JUNGLE_SHRUB),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.JUNGLE_SAPLING)))
                ));
        PlacementUtils.register(context, CEDAR_FOREST_TREES,
                feature.getOrThrow(EFeatures.TREES_CEDAR_AND_OAK_LEAF_LITTER),
                List.of(
                        CountPlacement.of(17),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(EBlocks.CEDAR_SAPLING)))
                ));
        PlacementUtils.register(context, CEDAR_TREES_SPARSE,
                feature.getOrThrow(EFeatures.CEDAR_TREE),
                List.of(
                        RarityFilter.onAverageOnceEvery(5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(EBlocks.CEDAR_SAPLING)))
                ));
        PlacementUtils.register(context, WINDSWEPT_MAHOGANY,
                feature.getOrThrow(EFeatures.MAHOGANY_TREE),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(EBlocks.MAHOGANY_SAPLING)))
                ));
        PlacementUtils.register(context, WINDSWEPT_SHRUB,
                feature.getOrThrow(EFeatures.JUNGLE_SHRUB),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(Blocks.JUNGLE_SAPLING)))
                ));

        PlacementUtils.register(context, JUNGLE_TREES,
                feature.getOrThrow(EFeatures.JUNGLE_TREES_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(50, 0.1F, 1), Blocks.JUNGLE_SAPLING)
        );
        PlacementUtils.register(context, SPARSE_JUNGLE_TREES,
                feature.getOrThrow(EFeatures.SPARSE_JUNGLE_TREES_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1F, 1), Blocks.JUNGLE_SAPLING)
        );
        PlacementUtils.register(context, BAMBOO_JUNGLE_TREES,
                feature.getOrThrow(EFeatures.BAMBOO_JUNGLE_TREES_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(30, 0.1F, 1), Blocks.JUNGLE_SAPLING)
        );

        PlacementUtils.register(context, FLOWER_FOREST_TREES,
                feature.getOrThrow(EFeatures.FLOWER_FOREST_TREES_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.1F, 1), Blocks.OAK_SAPLING)
        );
        PlacementUtils.register(context, FLOWER_FOREST_WILDFLOWERS,
                feature.getOrThrow(EFeatures.SPARSE_FOREST_FLOWERS),
                List.of(
                        CountPlacement.of(63),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.WILDFLOWERS)))
                ));
        PlacementUtils.register(context, CHERRY_GROVE_BAMBOO,
                feature.getOrThrow(VegetationFeatures.BAMBOO_NO_PODZOL),
                List.of(
                        CountPlacement.of(12),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.BAMBOO)))
                ));
        PlacementUtils.register(context, SPARSE_CHERRY,
                feature.getOrThrow(TreeFeatures.CHERRY_BEES_005),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1), Blocks.CHERRY_SAPLING)
        );

        PlacementUtils.register(context, BIRCH_TREES,
                feature.getOrThrow(EFeatures.OLD_GROWTH_BIRCH_TREES_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), Blocks.BIRCH_SAPLING)
        );
        PlacementUtils.register(context, OLD_GROWTH_BIRCH_TREES,
                feature.getOrThrow(EFeatures.OLD_GROWTH_BIRCH_TREES_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), Blocks.BIRCH_SAPLING)
        );

        PlacementUtils.register(context, DENSE_DRY_GRASS,
                feature.getOrThrow(VegetationFeatures.DRY_GRASS),
                List.of(
                        CountPlacement.of(18),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.wouldSurvive(Blocks.SHORT_DRY_GRASS)))
                ));

        PlacementUtils.register(context, BEACHSTONE_ROCKS,
                feature.getOrThrow(EFeatures.BEACHSTONE_ROCK_KEY),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(Blocks.SHORT_GRASS)))
                ));
        PlacementUtils.register(context, EXTRA_BEACHSTONE_ROCKS,
                feature.getOrThrow(EFeatures.BEACHSTONE_ROCK_KEY),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(Blocks.SHORT_GRASS)))
                ));
        PlacementUtils.register(context, UNDERWATER_BEACHSTONE_ROCKS,
                feature.getOrThrow(EFeatures.BEACHSTONE_ROCK_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP))
                ));

        PlacementUtils.register(context, ROCKWEED_BASALT_PATCH,
                feature.getOrThrow(EFeatures.ROCKWEED_BASALT_PATCH_KEY),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, TIDEPOOL,
                feature.getOrThrow(EFeatures.TIDEPOOL_KEY),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome(),
                        SurfaceWaterDepthFilter.forMaxDepth(1),
                        BlockPredicateFilter.forPredicate(BlockPredicate.solid(Direction.DOWN)),
                        SeaLevelFilter.of(-1, 0)
                ));

        PlacementUtils.register(context, PLAINS_CRAG,
                feature.getOrThrow(EFeatures.PLAINS_CRAG),
                List.of(
                        RarityFilter.onAverageOnceEvery(7),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP)
                        ))
                ));

        PlacementUtils.register(context, SHALLOW_LUKEWARM_CRAG,
                feature.getOrThrow(EFeatures.SHALLOW_LUKEWARM_CRAG),
                List.of(
                        RarityFilter.onAverageOnceEvery(5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(Blocks.WATER),
                                BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP)
                        ))
                ));
        PlacementUtils.register(context, DEEP_LUKEWARM_CRAG,
                feature.getOrThrow(EFeatures.DEEP_LUKEWARM_CRAG),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(Blocks.WATER),
                                BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP)
                        ))
                ));

        PlacementUtils.register(context, SEAGRASS_EXTRA,
                feature.getOrThrow(AquaticFeatures.SEAGRASS_SHORT),
                List.of(
                        CountPlacement.of(12),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER))
                ));
        PlacementUtils.register(context, SEAGRASS_MID_EXTRA,
                feature.getOrThrow(AquaticFeatures.SEAGRASS_MID),
                List.of(
                        CountPlacement.of(8),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER))
                ));

        PlacementUtils.register(context, KELP_CLUSTER,
                feature.getOrThrow(EFeatures.RANDOM_SHORT_KELP),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        CountPlacement.of(7),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, CAVE_WEEDS,
                feature.getOrThrow(EFeatures.CAVE_WEEDS_KEY),
                List.of(
                        CountPlacement.of(40),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.bottom(),
                                VerticalAnchor.absolute(60)
                        ),
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(EBlocks.CAVE_WEED)))
                ));
        PlacementUtils.register(context, CAVE_ROOTS,
                feature.getOrThrow(EFeatures.CAVE_ROOTED_DIRT),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                        EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                        BiomeFilter.biome()
                ));


        PlacementUtils.register(context, SWAMP_CATTAILS,
                feature.getOrThrow(EFeatures.CATTAILS),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        SurfaceWaterDepthFilter.forMaxDepth(1),
                        SeaLevelFilter.of(-1, 0),
                        BiomeFilter.biome()
                ));
        PlacementUtils.register(context, MANGROVE_CATTAILS,
                feature.getOrThrow(EFeatures.CATTAILS),
                List.of(
                        CountPlacement.of(5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        SurfaceWaterDepthFilter.forMaxDepth(1),
                        SeaLevelFilter.of(-1, 0),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, STEPPE_GRASS,
                feature.getOrThrow(VegetationFeatures.TALL_GRASS),
                List.of(
                        CountPlacement.of(28),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(Blocks.TALL_GRASS)))
                ));
        PlacementUtils.register(context, STEPPE_PILLARS,
                feature.getOrThrow(EFeatures.LIMESTONE_ROCK),
                List.of(
                        RarityFilter.onAverageOnceEvery(9),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(Blocks.SHORT_GRASS)))
                ));

        PlacementUtils.register(context, SWAMP_GRASS,
                feature.getOrThrow(VegetationFeatures.GRASS_JUNGLE),
                List.of(
                        CountPlacement.of(232),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(Blocks.SHORT_GRASS)))
                ));


    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }
}
