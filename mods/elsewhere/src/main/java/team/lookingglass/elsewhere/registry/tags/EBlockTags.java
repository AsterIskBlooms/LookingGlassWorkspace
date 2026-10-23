package team.lookingglass.elsewhere.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBlockTags {
    TagKey<Block> MOSAICS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mosaics"));
    TagKey<Block> MOSAIC_STAIRS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mosaic_stairs"));
    TagKey<Block> MOSAIC_SLABS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mosaic_slabs"));
    TagKey<Block> BOARDS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "boards"));
    TagKey<Block> TRIMS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "trims"));

    TagKey<Block> AZALEA_LOGS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "azalea_logs"));
    TagKey<Block> CEDAR_LOGS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cedar_logs"));
    TagKey<Block> MAHOGANY_LOGS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mahogany_logs"));
    TagKey<Block> BAMBOO_STEMS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "bamboo_stems"));

    TagKey<Block> NATURAL_STONE_BLOCKS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "natural_stone_blocks"));
    TagKey<Block> SOIL = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "soil"));
    TagKey<Block> NETHER_SOIL = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "nether_soil"));

    TagKey<Block> CINNABAR_ORE_REPLACEABLES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cinnabar_ore_replaceables"));
    TagKey<Block> CALCITE_ORE_REPLACEABLES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "calcite_ore_replaceables"));
    TagKey<Block> SOUL_STONE_ORE_REPLACEABLES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "soul_stone_ore_replaceables"));

    TagKey<Block> AMETHYST_SPIRE_REPLACEABLE = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "amethyst_spire_replaceable"));

    TagKey<Block> NEEDS_COPPER_TOOL = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "needs_copper_tool"));
    TagKey<Block> INCORRECT_FOR_BRONZE_TOOL = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "incorrect_for_bronze_tool"));

    TagKey<Block> TERRACOTTA_STAIRS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_stairs"));
    TagKey<Block> TERRACOTTA_SLABS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_slabs"));

    TagKey<Block> TERRACOTTA_BRICKS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_bricks"));
    TagKey<Block> TERRACOTTA_BRICK_STAIRS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_brick_stairs"));
    TagKey<Block> TERRACOTTA_BRICK_SLABS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_brick_slabs"));

    TagKey<Block> TERRACOTTA_BRICK_TILES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_brick_tiles"));
    TagKey<Block> TERRACOTTA_BRICK_TILE_STAIRS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_brick_tile_stairs"));
    TagKey<Block> TERRACOTTA_BRICK_TILE_SLABS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "terracotta_brick_tile_slabs"));

    static void initialize() {}
}
