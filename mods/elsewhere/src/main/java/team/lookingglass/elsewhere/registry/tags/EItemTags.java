package team.lookingglass.elsewhere.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import team.lookingglass.elsewhere.Elsewhere;

public interface EItemTags {
    TagKey<Item> REPAIRS_BRONZE_ARMOR = create("repairs_bronze_armor");
    TagKey<Item> BRONZE_TOOLS = create("bronze_tools");
    TagKey<Item> BRONZE_EQUIPMENT = create("bronze_equipment");

    TagKey<Item> REPAIRS_SOULSTEEL_ARMOR = create("repairs_soulsteel_armor");
    TagKey<Item> SOULSTEEL_TOOLS = create("soulsteel_tools");
    TagKey<Item> SOULSTEEL_EQUIPMENT = create("soulsteel_equipment");

    TagKey<Item> REPAIRS_ELECTRUM_ARMOR = create("repairs_electrum_armor");
    TagKey<Item> ELECTRUM_TOOLS = create("electrum_tools");
    TagKey<Item> ELECTRUM_EQUIPMENT = create("electrum_equipment");

    TagKey<Item> REPAIRS_VANGUARD_MASK = create("repairs_vanguard_mask");

    TagKey<Item> MOSAICS = EBlockItemTags.MOSAICS.item();
    TagKey<Item> MOSAIC_STAIRS = EBlockItemTags.MOSAIC_STAIRS.item();
    TagKey<Item> MOSAIC_SLABS = EBlockItemTags.MOSAIC_SLABS.item();
    TagKey<Item> BOARDS = EBlockItemTags.BOARDS.item();
    TagKey<Item> TRIMS = EBlockItemTags.TRIMS.item();
    TagKey<Item> AZALEA_LOGS = EBlockItemTags.AZALEA_LOGS.item();
    TagKey<Item> CEDAR_LOGS = EBlockItemTags.CEDAR_LOGS.item();
    TagKey<Item> MAHOGANY_LOGS = EBlockItemTags.MAHOGANY_LOGS.item();
    TagKey<Item> BAMBOO_STEMS = EBlockItemTags.BAMBOO_STEMS.item();

    TagKey<Item> TERRACOTTA_STAIRS = EBlockItemTags.TERRACOTTA_STAIRS.item();
    TagKey<Item> TERRACOTTA_SLABS = EBlockItemTags.TERRACOTTA_SLABS.item();
    TagKey<Item> TERRACOTTA_BRICKS = EBlockItemTags.TERRACOTTA_BRICKS.item();
    TagKey<Item> TERRACOTTA_BRICK_STAIRS = EBlockItemTags.TERRACOTTA_BRICK_STAIRS.item();
    TagKey<Item> TERRACOTTA_BRICK_SLABS = EBlockItemTags.TERRACOTTA_BRICK_SLABS.item();
    TagKey<Item> TERRACOTTA_BRICK_TILES = EBlockItemTags.TERRACOTTA_BRICK_TILES.item();
    TagKey<Item> TERRACOTTA_BRICK_TILE_STAIRS = EBlockItemTags.TERRACOTTA_BRICK_TILE_STAIRS.item();
    TagKey<Item> TERRACOTTA_BRICK_TILE_SLABS = EBlockItemTags.TERRACOTTA_BRICK_TILE_SLABS.item();

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    static void initialize() {}
}
