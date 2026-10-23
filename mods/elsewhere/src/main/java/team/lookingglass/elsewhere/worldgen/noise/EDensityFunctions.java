package team.lookingglass.elsewhere.worldgen.noise;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import team.lookingglass.elsewhere.Elsewhere;

public class EDensityFunctions {
    public static final ResourceKey<DensityFunction> TEMPERATURE = createKey("temperature");
    public static final ResourceKey<DensityFunction> VANILLA_RIDGES = createKey("vanilla_ridges");
    public static final ResourceKey<DensityFunction> RIVERLESS_RIDGES = createKey("riverless_ridges");

    public static void bootstrap(BootstrapContext<DensityFunction> context) {
        HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise> noises = context.lookup(Registries.NOISE);

        DensityFunction shiftX = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_X);
        DensityFunction shiftZ = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_Z);

        context.register(TEMPERATURE, DensityFunctions.cache(DensityFunctions.cache(DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25, noises.getOrThrow(Noises.TEMPERATURE)))));
        DensityFunction vanillaRidges = NoiseRouterData.registerAndWrap(context, VANILLA_RIDGES, DensityFunctions.cache(DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25, noises.getOrThrow(Noises.RIDGE))));

        DensityFunction sign = DensityFunctions.rangeChoice(vanillaRidges, -1000000.0F, 0.0F,
                DensityFunctions.constant(-1.0F), DensityFunctions.constant(1.0F));
        DensityFunction magnitude = DensityFunctions.mul(DensityFunctions.constant(0.2F), sign);
        DensityFunction riverlessRidges = DensityFunctions.rangeChoice(vanillaRidges, -0.15F, 0.15F, magnitude, vanillaRidges);

        context.register(RIVERLESS_RIDGES, riverlessRidges);
    }

    private static ResourceKey<DensityFunction> createKey(String path) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.fromNamespaceAndPath(Elsewhere.MODID, path));
    }
}
