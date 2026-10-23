package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;

public class LargeBlobFeature implements Feature {
    public static final MapCodec<LargeBlobFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockState.CODEC.fieldOf("state").forGetter(feature -> feature.state),
            BlockPredicate.CODEC.fieldOf("can_place_on").forGetter(feature -> feature.canPlaceOn),
            IntProviders.codec(Integer.MIN_VALUE, Integer.MAX_VALUE).fieldOf("radius").forGetter(feature -> feature.radius)
    ).apply(instance, LargeBlobFeature::new));
    @Override
    public MapCodec<LargeBlobFeature> codec() {
        return CODEC;
    }

    private final BlockState state;
    private final BlockPredicate canPlaceOn;
    private final IntProvider radius;

    public LargeBlobFeature(final BlockState state, final BlockPredicate canPlaceOn, final IntProvider radius) {
        this.state = state;
        this.canPlaceOn = canPlaceOn;
        this.radius = radius;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos originArg) {
        BlockPos origin = originArg;

        while (origin.getY() > level.getMinY() + 3 && !this.canPlaceOn.test(level, origin.below())) {
            origin = origin.below();
        }

        if (origin.getY() <= level.getMinY() + 3) {
            return false;
        }

        for (int c = 0; c < 3; c++) {
            int xr = random.nextInt(this.radius.sample(random));
            int yr = random.nextInt(this.radius.sample(random));
            int zr = random.nextInt(this.radius.sample(random));
            float tr = (xr + yr + zr) * 0.333F + 0.5F;

            for (BlockPos blockPos : BlockPos.betweenClosed(origin.offset(-xr, -yr, -zr), origin.offset(xr, yr, zr))) {
                if (blockPos.distSqr(origin) <= tr * tr) {
                    level.setBlock(blockPos, this.state, 3);
                }
            }

            origin = origin.offset(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
        }

        return true;
    }
}