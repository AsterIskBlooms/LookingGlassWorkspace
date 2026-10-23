package team.lookingglass.elsewhere.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import team.lookingglass.elsewhere.entity.EEntities;
import team.lookingglass.elsewhere.registry.EItems;

import java.util.concurrent.CompletableFuture;

public class EntityLootTableProvider extends FabricEntityLootSubProvider {
    public EntityLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(EEntities.VANGUARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(EItems.SILVER_INGOT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(1, 2)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(EItems.VANGUARD_MASK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).setWeight(1))
                        .add(EmptyLootItem.emptyItem().setWeight(19))
                )
        );
        add(EEntities.SWEEPER, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(1, 2))))
                )
        );
        add(EEntities.MUD_GOLEM, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.MUD)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(1, 2))))
                )
        );
        add(EEntities.SUBSLIME, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.AMETHYST_SHARD)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(1, 2))))
                )
        );
        add(EEntities.TROLL, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 7)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(1, 2))))
                )
        );
        add(EEntities.GRAVEKEEPER, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(EItems.DIAMOND_NUGGET)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.exactly(1))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.exactly(1))))
                )
        );
        add(EEntities.WHISTLER, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.COBBLED_DEEPSLATE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                )
        );
        add(EEntities.LOST_SOUL, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(EItems.SOULGLASS_SHARD)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1))))
                )
        );

        add(EEntities.PERCH, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                .add(LootItem.lootTableItem(EItems.RAW_PERCH).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))));

        add(EntityTypes.PIGLIN_BRUTE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 11)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(3, 5)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                        .add(EmptyLootItem.emptyItem().setWeight(4)))
        );
    }
}
