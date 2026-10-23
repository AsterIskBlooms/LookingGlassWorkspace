package team.lookingglass.elsewhere.datagen.loot;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import team.lookingglass.elsewhere.registry.EItems;
import team.lookingglass.elsewhere.registry.blocktypes.entity.DungeonSpawnerBlockEntity;

public class StandardLootTableProvider extends SimpleFabricLootTableSubProvider {
    public StandardLootTableProvider(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, LootContextParamSets.EMPTY);
    }

    @Override
    public void generate(final BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        // Dungeon Spawners
        output.accept(DungeonSpawnerBlockEntity.REWARD_LOOT_KEY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(EItems.DUNGEON_KEY)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).setWeight(10))
                        .add(LootItem.lootTableItem(Items.CARROT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))).setWeight(4))
                        .add(LootItem.lootTableItem(Items.APPLE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))).setWeight(3))
                        .add(LootItem.lootTableItem(Items.IRON_NAUTILUS_ARMOR)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).setWeight(1))
                        .add(LootItem.lootTableItem(Items.SPLASH_POTION)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .apply(SetPotionFunction.setPotion(Potions.FREEZING)).setWeight(1))
                        .add(LootItem.lootTableItem(Items.POTION)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .apply(SetPotionFunction.setPotion(Potions.INVISIBILITY)).setWeight(1))
                ));
        output.accept(DungeonSpawnerBlockEntity.OMINOUS_REWARD_LOOT_KEY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(EItems.OMINOUS_DUNGEON_KEY)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).setWeight(30))
                        .add(LootItem.lootTableItem(Items.MUSHROOM_STEW)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(2))).setWeight(21))
                        .add(LootItem.lootTableItem(Items.GOLDEN_CARROT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(12))
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))).setWeight(15))
                        .add(LootItem.lootTableItem(Items.DIAMOND_NAUTILUS_ARMOR)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).setWeight(6))
                        .add(LootItem.lootTableItem(Items.POTION)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .apply(SetPotionFunction.setPotion(Potions.STRONG_REGENERATION)).setWeight(8))
                        .add(LootItem.lootTableItem(Items.POTION)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .apply(SetPotionFunction.setPotion(Potions.FIRE_RESISTANCE)).setWeight(8))
                ));
    }

    @Override
    public void run() {}
}
