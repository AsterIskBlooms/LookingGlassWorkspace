package team.lookingglass.elsewhere.mixin.equipment.shield;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.registry.util.shield.ShieldProfile;
import team.lookingglass.elsewhere.registry.util.shield.ShieldProfiles;

import java.util.function.Consumer;

@Mixin(Item.class)
public class ShieldItemMixin {
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void elsewhere$appendShieldTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type, CallbackInfo ci) {
        if (!(stack.getItem() instanceof ShieldItem) || stack.getItem() instanceof ShieldProfile) { return; }

        textConsumer.accept(Component.literal("").withStyle(ChatFormatting.GRAY));
        textConsumer.accept(Component.literal("When Blocking:").withStyle(ChatFormatting.GRAY));

        float release = ShieldProfiles.releaseCooldownTicks(stack) / 20.0F;
        String releaseText = release % 1 == 0 ? Integer.toString((int) release) : Float.toString(release);
        float disable = ShieldProfiles.disableCooldownTicks(stack) / 20.0F;
        String disableText = disable % 1 == 0 ? Integer.toString((int) disable) : Float.toString(disable);
        float damage = ShieldProfiles.blockThreshold(stack);
        String damageText = damage % 1 == 0 ? Integer.toString((int) damage) : Float.toString(damage);

        textConsumer.accept(Component.literal(" " + damageText + " Defense").withStyle(ChatFormatting.DARK_GREEN));
        textConsumer.accept(Component.literal(" " + releaseText + "s Cooldown").withStyle(ChatFormatting.DARK_GREEN));
        textConsumer.accept(Component.literal(" " + disableText + "s Heavy Cooldown").withStyle(ChatFormatting.DARK_GREEN));
    }
}