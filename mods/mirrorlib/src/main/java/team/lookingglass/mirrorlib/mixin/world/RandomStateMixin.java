package team.lookingglass.mirrorlib.mixin.world;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.densityfunction.SamplerContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.mirrorlib.world.region.RegionSelectors;

@Mixin(RandomState.class)
public abstract class RandomStateMixin {
    @Shadow @Final private long seed;

    @Inject(method = "createClimateSampler", at = @At("RETURN"))
    private void mirrorlib$bindRegionSelector(final SamplerContext context, final CallbackInfoReturnable<Climate.Sampler> cir) {
        RegionSelectors.bind(cir.getReturnValue(), this.seed);
    }
}