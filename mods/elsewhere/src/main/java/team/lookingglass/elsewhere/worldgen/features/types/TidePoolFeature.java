package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class TidePoolFeature implements Feature {
    public static final MapCodec<TidePoolFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("floor").forGetter(feature -> feature.floor),
            BlockStateProvider.DIRECT_CODEC.fieldOf("rim").forGetter(feature -> feature.rim),
            BlockStateProvider.DIRECT_CODEC.fieldOf("coral").forGetter(feature -> feature.coral)
    ).apply(instance, TidePoolFeature::new));

    private static final int NONE = 0;
    private static final int RIM = 1;
    private static final int BASIN = 2;
    private final BlockStateProvider floor;
    private final BlockStateProvider rim;
    private final BlockStateProvider coral;

    public TidePoolFeature(final BlockStateProvider floor, final BlockStateProvider rim, final BlockStateProvider coral) {
        this.floor = floor;
        this.rim = rim;
        this.coral = coral;
    }

    @Override
    public MapCodec<TidePoolFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        int lobeCount = Math.max(1, UniformInt.of(2, 3).sample(random));
        int[] lobeDx = new int[lobeCount];
        int[] lobeDz = new int[lobeCount];
        int[] lobeRadius = new int[lobeCount];
        int maxReach = 0;
        int primaryLobe = 0;

        for (int i = 0; i < lobeCount; i++) {
            int offset = UniformInt.of(1, 4).sample(random);
            lobeDx[i] = random.nextBoolean() ? offset : -offset;
            lobeDz[i] = random.nextBoolean() ? offset : -offset;
            lobeRadius[i] = UniformInt.of(2, 4).sample(random);
            maxReach = Math.max(maxReach, Math.abs(lobeDx[i]) + lobeRadius[i]);
            maxReach = Math.max(maxReach, Math.abs(lobeDz[i]) + lobeRadius[i]);

            if (lobeRadius[i] > lobeRadius[primaryLobe]) {
                primaryLobe = i;
            }
        }

        int rimThickness = Math.max(1, UniformInt.of(1, 2).sample(random));

        int bounds = maxReach + rimThickness + 1;
        int width = bounds * 2 + 1;
        int[][] classification = new int[width][width];

        for (int xi = 0; xi < width; xi++) {
            for (int zi = 0; zi < width; zi++) {
                int x = xi - bounds;
                int z = zi - bounds;
                double edgeDistance = Double.POSITIVE_INFINITY;

                for (int i = 0; i < lobeCount; i++) {
                    double dx = x - lobeDx[i];
                    double dz = z - lobeDz[i];
                    double distance = Math.sqrt(dx * dx + dz * dz) - lobeRadius[i];
                    edgeDistance = Math.min(edgeDistance, distance);
                }

                if (edgeDistance <= 0.0) {
                    classification[xi][zi] = BASIN;
                } else if (edgeDistance <= rimThickness) {
                    classification[xi][zi] = RIM;
                }
            }
        }

        for (int xi = 0; xi < width; xi++) {
            for (int zi = 0; zi < width; zi++) {
                if (classification[xi][zi] == BASIN) {
                    patchGap(classification, xi - 1, zi, width);
                    patchGap(classification, xi + 1, zi, width);
                    patchGap(classification, xi, zi - 1, width);
                    patchGap(classification, xi, zi + 1, width);
                }
            }
        }

        int depth = Math.max(2, UniformInt.of(1, 3).sample(random));
        int deepSpotRadius = UniformInt.of(6, 8).sample(random);
        int deepSpotBonus = 1;
        int deepCenterX = lobeDx[primaryLobe];
        int deepCenterZ = lobeDz[primaryLobe];
        boolean placedAny = false;

        for (int xi = 0; xi < width; xi++) {
            for (int zi = 0; zi < width; zi++) {
                int cls = classification[xi][zi];

                if (cls == NONE) {
                    continue;
                }

                int x = xi - bounds;
                int z = zi - bounds;
                BlockPos surfacePos = origin.offset(x, 0, z);

                if (cls == BASIN) {
                    int clearHeight = 6;
                    BlockPos.MutableBlockPos clearPos = surfacePos.mutable();

                    for (int y = 0; y < clearHeight; y++) {
                        clearPos.move(Direction.UP);
                        level.setBlock(clearPos, Blocks.AIR.defaultBlockState(), 2);
                    }

                    double deepDx = x - deepCenterX;
                    double deepDz = z - deepCenterZ;
                    boolean isDeepSpot = Math.sqrt(deepDx * deepDx + deepDz * deepDz) <= deepSpotRadius;
                    int cellDepth = isDeepSpot ? depth + deepSpotBonus : depth;

                    BlockPos.MutableBlockPos pos = surfacePos.mutable();

                    for (int y = 0; y < cellDepth; y++) {
                        if (y == cellDepth - 1) {
                            level.setBlock(pos, this.floor.getState(level, random, pos), 2);
                        } else if (y == cellDepth - 2) {
                            if (random.nextFloat() < 0.05F) {
                                level.setBlock(pos, this.coral.getState(level, random, pos), 2);
                            } else if (random.nextFloat() < 0.35F) {
                                if (cellDepth >= 3 && random.nextFloat() < 0.35F) {
                                    level.setBlock(pos, Blocks.TALL_SEAGRASS.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 2);
                                    level.setBlock(pos.above(), Blocks.TALL_SEAGRASS.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                                } else {
                                    level.setBlock(pos, Blocks.SEAGRASS.defaultBlockState(), 3);
                                }
                            } else {
                                level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
                            }
                        } else {
                            level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
                        }

                        pos.move(Direction.DOWN);
                    }

                    int foundationDepth = UniformInt.of(5, 7).sample(random);

                    for (int y = 0; y < foundationDepth; y++) {
                        level.setBlock(pos, this.floor.getState(level, random, pos), 2);
                        pos.move(Direction.DOWN);
                    }

                    placedAny = true;
                } else {
                    boolean tall = random.nextFloat() < 0.65F;
                    int extraHeight = tall ? 1 : 0;
                    BlockPos.MutableBlockPos pos = surfacePos.mutable();

                    for (int y = 0; y <= extraHeight; y++) {
                        level.setBlock(pos, this.rim.getState(level, random, pos), 2);
                        pos.move(Direction.UP);
                    }

                    int foundationDepth = UniformInt.of(5, 7).sample(random);
                    pos = surfacePos.mutable();

                    for (int y = 0; y < foundationDepth; y++) {
                        pos.move(Direction.DOWN);
                        level.setBlock(pos, this.rim.getState(level, random, pos), 2);
                    }

                    placedAny = true;
                }
            }
        }

        return placedAny;
    }

    private static void patchGap(final int[][] classification, final int xi, final int zi, final int width) {
        if (xi >= 0 && xi < width && zi >= 0 && zi < width && classification[xi][zi] == NONE) {
            classification[xi][zi] = RIM;
        }
    }
}