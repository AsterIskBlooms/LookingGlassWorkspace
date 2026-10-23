package team.lookingglass.elsewhere.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class EAdvancementProvider extends FabricAdvancementProvider {
    public EAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @SuppressWarnings("removal")
    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder root = Advancement.Builder.advancement().build(Identifier.withDefaultNamespace("story/root"));

//        AdvancementHolder stonePickaxe = Advancement.Builder.advancement()
//                .parent(root)
//                .display(
//                        Items.STONE_PICKAXE,
//                        Component.translatable("advancements.elsewhere.stone_pickaxe.title"),
//                        Component.translatable("advancements.elsewhere.stone_pickaxe.description"),
//                        null, AdvancementType.TASK, true, true, false
//                )
//                .addCriterion("stone_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PICKAXE))
//                .save(consumer, "minecraft:story/mine_stone");
//
//        AdvancementHolder copperPickaxe = Advancement.Builder.advancement()
//                .parent(stonePickaxe)
//                .display(
//                        Items.COPPER_PICKAXE,
//                        Component.translatable("advancements.elsewhere.copper_pickaxe.title"),
//                        Component.translatable("advancements.elsewhere.copper_pickaxe.description"),
//                        null, AdvancementType.TASK, true, true, false
//                )
//                .addCriterion("copper_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_PICKAXE))
//                .save(consumer, "minecraft:story/upgrade_tools");
//
//        AdvancementHolder copperArmor = Advancement.Builder.advancement()
//                .parent(stonePickaxe)
//                .display(
//                        Items.COPPER_CHESTPLATE,
//                        Component.translatable("advancements.elsewhere.copper_armor.title"),
//                        Component.translatable("advancements.elsewhere.copper_armor.description"),
//                        null, AdvancementType.GOAL, true, true, false
//                )
//                .addCriterion("copper_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_HELMET))
//                .addCriterion("copper_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_CHESTPLATE))
//                .addCriterion("copper_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_LEGGINGS))
//                .addCriterion("copper_boots", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_BOOTS))
//                .requirements(AdvancementRequirements.Strategy.OR)
//                .save(consumer, Elsewhere.MODID + ":story/copper_armor");
//
//        AdvancementHolder smeltIron = Advancement.Builder.advancement()
//                .parent(copperPickaxe)
//                .display(
//                        Items.IRON_INGOT,
//                        Component.translatable("advancements.story.smelt_iron.title"),
//                        Component.translatable("advancements.story.smelt_iron.description"),
//                        null, AdvancementType.TASK, true, true, false
//                )
//                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
//                .save(consumer, "minecraft:story/smelt_iron");
//
//        AdvancementHolder ironPickaxe = Advancement.Builder.advancement()
//                .parent(smeltIron)
//                .display(
//                        Items.IRON_PICKAXE,
//                        Component.translatable("advancements.elsewhere.iron_pickaxe.title"),
//                        Component.translatable("advancements.elsewhere.iron_pickaxe.description"),
//                        null, AdvancementType.TASK, true, true, false
//                )
//                .addCriterion("iron_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_PICKAXE))
//                .save(consumer, "minecraft:story/iron_tools");
//
//        AdvancementHolder ironArmor = Advancement.Builder.advancement()
//                .parent(smeltIron)
//                .display(
//                        Items.IRON_CHESTPLATE,
//                        Component.translatable("advancements.story.obtain_armor.title"),
//                        Component.translatable("advancements.story.obtain_armor.description"),
//                        null, AdvancementType.GOAL, true, true, false
//                )
//                .addCriterion("iron_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_HELMET))
//                .addCriterion("iron_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_CHESTPLATE))
//                .addCriterion("iron_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_LEGGINGS))
//                .addCriterion("iron_boots", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BOOTS))
//                .requirements(AdvancementRequirements.Strategy.AND)
//                .save(consumer, "minecraft:story/obtain_armor");
//
//        AdvancementHolder upgradeBronze = Advancement.Builder.advancement()
//                .parent(ironPickaxe)
//                .display(
//                        EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE,
//                        Component.translatable("advancements.elsewhere.upgrade_bronze.title"),
//                        Component.translatable("advancements.elsewhere.upgrade_bronze.description"),
//                        null, AdvancementType.TASK, true, true, false
//                )
//                .addCriterion("bronze_sword", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_SWORD))
//                .addCriterion("bronze_axe", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_AXE))
//                .addCriterion("bronze_spear", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_SPEAR))
//                .addCriterion("bronze_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_PICKAXE))
//                .addCriterion("bronze_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_SHOVEL))
//                .addCriterion("bronze_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_HOE))
//                .addCriterion("bronze_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_HELMET))
//                .addCriterion("bronze_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_CHESTPLATE))
//                .addCriterion("bronze_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_LEGGINGS))
//                .addCriterion("bronze_boots", InventoryChangeTrigger.TriggerInstance.hasItems(EItems.BRONZE_BOOTS))
//                .requirements(AdvancementRequirements.Strategy.OR)
//                .save(consumer, Elsewhere.MODID + ":story/upgrade_bronze");
    }
}
