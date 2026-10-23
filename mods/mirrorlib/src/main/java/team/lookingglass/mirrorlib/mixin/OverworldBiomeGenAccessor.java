package team.lookingglass.mirrorlib.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public interface OverworldBiomeGenAccessor {
    @Mutable @Accessor("MIDDLE_BIOMES") ResourceKey<Biome>[][] getMiddleBiomes();
    @Mutable @Accessor("MIDDLE_BIOMES") void setMiddleBiomes(ResourceKey<Biome>[][] value);
    @Mutable @Accessor("MIDDLE_BIOMES_VARIANT") ResourceKey<Biome>[][] getMiddleBiomesVariant();
    @Mutable @Accessor("MIDDLE_BIOMES_VARIANT") void setMiddleBiomesVariant(ResourceKey<Biome>[][] value);
    @Mutable @Accessor("PLATEAU_BIOMES") ResourceKey<Biome>[][] getPlateauBiomes();
    @Mutable @Accessor("PLATEAU_BIOMES") void setPlateauBiomes(ResourceKey<Biome>[][] value);
    @Mutable @Accessor("PLATEAU_BIOMES_VARIANT") ResourceKey<Biome>[][] getPlateauBiomesVariant();
    @Mutable @Accessor("PLATEAU_BIOMES_VARIANT") void setPlateauBiomesVariant(ResourceKey<Biome>[][] value);
    @Accessor("SHATTERED_BIOMES") ResourceKey<Biome>[][] getShatteredBiomes();
    @Accessor("OCEANS") ResourceKey<Biome>[][] getOceans();
    @Accessor("FULL_RANGE") Climate.Parameter getFULL_RANGE();
    @Accessor("UNFROZEN_RANGE") Climate.Parameter getUnfrozenRange();
    @Accessor("temperatures") Climate.Parameter[] getTemperatures();

    @Invoker("addSurfaceBiome")
    void invokeAddSurfaceBiome(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes,
            Climate.Parameter temperature, Climate.Parameter humidity,
            Climate.Parameter continentalness, Climate.Parameter erosion,
            Climate.Parameter weirdness,
            float offset,
            ResourceKey<Biome> biome
    );
    @Invoker("addUndergroundBiome")
    void invokeAddUndergroundBiome(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes,
            Climate.Parameter temperature,
            Climate.Parameter humidity,
            Climate.Parameter continentalness,
            Climate.Parameter erosion,
            Climate.Parameter weirdness,
            float offset,
            ResourceKey<Biome> biome
    );
    @Invoker("addBottomBiome")
    void invokeAddBottomBiome(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes,
            Climate.Parameter temperature,
            Climate.Parameter humidity,
            Climate.Parameter continentalness,
            Climate.Parameter erosion,
            Climate.Parameter weirdness,
            float offset,
            ResourceKey<Biome> biome
    );
}