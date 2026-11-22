package team.lookingglass.elsewhere.registry.util.bolt;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public final class BoltTooltips {
    public static void addDamageLines(Consumer<Component> out, double power, boolean magic) {
        out.accept(Component.literal("").withStyle(ChatFormatting.GRAY));
        out.accept(Component.literal("When Fired:").withStyle(ChatFormatting.GRAY));
        out.accept(Component.literal(" " + fmt(power) + (magic ? " Magic Power" : " Power")).withStyle(ChatFormatting.DARK_GREEN));
    }

    private static String fmt(double value) {
        return value % 1 == 0 ? Integer.toString((int) value) : Double.toString(value);
    }
}