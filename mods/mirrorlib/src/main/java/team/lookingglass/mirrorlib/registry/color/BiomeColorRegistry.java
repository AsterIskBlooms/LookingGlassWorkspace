package team.lookingglass.mirrorlib.registry.color;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public final class BiomeColorRegistry {
    private static final Map<ResourceKey<Biome>, GrassColorProvider> REGISTERED = new HashMap<>();
    private static volatile Map<Biome, GrassColorProvider> bound = new IdentityHashMap<>();

    private BiomeColorRegistry() {}

    /** Call during mod init. */
    public static void register(ResourceKey<Biome> biome, GrassColorProvider provider) {
        REGISTERED.put(biome, provider);
    }

    /** Called by MirrorLib when a world's biome registry is available. */
    public static void bind(Registry<Biome> registry) {
        Map<Biome, GrassColorProvider> map = new IdentityHashMap<>();
        REGISTERED.forEach((key, provider) ->
                registry.get(key).ifPresent(holder -> map.put(holder.value(), provider)));
        bound = map;
    }

    public static void clear() {
        bound = new IdentityHashMap<>();
    }

    public static @Nullable GrassColorProvider get(Biome biome) {
        Map<Biome, GrassColorProvider> map = bound;
        return map.isEmpty() ? null : map.get(biome);
    }
}
