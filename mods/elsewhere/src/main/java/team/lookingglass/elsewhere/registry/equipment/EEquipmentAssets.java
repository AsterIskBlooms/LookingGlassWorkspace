package team.lookingglass.elsewhere.registry.equipment;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import team.lookingglass.elsewhere.Elsewhere;

public interface EEquipmentAssets {
    ResourceKey<EquipmentAsset> BRONZE = createKey("bronze");
    ResourceKey<EquipmentAsset> SOULSTEEL = createKey("soulsteel");
    ResourceKey<EquipmentAsset> ELECTRUM = createKey("electrum");

    private static ResourceKey<EquipmentAsset> createKey(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }
}