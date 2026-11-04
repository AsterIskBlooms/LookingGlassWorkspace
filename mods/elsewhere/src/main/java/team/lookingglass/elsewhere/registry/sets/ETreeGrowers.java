package team.lookingglass.elsewhere.registry.sets;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;
import team.lookingglass.elsewhere.worldgen.features.EFeatures;

public interface ETreeGrowers {

    TreeGrower CEDAR = new TreeGrower(
            WeightedList.<ResourceKey<Feature>>builder().add(EFeatures.CEDAR_TREE).build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            EFeatures.CEDAR_TREE
    );
    TreeGrower MAHOGANY = new TreeGrower(
            WeightedList.<ResourceKey<Feature>>builder().add(EFeatures.MAHOGANY_TREE).build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            EFeatures.MAHOGANY_TREE
    );
    TreeGrower PINE = new TreeGrower(
            WeightedList.<ResourceKey<Feature>>builder().add(EFeatures.PINE_TREE).build(),
            WeightedList.<ResourceKey<Feature>>builder().add(EFeatures.MEGA_PINE_TREE).build(),
            WeightedList.<ResourceKey<Feature>>builder().build(),
            EFeatures.PINE_TREE
    );

    static void initialize() {
    }
}