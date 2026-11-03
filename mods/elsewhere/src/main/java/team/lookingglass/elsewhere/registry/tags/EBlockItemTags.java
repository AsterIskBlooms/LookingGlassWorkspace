package team.lookingglass.elsewhere.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBlockItemTags {
    BlockItemTagId MOSAICS = create("mosaics");
    BlockItemTagId MOSAIC_STAIRS = create("mosaic_stairs");
    BlockItemTagId MOSAIC_SLABS = create("mosaic_slabs");
    BlockItemTagId BOARDS = create("boards");
    BlockItemTagId TRIMS = create("trims");

    BlockItemTagId AZALEA_LOGS = create("azalea_logs");
    BlockItemTagId CEDAR_LOGS = create("cedar_logs");
    BlockItemTagId MAHOGANY_LOGS = create("mahogany_logs");
    BlockItemTagId PINE_LOGS = create("pine_logs");
    BlockItemTagId BAMBOO_STEMS = create("bamboo_stems");

    BlockItemTagId TERRACOTTA_STAIRS = create("terracotta_stairs");
    BlockItemTagId TERRACOTTA_SLABS = create("terracotta_slabs");

    BlockItemTagId TERRACOTTA_BRICKS = create("terracotta_bricks");
    BlockItemTagId TERRACOTTA_BRICK_STAIRS = create("terracotta_brick_stairs");
    BlockItemTagId TERRACOTTA_BRICK_SLABS = create("terracotta_brick_slabs");

    BlockItemTagId TERRACOTTA_BRICK_TILES = create("terracotta_brick_tiles");
    BlockItemTagId TERRACOTTA_BRICK_TILE_STAIRS = create("terracotta_brick_tile_stairs");
    BlockItemTagId TERRACOTTA_BRICK_TILE_SLABS = create("terracotta_brick_tile_slabs");

    private static BlockItemTagId create(String name) {
        return create(name, name);
    }

    private static BlockItemTagId create(String blockName, String itemName) {
        return new BlockItemTagId(
                TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, blockName)),
                TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Elsewhere.MODID, itemName))
        );
    }

    static void initialize() {}
}
