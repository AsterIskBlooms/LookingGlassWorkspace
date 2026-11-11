package team.lookingglass.mirrorlib.world.region;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import team.lookingglass.mirrorlib.MirrorLib;

public final class Region {
    public static final ResourceKey<Registry<Region>> REGISTRY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(MirrorLib.MODID, "region"));
    public static final ResourceKey<Region> VANILLA = key("vanilla");

    public static ResourceKey<Region> key(String path) {
        return ResourceKey.create(REGISTRY, Identifier.withDefaultNamespace(path));
    }
    public static ResourceKey<Region> key(String namespace, String path) {
        return ResourceKey.create(REGISTRY, Identifier.fromNamespaceAndPath(namespace, path));
    }
}
