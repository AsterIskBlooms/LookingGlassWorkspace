package team.lookingglass.mirrorlib.mixin.world;

import com.mojang.datafixers.util.Either;
import java.util.Arrays;
import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.biome.NoiseBiomeResolver;
import net.minecraft.world.level.levelgen.densityfunction.DensityBuffer;
import net.minecraft.world.level.levelgen.densityfunction.DensityVolume;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.mirrorlib.world.region.RegionDebug;
import team.lookingglass.mirrorlib.world.region.RegionLists;
import team.lookingglass.mirrorlib.world.region.RegionListsHolder;
import team.lookingglass.mirrorlib.world.region.RegionSelector;
import team.lookingglass.mirrorlib.world.region.RegionSelectors;

@Mixin(MultiNoiseBiomeSource.class)
public abstract class MultiNoiseBiomeSourceMixin {

    @Shadow
    @Final
    private Either<Climate.ParameterList<Holder<Biome>>, Holder<MultiNoiseBiomeSourceParameterList>> parameters;

    @Unique
    private volatile boolean mirrorlib$resolved;

    @Unique
    private volatile RegionLists mirrorlib$lists;

    @Inject(method = "createResolver", at = @At("HEAD"), cancellable = true)
    private void mirrorlib$createRegionResolver(final Climate.Sampler sampler, final CallbackInfoReturnable<NoiseBiomeResolver> cir) {
        RegionLists lists = this.mirrorlib$lists();
        RegionSelector selector = RegionSelectors.get(sampler);
        if (lists == null || selector == null) {
            return;
        }

        cir.setReturnValue((quartX, quartY, quartZ) -> {
            int region = selector.select(QuartPos.toBlock(quartX) + 2, QuartPos.toBlock(quartZ) + 2);
            return lists.region(region).findValue(sampler.sample(quartX, quartY, quartZ));
        });
    }

    @Inject(method = "createResolverForChunk", at = @At("HEAD"), cancellable = true)
    private void mirrorlib$createRegionChunkResolver(final Climate.Sampler sampler, final int minQuartX, final int minQuartY, final int minQuartZ, final int quartSizeX, final int quartSizeY, final int quartSizeZ, final CallbackInfoReturnable<NoiseBiomeResolver> cir) {
        RegionLists lists = this.mirrorlib$lists();
        RegionSelector selector = RegionSelectors.get(sampler);
        if (lists == null || selector == null) {
            return;
        }

        DensityVolume volume = new DensityVolume(quartSizeX, quartSizeY, quartSizeZ, QuartPos.toBlock(minQuartX), QuartPos.toBlock(minQuartY), QuartPos.toBlock(minQuartZ), 4, 4, 4);
        DensityBuffer temperature = DensityBuffer.createUnpooled(volume.size());
        DensityBuffer vegetation = DensityBuffer.createUnpooled(volume.size());
        DensityBuffer continents = DensityBuffer.createUnpooled(volume.size());
        DensityBuffer erosion = DensityBuffer.createUnpooled(volume.size());
        DensityBuffer depth = DensityBuffer.createUnpooled(volume.size());
        DensityBuffer ridges = DensityBuffer.createUnpooled(volume.size());
        sampler.temperature().sampleVolume(temperature, volume);
        sampler.humidity().sampleVolume(vegetation, volume);
        sampler.continentalness().sampleVolume(continents, volume);
        sampler.erosion().sampleVolume(erosion, volume);
        sampler.depth().sampleVolume(depth, volume);
        sampler.weirdness().sampleVolume(ridges, volume);

        int[] regionColumns = new int[quartSizeX * quartSizeZ];
        Arrays.fill(regionColumns, -1);

        cir.setReturnValue((quartX, quartY, quartZ) -> {
            int localX = quartX - minQuartX;
            int localZ = quartZ - minQuartZ;
            int index = volume.indexUnchecked(localX, quartY - minQuartY, localZ);

            int column = localX + localZ * quartSizeX;
            int region = regionColumns[column];
            if (region < 0) {
                region = selector.select(QuartPos.toBlock(quartX) + 2, QuartPos.toBlock(quartZ) + 2);
                regionColumns[column] = region;
            }

            return lists.region(region).findValue(Climate.target(temperature.get(index), vegetation.get(index), continents.get(index), erosion.get(index), depth.get(index), ridges.get(index)));
        });
    }

    @Inject(method = "addDebugInfo", at = @At("TAIL"))
    private void mirrorlib$addRegionDebugInfo(final BiConsumer<String, String> addFact, final BlockPos feetPos, final Climate.Sampler sampler, final CallbackInfo ci) {
        if (this.mirrorlib$lists() == null) {
            return;
        }

        RegionSelector selector = RegionSelectors.get(sampler);
        if (selector != null) {
            addFact.accept("Region", RegionDebug.describe(selector, feetPos.getX(), feetPos.getZ()));
        }
    }

    @Unique
    private RegionLists mirrorlib$lists() {
        if (!this.mirrorlib$resolved) {
            this.mirrorlib$lists = this.parameters.right()
                    .map(holder -> ((RegionListsHolder) (Object) holder.value()).mirrorlib$regionLists())
                    .orElse(null);
            this.mirrorlib$resolved = true;
        }

        return this.mirrorlib$lists;
    }
}