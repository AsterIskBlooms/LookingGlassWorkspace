package team.lookingglass.elsewhere.mixin.world;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.worldgen.features.EFeatures;

@Mixin(TreeGrower.class)
public class TreeGrowerMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void elsewhere$treeChanges(CallbackInfo ci) {
        TreeGrower.ACACIA = new TreeGrower(
                "acacia",
                WeightedList.<ResourceKey<Feature>>builder().add(TreeFeatures.ACACIA).build(),
                WeightedList.<ResourceKey<Feature>>builder().add(EFeatures.MEGA_ACACIA).build(),
                WeightedList.<ResourceKey<Feature>>builder().build(),
                TreeFeatures.ACACIA
        );
        TreeGrower.BIRCH = new TreeGrower(
                "birch",
                WeightedList.<ResourceKey<Feature>>builder().add(TreeFeatures.BIRCH).add(EFeatures.GILDED_BIRCH_CLEAR).build(),
                WeightedList.<ResourceKey<Feature>>builder().build(),
                WeightedList.<ResourceKey<Feature>>builder().build(),
                TreeFeatures.BIRCH
        );
    }
}