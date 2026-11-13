package team.lookingglass.mirrorlib.world.cave;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public final class CaveRegistry {
    private static final List<Consumer<Underground>> ENTRIES = new ArrayList<>();
    private static final Set<ResourceKey<Biome>> SUPPRESSED = new HashSet<>();

    private CaveRegistry() {}

    /** Add cave points. Call during mod init, before any world's biome source is built. */
    public static void add(Consumer<Underground> caves) {
        ENTRIES.add(caves);
    }

    /** Optional: drop a vanilla underground biome (e.g. Biomes.SULFUR_CAVES) so yours can replace it. */
    public static void suppressVanilla(ResourceKey<Biome> biome) {
        SUPPRESSED.add(biome);
    }

    public static boolean isSuppressed(ResourceKey<Biome> biome) { return SUPPRESSED.contains(biome); }

    public static void apply(Underground u) {
        ENTRIES.forEach(e -> e.accept(u));
    }
}
