package team.lookingglass.elsewhere.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import team.lookingglass.elsewhere.Elsewhere;

public interface EItemIds {
    ResourceKey<Item> AZALEA_SIGN = register("azalea_sign");
    ResourceKey<Item> AZALEA_HANGING_SIGN = register("azalea_hanging_sign");
    ResourceKey<Item> AZALEA_BOAT = register("azalea_boat");
    ResourceKey<Item> AZALEA_CHEST_BOAT = register("azalea_chest_boat");
    ResourceKey<Item> CEDAR_SIGN = register("cedar_sign");
    ResourceKey<Item> CEDAR_HANGING_SIGN = register("cedar_hanging_sign");
    ResourceKey<Item> CEDAR_BOAT = register("cedar_boat");
    ResourceKey<Item> CEDAR_CHEST_BOAT = register("cedar_chest_boat");
    ResourceKey<Item> MAHOGANY_SIGN = register("mahogany_sign");
    ResourceKey<Item> MAHOGANY_HANGING_SIGN = register("mahogany_hanging_sign");
    ResourceKey<Item> MAHOGANY_BOAT = register("mahogany_boat");
    ResourceKey<Item> MAHOGANY_CHEST_BOAT = register("mahogany_chest_boat");

    ResourceKey<Item> DIAMOND_NUGGET = register("diamond_nugget");
    ResourceKey<Item> EMERALD_NUGGET = register("emerald_nugget");
    ResourceKey<Item> SOULGLASS_SHARD = register("soulglass_shard");
    ResourceKey<Item> RAW_TIN = register("raw_tin");
    ResourceKey<Item> TIN_INGOT = register("tin_ingot");
    ResourceKey<Item> TIN_NUGGET = register("tin_nugget");

    ResourceKey<Item> BRONZE_ALLOY = register("bronze_alloy");
    ResourceKey<Item> BRONZE_UPGRADE_SMITHING_TEMPLATE = register("bronze_upgrade_smithing_template");
    ResourceKey<Item> BRONZE_HELMET = register("bronze_helmet");
    ResourceKey<Item> BRONZE_CHESTPLATE = register("bronze_chestplate");
    ResourceKey<Item> BRONZE_LEGGINGS = register("bronze_leggings");
    ResourceKey<Item> BRONZE_BOOTS = register("bronze_boots");
    ResourceKey<Item> BRONZE_SWORD = register("bronze_sword");
    ResourceKey<Item> BRONZE_AXE = register("bronze_axe");
    ResourceKey<Item> BRONZE_SPEAR = register("bronze_spear");
    ResourceKey<Item> BRONZE_PICKAXE = register("bronze_pickaxe");
    ResourceKey<Item> BRONZE_SHOVEL = register("bronze_shovel");
    ResourceKey<Item> BRONZE_HOE = register("bronze_hoe");

    ResourceKey<Item> RAW_SILVER = register("raw_silver");
    ResourceKey<Item> SILVER_INGOT = register("silver_ingot");
    ResourceKey<Item> SILVER_NUGGET = register("silver_nugget");

    ResourceKey<Item> SOULSTEEL_ALLOY = register("soulsteel_alloy");
    ResourceKey<Item> SOULSTEEL_UPGRADE_SMITHING_TEMPLATE = register("soulsteel_upgrade_smithing_template");
    ResourceKey<Item> SOULSTEEL_HELMET = register("soulsteel_helmet");
    ResourceKey<Item> SOULSTEEL_CHESTPLATE = register("soulsteel_chestplate");
    ResourceKey<Item> SOULSTEEL_LEGGINGS = register("soulsteel_leggings");
    ResourceKey<Item> SOULSTEEL_BOOTS = register("soulsteel_boots");
    ResourceKey<Item> SOULSTEEL_SWORD = register("soulsteel_sword");
    ResourceKey<Item> SOULSTEEL_AXE = register("soulsteel_axe");
    ResourceKey<Item> SOULSTEEL_SPEAR = register("soulsteel_spear");
    ResourceKey<Item> SOULSTEEL_PICKAXE = register("soulsteel_pickaxe");
    ResourceKey<Item> SOULSTEEL_SHOVEL = register("soulsteel_shovel");
    ResourceKey<Item> SOULSTEEL_HOE = register("soulsteel_hoe");

    ResourceKey<Item> ELECTRUM_ALLOY = register("electrum_alloy");
    ResourceKey<Item> ELECTRUM_UPGRADE_SMITHING_TEMPLATE = register("electrum_upgrade_smithing_template");
    ResourceKey<Item> ELECTRUM_HELMET = register("electrum_helmet");
    ResourceKey<Item> ELECTRUM_CHESTPLATE = register("electrum_chestplate");
    ResourceKey<Item> ELECTRUM_LEGGINGS = register("electrum_leggings");
    ResourceKey<Item> ELECTRUM_BOOTS = register("electrum_boots");
    ResourceKey<Item> ELECTRUM_SWORD = register("electrum_sword");
    ResourceKey<Item> ELECTRUM_AXE = register("electrum_axe");
    ResourceKey<Item> ELECTRUM_SPEAR = register("electrum_spear");
    ResourceKey<Item> ELECTRUM_PICKAXE = register("electrum_pickaxe");
    ResourceKey<Item> ELECTRUM_SHOVEL = register("electrum_shovel");
    ResourceKey<Item> ELECTRUM_HOE = register("electrum_hoe");

    ResourceKey<Item> URANIUM = register("uranium");
    ResourceKey<Item> URANIUM_SHARD = register("uranium_shard");

    ResourceKey<Item> REINFORCED_SHIELD = register("reinforced_shield");
    ResourceKey<Item> QUICK_SHIELD = register("quick_shield");

    ResourceKey<Item> WEREWOLF_SPAWN_EGG = register("werewolf_spawn_egg");
    ResourceKey<Item> VANGUARD_SPAWN_EGG = register("vanguard_spawn_egg");
    ResourceKey<Item> SUBSLIME_SPAWN_EGG = register("subslime_spawn_egg");
    ResourceKey<Item> SWEEPER_SPAWN_EGG = register("sweeper_spawn_egg");
    ResourceKey<Item> MUD_GOLEM_SPAWN_EGG = register("mud_golem_spawn_egg");
    ResourceKey<Item> LOST_SOUL_SPAWN_EGG = register("lost_soul_spawn_egg");
    ResourceKey<Item> TROLL_SPAWN_EGG = register("troll_spawn_egg");
    ResourceKey<Item> GRAVEKEEPER_SPAWN_EGG = register("gravekeeper_spawn_egg");
    ResourceKey<Item> WHISTLER_SPAWN_EGG = register("whistler_spawn_egg");

    ResourceKey<Item> VANGUARD_MASK = register("vanguard_mask");

    ResourceKey<Item> PERCH_SPAWN_EGG = register("perch_spawn_egg");
    ResourceKey<Item> RAW_PERCH = register("raw_perch");
    ResourceKey<Item> COOKED_PERCH = register("cooked_perch");
    ResourceKey<Item> SHINING_PERCH = register("shining_perch");
    ResourceKey<Item> PERCH_BUCKET = register("perch_bucket");

    ResourceKey<Item> MUSIC_DISC_FOX = register("music_disc_fox");

    ResourceKey<Item> DUNGEON_KEY = register("dungeon_key");
    ResourceKey<Item> OMINOUS_DUNGEON_KEY = register("ominous_dungeon_key");

    private static ResourceKey<Item> register(final String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }
    static void initialize() {}
}