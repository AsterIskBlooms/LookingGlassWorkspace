package team.lookingglass.elsewhere.mixin.equipment;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PickaxePierceMixin {
    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void elsewhere$bypassArmorForPickaxes(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        ItemStack weaponItem = damageSource.getWeaponItem();
        if (weaponItem != null && weaponItem.is(ItemTags.PICKAXES)) {
            cir.setReturnValue(damage);
        }
    }
}