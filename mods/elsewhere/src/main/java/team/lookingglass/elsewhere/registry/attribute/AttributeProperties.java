package team.lookingglass.elsewhere.registry.attribute;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class AttributeProperties extends Item.Properties {
    private ItemAttributeModifiers currentAttributes = ItemAttributeModifiers.EMPTY;

    @Override
    public Item.Properties attributes(ItemAttributeModifiers attributes) {
        for (ItemAttributeModifiers.Entry entry : attributes.modifiers()) {
            currentAttributes = currentAttributes.withModifierAdded(entry.attribute(), entry.modifier(), entry.slot());
        }
        return super.attributes(currentAttributes);
    }

    public AttributeProperties attribute(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
        currentAttributes = currentAttributes.withModifierAdded(attribute, modifier, slot);
        super.attributes(currentAttributes);
        return this;
    }

    @Override
    public AttributeProperties fireResistant() {
        super.fireResistant();
        return this;
    }

    @Override
    public AttributeProperties sword(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        super.sword(material, attackDamageBaseline, attackSpeedBaseline);
        return this;
    }

    @Override
    public AttributeProperties axe(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        super.axe(material, attackDamageBaseline, attackSpeedBaseline);
        return this;
    }

    @Override
    public AttributeProperties spear(ToolMaterial material, float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold) {
        super.spear(material, attackDuration, damageMultiplier, delay, dismountTime, dismountThreshold, knockbackTime, knockbackThreshold, damageTime, damageThreshold);
        return this;
    }

    @Override
    public AttributeProperties pickaxe(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        super.pickaxe(material, attackDamageBaseline, attackSpeedBaseline);
        return this;
    }

    @Override
    public AttributeProperties shovel(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        super.shovel(material, attackDamageBaseline, attackSpeedBaseline);
        return this;
    }

    @Override
    public AttributeProperties hoe(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        super.hoe(material, attackDamageBaseline, attackSpeedBaseline);
        return this;
    }

    @Override
    public AttributeProperties humanoidArmor(ArmorMaterial material, ArmorType type) {
        super.humanoidArmor(material, type);
        return this;
    }
}