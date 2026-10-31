package team.lookingglass.stratum.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.levelgen.densityfunction.op.SplineFunction;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import team.lookingglass.stratum.Stratum;

public class StratumDensityFunctions {
    public static final ResourceKey<DensityFunction> TEMPERATURE = createKey("temperature");
    public static final ResourceKey<DensityFunction> VANILLA_RIDGES = createKey("vanilla_ridges");
    public static final ResourceKey<DensityFunction> RIVERLESS_RIDGES = createKey("riverless_ridges");
    public static final ResourceKey<DensityFunction> OFFSET = createKey("offset");

    private static final float SEA_BASE = -0.49F; // ~y 65, just above sea level
    private static final float K_LOW_E_HIGH_C = 1.25F; // jagged peaks, cliffs
    private static final float K_HIGH_E_HIGH_C = 0.5F; // rolling hills, gentle mountains
    private static final float K_LOW_E_LOW_C = 0.9F; // soft but mountainous
    private static final float K_HIGH_E_LOW_C = 0.1F; // flat plains
    private static final float LOW_C_LIFT = 0.04F; // ~5 blocks
    private static final float FLAT_KEEP = 0.5F; // relief kept on plains-level erosion (1 = vanilla, 0 = dead flat)
    private static final float PLATEAU_TOP = 0.02F; // plateau height, y ~130
    private static final float PLATEAU_KEEP = 0.3F; // relief kept on plateau tops (lower = flatter)
    private static final float RING_LIFT = 0.08F; // ring around Pale Garden, ~10 blocks up
    private static final float GARDEN_DEPTH = 0.12F; // garden floor, ~15 blocks below the ring base
    private static final float BADLANDS_STEP = 0.08F; // terrace height, ~10 blocks
    private static final float HILL_BLOCKS = 45.0F; // peak added height, ~y blocks
    private static final float SPIRE_BLOCKS = 55.0F; // max spire height above the surrounding jungle
    private static final float SAVANNA_KEEP = 0.08F; // relief kept on savanna ground (1 = unchanged, 0 = dead flat)
    private static final float DESERT_KEEP = 0.05F; // relief kept on desert ground (1 = unchanged, 0 = dead flat)
    private static final float SAVANNA_TOP_Y = 84.0F; // savanna plateau top, in blocks
    private static final float SAVANNA_SPIRE_BLOCKS = 82.0F; // taller than jungle spires

    public static void bootstrap(BootstrapContext<DensityFunction> context) {
        HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise> noises = context.lookup(Registries.NOISE);
        var names = NoiseRouterData.OVERWORLD_FUNCTIONS;


        DensityFunction shiftX = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_X);
        DensityFunction shiftZ = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_Z);
        DensityFunction temperature = NoiseRouterData.registerAndWrap(context, TEMPERATURE, DensityFunctions.cache(DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25, noises.getOrThrow(Noises.TEMPERATURE))));
        DensityFunction continentsFn = NoiseRouterData.getFunction(functions, names.continents());
        DensityFunction erosionFn = NoiseRouterData.getFunction(functions, names.erosion());

        // Riverless Ridges
        float riverFloor = 0.2F;
        float ridgeBump = 0.3F;
        DensityFunction vanillaRidges = NoiseRouterData.registerAndWrap(context, VANILLA_RIDGES,
                DensityFunctions.cache(DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25, noises.getOrThrow(Noises.RIDGE))));
        DensityFunction sign = DensityFunctions.rangeChoice(vanillaRidges, -1000000.0F, 0.0F,
                DensityFunctions.constant(-1.0F), DensityFunctions.constant(1.0F));
        DensityFunction absRidges = vanillaRidges.abs();
        DensityFunction falloff = DensityFunctions.max(DensityFunctions.constant(0.0F),
                DensityFunctions.add(DensityFunctions.constant(1.0F),
                        DensityFunctions.mul(DensityFunctions.constant(-1.0F / riverFloor), absRidges)));
        DensityFunction bumpedFloor = DensityFunctions.add(DensityFunctions.constant(riverFloor),
                DensityFunctions.mul(DensityFunctions.constant(ridgeBump), falloff));
        context.register(RIVERLESS_RIDGES,
                DensityFunctions.mul(sign, DensityFunctions.max(absRidges, bumpedFloor)));

        // Offset
        var continents = new SplineFunction.Coordinate(continentsFn);
        var erosion = new SplineFunction.Coordinate(erosionFn);
        var ridgesFolded = new SplineFunction.Coordinate(NoiseRouterData.getFunction(functions, NoiseRouterData.RIDGES_FOLDED));
        DensityFunction foldedFn = NoiseRouterData.getFunction(functions, NoiseRouterData.RIDGES_FOLDED);
        DensityFunction vegetationFn = NoiseRouterData.getFunction(functions, names.vegetation());
        DensityFunction vanillaOffset = DensityFunctions.add(
                DensityFunctions.constant(NoiseRouterData.GLOBAL_OFFSET),
                DensityFunctions.spline(TerrainProvider.overworldOffset(continents, erosion, ridgesFolded, false)));

        // Masks
        DensityFunction highE = up(erosionFn, 0.0F, 0.7F);
        DensityFunction inland = up(continentsFn, -0.11F, 0.3F);

        // plateau slices only: erosion 2-3, mid-inland+, folded ridges in mid/high/peak slices
        DensityFunction plateauW = all(
                up(erosionFn, -0.45F, -0.35F), down(erosionFn, 0.0F, 0.1F),
                up(continentsFn, 0.0F, 0.1F), up(foldedFn, -0.05F, 0.2F));

        // looser version, so the Pale Garden ring reaches into the dark forest slices
        DensityFunction plateauWide = all(
                up(erosionFn, -0.6F, -0.4F), down(erosionFn, 0.2F, 0.4F), up(continentsFn, -0.05F, 0.05F));
        DensityFunction flatW = DensityFunctions.mul(up(erosionFn, 0.1F, 0.45F), plateauW.mul(-1.0F).add(1.0F));

        // Base shape (the four-quadrant k blend), flattened toward swamp-like at plains erosion
        DensityFunction kLowC = DensityFunctions.lerp(highE,
                DensityFunctions.constant(K_LOW_E_LOW_C), DensityFunctions.constant(K_HIGH_E_LOW_C));
        DensityFunction kHighC = DensityFunctions.lerp(highE,
                DensityFunctions.constant(K_LOW_E_HIGH_C), DensityFunctions.constant(K_HIGH_E_HIGH_C));
        DensityFunction k = DensityFunctions.mul(
                DensityFunctions.lerp(inland, kLowC, kHighC),
                DensityFunctions.lerp(flatW, DensityFunctions.constant(1.0F), DensityFunctions.constant(FLAT_KEEP)));

        DensityFunction rise = vanillaOffset.sub(SEA_BASE);
        DensityFunction above = DensityFunctions.max(rise, DensityFunctions.zero());
        DensityFunction below = DensityFunctions.min(rise, DensityFunctions.zero());
        DensityFunction lift = DensityFunctions.mul(
                DensityFunctions.mul(highE.mul(-1.0F).add(1.0F), inland.mul(-1.0F).add(1.0F)),
                DensityFunctions.constant(LOW_C_LIFT));
        DensityFunction shaped = DensityFunctions.add(
                DensityFunctions.add(DensityFunctions.constant(SEA_BASE), DensityFunctions.mul(k, above)),
                DensityFunctions.add(below, lift));

        // Savanna plateaus: temperature 0.2-0.55, humidity < -0.1, on plateau slices
        DensityFunction savannaW = all(
                plateauW,
                up(temperature, 0.15F, 0.25F), down(temperature, 0.5F, 0.6F),
                down(vegetationFn, -0.15F, -0.05F));
        float savannaDrop = PLATEAU_TOP - (SAVANNA_TOP_Y - 128.0F) / 128.0F;

        // Plateaus: pull vanilla's relief toward one flat height
        DensityFunction plateauTarget = vanillaOffset.mul(PLATEAU_KEEP).add(PLATEAU_TOP * (1.0F - PLATEAU_KEEP))
                .sub(savannaW.mul(savannaDrop));
        DensityFunction mixed = DensityFunctions.lerp(plateauW, shaped, plateauTarget);

        // Savanna flats: same climate as the plateaus, but off the plateau slices
        DensityFunction savannaFlatW = all(
                up(temperature, 0.15F, 0.25F), down(temperature, 0.5F, 0.6F),
                down(vegetationFn, -0.15F, -0.05F),
                up(erosionFn, -0.45F, -0.3F),
                up(continentsFn, -0.11F, 0.0F),
                plateauW.mul(-1.0F).add(1.0F));
        float flatBase = SEA_BASE + 0.01F; // sits just above the lowland base
        DensityFunction savannaFlat = mixed.sub(flatBase).mul(SAVANNA_KEEP).add(flatBase);
        mixed = DensityFunctions.lerp(savannaFlatW, mixed, savannaFlat);

        // Pale Garden: temperature -0.15..0.2, vegetation > 0.3. Ring around it, divot inside it
        DensityFunction core = all(up(temperature, -0.15F, -0.05F), down(temperature, 0.1F, 0.2F),
                up(vegetationFn, 0.3F, 0.4F), plateauW);
        DensityFunction outer = all(up(temperature, -0.35F, -0.15F), down(temperature, 0.2F, 0.4F),
                up(vegetationFn, 0.1F, 0.3F), plateauWide);
        DensityFunction gardenDelta = DensityFunctions.sub(
                outer.mul(RING_LIFT), core.mul(RING_LIFT + GARDEN_DEPTH));
        mixed = DensityFunctions.add(mixed, gardenDelta);

        // Badlands: hot (> 0.55) and inland get stepped terraces with steep risers
        DensityFunction hotW = DensityFunctions.mul(up(temperature, 0.5F, 0.6F), up(continentsFn, -0.05F, 0.05F));
        DensityFunction bRise = mixed.sub(SEA_BASE);
        DensityFunction x = DensityFunctions.max(bRise, DensityFunctions.zero()).mul(1.0F / BADLANDS_STEP);
        DensityFunction fl = DensityFunctions.floor(x);
        DensityFunction s = DensityFunctions.clampedMap(DensityFunctions.sub(x, fl), 0.4F, 0.6F, 0.0F, 1.0F);
        DensityFunction smooth = DensityFunctions.mul(s.square(), s.mul(-2.0F).add(3.0F));
        DensityFunction terraced = DensityFunctions.add(
                DensityFunctions.min(bRise, DensityFunctions.zero()).add(SEA_BASE),
                DensityFunctions.add(fl, smooth).mul(BADLANDS_STEP));
        DensityFunction withBadlands = DensityFunctions.lerp(hotW, mixed, terraced);

        // Desert flats: hot, and not badlands (erosion >= 0.05, or near inland above erosion -0.375)
        DensityFunction hotInlandW = DensityFunctions.mul(up(temperature, 0.5F, 0.6F), up(continentsFn, -0.11F, -0.05F));
        DensityFunction highErosionDesert = up(erosionFn, 0.0F, 0.1F);
        DensityFunction nearInlandDesert = DensityFunctions.mul(down(continentsFn, 0.0F, 0.03F), up(erosionFn, -0.4F, -0.35F));
        DensityFunction desertW = DensityFunctions.mul(hotInlandW, DensityFunctions.max(highErosionDesert, nearInlandDesert));
        // only flatten relief above the base, so basins and oases keep their depth
        DensityFunction desertRise = withBadlands.sub(flatBase);
        DensityFunction desertFlat = DensityFunctions.add(
                DensityFunctions.max(desertRise, DensityFunctions.zero()).mul(DESERT_KEEP).add(flatBase),
                DensityFunctions.min(desertRise, DensityFunctions.zero()));
        withBadlands = DensityFunctions.lerp(desertW, withBadlands, desertFlat);

        // Windswept hills: erosion ~0.45-0.55, mid-inland+, folded ridges >= -0.2, temperature < 0.2
        DensityFunction hillMask = all(
                up(erosionFn, 0.35F, 0.45F),
                down(erosionFn, 0.55F, 0.65F),
                up(continentsFn, 0.0F, 0.06F),
                up(foldedFn, -0.35F, -0.2F),
                down(temperature, 0.1F, 0.25F));
        DensityFunction hillNoise = DensityFunctions.clamp(DensityFunctions.noise(
                noises.getOrThrow(StratumNoises.HILLS), 1.0, 0.0).mul(0.5F).add(0.5F), 0.0F, 1.0F);
        DensityFunction withHills = DensityFunctions.add(withBadlands,
                DensityFunctions.mul(hillMask, hillNoise.mul(HILL_BLOCKS / 128.0F)));

        // Jungle spires: temperature 0.2-0.55, humidity > 0.1, inland, fading out at high erosion
        DensityFunction jungleW = all(
                up(temperature, 0.15F, 0.25F), down(temperature, 0.5F, 0.6F),
                up(vegetationFn, 0.05F, 0.15F),
                up(continentsFn, 0.0F, 0.1F),
                down(erosionFn, 0.1F, 0.45F));
        // narrow ramp on a high noise threshold: sparse towers with near-vertical walls
        DensityFunction tower = DensityFunctions.clampedMap(
                DensityFunctions.noise(noises.getOrThrow(StratumNoises.SPIRES), 1.0, 0.0), 0.35F, 0.45F, 0.0F, 1.0F);
        // vary tower height using the hills noise
        DensityFunction spireHeight = DensityFunctions.clampedMap(
                DensityFunctions.noise(noises.getOrThrow(StratumNoises.HILLS), 1.0, 0.0), -0.6F, 0.6F, 0.5F, 1.0F);
        DensityFunction withSpires = DensityFunctions.add(withHills,
                DensityFunctions.mul(DensityFunctions.mul(jungleW, tower), spireHeight.mul(SPIRE_BLOCKS / 128.0F)));

        // Windswept savanna spires: temperature >= -0.15, humidity < 0.3, raw ridges >= 0,
        // erosion 0.45-0.55, coast to near inland
        DensityFunction ridgesRawFn = NoiseRouterData.getFunction(functions, NoiseRouterData.RIDGES);
        DensityFunction wsSavannaW = all(
                up(temperature, -0.2F, -0.1F),
                down(vegetationFn, 0.2F, 0.3F),
                up(ridgesRawFn, -0.05F, 0.05F),
                up(erosionFn, 0.35F, 0.45F), down(erosionFn, 0.55F, 0.65F),
                up(continentsFn, -0.19F, -0.15F), down(continentsFn, 0.0F, 0.06F));
        // lower, wider threshold than jungle = denser towers
        DensityFunction denseTower = DensityFunctions.clampedMap(
                DensityFunctions.noise(noises.getOrThrow(StratumNoises.SPIRES), 1.0, 0.0), 0.1F, 0.2F, 0.0F, 1.0F);
        withSpires = DensityFunctions.add(withSpires,
                DensityFunctions.mul(DensityFunctions.mul(wsSavannaW, denseTower),
                        spireHeight.mul(SAVANNA_SPIRE_BLOCKS / 128.0F)));

        // Rivers/valleys stay vanilla: folded ridges below -0.85 is the valley slice
        DensityFunction valleyW = down(foldedFn, -0.85F, -0.6F);
        DensityFunction shapedLand = DensityFunctions.lerp(valleyW, withSpires, vanillaOffset);

        // leave coasts and oceans fully vanilla
        DensityFunction landWeight = up(continentsFn, -0.19F, -0.05F);
        context.register(OFFSET, NoiseRouterData.splineWithBlending(
                DensityFunctions.lerp(landWeight, vanillaOffset, shapedLand), DensityFunctions.blendOffset()));
    }

    private static DensityFunction up(DensityFunction f, float a, float b) {
        return DensityFunctions.clampedMap(f, a, b, 0.0F, 1.0F);
    }

    private static DensityFunction down(DensityFunction f, float a, float b) {
        return DensityFunctions.clampedMap(f, a, b, 1.0F, 0.0F);
    }

    private static DensityFunction all(DensityFunction... fs) {
        DensityFunction out = fs[0];
        for (int i = 1; i < fs.length; i++) out = DensityFunctions.mul(out, fs[i]);
        return out;
    }

    private static ResourceKey<DensityFunction> createKey(String path) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.fromNamespaceAndPath(Stratum.MODID, path));
    }
}
