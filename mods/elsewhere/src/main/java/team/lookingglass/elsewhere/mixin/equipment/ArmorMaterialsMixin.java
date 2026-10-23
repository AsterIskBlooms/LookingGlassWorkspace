package team.lookingglass.elsewhere.mixin.equipment;

import com.google.common.collect.Maps;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ArmorMaterials.class)
public interface ArmorMaterialsMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void patchMaterials(CallbackInfo ci) {
        mutate(ArmorMaterials.LEATHER, 5, makeDefense(1, 3, 2, 1, 3), 6, 0.0F, 0.0F);
        mutate(ArmorMaterials.COPPER, 5, makeDefense(2, 4, 3, 1, 4), 8, 0.0F, 0.0F);
        mutate(ArmorMaterials.CHAINMAIL, 18, makeDefense(2, 4, 3, 1, 6), 12, 0.0F, 0.1F);
        mutate(ArmorMaterials.IRON, 15, makeDefense(3, 5, 4, 2, 9), 9, 0.0F, 0.0F);
        mutate(ArmorMaterials.GOLD, 7, makeDefense(2, 4, 3, 1, 7), 25, 0.0F, 0.0F);
    }

    @Unique
    private static void mutate(ArmorMaterial mat, int durability, Map<ArmorType, Integer> defense, int enchantmentValue, float toughness, float knockbackResistance) {
        mat.durability = durability;
        mat.defense = defense;
        mat.enchantmentValue = enchantmentValue;
        mat.toughness = toughness;
        mat.knockbackResistance = knockbackResistance;
    }

    @Unique
    private static Map<ArmorType, Integer> makeDefense( int helm, int chest, int legs, int boots, int body) {
        return Maps.newEnumMap(Map.of(
                ArmorType.BOOTS, boots,
                ArmorType.LEGGINGS, legs,
                ArmorType.CHESTPLATE, chest,
                ArmorType.HELMET, helm,
                ArmorType.BODY, body));
    }
}