package team.lookingglass.elsewhere.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.DispenseBehavior;
import net.minecraft.core.dispenser.DispenseBehaviorIds;
import net.minecraft.core.dispenser.ShootProjectileDispenseBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import team.lookingglass.elsewhere.Elsewhere;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import team.lookingglass.elsewhere.entity.EEntities;
import team.lookingglass.elsewhere.registry.attribute.AttributeIDs;
import team.lookingglass.elsewhere.registry.attribute.AttributeProperties;
import team.lookingglass.elsewhere.registry.attribute.EAttributes;
import team.lookingglass.elsewhere.registry.equipment.*;
import team.lookingglass.elsewhere.registry.equipment.items.ConfigurableShieldItem;
import team.lookingglass.elsewhere.registry.equipment.items.SoulsteelItem;
import team.lookingglass.elsewhere.registry.sets.EDiscs;
import team.lookingglass.elsewhere.registry.util.bolt.Bolt;
import team.lookingglass.elsewhere.registry.util.bolt.BoltItem;
import team.lookingglass.elsewhere.registry.util.bolt.BoltStats;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface EItems {

    Item AZALEA_SIGN = register(EBlockItemIds.AZALEA_SIGN.item(), p -> new StandingAndWallBlockItem(EBlocks.AZALEA_SIGN, EBlocks.AZALEA_WALL_SIGN, Direction.DOWN, p), new Item.Properties().stacksTo(16));
    Item AZALEA_HANGING_SIGN = register(EBlockItemIds.AZALEA_HANGING_SIGN.item(), p -> new HangingSignItem(EBlocks.AZALEA_HANGING_SIGN, EBlocks.AZALEA_WALL_HANGING_SIGN, p), new Item.Properties().stacksTo(16));
    Item AZALEA_BOAT = registerBoat(EItemIds.AZALEA_BOAT, EEntities.AZALEA_BOAT);
    Item AZALEA_CHEST_BOAT = registerBoat(EItemIds.AZALEA_CHEST_BOAT, EEntities.AZALEA_CHEST_BOAT);
    Item CEDAR_SIGN = register(EBlockItemIds.CEDAR_SIGN.item(), p -> new StandingAndWallBlockItem(EBlocks.CEDAR_SIGN, EBlocks.CEDAR_WALL_SIGN, Direction.DOWN, p), new Item.Properties().stacksTo(16));
    Item CEDAR_HANGING_SIGN = register(EBlockItemIds.CEDAR_HANGING_SIGN.item(), p -> new HangingSignItem(EBlocks.CEDAR_HANGING_SIGN, EBlocks.CEDAR_WALL_HANGING_SIGN, p), new Item.Properties().stacksTo(16));
    Item CEDAR_BOAT = registerBoat(EItemIds.CEDAR_BOAT, EEntities.CEDAR_BOAT);
    Item CEDAR_CHEST_BOAT = registerBoat(EItemIds.CEDAR_CHEST_BOAT, EEntities.CEDAR_CHEST_BOAT);
    Item MAHOGANY_SIGN = register(EBlockItemIds.MAHOGANY_SIGN.item(), p -> new StandingAndWallBlockItem(EBlocks.MAHOGANY_SIGN, EBlocks.MAHOGANY_WALL_SIGN, Direction.DOWN, p), new Item.Properties().stacksTo(16));
    Item MAHOGANY_HANGING_SIGN = register(EBlockItemIds.MAHOGANY_HANGING_SIGN.item(), p -> new HangingSignItem(EBlocks.MAHOGANY_HANGING_SIGN, EBlocks.MAHOGANY_WALL_HANGING_SIGN, p), new Item.Properties().stacksTo(16));
    Item MAHOGANY_BOAT = registerBoat(EItemIds.MAHOGANY_BOAT, EEntities.MAHOGANY_BOAT);
    Item MAHOGANY_CHEST_BOAT = registerBoat(EItemIds.MAHOGANY_CHEST_BOAT, EEntities.MAHOGANY_CHEST_BOAT);
    Item PINE_SIGN = register(EBlockItemIds.PINE_SIGN.item(), p -> new StandingAndWallBlockItem(EBlocks.PINE_SIGN, EBlocks.PINE_WALL_SIGN, Direction.DOWN, p), new Item.Properties().stacksTo(16));
    Item PINE_HANGING_SIGN = register(EBlockItemIds.PINE_HANGING_SIGN.item(), p -> new HangingSignItem(EBlocks.PINE_HANGING_SIGN, EBlocks.PINE_WALL_HANGING_SIGN, p), new Item.Properties().stacksTo(16));
    Item PINE_BOAT = registerBoat(EItemIds.PINE_BOAT, EEntities.PINE_BOAT);
    Item PINE_CHEST_BOAT = registerBoat(EItemIds.PINE_CHEST_BOAT, EEntities.PINE_CHEST_BOAT);

    Item FIR_SIGN = register(EBlockItemIds.FIR_SIGN.item(), p -> new StandingAndWallBlockItem(EBlocks.FIR_SIGN, EBlocks.FIR_WALL_SIGN, Direction.DOWN, p), new Item.Properties().stacksTo(16));
    Item FIR_HANGING_SIGN = register(EBlockItemIds.FIR_HANGING_SIGN.item(), p -> new HangingSignItem(EBlocks.FIR_HANGING_SIGN, EBlocks.FIR_WALL_HANGING_SIGN, p), new Item.Properties().stacksTo(16));
    Item FIR_BOAT = registerBoat(EItemIds.FIR_BOAT, EEntities.FIR_BOAT);
    Item FIR_CHEST_BOAT = registerBoat(EItemIds.FIR_CHEST_BOAT, EEntities.FIR_CHEST_BOAT);
    Item WILLOW_SIGN = register(EBlockItemIds.WILLOW_SIGN.item(), p -> new StandingAndWallBlockItem(EBlocks.WILLOW_SIGN, EBlocks.WILLOW_WALL_SIGN, Direction.DOWN, p), new Item.Properties().stacksTo(16));
    Item WILLOW_HANGING_SIGN = register(EBlockItemIds.WILLOW_HANGING_SIGN.item(), p -> new HangingSignItem(EBlocks.WILLOW_HANGING_SIGN, EBlocks.WILLOW_WALL_HANGING_SIGN, p), new Item.Properties().stacksTo(16));
    Item WILLOW_BOAT = registerBoat(EItemIds.WILLOW_BOAT, EEntities.WILLOW_BOAT);
    Item WILLOW_CHEST_BOAT = registerBoat(EItemIds.WILLOW_CHEST_BOAT, EEntities.WILLOW_CHEST_BOAT);

    Item DIAMOND_NUGGET = register(EItemIds.DIAMOND_NUGGET, Item::new, new Item.Properties());
    Item EMERALD_NUGGET = register(EItemIds.EMERALD_NUGGET, Item::new, new Item.Properties());

    Item SOULGLASS_SHARD = register(EItemIds.SOULGLASS_SHARD, Item::new, new Item.Properties());

    Item RAW_SILVER = register(EItemIds.RAW_SILVER, Item::new, new Item.Properties());
    Item SILVER_INGOT = register(EItemIds.SILVER_INGOT, Item::new, new Item.Properties().trimMaterial(ETrims.SILVER));
    Item SILVER_NUGGET = register(EItemIds.SILVER_NUGGET, Item::new, new Item.Properties());

    Item SOULSTEEL_ALLOY = register(EItemIds.SOULSTEEL_ALLOY, Item::new, new Item.Properties().trimMaterial(ETrims.SOULSTEEL));
    Item SOULSTEEL_UPGRADE_SMITHING_TEMPLATE = register(EItemIds.SOULSTEEL_UPGRADE_SMITHING_TEMPLATE, p -> new SmithingTemplateItem(
                    Component.translatable("item.elsewhere.smithing_template.soulsteel_upgrade.applies_to").withStyle(ChatFormatting.BLUE),
                    Component.translatable("item.elsewhere.smithing_template.soulsteel_upgrade.ingredients").withStyle(ChatFormatting.BLUE),
                    Component.translatable("item.elsewhere.smithing_template.soulsteel_upgrade.base_slot_description"),
                    Component.translatable("item.elsewhere.smithing_template.soulsteel_upgrade.additions_slot_description"),
                    SmithingTemplateItem.createNetheriteUpgradeIconList(), SmithingTemplateItem.createNetheriteUpgradeMaterialList(), p),
            new Item.Properties().rarity(Rarity.UNCOMMON));
    Item SOULSTEEL_HELMET = register(EItemIds.SOULSTEEL_HELMET, Item::new, new AttributeProperties().humanoidArmor(EMaterials.SOULSTEEL_ARMOR, ArmorType.HELMET));
    Item SOULSTEEL_CHESTPLATE = register(EItemIds.SOULSTEEL_CHESTPLATE, Item::new, new AttributeProperties().humanoidArmor(EMaterials.SOULSTEEL_ARMOR, ArmorType.CHESTPLATE));
    Item SOULSTEEL_LEGGINGS = register(EItemIds.SOULSTEEL_LEGGINGS, Item::new, new AttributeProperties().humanoidArmor(EMaterials.SOULSTEEL_ARMOR, ArmorType.LEGGINGS));
    Item SOULSTEEL_BOOTS = register(EItemIds.SOULSTEEL_BOOTS, Item::new, new AttributeProperties().humanoidArmor(EMaterials.SOULSTEEL_ARMOR, ArmorType.BOOTS));
    Item SOULSTEEL_SWORD = register(EItemIds.SOULSTEEL_SWORD, SoulsteelItem::new, new AttributeProperties()
            .attribute(EAttributes.LIFESTEAL, new AttributeModifier(AttributeIDs.SOULSTEEL_LIFESTEAL_ID, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .sword(EMaterials.SOULSTEEL_TOOL, 3.0F, -2.4F));
    Item SOULSTEEL_AXE = register(EItemIds.SOULSTEEL_AXE, SoulsteelItem::new, new AttributeProperties()
            .attribute(EAttributes.LIFESTEAL, new AttributeModifier(AttributeIDs.SOULSTEEL_LIFESTEAL_ID, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .attribute(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(AttributeIDs.SOULSTEEL_REACH_ID, 0.35, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .axe(EMaterials.SOULSTEEL_TOOL, 5.0F, -3.0F));
    Item SOULSTEEL_SPEAR = register(EItemIds.SOULSTEEL_SPEAR, SoulsteelItem::new, new AttributeProperties()
            .attribute(EAttributes.LIFESTEAL, new AttributeModifier(AttributeIDs.SOULSTEEL_LIFESTEAL_ID, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .spear(EMaterials.SOULSTEEL_TOOL,
                    1.05F, 1.075F, 0.5F, 3.0F, 10.0F,
                    6.5F, 5.1F, 10.0F, 4.6F));
    Item SOULSTEEL_PICKAXE = register(EItemIds.SOULSTEEL_PICKAXE, SoulsteelItem::new, new AttributeProperties()
            .attribute(EAttributes.LIFESTEAL, new AttributeModifier(AttributeIDs.SOULSTEEL_LIFESTEAL_ID, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .attribute(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(AttributeIDs.SOULSTEEL_REACH_ID, 0.35, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .pickaxe(EMaterials.SOULSTEEL_TOOL, 1.0F, -2.8F));
    Item SOULSTEEL_SHOVEL = register(EItemIds.SOULSTEEL_SHOVEL, SoulsteelItem::new, new AttributeProperties()
            .attribute(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(AttributeIDs.SOULSTEEL_REACH_ID, 0.35, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .shovel(EMaterials.SOULSTEEL_TOOL, 1.5F, -3.0F));
    Item SOULSTEEL_HOE = register(EItemIds.SOULSTEEL_HOE, SoulsteelItem::new, new AttributeProperties()
            .attribute(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(AttributeIDs.SOULSTEEL_REACH_ID, 0.35, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
            .hoe(EMaterials.SOULSTEEL_TOOL, -3.0F, 0.0F));

    Item ELECTRUM_ALLOY = register(EItemIds.ELECTRUM_ALLOY, Item::new, new Item.Properties().trimMaterial(ETrims.ELECTRUM));
    Item ELECTRUM_UPGRADE_SMITHING_TEMPLATE = register(EItemIds.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, p -> new SmithingTemplateItem(
                    Component.translatable("item.elsewhere.smithing_template.electrum_upgrade.applies_to").withStyle(ChatFormatting.BLUE),
                    Component.translatable("item.elsewhere.smithing_template.electrum_upgrade.ingredients").withStyle(ChatFormatting.BLUE),
                    Component.translatable("item.elsewhere.smithing_template.electrum_upgrade.base_slot_description"),
                    Component.translatable("item.elsewhere.smithing_template.electrum_upgrade.additions_slot_description"),
                    SmithingTemplateItem.createNetheriteUpgradeIconList(), SmithingTemplateItem.createNetheriteUpgradeMaterialList(), p),
            new Item.Properties().rarity(Rarity.UNCOMMON));
    Item ELECTRUM_HELMET = register(EItemIds.ELECTRUM_HELMET, Item::new, new AttributeProperties()
            .humanoidArmor(EMaterials.ELECTRUM_ARMOR, ArmorType.HELMET)
            .attribute(Attributes.MOVEMENT_SPEED, new AttributeModifier(AttributeIDs.ELECTRUM_HELMET_SPEED_ID, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HEAD));
    Item ELECTRUM_CHESTPLATE = register(EItemIds.ELECTRUM_CHESTPLATE, Item::new, new AttributeProperties()
            .humanoidArmor(EMaterials.ELECTRUM_ARMOR, ArmorType.CHESTPLATE)
            .attribute(Attributes.MOVEMENT_SPEED, new AttributeModifier(AttributeIDs.ELECTRUM_CHESTPLATE_SPEED_ID, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.CHEST));
    Item ELECTRUM_LEGGINGS = register(EItemIds.ELECTRUM_LEGGINGS, Item::new, new AttributeProperties()
            .humanoidArmor(EMaterials.ELECTRUM_ARMOR, ArmorType.LEGGINGS)
            .attribute(Attributes.MOVEMENT_SPEED, new AttributeModifier(AttributeIDs.ELECTRUM_LEGGINGS_SPEED_ID, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.LEGS));
    Item ELECTRUM_BOOTS = register(EItemIds.ELECTRUM_BOOTS, Item::new, new AttributeProperties()
            .humanoidArmor(EMaterials.ELECTRUM_ARMOR, ArmorType.BOOTS)
            .attribute(Attributes.MOVEMENT_SPEED, new AttributeModifier(AttributeIDs.ELECTRUM_BOOTS_SPEED_ID, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.FEET));
    Item ELECTRUM_SWORD = register(EItemIds.ELECTRUM_SWORD, Item::new, new AttributeProperties().sword(EMaterials.ELECTRUM_TOOL, 3.0F, -2.1F));
    Item ELECTRUM_AXE = register(EItemIds.ELECTRUM_AXE, Item::new, new AttributeProperties().axe(EMaterials.ELECTRUM_TOOL, 5.0F, -2.9F));
    Item ELECTRUM_SPEAR = register(EItemIds.ELECTRUM_SPEAR, Item::new, new AttributeProperties()
            .spear(EMaterials.ELECTRUM_TOOL,
                    0.9F, 1.075F, 0.45F,
                    4.0F, 10.0F,
                    7.5F, 5.1F,
                    14.0F, 4.6F
            ));
    Item ELECTRUM_PICKAXE = register(EItemIds.ELECTRUM_PICKAXE, Item::new, new AttributeProperties().pickaxe(EMaterials.ELECTRUM_TOOL, 1.0F, -2.5F));
    Item ELECTRUM_SHOVEL = register(EItemIds.ELECTRUM_SHOVEL, Item::new, new AttributeProperties().shovel(EMaterials.ELECTRUM_TOOL, 1.5F, -2.0F));
    Item ELECTRUM_HOE = register(EItemIds.ELECTRUM_HOE, Item::new, new AttributeProperties().hoe(EMaterials.ELECTRUM_TOOL, -3.0F, 1.0F));

    Item URANIUM = register(EItemIds.URANIUM, Item::new, new Item.Properties().trimMaterial(ETrims.URANIUM));
    Item URANIUM_SHARD = register(EItemIds.URANIUM_SHARD, Item::new, new Item.Properties());

    Holder<List<DispenseBehavior>> boltShot = Holder.direct(List.of(ShootProjectileDispenseBehavior.builder().power(2.0F).uncertainty(3.0F).build()));

    Item IRON_BOLT = registerBolt(EItemIds.IRON_BOLT, EEntities.IRON_BOLT);
    Item SILVER_BOLT = registerBolt(EItemIds.SILVER_BOLT, EEntities.SILVER_BOLT);



    Item WEREWOLF_SPAWN_EGG = register(EItemIds.WEREWOLF_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.WEREWOLF));
    Item VANGUARD_SPAWN_EGG = register(EItemIds.VANGUARD_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.VANGUARD));
    Item SUBSLIME_SPAWN_EGG = register(EItemIds.SUBSLIME_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.SUBSLIME));
    Item SWEEPER_SPAWN_EGG = register(EItemIds.SWEEPER_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.SWEEPER));
    Item MUD_GOLEM_SPAWN_EGG = register(EItemIds.MUD_GOLEM_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.MUD_GOLEM));
    Item LOST_SOUL_SPAWN_EGG = register(EItemIds.LOST_SOUL_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.LOST_SOUL));
    Item TROLL_SPAWN_EGG = register(EItemIds.TROLL_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.TROLL));
    Item GRAVEKEEPER_SPAWN_EGG = register(EItemIds.GRAVEKEEPER_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.GRAVEKEEPER));
    Item WHISTLER_SPAWN_EGG = registerSpawnEgg(EItemIds.WHISTLER_SPAWN_EGG, EEntities.WHISTLER);

    Item VANGUARD_MASK = register(EItemIds.VANGUARD_MASK, Item::new, new Item.Properties()
            .humanoidArmor(EMaterials.VANGUARD_MASK, ArmorType.HELMET)
            .component(DataComponents.ATTRIBUTE_MODIFIERS,
                    ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "vanguard_mask_night_vision"),
                                            0.0, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.HEAD, ItemAttributeModifiers.Display.override(
                                            Component.translatable("item.elsewhere.vanguard_mask.night_vision").withStyle(ChatFormatting.BLUE)))
                            .build()
            )
    );

    Item PERCH_SPAWN_EGG = register(EItemIds.PERCH_SPAWN_EGG, SpawnEggItem::new, new Item.Properties().spawns(EEntities.PERCH));

    Item RAW_PERCH = register(EItemIds.RAW_PERCH, Item::new, new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.25F).build()));
    Item COOKED_PERCH = register(EItemIds.COOKED_PERCH, Item::new, new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.75F).build()));
    Item SHINING_PERCH = register(EItemIds.SHINING_PERCH, Item::new, new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(6).saturationModifier(1.25F).alwaysEdible().build(), Consumables.defaultFood()
                    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 45 * 20,1), 1.0F))
                    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 90 * 20,0), 1.0F))
                    .build()));

    Item PERCH_BUCKET = register(EItemIds.PERCH_BUCKET, p -> new MobBucketItem(EEntities.PERCH, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, p), new Item.Properties());

    Item MUSIC_DISC_FOX = register(EItemIds.MUSIC_DISC_FOX, Item::new, new Item.Properties().jukeboxPlayable(EDiscs.FOX).rarity(Rarity.UNCOMMON).stacksTo(1));

    Item DUNGEON_KEY = register(EItemIds.DUNGEON_KEY, Item::new, new Item.Properties());
    Item OMINOUS_DUNGEON_KEY = register(EItemIds.OMINOUS_DUNGEON_KEY, Item::new, new Item.Properties());





    // Shields
    Item REINFORCED_SHIELD = register(EItemIds.REINFORCED_SHIELD, p -> new ConfigurableShieldItem(p, 70, 160, 15.0F), new Item.Properties()
            .durability(672)
            .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
            .repairable(ItemTags.DIAMOND_TOOL_MATERIALS).equippableUnswappable(EquipmentSlot.OFFHAND)
            .delayedComponent(DataComponents.BLOCKS_ATTACKS, context -> new BlocksAttacks(
                    0.0F, 1.0F,
                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                    Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                    Optional.of(SoundEvents.SHIELD_BLOCK),
                    Optional.of(SoundEvents.SHIELD_BREAK)))
            .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
    );

    Item QUICK_SHIELD = register(EItemIds.QUICK_SHIELD, p -> new ConfigurableShieldItem(p, 15, 50, 8.0F), new Item.Properties()
            .durability(434)
            .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
            .repairable(ItemTags.WOODEN_TOOL_MATERIALS).equippableUnswappable(EquipmentSlot.OFFHAND)
            .delayedComponent(DataComponents.BLOCKS_ATTACKS, (context) -> new BlocksAttacks(
                    0.0F, 1.0F,
                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                    Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                    Optional.of(SoundEvents.SHIELD_BLOCK),
                    Optional.of(SoundEvents.SHIELD_BREAK)))
            .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
    );









    // Method
    static <GenericItem extends Item> GenericItem register(ResourceKey<Item> id, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        GenericItem item = itemFactory.apply(settings.setId(id));
        Registry.register(BuiltInRegistries.ITEM, id, item);
        return item;
    }
    private static Item registerBoat(final ResourceKey<Item> id, final EntityType<? extends AbstractBoat> type) {
        return register(id, BoatItem::new, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1).spawns(type).dispensed(DispenseBehaviorIds.BOAT));
    }
    private static Item registerSpawnEgg(final ResourceKey<Item> id, final EntityType<?> type) {
        return register(id, SpawnEggItem::new, new Item.Properties().spawns(type).dispensed(DispenseBehaviorIds.SPAWN_EGG));
    }
    private static Item registerBolt(final ResourceKey<Item> id, final EntityType<? extends Bolt> type) {
        return register(id, p -> new BoltItem(p, type), new Item.Properties().stacksTo(16).component(DataComponents.DISPENSE_BEHAVIOR, boltShot));
    }


    // Initialize
    static void initialize() {
        BoltStats.register(EEntities.IRON_BOLT, new BoltStats(3.0, 0.08, 2, false, () -> IRON_BOLT));
        BoltStats.register(EEntities.SILVER_BOLT, new BoltStats(2.5, 0.08, 2, true, () -> SILVER_BOLT));
    }
}