package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import team.lookingglass.elsewhere.worldgen.features.EFoliagePlacers;

public class EPineFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<EPineFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(i ->
            foliagePlacerParts(i)
                    .and(IntProviders.codec(0, 32).fieldOf("height").forGetter(p -> p.height))
                    .apply(i, EPineFoliagePlacer::new));
    private final IntProvider height;

    public EPineFoliagePlacer(final IntProvider radius, final IntProvider offset, final IntProvider height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EFoliagePlacers.PINE_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(
            final WorldGenLevel level,
            final FoliagePlacer.FoliageSetter foliageSetter,
            final RandomSource random,
            final TreeFeature tree,
            final int treeHeight,
            final FoliagePlacer.FoliageAttachment foliageAttachment,
            final int foliageHeight,
            final int leafRadius,
            final int offset
    ) {
        int maxRadius = leafRadius + foliageAttachment.radiusOffsetXZ();

        for (int i = 0; i <= foliageHeight; i++) {
            int radius;
            if (i < 2) {
                radius = 0;
            } else if (i < 4) {
                radius = 1;
            } else {
                int j = i - 4;
                if (j % 2 == 0) {
                    radius = Math.min(maxRadius, 2 + j / 4);
                } else {
                    int discAbove = Math.min(maxRadius, 2 + (j - 1) / 4);
                    radius = Math.max(1, discAbove - 1);
                }
            }
            this.placeLeavesRow(level, foliageSetter, random, tree, foliageAttachment.pos(), radius, offset - i, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(final RandomSource random, final int treeHeight, final TreeFeature tree) {
        return this.height.sample(random);
    }

    @Override
    protected boolean shouldSkipLocation(final RandomSource random, final int dx, final int y, final int dz, final int currentRadius, final boolean doubleTrunk) {
        if (currentRadius == 1) {
            return dx == 1 && dz == 1;
        }
        return dx * dx + dz * dz > currentRadius * currentRadius + currentRadius;
    }
}