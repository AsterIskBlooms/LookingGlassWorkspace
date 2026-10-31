package team.lookingglass.stratum.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import team.lookingglass.stratum.Stratum;

public class StratumNoises {
    public static final ResourceKey<NormalNoise> EDGE_TEXTURE = createKey("edge_texture");
    public static final ResourceKey<NormalNoise> SLOPE_VARIATION = createKey("slope_variation");
    public static final ResourceKey<NormalNoise> HILLS = createKey("hills");
    public static final ResourceKey<NormalNoise> SPIRES = createKey("spires");

    public static void bootstrap(BootstrapContext<NormalNoise> context) {
        context.register(EDGE_TEXTURE, NormalNoise.createParity(-5, 1.0, 1.0, 1.0));
        context.register(SLOPE_VARIATION, NormalNoise.createParity(-7, 1.0, 1.0));
        context.register(HILLS, NormalNoise.createParity(-6, 1.0, 1.1, 0.85));
        context.register(SPIRES, NormalNoise.createParity(-5, 1.0, 1.0, 0.5));
    }

    private static ResourceKey<NormalNoise> createKey(String path) {
        return ResourceKey.create(Registries.NOISE, Identifier.fromNamespaceAndPath(Stratum.MODID, path));
    }
}