package team.lookingglass.elsewhere.mixin.equipment;

import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToolMaterial.class)
public abstract class ToolMaterialMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void patchMaterials(CallbackInfo ci) {
        mutate(ToolMaterial.WOOD, 1000, 3.0F, -1.0F, 5);
        mutate(ToolMaterial.STONE, 161, 4.0F, 0.0F, 5);
        mutate(ToolMaterial.COPPER, 212, 5.0F, 1.0F, 13);
        mutate(ToolMaterial.IRON, 350, 6.0F, 2.0F, 14);
        mutate(ToolMaterial.GOLD, 232, 12.0F, 2.0F, 25);
    }

    @Unique
    private static void mutate(ToolMaterial mat, int durability, float speed, float attackDamageBonus, int enchantmentValue) {
        mat.durability = durability;
        mat.speed = speed;
        mat.attackDamageBonus = attackDamageBonus;
        mat.enchantmentValue = enchantmentValue;
    }
}
