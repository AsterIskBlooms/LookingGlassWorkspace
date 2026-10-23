package team.lookingglass.elsewhere.registry.util.shield;

import net.minecraft.world.item.ItemStack;

public final class ShieldProfiles {
    public static final int DEFAULT_RELEASE_COOLDOWN_TICKS = 40;
    public static final int DEFAULT_DISABLE_COOLDOWN_TICKS = 100;
    public static final float DEFAULT_BLOCK_THRESHOLD = 10.0F;

    private ShieldProfiles() {}

    public static int releaseCooldownTicks(ItemStack stack) {
        return stack.getItem() instanceof ShieldProfile profile ? profile.releaseCooldownTicks() : DEFAULT_RELEASE_COOLDOWN_TICKS;
    }

    public static int disableCooldownTicks(ItemStack stack) {
        return stack.getItem() instanceof ShieldProfile profile ? profile.disableCooldownTicks() : DEFAULT_DISABLE_COOLDOWN_TICKS;
    }

    public static float blockThreshold(ItemStack stack) {
        return stack.getItem() instanceof ShieldProfile profile ? profile.blockThreshold() : DEFAULT_BLOCK_THRESHOLD;
    }
}