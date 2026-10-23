package team.lookingglass.elsewhere.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColorCollection;

import java.util.List;
import java.util.Map;

public class ETabs {

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.insertAfter(Items.GRASS_BLOCK, EBlocks.ARID_GRASS_BLOCK, EBlocks.RED_GRASS_BLOCK);
            output.insertAfter(Items.DIRT, EBlocks.ARID_DIRT);
            output.insertAfter(Items.PODZOL, EBlocks.LATERITE);

            output.insertAfter(Items.SANDSTONE, EBlocks.PINK_SAND, EBlocks.PINK_SANDSTONE);

            output.insertAfter(Items.SHORT_GRASS,
                    EBlocks.SHORT_RED_GRASS,
                    EBlocks.SHORT_MOSS_SPOROPHYTE, EBlocks.SHORT_RUSTY_MOSS_SPOROPHYTE, EBlocks.SHORT_PALE_MOSS_SPOROPHYTE
            );
            output.insertAfter(Items.TALL_GRASS,
                    EBlocks.TALL_RED_GRASS,
                    EBlocks.TALL_MOSS_SPOROPHYTE, EBlocks.TALL_RUSTY_MOSS_SPOROPHYTE, EBlocks.TALL_PALE_MOSS_SPOROPHYTE
            );
            output.insertAfter(Items.DRY_SHORT_GRASS, EBlocks.CAVE_WEED);
            output.insertAfter(Items.RED_MUSHROOM, EBlocks.GLEAMSHROOM);
            output.insertAfter(Items.LARGE_FERN, EBlocks.CATTAILS);

            output.insertAfter(Items.MOSS_CARPET, EBlocks.RUSTY_MOSS_BLOCK, EBlocks.RUSTY_MOSS_CARPET);
            output.insertAfter(Items.MUD, EBlocks.MOSSY_MUD, EBlocks.MUDSTONE);

            output.insertAfter(Items.NETHER_SPROUTS, EBlocks.SHORT_SOUL_SPROUT, EBlocks.TALL_SOUL_SPROUT, EBlocks.SOULGLASS_CLUSTER);

            output.insertAfter(Items.RED_SANDSTONE, EBlocks.BEACHSTONE);
            output.insertAfter(Items.BASALT, EBlocks.BASALT_VENT);
            output.insertAfter(Items.SMOOTH_BASALT, EBlocks.ROCKWEED_BASALT);
            output.insertAfter(Items.SEAGRASS, EBlocks.SEAGRASS_SAND);

            output.insertAfter(Items.BIRCH_SAPLING, EBlocks.CEDAR_SAPLING);
            output.insertAfter(Items.BIRCH_LOG, EBlocks.CEDAR_LOG);
            output.insertAfter(Items.BIRCH_LEAVES, EBlocks.GILDED_BIRCH_LEAVES, EBlocks.CEDAR_LEAVES);

            output.insertAfter(Items.JUNGLE_SAPLING, EBlocks.MAHOGANY_SAPLING);
            output.insertAfter(Items.JUNGLE_LOG, EBlocks.MAHOGANY_LOG);
            output.insertAfter(Items.JUNGLE_LEAVES, EBlocks.MAHOGANY_LEAVES);

            output.insertAfter(Items.STONE, EBlocks.SHALE);
            output.insertAfter(Items.CALCITE, EBlocks.SODALITE);
            output.insertAfter(Items.ANDESITE, EBlocks.LIMESTONE);
            output.insertAfter(Items.CINNABAR, EBlocks.ORPIMENT);
            output.insertAfter(Items.SOUL_SOIL, EBlocks.SOUL_STONE);
            output.insertAfter(Items.AMETHYST_CLUSTER, EBlocks.AMETHYST_SPIRE);
            output.insertAfter(Items.AMETHYST_CLUSTER, EBlocks.RAW_SOULGLASS, EBlocks.SOULGLASS_CLUSTER);

            output.insertAfter(Items.DEEPSLATE_COPPER_ORE, EBlocks.TIN_ORE, EBlocks.CINNABAR_TIN_ORE, EBlocks.DEEPSLATE_TIN_ORE);
            output.insertAfter(Items.GOLD_ORE, EBlocks.CINNABAR_GOLD_ORE);
            output.insertAfter(Items.DEEPSLATE_GOLD_ORE, EBlocks.SILVER_ORE, EBlocks.CINNABAR_SILVER_ORE, EBlocks.DEEPSLATE_SILVER_ORE);

            output.insertAfter(Items.NETHER_GOLD_ORE, EBlocks.SOUL_SILVER_ORE);
            output.insertAfter(Items.NETHER_QUARTZ_ORE, EBlocks.SOUL_QUARTZ_ORE);
            output.insertBefore(Items.GILDED_BLACKSTONE, EBlocks.BEJEWELED_CALCITE);

            output.insertAfter(Items.RAW_COPPER_BLOCK, EBlocks.RAW_TIN_BLOCK);
            output.insertAfter(Items.RAW_GOLD_BLOCK, EBlocks.RAW_SILVER_BLOCK);

            output.insertAfter(Items.LILY_OF_THE_VALLEY, EBlocks.BLUEBONNET, EBlocks.YELLOW_CONEFLOWER, EBlocks.MAGENTA_CONEFLOWER);
            output.insertAfter(Items.PINK_TULIP,
                    EBlocks.RED_HIBISCUS, EBlocks.ORANGE_HIBISCUS, EBlocks.YELLOW_HIBISCUS,
                    EBlocks.BLUE_HIBISCUS, EBlocks.PURPLE_HIBISCUS, EBlocks.PINK_HIBISCUS, EBlocks.WHITE_HIBISCUS
            );
            output.insertAfter(Items.DANDELION, EBlocks.ROSE);
            output.insertAfter(Items.LEAF_LITTER, EBlocks.PEBBLE);

        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {

            // Stone & Shale
            output.insertAfter(Items.STONE_SLAB, EBlocks.STONE_WALL);

            output.insertAfter(Items.SMOOTH_STONE_SLAB,
                    EBlocks.SMOOTH_STONE_STAIRS, EBlocks.SMOOTH_STONE_WALL,
                    EBlocks.CHISELED_STONE,
                    EBlocks.STONE_PILLAR,
                    EBlocks.POLISHED_STONE, EBlocks.POLISHED_STONE_STAIRS, EBlocks.POLISHED_STONE_SLAB, EBlocks.POLISHED_STONE_WALL
            );

            output.insertAfter(Items.MOSSY_STONE_BRICK_WALL,
                    EBlocks.STONE_TILES, EBlocks.STONE_TILE_STAIRS, EBlocks.STONE_TILE_SLAB, EBlocks.STONE_TILE_WALL,
                    EBlocks.MOSSY_STONE_TILES, EBlocks.MOSSY_STONE_TILE_STAIRS, EBlocks.MOSSY_STONE_TILE_SLAB, EBlocks.MOSSY_STONE_TILE_WALL
            );

            output.insertAfter(Items.CHISELED_TUFF_BRICKS,
                    EBlocks.SHALE, EBlocks.SHALE_STAIRS, EBlocks.SHALE_SLAB, EBlocks.SHALE_WALL,
                    EBlocks.POLISHED_SHALE, EBlocks.POLISHED_SHALE_STAIRS, EBlocks.POLISHED_SHALE_SLAB, EBlocks.POLISHED_SHALE_WALL,
                    EBlocks.CHISELED_SHALE, EBlocks.SHALE_PILLAR,
                    EBlocks.SHALE_BRICKS, EBlocks.SHALE_BRICK_STAIRS, EBlocks.SHALE_BRICK_SLAB, EBlocks.SHALE_BRICK_WALL,
                    EBlocks.CHISELED_SHALE_BRICKS
            );

            // Deepslate
            output.insertAfter(Items.DEEPSLATE,
                    EBlocks.DEEPSLATE_STAIRS, EBlocks.DEEPSLATE_SLAB, EBlocks.DEEPSLATE_WALL);
            output.insertAfter(Items.CHISELED_DEEPSLATE, EBlocks.DEEPSLATE_PILLAR);
            output.insertAfter(Items.DEEPSLATE_BRICK_WALL, EBlocks.CHISELED_DEEPSLATE_BRICKS);

            // Granite, Diorite, Andesite
            output.insertAfter(Items.GRANITE_WALL, EBlocks.CHISELED_GRANITE);
            output.insertAfter(Items.POLISHED_GRANITE_SLAB,
                    EBlocks.POLISHED_GRANITE_WALL,
                    EBlocks.GRANITE_BRICKS, EBlocks.GRANITE_BRICK_STAIRS, EBlocks.GRANITE_BRICK_SLAB, EBlocks.GRANITE_BRICK_WALL);

            output.insertAfter(Items.DIORITE_WALL, EBlocks.CHISELED_DIORITE);
            output.insertAfter(Items.POLISHED_DIORITE_SLAB,
                    EBlocks.POLISHED_DIORITE_WALL,
                    EBlocks.DIORITE_BRICKS, EBlocks.DIORITE_BRICK_STAIRS, EBlocks.DIORITE_BRICK_SLAB, EBlocks.DIORITE_BRICK_WALL);

            output.insertAfter(Items.ANDESITE_WALL,
                    EBlocks.CHISELED_ANDESITE, EBlocks.ANDESITE_PILLAR);
            output.insertAfter(Items.POLISHED_ANDESITE_SLAB,
                    EBlocks.POLISHED_ANDESITE_WALL,
                    EBlocks.ANDESITE_BRICKS, EBlocks.ANDESITE_BRICK_STAIRS, EBlocks.ANDESITE_BRICK_SLAB, EBlocks.ANDESITE_BRICK_WALL);

            // Tuff
            output.insertAfter(Items.CHISELED_TUFF, EBlocks.TUFF_PILLAR);

            // Calcite, Sodalite, Dripstone, Sulfur, Orpiment, & Cinnabar
            output.insertAfter(Items.CHISELED_TUFF_BRICKS,
                    Blocks.CALCITE, EBlocks.CALCITE_STAIRS, EBlocks.CALCITE_SLAB, EBlocks.CALCITE_WALL,
                    EBlocks.CHISELED_CALCITE, EBlocks.CALCITE_PILLAR,
                    EBlocks.POLISHED_CALCITE, EBlocks.POLISHED_CALCITE_STAIRS, EBlocks.POLISHED_CALCITE_SLAB, EBlocks.POLISHED_CALCITE_WALL,
                    EBlocks.CALCITE_BRICKS, EBlocks.CALCITE_BRICK_STAIRS, EBlocks.CALCITE_BRICK_SLAB, EBlocks.CALCITE_BRICK_WALL,
                    EBlocks.CHISELED_CALCITE_BRICKS,

                    EBlocks.SODALITE, EBlocks.SODALITE_STAIRS, EBlocks.SODALITE_SLAB, EBlocks.SODALITE_WALL,
                    EBlocks.CHISELED_SODALITE, EBlocks.SODALITE_PILLAR,
                    EBlocks.POLISHED_SODALITE, EBlocks.POLISHED_SODALITE_STAIRS, EBlocks.POLISHED_SODALITE_SLAB, EBlocks.POLISHED_SODALITE_WALL,
                    EBlocks.SODALITE_BRICKS, EBlocks.SODALITE_BRICK_STAIRS, EBlocks.SODALITE_BRICK_SLAB, EBlocks.SODALITE_BRICK_WALL,
                    EBlocks.CHISELED_SODALITE_BRICKS,

                    Blocks.DRIPSTONE_BLOCK, EBlocks.DRIPSTONE_STAIRS, EBlocks.DRIPSTONE_SLAB, EBlocks.DRIPSTONE_WALL,EBlocks.DRIPSTONE_PILLAR,
                    EBlocks.POLISHED_DRIPSTONE, EBlocks.POLISHED_DRIPSTONE_STAIRS, EBlocks.POLISHED_DRIPSTONE_SLAB, EBlocks.POLISHED_DRIPSTONE_WALL,
                    EBlocks.DRIPSTONE_TILES, EBlocks.DRIPSTONE_TILE_STAIRS, EBlocks.DRIPSTONE_TILE_SLAB, EBlocks.DRIPSTONE_TILE_WALL,
                    EBlocks.CHISELED_DRIPSTONE_TILES,

                    EBlocks.LIMESTONE, EBlocks.LIMESTONE_STAIRS, EBlocks.LIMESTONE_SLAB, EBlocks.LIMESTONE_WALL,
                    EBlocks.CUT_LIMESTONE, EBlocks.CUT_LIMESTONE_STAIRS, EBlocks.CUT_LIMESTONE_SLAB, EBlocks.CUT_LIMESTONE_WALL
            );
            output.insertAfter(Blocks.CHISELED_SULFUR, EBlocks.SULFUR_PILLAR);
            output.insertAfter(Blocks.CHISELED_CINNABAR, EBlocks.CINNABAR_PILLAR);
            output.insertAfter(Blocks.CINNABAR_BRICK_WALL,
                    EBlocks.ORPIMENT, EBlocks.ORPIMENT_STAIRS, EBlocks.ORPIMENT_SLAB, EBlocks.ORPIMENT_WALL,
                    EBlocks.CHISELED_ORPIMENT, EBlocks.ORPIMENT_PILLAR,
                    EBlocks.POLISHED_ORPIMENT, EBlocks.POLISHED_ORPIMENT_STAIRS, EBlocks.POLISHED_ORPIMENT_SLAB, EBlocks.POLISHED_ORPIMENT_WALL,
                    EBlocks.ORPIMENT_BRICKS, EBlocks.ORPIMENT_BRICK_STAIRS, EBlocks.ORPIMENT_BRICK_SLAB, EBlocks.ORPIMENT_BRICK_WALL
            );

            // Prismarine
            output.insertAfter(Items.PRISMARINE_WALL,
                    EBlocks.CHISELED_PRISMARINE, EBlocks.PRISMARINE_PILLAR,
                    EBlocks.POLISHED_PRISMARINE, EBlocks.POLISHED_PRISMARINE_STAIRS, EBlocks.POLISHED_PRISMARINE_SLAB, EBlocks.POLISHED_PRISMARINE_WALL);
            output.insertAfter(Items.PRISMARINE_BRICK_SLAB,
                    EBlocks.PRISMARINE_BRICK_WALL);

            output.insertAfter(Items.DARK_PRISMARINE_SLAB,
                    EBlocks.DARK_PRISMARINE_WALL,
                    EBlocks.CHISELED_DARK_PRISMARINE,
                    EBlocks.POLISHED_DARK_PRISMARINE, EBlocks.POLISHED_DARK_PRISMARINE_STAIRS, EBlocks.POLISHED_DARK_PRISMARINE_SLAB, EBlocks.POLISHED_DARK_PRISMARINE_WALL,
                    EBlocks.DARK_PRISMARINE_SCALES, EBlocks.DARK_PRISMARINE_SCALE_STAIRS, EBlocks.DARK_PRISMARINE_SCALE_SLAB, EBlocks.DARK_PRISMARINE_SCALE_WALL);

            output.insertAfter(Items.NETHERRACK,
                    EBlocks.NETHERRACK_STAIRS, EBlocks.NETHERRACK_SLAB, EBlocks.NETHERRACK_WALL,
                    EBlocks.CHISELED_NETHERRACK, EBlocks.NETHERRACK_PILLAR,
                    EBlocks.POLISHED_NETHERRACK, EBlocks.POLISHED_NETHERRACK_STAIRS, EBlocks.POLISHED_NETHERRACK_SLAB, EBlocks.POLISHED_NETHERRACK_WALL,
                    EBlocks.NETHERRACK_BRICKS, EBlocks.NETHERRACK_BRICK_STAIRS, EBlocks.NETHERRACK_BRICK_SLAB, EBlocks.NETHERRACK_BRICK_WALL,
                    EBlocks.CHISELED_NETHERRACK_BRICKS);

            output.insertAfter(Items.BLACKSTONE_WALL, EBlocks.BLACKSTONE_PILLAR);

            // Basalt & End Stone
            output.insertAfter(Items.BASALT, EBlocks.BASALT_SLAB);
            output.insertAfter(Items.SMOOTH_BASALT,
                    EBlocks.SMOOTH_BASALT_STAIRS, EBlocks.SMOOTH_BASALT_SLAB, EBlocks.SMOOTH_BASALT_WALL
                    );
            output.insertAfter(Items.POLISHED_BASALT,
                    EBlocks.POLISHED_BASALT_SLAB,
                    EBlocks.SMOOTH_BASALT_STAIRS, EBlocks.SMOOTH_BASALT_SLAB, EBlocks.SMOOTH_BASALT_WALL,
                    EBlocks.POLISHED_SMOOTH_BASALT, EBlocks.POLISHED_SMOOTH_BASALT_STAIRS, EBlocks.POLISHED_SMOOTH_BASALT_SLAB, EBlocks.POLISHED_SMOOTH_BASALT_WALL,
                    EBlocks.SMOOTH_BASALT_BRICKS, EBlocks.SMOOTH_BASALT_BRICK_STAIRS, EBlocks.SMOOTH_BASALT_BRICK_SLAB, EBlocks.SMOOTH_BASALT_BRICK_WALL,
                    EBlocks.SMOOTH_BASALT_TILES, EBlocks.SMOOTH_BASALT_TILE_STAIRS, EBlocks.SMOOTH_BASALT_TILE_SLAB, EBlocks.SMOOTH_BASALT_TILE_WALL
                    );

            output.insertAfter(Items.END_STONE,
                    EBlocks.END_STONE_STAIRS, EBlocks.END_STONE_SLAB, EBlocks.END_STONE_WALL,
                    EBlocks.CHISELED_END_STONE, EBlocks.END_STONE_PILLAR,
                    EBlocks.POLISHED_END_STONE, EBlocks.POLISHED_END_STONE_STAIRS, EBlocks.POLISHED_END_STONE_SLAB, EBlocks.POLISHED_END_STONE_WALL);

            // Sandstones
            output.insertAfter(Items.SMOOTH_SANDSTONE_SLAB,
                    EBlocks.SMOOTH_SANDSTONE_WALL);
            output.insertAfter(Items.CUT_SANDSTONE, EBlocks.CUT_SANDSTONE_STAIRS);
            output.insertAfter(Blocks.CUT_SANDSTONE_SLAB,
                    EBlocks.CUT_SANDSTONE_WALL,
                    EBlocks.POLISHED_SANDSTONE, EBlocks.POLISHED_SANDSTONE_STAIRS, EBlocks.POLISHED_SANDSTONE_SLAB, EBlocks.POLISHED_SANDSTONE_WALL);

            output.insertAfter(Items.SMOOTH_RED_SANDSTONE_SLAB,
                    EBlocks.SMOOTH_RED_SANDSTONE_WALL);
            output.insertAfter(Items.CUT_RED_SANDSTONE, EBlocks.CUT_RED_SANDSTONE_STAIRS);
            output.insertAfter(Items.CUT_RED_SANDSTONE_SLAB,
                    EBlocks.CUT_RED_SANDSTONE_WALL,
                    EBlocks.POLISHED_RED_SANDSTONE, EBlocks.POLISHED_RED_SANDSTONE_STAIRS, EBlocks.POLISHED_RED_SANDSTONE_SLAB, EBlocks.POLISHED_RED_SANDSTONE_WALL,

                    EBlocks.PINK_SANDSTONE, EBlocks.PINK_SANDSTONE_STAIRS, EBlocks.PINK_SANDSTONE_SLAB, EBlocks.PINK_SANDSTONE_WALL,
                    EBlocks.SMOOTH_PINK_SANDSTONE, EBlocks.SMOOTH_PINK_SANDSTONE_STAIRS, EBlocks.SMOOTH_PINK_SANDSTONE_SLAB, EBlocks.SMOOTH_PINK_SANDSTONE_WALL,
                    EBlocks.CUT_PINK_SANDSTONE, EBlocks.CUT_PINK_SANDSTONE_STAIRS, EBlocks.CUT_PINK_SANDSTONE_SLAB, EBlocks.CUT_PINK_SANDSTONE_WALL,
                    EBlocks.CHISELED_PINK_SANDSTONE,
                    EBlocks.POLISHED_PINK_SANDSTONE, EBlocks.POLISHED_PINK_SANDSTONE_STAIRS, EBlocks.POLISHED_PINK_SANDSTONE_SLAB, EBlocks.POLISHED_PINK_SANDSTONE_WALL,

                    EBlocks.SOUL_STONE, EBlocks.SOUL_STONE_STAIRS, EBlocks.SOUL_STONE_SLAB, EBlocks.SOUL_STONE_WALL,
                    EBlocks.SMOOTH_SOUL_STONE, EBlocks.SMOOTH_SOUL_STONE_STAIRS, EBlocks.SMOOTH_SOUL_STONE_SLAB, EBlocks.SMOOTH_SOUL_STONE_WALL,
                    EBlocks.CUT_SOUL_STONE, EBlocks.CUT_SOUL_STONE_STAIRS, EBlocks.CUT_SOUL_STONE_SLAB, EBlocks.CUT_SOUL_STONE_WALL,
                    EBlocks.CHISELED_SOUL_STONE,
                    EBlocks.POLISHED_SOUL_STONE, EBlocks.POLISHED_SOUL_STONE_STAIRS, EBlocks.POLISHED_SOUL_STONE_SLAB, EBlocks.POLISHED_SOUL_STONE_WALL);

            // Mud, Quartz, & Amethyst
            output.insertAfter(Items.PACKED_MUD, EBlocks.PACKED_MUD_STAIRS, EBlocks.PACKED_MUD_SLAB, EBlocks.PACKED_MUD_WALL);
            output.insertAfter(Items.MUD_BRICK_WALL, EBlocks.CHISELED_MUD_BRICKS, EBlocks.MUD_BRICK_PILLAR);
            output.insertAfter(Items.QUARTZ_BRICKS, EBlocks.QUARTZ_BRICK_STAIRS, EBlocks.QUARTZ_BRICK_SLAB);
            output.insertAfter(Items.PURPUR_PILLAR, EBlocks.CHISELED_PURPUR);

            output.insertAfter(Items.PURPUR_SLAB,
                    Blocks.OBSIDIAN, EBlocks.OBSIDIAN_STAIRS, EBlocks.OBSIDIAN_SLAB, EBlocks.CHISELED_OBSIDIAN,
                    EBlocks.OBSIDIAN_BRICKS, EBlocks.OBSIDIAN_BRICK_STAIRS, EBlocks.OBSIDIAN_BRICK_SLAB, EBlocks.OBSIDIAN_PILLAR
            );

            output.insertAfter(Items.HEAVY_WEIGHTED_PRESSURE_PLATE,
                    EBlocks.WROUGHT_IRON_BLOCK, EBlocks.WROUGHT_IRON_STAIRS, EBlocks.WROUGHT_IRON_SLAB,
                    EBlocks.WROUGHT_IRON_GRATE,
                    EBlocks.CUT_WROUGHT_IRON, EBlocks.CUT_WROUGHT_IRON_STAIRS, EBlocks.CUT_WROUGHT_IRON_SLAB,
                    EBlocks.WROUGHT_IRON_BARS
            );
            output.insertAfter(Items.COAL_BLOCK,
                    EBlocks.TIN_BLOCK, EBlocks.CHISELED_TIN,
                    EBlocks.CUT_TIN, EBlocks.CUT_TIN_STAIRS, EBlocks.CUT_TIN_SLAB,
                    EBlocks.TIN_BARS
            );

            output.insertAfter(
                    Items.AMETHYST_BLOCK, EBlocks.AMETHYST_STAIRS, EBlocks.AMETHYST_SLAB, EBlocks.CHISELED_AMETHYST,
                    EBlocks.AMETHYST_BRICKS, EBlocks.AMETHYST_BRICK_STAIRS, EBlocks.AMETHYST_BRICK_SLAB, EBlocks.AMETHYST_PILLAR
//                    EBlocks.URANIUM_BLOCK
            );
            output.insertAfter(
                    Items.LIGHT_WEIGHTED_PRESSURE_PLATE, EBlocks.GOLD_CHAIN,

                    EBlocks.SILVER_BLOCK, EBlocks.SILVER_PILLAR,
                    EBlocks.POLISHED_SILVER, EBlocks.POLISHED_SILVER_STAIRS, EBlocks.POLISHED_SILVER_SLAB,
                    EBlocks.CUT_SILVER, EBlocks.CUT_SILVER_STAIRS, EBlocks.CUT_SILVER_SLAB,
                    EBlocks.SILVER_BARS, EBlocks.SILVER_CHAIN
            );
            output.insertAfter(Items.LAPIS_BLOCK, EBlocks.LAPIS_BRICKS, EBlocks.LAPIS_BRICK_STAIRS, EBlocks.LAPIS_BRICK_SLAB);

            // Planks & Mosaics, Boards & Trims
            output.insertAfter(Items.OAK_PLANKS, EBlocks.OAK_MOSAIC);
            output.insertAfter(Items.OAK_STAIRS, EBlocks.OAK_MOSAIC_STAIRS);
            output.insertAfter(Items.OAK_SLAB, EBlocks.OAK_MOSAIC_SLAB, EBlocks.OAK_BOARDS, EBlocks.OAK_TRIM);

            output.insertAfter(Items.SPRUCE_PLANKS, EBlocks.SPRUCE_MOSAIC);
            output.insertAfter(Items.SPRUCE_STAIRS, EBlocks.SPRUCE_MOSAIC_STAIRS);
            output.insertAfter(Items.SPRUCE_SLAB, EBlocks.SPRUCE_MOSAIC_SLAB, EBlocks.SPRUCE_BOARDS, EBlocks.SPRUCE_TRIM);

            output.insertAfter(Items.BIRCH_PLANKS, EBlocks.BIRCH_MOSAIC);
            output.insertAfter(Items.BIRCH_STAIRS, EBlocks.BIRCH_MOSAIC_STAIRS);
            output.insertAfter(Items.BIRCH_SLAB, EBlocks.BIRCH_MOSAIC_SLAB, EBlocks.BIRCH_BOARDS, EBlocks.BIRCH_TRIM);

            output.insertAfter(Items.JUNGLE_PLANKS, EBlocks.JUNGLE_MOSAIC);
            output.insertAfter(Items.JUNGLE_STAIRS, EBlocks.JUNGLE_MOSAIC_STAIRS);
            output.insertAfter(Items.JUNGLE_SLAB, EBlocks.JUNGLE_MOSAIC_SLAB, EBlocks.JUNGLE_BOARDS, EBlocks.JUNGLE_TRIM);

            output.insertAfter(Items.ACACIA_PLANKS, EBlocks.ACACIA_MOSAIC);
            output.insertAfter(Items.ACACIA_STAIRS, EBlocks.ACACIA_MOSAIC_STAIRS);
            output.insertAfter(Items.ACACIA_SLAB, EBlocks.ACACIA_MOSAIC_SLAB, EBlocks.ACACIA_BOARDS, EBlocks.ACACIA_TRIM);

            output.insertAfter(Items.DARK_OAK_PLANKS, EBlocks.DARK_OAK_MOSAIC);
            output.insertAfter(Items.DARK_OAK_STAIRS, EBlocks.DARK_OAK_MOSAIC_STAIRS);
            output.insertAfter(Items.DARK_OAK_SLAB, EBlocks.DARK_OAK_MOSAIC_SLAB, EBlocks.DARK_OAK_BOARDS, EBlocks.DARK_OAK_TRIM);

            output.insertAfter(Items.MANGROVE_PLANKS, EBlocks.MANGROVE_MOSAIC);
            output.insertAfter(Items.MANGROVE_STAIRS, EBlocks.MANGROVE_MOSAIC_STAIRS);
            output.insertAfter(Items.MANGROVE_SLAB, EBlocks.MANGROVE_MOSAIC_SLAB, EBlocks.MANGROVE_BOARDS, EBlocks.MANGROVE_TRIM);

            output.insertAfter(Items.CHERRY_PLANKS, EBlocks.CHERRY_MOSAIC);
            output.insertAfter(Items.CHERRY_STAIRS, EBlocks.CHERRY_MOSAIC_STAIRS);
            output.insertAfter(Items.CHERRY_SLAB, EBlocks.CHERRY_MOSAIC_SLAB, EBlocks.CHERRY_BOARDS, EBlocks.CHERRY_TRIM);

            output.insertAfter(Items.PALE_OAK_PLANKS, EBlocks.PALE_OAK_MOSAIC);
            output.insertAfter(Items.PALE_OAK_STAIRS, EBlocks.PALE_OAK_MOSAIC_STAIRS);
            output.insertAfter(Items.PALE_OAK_SLAB, EBlocks.PALE_OAK_MOSAIC_SLAB, EBlocks.PALE_OAK_BOARDS, EBlocks.PALE_OAK_TRIM);

            output.insertAfter(Items.CRIMSON_PLANKS, EBlocks.CRIMSON_MOSAIC);
            output.insertAfter(Items.CRIMSON_STAIRS, EBlocks.CRIMSON_MOSAIC_STAIRS);
            output.insertAfter(Items.CRIMSON_SLAB, EBlocks.CRIMSON_MOSAIC_SLAB, EBlocks.CRIMSON_BOARDS, EBlocks.CRIMSON_TRIM);

            output.insertAfter(Items.WARPED_PLANKS, EBlocks.WARPED_MOSAIC);
            output.insertAfter(Items.WARPED_STAIRS, EBlocks.WARPED_MOSAIC_STAIRS);
            output.insertAfter(Items.WARPED_SLAB, EBlocks.WARPED_MOSAIC_SLAB, EBlocks.WARPED_BOARDS, EBlocks.WARPED_TRIM);

            output.insertAfter(Items.BAMBOO_BLOCK, EBlocks.BAMBOO_STEM);
            output.insertAfter(Items.STRIPPED_BAMBOO_BLOCK, EBlocks.STRIPPED_BAMBOO_STEM);
            output.insertAfter(Items.BAMBOO_MOSAIC_SLAB, EBlocks.BAMBOO_BOARDS, EBlocks.BAMBOO_TRIM);

            output.insertAfter(Items.BIRCH_BUTTON,
                    EBlocks.CEDAR_LOG, EBlocks.CEDAR_WOOD,
                    EBlocks.STRIPPED_CEDAR_LOG, EBlocks.STRIPPED_CEDAR_WOOD,
                    EBlocks.CEDAR_PLANKS, EBlocks.CEDAR_MOSAIC,
                    EBlocks.CEDAR_STAIRS, EBlocks.CEDAR_MOSAIC_STAIRS,
                    EBlocks.CEDAR_SLAB, EBlocks.CEDAR_MOSAIC_SLAB,
                    EBlocks.CEDAR_BOARDS, EBlocks.CEDAR_TRIM,
                    EBlocks.CEDAR_FENCE, EBlocks.CEDAR_FENCE_GATE,
                    EBlocks.CEDAR_DOOR, EBlocks.CEDAR_TRAPDOOR,
                    EBlocks.CEDAR_PRESSURE_PLATE, EBlocks.CEDAR_BUTTON
            );

            output.insertAfter(Items.JUNGLE_BUTTON,
                    EBlocks.MAHOGANY_LOG, EBlocks.MAHOGANY_WOOD,
                    EBlocks.STRIPPED_MAHOGANY_LOG, EBlocks.STRIPPED_MAHOGANY_WOOD,
                    EBlocks.MAHOGANY_PLANKS, EBlocks.MAHOGANY_MOSAIC,
                    EBlocks.MAHOGANY_STAIRS, EBlocks.MAHOGANY_MOSAIC_STAIRS,
                    EBlocks.MAHOGANY_SLAB, EBlocks.MAHOGANY_MOSAIC_SLAB,
                    EBlocks.MAHOGANY_BOARDS, EBlocks.MAHOGANY_TRIM,
                    EBlocks.MAHOGANY_FENCE, EBlocks.MAHOGANY_FENCE_GATE,
                    EBlocks.MAHOGANY_DOOR, EBlocks.MAHOGANY_TRAPDOOR,
                    EBlocks.MAHOGANY_PRESSURE_PLATE, EBlocks.MAHOGANY_BUTTON
            );

            output.insertAfter(Blocks.POPLAR_BUTTON,
                    EBlocks.AZALEA_LOG, EBlocks.AZALEA_WOOD,
                    EBlocks.STRIPPED_AZALEA_LOG, EBlocks.STRIPPED_AZALEA_WOOD,
                    EBlocks.AZALEA_PLANKS, EBlocks.AZALEA_MOSAIC,
                    EBlocks.AZALEA_STAIRS, EBlocks.AZALEA_MOSAIC_STAIRS,
                    EBlocks.AZALEA_SLAB, EBlocks.AZALEA_MOSAIC_SLAB,
                    EBlocks.AZALEA_BOARDS, EBlocks.AZALEA_TRIM,
                    EBlocks.AZALEA_FENCE, EBlocks.AZALEA_FENCE_GATE,
                    EBlocks.AZALEA_DOOR, EBlocks.AZALEA_TRAPDOOR,
                    EBlocks.AZALEA_PRESSURE_PLATE, EBlocks.AZALEA_BUTTON
            );
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS).register(output -> {
            output.insertAfter(Items.TINTED_GLASS, EBlocks.SOULGLASS);

            Block anchor = Blocks.DYED_TERRACOTTA.pink();
            List<Map.Entry<Block, ColorCollection<Block>>> groups = List.of(
                    Map.entry(EBlocks.TERRACOTTA_STAIRS, EBlocks.DYED_TERRACOTTA_STAIRS),
                    Map.entry(EBlocks.TERRACOTTA_SLAB, EBlocks.DYED_TERRACOTTA_SLAB),
                    Map.entry(EBlocks.TERRACOTTA_BRICKS, EBlocks.DYED_TERRACOTTA_BRICKS),
                    Map.entry(EBlocks.TERRACOTTA_BRICK_STAIRS, EBlocks.DYED_TERRACOTTA_BRICK_STAIRS),
                    Map.entry(EBlocks.TERRACOTTA_BRICK_SLAB, EBlocks.DYED_TERRACOTTA_BRICK_SLAB),
                    Map.entry(EBlocks.TERRACOTTA_BRICK_TILES, EBlocks.DYED_TERRACOTTA_BRICK_TILES),
                    Map.entry(EBlocks.TERRACOTTA_BRICK_TILE_STAIRS, EBlocks.DYED_TERRACOTTA_BRICK_TILE_STAIRS),
                    Map.entry(EBlocks.TERRACOTTA_BRICK_TILE_SLAB, EBlocks.DYED_TERRACOTTA_BRICK_TILE_SLAB));

            for (Map.Entry<Block, ColorCollection<Block>> group : groups) {
                output.insertAfter(anchor, group.getKey());
                anchor = group.getKey();
                for (DyeColor color : COLOR_ORDER) {
                    Block dyed = group.getValue().pick(color);
                    output.insertAfter(anchor, dyed);
                    anchor = dyed;
                }
            }
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
            output.insertAfter(Items.BIRCH_SHELF, EBlocks.CEDAR_SHELF);
            output.insertAfter(Items.BIRCH_HANGING_SIGN, EItems.CEDAR_SIGN, EItems.CEDAR_HANGING_SIGN);

            output.insertAfter(Items.JUNGLE_SHELF, EBlocks.MAHOGANY_SHELF);
            output.insertAfter(Items.JUNGLE_HANGING_SIGN, EItems.MAHOGANY_SIGN, EItems.MAHOGANY_HANGING_SIGN);

            output.insertAfter(Items.PALE_OAK_SHELF, EBlocks.AZALEA_SHELF);
            output.insertAfter(Items.PALE_OAK_HANGING_SIGN, EItems.AZALEA_SIGN, EItems.AZALEA_HANGING_SIGN);

            output.insertAfter(Items.SOUL_LANTERN, EBlocks.REDSTONE_LANTERN);
            output.insertAfter(Items.IRON_CHAIN, EBlocks.GOLD_CHAIN, EBlocks.SILVER_CHAIN);

            output.insertAfter(Items.TINTED_GLASS, EBlocks.SOULGLASS);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.insertAfter(Items.DIAMOND_HOE,
                    EItems.BRONZE_SHOVEL, EItems.BRONZE_PICKAXE, EItems.BRONZE_AXE, EItems.BRONZE_HOE,
                    EItems.ELECTRUM_SHOVEL, EItems.ELECTRUM_PICKAXE, EItems.ELECTRUM_AXE, EItems.ELECTRUM_HOE,
                    EItems.SOULSTEEL_SHOVEL, EItems.SOULSTEEL_PICKAXE, EItems.SOULSTEEL_AXE, EItems.SOULSTEEL_HOE
            );

            output.insertAfter(Items.BIRCH_CHEST_BOAT, EItems.CEDAR_BOAT, EItems.CEDAR_CHEST_BOAT);
            output.insertAfter(Items.JUNGLE_CHEST_BOAT, EItems.MAHOGANY_BOAT, EItems.MAHOGANY_CHEST_BOAT);
            output.insertAfter(Items.PALE_OAK_BOAT, EItems.AZALEA_BOAT, EItems.AZALEA_CHEST_BOAT);

            output.insertAfter(Items.MUSIC_DISC_CAT, EItems.MUSIC_DISC_FOX);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.insertAfter(Items.DIAMOND_SWORD, EItems.BRONZE_SWORD, EItems.ELECTRUM_SWORD, EItems.SOULSTEEL_SWORD);
            output.insertAfter(Items.DIAMOND_SPEAR, EItems.BRONZE_SPEAR, EItems.ELECTRUM_SPEAR, EItems.SOULSTEEL_SPEAR);
            output.insertAfter(Items.DIAMOND_AXE, EItems.BRONZE_AXE, EItems.ELECTRUM_AXE, EItems.SOULSTEEL_AXE);
            output.insertAfter(Items.DIAMOND_BOOTS,
                    EItems.BRONZE_HELMET, EItems.BRONZE_CHESTPLATE, EItems.BRONZE_LEGGINGS, EItems.BRONZE_BOOTS,
                    EItems.ELECTRUM_HELMET, EItems.ELECTRUM_CHESTPLATE, EItems.ELECTRUM_LEGGINGS, EItems.ELECTRUM_BOOTS,
                    EItems.SOULSTEEL_HELMET, EItems.SOULSTEEL_CHESTPLATE, EItems.SOULSTEEL_LEGGINGS, EItems.SOULSTEEL_BOOTS
            );

//            output.insertAfter(Items.SHIELD, EItems.REINFORCED_SHIELD, EItems.QUICK_SHIELD);

            output.insertBefore(Items.WIND_CHARGE, Items.FIRE_CHARGE);

            output.insertAfter(Items.TURTLE_HELMET, EItems.VANGUARD_MASK);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertAfter(Items.RAW_COPPER, EItems.RAW_TIN);
            output.insertAfter(Items.RAW_GOLD, EItems.RAW_SILVER);
            output.insertAfter(Items.COPPER_NUGGET, EItems.TIN_NUGGET);
            output.insertAfter(Items.GOLD_NUGGET, EItems.SILVER_NUGGET);
            output.insertAfter(Items.COPPER_INGOT, EItems.TIN_INGOT);
            output.insertAfter(Items.GOLD_INGOT, EItems.SILVER_INGOT, EItems.BRONZE_ALLOY, EItems.ELECTRUM_ALLOY, EItems.SOULSTEEL_ALLOY);
            output.insertAfter(Items.QUARTZ, EItems.SOULGLASS_SHARD);
//            output.insertAfter(Items.AMETHYST_SHARD, EItems.URANIUM, EItems.URANIUM_SHARD);

            output.insertBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                    EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE,
                    EItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE,
                    EItems.SOULSTEEL_UPGRADE_SMITHING_TEMPLATE
            );
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
            output.insertAfter(Items.COOKED_SALMON, EItems.RAW_PERCH, EItems.COOKED_PERCH, EItems.SHINING_PERCH);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
//            output.insertAfter(Items.WARDEN_SPAWN_EGG, EItems.WEREWOLF_SPAWN_EGG);
            output.insertBefore(Items.HUSK_SPAWN_EGG, EItems.GRAVEKEEPER_SPAWN_EGG);
            output.insertBefore(Items.WARDEN_SPAWN_EGG, EItems.VANGUARD_SPAWN_EGG);
            output.insertAfter(Items.SLIME_SPAWN_EGG, EItems.SUBSLIME_SPAWN_EGG);
            output.insertBefore(Items.SILVERFISH_SPAWN_EGG, EItems.SWEEPER_SPAWN_EGG);
            output.insertAfter(Items.IRON_GOLEM_SPAWN_EGG, EItems.MUD_GOLEM_SPAWN_EGG);
            output.insertAfter(Items.SKELETON_HORSE_SPAWN_EGG, EItems.LOST_SOUL_SPAWN_EGG);
            output.insertAfter(Items.SLIME_SPAWN_EGG, EItems.TROLL_SPAWN_EGG);
            output.insertAfter(Items.WARDEN_SPAWN_EGG, EItems.WHISTLER_SPAWN_EGG);

            output.insertBefore(Items.PUFFERFISH_SPAWN_EGG, EItems.PERCH_SPAWN_EGG);

            output.insertBefore(Items.TRIAL_SPAWNER, EBlocks.DUNGEON_SPAWNER);
        });


    }

    private static final List<DyeColor> COLOR_ORDER = List.of(
            DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK,
            DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW,
            DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE,
            DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK);
}
