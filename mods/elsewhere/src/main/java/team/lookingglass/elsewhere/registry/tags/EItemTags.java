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

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    static void initialize() {}
}
