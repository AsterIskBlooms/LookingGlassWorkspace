package team.lookingglass.elsewhere.datagen.tags;

import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import team.lookingglass.elsewhere.registry.EBlockItemIds;
import team.lookingglass.elsewhere.registry.tags.EBlockItemTags;

import java.util.function.Function;

public class BlockItemTagProvider extends BlockItemTagsProvider {
    public BlockItemTagProvider(Function<BlockItemTagId, BlockItemTagsProvider.CombinedAppender> tagSupplier) {
        super(tagSupplier);
    }

    @Override
    public void run() {
        // Terrain
        tag(BlockItemTags.DIRT).add(
                EBlockItemIds.ARID_DIRT, EBlockItemIds.ARID_GRASS_BLOCK,
                EBlockItemIds.RED_GRASS_BLOCK, EBlockItemIds.LATERITE,
                BlockItemIds.MUD, EBlockItemIds.MOSSY_MUD
        );
        tag(BlockItemTags.GRASS_BLOCKS).add(
                EBlockItemIds.ARID_GRASS_BLOCK, EBlockItemIds.RED_GRASS_BLOCK, EBlockItemIds.SEAGRASS_SAND
        );
        tag(BlockItemTags.MUD).add(BlockItemIds.MUD, EBlockItemIds.MOSSY_MUD);
        tag(BlockItemTags.SAND).add(EBlockItemIds.PINK_SAND, EBlockItemIds.SEAGRASS_SAND);
        tag(BlockItemTags.MOSS_BLOCKS).add(EBlockItemIds.RUSTY_MOSS_BLOCK, EBlockItemIds.MOSSY_MUD);
        tag(BlockItemTags.LEAVES).add(
                EBlockItemIds.CEDAR_LEAVES, EBlockItemIds.MAHOGANY_LEAVES, EBlockItemIds.PINE_LEAVES,
                EBlockItemIds.GILDED_BIRCH_LEAVES
        );

        // Metalwork
        tag(BlockItemTags.CHAINS).add(EBlockItemIds.SILVER_CHAIN, EBlockItemIds.GOLD_CHAIN);
        tag(BlockItemTags.LANTERNS).add(EBlockItemIds.REDSTONE_LANTERN);
        tag(BlockItemTags.BARS).add(EBlockItemIds.TIN_BARS, EBlockItemIds.SILVER_BARS, EBlockItemIds.WROUGHT_IRON_BARS);

        // Flowers
        tag(BlockItemTags.FLOWERS).add(
                EBlockItemIds.BLUEBONNET,
                EBlockItemIds.RED_HIBISCUS, EBlockItemIds.ORANGE_HIBISCUS, EBlockItemIds.YELLOW_HIBISCUS, EBlockItemIds.BLUE_HIBISCUS,
                EBlockItemIds.PURPLE_HIBISCUS, EBlockItemIds.PINK_HIBISCUS, EBlockItemIds.WHITE_HIBISCUS,
                EBlockItemIds.MAGENTA_CONEFLOWER, EBlockItemIds.YELLOW_CONEFLOWER, EBlockItemIds.ROSE,
                EBlockItemIds.IRONWEED
        );
        tag(BlockItemTags.SMALL_FLOWERS).add(
                EBlockItemIds.BLUEBONNET,
                EBlockItemIds.RED_HIBISCUS, EBlockItemIds.ORANGE_HIBISCUS, EBlockItemIds.YELLOW_HIBISCUS, EBlockItemIds.BLUE_HIBISCUS,
                EBlockItemIds.PURPLE_HIBISCUS, EBlockItemIds.PINK_HIBISCUS, EBlockItemIds.WHITE_HIBISCUS,
                EBlockItemIds.MAGENTA_CONEFLOWER, EBlockItemIds.YELLOW_CONEFLOWER, EBlockItemIds.ROSE
        );

        // Logs
        tag(EBlockItemTags.AZALEA_LOGS).add(
                EBlockItemIds.AZALEA_LOG, EBlockItemIds.STRIPPED_AZALEA_LOG,
                EBlockItemIds.AZALEA_WOOD, EBlockItemIds.STRIPPED_AZALEA_WOOD
        );
        tag(EBlockItemTags.CEDAR_LOGS).add(
                EBlockItemIds.CEDAR_LOG, EBlockItemIds.STRIPPED_CEDAR_LOG,
                EBlockItemIds.CEDAR_WOOD, EBlockItemIds.STRIPPED_CEDAR_WOOD
        );
        tag(EBlockItemTags.MAHOGANY_LOGS).add(
                EBlockItemIds.MAHOGANY_LOG, EBlockItemIds.STRIPPED_MAHOGANY_LOG,
                EBlockItemIds.MAHOGANY_WOOD, EBlockItemIds.STRIPPED_MAHOGANY_WOOD
        );
        tag(EBlockItemTags.PINE_LOGS).add(
                EBlockItemIds.PINE_LOG, EBlockItemIds.STRIPPED_PINE_LOG,
                EBlockItemIds.PINE_WOOD, EBlockItemIds.STRIPPED_PINE_WOOD
        );
        tag(EBlockItemTags.BAMBOO_STEMS).add(EBlockItemIds.BAMBOO_STEM, EBlockItemIds.STRIPPED_BAMBOO_STEM);

        tag(BlockItemTags.LOGS_THAT_BURN)
                .addTag(EBlockItemTags.AZALEA_LOGS)
                .addTag(EBlockItemTags.CEDAR_LOGS)
                .addTag(EBlockItemTags.MAHOGANY_LOGS)
                .addTag(EBlockItemTags.PINE_LOGS)
                .addTag(EBlockItemTags.BAMBOO_STEMS)
        ;

        // Wood Sets
        tag(BlockItemTags.PLANKS).add(
                EBlockItemIds.AZALEA_PLANKS, EBlockItemIds.CEDAR_PLANKS, EBlockItemIds.MAHOGANY_PLANKS, EBlockItemIds.PINE_PLANKS
        );
        tag(BlockItemTags.WOODEN_STAIRS).add(
                EBlockItemIds.AZALEA_STAIRS, EBlockItemIds.CEDAR_STAIRS, EBlockItemIds.MAHOGANY_STAIRS, EBlockItemIds.PINE_STAIRS
        );
        tag(BlockItemTags.WOODEN_SLABS).add(
                EBlockItemIds.AZALEA_SLAB, EBlockItemIds.CEDAR_SLAB, EBlockItemIds.MAHOGANY_SLAB, EBlockItemIds.PINE_SLAB
        );
        tag(BlockItemTags.WOODEN_FENCES).add(
                EBlockItemIds.AZALEA_FENCE, EBlockItemIds.CEDAR_FENCE, EBlockItemIds.MAHOGANY_FENCE, EBlockItemIds.PINE_FENCE
        );
        tag(BlockItemTags.FENCE_GATES).add(
                EBlockItemIds.AZALEA_FENCE_GATE, EBlockItemIds.CEDAR_FENCE_GATE, EBlockItemIds.MAHOGANY_FENCE_GATE, EBlockItemIds.PINE_FENCE_GATE
        );
        tag(BlockItemTags.WOODEN_PRESSURE_PLATES).add(
                EBlockItemIds.AZALEA_PRESSURE_PLATE, EBlockItemIds.CEDAR_PRESSURE_PLATE, EBlockItemIds.MAHOGANY_PRESSURE_PLATE, EBlockItemIds.PINE_PRESSURE_PLATE
        );
        tag(BlockItemTags.WOODEN_BUTTONS).add(
                EBlockItemIds.AZALEA_BUTTON, EBlockItemIds.CEDAR_BUTTON, EBlockItemIds.MAHOGANY_BUTTON, EBlockItemIds.PINE_BUTTON
        );
        tag(BlockItemTags.WOODEN_DOORS).add(
                EBlockItemIds.AZALEA_DOOR, EBlockItemIds.CEDAR_DOOR, EBlockItemIds.MAHOGANY_DOOR, EBlockItemIds.PINE_DOOR
        );
        tag(BlockItemTags.WOODEN_TRAPDOORS).add(
                EBlockItemIds.AZALEA_TRAPDOOR, EBlockItemIds.CEDAR_TRAPDOOR, EBlockItemIds.MAHOGANY_TRAPDOOR, EBlockItemIds.PINE_TRAPDOOR
        );
        tag(BlockItemTags.WOODEN_SHELVES).add(
                EBlockItemIds.AZALEA_SHELF, EBlockItemIds.CEDAR_SHELF, EBlockItemIds.MAHOGANY_SHELF, EBlockItemIds.PINE_SHELF
        );
        tag(BlockItemTags.SIGNS).add(
                EBlockItemIds.AZALEA_SIGN, EBlockItemIds.CEDAR_SIGN, EBlockItemIds.MAHOGANY_SIGN, EBlockItemIds.PINE_SIGN
        );
        tag(BlockItemTags.HANGING_SIGNS).add(
                EBlockItemIds.AZALEA_HANGING_SIGN, EBlockItemIds.CEDAR_HANGING_SIGN, EBlockItemIds.MAHOGANY_HANGING_SIGN, EBlockItemIds.PINE_HANGING_SIGN
        );

        // Mosaics / boards / trims
        tag(EBlockItemTags.MOSAICS).add(
                EBlockItemIds.OAK_MOSAIC, EBlockItemIds.DARK_OAK_MOSAIC, EBlockItemIds.PALE_OAK_MOSAIC,
                EBlockItemIds.BIRCH_MOSAIC, EBlockItemIds.SPRUCE_MOSAIC, EBlockItemIds.JUNGLE_MOSAIC,
                EBlockItemIds.ACACIA_MOSAIC, EBlockItemIds.CHERRY_MOSAIC, EBlockItemIds.MANGROVE_MOSAIC,
                EBlockItemIds.CRIMSON_MOSAIC, EBlockItemIds.WARPED_MOSAIC,
                EBlockItemIds.AZALEA_MOSAIC, EBlockItemIds.CEDAR_MOSAIC, EBlockItemIds.MAHOGANY_MOSAIC, EBlockItemIds.PINE_MOSAIC,
                BlockItemIds.BAMBOO_MOSAIC
        );
        tag(EBlockItemTags.MOSAIC_STAIRS).add(
                EBlockItemIds.OAK_MOSAIC_STAIRS, EBlockItemIds.DARK_OAK_MOSAIC_STAIRS, EBlockItemIds.PALE_OAK_MOSAIC_STAIRS,
                EBlockItemIds.BIRCH_MOSAIC_STAIRS, EBlockItemIds.SPRUCE_MOSAIC_STAIRS, EBlockItemIds.JUNGLE_MOSAIC_STAIRS,
                EBlockItemIds.ACACIA_MOSAIC_STAIRS, EBlockItemIds.CHERRY_MOSAIC_STAIRS, EBlockItemIds.MANGROVE_MOSAIC_STAIRS,
                EBlockItemIds.CRIMSON_MOSAIC_STAIRS, EBlockItemIds.WARPED_MOSAIC_STAIRS,
                EBlockItemIds.AZALEA_MOSAIC_STAIRS, EBlockItemIds.CEDAR_MOSAIC_STAIRS, EBlockItemIds.MAHOGANY_MOSAIC_STAIRS, EBlockItemIds.PINE_MOSAIC_STAIRS,
                BlockItemIds.BAMBOO_MOSAIC_STAIRS
        );
        tag(EBlockItemTags.MOSAIC_SLABS).add(
                EBlockItemIds.OAK_MOSAIC_SLAB, EBlockItemIds.DARK_OAK_MOSAIC_SLAB, EBlockItemIds.PALE_OAK_MOSAIC_SLAB,
                EBlockItemIds.BIRCH_MOSAIC_SLAB, EBlockItemIds.SPRUCE_MOSAIC_SLAB, EBlockItemIds.JUNGLE_MOSAIC_SLAB,
                EBlockItemIds.ACACIA_MOSAIC_SLAB, EBlockItemIds.CHERRY_MOSAIC_SLAB, EBlockItemIds.MANGROVE_MOSAIC_SLAB,
                EBlockItemIds.CRIMSON_MOSAIC_SLAB, EBlockItemIds.WARPED_MOSAIC_SLAB,
                EBlockItemIds.AZALEA_MOSAIC_SLAB, EBlockItemIds.CEDAR_MOSAIC_SLAB, EBlockItemIds.MAHOGANY_MOSAIC_SLAB, EBlockItemIds.PINE_MOSAIC_SLAB,
                BlockItemIds.BAMBOO_MOSAIC_SLAB
        );
        tag(EBlockItemTags.BOARDS).add(
                EBlockItemIds.OAK_BOARDS, EBlockItemIds.DARK_OAK_BOARDS, EBlockItemIds.PALE_OAK_BOARDS,
                EBlockItemIds.BIRCH_BOARDS, EBlockItemIds.SPRUCE_BOARDS, EBlockItemIds.JUNGLE_BOARDS,
                EBlockItemIds.ACACIA_BOARDS, EBlockItemIds.CHERRY_BOARDS, EBlockItemIds.MANGROVE_BOARDS,
                EBlockItemIds.CRIMSON_BOARDS, EBlockItemIds.WARPED_BOARDS,
                EBlockItemIds.AZALEA_BOARDS, EBlockItemIds.CEDAR_BOARDS, EBlockItemIds.MAHOGANY_BOARDS, EBlockItemIds.PINE_BOARDS,
                EBlockItemIds.BAMBOO_BOARDS
        );
        tag(EBlockItemTags.TRIMS).add(
                EBlockItemIds.OAK_TRIM, EBlockItemIds.DARK_OAK_TRIM, EBlockItemIds.PALE_OAK_TRIM,
                EBlockItemIds.BIRCH_TRIM, EBlockItemIds.SPRUCE_TRIM, EBlockItemIds.JUNGLE_TRIM,
                EBlockItemIds.ACACIA_TRIM, EBlockItemIds.CHERRY_TRIM, EBlockItemIds.MANGROVE_TRIM,
                EBlockItemIds.CRIMSON_TRIM, EBlockItemIds.WARPED_TRIM,
                EBlockItemIds.AZALEA_TRIM, EBlockItemIds.CEDAR_TRIM, EBlockItemIds.MAHOGANY_TRIM, EBlockItemIds.PINE_TRIM,
                EBlockItemIds.BAMBOO_TRIM
        );

        // Terracotta variants
        tag(EBlockItemTags.TERRACOTTA_STAIRS).add(EBlockItemIds.TERRACOTTA_STAIRS).addAll(EBlockItemIds.DYED_TERRACOTTA_STAIRS.asList());
        tag(EBlockItemTags.TERRACOTTA_SLABS).addAll(EBlockItemIds.DYED_TERRACOTTA_SLAB.asList());
        tag(EBlockItemTags.TERRACOTTA_BRICKS).addAll(EBlockItemIds.DYED_TERRACOTTA_BRICKS.asList());
        tag(EBlockItemTags.TERRACOTTA_BRICK_STAIRS).addAll(EBlockItemIds.DYED_TERRACOTTA_BRICK_STAIRS.asList());
        tag(EBlockItemTags.TERRACOTTA_BRICK_SLABS).addAll(EBlockItemIds.DYED_TERRACOTTA_BRICK_SLAB.asList());
        tag(EBlockItemTags.TERRACOTTA_BRICK_TILES).addAll(EBlockItemIds.DYED_TERRACOTTA_BRICK_TILES.asList());
        tag(EBlockItemTags.TERRACOTTA_BRICK_TILE_STAIRS).addAll(EBlockItemIds.DYED_TERRACOTTA_BRICK_TILE_STAIRS.asList());
        tag(EBlockItemTags.TERRACOTTA_BRICK_TILE_SLABS).addAll(EBlockItemIds.DYED_TERRACOTTA_BRICK_TILE_SLAB.asList());

        tag(BlockItemTags.STAIRS)
                .addTag(EBlockItemTags.TERRACOTTA_STAIRS)
                .addTag(EBlockItemTags.TERRACOTTA_BRICK_STAIRS)
                .addTag(EBlockItemTags.TERRACOTTA_BRICK_TILE_STAIRS);
        tag(BlockItemTags.SLABS)
                .addTag(EBlockItemTags.TERRACOTTA_SLABS)
                .addTag(EBlockItemTags.TERRACOTTA_BRICK_SLABS)
                .addTag(EBlockItemTags.TERRACOTTA_BRICK_TILE_SLABS);

        // Walls
        tag(BlockItemTags.WALLS).add(
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
                EBlockItemIds.NETHERRACK_WALL, EBlockItemIds.POLISHED_NETHERRACK_WALL, EBlockItemIds.NETHERRACK_BRICK_WALL,
                EBlockItemIds.SHALE_WALL, EBlockItemIds.POLISHED_SHALE_WALL, EBlockItemIds.SHALE_BRICK_WALL
        );
    }
}