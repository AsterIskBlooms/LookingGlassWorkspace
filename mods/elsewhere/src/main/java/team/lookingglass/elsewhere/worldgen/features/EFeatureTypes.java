package team.lookingglass.elsewhere.worldgen.features;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.worldgen.features.types.*;

public class EFeatureTypes {
    public static void initialize() {
        Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "amethyst_node"), AmethystNodeFeature.CODEC);
        Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "block_column_cluster"), BlockColumnClusterFeature.CODEC);
        Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "block_patch"), BlockPatchFeature.CODEC);
        Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "large_blob"), LargeBlobFeature.CODEC);
        Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "tidepool"), TidePoolFeature.CODEC);
        Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "crag"), CragFeature.CODEC);
    }
}
