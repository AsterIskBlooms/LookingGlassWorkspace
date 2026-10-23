package team.lookingglass.elsewhere.mixin.equipment;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import team.lookingglass.elsewhere.registry.tags.EItemTags;

@Mixin(Item.Properties.class)
public abstract class SpearMixin {
    @ModifyConstant(method = "spear", constant = @Constant(floatValue = 0.0F))
    private float elsewhere$increaseSpearBaseDamage(float original) {
        return 4.0F;
    }

    @ModifyVariable(method = "spear", at = @At("HEAD"), argsOnly = true, name = "attackDuration")
    private float elsewhere$forceSpearAttackDuration(float attackDuration) {
        if (attackDuration == 0.9F) {
            return attackDuration;
        }
        return 0.8F;
    }

    @ModifyVariable(method = "spear", at = @At("HEAD"), argsOnly = true, name = "damageMultiplier")
    private float elsewhere$forceSpearDamageMultiplier(float damageMultiplier) {
        return 1.05F;
    }

    @ModifyVariable(method = "spear", at = @At("HEAD"), argsOnly = true, name = "delay")
    private float elsewhere$forceSpearDelay(float delay) {
        return 0.5F;
    }

    @ModifyVariable(method = "spear", at = @At("HEAD"), argsOnly = true, name = "dismountTime")
    private float elsewhere$forceSpearDismountTime(float dismountTime) {
        return 3.5F;
    }

    @ModifyVariable(method = "spear", at = @At("HEAD"), argsOnly = true, name = "dismountThreshold")
    private float elsewhere$forceSpearDismountThreshold(float dismountThreshold) {
        return 11.0F;
    }

    @ModifyVariable(method = "spear", at = @At("HEAD"), argsOnly = true, name = "knockbackTime")
    private float elsewhere$forceSpearKnockbackTime(float knockbackTime) {
        return 6.5F;
    }

    @ModifyVariable(method = "spear", at = @At("HEAD"), argsOnly = true, name = "damageTime")
    private float elsewhere$forceSpearDamageTime(float damageTime) {
        return 12.5F;
    }
}
