package team.lookingglass.elsewhere.worldgen.features;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.worldgen.features.types.EPineFoliagePlacer;

public interface EFoliagePlacers {
    FoliagePlacerType<EPineFoliagePlacer> PINE_FOLIAGE_PLACER = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "pine_foliage_placer"), new FoliagePlacerType<>(EPineFoliagePlacer.CODEC));

    static void initialize() {}
}
