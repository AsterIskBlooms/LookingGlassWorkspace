package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

public record PatchySnowFeature(double scale, double threshold, double edgeJitter) implements Feature {
    public static final MapCodec<PatchySnowFeature> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Codec.doubleRange(0.001, 1.0).fieldOf("scale").forGetter(PatchySnowFeature::scale),
            Codec.doubleRange(-1.0, 1.0).fieldOf("threshold").forGetter(PatchySnowFeature::threshold),
            Codec.doubleRange(0.0, 1.0).fieldOf("edge_jitter").forGetter(PatchySnowFeature::edgeJitter)
    ).apply(i, PatchySnowFeature::new));
    private static volatile long cachedSeed;
    private static volatile PerlinNoise cachedNoise;

    private static PerlinNoise noiseFor(long seed) {
        PerlinNoise noise = cachedNoise;
        if (noise == null || cachedSeed != seed) {
            noise = new PerlinNoise(RandomSource.create(seed ^ 0x5EEDL));
            cachedNoise = noise;
            cachedSeed = seed;
        }
        return noise;
    }

    @Override
    public MapCodec<PatchySnowFeature> codec() { return CODEC; }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        PerlinNoise noise = noiseFor(level.getSeed());
        BlockPos.MutableBlockPos topPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos belowPos = new BlockPos.MutableBlockPos();

        for (int dx = 0; dx < 16; ++dx) {
            for (int dz = 0; dz < 16; ++dz) {
                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;
                int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
                topPos.set(x, y, z);
                belowPos.set(topPos).move(Direction.DOWN, 1);
                Biome biome = level.getBiome(topPos).value();

                if (biome.shouldFreeze(level, belowPos, false)) {
                    level.setBlock(belowPos, Blocks.ICE.defaultBlockState(), 2);
                }

                double n = noise.get(x * scale, 0.0, z * scale)
                        + (random.nextDouble() - 0.5) * edgeJitter;
                if (n < threshold) continue;

                if (biome.shouldSnow(level, topPos)) {
                    level.setBlock(topPos, Blocks.SNOW.defaultBlockState(), 2);
                    BlockState belowState = level.getBlockState(belowPos);
                    if (belowState.hasProperty(SnowyBlock.SNOWY)) {
                        level.setBlock(belowPos, belowState.setValue(SnowyBlock.SNOWY, true), 2);
                    }
                }
            }
        }
        return true;
    }
}
