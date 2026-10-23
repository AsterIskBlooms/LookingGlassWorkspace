package team.lookingglass.elsewhere.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ELootModifications {
    private static final Map<ResourceKey<LootTable>, List<TemplateDrop>> DROPS = new HashMap<>();
    private record TemplateDrop(ItemLike item, float chance) {}

    public static void initialize() {

        add(BuiltInLootTables.SIMPLE_DUNGEON, EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, 0.65F);
        add(BuiltInLootTables.ABANDONED_MINESHAFT, EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, 0.15F);
        add(BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR, EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, 0.25F);
        add(BuiltInLootTables.TRIAL_CHAMBERS_INTERSECTION, EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, 0.25F);
        add(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_RARE, EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, 0.5F);

        add(BuiltInLootTables.SIMPLE_DUNGEON, EItems.TIN_INGOT, 0.85F);
        add(BuiltInLootTables.ABANDONED_MINESHAFT, EItems.TIN_INGOT, 0.65F);
        add(BuiltInLootTables.ANCIENT_CITY, EItems.BRONZE_ALLOY, 0.75F);

        add(BuiltInLootTables.ANCIENT_CITY, EItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, 0.35F);
        add(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_RARE, EItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, 0.35F);
        add(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON, EItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, 0.4F);

        add(BuiltInLootTables.NETHER_BRIDGE, EItems.SOULSTEEL_UPGRADE_SMITHING_TEMPLATE, 0.3F);





        LootTableEvents.MODIFY.register((key, tableBuilder, source, holder) -> {
            if (!source.isBuiltin()) return;
            List<TemplateDrop> drops = DROPS.get(key);
            if (drops == null) return;
            for (TemplateDrop drop : drops) {
                tableBuilder.withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(drop.item()))
                        .when(LootItemRandomChanceCondition.randomChance(drop.chance())));
            }
        });
    }

    private static void add(ResourceKey<LootTable> table, ItemLike item, float chance) {
        DROPS.computeIfAbsent(table, k -> new ArrayList<>()).add(new TemplateDrop(item, chance));
    }
}
