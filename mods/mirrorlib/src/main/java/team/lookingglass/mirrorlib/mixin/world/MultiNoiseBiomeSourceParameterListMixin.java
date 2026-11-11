package team.lookingglass.mirrorlib.mixin.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.mirrorlib.world.region.RegionListsHolder;
import team.lookingglass.mirrorlib.world.region.RegionLists;
import team.lookingglass.mirrorlib.world.region.RegionRegistry;

@Mixin(MultiNoiseBiomeSourceParameterList.class)
public abstract class MultiNoiseBiomeSourceParameterListMixin implements RegionListsHolder {

    @Shadow
    @Final
    @Mutable
    private Climate.ParameterList<Holder<Biome>> parameters;

    @Unique
    private RegionLists mirrorlib$regionLists;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void mirrorlib$buildRegionLists(final MultiNoiseBiomeSourceParameterList.Preset preset, final HolderGetter<Biome> biomes, final CallbackInfo ci) {
        if (!MultiNoiseBiomeSourceParameterList.Preset.OVERWORLD.equals(preset)) {
            return;
        }

        RegionLists lists = RegionLists.build(RegionRegistry.freezeAndGet(), biomes::getOrThrow);
        this.mirrorlib$regionLists = lists;
        this.parameters = lists.union();
    }

    @Override
    public RegionLists mirrorlib$regionLists() {
        return this.mirrorlib$regionLists;
    }
}