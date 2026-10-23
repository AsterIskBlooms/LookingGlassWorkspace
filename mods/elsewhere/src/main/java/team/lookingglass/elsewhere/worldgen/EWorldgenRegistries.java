package team.lookingglass.elsewhere.worldgen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import team.lookingglass.elsewhere.worldgen.features.EFeatures;
import team.lookingglass.elsewhere.worldgen.features.EPlacements;
import team.lookingglass.elsewhere.worldgen.noise.EDensityFunctions;

public final class EWorldgenRegistries {
    public static void addTo(RegistrySetBuilder builder) {
        builder.add(Registries.FEATURE, EFeatures::bootstrap);
        builder.add(Registries.PLACED_FEATURE, EPlacements::bootstrap);
        builder.add(Registries.BIOME, EBiomes::bootstrap);
        builder.add(Registries.DENSITY_FUNCTION, EDensityFunctions::bootstrap);
    }
}
