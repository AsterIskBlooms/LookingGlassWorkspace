package team.lookingglass.elsewhere.mixin.equipment.arrow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.registry.util.bolt.BoltTooltips;

import java.util.function.Consumer;

@Mixin(Item.class)
public class ArrowTooltipMixin {
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void elsewhere$arrowTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag, CallbackInfo ci) {
        if (stack.is(Items.ARROW)) {
            BoltTooltips.addDamageLines(consumer, AbstractArrow.ARROW_BASE_DAMAGE, false);
        }
    }
}