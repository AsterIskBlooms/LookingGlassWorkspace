package team.lookingglass.elsewhere.datagen.tags;

import net.minecraft.references.BlockItemIds;
import team.lookingglass.elsewhere.registry.EBlockItemIds;
import team.lookingglass.elsewhere.registry.tags.EBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }



    @Override
    protected void addTags(HolderLookup.Provider registries) {

        builder(BlockTags.BASE_STONE_OVERWORLD);
        builder(BlockTags.BASE_STONE_NETHER).add(EBlockItemIds.SOUL_STONE);
        builder(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(EBlockItemIds.SHALE);

        builder(EBlockTags.NATURAL_STONE_BLOCKS).add(
                BlockItemIds.STONE, BlockItemIds.DEEPSLATE, BlockItemIds.GRANITE, BlockItemIds.DIORITE, BlockItemIds.ANDESITE,
                BlockItemIds.CALCITE, EBlockItemIds.SODALITE, BlockItemIds.TUFF, EBlockItemIds.SHALE, BlockItemIds.DRIPSTONE_BLOCK,
                BlockItemIds.SULFUR, EBlockItemIds.ORPIMENT, BlockItemIds.CINNABAR, BlockItemIds.PRISMARINE, BlockItemIds.MAGMA_BLOCK, BlockItemIds.BLACKSTONE,
                BlockItemIds.BASALT, BlockItemIds.SMOOTH_BASALT, BlockItemIds.SANDSTONE, BlockItemIds.RED_SANDSTONE, EBlockItemIds.PINK_SANDSTONE, EBlockItemIds.SOUL_STONE
                );
        builder(BlockTags.MOSS_REPLACEABLE).addTag(EBlockTags.NATURAL_STONE_BLOCKS).addTag(BlockTags.DIRT);

        builder(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(BlockItemIds.SMOOTH_BASALT, BlockItemIds.BLACKSTONE);
        builder(EBlockTags.CINNABAR_ORE_REPLACEABLES).add(BlockItemIds.CINNABAR);
        builder(EBlockTags.CALCITE_ORE_REPLACEABLES).add(BlockItemIds.CALCITE);
        builder(EBlockTags.SOUL_STONE_ORE_REPLACEABLES).add(EBlockItemIds.SOUL_STONE);
        builder(EBlockTags.AMETHYST_SPIRE_REPLACEABLE).add(BlockItemIds.AMETHYST_BLOCK).addTag(EBlockTags.NATURAL_STONE_BLOCKS);
        builder(BlockTags.SPELEOTHEMS).add(EBlockItemIds.AMETHYST_SPIRE);

        builder(BlockTags.DIRT).add(
                EBlockItemIds.ARID_DIRT, EBlockItemIds.ARID_GRASS_BLOCK,
                EBlockItemIds.RED_GRASS_BLOCK,
                EBlockItemIds.LATERITE,
                BlockItemIds.MUD, EBlockItemIds.MOSSY_MUD
        );
        builder(BlockTags.GRASS_BLOCKS).add(
                EBlockItemIds.ARID_GRASS_BLOCK, EBlockItemIds.RED_GRASS_BLOCK, EBlockItemIds.SEAGRASS_SAND
        );
        builder(BlockTags.MUD).add(BlockItemIds.MUD, EBlockItemIds.MOSSY_MUD);
        builder(BlockTags.SAND).add(EBlockItemIds.PINK_SAND, EBlockItemIds.SEAGRASS_SAND);
        builder(EBlockTags.SOIL).addTag(BlockTags.DIRT).addTag(BlockTags.GRASS_BLOCKS).addTag(BlockTags.SAND);
        builder(EBlockTags.NETHER_SOIL).addTag(EBlockTags.SOIL).add(
                BlockItemIds.NETHERRACK, BlockItemIds.CRIMSON_NYLIUM, BlockItemIds.WARPED_NYLIUM,
                BlockItemIds.SOUL_SAND, BlockItemIds.SOUL_SOIL
        );

        builder(BlockTags.SUPPORTS_DRY_VEGETATION).add(BlockItemIds.GRAVEL);
        builder(BlockTags.SUPPORTS_VEGETATION).addTag(EBlockTags.SOIL);
        builder(BlockTags.SUPPORTS_CACTUS).addTag(EBlockTags.SOIL);

        builder(BlockTags.ENABLES_BUBBLE_COLUMN_PUSH_UP).add(EBlockItemIds.BASALT_VENT);

        builder(BlockTags.REPLACEABLE).add(
                BlockItemIds.WILDFLOWERS, BlockItemIds.PINK_PETALS,
                EBlockItemIds.PEBBLE,
                EBlockItemIds.TALL_RED_GRASS,
                EBlockItemIds.BLUEBONNET,
                EBlockItemIds.RED_HIBISCUS, EBlockItemIds.ORANGE_HIBISCUS, EBlockItemIds.YELLOW_HIBISCUS, EBlockItemIds.BLUE_HIBISCUS,
                EBlockItemIds.PURPLE_HIBISCUS, EBlockItemIds.PINK_HIBISCUS, EBlockItemIds.WHITE_HIBISCUS,
                EBlockItemIds.MAGENTA_CONEFLOWER, EBlockItemIds.YELLOW_CONEFLOWER, EBlockItemIds.IRONWEED,
                EBlockItemIds.CATTAILS,
                EBlockItemIds.CAVE_WEED, EBlockItemIds.GLEAMSHROOM,
                EBlockItemIds.SHORT_MOSS_SPOROPHYTE, EBlockItemIds.TALL_MOSS_SPOROPHYTE,
                EBlockItemIds.SHORT_RUSTY_MOSS_SPOROPHYTE, EBlockItemIds.TALL_RUSTY_MOSS_SPOROPHYTE,
                EBlockItemIds.SHORT_PALE_MOSS_SPOROPHYTE, EBlockItemIds.TALL_PALE_MOSS_SPOROPHYTE
        );
        builder(BlockTags.MOSS_BLOCKS).add(
                EBlockItemIds.RUSTY_MOSS_BLOCK, EBlockItemIds.MOSSY_MUD
        );

        builder(BlockTags.LEAVES).add(
                EBlockItemIds.CEDAR_LEAVES, EBlockItemIds.MAHOGANY_LEAVES, EBlockItemIds.GILDED_BIRCH_LEAVES
        );

        builder(BlockTags.CHAINS).add(EBlockItemIds.SILVER_CHAIN, EBlockItemIds.GOLD_CHAIN);
        builder(BlockTags.LANTERNS).add(EBlockItemIds.REDSTONE_LANTERN);
        builder(BlockTags.BARS).add(EBlockItemIds.TIN_BARS, EBlockItemIds.SILVER_BARS, EBlockItemIds.WROUGHT_IRON_BARS);


        builder(BlockTags.FLOWERS)
                .add(
                        EBlockItemIds.BLUEBONNET,
                        EBlockItemIds.RED_HIBISCUS, EBlockItemIds.ORANGE_HIBISCUS, EBlockItemIds.YELLOW_HIBISCUS, EBlockItemIds.BLUE_HIBISCUS,
                        EBlockItemIds.PURPLE_HIBISCUS, EBlockItemIds.PINK_HIBISCUS, EBlockItemIds.WHITE_HIBISCUS,
                        EBlockItemIds.MAGENTA_CONEFLOWER, EBlockItemIds.YELLOW_CONEFLOWER, EBlockItemIds.ROSE,
                        EBlockItemIds.IRONWEED
                );
        builder(BlockTags.SMALL_FLOWERS)
                .add(
                        EBlockItemIds.BLUEBONNET,
                        EBlockItemIds.RED_HIBISCUS, EBlockItemIds.ORANGE_HIBISCUS, EBlockItemIds.YELLOW_HIBISCUS, EBlockItemIds.BLUE_HIBISCUS,
                        EBlockItemIds.PURPLE_HIBISCUS, EBlockItemIds.PINK_HIBISCUS, EBlockItemIds.WHITE_HIBISCUS,
                        EBlockItemIds.MAGENTA_CONEFLOWER, EBlockItemIds.YELLOW_CONEFLOWER, EBlockItemIds.ROSE
                );

        // Tool Mineables
        builder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(
                        EBlockItemIds.ARID_DIRT, EBlockItemIds.ARID_GRASS_BLOCK,
                        EBlockItemIds.RED_GRASS_BLOCK,
                        EBlockItemIds.LATERITE,
                        EBlockItemIds.PINK_SAND,
                        EBlockItemIds.SEAGRASS_SAND
                );

        builder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        BlockItemIds.SMITHING_TABLE,

                        EBlockItemIds.LATERITE,

                        EBlockItemIds.QUARTZ_BRICK_STAIRS, EBlockItemIds.QUARTZ_BRICK_SLAB,

                        EBlockItemIds.AMETHYST_STAIRS, EBlockItemIds.AMETHYST_SLAB,
                        EBlockItemIds.AMETHYST_BRICKS, EBlockItemIds.AMETHYST_BRICK_STAIRS, EBlockItemIds.AMETHYST_BRICK_SLAB,
                        EBlockItemIds.AMETHYST_PILLAR, EBlockItemIds.CHISELED_AMETHYST, EBlockItemIds.AMETHYST_SPIRE,

                        EBlockItemIds.OBSIDIAN_STAIRS, EBlockItemIds.OBSIDIAN_SLAB,
                        EBlockItemIds.OBSIDIAN_BRICKS, EBlockItemIds.OBSIDIAN_BRICK_STAIRS, EBlockItemIds.OBSIDIAN_BRICK_SLAB,
                        EBlockItemIds.OBSIDIAN_PILLAR, EBlockItemIds.CHISELED_OBSIDIAN,

                        EBlockItemIds.STONE_WALL,
                        EBlockItemIds.SMOOTH_STONE_STAIRS, EBlockItemIds.SMOOTH_STONE_WALL,
                        EBlockItemIds.POLISHED_STONE, EBlockItemIds.POLISHED_STONE_STAIRS, EBlockItemIds.POLISHED_STONE_SLAB, EBlockItemIds.POLISHED_STONE_WALL,
                        EBlockItemIds.STONE_TILES, EBlockItemIds.STONE_TILE_STAIRS, EBlockItemIds.STONE_TILE_SLAB, EBlockItemIds.STONE_TILE_WALL,
                        EBlockItemIds.MOSSY_STONE_TILES, EBlockItemIds.MOSSY_STONE_TILE_STAIRS, EBlockItemIds.MOSSY_STONE_TILE_SLAB, EBlockItemIds.MOSSY_STONE_TILE_WALL,
                        EBlockItemIds.STONE_PILLAR, EBlockItemIds.CHISELED_STONE,

                        EBlockItemIds.DEEPSLATE_STAIRS, EBlockItemIds.DEEPSLATE_SLAB, EBlockItemIds.DEEPSLATE_WALL,
                        EBlockItemIds.DEEPSLATE_PILLAR, EBlockItemIds.CHISELED_DEEPSLATE_BRICKS,

                        EBlockItemIds.TUFF_PILLAR,
                        EBlockItemIds.BLACKSTONE_PILLAR,

                        EBlockItemIds.POLISHED_ANDESITE_WALL,
                        EBlockItemIds.ANDESITE_BRICKS, EBlockItemIds.ANDESITE_BRICK_STAIRS, EBlockItemIds.ANDESITE_BRICK_SLAB, EBlockItemIds.ANDESITE_BRICK_WALL,
                        EBlockItemIds.ANDESITE_PILLAR, EBlockItemIds.CHISELED_ANDESITE,

                        EBlockItemIds.POLISHED_GRANITE_WALL,
                        EBlockItemIds.GRANITE_BRICKS, EBlockItemIds.GRANITE_BRICK_STAIRS, EBlockItemIds.GRANITE_BRICK_SLAB, EBlockItemIds.GRANITE_BRICK_WALL,

                        EBlockItemIds.POLISHED_DIORITE_WALL,
                        EBlockItemIds.DIORITE_BRICKS, EBlockItemIds.DIORITE_BRICK_STAIRS, EBlockItemIds.DIORITE_BRICK_SLAB, EBlockItemIds.DIORITE_BRICK_WALL,

                        EBlockItemIds.BEJEWELED_CALCITE,
                        EBlockItemIds.CALCITE_STAIRS, EBlockItemIds.CALCITE_SLAB, EBlockItemIds.CALCITE_WALL,
                        EBlockItemIds.POLISHED_CALCITE, EBlockItemIds.POLISHED_CALCITE_STAIRS, EBlockItemIds.POLISHED_CALCITE_SLAB, EBlockItemIds.POLISHED_CALCITE_WALL,
                        EBlockItemIds.CALCITE_BRICKS, EBlockItemIds.CALCITE_BRICK_STAIRS, EBlockItemIds.CALCITE_BRICK_SLAB, EBlockItemIds.CALCITE_BRICK_WALL,
                        EBlockItemIds.CALCITE_PILLAR, EBlockItemIds.CHISELED_CALCITE, EBlockItemIds.CHISELED_CALCITE_BRICKS,

                        EBlockItemIds.DRIPSTONE_STAIRS, EBlockItemIds.DRIPSTONE_SLAB, EBlockItemIds.DRIPSTONE_WALL,
                        EBlockItemIds.POLISHED_DRIPSTONE, EBlockItemIds.POLISHED_DRIPSTONE_STAIRS, EBlockItemIds.POLISHED_DRIPSTONE_SLAB, EBlockItemIds.POLISHED_DRIPSTONE_WALL,
                        EBlockItemIds.DRIPSTONE_TILES, EBlockItemIds.DRIPSTONE_TILE_STAIRS, EBlockItemIds.DRIPSTONE_TILE_SLAB, EBlockItemIds.DRIPSTONE_TILE_WALL,
                        EBlockItemIds.DRIPSTONE_PILLAR, EBlockItemIds.CHISELED_DRIPSTONE_TILES,

                        EBlockItemIds.POLISHED_SANDSTONE, EBlockItemIds.POLISHED_SANDSTONE_STAIRS, EBlockItemIds.POLISHED_SANDSTONE_SLAB, EBlockItemIds.POLISHED_SANDSTONE_WALL,
                        EBlockItemIds.SMOOTH_SANDSTONE_WALL, EBlockItemIds.CUT_SANDSTONE_STAIRS, EBlockItemIds.CUT_SANDSTONE_WALL,

                        EBlockItemIds.POLISHED_RED_SANDSTONE, EBlockItemIds.POLISHED_RED_SANDSTONE_STAIRS, EBlockItemIds.POLISHED_RED_SANDSTONE_SLAB, EBlockItemIds.POLISHED_RED_SANDSTONE_WALL,
                        EBlockItemIds.SMOOTH_RED_SANDSTONE_WALL, EBlockItemIds.CUT_RED_SANDSTONE_STAIRS, EBlockItemIds.CUT_RED_SANDSTONE_WALL,

                        EBlockItemIds.POLISHED_PRISMARINE, EBlockItemIds.POLISHED_PRISMARINE_STAIRS, EBlockItemIds.POLISHED_PRISMARINE_SLAB, EBlockItemIds.POLISHED_PRISMARINE_WALL,
                        EBlockItemIds.PRISMARINE_BRICK_WALL, EBlockItemIds.PRISMARINE_PILLAR, EBlockItemIds.CHISELED_PRISMARINE,

                        EBlockItemIds.DARK_PRISMARINE_WALL,
                        EBlockItemIds.POLISHED_DARK_PRISMARINE, EBlockItemIds.POLISHED_DARK_PRISMARINE_STAIRS, EBlockItemIds.POLISHED_DARK_PRISMARINE_SLAB, EBlockItemIds.POLISHED_DARK_PRISMARINE_WALL,
                        EBlockItemIds.DARK_PRISMARINE_SCALES, EBlockItemIds.DARK_PRISMARINE_SCALE_STAIRS, EBlockItemIds.DARK_PRISMARINE_SCALE_SLAB, EBlockItemIds.DARK_PRISMARINE_SCALE_WALL,
                        EBlockItemIds.CHISELED_DARK_PRISMARINE,

                        EBlockItemIds.BASALT_SLAB, EBlockItemIds.POLISHED_BASALT_SLAB, EBlockItemIds.BASALT_VENT,
                        EBlockItemIds.SMOOTH_BASALT_STAIRS, EBlockItemIds.SMOOTH_BASALT_SLAB, EBlockItemIds.SMOOTH_BASALT_WALL, EBlockItemIds.ROCKWEED_BASALT,
                        EBlockItemIds.POLISHED_SMOOTH_BASALT, EBlockItemIds.POLISHED_SMOOTH_BASALT_STAIRS, EBlockItemIds.POLISHED_SMOOTH_BASALT_SLAB, EBlockItemIds.POLISHED_SMOOTH_BASALT_WALL,
                        EBlockItemIds.SMOOTH_BASALT_BRICKS, EBlockItemIds.SMOOTH_BASALT_BRICK_STAIRS, EBlockItemIds.SMOOTH_BASALT_BRICK_SLAB, EBlockItemIds.SMOOTH_BASALT_BRICK_WALL,
                        EBlockItemIds.SMOOTH_BASALT_TILES, EBlockItemIds.SMOOTH_BASALT_TILE_STAIRS, EBlockItemIds.SMOOTH_BASALT_TILE_SLAB, EBlockItemIds.SMOOTH_BASALT_TILE_WALL,

                        EBlockItemIds.NETHERRACK_STAIRS, EBlockItemIds.NETHERRACK_SLAB, EBlockItemIds.NETHERRACK_WALL,
                        EBlockItemIds.POLISHED_NETHERRACK, EBlockItemIds.POLISHED_NETHERRACK_STAIRS, EBlockItemIds.POLISHED_NETHERRACK_SLAB, EBlockItemIds.POLISHED_NETHERRACK_WALL,
                        EBlockItemIds.NETHERRACK_BRICKS, EBlockItemIds.NETHERRACK_BRICK_STAIRS, EBlockItemIds.NETHERRACK_BRICK_SLAB, EBlockItemIds.NETHERRACK_BRICK_WALL,
                        EBlockItemIds.NETHERRACK_PILLAR, EBlockItemIds.CHISELED_NETHERRACK, EBlockItemIds.CHISELED_NETHERRACK_BRICKS,

                        EBlockItemIds.END_STONE_STAIRS, EBlockItemIds.END_STONE_SLAB, EBlockItemIds.END_STONE_WALL,
                        EBlockItemIds.POLISHED_END_STONE, EBlockItemIds.POLISHED_END_STONE_STAIRS, EBlockItemIds.POLISHED_END_STONE_SLAB, EBlockItemIds.POLISHED_END_STONE_WALL,
                        EBlockItemIds.END_STONE_PILLAR, EBlockItemIds.CHISELED_END_STONE,

                        EBlockItemIds.SODALITE, EBlockItemIds.SODALITE_STAIRS, EBlockItemIds.SODALITE_SLAB, EBlockItemIds.SODALITE_WALL,
                        EBlockItemIds.POLISHED_SODALITE, EBlockItemIds.POLISHED_SODALITE_STAIRS, EBlockItemIds.POLISHED_SODALITE_SLAB, EBlockItemIds.POLISHED_SODALITE_WALL,
                        EBlockItemIds.SODALITE_BRICKS, EBlockItemIds.SODALITE_BRICK_STAIRS, EBlockItemIds.SODALITE_BRICK_SLAB, EBlockItemIds.SODALITE_BRICK_WALL,
                        EBlockItemIds.SODALITE_PILLAR, EBlockItemIds.CHISELED_SODALITE, EBlockItemIds.CHISELED_SODALITE_BRICKS,

                        EBlockItemIds.SHALE, EBlockItemIds.SHALE_STAIRS, EBlockItemIds.SHALE_SLAB, EBlockItemIds.SHALE_WALL,
                        EBlockItemIds.POLISHED_SHALE, EBlockItemIds.POLISHED_SHALE_STAIRS, EBlockItemIds.POLISHED_SHALE_SLAB, EBlockItemIds.POLISHED_SHALE_WALL,
                        EBlockItemIds.SHALE_BRICKS, EBlockItemIds.SHALE_BRICK_STAIRS, EBlockItemIds.SHALE_BRICK_SLAB, EBlockItemIds.SHALE_BRICK_WALL,
                        EBlockItemIds.SHALE_PILLAR, EBlockItemIds.CHISELED_SHALE, EBlockItemIds.CHISELED_SHALE_BRICKS,

                        EBlockItemIds.CINNABAR_COAL_ORE, EBlockItemIds.CINNABAR_COPPER_ORE, EBlockItemIds.CINNABAR_IRON_ORE, EBlockItemIds.CINNABAR_GOLD_ORE, EBlockItemIds.CINNABAR_REDSTONE_ORE, EBlockItemIds.CINNABAR_DIAMOND_ORE, EBlockItemIds.CINNABAR_LAPIS_ORE, EBlockItemIds.CINNABAR_EMERALD_ORE,

                        EBlockItemIds.NETHERRACK_STAIRS, EBlockItemIds.NETHERRACK_SLAB, EBlockItemIds.NETHERRACK_WALL,
                        EBlockItemIds.POLISHED_NETHERRACK, EBlockItemIds.POLISHED_NETHERRACK_STAIRS, EBlockItemIds.POLISHED_NETHERRACK_SLAB, EBlockItemIds.POLISHED_NETHERRACK_WALL,
                        EBlockItemIds.NETHERRACK_BRICKS, EBlockItemIds.NETHERRACK_BRICK_STAIRS, EBlockItemIds.NETHERRACK_BRICK_SLAB, EBlockItemIds.NETHERRACK_BRICK_WALL,
                        EBlockItemIds.NETHERRACK_PILLAR, EBlockItemIds.CHISELED_NETHERRACK, EBlockItemIds.CHISELED_NETHERRACK_BRICKS,

                        EBlockItemIds.TIN_ORE, EBlockItemIds.DEEPSLATE_TIN_ORE, EBlockItemIds.CINNABAR_TIN_ORE,
                        EBlockItemIds.TIN_BLOCK, EBlockItemIds.RAW_TIN_BLOCK,
                        EBlockItemIds.CUT_TIN, EBlockItemIds.CUT_TIN_STAIRS, EBlockItemIds.CUT_TIN_SLAB,
                        EBlockItemIds.CHISELED_TIN, EBlockItemIds.TIN_BARS,

                        EBlockItemIds.BRONZE_BLOCK,

                        EBlockItemIds.SILVER_ORE, EBlockItemIds.DEEPSLATE_SILVER_ORE, EBlockItemIds.CINNABAR_SILVER_ORE,
                        EBlockItemIds.SILVER_BLOCK, EBlockItemIds.RAW_SILVER_BLOCK,
                        EBlockItemIds.POLISHED_SILVER, EBlockItemIds.POLISHED_SILVER_STAIRS, EBlockItemIds.POLISHED_SILVER_SLAB,
                        EBlockItemIds.CUT_SILVER, EBlockItemIds.CUT_SILVER_STAIRS, EBlockItemIds.CUT_SILVER_SLAB,
                        EBlockItemIds.SILVER_PILLAR, EBlockItemIds.SILVER_BARS, EBlockItemIds.SILVER_CHAIN,

                        EBlockItemIds.GOLD_CHAIN,

                        EBlockItemIds.WROUGHT_IRON_BLOCK, EBlockItemIds.WROUGHT_IRON_STAIRS, EBlockItemIds.WROUGHT_IRON_SLAB,
                        EBlockItemIds.CUT_WROUGHT_IRON, EBlockItemIds.CUT_WROUGHT_IRON_STAIRS, EBlockItemIds.CUT_WROUGHT_IRON_SLAB,
                        EBlockItemIds.WROUGHT_IRON_GRATE, EBlockItemIds.WROUGHT_IRON_BARS,

                        EBlockItemIds.SOULSTEEL_BLOCK, EBlockItemIds.ELECTRUM_BLOCK,

                        EBlockItemIds.URANIUM_BLOCK,

                        EBlockItemIds.SULFUR_PILLAR,
                        EBlockItemIds.CINNABAR_PILLAR,

                        EBlockItemIds.ORPIMENT, EBlockItemIds.ORPIMENT_STAIRS, EBlockItemIds.ORPIMENT_SLAB, EBlockItemIds.ORPIMENT_WALL,
                        EBlockItemIds.POLISHED_ORPIMENT, EBlockItemIds.POLISHED_ORPIMENT_STAIRS, EBlockItemIds.POLISHED_ORPIMENT_SLAB, EBlockItemIds.POLISHED_ORPIMENT_WALL,
                        EBlockItemIds.ORPIMENT_BRICKS, EBlockItemIds.ORPIMENT_BRICK_STAIRS, EBlockItemIds.ORPIMENT_BRICK_SLAB, EBlockItemIds.ORPIMENT_BRICK_WALL,
                        EBlockItemIds.ORPIMENT_PILLAR, EBlockItemIds.CHISELED_ORPIMENT,

                        EBlockItemIds.SOUL_STONE, EBlockItemIds.SOUL_STONE_STAIRS, EBlockItemIds.SOUL_STONE_SLAB, EBlockItemIds.SOUL_STONE_WALL,
                        EBlockItemIds.SMOOTH_SOUL_STONE, EBlockItemIds.SMOOTH_SOUL_STONE_STAIRS, EBlockItemIds.SMOOTH_SOUL_STONE_SLAB, EBlockItemIds.SMOOTH_SOUL_STONE_WALL,
                        EBlockItemIds.CUT_SOUL_STONE, EBlockItemIds.CUT_SOUL_STONE_STAIRS, EBlockItemIds.CUT_SOUL_STONE_SLAB, EBlockItemIds.CUT_SOUL_STONE_WALL,
                        EBlockItemIds.POLISHED_SOUL_STONE, EBlockItemIds.POLISHED_SOUL_STONE_STAIRS, EBlockItemIds.POLISHED_SOUL_STONE_SLAB, EBlockItemIds.POLISHED_SOUL_STONE_WALL,
                        EBlockItemIds.CHISELED_SOUL_STONE,
                        EBlockItemIds.SOUL_SILVER_ORE, EBlockItemIds.SOUL_QUARTZ_ORE,

                        EBlockItemIds.PINK_SANDSTONE, EBlockItemIds.PINK_SANDSTONE_STAIRS, EBlockItemIds.PINK_SANDSTONE_SLAB, EBlockItemIds.PINK_SANDSTONE_WALL,
                        EBlockItemIds.SMOOTH_PINK_SANDSTONE, EBlockItemIds.SMOOTH_PINK_SANDSTONE_STAIRS, EBlockItemIds.SMOOTH_PINK_SANDSTONE_SLAB, EBlockItemIds.SMOOTH_PINK_SANDSTONE_WALL,
                        EBlockItemIds.CUT_PINK_SANDSTONE, EBlockItemIds.CUT_PINK_SANDSTONE_STAIRS, EBlockItemIds.CUT_PINK_SANDSTONE_SLAB, EBlockItemIds.CUT_PINK_SANDSTONE_WALL,
                        EBlockItemIds.POLISHED_PINK_SANDSTONE, EBlockItemIds.POLISHED_PINK_SANDSTONE_STAIRS, EBlockItemIds.POLISHED_PINK_SANDSTONE_SLAB, EBlockItemIds.POLISHED_PINK_SANDSTONE_WALL,
                        EBlockItemIds.CHISELED_PINK_SANDSTONE,

                        EBlockItemIds.BEACHSTONE, EBlockItemIds.BEACHSTONE_STAIRS, EBlockItemIds.BEACHSTONE_SLAB, EBlockItemIds.BEACHSTONE_WALL,
                        EBlockItemIds.POLISHED_BEACHSTONE, EBlockItemIds.POLISHED_BEACHSTONE_STAIRS, EBlockItemIds.POLISHED_BEACHSTONE_SLAB, EBlockItemIds.POLISHED_BEACHSTONE_WALL,
                        EBlockItemIds.BEACHSTONE_BRICKS, EBlockItemIds.BEACHSTONE_BRICK_STAIRS, EBlockItemIds.BEACHSTONE_BRICK_SLAB, EBlockItemIds.BEACHSTONE_BRICK_WALL,
                        EBlockItemIds.CHISELED_BEACHSTONE,

                        EBlockItemIds.LIMESTONE, EBlockItemIds.LIMESTONE_STAIRS, EBlockItemIds.LIMESTONE_SLAB, EBlockItemIds.LIMESTONE_WALL,
                        EBlockItemIds.CUT_LIMESTONE, EBlockItemIds.CUT_LIMESTONE_STAIRS, EBlockItemIds.CUT_LIMESTONE_SLAB, EBlockItemIds.CUT_LIMESTONE_WALL,

                        EBlockItemIds.PACKED_MUD_STAIRS, EBlockItemIds.PACKED_MUD_SLAB, EBlockItemIds.PACKED_MUD_WALL,
                        EBlockItemIds.CHISELED_MUD_BRICKS, EBlockItemIds.MUD_BRICK_PILLAR,

                        EBlockItemIds.CHISELED_PURPUR, EBlockItemIds.LAPIS_BRICKS,

                        EBlockItemIds.SOULGLASS_CLUSTER, EBlockItemIds.RAW_SOULGLASS, EBlockItemIds.SOULGLASS
                )
                .addTag(EBlockTags.TERRACOTTA_STAIRS).addTag(EBlockTags.TERRACOTTA_SLABS)
                .addTag(EBlockTags.TERRACOTTA_BRICKS).addTag(EBlockTags.TERRACOTTA_BRICK_STAIRS).addTag(EBlockTags.TERRACOTTA_BRICK_SLABS)
                .addTag(EBlockTags.TERRACOTTA_BRICK_TILES).addTag(EBlockTags.TERRACOTTA_BRICK_TILE_STAIRS).addTag(EBlockTags.TERRACOTTA_BRICK_TILE_SLABS)
        ;

        // Tool Tiers
        builder(BlockTags.NEEDS_STONE_TOOL)
                .removeAll(List.of(
                        BlockItemIds.IRON_ORE.block(), BlockItemIds.DEEPSLATE_IRON_ORE.block(), BlockItemIds.IRON_BLOCK.block(),
                        BlockItemIds.GOLD_ORE.block(), BlockItemIds.DEEPSLATE_GOLD_ORE.block(), BlockItemIds.GOLD_BLOCK.block(), BlockItemIds.RAW_GOLD_BLOCK.block(),
                        BlockItemIds.REDSTONE_ORE.block(), BlockItemIds.DEEPSLATE_REDSTONE_ORE.block(), BlockItemIds.REDSTONE_BLOCK.block(),
                        BlockItemIds.LAPIS_ORE.block(), BlockItemIds.DEEPSLATE_LAPIS_ORE.block(), BlockItemIds.LAPIS_BLOCK.block()
                ));
        builder(EBlockTags.NEEDS_COPPER_TOOL)
                .add(
                        BlockItemIds.IRON_ORE, BlockItemIds.DEEPSLATE_IRON_ORE, EBlockItemIds.CINNABAR_IRON_ORE,
                        BlockItemIds.IRON_BLOCK, BlockItemIds.RAW_IRON_BLOCK,

                        BlockItemIds.REDSTONE_ORE, EBlockItemIds.CINNABAR_REDSTONE_ORE, BlockItemIds.DEEPSLATE_REDSTONE_ORE, BlockItemIds.REDSTONE_BLOCK,

                        BlockItemIds.LAPIS_ORE, EBlockItemIds.CINNABAR_LAPIS_ORE, BlockItemIds.DEEPSLATE_LAPIS_ORE,
                        BlockItemIds.LAPIS_BLOCK, EBlockItemIds.LAPIS_BRICKS, EBlockItemIds.LAPIS_BRICK_STAIRS, EBlockItemIds.LAPIS_BRICK_SLAB,

                        BlockItemIds.DEEPSLATE_COAL_ORE, BlockItemIds.DEEPSLATE_COPPER_ORE,

                        BlockItemIds.DEEPSLATE, EBlockItemIds.DEEPSLATE_STAIRS, EBlockItemIds.DEEPSLATE_SLAB, EBlockItemIds.DEEPSLATE_WALL,
                        BlockItemIds.COBBLED_DEEPSLATE, BlockItemIds.COBBLED_DEEPSLATE_STAIRS, BlockItemIds.COBBLED_DEEPSLATE_SLAB, BlockItemIds.COBBLED_DEEPSLATE_WALL,
                        BlockItemIds.POLISHED_DEEPSLATE, BlockItemIds.POLISHED_DEEPSLATE_STAIRS, BlockItemIds.POLISHED_DEEPSLATE_SLAB, BlockItemIds.POLISHED_DEEPSLATE_WALL,
                        BlockItemIds.DEEPSLATE_BRICKS, BlockItemIds.CRACKED_DEEPSLATE_BRICKS, BlockItemIds.DEEPSLATE_BRICK_STAIRS, BlockItemIds.DEEPSLATE_BRICK_SLAB, BlockItemIds.DEEPSLATE_BRICK_WALL,
                        BlockItemIds.DEEPSLATE_TILES, BlockItemIds.CRACKED_DEEPSLATE_TILES, BlockItemIds.DEEPSLATE_TILE_STAIRS, BlockItemIds.DEEPSLATE_TILE_SLAB, BlockItemIds.DEEPSLATE_TILE_WALL,
                        EBlockItemIds.DEEPSLATE_PILLAR, BlockItemIds.CHISELED_DEEPSLATE, EBlockItemIds.CHISELED_DEEPSLATE_BRICKS
                )
        ;
        builder(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        EBlockItemIds.TIN_ORE, EBlockItemIds.CINNABAR_TIN_ORE, EBlockItemIds.DEEPSLATE_TIN_ORE,
                        EBlockItemIds.TIN_BLOCK, EBlockItemIds.RAW_TIN_BLOCK,

                        EBlockItemIds.BRONZE_BLOCK,

                        BlockItemIds.GOLD_ORE, BlockItemIds.DEEPSLATE_GOLD_ORE, EBlockItemIds.CINNABAR_GOLD_ORE, BlockItemIds.NETHER_GOLD_ORE,
                        BlockItemIds.GOLD_BLOCK, BlockItemIds.RAW_GOLD_BLOCK,

                        EBlockItemIds.SILVER_ORE, EBlockItemIds.DEEPSLATE_SILVER_ORE, EBlockItemIds.CINNABAR_SILVER_ORE,
                        EBlockItemIds.SILVER_BLOCK, EBlockItemIds.RAW_SILVER_BLOCK,

                        BlockItemIds.EMERALD_ORE, BlockItemIds.DEEPSLATE_EMERALD_ORE, EBlockItemIds.CINNABAR_EMERALD_ORE, BlockItemIds.EMERALD_BLOCK,

                        EBlockItemIds.SOULSTEEL_BLOCK, EBlockItemIds.ELECTRUM_BLOCK,

                        EBlockItemIds.URANIUM_BLOCK,

                        BlockItemIds.OBSIDIAN, EBlockItemIds.OBSIDIAN_STAIRS, EBlockItemIds.OBSIDIAN_SLAB,
                        EBlockItemIds.OBSIDIAN_BRICKS, EBlockItemIds.OBSIDIAN_BRICK_STAIRS, EBlockItemIds.OBSIDIAN_BRICK_SLAB,
                        EBlockItemIds.OBSIDIAN_PILLAR, EBlockItemIds.CHISELED_OBSIDIAN,
                        BlockItemIds.CRYING_OBSIDIAN, BlockItemIds.RESPAWN_ANCHOR
                )
                .remove(BlockItemIds.REDSTONE_ORE.block())
        ;
        builder(BlockTags.NEEDS_DIAMOND_TOOL)
                .removeAll(List.of(
                        BlockItemIds.DIAMOND_ORE.block(),
                        BlockItemIds.DEEPSLATE_DIAMOND_ORE.block(), EBlockItemIds.CINNABAR_DIAMOND_ORE.block(), BlockItemIds.DIAMOND_BLOCK.block(),
                        BlockItemIds.OBSIDIAN.block(), BlockItemIds.CRYING_OBSIDIAN.block(), BlockItemIds.RESPAWN_ANCHOR.block()
                ));

        // Incorrect Tags
        builder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(EBlockTags.NEEDS_COPPER_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        builder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(EBlockTags.NEEDS_COPPER_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        builder(BlockTags.INCORRECT_FOR_COPPER_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        builder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        builder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .removeTag(BlockTags.NEEDS_IRON_TOOL)
        ;
        builder(EBlockTags.INCORRECT_FOR_BRONZE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        builder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
        ;
        builder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
        ;

        //Logs
        builder(EBlockTags.AZALEA_LOGS).add(
                EBlockItemIds.AZALEA_LOG, EBlockItemIds.STRIPPED_AZALEA_LOG,
                EBlockItemIds.AZALEA_WOOD, EBlockItemIds.STRIPPED_AZALEA_WOOD
        );
        builder(EBlockTags.CEDAR_LOGS).add(
                EBlockItemIds.CEDAR_LOG, EBlockItemIds.STRIPPED_CEDAR_LOG,
                EBlockItemIds.CEDAR_WOOD, EBlockItemIds.STRIPPED_CEDAR_WOOD
        );
        builder(EBlockTags.MAHOGANY_LOGS).add(
                EBlockItemIds.MAHOGANY_LOG, EBlockItemIds.STRIPPED_MAHOGANY_LOG,
                EBlockItemIds.MAHOGANY_WOOD, EBlockItemIds.STRIPPED_MAHOGANY_WOOD
        );
        builder(EBlockTags.BAMBOO_STEMS).add(EBlockItemIds.BAMBOO_STEM, EBlockItemIds.STRIPPED_BAMBOO_STEM);

        builder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .addTag(EBlockTags.AZALEA_LOGS)
                .addTag(EBlockTags.CEDAR_LOGS)
                .addTag(EBlockTags.MAHOGANY_LOGS)
                .addTag(EBlockTags.BAMBOO_STEMS)
        ;
        builder(BlockTags.LOGS)
                .addTag(EBlockTags.AZALEA_LOGS)
                .addTag(EBlockTags.CEDAR_LOGS)
                .addTag(EBlockTags.MAHOGANY_LOGS)
                .addTag(EBlockTags.BAMBOO_STEMS)
        ;

        // Planks
        builder(BlockTags.PLANKS).add(
                EBlockItemIds.AZALEA_PLANKS,
                EBlockItemIds.CEDAR_PLANKS,
                EBlockItemIds.MAHOGANY_PLANKS
        );

        // Stairs
        builder(BlockTags.WOODEN_STAIRS).add(
                EBlockItemIds.AZALEA_STAIRS,
                EBlockItemIds.CEDAR_STAIRS,
                EBlockItemIds.MAHOGANY_STAIRS
        );
        builder(BlockTags.STAIRS).add(
                EBlockItemIds.AZALEA_STAIRS,
                EBlockItemIds.CEDAR_STAIRS,
                EBlockItemIds.MAHOGANY_STAIRS
        ).addTag(EBlockTags.TERRACOTTA_STAIRS).addTag(EBlockTags.TERRACOTTA_BRICK_STAIRS).addTag(EBlockTags.TERRACOTTA_BRICK_TILE_STAIRS)
        ;

        // Slabs
        builder(BlockTags.WOODEN_SLABS).add(
                EBlockItemIds.AZALEA_SLAB,
                EBlockItemIds.CEDAR_SLAB,
                EBlockItemIds.MAHOGANY_SLAB
        );
        builder(BlockTags.SLABS).add(
                EBlockItemIds.AZALEA_SLAB,
                EBlockItemIds.CEDAR_SLAB,
                EBlockItemIds.MAHOGANY_SLAB
        ).addTag(EBlockTags.TERRACOTTA_SLABS).addTag(EBlockTags.TERRACOTTA_BRICK_SLABS).addTag(EBlockTags.TERRACOTTA_BRICK_TILE_SLABS)
        ;

        // Fences
        builder(BlockTags.WOODEN_FENCES).add(
                EBlockItemIds.AZALEA_FENCE,
                EBlockItemIds.CEDAR_FENCE,
                EBlockItemIds.MAHOGANY_FENCE
        );
        builder(BlockTags.FENCES).add(
                EBlockItemIds.AZALEA_FENCE,
                EBlockItemIds.CEDAR_FENCE,
                EBlockItemIds.MAHOGANY_FENCE
        );
        builder(BlockTags.FENCE_GATES).add(
                EBlockItemIds.AZALEA_FENCE_GATE,
                EBlockItemIds.CEDAR_FENCE_GATE,
                EBlockItemIds.MAHOGANY_FENCE_GATE
        );

        // Pressure Plates
        builder(BlockTags.WOODEN_PRESSURE_PLATES).add(
                EBlockItemIds.AZALEA_PRESSURE_PLATE,
                EBlockItemIds.CEDAR_PRESSURE_PLATE,
                EBlockItemIds.MAHOGANY_PRESSURE_PLATE
        );
        builder(BlockTags.PRESSURE_PLATES).add(
                EBlockItemIds.AZALEA_PRESSURE_PLATE,
                EBlockItemIds.CEDAR_PRESSURE_PLATE,
                EBlockItemIds.MAHOGANY_PRESSURE_PLATE
        );

        // Buttons
        builder(BlockTags.WOODEN_BUTTONS).add(
                EBlockItemIds.AZALEA_BUTTON,
                EBlockItemIds.CEDAR_BUTTON,
                EBlockItemIds.MAHOGANY_BUTTON
        );
        builder(BlockTags.BUTTONS).add(
                EBlockItemIds.AZALEA_BUTTON,
                EBlockItemIds.CEDAR_BUTTON,
                EBlockItemIds.MAHOGANY_BUTTON
        );

        // Doors
        builder(BlockTags.WOODEN_DOORS).add(
                EBlockItemIds.AZALEA_DOOR,
                EBlockItemIds.CEDAR_DOOR,
                EBlockItemIds.MAHOGANY_DOOR
        );
        builder(BlockTags.DOORS).add(
                EBlockItemIds.AZALEA_DOOR,
                EBlockItemIds.CEDAR_DOOR,
                EBlockItemIds.MAHOGANY_DOOR
        );
        builder(BlockTags.MOB_INTERACTABLE_DOORS).add(
                EBlockItemIds.AZALEA_DOOR,
                EBlockItemIds.CEDAR_DOOR,
                EBlockItemIds.MAHOGANY_DOOR
        );

        // Trapdoors
        builder(BlockTags.WOODEN_TRAPDOORS).add(
                EBlockItemIds.AZALEA_TRAPDOOR,
                EBlockItemIds.CEDAR_TRAPDOOR,
                EBlockItemIds.MAHOGANY_TRAPDOOR
        );
        builder(BlockTags.TRAPDOORS).add(
                EBlockItemIds.AZALEA_TRAPDOOR,
                EBlockItemIds.CEDAR_TRAPDOOR,
                EBlockItemIds.MAHOGANY_TRAPDOOR
        );

        // Signs
        builder(BlockTags.STANDING_SIGNS).add(
                EBlockItemIds.AZALEA_SIGN,
                EBlockItemIds.CEDAR_SIGN,
                EBlockItemIds.MAHOGANY_SIGN
        );
        builder(BlockTags.WALL_SIGNS).add(
                EBlockItemIds.AZALEA_WALL_SIGN,
                EBlockItemIds.CEDAR_WALL_SIGN,
                EBlockItemIds.MAHOGANY_WALL_SIGN
        );
        builder(BlockTags.SIGNS).add(
                EBlockItemIds.AZALEA_SIGN, EBlockItemIds.AZALEA_WALL_SIGN,
                EBlockItemIds.CEDAR_SIGN, EBlockItemIds.CEDAR_WALL_SIGN,
                EBlockItemIds.MAHOGANY_SIGN, EBlockItemIds.MAHOGANY_WALL_SIGN
        );
        builder(BlockTags.ALL_SIGNS).add(
                EBlockItemIds.AZALEA_SIGN, EBlockItemIds.AZALEA_WALL_SIGN,
                EBlockItemIds.CEDAR_SIGN, EBlockItemIds.CEDAR_WALL_SIGN,
                EBlockItemIds.MAHOGANY_SIGN, EBlockItemIds.MAHOGANY_WALL_SIGN
        );

        // Hanging Signs
        builder(BlockTags.CEILING_HANGING_SIGNS).add(
                EBlockItemIds.AZALEA_HANGING_SIGN,
                EBlockItemIds.CEDAR_HANGING_SIGN,
                EBlockItemIds.MAHOGANY_HANGING_SIGN
        );
        builder(BlockTags.WALL_HANGING_SIGNS).add(
                EBlockItemIds.AZALEA_WALL_HANGING_SIGN,
                EBlockItemIds.CEDAR_WALL_HANGING_SIGN,
                EBlockItemIds.MAHOGANY_WALL_HANGING_SIGN
        );
        builder(BlockTags.ALL_HANGING_SIGNS).add(
                EBlockItemIds.AZALEA_HANGING_SIGN, EBlockItemIds.AZALEA_WALL_HANGING_SIGN,
                EBlockItemIds.CEDAR_HANGING_SIGN, EBlockItemIds.CEDAR_WALL_HANGING_SIGN,
                EBlockItemIds.MAHOGANY_HANGING_SIGN, EBlockItemIds.MAHOGANY_WALL_HANGING_SIGN
        );

        // Shelves
        builder(BlockTags.WOODEN_SHELVES).add(
                EBlockItemIds.AZALEA_SHELF, EBlockItemIds.CEDAR_SHELF, EBlockItemIds.MAHOGANY_SHELF
        );

        // New Wood Blocks
        builder(EBlockTags.MOSAICS).add(
                EBlockItemIds.OAK_MOSAIC, EBlockItemIds.DARK_OAK_MOSAIC, EBlockItemIds.PALE_OAK_MOSAIC,
                EBlockItemIds.BIRCH_MOSAIC, EBlockItemIds.SPRUCE_MOSAIC, EBlockItemIds.JUNGLE_MOSAIC,
                EBlockItemIds.ACACIA_MOSAIC, EBlockItemIds.CHERRY_MOSAIC, EBlockItemIds.MANGROVE_MOSAIC,
                EBlockItemIds.CRIMSON_MOSAIC, EBlockItemIds.WARPED_MOSAIC,
                EBlockItemIds.AZALEA_MOSAIC, EBlockItemIds.CEDAR_MOSAIC, EBlockItemIds.MAHOGANY_MOSAIC,
                BlockItemIds.BAMBOO_MOSAIC
        );
        builder(EBlockTags.MOSAIC_STAIRS).add(
                EBlockItemIds.OAK_MOSAIC_STAIRS, EBlockItemIds.DARK_OAK_MOSAIC_STAIRS, EBlockItemIds.PALE_OAK_MOSAIC_STAIRS,
                EBlockItemIds.BIRCH_MOSAIC_STAIRS, EBlockItemIds.SPRUCE_MOSAIC_STAIRS, EBlockItemIds.JUNGLE_MOSAIC_STAIRS,
                EBlockItemIds.ACACIA_MOSAIC_STAIRS, EBlockItemIds.CHERRY_MOSAIC_STAIRS, EBlockItemIds.MANGROVE_MOSAIC_STAIRS,
                EBlockItemIds.CRIMSON_MOSAIC_STAIRS, EBlockItemIds.WARPED_MOSAIC_STAIRS,
                EBlockItemIds.AZALEA_MOSAIC_STAIRS, EBlockItemIds.CEDAR_MOSAIC_STAIRS, EBlockItemIds.MAHOGANY_MOSAIC_STAIRS,
                BlockItemIds.BAMBOO_MOSAIC_STAIRS
        );
        builder(EBlockTags.MOSAIC_SLABS).add(
                EBlockItemIds.OAK_MOSAIC_SLAB, EBlockItemIds.DARK_OAK_MOSAIC_SLAB, EBlockItemIds.PALE_OAK_MOSAIC_SLAB,
                EBlockItemIds.BIRCH_MOSAIC_SLAB, EBlockItemIds.SPRUCE_MOSAIC_SLAB, EBlockItemIds.JUNGLE_MOSAIC_SLAB,
                EBlockItemIds.ACACIA_MOSAIC_SLAB, EBlockItemIds.CHERRY_MOSAIC_SLAB, EBlockItemIds.MANGROVE_MOSAIC_SLAB,
                EBlockItemIds.CRIMSON_MOSAIC_SLAB, EBlockItemIds.WARPED_MOSAIC_SLAB,
                EBlockItemIds.AZALEA_MOSAIC_SLAB, EBlockItemIds.CEDAR_MOSAIC_SLAB, EBlockItemIds.MAHOGANY_MOSAIC_SLAB,
                BlockItemIds.BAMBOO_MOSAIC_SLAB
        );
        builder(EBlockTags.BOARDS).add(
                EBlockItemIds.OAK_BOARDS, EBlockItemIds.DARK_OAK_BOARDS, EBlockItemIds.PALE_OAK_BOARDS,
                EBlockItemIds.BIRCH_BOARDS, EBlockItemIds.SPRUCE_BOARDS, EBlockItemIds.JUNGLE_BOARDS,
                EBlockItemIds.ACACIA_BOARDS, EBlockItemIds.CHERRY_BOARDS, EBlockItemIds.MANGROVE_BOARDS,
                EBlockItemIds.CRIMSON_BOARDS, EBlockItemIds.WARPED_BOARDS,
                EBlockItemIds.AZALEA_BOARDS, EBlockItemIds.CEDAR_BOARDS, EBlockItemIds.MAHOGANY_BOARDS,
                EBlockItemIds.BAMBOO_BOARDS
        );
        builder(EBlockTags.TRIMS).add(
                EBlockItemIds.OAK_TRIM, EBlockItemIds.DARK_OAK_TRIM, EBlockItemIds.PALE_OAK_TRIM,
                EBlockItemIds.BIRCH_TRIM, EBlockItemIds.SPRUCE_TRIM, EBlockItemIds.JUNGLE_TRIM,
                EBlockItemIds.ACACIA_TRIM, EBlockItemIds.CHERRY_TRIM, EBlockItemIds.MANGROVE_TRIM,
                EBlockItemIds.CRIMSON_TRIM, EBlockItemIds.WARPED_TRIM,
                EBlockItemIds.AZALEA_TRIM, EBlockItemIds.CEDAR_TRIM, EBlockItemIds.MAHOGANY_TRIM,
                EBlockItemIds.BAMBOO_TRIM
        );
        builder(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.LOGS)
                .addTag(BlockTags.PLANKS).addTag(BlockTags.WOODEN_STAIRS).addTag(BlockTags.WOODEN_SLABS)
                .addTag(BlockTags.WOODEN_FENCES).addTag(BlockTags.FENCE_GATES)
                .addTag(BlockTags.WOODEN_PRESSURE_PLATES).addTag(BlockTags.WOODEN_BUTTONS)
                .addTag(BlockTags.WOODEN_DOORS).addTag(BlockTags.WOODEN_TRAPDOORS)
                .addTag(BlockTags.ALL_SIGNS).addTag(BlockTags.ALL_HANGING_SIGNS)
                .addTag(EBlockTags.MOSAICS).addTag(EBlockTags.MOSAIC_STAIRS).addTag(EBlockTags.MOSAIC_SLABS)
                .addTag(EBlockTags.BOARDS).addTag(EBlockTags.TRIMS)
        ;
        builder(BlockTags.PREVENTS_NEARBY_LEAF_DECAY)
                .addTag(BlockTags.LOGS)
        ;

        // Walls
        builder(BlockTags.WALLS).add(
                EBlockItemIds.STONE_WALL, EBlockItemIds.SMOOTH_STONE_WALL, EBlockItemIds.POLISHED_STONE_WALL, EBlockItemIds.STONE_TILE_WALL, EBlockItemIds.MOSSY_STONE_TILE_WALL,
                EBlockItemIds.DEEPSLATE_WALL,
                EBlockItemIds.POLISHED_ANDESITE_WALL, EBlockItemIds.ANDESITE_BRICK_WALL,
                EBlockItemIds.POLISHED_GRANITE_WALL, EBlockItemIds.GRANITE_BRICK_WALL,
                EBlockItemIds.POLISHED_DIORITE_WALL, EBlockItemIds.DIORITE_BRICK_WALL,
                EBlockItemIds.CALCITE_WALL, EBlockItemIds.POLISHED_CALCITE_WALL, EBlockItemIds.CALCITE_BRICK_WALL,
                EBlockItemIds.DRIPSTONE_WALL, EBlockItemIds.POLISHED_DRIPSTONE_WALL, EBlockItemIds.DRIPSTONE_TILE_WALL,

                EBlockItemIds.POLISHED_SANDSTONE_WALL, EBlockItemIds.POLISHED_RED_SANDSTONE_WALL,
                EBlockItemIds.SMOOTH_SANDSTONE_WALL, EBlockItemIds.SMOOTH_RED_SANDSTONE_WALL,
                EBlockItemIds.CUT_SANDSTONE_WALL, EBlockItemIds.CUT_RED_SANDSTONE_WALL,

                EBlockItemIds.POLISHED_PRISMARINE_WALL, EBlockItemIds.PRISMARINE_BRICK_WALL,
                EBlockItemIds.DARK_PRISMARINE_WALL, EBlockItemIds.POLISHED_DARK_PRISMARINE_WALL, EBlockItemIds.DARK_PRISMARINE_SCALE_WALL,
                EBlockItemIds.SMOOTH_BASALT_WALL, EBlockItemIds.POLISHED_SMOOTH_BASALT_WALL, EBlockItemIds.SMOOTH_BASALT_BRICK_WALL, EBlockItemIds.SMOOTH_BASALT_TILE_WALL,
                EBlockItemIds.END_STONE_WALL, EBlockItemIds.POLISHED_END_STONE_WALL,
                EBlockItemIds.SODALITE_WALL, EBlockItemIds.POLISHED_SODALITE_WALL, EBlockItemIds.SODALITE_BRICK_WALL,
                EBlockItemIds.ORPIMENT_WALL, EBlockItemIds.POLISHED_ORPIMENT_WALL, EBlockItemIds.ORPIMENT_BRICK_WALL,
                EBlockItemIds.SOUL_STONE_WALL, EBlockItemIds.SMOOTH_SOUL_STONE_WALL, EBlockItemIds.CUT_SOUL_STONE_WALL, EBlockItemIds.POLISHED_SOUL_STONE_WALL,
                EBlockItemIds.PACKED_MUD_WALL,

                EBlockItemIds.NETHERRACK_WALL, EBlockItemIds.POLISHED_NETHERRACK_WALL, EBlockItemIds.NETHERRACK_BRICK_WALL,

                EBlockItemIds.SHALE_WALL, EBlockItemIds.POLISHED_SHALE_WALL, EBlockItemIds.SHALE_BRICK_WALL
        );

        builder(EBlockTags.TERRACOTTA_STAIRS).add(EBlockItemIds.TERRACOTTA_STAIRS);
        EBlockItemIds.DYED_TERRACOTTA_STAIRS.forEach(c -> builder(EBlockTags.TERRACOTTA_STAIRS).add(c));
        EBlockItemIds.DYED_TERRACOTTA_STAIRS.forEach(c -> builder(EBlockTags.TERRACOTTA_STAIRS).add(c));
        EBlockItemIds.DYED_TERRACOTTA_SLAB.forEach(c -> builder(EBlockTags.TERRACOTTA_SLABS).add(c));
        EBlockItemIds.DYED_TERRACOTTA_BRICKS.forEach(c -> builder(EBlockTags.TERRACOTTA_BRICKS).add(c));
        EBlockItemIds.DYED_TERRACOTTA_BRICK_STAIRS.forEach(c -> builder(EBlockTags.TERRACOTTA_BRICK_STAIRS).add(c));
        EBlockItemIds.DYED_TERRACOTTA_BRICK_SLAB.forEach(c -> builder(EBlockTags.TERRACOTTA_BRICK_SLABS).add(c));
        EBlockItemIds.DYED_TERRACOTTA_BRICK_TILES.forEach(c -> builder(EBlockTags.TERRACOTTA_BRICK_TILES).add(c));
        EBlockItemIds.DYED_TERRACOTTA_BRICK_TILE_STAIRS.forEach(c -> builder(EBlockTags.TERRACOTTA_BRICK_TILE_STAIRS).add(c));
        EBlockItemIds.DYED_TERRACOTTA_BRICK_TILE_SLAB.forEach(c -> builder(EBlockTags.TERRACOTTA_BRICK_TILE_SLABS).add(c));
    }
}
