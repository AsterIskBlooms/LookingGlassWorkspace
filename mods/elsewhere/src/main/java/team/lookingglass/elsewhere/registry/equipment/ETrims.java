package team.lookingglass.elsewhere.registry.equipment;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.mirrorlib.trim.TrimKit;

public interface ETrims {
    ResourceKey<TrimMaterial> TIN = TrimKit.trimKey(Elsewhere.MODID, "tin");
    ResourceKey<TrimMaterial> SILVER = TrimKit.trimKey(Elsewhere.MODID, "silver");
    ResourceKey<TrimMaterial> BRONZE = TrimKit.trimKey(Elsewhere.MODID, "bronze");
    ResourceKey<TrimMaterial> SOULSTEEL = TrimKit.trimKey(Elsewhere.MODID, "soulsteel");
    ResourceKey<TrimMaterial> ELECTRUM = TrimKit.trimKey(Elsewhere.MODID, "electrum");
    ResourceKey<TrimMaterial> URANIUM = TrimKit.trimKey(Elsewhere.MODID, "uranium");

    TrimKit KIT = TrimKit.builder(Elsewhere.MODID)
            .trim(TIN, 0xDBDBC0)
            .trim(SILVER, 0xDEF6EE)
            .trim(URANIUM, 0x97FF44)

            .trim(BRONZE, 0xC4864A, EEquipmentAssets.BRONZE).ownedArmor(EEquipmentAssets.BRONZE)
            .trim(SOULSTEEL, 0x758894, EEquipmentAssets.SOULSTEEL).ownedArmor(EEquipmentAssets.SOULSTEEL)
            .trim(ELECTRUM, 0xEAE297, EEquipmentAssets.ELECTRUM).ownedArmor(EEquipmentAssets.ELECTRUM)

            .targetSet("minecraft", "leather", EquipmentAssets.LEATHER, true)
            .targetSet("minecraft", "copper", EquipmentAssets.COPPER, false)
            .targetSet("minecraft", "chainmail", EquipmentAssets.CHAINMAIL, false)
            .targetSet("minecraft", "iron", EquipmentAssets.IRON, false)
            .targetSet("minecraft", "golden", EquipmentAssets.GOLD, false)
            .targetSet("minecraft", "diamond", EquipmentAssets.DIAMOND, false)
            .targetSet("minecraft", "netherite", EquipmentAssets.NETHERITE, false)
            .build();
}