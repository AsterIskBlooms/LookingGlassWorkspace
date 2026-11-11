package team.lookingglass.elsewhere.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import team.lookingglass.mirrorlib.world.density.DensityModifiers;

public class EDensityEdits {
    public static void initialize() {

//        DensityModifiers.register(NoiseRouterData.BASE_3D_NOISE_OVERWORLD, (current, registries) -> {
//            DensityFunction erosion = NoiseRouterData.getFunction(registries.lookupOrThrow(Registries.DENSITY_FUNCTION), NoiseRouterData.OVERWORLD_FUNCTIONS.erosion());
//            DensityFunction soft = new BlendedNoise(0.15D, 0.08D, 80.0D, 160.0D, 8.0D);
//            DensityFunction vanillaWeight = DensityFunctions.clampedMap(erosion, -0.2F, 0.45F, 0.0F, 1.0F);
//            return DensityFunctions.lerp(vanillaWeight, soft, current);
//        });

    }
}
