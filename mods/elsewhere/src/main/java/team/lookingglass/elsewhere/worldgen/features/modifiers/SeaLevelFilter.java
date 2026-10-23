package team.lookingglass.elsewhere.worldgen.features.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;

public record SeaLevelFilter(int minOffset, int maxOffset) implements PlacementFilter {
    public static final MapCodec<SeaLevelFilter> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(Codec.INT.fieldOf("min_offset").forGetter(SeaLevelFilter::minOffset), Codec.INT.fieldOf("max_offset").forGetter(SeaLevelFilter::maxOffset)).apply(i, SeaLevelFilter::new));

    public static SeaLevelFilter of(final int minOffset, final int maxOffset) {
        return new SeaLevelFilter(minOffset, maxOffset);
    }

    @Override
    public boolean shouldPlace(final PlacementContext context, final RandomSource random, final BlockPos pos) {
        int relative = pos.getY() - context.level().getSeaLevel();
        return relative >= this.minOffset && relative <= this.maxOffset;
    }

    @Override
    public MapCodec<? extends PlacementFilter> codec() {
        return CODEC;
    }
}