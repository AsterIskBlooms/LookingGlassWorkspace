package team.lookingglass.mirrorlib.world;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class WorldPatch {
    private static final List<Consumer<BootstrapContext<Biome>>> BIOMES = new ArrayList<>();
    private static final List<Consumer<BootstrapContext<Feature>>> CONFIGURED_FEATURES = new ArrayList<>();
    private static final List<Consumer<BootstrapContext<PlacedFeature>>> PLACED_FEATURES = new ArrayList<>();

    private WorldPatch() {}

    public static void biomes(Consumer<BootstrapContext<Biome>> bootstrap) {
        BIOMES.add(bootstrap);
    }

    public static void features(Consumer<BootstrapContext<Feature>> bootstrap) {
        CONFIGURED_FEATURES.add(bootstrap);
    }

    public static void placements(Consumer<BootstrapContext<PlacedFeature>> bootstrap) {
        PLACED_FEATURES.add(bootstrap);
    }

    public static void applyBiomes(BootstrapContext<Biome> context) {
        for (Consumer<BootstrapContext<Biome>> bootstrap : BIOMES) bootstrap.accept(context);
    }

    public static void applyFeatures(BootstrapContext<Feature> context) {
        for (Consumer<BootstrapContext<Feature>> bootstrap : CONFIGURED_FEATURES) bootstrap.accept(context);
    }

    public static void applyPlacements(BootstrapContext<PlacedFeature> context) {
        for (Consumer<BootstrapContext<PlacedFeature>> bootstrap : PLACED_FEATURES) bootstrap.accept(context);
    }
}
