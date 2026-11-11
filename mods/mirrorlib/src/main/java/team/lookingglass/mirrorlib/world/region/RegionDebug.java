package team.lookingglass.mirrorlib.world.region;

import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;

public final class RegionDebug {
    private RegionDebug() {
    }

    public static String describe(final RegionSelector selector, final int blockX, final int blockZ) {
        int centerX = QuartPos.toBlock(QuartPos.fromBlock(blockX)) + 2;
        int centerZ = QuartPos.toBlock(QuartPos.fromBlock(blockZ)) + 2;
        int index = selector.select(centerX, centerZ);
        return displayName(RegionRegistry.freezeAndGet().regions().get(index).key());
    }

    private static String displayName(final ResourceKey<Region> key) {
        StringBuilder name = new StringBuilder();

        for (String word : key.identifier().getPath().split("_")) {
            if (word.isEmpty()) {
                continue;
            }
            if (!name.isEmpty()) {
                name.append(' ');
            }
            name.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }

        return name.toString();
    }
}