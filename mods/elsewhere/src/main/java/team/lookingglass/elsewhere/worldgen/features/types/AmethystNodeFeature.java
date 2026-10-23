package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public class AmethystNodeFeature implements Feature {
    public static final MapCodec<AmethystNodeFeature> CODEC = MapCodec.unit(AmethystNodeFeature::new);

    @Override
    public MapCodec<AmethystNodeFeature> codec() {
        return CODEC;
    }

    private BlockState randomBudStage(RandomSource random, Direction dir, boolean waterlogged) {
        float roll = random.nextFloat();
        BlockState state;
        if (roll < 0.35F) {
            state = Blocks.SMALL_AMETHYST_BUD.defaultBlockState();
        } else if (roll < 0.65F) {
            state = Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState();
        } else if (roll < 0.9F) {
            state = Blocks.LARGE_AMETHYST_BUD.defaultBlockState();
        } else {
            state = Blocks.AMETHYST_CLUSTER.defaultBlockState();
        }
        return state.setValue(AmethystClusterBlock.FACING, dir).setValue(AmethystClusterBlock.WATERLOGGED, waterlogged);
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        boolean placedAny = false;
        int clusterCount = 2 + random.nextInt(3);

        for (int c = 0; c < clusterCount; c++) {
            BlockPos clusterOrigin = origin.offset(
                    random.nextIntBetweenInclusive(-6, 6),
                    random.nextIntBetweenInclusive(-3, 3),
                    random.nextIntBetweenInclusive(-6, 6)
            );

            int radius = 2 + random.nextInt(4);

            for (int x = -radius; x <= radius; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (x * x + z * z > radius * radius) continue;
                        BlockPos pos = clusterOrigin.offset(x, y, z);
                        if (!level.getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD)) continue;
                        boolean budding = random.nextFloat() < 0.15F;
                        level.setBlock(pos,
                                budding ? Blocks.BUDDING_AMETHYST.defaultBlockState()
                                        : Blocks.AMETHYST_BLOCK.defaultBlockState(), 2);
                        placedAny = true;
                    }
                }
            }

            for (int x = -radius; x <= radius; x++) {
                for (int y = -2; y <= 2; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos pos = clusterOrigin.offset(x, y, z);
                        if (!level.getBlockState(pos).is(Blocks.BUDDING_AMETHYST)) continue;
                        for (Direction dir : Direction.values()) {
                            if (random.nextFloat() > 0.7F) continue;
                            BlockPos face = pos.relative(dir);
                            var faceState = level.getBlockState(face);
                            if (faceState.isAir() || faceState.is(Blocks.WATER)) {
                                level.setBlock(face, randomBudStage(random, dir,
                                        faceState.is(Blocks.WATER)), 2);
                            }
                        }
                    }
                }
            }
        }
        return placedAny;
    }
}