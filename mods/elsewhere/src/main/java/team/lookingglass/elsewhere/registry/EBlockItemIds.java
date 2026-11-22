package team.lookingglass.elsewhere.registry;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.ColorCollection;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBlockItemIds {

    // Quartz Brick Fill
    BlockItemId QUARTZ_BRICK_STAIRS = register("quartz_brick_stairs");
    BlockItemId QUARTZ_BRICK_SLAB = register("quartz_brick_slab");

    // Amethyst-Obsidian Set
    BlockItemId AMETHYST_STAIRS = register("amethyst_stairs");
    BlockItemId AMETHYST_SLAB = register("amethyst_slab");
    BlockItemId AMETHYST_BRICKS = register("amethyst_bricks");
    BlockItemId AMETHYST_BRICK_STAIRS = register("amethyst_brick_stairs");
    BlockItemId AMETHYST_BRICK_SLAB = register("amethyst_brick_slab");
    BlockItemId AMETHYST_PILLAR = register("amethyst_pillar");
    BlockItemId CHISELED_AMETHYST = register("chiseled_amethyst");
    BlockItemId OBSIDIAN_STAIRS = register("obsidian_stairs");
    BlockItemId OBSIDIAN_SLAB = register("obsidian_slab");
    BlockItemId OBSIDIAN_BRICKS = register("obsidian_bricks");
    BlockItemId OBSIDIAN_BRICK_STAIRS = register("obsidian_brick_stairs");
    BlockItemId OBSIDIAN_BRICK_SLAB = register("obsidian_brick_slab");
    BlockItemId OBSIDIAN_PILLAR = register("obsidian_pillar");
    BlockItemId CHISELED_OBSIDIAN = register("chiseled_obsidian");

    // Stone
    BlockItemId STONE_WALL = register("stone_wall");
    BlockItemId SMOOTH_STONE_STAIRS = register("smooth_stone_stairs");
    BlockItemId SMOOTH_STONE_WALL = register("smooth_stone_wall");
    BlockItemId POLISHED_STONE = register("polished_stone");
    BlockItemId POLISHED_STONE_STAIRS = register("polished_stone_stairs");
    BlockItemId POLISHED_STONE_SLAB = register("polished_stone_slab");
    BlockItemId POLISHED_STONE_WALL = register("polished_stone_wall");
    BlockItemId STONE_TILES = register("stone_tiles");
    BlockItemId STONE_TILE_STAIRS = register("stone_tile_stairs");
    BlockItemId STONE_TILE_SLAB = register("stone_tile_slab");
    BlockItemId STONE_TILE_WALL = register("stone_tile_wall");
    BlockItemId MOSSY_STONE_TILES = register("mossy_stone_tiles");
    BlockItemId MOSSY_STONE_TILE_STAIRS = register("mossy_stone_tile_stairs");
    BlockItemId MOSSY_STONE_TILE_SLAB = register("mossy_stone_tile_slab");
    BlockItemId MOSSY_STONE_TILE_WALL = register("mossy_stone_tile_wall");
    BlockItemId COBBLESTONE_BRICKS = register("cobblestone_bricks");
    BlockItemId COBBLESTONE_BRICK_STAIRS = register("cobblestone_brick_stairs");
    BlockItemId COBBLESTONE_BRICK_SLAB = register("cobblestone_brick_slab");
    BlockItemId COBBLESTONE_BRICK_WALL = register("cobblestone_brick_wall");
    BlockItemId MOSSY_COBBLESTONE_BRICKS = register("mossy_cobblestone_bricks");
    BlockItemId MOSSY_COBBLESTONE_BRICK_STAIRS = register("mossy_cobblestone_brick_stairs");
    BlockItemId MOSSY_COBBLESTONE_BRICK_SLAB = register("mossy_cobblestone_brick_slab");
    BlockItemId MOSSY_COBBLESTONE_BRICK_WALL = register("mossy_cobblestone_brick_wall");
    BlockItemId CRACKED_COBBLESTONE_BRICKS = register("cracked_cobblestone_bricks");
    BlockItemId CHISELED_COBBLESTONE_BRICKS = register("chiseled_cobblestone_bricks");
    BlockItemId CHISELED_STONE = register("chiseled_stone");
    BlockItemId STONE_PILLAR = register("stone_pillar");
    BlockItemId DEEPSLATE_STAIRS = register("deepslate_stairs");
    BlockItemId DEEPSLATE_SLAB = register("deepslate_slab");
    BlockItemId DEEPSLATE_WALL = register("deepslate_wall");
    BlockItemId CHISELED_DEEPSLATE_BRICKS = register("chiseled_deepslate_bricks");
    BlockItemId DEEPSLATE_PILLAR = register("deepslate_pillar");
    BlockItemId TUFF_PILLAR = register("tuff_pillar");
    BlockItemId BLACKSTONE_PILLAR = register("blackstone_pillar");
    BlockItemId POLISHED_ANDESITE_WALL = register("polished_andesite_wall");
    BlockItemId ANDESITE_BRICKS = register("andesite_bricks");
    BlockItemId ANDESITE_BRICK_STAIRS = register("andesite_brick_stairs");
    BlockItemId ANDESITE_BRICK_SLAB = register("andesite_brick_slab");
    BlockItemId ANDESITE_BRICK_WALL = register("andesite_brick_wall");
    BlockItemId CHISELED_ANDESITE = register("chiseled_andesite");
    BlockItemId ANDESITE_PILLAR = register("andesite_pillar");
    BlockItemId POLISHED_GRANITE_WALL = register("polished_granite_wall");
    BlockItemId GRANITE_BRICKS = register("granite_bricks");
    BlockItemId GRANITE_BRICK_STAIRS = register("granite_brick_stairs");
    BlockItemId GRANITE_BRICK_SLAB = register("granite_brick_slab");
    BlockItemId GRANITE_BRICK_WALL = register("granite_brick_wall");
    BlockItemId CHISELED_GRANITE = register("chiseled_granite");
    BlockItemId POLISHED_DIORITE_WALL = register("polished_diorite_wall");
    BlockItemId DIORITE_BRICKS = register("diorite_bricks");
    BlockItemId DIORITE_BRICK_STAIRS = register("diorite_brick_stairs");
    BlockItemId DIORITE_BRICK_SLAB = register("diorite_brick_slab");
    BlockItemId DIORITE_BRICK_WALL = register("diorite_brick_wall");
    BlockItemId CHISELED_DIORITE = register("chiseled_diorite");
    BlockItemId BEJEWELED_CALCITE = register("bejeweled_calcite");
    BlockItemId CHISELED_POLISHED_CALCITE = register("chiseled_polished_calcite");
    BlockItemId CHISELED_POLISHED_CALCITE_BRICKS = register("chiseled_polished_calcite_bricks");
    BlockItemId DRIPSTONE_STAIRS = register("dripstone_stairs");
    BlockItemId DRIPSTONE_SLAB = register("dripstone_slab");
    BlockItemId DRIPSTONE_WALL = register("dripstone_wall");
    BlockItemId POLISHED_DRIPSTONE = register("polished_dripstone");
    BlockItemId POLISHED_DRIPSTONE_STAIRS = register("polished_dripstone_stairs");
    BlockItemId POLISHED_DRIPSTONE_SLAB = register("polished_dripstone_slab");
    BlockItemId POLISHED_DRIPSTONE_WALL = register("polished_dripstone_wall");
    BlockItemId DRIPSTONE_TILES = register("dripstone_tiles");
    BlockItemId DRIPSTONE_TILE_STAIRS = register("dripstone_tile_stairs");
    BlockItemId DRIPSTONE_TILE_SLAB = register("dripstone_tile_slab");
    BlockItemId DRIPSTONE_TILE_WALL = register("dripstone_tile_wall");
    BlockItemId CHISELED_DRIPSTONE_TILES = register("chiseled_dripstone_tiles");
    BlockItemId DRIPSTONE_PILLAR = register("dripstone_pillar");

    // Sandstone
    BlockItemId POLISHED_SANDSTONE = register("polished_sandstone");
    BlockItemId POLISHED_SANDSTONE_STAIRS = register("polished_sandstone_stairs");
    BlockItemId POLISHED_SANDSTONE_SLAB = register("polished_sandstone_slab");
    BlockItemId POLISHED_SANDSTONE_WALL = register("polished_sandstone_wall");
    BlockItemId SMOOTH_SANDSTONE_WALL = register("smooth_sandstone_wall");
    BlockItemId CUT_SANDSTONE_STAIRS = register("cut_sandstone_stairs");
    BlockItemId CUT_SANDSTONE_WALL = register("cut_sandstone_wall");
    BlockItemId POLISHED_RED_SANDSTONE = register("polished_red_sandstone");
    BlockItemId POLISHED_RED_SANDSTONE_STAIRS = register("polished_red_sandstone_stairs");
    BlockItemId POLISHED_RED_SANDSTONE_SLAB = register("polished_red_sandstone_slab");
    BlockItemId POLISHED_RED_SANDSTONE_WALL = register("polished_red_sandstone_wall");
    BlockItemId SMOOTH_RED_SANDSTONE_WALL = register("smooth_red_sandstone_wall");
    BlockItemId CUT_RED_SANDSTONE_STAIRS = register("cut_red_sandstone_stairs");
    BlockItemId CUT_RED_SANDSTONE_WALL = register("cut_red_sandstone_wall");

    // Prismarine
    BlockItemId POLISHED_PRISMARINE = register("polished_prismarine");
    BlockItemId POLISHED_PRISMARINE_STAIRS = register("polished_prismarine_stairs");
    BlockItemId POLISHED_PRISMARINE_SLAB = register("polished_prismarine_slab");
    BlockItemId POLISHED_PRISMARINE_WALL = register("polished_prismarine_wall");
    BlockItemId PRISMARINE_BRICK_WALL = register("prismarine_brick_wall");
    BlockItemId CHISELED_PRISMARINE = register("chiseled_prismarine");
    BlockItemId PRISMARINE_PILLAR = register("prismarine_pillar");
    BlockItemId DARK_PRISMARINE_WALL = register("dark_prismarine_wall");
    BlockItemId POLISHED_DARK_PRISMARINE = register("polished_dark_prismarine");
    BlockItemId POLISHED_DARK_PRISMARINE_STAIRS = register("polished_dark_prismarine_stairs");
    BlockItemId POLISHED_DARK_PRISMARINE_SLAB = register("polished_dark_prismarine_slab");
    BlockItemId POLISHED_DARK_PRISMARINE_WALL = register("polished_dark_prismarine_wall");
    BlockItemId DARK_PRISMARINE_SCALES = register("dark_prismarine_scales");
    BlockItemId DARK_PRISMARINE_SCALE_STAIRS = register("dark_prismarine_scale_stairs");
    BlockItemId DARK_PRISMARINE_SCALE_SLAB = register("dark_prismarine_scale_slab");
    BlockItemId DARK_PRISMARINE_SCALE_WALL = register("dark_prismarine_scale_wall");
    BlockItemId CHISELED_DARK_PRISMARINE = register("chiseled_dark_prismarine");

    // Basalt & Smooth Basalt
    BlockItemId BASALT_SLAB = register("basalt_slab");
    BlockItemId POLISHED_BASALT_SLAB = register("polished_basalt_slab");
    BlockItemId SMOOTH_BASALT_STAIRS = register("smooth_basalt_stairs");
    BlockItemId SMOOTH_BASALT_SLAB = register("smooth_basalt_slab");
    BlockItemId SMOOTH_BASALT_WALL = register("smooth_basalt_wall");
    BlockItemId POLISHED_SMOOTH_BASALT = register("polished_smooth_basalt");
    BlockItemId POLISHED_SMOOTH_BASALT_STAIRS = register("polished_smooth_basalt_stairs");
    BlockItemId POLISHED_SMOOTH_BASALT_SLAB = register("polished_smooth_basalt_slab");
    BlockItemId POLISHED_SMOOTH_BASALT_WALL = register("polished_smooth_basalt_wall");
    BlockItemId SMOOTH_BASALT_BRICKS = register("smooth_basalt_bricks");
    BlockItemId SMOOTH_BASALT_BRICK_STAIRS = register("smooth_basalt_brick_stairs");
    BlockItemId SMOOTH_BASALT_BRICK_SLAB = register("smooth_basalt_brick_slab");
    BlockItemId SMOOTH_BASALT_BRICK_WALL = register("smooth_basalt_brick_wall");
    BlockItemId SMOOTH_BASALT_TILES = register("smooth_basalt_tiles");
    BlockItemId SMOOTH_BASALT_TILE_STAIRS = register("smooth_basalt_tile_stairs");
    BlockItemId SMOOTH_BASALT_TILE_SLAB = register("smooth_basalt_tile_slab");
    BlockItemId SMOOTH_BASALT_TILE_WALL = register("smooth_basalt_tile_wall");

    // End Stone
    BlockItemId POLISHED_END_STONE = register("polished_end_stone");
    BlockItemId END_STONE_STAIRS = register("end_stone_stairs");
    BlockItemId END_STONE_SLAB = register("end_stone_slab");
    BlockItemId END_STONE_WALL = register("end_stone_wall");
    BlockItemId POLISHED_END_STONE_STAIRS = register("polished_end_stone_stairs");
    BlockItemId POLISHED_END_STONE_SLAB = register("polished_end_stone_slab");
    BlockItemId POLISHED_END_STONE_WALL = register("polished_end_stone_wall");
    BlockItemId CHISELED_END_STONE = register("chiseled_end_stone");
    BlockItemId END_STONE_PILLAR = register("end_stone_pillar");

    // Sodalite
    BlockItemId SODALITE = register("sodalite");
    BlockItemId SODALITE_STAIRS = register("sodalite_stairs");
    BlockItemId SODALITE_SLAB = register("sodalite_slab");
    BlockItemId SODALITE_WALL = register("sodalite_wall");
    BlockItemId POLISHED_SODALITE = register("polished_sodalite");
    BlockItemId POLISHED_SODALITE_STAIRS = register("polished_sodalite_stairs");
    BlockItemId POLISHED_SODALITE_SLAB = register("polished_sodalite_slab");
    BlockItemId POLISHED_SODALITE_WALL = register("polished_sodalite_wall");
    BlockItemId SODALITE_BRICKS = register("sodalite_bricks");
    BlockItemId SODALITE_BRICK_STAIRS = register("sodalite_brick_stairs");
    BlockItemId SODALITE_BRICK_SLAB = register("sodalite_brick_slab");
    BlockItemId SODALITE_BRICK_WALL = register("sodalite_brick_wall");
    BlockItemId CHISELED_SODALITE = register("chiseled_sodalite");
    BlockItemId CHISELED_SODALITE_BRICKS = register("chiseled_sodalite_bricks");
    BlockItemId SODALITE_PILLAR = register("sodalite_pillar");

    // Shale
    BlockItemId SHALE = register("shale");
    BlockItemId SHALE_STAIRS = register("shale_stairs");
    BlockItemId SHALE_SLAB = register("shale_slab");
    BlockItemId SHALE_WALL = register("shale_wall");
    BlockItemId POLISHED_SHALE = register("polished_shale");
    BlockItemId POLISHED_SHALE_STAIRS = register("polished_shale_stairs");
    BlockItemId POLISHED_SHALE_SLAB = register("polished_shale_slab");
    BlockItemId POLISHED_SHALE_WALL = register("polished_shale_wall");
    BlockItemId SHALE_BRICKS = register("shale_bricks");
    BlockItemId SHALE_BRICK_STAIRS = register("shale_brick_stairs");
    BlockItemId SHALE_BRICK_SLAB = register("shale_brick_slab");
    BlockItemId SHALE_BRICK_WALL = register("shale_brick_wall");
    BlockItemId CHISELED_SHALE = register("chiseled_shale");
    BlockItemId CHISELED_SHALE_BRICKS = register("chiseled_shale_bricks");
    BlockItemId SHALE_PILLAR = register("shale_pillar");

    BlockItemId SILVER_ORE = register("silver_ore");
    BlockItemId DEEPSLATE_SILVER_ORE = register("deepslate_silver_ore");

    BlockItemId SILVER_BLOCK = register("silver_block");
    BlockItemId RAW_SILVER_BLOCK = register("raw_silver_block");
    BlockItemId SOULSTEEL_BLOCK = register("soulsteel_block");
    BlockItemId ELECTRUM_BLOCK = register("electrum_block");
    BlockItemId URANIUM_BLOCK = register("uranium_block");

    BlockItemId SULFUR_PILLAR = register("sulfur_pillar");
    BlockItemId CINNABAR_PILLAR = register("cinnabar_pillar");
    BlockItemId CINNABAR_REDSTONE_ORE = register("cinnabar_redstone_ore");
    BlockItemId CINNABAR_SILVER_ORE = register("cinnabar_silver_ore");

    // Netherrack
    BlockItemId NETHERRACK_STAIRS = register("netherrack_stairs");
    BlockItemId NETHERRACK_SLAB = register("netherrack_slab");
    BlockItemId NETHERRACK_WALL = register("netherrack_wall");
    BlockItemId POLISHED_NETHERRACK = register("polished_netherrack");
    BlockItemId POLISHED_NETHERRACK_STAIRS = register("polished_netherrack_stairs");
    BlockItemId POLISHED_NETHERRACK_SLAB = register("polished_netherrack_slab");
    BlockItemId POLISHED_NETHERRACK_WALL = register("polished_netherrack_wall");
    BlockItemId NETHERRACK_BRICKS = register("netherrack_bricks");
    BlockItemId NETHERRACK_BRICK_STAIRS = register("netherrack_brick_stairs");
    BlockItemId NETHERRACK_BRICK_SLAB = register("netherrack_brick_slab");
    BlockItemId NETHERRACK_BRICK_WALL = register("netherrack_brick_wall");
    BlockItemId CHISELED_NETHERRACK = register("chiseled_netherrack");
    BlockItemId CHISELED_NETHERRACK_BRICKS = register("chiseled_netherrack_bricks");
    BlockItemId NETHERRACK_PILLAR = register("netherrack_pillar");

    // Orpiment
    BlockItemId ORPIMENT = register("orpiment");
    BlockItemId ORPIMENT_STAIRS = register("orpiment_stairs");
    BlockItemId ORPIMENT_SLAB = register("orpiment_slab");
    BlockItemId ORPIMENT_WALL = register("orpiment_wall");
    BlockItemId POLISHED_ORPIMENT = register("polished_orpiment");
    BlockItemId POLISHED_ORPIMENT_STAIRS = register("polished_orpiment_stairs");
    BlockItemId POLISHED_ORPIMENT_SLAB = register("polished_orpiment_slab");
    BlockItemId POLISHED_ORPIMENT_WALL = register("polished_orpiment_wall");
    BlockItemId ORPIMENT_BRICKS = register("orpiment_bricks");
    BlockItemId ORPIMENT_BRICK_STAIRS = register("orpiment_brick_stairs");
    BlockItemId ORPIMENT_BRICK_SLAB = register("orpiment_brick_slab");
    BlockItemId ORPIMENT_BRICK_WALL = register("orpiment_brick_wall");
    BlockItemId CHISELED_ORPIMENT = register("chiseled_orpiment");
    BlockItemId ORPIMENT_PILLAR = register("orpiment_pillar");

    // Soul Sand Valley
    BlockItemId SOUL_STONE = register("soul_stone");
    BlockItemId SOUL_STONE_STAIRS = register("soul_stone_stairs");
    BlockItemId SOUL_STONE_SLAB = register("soul_stone_slab");
    BlockItemId SOUL_STONE_WALL = register("soul_stone_wall");
    BlockItemId SMOOTH_SOUL_STONE = register("smooth_soul_stone");
    BlockItemId SMOOTH_SOUL_STONE_STAIRS = register("smooth_soul_stone_stairs");
    BlockItemId SMOOTH_SOUL_STONE_SLAB = register("smooth_soul_stone_slab");
    BlockItemId SMOOTH_SOUL_STONE_WALL = register("smooth_soul_stone_wall");
    BlockItemId CUT_SOUL_STONE = register("cut_soul_stone");
    BlockItemId CUT_SOUL_STONE_STAIRS = register("cut_soul_stone_stairs");
    BlockItemId CUT_SOUL_STONE_SLAB = register("cut_soul_stone_slab");
    BlockItemId CUT_SOUL_STONE_WALL = register("cut_soul_stone_wall");
    BlockItemId POLISHED_SOUL_STONE = register("polished_soul_stone");
    BlockItemId POLISHED_SOUL_STONE_STAIRS = register("polished_soul_stone_stairs");
    BlockItemId POLISHED_SOUL_STONE_SLAB = register("polished_soul_stone_slab");
    BlockItemId POLISHED_SOUL_STONE_WALL = register("polished_soul_stone_wall");
    BlockItemId CHISELED_SOUL_STONE = register("chiseled_soul_stone");

    BlockItemId SOUL_SILVER_ORE = register("soul_silver_ore");
    BlockItemId SOUL_QUARTZ_ORE = register("soul_quartz_ore");

    BlockItemId SHORT_SOUL_SPROUT = register("short_soul_sprout");
    BlockItemId TALL_SOUL_SPROUT = register("tall_soul_sprout");
    BlockItemId SOULGLASS_CLUSTER = register("soulglass_cluster");
    BlockItemId RAW_SOULGLASS = register("raw_soulglass");
    BlockItemId SOULGLASS = register("soulglass");

    BlockItemId CHISELED_PURPUR = register("chiseled_purpur");
    BlockItemId AMETHYST_SPIRE = register("amethyst_spire");
    BlockItemId PACKED_MUD_STAIRS = register("packed_mud_stairs");
    BlockItemId PACKED_MUD_SLAB = register("packed_mud_slab");
    BlockItemId CHISELED_MUD_BRICKS = register("chiseled_mud_bricks");
    BlockItemId MUD_BRICK_PILLAR = register("mud_brick_pillar");

    // Wood Blocks
    BlockItemId OAK_MOSAIC = register("oak_mosaic");
    BlockItemId OAK_MOSAIC_STAIRS = register("oak_mosaic_stairs");
    BlockItemId OAK_MOSAIC_SLAB = register("oak_mosaic_slab");
    BlockItemId OAK_BOARDS = register("oak_boards");
    BlockItemId OAK_TRIM = register("oak_trim");
    BlockItemId DARK_OAK_MOSAIC = register("dark_oak_mosaic");
    BlockItemId DARK_OAK_MOSAIC_STAIRS = register("dark_oak_mosaic_stairs");
    BlockItemId DARK_OAK_MOSAIC_SLAB = register("dark_oak_mosaic_slab");
    BlockItemId DARK_OAK_BOARDS = register("dark_oak_boards");
    BlockItemId DARK_OAK_TRIM = register("dark_oak_trim");
    BlockItemId PALE_OAK_MOSAIC = register("pale_oak_mosaic");
    BlockItemId PALE_OAK_MOSAIC_STAIRS = register("pale_oak_mosaic_stairs");
    BlockItemId PALE_OAK_MOSAIC_SLAB = register("pale_oak_mosaic_slab");
    BlockItemId PALE_OAK_BOARDS = register("pale_oak_boards");
    BlockItemId PALE_OAK_TRIM = register("pale_oak_trim");
    BlockItemId BIRCH_MOSAIC = register("birch_mosaic");
    BlockItemId BIRCH_MOSAIC_STAIRS = register("birch_mosaic_stairs");
    BlockItemId BIRCH_MOSAIC_SLAB = register("birch_mosaic_slab");
    BlockItemId BIRCH_BOARDS = register("birch_boards");
    BlockItemId BIRCH_TRIM = register("birch_trim");
    BlockItemId SPRUCE_MOSAIC = register("spruce_mosaic");
    BlockItemId SPRUCE_MOSAIC_STAIRS = register("spruce_mosaic_stairs");
    BlockItemId SPRUCE_MOSAIC_SLAB = register("spruce_mosaic_slab");
    BlockItemId SPRUCE_BOARDS = register("spruce_boards");
    BlockItemId SPRUCE_TRIM = register("spruce_trim");
    BlockItemId JUNGLE_MOSAIC = register("jungle_mosaic");
    BlockItemId JUNGLE_MOSAIC_STAIRS = register("jungle_mosaic_stairs");
    BlockItemId JUNGLE_MOSAIC_SLAB = register("jungle_mosaic_slab");
    BlockItemId JUNGLE_BOARDS = register("jungle_boards");
    BlockItemId JUNGLE_TRIM = register("jungle_trim");
    BlockItemId ACACIA_MOSAIC = register("acacia_mosaic");
    BlockItemId ACACIA_MOSAIC_STAIRS = register("acacia_mosaic_stairs");
    BlockItemId ACACIA_MOSAIC_SLAB = register("acacia_mosaic_slab");
    BlockItemId ACACIA_BOARDS = register("acacia_boards");
    BlockItemId ACACIA_TRIM = register("acacia_trim");
    BlockItemId CHERRY_MOSAIC = register("cherry_mosaic");
    BlockItemId CHERRY_MOSAIC_STAIRS = register("cherry_mosaic_stairs");
    BlockItemId CHERRY_MOSAIC_SLAB = register("cherry_mosaic_slab");
    BlockItemId CHERRY_BOARDS = register("cherry_boards");
    BlockItemId CHERRY_TRIM = register("cherry_trim");
    BlockItemId MANGROVE_MOSAIC = register("mangrove_mosaic");
    BlockItemId MANGROVE_MOSAIC_STAIRS = register("mangrove_mosaic_stairs");
    BlockItemId MANGROVE_MOSAIC_SLAB = register("mangrove_mosaic_slab");
    BlockItemId MANGROVE_BOARDS = register("mangrove_boards");
    BlockItemId MANGROVE_TRIM = register("mangrove_trim");
    BlockItemId POPLAR_MOSAIC = register("poplar_mosaic");
    BlockItemId POPLAR_MOSAIC_STAIRS = register("poplar_mosaic_stairs");
    BlockItemId POPLAR_MOSAIC_SLAB = register("poplar_mosaic_slab");
    BlockItemId POPLAR_BOARDS = register("poplar_boards");
    BlockItemId POPLAR_TRIM = register("poplar_trim");
    BlockItemId CRIMSON_MOSAIC = register("crimson_mosaic");
    BlockItemId CRIMSON_MOSAIC_STAIRS = register("crimson_mosaic_stairs");
    BlockItemId CRIMSON_MOSAIC_SLAB = register("crimson_mosaic_slab");
    BlockItemId CRIMSON_BOARDS = register("crimson_boards");
    BlockItemId CRIMSON_TRIM = register("crimson_trim");
    BlockItemId WARPED_MOSAIC = register("warped_mosaic");
    BlockItemId WARPED_MOSAIC_STAIRS = register("warped_mosaic_stairs");
    BlockItemId WARPED_MOSAIC_SLAB = register("warped_mosaic_slab");
    BlockItemId WARPED_BOARDS = register("warped_boards");
    BlockItemId WARPED_TRIM = register("warped_trim");
    BlockItemId BAMBOO_STEM = register("bamboo_stem");
    BlockItemId STRIPPED_BAMBOO_STEM = register("stripped_bamboo_stem");
    BlockItemId BAMBOO_BOARDS = register("bamboo_boards");
    BlockItemId BAMBOO_TRIM = register("bamboo_trim");

    // Hibiscus
    BlockItemId RED_HIBISCUS = register("red_hibiscus");
    BlockItemId ORANGE_HIBISCUS = register("orange_hibiscus");
    BlockItemId YELLOW_HIBISCUS = register("yellow_hibiscus");
    BlockItemId BLUE_HIBISCUS = register("blue_hibiscus");
    BlockItemId PURPLE_HIBISCUS = register("purple_hibiscus");
    BlockItemId PINK_HIBISCUS = register("pink_hibiscus");
    BlockItemId WHITE_HIBISCUS = register("white_hibiscus");

    // Natural blocks
    BlockItemId BLUEBONNET = register("bluebonnet");
    BlockItemId MAGENTA_CONEFLOWER = register("magenta_coneflower");
    BlockItemId YELLOW_CONEFLOWER = register("yellow_coneflower");
    BlockItemId IRONWEED = register("ironweed");
    BlockItemId ROSE = register("rose");
    BlockItemId PEBBLE = register("pebble");
    BlockItemId LATERITE = register("laterite");
    BlockItemId PEAT = register("peat");
    BlockItemId SILT = register("silt");
    BlockItemId SALT = register("salt");
    BlockItemId RUSTY_MOSS_BLOCK = register("rusty_moss_block");
    BlockItemId RUSTY_MOSS_CARPET = register("rusty_moss_carpet");
    BlockItemId OCHRE_MOSS_BLOCK = register("ochre_moss_block");
    BlockItemId OCHRE_MOSS_CARPET = register("ochre_moss_carpet");

    // Sporophytes
    BlockItemId MOSS_SPOROPHYTE = register("moss_sporophyte");
    BlockItemId RUSTY_MOSS_SPOROPHYTE = register("rusty_moss_sporophyte");
    BlockItemId OCHRE_MOSS_SPOROPHYTE = register("ochre_moss_sporophyte");
    BlockItemId PALE_MOSS_SPOROPHYTE = register("pale_moss_sporophyte");
    BlockItemId GILDED_BIRCH_LEAVES = register("gilded_birch_leaves");
    BlockItemId SEAGRASS_SAND = register("seagrass_sand");

    // Azalea
    BlockItemId AZALEA_LOG = register("azalea_log");
    BlockItemId STRIPPED_AZALEA_LOG = register("stripped_azalea_log");
    BlockItemId AZALEA_WOOD = register("azalea_wood");
    BlockItemId STRIPPED_AZALEA_WOOD = register("stripped_azalea_wood");
    BlockItemId AZALEA_PLANKS = register("azalea_planks");
    BlockItemId AZALEA_STAIRS = register("azalea_stairs");
    BlockItemId AZALEA_SLAB = register("azalea_slab");
    BlockItemId AZALEA_MOSAIC = register("azalea_mosaic");
    BlockItemId AZALEA_MOSAIC_STAIRS = register("azalea_mosaic_stairs");
    BlockItemId AZALEA_MOSAIC_SLAB = register("azalea_mosaic_slab");
    BlockItemId AZALEA_BOARDS = register("azalea_boards");
    BlockItemId AZALEA_TRIM = register("azalea_trim");
    BlockItemId AZALEA_FENCE = register("azalea_fence");
    BlockItemId AZALEA_FENCE_GATE = register("azalea_fence_gate");
    BlockItemId AZALEA_DOOR = register("azalea_door");
    BlockItemId AZALEA_TRAPDOOR = register("azalea_trapdoor");
    BlockItemId AZALEA_PRESSURE_PLATE = register("azalea_pressure_plate");
    BlockItemId AZALEA_BUTTON = register("azalea_button");
    BlockItemId AZALEA_SIGN = register("azalea_sign");
    BlockItemId AZALEA_WALL_SIGN = register("azalea_wall_sign");
    BlockItemId AZALEA_HANGING_SIGN = register("azalea_hanging_sign");
    BlockItemId AZALEA_WALL_HANGING_SIGN = register("azalea_wall_hanging_sign");
    BlockItemId AZALEA_SHELF = register("azalea_shelf");

    // Cedar
    BlockItemId CEDAR_LOG = register("cedar_log");
    BlockItemId STRIPPED_CEDAR_LOG = register("stripped_cedar_log");
    BlockItemId CEDAR_WOOD = register("cedar_wood");
    BlockItemId STRIPPED_CEDAR_WOOD = register("stripped_cedar_wood");
    BlockItemId CEDAR_PLANKS = register("cedar_planks");
    BlockItemId CEDAR_STAIRS = register("cedar_stairs");
    BlockItemId CEDAR_SLAB = register("cedar_slab");
    BlockItemId CEDAR_MOSAIC = register("cedar_mosaic");
    BlockItemId CEDAR_MOSAIC_STAIRS = register("cedar_mosaic_stairs");
    BlockItemId CEDAR_MOSAIC_SLAB = register("cedar_mosaic_slab");
    BlockItemId CEDAR_BOARDS = register("cedar_boards");
    BlockItemId CEDAR_TRIM = register("cedar_trim");
    BlockItemId CEDAR_FENCE = register("cedar_fence");
    BlockItemId CEDAR_FENCE_GATE = register("cedar_fence_gate");
    BlockItemId CEDAR_DOOR = register("cedar_door");
    BlockItemId CEDAR_TRAPDOOR = register("cedar_trapdoor");
    BlockItemId CEDAR_PRESSURE_PLATE = register("cedar_pressure_plate");
    BlockItemId CEDAR_BUTTON = register("cedar_button");
    BlockItemId CEDAR_SIGN = register("cedar_sign");
    BlockItemId CEDAR_WALL_SIGN = register("cedar_wall_sign");
    BlockItemId CEDAR_HANGING_SIGN = register("cedar_hanging_sign");
    BlockItemId CEDAR_WALL_HANGING_SIGN = register("cedar_wall_hanging_sign");
    BlockItemId CEDAR_SHELF = register("cedar_shelf");
    BlockItemId CEDAR_SAPLING = register("cedar_sapling");
    BlockItemId CEDAR_LEAVES = register("cedar_leaves");

    // Mahogany
    BlockItemId MAHOGANY_LOG = register("mahogany_log");
    BlockItemId STRIPPED_MAHOGANY_LOG = register("stripped_mahogany_log");
    BlockItemId MAHOGANY_WOOD = register("mahogany_wood");
    BlockItemId STRIPPED_MAHOGANY_WOOD = register("stripped_mahogany_wood");
    BlockItemId MAHOGANY_PLANKS = register("mahogany_planks");
    BlockItemId MAHOGANY_STAIRS = register("mahogany_stairs");
    BlockItemId MAHOGANY_SLAB = register("mahogany_slab");
    BlockItemId MAHOGANY_MOSAIC = register("mahogany_mosaic");
    BlockItemId MAHOGANY_MOSAIC_STAIRS = register("mahogany_mosaic_stairs");
    BlockItemId MAHOGANY_MOSAIC_SLAB = register("mahogany_mosaic_slab");
    BlockItemId MAHOGANY_BOARDS = register("mahogany_boards");
    BlockItemId MAHOGANY_TRIM = register("mahogany_trim");
    BlockItemId MAHOGANY_FENCE = register("mahogany_fence");
    BlockItemId MAHOGANY_FENCE_GATE = register("mahogany_fence_gate");
    BlockItemId MAHOGANY_DOOR = register("mahogany_door");
    BlockItemId MAHOGANY_TRAPDOOR = register("mahogany_trapdoor");
    BlockItemId MAHOGANY_PRESSURE_PLATE = register("mahogany_pressure_plate");
    BlockItemId MAHOGANY_BUTTON = register("mahogany_button");
    BlockItemId MAHOGANY_SIGN = register("mahogany_sign");
    BlockItemId MAHOGANY_WALL_SIGN = register("mahogany_wall_sign");
    BlockItemId MAHOGANY_HANGING_SIGN = register("mahogany_hanging_sign");
    BlockItemId MAHOGANY_WALL_HANGING_SIGN = register("mahogany_wall_hanging_sign");
    BlockItemId MAHOGANY_SHELF = register("mahogany_shelf");
    BlockItemId MAHOGANY_SAPLING = register("mahogany_sapling");
    BlockItemId MAHOGANY_LEAVES = register("mahogany_leaves");

    // Pine
    BlockItemId PINE_LOG = register("pine_log");
    BlockItemId STRIPPED_PINE_LOG = register("stripped_pine_log");
    BlockItemId PINE_WOOD = register("pine_wood");
    BlockItemId STRIPPED_PINE_WOOD = register("stripped_pine_wood");
    BlockItemId PINE_PLANKS = register("pine_planks");
    BlockItemId PINE_STAIRS = register("pine_stairs");
    BlockItemId PINE_SLAB = register("pine_slab");
    BlockItemId PINE_MOSAIC = register("pine_mosaic");
    BlockItemId PINE_MOSAIC_STAIRS = register("pine_mosaic_stairs");
    BlockItemId PINE_MOSAIC_SLAB = register("pine_mosaic_slab");
    BlockItemId PINE_BOARDS = register("pine_boards");
    BlockItemId PINE_TRIM = register("pine_trim");
    BlockItemId PINE_FENCE = register("pine_fence");
    BlockItemId PINE_FENCE_GATE = register("pine_fence_gate");
    BlockItemId PINE_DOOR = register("pine_door");
    BlockItemId PINE_TRAPDOOR = register("pine_trapdoor");
    BlockItemId PINE_PRESSURE_PLATE = register("pine_pressure_plate");
    BlockItemId PINE_BUTTON = register("pine_button");
    BlockItemId PINE_SIGN = register("pine_sign");
    BlockItemId PINE_WALL_SIGN = register("pine_wall_sign");
    BlockItemId PINE_HANGING_SIGN = register("pine_hanging_sign");
    BlockItemId PINE_WALL_HANGING_SIGN = register("pine_wall_hanging_sign");
    BlockItemId PINE_SHELF = register("pine_shelf");
    BlockItemId PINE_SAPLING = register("pine_sapling");
    BlockItemId PINE_LEAVES = register("pine_leaves");

    // Fir
    BlockItemId FIR_LOG = register("fir_log");
    BlockItemId STRIPPED_FIR_LOG = register("stripped_fir_log");
    BlockItemId FIR_WOOD = register("fir_wood");
    BlockItemId STRIPPED_FIR_WOOD = register("stripped_fir_wood");
    BlockItemId FIR_PLANKS = register("fir_planks");
    BlockItemId FIR_STAIRS = register("fir_stairs");
    BlockItemId FIR_SLAB = register("fir_slab");
    BlockItemId FIR_MOSAIC = register("fir_mosaic");
    BlockItemId FIR_MOSAIC_STAIRS = register("fir_mosaic_stairs");
    BlockItemId FIR_MOSAIC_SLAB = register("fir_mosaic_slab");
    BlockItemId FIR_BOARDS = register("fir_boards");
    BlockItemId FIR_TRIM = register("fir_trim");
    BlockItemId FIR_FENCE = register("fir_fence");
    BlockItemId FIR_FENCE_GATE = register("fir_fence_gate");
    BlockItemId FIR_DOOR = register("fir_door");
    BlockItemId FIR_TRAPDOOR = register("fir_trapdoor");
    BlockItemId FIR_PRESSURE_PLATE = register("fir_pressure_plate");
    BlockItemId FIR_BUTTON = register("fir_button");
    BlockItemId FIR_SIGN = register("fir_sign");
    BlockItemId FIR_WALL_SIGN = register("fir_wall_sign");
    BlockItemId FIR_HANGING_SIGN = register("fir_hanging_sign");
    BlockItemId FIR_WALL_HANGING_SIGN = register("fir_wall_hanging_sign");
    BlockItemId FIR_SHELF = register("fir_shelf");
    BlockItemId FIR_SAPLING = register("fir_sapling");
    BlockItemId FIR_LEAVES = register("fir_leaves");

    // Pine
    BlockItemId WILLOW_LOG = register("willow_log");
    BlockItemId STRIPPED_WILLOW_LOG = register("stripped_willow_log");
    BlockItemId WILLOW_WOOD = register("willow_wood");
    BlockItemId STRIPPED_WILLOW_WOOD = register("stripped_willow_wood");
    BlockItemId WILLOW_PLANKS = register("willow_planks");
    BlockItemId WILLOW_STAIRS = register("willow_stairs");
    BlockItemId WILLOW_SLAB = register("willow_slab");
    BlockItemId WILLOW_MOSAIC = register("willow_mosaic");
    BlockItemId WILLOW_MOSAIC_STAIRS = register("willow_mosaic_stairs");
    BlockItemId WILLOW_MOSAIC_SLAB = register("willow_mosaic_slab");
    BlockItemId WILLOW_BOARDS = register("willow_boards");
    BlockItemId WILLOW_TRIM = register("willow_trim");
    BlockItemId WILLOW_FENCE = register("willow_fence");
    BlockItemId WILLOW_FENCE_GATE = register("willow_fence_gate");
    BlockItemId WILLOW_DOOR = register("willow_door");
    BlockItemId WILLOW_TRAPDOOR = register("willow_trapdoor");
    BlockItemId WILLOW_PRESSURE_PLATE = register("willow_pressure_plate");
    BlockItemId WILLOW_BUTTON = register("willow_button");
    BlockItemId WILLOW_SIGN = register("willow_sign");
    BlockItemId WILLOW_WALL_SIGN = register("willow_wall_sign");
    BlockItemId WILLOW_HANGING_SIGN = register("willow_hanging_sign");
    BlockItemId WILLOW_WALL_HANGING_SIGN = register("willow_wall_hanging_sign");
    BlockItemId WILLOW_SHELF = register("willow_shelf");
    BlockItemId WILLOW_SAPLING = register("willow_sapling");
    BlockItemId WILLOW_LEAVES = register("willow_leaves");

    // Arid Dirt
    BlockItemId ARID_DIRT = register("arid_dirt");
    BlockItemId COARSE_ARID_DIRT = register("coarse_arid_dirt");
    BlockItemId ARID_GRASS_BLOCK = register("arid_grass_block");
    BlockItemId GLOOMY_DIRT = register("gloomy_dirt");
    BlockItemId COARSE_GLOOMY_DIRT = register("coarse_gloomy_dirt");
    BlockItemId GLOOMY_GRASS_BLOCK = register("gloomy_grass_block");
    BlockItemId CATTAILS = register("cattails");

    // CaveModifier Flora
    BlockItemId CAVE_WEED = register("cave_weed");
    BlockItemId GLEAMSHROOM = register("gleamshroom");

    // Terracotta
    BlockItemId TERRACOTTA_STAIRS = register("terracotta_stairs");
    BlockItemId TERRACOTTA_SLAB = register("terracotta_slab");
    BlockItemId TERRACOTTA_BRICKS = register("terracotta_bricks");
    BlockItemId TERRACOTTA_BRICK_STAIRS = register("terracotta_brick_stairs");
    BlockItemId TERRACOTTA_BRICK_SLAB = register("terracotta_brick_slab");
    BlockItemId TERRACOTTA_BRICK_TILES = register("terracotta_brick_tiles");
    BlockItemId TERRACOTTA_BRICK_TILE_STAIRS = register("terracotta_brick_tile_stairs");
    BlockItemId TERRACOTTA_BRICK_TILE_SLAB = register("terracotta_brick_tile_slab");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_STAIRS = createSimpleColored("terracotta_stairs");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_SLAB = createSimpleColored("terracotta_slab");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_BRICKS = createSimpleColored("terracotta_bricks");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_BRICK_STAIRS = createSimpleColored("terracotta_brick_stairs");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_BRICK_SLAB = createSimpleColored("terracotta_brick_slab");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_BRICK_TILES = createSimpleColored("terracotta_brick_tiles");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_BRICK_TILE_STAIRS = createSimpleColored("terracotta_brick_tile_stairs");
    ColorCollection<BlockItemId> DYED_TERRACOTTA_BRICK_TILE_SLAB = createSimpleColored("terracotta_brick_tile_slab");

    // Beachstone
    BlockItemId BEACHSTONE = register("beachstone");
    BlockItemId BEACHSTONE_STAIRS = register("beachstone_stairs");
    BlockItemId BEACHSTONE_SLAB = register("beachstone_slab");
    BlockItemId BEACHSTONE_WALL = register("beachstone_wall");
    BlockItemId POLISHED_BEACHSTONE = register("polished_beachstone");
    BlockItemId POLISHED_BEACHSTONE_STAIRS = register("polished_beachstone_stairs");
    BlockItemId POLISHED_BEACHSTONE_SLAB = register("polished_beachstone_slab");
    BlockItemId POLISHED_BEACHSTONE_WALL = register("polished_beachstone_wall");
    BlockItemId BEACHSTONE_BRICKS = register("beachstone_bricks");
    BlockItemId BEACHSTONE_BRICK_STAIRS = register("beachstone_brick_stairs");
    BlockItemId BEACHSTONE_BRICK_SLAB = register("beachstone_brick_slab");
    BlockItemId BEACHSTONE_BRICK_WALL = register("beachstone_brick_wall");
    BlockItemId CHISELED_BEACHSTONE = register("chiseled_beachstone");

    // Limestone
    BlockItemId LIMESTONE = register("limestone");
    BlockItemId LIMESTONE_STAIRS = register("limestone_stairs");
    BlockItemId LIMESTONE_SLAB = register("limestone_slab");
    BlockItemId LIMESTONE_WALL = register("limestone_wall");
    BlockItemId CUT_LIMESTONE = register("cut_limestone");
    BlockItemId CUT_LIMESTONE_STAIRS = register("cut_limestone_stairs");
    BlockItemId CUT_LIMESTONE_SLAB = register("cut_limestone_slab");
    BlockItemId CUT_LIMESTONE_WALL = register("cut_limestone_wall");

    // Basalt / Sandstone
    BlockItemId ROCKWEED_BASALT = register("rockweed_basalt");
    BlockItemId BASALT_VENT = register("basalt_vent");
    BlockItemId ORANGE_SAND = register("orange_sand");
    BlockItemId ORANGE_SANDSTONE = register("orange_sandstone");
    BlockItemId ORANGE_SANDSTONE_STAIRS = register("orange_sandstone_stairs");
    BlockItemId ORANGE_SANDSTONE_SLAB = register("orange_sandstone_slab");
    BlockItemId ORANGE_SANDSTONE_WALL = register("orange_sandstone_wall");
    BlockItemId SMOOTH_ORANGE_SANDSTONE = register("smooth_orange_sandstone");
    BlockItemId SMOOTH_ORANGE_SANDSTONE_STAIRS = register("smooth_orange_sandstone_stairs");
    BlockItemId SMOOTH_ORANGE_SANDSTONE_SLAB = register("smooth_orange_sandstone_slab");
    BlockItemId SMOOTH_ORANGE_SANDSTONE_WALL = register("smooth_orange_sandstone_wall");
    BlockItemId CUT_ORANGE_SANDSTONE = register("cut_orange_sandstone");
    BlockItemId CUT_ORANGE_SANDSTONE_STAIRS = register("cut_orange_sandstone_stairs");
    BlockItemId CUT_ORANGE_SANDSTONE_SLAB = register("cut_orange_sandstone_slab");
    BlockItemId CUT_ORANGE_SANDSTONE_WALL = register("cut_orange_sandstone_wall");
    BlockItemId POLISHED_ORANGE_SANDSTONE = register("polished_orange_sandstone");
    BlockItemId POLISHED_ORANGE_SANDSTONE_STAIRS = register("polished_orange_sandstone_stairs");
    BlockItemId POLISHED_ORANGE_SANDSTONE_SLAB = register("polished_orange_sandstone_slab");
    BlockItemId POLISHED_ORANGE_SANDSTONE_WALL = register("polished_orange_sandstone_wall");
    BlockItemId CHISELED_ORANGE_SANDSTONE = register("chiseled_orange_sandstone");

    // Mud / Metals
    BlockItemId MOSSY_MUD = register("mossy_mud");
    BlockItemId MUDSTONE = register("mudstone");
    BlockItemId POLISHED_SILVER = register("polished_silver");
    BlockItemId POLISHED_SILVER_STAIRS = register("polished_silver_stairs");
    BlockItemId POLISHED_SILVER_SLAB = register("polished_silver_slab");
    BlockItemId CUT_SILVER = register("cut_silver");
    BlockItemId CUT_SILVER_STAIRS = register("cut_silver_stairs");
    BlockItemId CUT_SILVER_SLAB = register("cut_silver_slab");
    BlockItemId SILVER_PILLAR = register("silver_pillar");
    BlockItemId SILVER_BARS = register("silver_bars");
    BlockItemId SILVER_CHAIN = register("silver_chain");
    BlockItemId GOLD_CHAIN = register("gold_chain");
    BlockItemId REDSTONE_LANTERN = register("redstone_lantern");
    BlockItemId WROUGHT_IRON_BLOCK = register("wrought_iron_block");
    BlockItemId WROUGHT_IRON_STAIRS = register("wrought_iron_stairs");
    BlockItemId WROUGHT_IRON_SLAB = register("wrought_iron_slab");
    BlockItemId CUT_WROUGHT_IRON = register("cut_wrought_iron");
    BlockItemId CUT_WROUGHT_IRON_STAIRS = register("cut_wrought_iron_stairs");
    BlockItemId CUT_WROUGHT_IRON_SLAB = register("cut_wrought_iron_slab");
    BlockItemId WROUGHT_IRON_GRATE = register("wrought_iron_grate");
    BlockItemId WROUGHT_IRON_BARS = register("wrought_iron_bars");
    BlockItemId LAPIS_BRICKS = register("lapis_bricks");
    BlockItemId LAPIS_BRICK_STAIRS = register("lapis_brick_stairs");
    BlockItemId LAPIS_BRICK_SLAB = register("lapis_brick_slab");

    BlockItemId PACKED_ICE_STAIRS = register("packed_ice_stairs");
    BlockItemId PACKED_ICE_SLAB = register("packed_ice_slab");
    BlockItemId CHISELED_PACKED_ICE_BRICKS = register("chiseled_packed_ice_bricks");
    BlockItemId CHISELED_SNOW_BRICKS = register("chiseled_snow_bricks");

    BlockItemId DUNGEON_SPAWNER = register("dungeon_spawner");
    BlockItemId DUNGEON_VAULT = register("dungeon_vault");

    static BlockItemId register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Elsewhere.MODID, name);
        return BlockItemId.create(id, id);
    }
    private static ColorCollection<BlockItemId> createSimpleColored(final String baseName) {
        return ColorCollection.prefixWithColor(ColorCollection.create(baseName)).map(EBlockItemIds::register);
    }
    static void initialize() {}
}