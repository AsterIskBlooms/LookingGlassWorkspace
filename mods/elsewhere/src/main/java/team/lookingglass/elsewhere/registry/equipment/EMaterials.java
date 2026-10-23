package team.lookingglass.elsewhere.registry.equipment;

import com.google.common.collect.Maps;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.tags.EBlockTags;
import team.lookingglass.elsewhere.registry.tags.EItemTags;

import java.util.Map;

public interface EMaterials {

    ResourceKey<EquipmentAsset> BRONZE_ARMOR_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "bronze"));
    ToolMaterial BRONZE_TOOL = new ToolMaterial(
            EBlockTags.INCORRECT_FOR_BRONZE_TOOL,
            620,
            7F,
            2.5F,
            11,
            EItemTags.REPAIRS_BRONZE_ARMOR
    );
    ArmorMaterial BRONZE_ARMOR = new ArmorMaterial(
            19,
            makeDefense(3, 6, 5, 2),
            11,
            SoundEvents.ARMOR_EQUIP_GOLD,
            0F,
            0.0F,
            EItemTags.REPAIRS_BRONZE_ARMOR,
            BRONZE_ARMOR_KEY
    );

    ResourceKey<EquipmentAsset> SOULSTEEL_ARMOR_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "soulsteel"));
    ToolMaterial SOULSTEEL_TOOL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            1261,
            10F,
            2F,
            13,
            EItemTags.REPAIRS_SOULSTEEL_ARMOR
    );
    ArmorMaterial SOULSTEEL_ARMOR = new ArmorMaterial(
            25,
            makeDefense(3, 7, 5, 3),
            13,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            1F,
            0.1F,
            EItemTags.REPAIRS_SOULSTEEL_ARMOR,
            SOULSTEEL_ARMOR_KEY
    );

    ResourceKey<EquipmentAsset> ELECTRUM_ARMOR_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "electrum"));
    ToolMaterial ELECTRUM_TOOL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            960,
            12F,
            3F,
            19,
            EItemTags.REPAIRS_ELECTRUM_ARMOR
    );
    ArmorMaterial ELECTRUM_ARMOR = new ArmorMaterial(
            22,
            makeDefense(3, 8, 6, 3),
            19,
            SoundEvents.ARMOR_EQUIP_GOLD,
            0F,
            0.0F,
            EItemTags.REPAIRS_ELECTRUM_ARMOR,
            ELECTRUM_ARMOR_KEY
    );

    ResourceKey<EquipmentAsset> VANGUARD_MASK_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "vanguard_mask"));
    ArmorMaterial VANGUARD_MASK = new ArmorMaterial(
            18,
            Maps.newEnumMap(Map.of(ArmorType.HELMET, 0)),
            12,
            SoundEvents.ARMOR_EQUIP_WOLF,
            0F,
            0.0F,
            EItemTags.REPAIRS_VANGUARD_MASK,
            VANGUARD_MASK_KEY
    );












    private static Map<ArmorType, Integer> makeDefense( int helm, int chest, int legs, int boots) {
        return Maps.newEnumMap(Map.of(
                ArmorType.BOOTS, boots,
                ArmorType.LEGGINGS, legs,
                ArmorType.CHESTPLATE, chest,
                ArmorType.HELMET, helm));
    }
    private static Map<ArmorType, Integer> makeDefense( int helm, int chest, int legs, int boots, int body) {
        return Maps.newEnumMap(Map.of(
                ArmorType.BOOTS, boots,
                ArmorType.LEGGINGS, legs,
                ArmorType.CHESTPLATE, chest,
                ArmorType.HELMET, helm,
                ArmorType.BODY, body));
    }
}