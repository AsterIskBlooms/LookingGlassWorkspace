package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CragFeature(IntProvider radius, IntProvider height,
                          Holder<BlockStateProvider> topState,
                          Holder<BlockStateProvider> middleState, int middleDepth,
                          Holder<BlockStateProvider> bodyState,
                          float edgeWidth, int maxGroundVariation,
                          boolean allowUnderwater) implements Feature {
    public static final MapCodec<CragFeature> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
            IntProviders.NON_NEGATIVE_CODEC.fieldOf("radius").forGetter(CragFeature::radius),
            IntProviders.NON_NEGATIVE_CODEC.fieldOf("height").forGetter(CragFeature::height),
            BlockStateProvider.CODEC.fieldOf("top_state").forGetter(CragFeature::topState),
            BlockStateProvider.CODEC.fieldOf("middle_state").forGetter(CragFeature::middleState),
            Codec.intRange(0, 16).fieldOf("middle_depth").forGetter(CragFeature::middleDepth),
            BlockStateProvider.CODEC.fieldOf("body_state").forGetter(CragFeature::bodyState),
            Codec.floatRange(0.05F, 1.0F).fieldOf("edge_width").forGetter(CragFeature::edgeWidth),
            Codec.intRange(0, 16).fieldOf("max_ground_variation").forGetter(CragFeature::maxGroundVariation),
            Codec.BOOL.fieldOf("allow_underwater").forGetter(CragFeature::allowUnderwater)
    ).apply(i, CragFeature::new));

    private static final int MAX_RADIUS = 12;
    private static final int RIM_HARMONICS = 3;
    private static final double RIM_MAX_SCALE = 1.18;
    private static final double RIM_AMPLITUDE = 0.06;
    private static final double BUMP_FREQUENCY_X = 0.35;
    private static final double BUMP_FREQUENCY_Z = 0.3;
    private static final int OUTSIDE = Integer.MIN_VALUE;

    public MapCodec<CragFeature> codec() {
        return CODEC;
    }

    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        int baseRadius = Math.min(this.radius.sample(random), MAX_RADIUS);
        int peakHeight = this.height.sample(random);
        if (baseRadius < 1 || peakHeight < 1) {
            return false;
        }

        Rim rim = Rim.random(random);
        Bump bump = Bump.random(random);

        Footprint footprint = this.scanFootprint(level, origin, baseRadius, rim);
        if (footprint == null) {
            return false;
        }

        return this.build(level, random, origin, footprint, peakHeight, bump);
    }

    private Footprint scanFootprint(final WorldGenLevel level, final BlockPos origin, final int baseRadius, final Rim rim) {
        int reach = (int)Math.ceil(baseRadius * RIM_MAX_SCALE);
        int size = reach * 2 + 1;
        int[] ground = new int[size * size];
        double[] rimT = new double[size * size];
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int minGround = Integer.MAX_VALUE;
        int maxGround = Integer.MIN_VALUE;

        for (int dx = -reach; dx <= reach; ++dx) {
            for (int dz = -reach; dz <= reach; ++dz) {
                int index = (dx + reach) * size + (dz + reach);
                double t = Math.sqrt(dx * dx + dz * dz) / rim.radiusAt(baseRadius, Math.atan2(dz, dx));
                if (t >= 1.0) {
                    ground[index] = OUTSIDE;
                    continue;
                }

                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;
                int g = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z);
                pos.set(x, g, z);
                if (!this.allowUnderwater && !level.getFluidState(pos).isEmpty()) {
                    return null;
                }

                ground[index] = g;
                rimT[index] = t;
                minGround = Math.min(minGround, g);
                maxGround = Math.max(maxGround, g);
            }
        }

        if (maxGround == Integer.MIN_VALUE || maxGround - minGround > this.maxGroundVariation) {
            return null;
        }

        return new Footprint(reach, size, ground, rimT, maxGround);
    }

    private boolean build(final WorldGenLevel level, final RandomSource random, final BlockPos origin, final Footprint footprint, final int peakHeight, final Bump bump) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int ceiling = level.getMaxY() + 1;
        boolean placedAny = false;

        for (int dx = -footprint.reach(); dx <= footprint.reach(); ++dx) {
            for (int dz = -footprint.reach(); dz <= footprint.reach(); ++dz) {
                int index = footprint.index(dx, dz);
                if (footprint.isOutside(index)) {
                    continue;
                }

                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;
                double profile = this.profile(footprint.rimT()[index]);
                double offset = peakHeight * profile + bump.at(x, z, peakHeight, profile);
                int top = Math.min(footprint.maxGround() + (int)Math.round(offset), ceiling);

                placedAny |= this.fillColumn(level, random, pos, x, z, footprint.ground()[index], top);
            }
        }

        return placedAny;
    }

    private boolean fillColumn(final WorldGenLevel level, final RandomSource random, final BlockPos.MutableBlockPos pos, final int x, final int z, final int bottom, final int top) {
        boolean placedAny = false;

        for (int y = bottom; y < top; ++y) {
            pos.set(x, y, z);
            if (!level.ensureCanWrite(pos)) {
                break;
            }

            BlockStateProvider provider = this.providerAtDepth(top - 1 - y).value();
            level.setBlock(pos, provider.getState(level, random, pos), 2);
            placedAny = true;
        }

        return placedAny;
    }

    private Holder<BlockStateProvider> providerAtDepth(final int depth) {
        if (depth == 0) {
            return this.topState;
        }

        return depth <= this.middleDepth ? this.middleState : this.bodyState;
    }

    private double profile(final double rimT) {
        double raw = Math.min(1.0, (1.0 - rimT) / this.edgeWidth);
        return raw * raw * raw * (raw * (raw * 6.0 - 15.0) + 10.0);
    }

    private record Footprint(int reach, int size, int[] ground, double[] rimT, int maxGround) {
        int index(final int dx, final int dz) {
            return (dx + this.reach) * this.size + (dz + this.reach);
        }

        boolean isOutside(final int index) {
            return this.ground[index] == OUTSIDE;
        }
    }

    private record Rim(float[] amplitude, float[] phase) {
        static Rim random(final RandomSource random) {
            float[] amplitude = new float[RIM_HARMONICS];
            float[] phase = new float[RIM_HARMONICS];

            for (int k = 0; k < RIM_HARMONICS; ++k) {
                amplitude[k] = random.nextFloat() * (float)RIM_AMPLITUDE;
                phase[k] = random.nextFloat() * (float)(Math.PI * 2.0);
            }

            return new Rim(amplitude, phase);
        }

        double radiusAt(final int baseRadius, final double angle) {
            double scale = 1.0;

            for (int k = 0; k < this.amplitude.length; ++k) {
                scale += this.amplitude[k] * Math.sin(angle * (k + 2) + this.phase[k]);
            }

            return baseRadius * scale;
        }
    }

    private record Bump(double phaseX, double phaseZ) {
        static Bump random(final RandomSource random) {
            return new Bump(random.nextDouble() * Math.PI * 2.0, random.nextDouble() * Math.PI * 2.0);
        }

        double at(final int x, final int z, final int peakHeight, final double profile) {
            double wave = (Math.sin(x * BUMP_FREQUENCY_X + this.phaseX) + Math.sin(z * BUMP_FREQUENCY_Z + this.phaseZ)) * 0.5;
            return wave * Math.max(1.0, peakHeight / 8.0) * profile;
        }
    }
}