package team.lookingglass.elsewhere.registry.sets;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;
import team.lookingglass.elsewhere.worldgen.features.EFeatures;

public interface ETreeGrowers {

    TreeGrower CEDAR = new TreeGrower(
            "cedar",
            WeightedList.<ResourceKey<Feature>>builder().add(EFeatures.CEDAR_TREE).build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            EFeatures.CEDAR_TREE
    );
    TreeGrower MAHOGANY = new TreeGrower(
            "mahogany",
            WeightedList.<ResourceKey<Feature>>builder().add(EFeatures.MAHOGANY_TREE).build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            EFeatures.MAHOGANY_TREE
    );

    static void initialize() {
    }
}