package team.lookingglass.mirrorlib.world.region;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public final class RegionRegistry {
    public static final float VANILLA_WEIGHT = 100.0F;

    @FunctionalInterface
    public interface Provider {
        void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes);
    }

    public record RegionInfo(
            ResourceKey<Region> key,
            float weight,
            Provider provider,
            boolean vanilla
    ) {}

    public static final class Snapshot {
        private final List<RegionInfo> regions;
        private final float[] weights;
        private final Map<Long, RegionSelector> selectors = new ConcurrentHashMap<>();

        private Snapshot(List<RegionInfo> regions) {
            this.regions = List.copyOf(regions);
            this.weights = new float[regions.size()];
            for (int i = 0; i < this.weights.length; i++) {
                this.weights[i] = regions.get(i).weight();
            }
        }

        public List<RegionInfo> regions() {
            return this.regions;
        }

        public RegionSelector selector(long seed) {
            return this.selectors.computeIfAbsent(seed,
                    s -> new RegionSelector(s, RegionSelector.DEFAULT_SCALE, this.weights));
        }

        public RegionInfo select(long seed, int blockX, int blockZ) {
            return this.regions.get(this.selector(seed).select(blockX, blockZ));
        }

        public RegionInfo vanilla() {
            return this.regions.stream()
                    .filter(RegionInfo::vanilla)
                    .findFirst()
                    .orElseThrow();
        }
    }

    private record Registration(float weight, Provider provider) {
    }

    private static final Map<ResourceKey<Region>, Registration> REGIONS = new LinkedHashMap<>();

    private static Registration vanillaReplacement;
    private static Snapshot snapshot;

    private RegionRegistry() {
    }

    /**
     * Registers an additional region.
     * Vanilla has a default weight of 100.
     */
    public static synchronized void addRegion(
            ResourceKey<Region> key,
            float weight,
            Provider provider
    ) {
        checkMutable();

        Objects.requireNonNull(key);
        Objects.requireNonNull(provider);
        validateWeight(weight);

        if (key.equals(Region.VANILLA)) {
            throw new IllegalArgumentException("Use replaceVanillaRegion() to replace vanilla.");
        }

        REGIONS.put(key, new Registration(weight, provider));
    }

    /**
     * Replaces vanilla's biome layout while retaining its default weight.
     */
    public static synchronized void replaceVanillaRegion(Provider provider) {
        replaceVanillaRegion(VANILLA_WEIGHT, provider);
    }

    /**
     * Replaces vanilla's biome layout and changes its weight.
     */
    public static synchronized void replaceVanillaRegion(float weight, Provider provider) {
        checkMutable();
        Objects.requireNonNull(provider);
        validateWeight(weight);
        vanillaReplacement = new Registration(weight, provider);
    }

    /**
     * Freezes registrations and creates the weighted region distribution.
     */
    public static synchronized Snapshot freezeAndGet() {
        if (snapshot != null) {
            return snapshot;
        }

        List<RegionInfo> regions = new ArrayList<>();

        Registration vanilla = vanillaReplacement;
        regions.add(new RegionInfo(
                Region.VANILLA,
                vanilla == null ? VANILLA_WEIGHT : vanilla.weight(),
                vanilla == null ? null : vanilla.provider(),
                true
        ));

        REGIONS.forEach((key, registration) ->
                regions.add(new RegionInfo(key, registration.weight(), registration.provider(), false))
        );

        snapshot = new Snapshot(regions);
        return snapshot;
    }

    private static void checkMutable() {
        if (snapshot != null) {
            throw new IllegalStateException(
                    "[MirrorLib] Region registration occurred after the region distribution was frozen."
            );
        }
    }

    private static void validateWeight(float weight) {
        if (!Float.isFinite(weight) || weight <= 0.0F) {
            throw new IllegalArgumentException("Region weight must be finite and positive: " + weight);
        }
    }

    private record RegistrationEntry(
            ResourceKey<Region> key,
            float weight,
            Provider provider,
            boolean vanilla
    ) {}
}