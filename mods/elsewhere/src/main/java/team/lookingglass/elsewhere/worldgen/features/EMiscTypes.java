package team.lookingglass.elsewhere.worldgen.features;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.worldgen.features.types.EAlterGroundDecorator;
import team.lookingglass.elsewhere.worldgen.features.types.EPineFoliagePlacer;

public interface EMiscTypes {
    FoliagePlacerType<EPineFoliagePlacer> PINE_FOLIAGE_PLACER = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "pine_foliage_placer"), new FoliagePlacerType<>(EPineFoliagePlacer.CODEC));
    TreeDecoratorType<EAlterGroundDecorator> ALTER_GROUND = Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "alter_ground"), new TreeDecoratorType<>(EAlterGroundDecorator.CODEC));

    static void initialize() {}
}
