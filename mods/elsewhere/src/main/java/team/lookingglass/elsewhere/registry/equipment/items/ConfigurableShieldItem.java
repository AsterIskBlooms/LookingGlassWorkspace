package team.lookingglass.elsewhere.registry.equipment.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import team.lookingglass.elsewhere.registry.util.shield.ShieldProfile;
import team.lookingglass.elsewhere.registry.util.shield.ShieldProfiles;

import java.util.function.Consumer;

public class ConfigurableShieldItem extends ShieldItem implements ShieldProfile {
    private final int releaseCooldownTicks;
    private final int disableCooldownTicks;
    private final float blockThreshold;

    public ConfigurableShieldItem(Properties properties, int releaseCooldownTicks, int disableCooldownTicks, float blockThreshold) {
        super(properties);
        this.releaseCooldownTicks = releaseCooldownTicks;
        this.disableCooldownTicks = disableCooldownTicks;
        this.blockThreshold = blockThreshold;
    }

    @Override
    public int releaseCooldownTicks() {
        return releaseCooldownTicks;
    }

    @Override
    public int disableCooldownTicks() {
        return disableCooldownTicks;
    }

    @Override
    public float blockThreshold() {
        return blockThreshold;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
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