package team.lookingglass.elsewhere.registry.render;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import team.lookingglass.elsewhere.registry.EDataComponents;

public class ETooltips {
    public static void register() {
        ItemTooltipCallback.EVENT.register((stack, context, flag, lines) -> {
            if (!stack.getOrDefault(EDataComponents.GLOWING_TRIM, false)) return;
            lines.add(Component.translatable("item.elsewhere.glowing_trim").withStyle(ChatFormatting.AQUA));
        });
    }
}
