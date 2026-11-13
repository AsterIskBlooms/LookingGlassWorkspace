package team.lookingglass.mirrorlib.mixin.world;

import com.mojang.datafixers.util.Pair;
import java.util.function.Consumer;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.mirrorlib.world.cave.CaveRegistry;
import team.lookingglass.mirrorlib.world.cave.Underground;
import team.lookingglass.mirrorlib.world.region.Region;
import team.lookingglass.mirrorlib.world.region.RegionBiomeBuilder;

@Mixin(OverworldBiomeBuilder.class)
public abstract class OverworldBiomeBuilderMixin implements Region.RegionHolder {
    @Unique
    private RegionBiomeBuilder mirrorlib$region;

    @Override
    public void mirrorlib$setRegion(RegionBiomeBuilder region) {
        this.mirrorlib$region = region;
    }

    @Inject(method = "pickBeachBiome", at = @At("HEAD"), cancellable = true)
    private void mirrorlib$beach(int temperatureIndex, int humidityIndex, CallbackInfoReturnable<ResourceKey<Biome>> cir) {
        if (this.mirrorlib$region != null) {
            cir.setReturnValue(this.mirrorlib$region.pickBeach(temperatureIndex, humidityIndex));
        }
    }

    @Inject(method = "pickBadlandsBiome", at = @At("HEAD"), cancellable = true)
    private void mirrorlib$badlands(int humidityIndex, Climate.Parameter weirdness, CallbackInfoReturnable<ResourceKey<Biome>> cir) {
        if (this.mirrorlib$region != null) {
            cir.setReturnValue(this.mirrorlib$region.pickBadlands(humidityIndex, weirdness.max() >= 0L));
        }
    }

    @Inject(method = "addUndergroundBiomes", at = @At("TAIL"))
    private void mirrorlib$caves(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, CallbackInfo ci) {
        CaveRegistry.apply(new Underground((OverworldBiomeGenAccessor) (Object) this, biomes));
    }

    @ModifyVariable(method = "addUndergroundBiomes", at = @At("HEAD"), argsOnly = true)
    private Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mirrorlib$filterVanillaCaves(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes) {
        return pair -> { if (!CaveRegistry.isSuppressed(pair.getSecond())) biomes.accept(pair); };
    }
}