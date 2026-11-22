package team.lookingglass.elsewhere.worldgen.features;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.EBlocks;
import team.lookingglass.elsewhere.registry.tags.EBlockTags;
import team.lookingglass.elsewhere.worldgen.features.modifiers.SeaLevelFilter;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static final ResourceKey<PlacedFeature> TREES_ROCKY_JUNGLE = registerKey("trees_rocky_jungle");

    public static final ResourceKey<PlacedFeature> TREES_FLOWER_FOREST = registerKey("trees_flower_forest");
    public static final ResourceKey<PlacedFeature> TREES_FOREST = registerKey("trees_forest");
    public static final ResourceKey<PlacedFeature> FLOWER_FOREST_WILDFLOWERS = registerKey("flower_forest_wildflowers");
    public static final ResourceKey<PlacedFeature> CHERRY_GROVE_BAMBOO = registerKey("cherry_grove_bamboo");
    public static final ResourceKey<PlacedFeature> SPARSE_CHERRY = registerKey("sparse_cherry");

    public static final ResourceKey<PlacedFeature> TREES_BIRCH = registerKey("trees_birch");
    public static final ResourceKey<PlacedFeature> TREES_GROVE = registerKey("trees_grove");
    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_BIRCH = registerKey("trees_old_growth_birch");

    public static final ResourceKey<PlacedFeature> TUNDRA_ROCK = registerKey("tundra_rock");
    public static final ResourceKey<PlacedFeature> SNOW_PATCH = registerKey("snow_patch");

    public static final ResourceKey<PlacedFeature> DRY_GRASS_CLUMP = registerKey("dry_grass_clump");
    public static final ResourceKey<PlacedFeature> DRY_GRASS_CLUMP_SPARSE = registerKey("dry_grass_clump_sparse");
    public static final ResourceKey<PlacedFeature> BEACH_DRY_GRASS = registerKey("beach_dry_grass");
    public static final ResourceKey<PlacedFeature> DUNE_DRY_GRASS = registerKey("dune_dry_grass");

    public static final ResourceKey<PlacedFeature> AMETHYST_NODE = registerKey("amethyst_node");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIRE_CLUSTER = registerKey("amethyst_spire_cluster");

    public static final ResourceKey<PlacedFeature> MUD_DISK_CLAY = registerKey("mud_disk_clay");
    public static final ResourceKey<PlacedFeature> MUD_DISK_GRAVEL = registerKey("mud_disk_gravel");
    public static final ResourceKey<PlacedFeature> MUD_DISK_SAND = registerKey("mud_disk_sand");

    public static final ResourceKey<PlacedFeature> ORE_BEJEWELED_CALCITE = registerKey("ore_bejeweled_calcite");
    public static final ResourceKey<PlacedFeature> ORE_SILVER = registerKey("ore_silver");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_SMALL = registerKey("ore_silver_small");
    public static final ResourceKey<PlacedFeature> SULFUR_CAVE_ORE_SILVER = registerKey("sulfur_cave_ore_silver");
    public static final ResourceKey<PlacedFeature> SULFUR_CAVE_ORE_REDSTONE = registerKey("sulfur_cave_ore_redstone");
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
    public static final ResourceKey<PlacedFeature> TUNDRA_RUSTY_MOSS_PATCH = registerKey("tundra_rusty_moss_patch");
    public static final ResourceKey<PlacedFeature> TUNDRA_OCHRE_MOSS_PATCH = registerKey("tundra_ochre_moss_patch");
    public static final ResourceKey<PlacedFeature> DAPPLED_OCHRE_MOSS_PATCH = registerKey("dappled_ochre_moss_patch");
    public static final ResourceKey<PlacedFeature> MOSSY_MUD_PATCH = registerKey("mossy_mud_patch");
    public static final ResourceKey<PlacedFeature> RAINFOREST_MOSS_PATCH = registerKey("rainforest_moss_patch");

    public static final ResourceKey<PlacedFeature> LUSH_SHORT_CACTUS = registerKey("lush_short_cactus");
    public static final ResourceKey<PlacedFeature> LUSH_TALL_CACTUS = registerKey("lush_tall_cactus");
    public static final ResourceKey<PlacedFeature> DESERT_SHORT_CACTUS = registerKey("desert_short_cactus");
    public static final ResourceKey<PlacedFeature> DESERT_TALL_CACTUS = registerKey("desert_tall_cactus");
    public static final ResourceKey<PlacedFeature> ROCKY_SHORT_CACTUS = registerKey("rocky_short_cactus");
    public static final ResourceKey<PlacedFeature> ROCKY_TALL_CACTUS = registerKey("rocky_tall_cactus");
    public static final ResourceKey<PlacedFeature> AZALEA_SHRUB = registerKey("azalea_shrub");
    public static final ResourceKey<PlacedFeature> DENSE_DRY_GRASS = registerKey("dense_dry_grass");
    public static final ResourceKey<PlacedFeature> LUSH_DIRT_PATCH = registerKey("lush_dirt_patch");
    public static final ResourceKey<PlacedFeature> DESERT_DIRT_PATCH = registerKey("desert_dirt_patch");

    public static final ResourceKey<PlacedFeature> DESERT_ROCKS = registerKey("desert_rocks");
    public static final ResourceKey<PlacedFeature> LUSH_DESERT_ROCKS = registerKey("lush_desert_rocks");
    public static final ResourceKey<PlacedFeature> OUTBACK_ROCKS = registerKey("outback_rocks");
    public static final ResourceKey<PlacedFeature> ROCKY_DESERT_ROCKS = registerKey("rocky_desert_rocks");

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
    public static final ResourceKey<PlacedFeature> SPARSE_DRIPSTONE = registerKey("sparse_dripstone");

    public static final ResourceKey<PlacedFeature> SWAMP_CATTAILS = registerKey("swamp_cattails");
    public static final ResourceKey<PlacedFeature> MANGROVE_CATTAILS = registerKey("mangrove_cattails");
    public static final ResourceKey<PlacedFeature> TREES_BOG = registerKey("trees_bog");

    public static final ResourceKey<PlacedFeature> TREES_TUNDRA = registerKey("trees_tundra");
    public static final ResourceKey<PlacedFeature> TREES_TAIGA = registerKey("trees_taiga");
    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_SPRUCE_TAIGA = registerKey("trees_old_growth_spruce_taiga");
    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_PINE_TAIGA = registerKey("trees_old_growth_pine_taiga");

    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_FOREST = registerKey("trees_old_growth_forest");
    public static final ResourceKey<PlacedFeature> FOREST_POND = registerKey("forest_pond");
    public static final ResourceKey<PlacedFeature> OLD_GROWTH_FLOWERS = registerKey("old_growth_flowers");

    public static final ResourceKey<PlacedFeature> COLD_BEACH_POND = registerKey("cold_beach_pond");

    public static final ResourceKey<PlacedFeature> STEPPE_GRASS = registerKey("steppe_grass");
    public static final ResourceKey<PlacedFeature> STEPPE_PILLARS = registerKey("steppe_pillars");

    public static final ResourceKey<PlacedFeature> SWAMP_GRASS = registerKey("swamp_grass");
    public static final ResourceKey<PlacedFeature> SWAMP_POND = registerKey("swamp_pond");

    public static final ResourceKey<PlacedFeature> TREES_PINE_BARRENS = registerKey("trees_pine_barrens");
    public static final ResourceKey<PlacedFeature> TREES_OAK_WOODLAND = registerKey("trees_oak_woodland");
    public static final ResourceKey<PlacedFeature> TREES_RAINFOREST = registerKey("trees_rainforest");
    public static final ResourceKey<PlacedFeature> TREES_DARK_FOREST = registerKey("trees_dark_forest");

    public static final ResourceKey<PlacedFeature> TREES_FIR_THICKET = registerKey("trees_fir_thicket");
    public static final ResourceKey<PlacedFeature> TREES_ALPINE_GROVE = registerKey("trees_alpine_grove");
    public static final ResourceKey<PlacedFeature> TREES_ALPINE_PLAINS = registerKey("trees_alpine_plains");
    public static final ResourceKey<PlacedFeature> TREES_HIGHLANDS = registerKey("trees_highlands");
    public static final ResourceKey<PlacedFeature> TREES_SWAMP = registerKey("trees_swamp");
    public static final ResourceKey<PlacedFeature> TREES_MARSH = registerKey("trees_marsh");
    public static final ResourceKey<PlacedFeature> TREES_DAPPLED_CLEARING = registerKey("trees_dappled_clearing");
    public static final ResourceKey<PlacedFeature> TREES_HOWLING_PEAKS = registerKey("trees_howling_peaks");
    public static final ResourceKey<PlacedFeature> SHRUBLAND_SHRUBS = registerKey("shrubland_shrubs");
    public static final ResourceKey<PlacedFeature> HOWLING_SPIRES = registerKey("howling_spires");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var feature = context.lookup(Registries.FEATURE);

        // Rocks
        PlacementUtils.register(context, FOREST_ROCK_SPARSE, feature.getOrThrow(MiscOverworldFeatures.FOREST_ROCK), surface(rare(4)));
        PlacementUtils.register(context, MEADOW_ROCK, feature.getOrThrow(MiscOverworldFeatures.FOREST_ROCK), surface(rare(36)));

        // Hibiscus
        PlacementUtils.register(context, HIBISCUS_JUNGLE, feature.getOrThrow(EFeatures.HIBISCUS_KEY), surface(rare(3)));
        PlacementUtils.register(context, HIBISCUS_SPARSE_JUNGLE, feature.getOrThrow(EFeatures.HIBISCUS_KEY), surface(rare(7)));

        // Bluebonnets :3
        PlacementUtils.register(context, BLUEBONNET, feature.getOrThrow(EFeatures.BLUEBONNET_KEY), surface(rare(6)));
        PlacementUtils.register(context, BLUEBONNET_DENSE, feature.getOrThrow(EFeatures.BLUEBONNET_DENSE_KEY), surface(count(3)));

        PlacementUtils.register(context, TUNDRA_ROCK, feature.getOrThrow(EFeatures.TUNDRA_ROCK), surface(rare(5)));
        PlacementUtils.register(context, SNOW_PATCH, feature.getOrThrow(EFeatures.SNOW_PATCH), BiomeFilter.biome());

        PlacementUtils.register(context, DRY_GRASS_CLUMP, feature.getOrThrow(EFeatures.DRY_GRASS_CLUMP), surface(count(2)));
        PlacementUtils.register(context, DRY_GRASS_CLUMP_SPARSE, feature.getOrThrow(EFeatures.DRY_GRASS_CLUMP), surface(rare(2)));
        PlacementUtils.register(context, BEACH_DRY_GRASS, feature.getOrThrow(EFeatures.BEACH_DRY_GRASS), surface(count(3)));
        PlacementUtils.register(context, DUNE_DRY_GRASS, feature.getOrThrow(EFeatures.BEACH_DRY_GRASS), surface(count(5)));

        // Amethyst Spires
        PlacementUtils.register(context, AMETHYST_NODE, feature.getOrThrow(EFeatures.AMETHYST_NODE_KEY),
                ranged(24, PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT));
        PlacementUtils.register(context, AMETHYST_SPIRE_CLUSTER, feature.getOrThrow(EFeatures.AMETHYST_SPIRE_CLUSTER_KEY),
                ranged(32, PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT));

        // Underwater disks
        PlacementUtils.register(context, MUD_DISK_CLAY, feature.getOrThrow(EFeatures.MUD_DISK_CLAY), waterDisk());
        PlacementUtils.register(context, MUD_DISK_GRAVEL, feature.getOrThrow(EFeatures.MUD_DISK_GRAVEL), waterDisk());
        PlacementUtils.register(context, MUD_DISK_SAND, feature.getOrThrow(EFeatures.MUD_DISK_SAND), waterDisk(count(3)));

        // Ores
        PlacementUtils.register(context, ORE_BEJEWELED_CALCITE, feature.getOrThrow(EFeatures.ORE_BEJEWELED_CALCITE),
                ranged(218, uniform(abs(-24), abs(128))));
        PlacementUtils.register(context, ORE_SILVER, feature.getOrThrow(EFeatures.ORE_SILVER),
                ranged(10, HeightRangePlacement.of(TrapezoidHeight.of(abs(-16), abs(16), 16))));
        PlacementUtils.register(context, ORE_SILVER_SMALL, feature.getOrThrow(EFeatures.ORE_SILVER_SMALL),
                ranged(2, HeightRangePlacement.triangle(abs(-48), abs(52))));
        PlacementUtils.register(context, SULFUR_CAVE_ORE_SILVER, feature.getOrThrow(EFeatures.SULFUR_CAVE_ORE_SILVER),
                ranged(14, uniform(VerticalAnchor.bottom(), abs(120))));
        PlacementUtils.register(context, SULFUR_CAVE_ORE_REDSTONE, feature.getOrThrow(EFeatures.SULFUR_CAVE_ORE_REDSTONE),
                ranged(31, uniform(VerticalAnchor.bottom(), abs(120))));
        PlacementUtils.register(context, ORE_SHALE, feature.getOrThrow(EFeatures.ORE_SHALE),
                ranged(2, uniform(VerticalAnchor.bottom(), abs(0))));
        PlacementUtils.register(context, ORE_ANCIENT_DEBRIS_LAVA, feature.getOrThrow(EFeatures.ORE_ANCIENT_DEBRIS_LAVA),
                ranged(1, uniform(VerticalAnchor.bottom(), abs(30))));

        PlacementUtils.register(context, BASALT_VENT, feature.getOrThrow(EFeatures.BASALT_VENT),
                rangedIf(290, uniform(VerticalAnchor.aboveBottom(24), VerticalAnchor.top()),
                        inAir(BlockPredicate.matchesBlocks(Direction.DOWN, Blocks.BASALT))));

        // Soul sand valley
        PlacementUtils.register(context, WITHER_ROSE, feature.getOrThrow(EFeatures.WITHER_ROSE),
                rangedIf(50, PlacementUtils.FULL_RANGE, survives(Blocks.WITHER_ROSE)));
        PlacementUtils.register(context, SOUL_CRYSTAL, feature.getOrThrow(EFeatures.SOUL_CRYSTAL),
                rangedIf(34, PlacementUtils.FULL_RANGE, inAir(BlockPredicate.hasSturdyFace(Direction.DOWN))));
        PlacementUtils.register(context, SOUL_SPROUTS, feature.getOrThrow(EFeatures.SOUL_SPROUTS),
                rangedIf(240, PlacementUtils.FULL_RANGE, survives(EBlocks.SHORT_SOUL_SPROUT)));
        PlacementUtils.register(context, SOULGLASS_PILLAR, feature.getOrThrow(EFeatures.SOULGLASS_PILLAR),
                rangedIf(28, PlacementUtils.FULL_RANGE, inAir(
                        BlockPredicate.wouldSurvive(EBlocks.SOULGLASS_CLUSTER),
                        BlockPredicate.matchesTag(Direction.DOWN, EBlockTags.NETHER_SOIL))));
        PlacementUtils.register(context, ORE_SOUL_SILVER, feature.getOrThrow(EFeatures.ORE_SOUL_SILVER),
                ranged(18, uniform(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10))));
        PlacementUtils.register(context, ORE_SOUL_QUARTZ, feature.getOrThrow(EFeatures.ORE_SOUL_QUARTZ),
                ranged(26, uniform(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10))));

        PlacementUtils.register(context, PEBBLE, feature.getOrThrow(EFeatures.PEBBLE),
                surfaceSurvive(EBlocks.PEBBLE, count(1)));

        PlacementUtils.register(context, OUTBACK_ACACIA, feature.getOrThrow(EFeatures.MEGA_ACACIA),
                surfaceIf(BlockPredicate.wouldSurvive(Blocks.ACACIA_SAPLING), rare(3)));

        // Moss / dirt patches
        PlacementUtils.register(context, RUSTY_MOSS_PATCH, feature.getOrThrow(EFeatures.RUSTY_MOSS_PATCH), surface(rare(5)));
        PlacementUtils.register(context, TUNDRA_RUSTY_MOSS_PATCH, feature.getOrThrow(EFeatures.TUNDRA_RUSTY_MOSS_PATCH), surface(rare(5)));
        PlacementUtils.register(context, TUNDRA_OCHRE_MOSS_PATCH, feature.getOrThrow(EFeatures.TUNDRA_OCHRE_MOSS_PATCH), surface(rare(4)));
        PlacementUtils.register(context, DAPPLED_OCHRE_MOSS_PATCH, feature.getOrThrow(EFeatures.OCHRE_MOSS_PATCH), surface(rare(5)));
        PlacementUtils.register(context, MOSSY_MUD_PATCH, feature.getOrThrow(EFeatures.MOSSY_MUD_PATCH_KEY), surface(rare(4)));
        PlacementUtils.register(context, RAINFOREST_MOSS_PATCH, feature.getOrThrow(CaveFeatures.MOSS_PATCH), surface(rare(2)));

        PlacementUtils.register(context, LUSH_DIRT_PATCH, feature.getOrThrow(EFeatures.COARSE_DIRT_PATCH), surface(rare(3)));
        PlacementUtils.register(context, DESERT_DIRT_PATCH, feature.getOrThrow(EFeatures.COARSE_DIRT_PATCH), surface(count(1)));

        // Desert rocks
        PlacementUtils.register(context, LUSH_DESERT_ROCKS, feature.getOrThrow(EFeatures.LUSH_SANDSTONE_ROCKS), surface(count(1)));
        PlacementUtils.register(context, DESERT_ROCKS, feature.getOrThrow(EFeatures.SANDSTONE_ROCK), surface(rare(2)));
        PlacementUtils.register(context, OUTBACK_ROCKS, feature.getOrThrow(EFeatures.RED_SANDSTONE_ROCK), surface(rare(3)));
        PlacementUtils.register(context, ROCKY_DESERT_ROCKS, feature.getOrThrow(EFeatures.GRANITE_ROCK), surface(count(1)));

        // Cacti
        PlacementUtils.register(context, LUSH_SHORT_CACTUS, feature.getOrThrow(EFeatures.SHORT_CACTUS),
                surfaceSurvive(Blocks.CACTUS, count(3)));
        PlacementUtils.register(context, LUSH_TALL_CACTUS, feature.getOrThrow(EFeatures.TALL_CACTUS),
                surfaceSurvive(Blocks.CACTUS, rare(6), count(3)));
        PlacementUtils.register(context, DESERT_SHORT_CACTUS, feature.getOrThrow(EFeatures.SHORT_CACTUS),
                surfaceSurvive(Blocks.CACTUS, count(2)));
        PlacementUtils.register(context, DESERT_TALL_CACTUS, feature.getOrThrow(EFeatures.TALL_CACTUS),
                surfaceSurvive(Blocks.CACTUS, rare(7), count(2)));
        PlacementUtils.register(context, ROCKY_SHORT_CACTUS, feature.getOrThrow(EFeatures.SHORT_CACTUS),
                surfaceSurvive(Blocks.CACTUS, count(4)));
        PlacementUtils.register(context, ROCKY_TALL_CACTUS, feature.getOrThrow(EFeatures.TALL_CACTUS),
                surfaceSurvive(Blocks.CACTUS, rare(6), count(4)));

        // Shrubs
        // NOTE: this checks wouldSurvive(CACTUS), which looks like a copy-paste leftover. Kept as-is.
        PlacementUtils.register(context, AZALEA_SHRUB, feature.getOrThrow(EFeatures.AZALEA_SHRUB),
                surfaceSurvive(Blocks.CACTUS, count(1)));
        PlacementUtils.register(context, OAK_SHRUBS, feature.getOrThrow(EFeatures.OAK_SHRUB),
                surfaceSurvive(Blocks.OAK_SAPLING, count(1)));
        PlacementUtils.register(context, JUNGLE_SHRUBS, feature.getOrThrow(EFeatures.JUNGLE_SHRUB),
                surfaceSurvive(Blocks.JUNGLE_SAPLING, count(4)));
        PlacementUtils.register(context, WINDSWEPT_SHRUB, feature.getOrThrow(EFeatures.JUNGLE_SHRUB),
                surfaceSurvive(Blocks.JUNGLE_SAPLING, count(3)));
        PlacementUtils.register(context, SHRUBLAND_SHRUBS, feature.getOrThrow(EFeatures.OAK_SHRUB),
                surfaceSurvive(Blocks.OAK_SAPLING, count(5)));

        // Cedar / mahogany
        PlacementUtils.register(context, CEDAR_FOREST_TREES, feature.getOrThrow(EFeatures.TREES_CEDAR_AND_OAK_LEAF_LITTER),
                surfaceSurvive(EBlocks.CEDAR_SAPLING, count(17)));
        PlacementUtils.register(context, CEDAR_TREES_SPARSE, feature.getOrThrow(EFeatures.CEDAR_TREE),
                surfaceSurvive(EBlocks.CEDAR_SAPLING, rare(5)));
        PlacementUtils.register(context, WINDSWEPT_MAHOGANY, feature.getOrThrow(EFeatures.MAHOGANY_TREE),
                surfaceSurvive(EBlocks.MAHOGANY_SAPLING, count(4)));

        // Jungle
        PlacementUtils.register(context, JUNGLE_TREES, feature.getOrThrow(EFeatures.JUNGLE_TREES_KEY), trees(50, Blocks.JUNGLE_SAPLING));
        PlacementUtils.register(context, SPARSE_JUNGLE_TREES, feature.getOrThrow(EFeatures.SPARSE_JUNGLE_TREES_KEY), trees(2, Blocks.JUNGLE_SAPLING));
        PlacementUtils.register(context, BAMBOO_JUNGLE_TREES, feature.getOrThrow(EFeatures.BAMBOO_JUNGLE_TREES_KEY), trees(30, Blocks.JUNGLE_SAPLING));
        PlacementUtils.register(context, TREES_ROCKY_JUNGLE, feature.getOrThrow(EFeatures.TREES_ROCKY_JUNGLE), trees(35, Blocks.JUNGLE_SAPLING));

        // Forest / cherry / birch
        PlacementUtils.register(context, TREES_FLOWER_FOREST, feature.getOrThrow(EFeatures.TREES_FLOWER_FOREST), trees(7, Blocks.OAK_SAPLING));
        PlacementUtils.register(context, TREES_FOREST, feature.getOrThrow(EFeatures.TREES_FOREST), trees(14, Blocks.OAK_SAPLING));
        PlacementUtils.register(context, FLOWER_FOREST_WILDFLOWERS, feature.getOrThrow(EFeatures.SPARSE_FOREST_FLOWERS),
                surfaceSurvive(Blocks.WILDFLOWERS, count(63)));
        PlacementUtils.register(context, CHERRY_GROVE_BAMBOO, feature.getOrThrow(VegetationFeatures.BAMBOO_NO_PODZOL),
                surfaceSurvive(Blocks.BAMBOO, count(12)));
        PlacementUtils.register(context, SPARSE_CHERRY, feature.getOrThrow(TreeFeatures.CHERRY_BEES_005), trees(1, Blocks.CHERRY_SAPLING));

        PlacementUtils.register(context, TREES_BIRCH, feature.getOrThrow(EFeatures.TREES_BIRCH), trees(9, Blocks.BIRCH_SAPLING));
        PlacementUtils.register(context, TREES_GROVE, feature.getOrThrow(EFeatures.TREES_GROVE),
                surfaceIf(BlockPredicate.matchesBlocks(Direction.DOWN, Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW),
                        PlacementUtils.countExtra(7, 0.1F, 1)));
        PlacementUtils.register(context, TREES_OLD_GROWTH_BIRCH, feature.getOrThrow(EFeatures.TREES_OLD_GROWTH_BIRCH), trees(9, Blocks.BIRCH_SAPLING));

        PlacementUtils.register(context, DENSE_DRY_GRASS, feature.getOrThrow(VegetationFeatures.DRY_GRASS),
                surfaceSurvive(Blocks.SHORT_DRY_GRASS, count(18)));

        // Beaches / coast
        PlacementUtils.register(context, BEACHSTONE_ROCKS, feature.getOrThrow(EFeatures.BEACHSTONE_ROCK_KEY),
                surfaceSurvive(Blocks.SHORT_GRASS, count(1)));
        PlacementUtils.register(context, EXTRA_BEACHSTONE_ROCKS, feature.getOrThrow(EFeatures.BEACHSTONE_ROCK_KEY),
                surfaceSurvive(Blocks.SHORT_GRASS, count(2)));
        PlacementUtils.register(context, UNDERWATER_BEACHSTONE_ROCKS, feature.getOrThrow(EFeatures.BEACHSTONE_ROCK_KEY),
                seabedIf(BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP), rare(3)));
        PlacementUtils.register(context, ROCKWEED_BASALT_PATCH, feature.getOrThrow(EFeatures.ROCKWEED_BASALT_PATCH_KEY), surface(count(2)));
        PlacementUtils.register(context, TIDEPOOL, feature.getOrThrow(EFeatures.TIDEPOOL_KEY),
                with(seabed(count(3)),
                        SurfaceWaterDepthFilter.forMaxDepth(1),
                        BlockPredicateFilter.forPredicate(BlockPredicate.solid(Direction.DOWN)),
                        SeaLevelFilter.of(-1, 0)));

        // Crags
        PlacementUtils.register(context, PLAINS_CRAG, feature.getOrThrow(EFeatures.PLAINS_CRAG),
                surfaceIf(inAir(BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP)), rare(7)));
        PlacementUtils.register(context, SHALLOW_LUKEWARM_CRAG, feature.getOrThrow(EFeatures.SHALLOW_LUKEWARM_CRAG),
                seabedIf(inWaterOnSturdy(), rare(5)));
        PlacementUtils.register(context, DEEP_LUKEWARM_CRAG, feature.getOrThrow(EFeatures.DEEP_LUKEWARM_CRAG),
                seabedIf(inWaterOnSturdy(), rare(4)));

        // Seagrass / kelp
        PlacementUtils.register(context, SEAGRASS_EXTRA, feature.getOrThrow(AquaticFeatures.SEAGRASS_SHORT),
                seabedIf(BlockPredicate.matchesBlocks(Blocks.WATER), count(12)));
        PlacementUtils.register(context, SEAGRASS_MID_EXTRA, feature.getOrThrow(AquaticFeatures.SEAGRASS_MID),
                seabedIf(BlockPredicate.matchesBlocks(Blocks.WATER), count(8)));
        PlacementUtils.register(context, KELP_CLUSTER, feature.getOrThrow(EFeatures.RANDOM_SHORT_KELP),
                seabed(rare(2), count(7)));

        // Caves
        PlacementUtils.register(context, CAVE_WEEDS, feature.getOrThrow(EFeatures.CAVE_WEEDS_KEY),
                rangedIf(40, uniform(VerticalAnchor.bottom(), abs(60)), survives(EBlocks.CAVE_WEED)));
        PlacementUtils.register(context, CAVE_ROOTS, feature.getOrThrow(EFeatures.CAVE_ROOTED_DIRT),
                count(4),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                BiomeFilter.biome());
        PlacementUtils.register(context, SPARSE_DRIPSTONE, feature.getOrThrow(CaveFeatures.DRIPSTONE_CLUSTER),
                CountPlacement.of(UniformInt.of(9, 14)),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                BiomeFilter.biome());

        // Wetlands
        PlacementUtils.register(context, SWAMP_CATTAILS, feature.getOrThrow(EFeatures.CATTAILS),
                with(seabed(count(3)), SurfaceWaterDepthFilter.forMaxDepth(1), SeaLevelFilter.of(-2, -1)));
        PlacementUtils.register(context, MANGROVE_CATTAILS, feature.getOrThrow(EFeatures.CATTAILS),
                with(seabed(count(5)), SurfaceWaterDepthFilter.forMaxDepth(1), SeaLevelFilter.of(-2, -1)));
        PlacementUtils.register(context, TREES_BOG, feature.getOrThrow(EFeatures.TREES_BOG),
                surfaceSurvive(Blocks.OAK_SAPLING, count(3)));
        PlacementUtils.register(context, SWAMP_GRASS, feature.getOrThrow(VegetationFeatures.GRASS_JUNGLE),
                surfaceSurvive(Blocks.SHORT_GRASS, count(232)));
        PlacementUtils.register(context, TREES_SWAMP, feature.getOrThrow(EFeatures.TREES_SWAMP),
                with(wetTrees(PlacementUtils.countExtra(7, 0.1F, 1), 3),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING))));
        PlacementUtils.register(context, TREES_MARSH, feature.getOrThrow(EFeatures.TREES_SWAMP),
                wetTrees(rare(4), 2));
        PlacementUtils.register(context, SWAMP_POND, feature.getOrThrow(EFeatures.SWAMP_POND), surface(count(3)));

        // Cold biomes
        PlacementUtils.register(context, TREES_TUNDRA, feature.getOrThrow(EFeatures.TREES_TUNDRA),
                surfaceSurvive(Blocks.SPRUCE_SAPLING, rare(7)));
        PlacementUtils.register(context, TREES_TAIGA, feature.getOrThrow(EFeatures.TREES_TAIGA), trees(8, Blocks.SPRUCE_SAPLING));
        PlacementUtils.register(context, TREES_OLD_GROWTH_SPRUCE_TAIGA, feature.getOrThrow(EFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA), trees(10, Blocks.SPRUCE_SAPLING));
        PlacementUtils.register(context, TREES_OLD_GROWTH_PINE_TAIGA, feature.getOrThrow(EFeatures.TREES_OLD_GROWTH_PINE_TAIGA), trees(10, Blocks.SPRUCE_SAPLING));

        // Temperate
        PlacementUtils.register(context, TREES_OLD_GROWTH_FOREST, feature.getOrThrow(EFeatures.TREES_OLD_GROWTH_FOREST), trees(16, 0.5F, Blocks.OAK_SAPLING));
        PlacementUtils.register(context, FOREST_POND, feature.getOrThrow(EFeatures.FOREST_POND), surface(rare(5)));
        PlacementUtils.register(context, OLD_GROWTH_FLOWERS, feature.getOrThrow(EFeatures.OLD_GROWTH_FLOWERS),
                surfaceIf(BlockPredicate.ONLY_IN_AIR_PREDICATE, rare(3)));
        PlacementUtils.register(context, COLD_BEACH_POND, feature.getOrThrow(EFeatures.COLD_BEACH_POND), surface(count(2)));

        PlacementUtils.register(context, STEPPE_GRASS, feature.getOrThrow(VegetationFeatures.TALL_GRASS),
                surfaceSurvive(Blocks.TALL_GRASS, count(28)));
        PlacementUtils.register(context, STEPPE_PILLARS, feature.getOrThrow(EFeatures.LIMESTONE_ROCK),
                surfaceSurvive(Blocks.SHORT_GRASS, rare(9)));

        PlacementUtils.register(context, TREES_PINE_BARRENS, feature.getOrThrow(EFeatures.TREES_PINE_BARRENS), surfaceSurvive(EBlocks.PINE_SAPLING, rare(3)));
        PlacementUtils.register(context, TREES_OAK_WOODLAND, feature.getOrThrow(EFeatures.TREES_OAK_WOODLAND), trees(5, 0.05F, Blocks.OAK_SAPLING));
        PlacementUtils.register(context, TREES_RAINFOREST, feature.getOrThrow(EFeatures.TREES_RAINFOREST), trees(12, 0.05F, Blocks.OAK_SAPLING));
        PlacementUtils.register(context, TREES_DARK_FOREST, feature.getOrThrow(EFeatures.TREES_DARK_FOREST), trees(15, Blocks.DARK_OAK_SAPLING));

        PlacementUtils.register(context, TREES_FIR_THICKET, feature.getOrThrow(EFeatures.TREES_FIR_THICKET), trees(14, EBlocks.PINE_SAPLING));
        PlacementUtils.register(context, TREES_ALPINE_GROVE, feature.getOrThrow(EFeatures.TREES_ALPINE), trees(8, EBlocks.PINE_SAPLING));
        PlacementUtils.register(context, TREES_ALPINE_PLAINS, feature.getOrThrow(EFeatures.TREES_ALPINE), trees(0, 0.5F, EBlocks.PINE_SAPLING));
        PlacementUtils.register(context, TREES_HIGHLANDS, feature.getOrThrow(EFeatures.TREES_HIGHLANDS), trees(2, Blocks.SPRUCE_SAPLING));
        PlacementUtils.register(context, TREES_DAPPLED_CLEARING, feature.getOrThrow(EFeatures.TREES_DAPPLED_CLEARING), trees(1, Blocks.OAK_SAPLING));
        PlacementUtils.register(context, TREES_HOWLING_PEAKS, feature.getOrThrow(EFeatures.TREES_HOWLING_PEAKS), trees(1, 0.5F, Blocks.SPRUCE_SAPLING));
        PlacementUtils.register(context, HOWLING_SPIRES, feature.getOrThrow(EFeatures.HOWLING_SPIRES), surfaceSurvive(Blocks.SHORT_GRASS, rare(5)));
    }

    // Helper Methods

    // frequency
    private static PlacementModifier count(int n) {
        return CountPlacement.of(n);
    }

    private static PlacementModifier rare(int n) {
        return RarityFilter.onAverageOnceEvery(n);
    }

    // heights
    private static VerticalAnchor abs(int y) {
        return VerticalAnchor.absolute(y);
    }

    private static PlacementModifier uniform(VerticalAnchor min, VerticalAnchor max) {
        return HeightRangePlacement.uniform(min, max);
    }

    // predicates
    private static BlockPredicate inAir(BlockPredicate... extra) {
        BlockPredicate[] all = new BlockPredicate[extra.length + 1];
        all[0] = BlockPredicate.ONLY_IN_AIR_PREDICATE;
        System.arraycopy(extra, 0, all, 1, extra.length);
        return BlockPredicate.allOf(all);
    }

    private static BlockPredicate survives(Block block) {
        return inAir(BlockPredicate.wouldSurvive(block));
    }

    private static BlockPredicate inWaterOnSturdy() {
        return BlockPredicate.allOf(
                BlockPredicate.matchesBlocks(Blocks.WATER),
                BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP));
    }

    // core: frequency..., InSquare, heightmap, biome, [predicate]
    private static List<PlacementModifier> scatter(PlacementModifier heightmap, BlockPredicate predicate, PlacementModifier... frequency) {
        List<PlacementModifier> list = new ArrayList<>(Arrays.asList(frequency));
        list.add(InSquarePlacement.spread());
        list.add(heightmap);
        list.add(BiomeFilter.biome());
        if (predicate != null) {
            list.add(BlockPredicateFilter.forPredicate(predicate));
        }
        return list;
    }

    private static List<PlacementModifier> with(List<PlacementModifier> base, PlacementModifier... extra) {
        List<PlacementModifier> list = new ArrayList<>(base);
        list.addAll(Arrays.asList(extra));
        return list;
    }

    // world surface
    private static List<PlacementModifier> surface(PlacementModifier... frequency) {
        return scatter(PlacementUtils.HEIGHTMAP_WORLD_SURFACE, null, frequency);
    }

    private static List<PlacementModifier> surfaceIf(BlockPredicate predicate, PlacementModifier... frequency) {
        return scatter(PlacementUtils.HEIGHTMAP_WORLD_SURFACE, predicate, frequency);
    }

    private static List<PlacementModifier> surfaceSurvive(Block block, PlacementModifier... frequency) {
        return surfaceIf(survives(block), frequency);
    }

    // ocean floor
    private static List<PlacementModifier> seabed(PlacementModifier... frequency) {
        return scatter(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, null, frequency);
    }

    private static List<PlacementModifier> seabedIf(BlockPredicate predicate, PlacementModifier... frequency) {
        return scatter(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, predicate, frequency);
    }

    // underground / height range: count, InSquare, height, [predicate], biome
    private static List<PlacementModifier> ranged(int count, PlacementModifier height) {
        return List.of(count(count), InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    private static List<PlacementModifier> rangedIf(int count, PlacementModifier height, BlockPredicate predicate) {
        return List.of(count(count), InSquarePlacement.spread(), height,
                BlockPredicateFilter.forPredicate(predicate), BiomeFilter.biome());
    }

    // water disks: top solid with a water check
    private static List<PlacementModifier> waterDisk(PlacementModifier... frequency) {
        return scatter(PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicate.matchesSourceFluids(Fluids.WATER), frequency);
    }

    // trees
    private static List<PlacementModifier> trees(int base, Block sapling) {
        return trees(base, 0.1F, sapling);
    }

    private static List<PlacementModifier> trees(int base, float extraChance, Block sapling) {
        return VegetationPlacements.treePlacement(PlacementUtils.countExtra(base, extraChance, 1), sapling);
    }

    private static List<PlacementModifier> wetTrees(PlacementModifier frequency, int maxWaterDepth) {
        return List.of(frequency, InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(maxWaterDepth), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(Direction.DOWN, EBlockTags.SOIL)), BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }
}