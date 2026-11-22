package team.lookingglass.elsewhere.mixin.equipment.arrow;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.elsewhere.registry.tags.EItemTags;

import java.util.function.Predicate;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
    @Inject(method = "getSupportedHeldProjectiles", at = @At("RETURN"), cancellable = true)
    private void elsewhere$heldBolts(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue(cir.getReturnValue().or(stack -> stack.is(EItemTags.BOLTS)));
    }

    @Inject(method = "getAllSupportedProjectiles", at = @At("RETURN"), cancellable = true)
    private void elsewhere$allBolts(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue(cir.getReturnValue().or(stack -> stack.is(EItemTags.BOLTS)));
    }
}
