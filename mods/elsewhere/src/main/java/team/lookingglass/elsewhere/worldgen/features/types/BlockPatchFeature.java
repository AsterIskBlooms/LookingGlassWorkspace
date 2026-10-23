package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BlockPatchFeature implements Feature {
    public static final MapCodec<BlockPatchFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("to_place").forGetter(feature -> feature.toPlace),
            BlockPredicate.CODEC.fieldOf("valid_ground").forGetter(feature -> feature.validGround),
            BlockPredicate.CODEC.fieldOf("replaceable").forGetter(feature -> feature.replaceable),
            Codec.INT.fieldOf("base_radius").forGetter(feature -> feature.baseRadius),
            Codec.INT.fieldOf("radius_variance").forGetter(feature -> feature.radiusVariance),
            Codec.INT.fieldOf("base_count").forGetter(feature -> feature.baseCount),
            Codec.INT.fieldOf("count_variance").forGetter(feature -> feature.countVariance),
            Direction.CODEC.fieldOf("scan_direction").forGetter(feature -> feature.scanDirection),
            Codec.INT.fieldOf("max_scan_distance").forGetter(feature -> feature.maxScanDistance)
    ).apply(instance, BlockPatchFeature::new));
    @Override
    public MapCodec<BlockPatchFeature> codec() {
        return CODEC;
    }

    private final BlockStateProvider toPlace;
    private final BlockPredicate validGround;
    private final BlockPredicate replaceable;
    private final int baseRadius;
    private final int radiusVariance;
    private final int baseCount;
    private final int countVariance;
    private final Direction scanDirection;
    private final int maxScanDistance;

    public BlockPatchFeature(final BlockStateProvider toPlace, final BlockPredicate validGround, final BlockPredicate replaceable,
                             final int baseRadius, final int radiusVariance, final int baseCount, final int countVariance,
                             final Direction scanDirection, final int maxScanDistance) {
        this.toPlace = toPlace;
        this.validGround = validGround;
        this.replaceable = replaceable;
        this.baseRadius = baseRadius;
        this.radiusVariance = radiusVariance;
        this.baseCount = baseCount;
        this.countVariance = countVariance;
        this.scanDirection = scanDirection;
        this.maxScanDistance = maxScanDistance;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        int radius = this.baseRadius + random.nextInt(this.radiusVariance + 1);
        int count = this.baseCount + random.nextInt(this.countVariance + 1);
        int placed = 0;
        Direction scanDir = this.scanDirection;
        Direction placeSide = scanDir.getOpposite();

        for (int i = 0; i < count * 3; i++) {
            int x = origin.getX() + random.nextInt(radius * 2 + 1) - radius;
            int z = origin.getZ() + random.nextInt(radius * 2 + 1) - radius;

            BlockPos.MutableBlockPos scanPos = new BlockPos.MutableBlockPos(x, origin.getY(), z);
            BlockPos ground = null;

            for (int step = 0; step <= this.maxScanDistance; step++) {
                if (this.validGround.test(level, scanPos)) {
                    ground = scanPos.immutable();
                    break;
                }
                scanPos.move(scanDir);
            }

            if (ground == null) continue;

            BlockPos surface = ground.relative(placeSide);
            BlockState state = this.toPlace.getState(level, random, surface);

            if (scanDir == Direction.DOWN && state.getBlock() instanceof DoublePlantBlock) {
                if (this.replaceable.test(level, surface) && this.replaceable.test(level, surface.above())) {
                    DoublePlantBlock.placeAt(level, state, surface, 2);
                    placed++;
                    if (placed >= count) break;
                }
            } else if (this.replaceable.test(level, surface)) {
                level.setBlock(surface, state, 2);
                placed++;
                if (placed >= count) break;
            }
        }

        return placed > 0;
    }
}