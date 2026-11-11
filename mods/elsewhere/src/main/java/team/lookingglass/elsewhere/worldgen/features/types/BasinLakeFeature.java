package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;

import java.util.Arrays;
import java.util.List;

public record BasinLakeFeature(
        BlockStateProvider fluid,
        BlockStateProvider base,
        List<BlockStateProvider> rim,
        IntProvider depth,
        IntProvider size,
        BlockPredicate canPlaceFeature,
        BlockPredicate canReplaceWithAirOrFluid,
        BlockPredicate canReplaceWithBarrier
) implements Feature {
    public static final int MAX_DEPTH = 16;
    public static final int MAX_RIM = 8;
    public static final int MIN_SIZE = 8;
    public static final int MAX_SIZE = 32;
    private static final int AIR_HEIGHT = 4;
    private static final double AIR_RADIUS = 3.0;
    private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();
    public static final MapCodec<BasinLakeFeature> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("fluid").forGetter(BasinLakeFeature::fluid),
            BlockStateProvider.DIRECT_CODEC.fieldOf("base").forGetter(BasinLakeFeature::base),
            BlockStateProvider.DIRECT_CODEC.listOf().optionalFieldOf("rim", List.of()).forGetter(BasinLakeFeature::rim),
            IntProviders.codec(1, MAX_DEPTH).fieldOf("depth").forGetter(BasinLakeFeature::depth),
            IntProviders.codec(MIN_SIZE, MAX_SIZE).optionalFieldOf("size", ConstantInt.of(16)).forGetter(BasinLakeFeature::size),
            BlockPredicate.CODEC.fieldOf("can_place_feature").forGetter(BasinLakeFeature::canPlaceFeature),
            BlockPredicate.CODEC.fieldOf("can_replace_with_air_or_fluid").forGetter(BasinLakeFeature::canReplaceWithAirOrFluid),
            BlockPredicate.CODEC.fieldOf("can_replace_with_barrier").forGetter(BasinLakeFeature::canReplaceWithBarrier)
    ).apply(i, BasinLakeFeature::new));

    public MapCodec<BasinLakeFeature> codec() {
        return CODEC;
    }

    private static boolean isEdge(boolean[] grid, int size, int xx, int yy, int zz, int h) {
        return !grid[(xx * size + zz) * h + yy] && (
                xx < size - 1 && grid[((xx + 1) * size + zz) * h + yy]
                        || xx > 0 && grid[((xx - 1) * size + zz) * h + yy]
                        || zz < size - 1 && grid[(xx * size + zz + 1) * h + yy]
                        || zz > 0 && grid[(xx * size + (zz - 1)) * h + yy]
                        || yy < h - 1 && grid[(xx * size + zz) * h + yy + 1]
                        || yy > 0 && grid[(xx * size + zz) * h + (yy - 1)]);
    }

    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, BlockPos origin) {
        int width = Math.min(this.rim.size(), MAX_RIM);
        int size = Math.clamp(this.size.sample(random), MIN_SIZE, MAX_SIZE - 2 * width);
        double scale = size / 16.0;

        int maxDepth = Math.clamp(this.depth.maxInclusive(), 1, MAX_DEPTH);
        int waterline = maxDepth + 1;
        int h = waterline + AIR_HEIGHT;

        if (origin.getY() <= level.getMinY() + waterline) {
            return false;
        }

        origin = origin.offset(-size / 2, -waterline, -size / 2);
        boolean[] grid = new boolean[size * size * h];
        int spots = Math.max(1, (int) Math.round((random.nextInt(4) + 4) * scale * scale));

        for (int i = 0; i < spots; ++i) {
            double xr = (random.nextDouble() * 6.0 + 3.0) * scale;
            double zr = (random.nextDouble() * 6.0 + 3.0) * scale;
            double xp = random.nextDouble() * (size - xr - 2.0) + 1.0 + xr / 2.0;
            double zp = random.nextDouble() * (size - zr - 2.0) + 1.0 + zr / 2.0;
            int spotDepth = Math.clamp(this.depth.sample(random), 1, maxDepth);

            for (int xx = 1; xx < size - 1; ++xx) {
                for (int zz = 1; zz < size - 1; ++zz) {
                    double xd = (xx - xp) / (xr / 2.0);
                    double zd = (zz - zp) / (zr / 2.0);
                    for (int yy = 1; yy < h - 1; ++yy) {
                        double yd = yy < waterline
                                ? (yy - (waterline - 1)) / (double) spotDepth
                                : (yy - waterline) / AIR_RADIUS;
                        if (xd * xd + yd * yd + zd * zd < 1.0) {
                            grid[(xx * size + zz) * h + yy] = true;
                        }
                    }
                }
            }
        }

        BlockState fluid = this.fluid.getState(level, random, origin);

        for (int xx = 0; xx < size; ++xx) {
            for (int zz = 0; zz < size; ++zz) {
                for (int yy = 0; yy < h; ++yy) {
                    if (isEdge(grid, size, xx, yy, zz, h)) {
                        BlockPos pos = origin.offset(xx, yy, zz);
                        BlockState state = level.getBlockState(pos);
                        if (yy >= waterline && !state.getFluidState().isEmpty()) {
                            return false;
                        }
                        if (yy < waterline && !state.isSolidRender() && state != fluid) {
                            return false;
                        }
                        if (!this.canPlaceFeature.test(level, pos)) {
                            return false;
                        }
                    }
                }
            }
        }

        for (int xx = 0; xx < size; ++xx) {
            for (int zz = 0; zz < size; ++zz) {
                for (int yy = 0; yy < h; ++yy) {
                    if (grid[(xx * size + zz) * h + yy]) {
                        BlockPos pos = origin.offset(xx, yy, zz);
                        if (this.canReplaceWithAirOrFluid.test(level, pos)) {
                            boolean placeAir = yy >= waterline;
                            level.setBlock(pos, placeAir ? AIR : fluid, 2);
                            if (placeAir) {
                                level.scheduleTick(pos, AIR.getBlock(), 0);
                                this.markAboveForPostProcessing(level, pos);
                            }
                        }
                    }
                }
            }
        }

        for (int xx = 0; xx < size; ++xx) {
            for (int zz = 0; zz < size; ++zz) {
                for (int yy = 0; yy < waterline; ++yy) {
                    if (isEdge(grid, size, xx, yy, zz, h)) {
                        BlockPos pos = origin.offset(xx, yy, zz);
                        if (level.getBlockState(pos).isSolidRender() && this.canReplaceWithBarrier.test(level, pos)) {
                            BlockState baseState = this.base.getState(level, random, pos);
                            if (!baseState.isAir()) {
                                level.setBlock(pos, baseState, 2);
                                this.markAboveForPostProcessing(level, pos);
                            }
                        }
                    }
                }
            }
        }

        this.placeRim(level, random, origin, grid, size, width, waterline, h);

        if (fluid.getFluidState().is(Fluids.WATER)) {
            for (int xx = 0; xx < size; ++xx) {
                for (int zz = 0; zz < size; ++zz) {
                    BlockPos pos = origin.offset(xx, waterline, zz);
                    if (level.getBiome(pos).value().shouldFreeze(level, pos, false)
                            && this.canReplaceWithAirOrFluid.test(level, pos)) {
                        level.setBlock(pos, Blocks.ICE.defaultBlockState(), 2);
                    }
                }
            }
        }
        return true;
    }

    private void placeRim(WorldGenLevel level, RandomSource random, BlockPos origin, boolean[] grid, int size, int width, int waterline, int h) {
        if (width == 0) {
            return;
        }

        int shoreY = waterline - 1;
        int span = size + 2 * width;
        int maxSq = width * width;
        int[] distSq = new int[span * span];
        Arrays.fill(distSq, Integer.MAX_VALUE);

        for (int xx = 0; xx < size; ++xx) {
            for (int zz = 0; zz < size; ++zz) {
                if (!grid[(xx * size + zz) * h + shoreY]) {
                    continue;
                }
                for (int dx = -width; dx <= width; ++dx) {
                    for (int dz = -width; dz <= width; ++dz) {
                        int sq = dx * dx + dz * dz;
                        if (sq > maxSq) {
                            continue;
                        }
                        int idx = (xx + dx + width) * span + (zz + dz + width);
                        if (sq < distSq[idx]) {
                            distSq[idx] = sq;
                        }
                    }
                }
            }
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int cx = 0; cx < span; ++cx) {
            for (int cz = 0; cz < span; ++cz) {
                int sq = distSq[cx * span + cz];
                if (sq == 0 || sq > maxSq) {
                    continue;
                }
                int ring = (int) Math.ceil(Math.sqrt(sq));
                if (ring > width) {
                    continue;
                }

                for (int yy = h - 1; yy >= shoreY - 1; --yy) {
                    pos.set(origin.getX() + cx - width, origin.getY() + yy, origin.getZ() + cz - width);
                    BlockState existing = level.getBlockState(pos);
                    if (existing.isAir()) {
                        continue;
                    }
                    if (existing.isSolidRender() && this.canReplaceWithBarrier.test(level, pos)) {
                        BlockState rimState = this.rim.get(ring - 1).getState(level, random, pos);
                        if (!rimState.isAir()) {
                            level.setBlock(pos, rimState, 2);
                            this.markAboveForPostProcessing(level, pos);
                        }
                    }
                    break;
                }
            }
        }
    }
}