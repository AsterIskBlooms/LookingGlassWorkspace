package team.lookingglass.elsewhere.worldgen.features.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class BlockColumnClusterFeature implements Feature {
    public static final MapCodec<BlockColumnClusterFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockColumnFeature.Layer.CODEC.listOf().fieldOf("layers").forGetter(feature -> feature.layers),
            BlockStateProvider.CODEC.optionalFieldOf("top_decoration").forGetter(feature -> Optional.ofNullable(feature.topDecoration)),
            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("top_decoration_chance", 1.0F).forGetter(feature -> feature.topDecorationChance),
            Direction.CODEC.fieldOf("direction").forGetter(feature -> feature.direction),
            BlockPredicate.CODEC.fieldOf("allowed_placement").forGetter(feature -> feature.allowedPlacement),
            Codec.BOOL.fieldOf("prioritize_tip").forGetter(feature -> feature.prioritizeTip),
            IntProviders.codec(1, 32).fieldOf("size").forGetter(feature -> feature.size),
            IntProviders.codec(0, 16).fieldOf("falloff_per_step").forGetter(feature -> feature.falloffPerStep),
            IntProviders.codec(0, 16).fieldOf("jitter").forGetter(feature -> feature.jitter),
            IntProviders.codec(0, 16).fieldOf("sunken_depth").forGetter(feature -> feature.sunkenDepth)
    ).apply(instance, (layers, topDecoration, topDecorationChance, direction, allowedPlacement, prioritizeTip, size, falloffPerStep, jitter, sunkenDepth) ->
            new BlockColumnClusterFeature(layers, topDecoration.orElse(null), topDecorationChance, direction, allowedPlacement, prioritizeTip, size, falloffPerStep, jitter, sunkenDepth)));


    private final List<BlockColumnFeature.Layer> layers;
    private final Holder<BlockStateProvider> topDecoration; // nullable
    private final float topDecorationChance;
    private final Direction direction;
    private final BlockPredicate allowedPlacement;
    private final boolean prioritizeTip;
    private final IntProvider size;
    private final IntProvider falloffPerStep;
    private final IntProvider jitter;
    private final IntProvider sunkenDepth;

    public BlockColumnClusterFeature(final List<BlockColumnFeature.Layer> layers,
                                     final @Nullable Holder<BlockStateProvider> topDecoration,
                                     final float topDecorationChance,
                                     final Direction direction,
                                     final BlockPredicate allowedPlacement, final boolean prioritizeTip,
                                     final IntProvider size, final IntProvider falloffPerStep,
                                     final IntProvider jitter, final IntProvider sunkenDepth) {
        this.layers = layers;
        this.topDecoration = topDecoration;
        this.topDecorationChance = topDecorationChance;
        this.direction = direction;
        this.allowedPlacement = allowedPlacement;
        this.prioritizeTip = prioritizeTip;
        this.size = size;
        this.falloffPerStep = falloffPerStep;
        this.jitter = jitter;
        this.sunkenDepth = sunkenDepth;
    }

    @Override
    public MapCodec<BlockColumnClusterFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        int size = this.size.sample(random);
        int peakX = size % 2 == 1 ? size / 2 : random.nextInt(2) * (size - 1);
        int peakZ = size % 2 == 1 ? size / 2 : random.nextInt(2) * (size - 1);

        int layerCount = this.layers.size();
        int[] baseLayerHeights = new int[layerCount];
        int baseTotalHeight = 0;

        for (int i = 0; i < layerCount; i++) {
            baseLayerHeights[i] = this.layers.get(i).height().sample(random);
            baseTotalHeight += baseLayerHeights[i];
        }

        if (baseTotalHeight == 0) {
            return false;
        }

        int falloffPerStep = this.falloffPerStep.sample(random);
        int sunkenDepth = this.sunkenDepth.sample(random);
        BlockColumnFeature.Layer baseLayer = this.layers.get(0);
        boolean placedAny = false;

        for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                int distance = Math.max(Math.abs(x - peakX), Math.abs(z - peakZ));
                int jitter = this.jitter.sample(random);
                int targetHeight = Math.clamp(baseTotalHeight - (long) distance * falloffPerStep + jitter, 0, baseTotalHeight);

                if (targetHeight == 0) {
                    continue;
                }

                int[] layerHeights = baseLayerHeights.clone();
                truncate(layerHeights, baseTotalHeight, targetHeight, this.prioritizeTip);

                BlockPos.MutableBlockPos sunkenPos = origin.offset(x, 0, z).mutable().move(this.direction.getOpposite(), sunkenDepth);

                for (int y = 0; y < sunkenDepth; y++) {
                    level.setBlock(sunkenPos, baseLayer.state().value().getState(level, random, sunkenPos), 2);
                    sunkenPos.move(this.direction);
                    placedAny = true;
                }

                BlockPos.MutableBlockPos placePos = origin.offset(x, 0, z).mutable();
                BlockPos.MutableBlockPos nextPos = placePos.mutable().move(this.direction);
                int columnTotal = 0;

                for (int height : layerHeights) {
                    columnTotal += height;
                }

                for (int y = 0; y < columnTotal; y++) {
                    if (!this.allowedPlacement.test(level, nextPos)) {
                        truncate(layerHeights, columnTotal, y, this.prioritizeTip);
                        break;
                    }

                    nextPos.move(this.direction);
                }

                int placedHeight = 0;

                for (int i = 0; i < layerCount; i++) {
                    int count = layerHeights[i];

                    if (count != 0) {
                        BlockColumnFeature.Layer layer = this.layers.get(i);

                        for (int y = 0; y < count; y++) {
                            level.setBlock(placePos, layer.state().value().getState(level, random, placePos), 2);
                            placePos.move(this.direction);
                        }

                        placedHeight += count;
                        placedAny = true;
                    }
                }

                if (placedHeight > 0
                        && this.topDecoration != null
                        && random.nextFloat() < this.topDecorationChance
                        && this.allowedPlacement.test(level, placePos)) {
                    level.setBlock(placePos, this.topDecoration.value().getState(level, random, placePos), 2);
                }
            }
        }

        return placedAny;
    }

    private static void truncate(final int[] layerHeights, final int totalHeight, final int newHeight, final boolean prioritizeTip) {
        int amountToRemove = totalHeight - newHeight;
        int direction = prioritizeTip ? 1 : -1;
        int start = prioritizeTip ? 0 : layerHeights.length - 1;
        int end = prioritizeTip ? layerHeights.length : -1;

        for (int i = start; i != end && amountToRemove > 0; i += direction) {
            int thisLayer = layerHeights[i];
            int toRemoveFromLayer = Math.min(thisLayer, amountToRemove);
            amountToRemove -= toRemoveFromLayer;
            layerHeights[i] -= toRemoveFromLayer;
        }
    }
}