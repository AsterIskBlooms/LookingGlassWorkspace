package team.lookingglass.elsewhere.mixin.equipment.shield;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class ShieldBlockDelayMixin {
    @ModifyExpressionValue(method = "getItemBlockingWith", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/BlocksAttacks;blockDelayTicks()I"))
    private int elsewhere$removeShieldDelay(int delay) {
        LivingEntity self = (LivingEntity)(Object)this;
        ItemStack stack = self.getUseItem();
        if (stack.getItem() instanceof ShieldItem) { return 0; }
        return delay;
    }
}
