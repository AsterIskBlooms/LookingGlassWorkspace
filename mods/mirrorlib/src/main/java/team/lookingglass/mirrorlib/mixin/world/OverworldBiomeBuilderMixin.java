package team.lookingglass.mirrorlib.mixin.world;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.mirrorlib.world.OverworldBiomeEvents;
import team.lookingglass.mirrorlib.world.WorldOptions;
import team.lookingglass.mirrorlib.world.OverworldBiomeView;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public abstract class OverworldBiomeBuilderMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void mirrorlib$modifyGrid(CallbackInfo ci) {
        OverworldBiomeEvents.MODIFY_GRID.invoker().add(OverworldBiomeView.of((OverworldBiomeGenAccessor) this));
    }

    @Inject(method = "addUndergroundBiomes", at = @At("TAIL"))
    private void mirrorlib$addUnderground(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, CallbackInfo ci) {
        OverworldBiomeEvents.ADD_UNDERGROUND.invoker().add(OverworldBiomeView.of((OverworldBiomeGenAccessor) this), biomes);
    }

    @WrapWithCondition(method = "addSurfaceBiome", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 1))
    private boolean mirrorlib$skipUndergroundCopy(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Object pair) {
        return false;
    }

    @WrapOperation(method = {"addValleys", "addMidSlice", "addLowSlice"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;addSurfaceBiome(Ljava/util/function/Consumer;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;Lnet/minecraft/world/level/biome/Climate$Parameter;FLnet/minecraft/resources/ResourceKey;)V"))
    private void mirrorlib$rewriteSurface(OverworldBiomeBuilder builder, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome, Operation<Void> original) {
        OverworldBiomeView view = OverworldBiomeView.of((OverworldBiomeGenAccessor) (Object) builder);
        WorldOptions.SurfaceEntry entry = new WorldOptions.SurfaceEntry(temperature, humidity, continentalness, erosion, weirdness, offset, biome);
        for (WorldOptions.SurfaceEntry e : OverworldBiomeEvents.REWRITE_SURFACE.invoker().rewrite(view, entry)) {
            original.call(builder, biomes, e.temperature(), e.humidity(), e.continentalness(), e.erosion(), e.weirdness(), e.offset(), e.biome());
        }
    }

    @ModifyReturnValue(method = "pickBadlandsBiome", at = @At("RETURN"))
    private ResourceKey<Biome> mirrorlib$pickBadlands(ResourceKey<Biome> original, int humidityIndex, Climate.Parameter weirdness) {
        return OverworldBiomeEvents.PICK_BADLANDS.invoker().pick(OverworldBiomeView.of((OverworldBiomeGenAccessor) this), 4, humidityIndex, weirdness, original);
    }
}
