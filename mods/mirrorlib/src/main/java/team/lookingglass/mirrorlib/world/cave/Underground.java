package team.lookingglass.mirrorlib.world.cave;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import team.lookingglass.mirrorlib.mixin.world.OverworldBiomeGenAccessor;

import java.util.function.Consumer;

public final class Underground {
    private final OverworldBiomeGenAccessor acc;
    private final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> out;

    public Underground(OverworldBiomeGenAccessor acc, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> out) {
        this.acc = acc;
        this.out = out;
    }

    public Climate.Parameter fullRange() { return acc.getFULL_RANGE(); }
    public Climate.Parameter[] temperatures() { return acc.getTemperatures(); }
    public Climate.Parameter[] humidities() { return acc.getHumidities(); }
    public Climate.Parameter[] erosions() { return acc.getErosions(); }
    public Climate.Parameter coast() { return acc.getCoastContinentalness(); }
    public Climate.Parameter inland() { return acc.getInlandContinentalness(); }
    public Climate.Parameter deepOcean() { return acc.getDeepOceanContinentalness(); }
    public Climate.Parameter ocean() { return acc.getOceanContinentalness(); }

    /** Depth 0.2-0.9, like vanilla's addUndergroundBiome. */
    public void underground(Climate.Parameter t, Climate.Parameter h, Climate.Parameter c,
                            Climate.Parameter e, Climate.Parameter w, float offset, ResourceKey<Biome> biome) {
        acc.invokeAddUndergroundBiome(out, t, h, c, e, w, offset, biome);
    }

    /** Depth 1.1, like vanilla's addBottomBiome. */
    public void bottom(Climate.Parameter t, Climate.Parameter h, Climate.Parameter c,
                       Climate.Parameter e, Climate.Parameter w, float offset, ResourceKey<Biome> biome) {
        acc.invokeAddBottomBiome(out, t, h, c, e, w, offset, biome);
    }

    /** Raw point with a custom depth span, for deep caves and default fill. */
    public void point(Climate.Parameter t, Climate.Parameter h, Climate.Parameter c, Climate.Parameter e,
                      Climate.Parameter depth, Climate.Parameter w, float offset, ResourceKey<Biome> biome) {
        out.accept(Pair.of(Climate.parameters(t, h, c, e, depth, w, offset), biome));
    }
}