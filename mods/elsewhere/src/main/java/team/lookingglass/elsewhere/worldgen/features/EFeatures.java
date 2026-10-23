package team.lookingglass.elsewhere.worldgen.features;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.*;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.tags.EBlockTags;
import team.lookingglass.elsewhere.registry.EBlocks;
import team.lookingglass.elsewhere.registry.blocktypes.PebbleBlock;
import team.lookingglass.elsewhere.worldgen.features.types.*;

import java.util.List;

public class EFeatures {

    public static final ResourceKey<Feature> BLUEBONNET_KEY = registerKey("bluebonnet");
    public static final ResourceKey<Feature> BLUEBONNET_DENSE_KEY = registerKey("bluebonnet_dense");
    public static final ResourceKey<Feature> HIBISCUS_KEY = registerKey("hibiscus");

    public static final ResourceKey<Feature> RED_GRASS_VEGETATION = registerKey("red_grass_vegetation");
    public static final ResourceKey<Feature> RED_VEGETATION_PATCH = registerKey("red_vegetation_patch");
    public static final ResourceKey<Feature> DRY_GRASS_CLUMP = registerKey("dry_glass_clump");
    public static final ResourceKey<Feature> TUNDRA_ROCK = registerKey("tundra_rock");
    public static final ResourceKey<Feature> SNOW_PATCH = registerKey("snow_patch");

    public static final ResourceKey<Feature> AMETHYST_NODE_KEY = registerKey("amethyst_node");
    public static final ResourceKey<Feature> AMETHYST_SPIRE_KEY = registerKey("amethyst_spire");
    public static final ResourceKey<Feature> AMETHYST_SPIRE_CLUSTER_KEY = registerKey("amethyst_spire_cluster");

    public static final ResourceKey<Feature> ORE_BEJEWELED_CALCITE = registerKey("ore_bejeweled_calcite");
    public static final ResourceKey<Feature> ORE_SILVER = registerKey("ore_silver");
    public static final ResourceKey<Feature> ORE_SILVER_SMALL = registerKey("ore_silver_small");
    public static final ResourceKey<Feature> ORE_TIN = registerKey("ore_tin");
    public static final ResourceKey<Feature> ORE_TIN_LARGE = registerKey("ore_tin_large");
    public static final ResourceKey<Feature> ORE_TIN_SMALL = registerKey("ore_tin_small");
    public static final ResourceKey<Feature> ORE_SHALE = registerKey("ore_shale");
    public static final ResourceKey<Feature> ORE_ANCIENT_DEBRIS_LAVA = registerKey("ore_ancient_debris_lava");

    public static final ResourceKey<Feature> BASALT_VENT = registerKey("basalt_vent");

    public static final ResourceKey<Feature> WITHER_ROSE = registerKey("wither_rose");
    public static final ResourceKey<Feature> SOUL_CRYSTAL = registerKey("soul_crystal");
    public static final ResourceKey<Feature> SOUL_SPROUTS = registerKey("soul_sprouts");
    public static final ResourceKey<Feature> SOULGLASS_PILLAR = registerKey("soulgass_pillar");
    public static final ResourceKey<Feature> ORE_SOUL_SILVER = registerKey("ore_soul_silver");
    public static final ResourceKey<Feature> ORE_SOUL_QUARTZ = registerKey("ore_soul_quartz");

    public static final ResourceKey<Feature> PEBBLE = registerKey("pebble");
    public static final ResourceKey<Feature> MEGA_ACACIA = registerKey("mega_acacia_tree");
    public static final ResourceKey<Feature> OAK_SHRUB = registerKey("oak_shrub");
    public static final ResourceKey<Feature> JUNGLE_SHRUB = registerKey("jungle_shrub");
    public static final ResourceKey<Feature> CEDAR_TREE = registerKey("cedar_tree");
    public static final ResourceKey<Feature> CEDAR_TREE_LEAF_LITTER = registerKey("cedar_tree_leaf_litter");
    public static final ResourceKey<Feature> TALL_CEDAR_LEAF_LITTER = registerKey("tall_cedar_leaf_litter");
    public static final ResourceKey<Feature> FALLEN_CEDAR_TREE = registerKey("fallen_cedar_tree");
    public static final ResourceKey<Feature> TREES_CEDAR_AND_OAK_LEAF_LITTER = registerKey("trees_cedar_and_oak_leaf_litter");

    public static final ResourceKey<Feature> MAHOGANY_TREE = registerKey("mahogany_tree");
    public static final ResourceKey<Feature> JUNGLE_TREES_KEY = registerKey("jungle_trees");
    public static final ResourceKey<Feature> SPARSE_JUNGLE_TREES_KEY = registerKey("sparse_jungle_trees");
    public static final ResourceKey<Feature> BAMBOO_JUNGLE_TREES_KEY = registerKey("bamboo_jungle_trees");

    public static final ResourceKey<Feature> FLOWER_FOREST_TREES_KEY = registerKey("flower_forest_trees");
    public static final ResourceKey<Feature> SPARSE_FOREST_FLOWERS = registerKey("sparse_forest_flowers");

    public static final ResourceKey<Feature> GILDED_BIRCH_CLEAR = registerKey("gilded_birch_clear");
    public static final ResourceKey<Feature> GILDED_BIRCH = registerKey("gilded_birch");
    public static final ResourceKey<Feature> SUPER_GILDED_BIRCH = registerKey("super_gilded_birch");
    public static final ResourceKey<Feature> BIRCH_TREES_KEY = registerKey("birch_trees");
    public static final ResourceKey<Feature> OLD_GROWTH_BIRCH_TREES_KEY = registerKey("old_growth_birch_trees");
    public static final ResourceKey<Feature> RANDOM_BIRCH = registerKey("random_birch");

    public static final ResourceKey<Feature> TALL_CACTUS = registerKey("tall_cactus");
    public static final ResourceKey<Feature> SHORT_CACTUS = registerKey("short_cactus");
    public static final ResourceKey<Feature> COARSE_DIRT_PATCH = registerKey("coarse_dirt_patch");
    public static final ResourceKey<Feature> AZALEA_SHRUB_KEY = registerKey("azalea_shrub");

    public static final ResourceKey<Feature> SANDSTONE_ROCK = registerKey("sandstone_rock");
    public static final ResourceKey<Feature> RED_SANDSTONE_ROCK = registerKey("red_sandstone_rock");
    public static final ResourceKey<Feature> PINK_SANDSTONE_ROCK = registerKey("pink_sandstone_rock");
    public static final ResourceKey<Feature> LUSH_SANDSTONE_ROCKS = registerKey("lush_sandstone_rocks");

    public static final ResourceKey<Feature> RUSTY_MOSS_VEGETATION = registerKey("rusty_moss_vegetation");
    public static final ResourceKey<Feature> RUSTY_MOSS_PATCH_KEY = registerKey("rusty_moss_patch");
    public static final ResourceKey<Feature> RUSTY_MOSS_PATCH_BONEMEAL = registerKey("rusty_moss_patch_bonemeal");

    public static final ResourceKey<Feature> MOSSY_MUD_VEGETATION = registerKey("mossy_mud_vegetation");
    public static final ResourceKey<Feature> MOSSY_MUD_PATCH_KEY = registerKey("mossy_mud_patch");

    public static final ResourceKey<Feature> BEACHSTONE_ROCK_KEY = registerKey("beachstone_rock");
    public static final ResourceKey<Feature> ROCKWEED_BASALT_PATCH_KEY = registerKey("rockweed_basalt_patch");
    public static final ResourceKey<Feature> TIDEPOOL_KEY = registerKey("tidepool");

    public static final ResourceKey<Feature> PLAINS_CRAG = registerKey("plains_crag");
    public static final ResourceKey<Feature> SHALLOW_LUKEWARM_CRAG = registerKey("shallow_lukewarm_crag");
    public static final ResourceKey<Feature> DEEP_LUKEWARM_CRAG = registerKey("deep_lukewarm_crag");

    public static final ResourceKey<Feature> SHORT_KELP = registerKey("short_kelp");
    public static final ResourceKey<Feature> MEDIUM_KELP = registerKey("medium_kelp");
    public static final ResourceKey<Feature> TALL_KELP = registerKey("tall_kelp");
    public static final ResourceKey<Feature> RANDOM_SHORT_KELP = registerKey("random_short_kelp");

    public static final ResourceKey<Feature> CAVE_WEEDS_KEY = registerKey("cave_weeds_key");
    public static final ResourceKey<Feature> HANGING_ROOTS_CEILING = registerKey("hanging_roots_ceiling");
    public static final ResourceKey<Feature> CAVE_ROOTED_DIRT = registerKey("cave_rooted_dirt");

    public static final ResourceKey<Feature> CATTAILS = registerKey("cattails");

    public static final ResourceKey<Feature> LIMESTONE_ROCK = registerKey("limestone_rock");


    @SuppressWarnings("deprecation")
    public static void bootstrap(BootstrapContext<Feature> context) {
        HolderGetter<Feature> features = context.lookup(Registries.FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        PlaceOnGroundDecorator sparseLeafLitter = new PlaceOnGroundDecorator(96, 4, 2, Holder.direct(new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 3))));
        PlaceOnGroundDecorator thickLeafLitter = new PlaceOnGroundDecorator(150, 2, 2, Holder.direct(new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 4))));
        BeehiveDecorator beehive = new BeehiveDecorator(0.002F);

        context.register(BLUEBONNET_KEY, new BlockPatchFeature(
                BlockStateProvider.of(EBlocks.BLUEBONNET.defaultBlockState()),
                BlockPredicate.matchesTag(BlockTags.DIRT),
                BlockPredicate.replaceable(),
                3, 1, 6, 3,
                Direction.DOWN, 1
        ));

        context.register(BLUEBONNET_DENSE_KEY, new BlockPatchFeature(
                BlockStateProvider.of(EBlocks.BLUEBONNET.defaultBlockState()),
                BlockPredicate.matchesTag(BlockTags.DIRT),
                BlockPredicate.replaceable(),
                4, 1, 12, 6,
                Direction.DOWN, 1
        ));

        context.register(HIBISCUS_KEY, new BlockPatchFeature(
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(EBlocks.RED_HIBISCUS.defaultBlockState(), 1)
                                .add(EBlocks.ORANGE_HIBISCUS.defaultBlockState(), 1)
                                .add(EBlocks.YELLOW_HIBISCUS.defaultBlockState(), 1)
                                .add(EBlocks.BLUE_HIBISCUS.defaultBlockState(), 1)
                                .add(EBlocks.PURPLE_HIBISCUS.defaultBlockState(), 1)
                                .add(EBlocks.PINK_HIBISCUS.defaultBlockState(), 1)
                                .add(EBlocks.WHITE_HIBISCUS.defaultBlockState(), 1)
                                .build()
                ),
                BlockPredicate.matchesTag(BlockTags.DIRT),
                BlockPredicate.replaceable(),
                6, 3, 7, 5,
                Direction.DOWN, 1
        ));

        context.register(RED_GRASS_VEGETATION, new SimpleBlockFeature(BlockStateProvider.of(EBlocks.SHORT_RED_GRASS)));

        context.register(RED_VEGETATION_PATCH, new VegetationPatchFeature(
                blocks.getOrThrow(EBlockTags.SOIL), Holder.direct(BlockStateProvider.of(EBlocks.RED_GRASS_BLOCK)),
                PlacementUtils.inlinePlaced(features.getOrThrow(RED_GRASS_VEGETATION)), CaveSurface.FLOOR,
                ConstantInt.of(1), 0.0F, 5, 0.25F, UniformInt.of(3, 5), 0.35F)
        );

        context.register(DRY_GRASS_CLUMP, new BlockPatchFeature(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.SHORT_DRY_GRASS.defaultBlockState(), 5)
                        .add(Blocks.TALL_DRY_GRASS.defaultBlockState(), 2)
                        .build()),
                BlockPredicate.matchesTag(EBlockTags.SOIL),
                BlockPredicate.replaceable(),
                2, 1, 5, 3,
                Direction.DOWN, 1
        ));

        context.register(TUNDRA_ROCK, new LargeBlobFeature(Blocks.TUFF.defaultBlockState(), BlockPredicate.solid(), UniformInt.of(3, 4)));

        context.register(SNOW_PATCH, new BlockPatchFeature(
                BlockStateProvider.of(Blocks.SNOW),
                BlockPredicate.allOf(BlockPredicate.solid(Direction.DOWN), BlockPredicate.noFluid()),
                BlockPredicate.replaceable(),
                3, 1, 15, 7,
                Direction.DOWN, 1
        ));

        context.register(AMETHYST_NODE_KEY, new AmethystNodeFeature());

        context.register(AMETHYST_SPIRE_KEY, new SpeleothemFeature(
                Blocks.AMETHYST_BLOCK.defaultBlockState(),
                EBlocks.AMETHYST_SPIRE.defaultBlockState(),
                blocks.getOrThrow(EBlockTags.AMETHYST_SPIRE_REPLACEABLE),
                0.2F, 0.7F, 0.5F, 0.5F
        ));

        context.register(AMETHYST_SPIRE_CLUSTER_KEY, new SpeleothemClusterFeature(
                Blocks.AMETHYST_BLOCK.defaultBlockState(),
                EBlocks.AMETHYST_SPIRE.defaultBlockState(),
                blocks.getOrThrow(EBlockTags.AMETHYST_SPIRE_REPLACEABLE),
                12, UniformInt.of(1, 9), UniformInt.of(1, 3), 3, 3, UniformInt.of(1, 2),
                UniformFloat.of(0.3F, 0.7F), ConstantFloat.of(0.1F), 0.05F, 1, 3
        ));

        context.register(ORE_BEJEWELED_CALCITE, new OreFeature(
                new TagMatchTest(EBlockTags.CALCITE_ORE_REPLACEABLES), EBlocks.BEJEWELED_CALCITE.defaultBlockState(), 9));

        context.register(ORE_SILVER, new OreFeature(List.of(
                new BlockReplacement(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), EBlocks.SILVER_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), EBlocks.DEEPSLATE_SILVER_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(EBlockTags.CINNABAR_ORE_REPLACEABLES), EBlocks.CINNABAR_SILVER_ORE.defaultBlockState())
        ), 3));
        context.register(ORE_SILVER_SMALL, new OreFeature(List.of(
                new BlockReplacement(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), EBlocks.SILVER_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), EBlocks.DEEPSLATE_SILVER_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(EBlockTags.CINNABAR_ORE_REPLACEABLES), EBlocks.CINNABAR_SILVER_ORE.defaultBlockState())
        ), 1));

        context.register(ORE_TIN, new OreFeature(List.of(
                new BlockReplacement(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), EBlocks.TIN_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), EBlocks.DEEPSLATE_TIN_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(EBlockTags.CINNABAR_ORE_REPLACEABLES), EBlocks.CINNABAR_TIN_ORE.defaultBlockState())
        ), 6));
        context.register(ORE_TIN_LARGE, new OreFeature(List.of(
                new BlockReplacement(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), EBlocks.TIN_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), EBlocks.DEEPSLATE_TIN_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(EBlockTags.CINNABAR_ORE_REPLACEABLES), EBlocks.CINNABAR_TIN_ORE.defaultBlockState())
        ), 15));
        context.register(ORE_TIN_SMALL, new OreFeature(List.of(
                new BlockReplacement(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), EBlocks.TIN_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), EBlocks.DEEPSLATE_TIN_ORE.defaultBlockState()),
                new BlockReplacement(new TagMatchTest(EBlockTags.CINNABAR_ORE_REPLACEABLES), EBlocks.CINNABAR_TIN_ORE.defaultBlockState())
        ), 4));

        context.register(ORE_SHALE, new OreFeature(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), EBlocks.SHALE.defaultBlockState(), 32));
        context.register(ORE_ANCIENT_DEBRIS_LAVA, new OreFeature(new TagMatchTest(BlockTags.BASE_STONE_NETHER), Blocks.ANCIENT_DEBRIS.defaultBlockState(), 5));

        context.register(BASALT_VENT, new SimpleBlockFeature(BlockStateProvider.of(EBlocks.BASALT_VENT)));

        context.register(WITHER_ROSE, new SimpleBlockFeature(BlockStateProvider.of(Blocks.WITHER_ROSE)));
        context.register(SOUL_CRYSTAL, new BlockPatchFeature(
                BlockStateProvider.of(EBlocks.SOULGLASS_CLUSTER),
                BlockPredicate.matchesTag(EBlockTags.NETHER_SOIL),
                BlockPredicate.replaceable(),
                2, 1, 3, 2,
                Direction.DOWN, 1
        ));
        context.register(SOUL_SPROUTS, new BlockPatchFeature(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(EBlocks.SHORT_SOUL_SPROUT.defaultBlockState(), 7)
                        .add(EBlocks.TALL_SOUL_SPROUT.defaultBlockState(), 2)
                        .build()),
                BlockPredicate.matchesTag(EBlockTags.NETHER_SOIL),
                BlockPredicate.replaceable(),
                3, 1, 6, 3,
                Direction.DOWN, 1
                ));
        context.register(SOULGLASS_PILLAR, new BlockColumnClusterFeature(
                List.of(BlockColumnFeature.layer(UniformInt.of(5, 7), BlockStateProvider.of(EBlocks.RAW_SOULGLASS.defaultBlockState()))),
                Holder.direct(BlockStateProvider.of(EBlocks.SOULGLASS_CLUSTER)), 0.65F,
                Direction.UP,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true,
                ConstantInt.of(2),
                UniformInt.of(3, 4),
                UniformInt.of(0, 2),
                ConstantInt.of(1)
        ));
        context.register(ORE_SOUL_SILVER, new OreFeature(new TagMatchTest(
                EBlockTags.SOUL_STONE_ORE_REPLACEABLES), EBlocks.SOUL_SILVER_ORE.defaultBlockState(), 7));
        context.register(ORE_SOUL_QUARTZ, new OreFeature(new TagMatchTest(
                EBlockTags.SOUL_STONE_ORE_REPLACEABLES), EBlocks.SOUL_QUARTZ_ORE.defaultBlockState(), 12));

        context.register(PEBBLE, new SimpleBlockFeature(new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(PebbleBlock.FACING, Direction.NORTH))
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(PebbleBlock.FACING, Direction.EAST))
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(PebbleBlock.FACING, Direction.SOUTH))
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(PebbleBlock.FACING, Direction.WEST))
                        .build()
        )));

        context.register(MEGA_ACACIA, new TreeFeature.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                new GiantTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(1), ConstantInt.of(4),
                        0.25F, 0.75F, 0.40F, 0.35F),
                new TwoLayersFeatureSize(4, 1, 5),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).build());

        context.register(SHORT_CACTUS, new BlockColumnFeature(
                List.of(BlockColumnFeature.layer(BiasedToBottomInt.of(1, 2), BlockStateProvider.of(Blocks.CACTUS)),
                        BlockColumnFeature.layer(new WeightedListInt(WeightedList.<IntProvider>builder()
                                        .add(ConstantInt.of(0), 3).add(ConstantInt.of(1), 2).build()),
                                BlockStateProvider.of(Blocks.CACTUS_FLOWER))),
                Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
        ));
        context.register(TALL_CACTUS, new BlockColumnFeature(
                List.of(BlockColumnFeature.layer(UniformInt.of(4, 6), BlockStateProvider.of(Blocks.CACTUS)),
                        BlockColumnFeature.layer(new WeightedListInt(WeightedList.<IntProvider>builder()
                                        .add(ConstantInt.of(0), 3).add(ConstantInt.of(1), 2).build()),
                                BlockStateProvider.of(Blocks.CACTUS_FLOWER))),
                Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
        ));

        context.register(SANDSTONE_ROCK, new LargeBlobFeature(Blocks.SANDSTONE.defaultBlockState(), BlockPredicate.solid(), UniformInt.of(3, 5)));
        context.register(RED_SANDSTONE_ROCK, new LargeBlobFeature(Blocks.RED_SANDSTONE.defaultBlockState(), BlockPredicate.solid(), UniformInt.of(3, 5)));
        context.register(PINK_SANDSTONE_ROCK, new LargeBlobFeature(EBlocks.PINK_SANDSTONE.defaultBlockState(), BlockPredicate.solid(), UniformInt.of(3, 5)));
        context.register(LUSH_SANDSTONE_ROCKS, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(SANDSTONE_ROCK)), 0.5F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(PINK_SANDSTONE_ROCK)), 0.5F)),
                PlacementUtils.inlinePlaced(features.getOrThrow(SANDSTONE_ROCK))));

        context.register(COARSE_DIRT_PATCH, new VegetationPatchFeature(
                blocks.getOrThrow(EBlockTags.SOIL), Holder.direct(BlockStateProvider.of(Blocks.COARSE_DIRT)),
                PlacementUtils.inlinePlaced(features.getOrThrow(VegetationFeatures.GRASS)), CaveSurface.FLOOR,
                ConstantInt.of(1), 0.4F, 5, 0.25F, UniformInt.of(2, 4), 0.75F));

        context.register(AZALEA_SHRUB_KEY, new TreeFeature.Builder(
                BlockStateProvider.of(EBlocks.AZALEA_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 4)
                        .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                        .build()),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 2),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).build());
        context.register(OAK_SHRUB, new TreeFeature.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).build());
        context.register(JUNGLE_SHRUB, new TreeFeature.Builder(
                BlockStateProvider.of(Blocks.JUNGLE_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.JUNGLE_LEAVES),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).build());

        context.register(CEDAR_TREE, new TreeFeature.Builder(
                BlockStateProvider.of(EBlocks.CEDAR_LOG),
                new StraightTrunkPlacer(6, 1, 1),
                BlockStateProvider.of(EBlocks.CEDAR_LEAVES),
                new PineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(3)),
                new TwoLayersFeatureSize(2, 0, 1),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).decorators(List.of(beehive)).build());
        context.register(CEDAR_TREE_LEAF_LITTER, new TreeFeature.Builder(
                BlockStateProvider.of(EBlocks.CEDAR_LOG),
                new StraightTrunkPlacer(6, 1, 1),
                BlockStateProvider.of(EBlocks.CEDAR_LEAVES),
                new PineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(3)),
                new TwoLayersFeatureSize(2, 0, 1),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).decorators(List.of(sparseLeafLitter, thickLeafLitter, beehive)).build());
        context.register(TALL_CEDAR_LEAF_LITTER, new TreeFeature.Builder(
                BlockStateProvider.of(EBlocks.CEDAR_LOG),
                new StraightTrunkPlacer(13, 2, 1),
                BlockStateProvider.of(EBlocks.CEDAR_LEAVES),
                new PineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(4)),
                new TwoLayersFeatureSize(7, 0, 2),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).decorators(List.of(sparseLeafLitter, thickLeafLitter, beehive)).build());
        context.register(FALLEN_CEDAR_TREE, createFallenTrees(EBlocks.CEDAR_LOG, 5, 8)
                .stumpDecorator(TrunkVineDecorator.INSTANCE).build());

        context.register(TREES_CEDAR_AND_OAK_LEAF_LITTER, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(CEDAR_TREE_LEAF_LITTER)), 0.2F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(TALL_CEDAR_LEAF_LITTER)), 0.035F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(FALLEN_CEDAR_TREE)), 0.008F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.OAK_BEES_0002_LEAF_LITTER), 0.18F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FANCY_OAK_BEES_0002_LEAF_LITTER), 0.025F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FALLEN_OAK_TREE), 0.005F)
        ), PlacementUtils.inlinePlaced(features.getOrThrow(CEDAR_TREE_LEAF_LITTER))));

        context.register(MAHOGANY_TREE, new TreeFeature.Builder(
                BlockStateProvider.of(EBlocks.MAHOGANY_LOG),
                new CherryTrunkPlacer(6, 1, 2,
                        UniformInt.of(1, 3), BiasedToBottomInt.of(2, 3), UniformInt.of(-4, -3), UniformInt.of(-1, 2)),
                BlockStateProvider.of(EBlocks.MAHOGANY_LEAVES),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(6, 0, 5),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).decorators(List.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.15F), beehive)).build());

        context.register(JUNGLE_TREES_KEY, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(MAHOGANY_TREE)), 0.35F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(CEDAR_TREE)), 0.1F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(FALLEN_CEDAR_TREE)), 0.005F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(JUNGLE_SHRUB)), 0.5F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.MEGA_JUNGLE_TREE_CHECKED), 0.33333334F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FALLEN_JUNGLE_TREE), 0.0125F)
        ), placedFeatures.getOrThrow(TreePlacements.JUNGLE_TREE_CHECKED)));

        context.register(SPARSE_JUNGLE_TREES_KEY, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(MAHOGANY_TREE)), 0.1F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(JUNGLE_SHRUB)), 0.5F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FALLEN_JUNGLE_TREE), 0.0125F)
        ), placedFeatures.getOrThrow(TreePlacements.JUNGLE_TREE_CHECKED)));

        context.register(BAMBOO_JUNGLE_TREES_KEY, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(MAHOGANY_TREE)), 0.05F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(JUNGLE_SHRUB)), 0.15F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.MEGA_JUNGLE_TREE_CHECKED), 0.7F)
        ), PlacementUtils.inlinePlaced(features.getOrThrow(VegetationFeatures.GRASS_JUNGLE),
                CountPlacement.of(32), OffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN, new Block[]{Blocks.PODZOL})))))));

        context.register(FLOWER_FOREST_TREES_KEY, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FALLEN_BIRCH_TREE), 0.0025F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.BIRCH_BEES_002), 0.18F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(GILDED_BIRCH)), 0.1F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FANCY_OAK_BEES_002), 0.12F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.CHERRY_BEES_005), 0.15F)
        ), placedFeatures.getOrThrow(TreePlacements.OAK_BEES_002)));

        context.register(SPARSE_FOREST_FLOWERS, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(VegetationFeatures.FLOWER_CHERRY)), 0.5F)
        ), PlacementUtils.inlinePlaced(features.getOrThrow(VegetationFeatures.WILDFLOWER))));

        context.register(GILDED_BIRCH_CLEAR, new TreeFeature.Builder(
                BlockStateProvider.of(Blocks.BIRCH_LOG),
                new StraightTrunkPlacer(5, 2, 0),
                BlockStateProvider.of(EBlocks.GILDED_BIRCH_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).build());
        context.register(GILDED_BIRCH, new TreeFeature.Builder(
                BlockStateProvider.of(Blocks.BIRCH_LOG),
                new StraightTrunkPlacer(5, 2, 0),
                BlockStateProvider.of(EBlocks.GILDED_BIRCH_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).decorators(List.of(beehive)).build());
        context.register(SUPER_GILDED_BIRCH, new TreeFeature.Builder(
                BlockStateProvider.of(Blocks.BIRCH_LOG),
                new StraightTrunkPlacer(5, 2, 6),
                BlockStateProvider.of(EBlocks.GILDED_BIRCH_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1),
                Holder.direct(BlockStateProvider.of(Blocks.AIR.defaultBlockState()))
        ).decorators(List.of(beehive)).build());

        context.register(BIRCH_TREES_KEY, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FALLEN_BIRCH_TREE), 0.0125F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(GILDED_BIRCH)), 0.45F)
        ), placedFeatures.getOrThrow(TreePlacements.BIRCH_BEES_0002_PLACED)));

        context.register(RANDOM_BIRCH, new RandomSelectorFeature(
                List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(GILDED_BIRCH_CLEAR)), 0.25F)),
                placedFeatures.getOrThrow(TreePlacements.BIRCH_CHECKED)));

        context.register(OLD_GROWTH_BIRCH_TREES_KEY, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FALLEN_SUPER_BIRCH_TREE), 0.00625F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.SUPER_BIRCH_BEES_0002), 0.15F),
                new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FALLEN_BIRCH_TREE), 0.0125F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(GILDED_BIRCH)), 0.30F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(SUPER_GILDED_BIRCH)), 0.15F)
        ), placedFeatures.getOrThrow(TreePlacements.BIRCH_BEES_0002_PLACED)));

        context.register(RUSTY_MOSS_VEGETATION, new SimpleBlockFeature(new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(EBlocks.RUSTY_MOSS_CARPET.defaultBlockState(), 25)
                        .add(EBlocks.SHORT_RUSTY_MOSS_SPOROPHYTE.defaultBlockState(), 50)
                        .add(EBlocks.TALL_RUSTY_MOSS_SPOROPHYTE.defaultBlockState(), 10)
                        .add(Blocks.RED_SHRUB.defaultBlockState(), 10)
        )));
        context.register(RUSTY_MOSS_PATCH_KEY, new VegetationPatchFeature(
                blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                Holder.direct(BlockStateProvider.of(EBlocks.RUSTY_MOSS_BLOCK)),
                PlacementUtils.inlinePlaced(features.getOrThrow(RUSTY_MOSS_VEGETATION)),
                CaveSurface.FLOOR, ConstantInt.of(1),
                0.0F, 5, 0.5F,
                UniformInt.of(4, 7), 0.3F
        ));
        context.register(RUSTY_MOSS_PATCH_BONEMEAL, new VegetationPatchFeature(
                blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                Holder.direct(BlockStateProvider.of(EBlocks.RUSTY_MOSS_BLOCK)),
                PlacementUtils.inlinePlaced(features.getOrThrow(RUSTY_MOSS_VEGETATION)),
                CaveSurface.FLOOR, ConstantInt.of(1),
                0.0F, 5, 0.6F,
                UniformInt.of(1, 2), 0.75F
        ));

        context.register(MOSSY_MUD_VEGETATION, new SimpleBlockFeature(new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(Blocks.MOSS_CARPET.defaultBlockState(), 18)
                        .add(EBlocks.SHORT_MOSS_SPOROPHYTE.defaultBlockState(), 40)
                        .add(EBlocks.TALL_MOSS_SPOROPHYTE.defaultBlockState(), 10)
        )));
        context.register(MOSSY_MUD_PATCH_KEY, new VegetationPatchFeature(
                blocks.getOrThrow(BlockTags.MUD),
                Holder.direct(BlockStateProvider.of(EBlocks.MOSSY_MUD)),
                PlacementUtils.inlinePlaced(features.getOrThrow(MOSSY_MUD_VEGETATION)),
                CaveSurface.FLOOR, ConstantInt.of(1),
                0.0F, 5, 0.35F,
                UniformInt.of(5, 9), 0.3F
        ));

        context.register(BEACHSTONE_ROCK_KEY, new BlockColumnClusterFeature(
                List.of(BlockColumnFeature.layer(UniformInt.of(2, 4), BlockStateProvider.of(EBlocks.BEACHSTONE.defaultBlockState()))),
                Holder.direct(BlockStateProvider.of(Blocks.AIR)), 0,
                Direction.UP,
                BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                true,
                UniformInt.of(2, 3),
                UniformInt.of(2, 3),
                UniformInt.of(0, 2),
                UniformInt.of(3, 4)
        ));

        context.register(ROCKWEED_BASALT_PATCH_KEY, new VegetationPatchFeature(
                blocks.getOrThrow(EBlockTags.NATURAL_STONE_BLOCKS),
                Holder.direct(BlockStateProvider.of(EBlocks.ROCKWEED_BASALT)),
                PlacementUtils.inlinePlaced(features.getOrThrow(VegetationFeatures.DRY_GRASS)),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0.4F,
                5,
                0.25F,
                UniformInt.of(2, 3),
                0.75F
        ));

        context.register(TIDEPOOL_KEY, new TidePoolFeature(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.SMOOTH_BASALT.defaultBlockState(), 1)
                        .add(EBlocks.ROCKWEED_BASALT.defaultBlockState(), 1)
                        .build()),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.BASALT.defaultBlockState(), 4)
                        .add(Blocks.SMOOTH_BASALT.defaultBlockState(), 1)
                        .add(EBlocks.ROCKWEED_BASALT.defaultBlockState(), 2)
                        .build()),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(PebbleBlock.FACING, Direction.NORTH), 8)
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(PebbleBlock.FACING, Direction.EAST), 8)
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(PebbleBlock.FACING, Direction.WEST), 8)
                        .add(EBlocks.PEBBLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(PebbleBlock.FACING, Direction.SOUTH), 8)
                        .add(Blocks.SEA_PICKLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(SeaPickleBlock.PICKLES, 1), 5)
                        .add(Blocks.SEA_PICKLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(SeaPickleBlock.PICKLES, 2), 5)
                        .add(Blocks.SEA_PICKLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(SeaPickleBlock.PICKLES, 3), 3)
                        .add(Blocks.SEA_PICKLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)
                                .setValue(SeaPickleBlock.PICKLES, 4), 1)
                        .build())
        ));

        context.register(PLAINS_CRAG, new CragFeature(
                UniformInt.of(11, 14),
                UniformInt.of(5, 11),
                Holder.direct(BlockStateProvider.of(Blocks.GRASS_BLOCK)),
                Holder.direct(BlockStateProvider.of(Blocks.DIRT)), 1,
                Holder.direct(BlockStateProvider.of(Blocks.STONE)),
                0.28F, 1, false
        ));

        context.register(SHALLOW_LUKEWARM_CRAG, new CragFeature(
                UniformInt.of(7, 9),
                UniformInt.of(3, 5),
                Holder.direct(BlockStateProvider.of(EBlocks.SEAGRASS_SAND)),
                Holder.direct(BlockStateProvider.of(Blocks.SAND)), 1,
                Holder.direct(BlockStateProvider.of(Blocks.SANDSTONE)),
                0.32F, 3, true
        ));
        context.register(DEEP_LUKEWARM_CRAG, new CragFeature(
                UniformInt.of(8, 11),
                UniformInt.of(7, 9),
                Holder.direct(BlockStateProvider.of(EBlocks.SEAGRASS_SAND)),
                Holder.direct(BlockStateProvider.of(Blocks.SAND)), 2,
                Holder.direct(BlockStateProvider.of(Blocks.SANDSTONE)),
                0.25F, 3, true
        ));

        context.register(SHORT_KELP, new BlockColumnFeature(List.of(
                BlockColumnFeature.layer(UniformInt.of(0, 3), BlockStateProvider.of(Blocks.KELP_PLANT.defaultBlockState())),
                BlockColumnFeature.layer(ConstantInt.of(1), BlockStateProvider.of(Blocks.KELP.defaultBlockState().setValue(KelpBlock.AGE, 25)))),
                Direction.UP, BlockPredicate.matchesFluids(Fluids.WATER), false
        ));
        context.register(MEDIUM_KELP, new BlockColumnFeature(List.of(
                BlockColumnFeature.layer(UniformInt.of(5, 9), BlockStateProvider.of(Blocks.KELP_PLANT.defaultBlockState())),
                BlockColumnFeature.layer(ConstantInt.of(1), BlockStateProvider.of(Blocks.KELP.defaultBlockState().setValue(KelpBlock.AGE, 25)))),
                Direction.UP, BlockPredicate.matchesFluids(Fluids.WATER), false
        ));
        context.register(TALL_KELP, new BlockColumnFeature(List.of(
                BlockColumnFeature.layer(UniformInt.of(13, 22), BlockStateProvider.of(Blocks.KELP_PLANT.defaultBlockState())),
                BlockColumnFeature.layer(ConstantInt.of(1), BlockStateProvider.of(Blocks.KELP.defaultBlockState().setValue(KelpBlock.AGE, 25)))),
                Direction.UP, BlockPredicate.matchesFluids(Fluids.WATER), false
        ));

        context.register(RANDOM_SHORT_KELP, new RandomSelectorFeature(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(SHORT_KELP)), 0.6F),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(features.getOrThrow(MEDIUM_KELP)), 0.4F)
        ), PlacementUtils.inlinePlaced(features.getOrThrow(SHORT_KELP))));

        context.register(CAVE_WEEDS_KEY, new BlockPatchFeature(
                BlockStateProvider.of(EBlocks.CAVE_WEED.defaultBlockState()),
                BlockPredicate.solid(),
                BlockPredicate.replaceable(),
                3, 2, 2, 2,
                Direction.DOWN, 2
        ));
        context.register(HANGING_ROOTS_CEILING, new BlockPatchFeature(
                BlockStateProvider.of(Blocks.HANGING_ROOTS.defaultBlockState()),
                BlockPredicate.solid(),
                BlockPredicate.replaceable(),
                3, 2, 2, 2,
                Direction.UP, 1
        ));

        context.register(CAVE_ROOTED_DIRT, new VegetationPatchFeature(
                blocks.getOrThrow(BlockTags.BASE_STONE_OVERWORLD),
                Holder.direct(new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.ROOTED_DIRT.defaultBlockState(), 2)
                        .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                        .build())),
                PlacementUtils.inlinePlaced(features.getOrThrow(HANGING_ROOTS_CEILING)),
                CaveSurface.CEILING,
                UniformInt.of(1, 2), 0.0F, 5, 0.25F, UniformInt.of(2, 4), 0.5F
        ));

        context.register(CATTAILS, new BlockPatchFeature(
                BlockStateProvider.of(EBlocks.CATTAILS.defaultBlockState()),
                BlockPredicate.matchesTag(BlockTags.SUPPORTS_VEGETATION),
                BlockPredicate.replaceable(),
                4, 2, 6, 5,
                Direction.DOWN, 1
        ));

        context.register(LIMESTONE_ROCK, new BlockColumnClusterFeature(
                List.of(BlockColumnFeature.layer(UniformInt.of(5, 7), BlockStateProvider.of(EBlocks.LIMESTONE.defaultBlockState()))),
                Holder.direct(BlockStateProvider.of(Blocks.AIR)), 0,
                Direction.UP,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true,
                ConstantInt.of(2),
                UniformInt.of(3, 4),
                UniformInt.of(0, 2),
                UniformInt.of(3, 4)
        ));
    }


    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE,
                Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    private static FallenTreeFeature.Builder createFallenTrees(final Block logBlock, final int minLength, final int maxLength) {
        return new FallenTreeFeature.Builder(BlockStateProvider.of(logBlock), UniformInt.of(minLength, maxLength))
                .logDecorator(new AttachedToLogsDecorator(0.1F,
                        Holder.direct(new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.RED_MUSHROOM.defaultBlockState(), 2)
                                .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1))),
                        List.of(Direction.UP)));
    }

    private static WeightedList.Builder<BlockState> flowerBedPatchBuilder(final Block flowerBedBlock) {
        return segmentedBlockPatchBuilder(flowerBedBlock, 1, 4, FlowerBedBlock.AMOUNT, FlowerBedBlock.FACING);
    }

    private static WeightedList.Builder<BlockState> segmentedBlockPatchBuilder(final Block block, final int minState, final int maxState, final IntegerProperty amountProperty, final EnumProperty<Direction> directionProperty) {
        WeightedList.Builder<BlockState> segmentedBlockBuild = WeightedList.builder();

        for(int amount = minState; amount <= maxState; ++amount) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                segmentedBlockBuild.add((BlockState)((BlockState)block.defaultBlockState().setValue(amountProperty, amount)).setValue(directionProperty, direction), 1);
            }
        }

        return segmentedBlockBuild;
    }
}
