package team.lookingglass.elsewhere.mixin.world;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public class CaveMixin {
    @Redirect(method = "addSurfaceBiome", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 1))
    private void elsewhere$skipUndergroundSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes, Object pair) {}
}
