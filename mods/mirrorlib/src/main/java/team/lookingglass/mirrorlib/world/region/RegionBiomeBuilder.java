
package team.lookingglass.mirrorlib.world.region;

import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.function.Consumer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import team.lookingglass.mirrorlib.MirrorLib;
import team.lookingglass.mirrorlib.mixin.world.OverworldBiomeGenAccessor;

@SuppressWarnings("unchecked")
public final class RegionBiomeBuilder {
    private static final ThreadLocal<Integer> BUILD_DEPTH =
            ThreadLocal.withInitial(() -> 0);

    public static final class BiomeGrid {
        private final ResourceKey<Biome>[][] original;
        private final BiomeGrid[] fallbacks;
        private ResourceKey<Biome>[][] values;

        private BiomeGrid(ResourceKey<Biome>[][] original, BiomeGrid... fallbacks) {
            this.original = copy(original);
            this.values = copy(original);
            this.fallbacks = fallbacks;
        }

        /** Only the value set on this grid itself, or null. Never consults fallbacks. */
        private ResourceKey<Biome> own(int temperature, int humidity) {
            ResourceKey<Biome>[] row = this.values[temperature];
            return row == null ? null : row[humidity];
        }

        public ResourceKey<Biome> get(int temperature, int humidity) {
            ResourceKey<Biome> own = this.own(temperature, humidity);
            if (own != null) {
                return own;
            }
            for (int i = 0; i < this.fallbacks.length; i++) {
                BiomeGrid grid = this.fallbacks[i];
                ResourceKey<Biome> found = i == this.fallbacks.length - 1
                        ? grid.get(temperature, humidity)
                        : grid.own(temperature, humidity);
                if (found != null) {
                    return found;
                }
            }

            return this.original[temperature][humidity];
        }

        public BiomeGrid replace(ResourceKey<Biome>[][] values) {
            Objects.requireNonNull(values);

            if (values.length != this.original.length) {
                throw new IllegalArgumentException("Biome grid row count mismatch");
            }

            for (int i = 0; i < values.length; i++) {
                if (values[i] != null && values[i].length != this.original[i].length) {
                    throw new IllegalArgumentException("Biome grid column count mismatch at row " + i);
                }
            }

            this.values = copy(values);
            return this;
        }

        public ResourceKey<Biome>[][] values() {
            ResourceKey<Biome>[][] result = copy(this.original);

            for (int t = 0; t < result.length; t++) {
                for (int h = 0; h < result[t].length; h++) {
                    result[t][h] = this.get(t, h);
                }
            }

            return result;
        }

        private static ResourceKey<Biome>[][] copy(ResourceKey<Biome>[][] source) {
            ResourceKey<Biome>[][] result = source.clone();

            for (int i = 0; i < source.length; i++) {
                result[i] = source[i] == null ? null : source[i].clone();
            }

            return result;
        }

        /** One biome per temperature row, repeated across all humidities: { frozen, cold, normal, warm, warm }. */
        public BiomeGrid byTemperature(ResourceKey<Biome>[] perTemperature) {
            if (perTemperature.length != this.original.length) {
                throw new IllegalArgumentException("Need one entry per temperature row");
            }
            ResourceKey<Biome>[][] grid = new ResourceKey[perTemperature.length][];
            for (int t = 0; t < grid.length; t++) {
                grid[t] = new ResourceKey[this.original[t].length];
                java.util.Arrays.fill(grid[t], perTemperature[t]);
            }
            return this.replace(grid);
        }

        /** For 1-row grids (beaches, badlands). */
        public BiomeGrid replaceRow(ResourceKey<Biome>[] row) {
            if (this.original.length != 1) throw new IllegalStateException("Not a single-row grid");
            return this.replace(new ResourceKey[][]{ row });
        }
    }

    public static final ResourceKey<Biome> MIDDLE = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MirrorLib.MODID, "middle"));
    private final BiomeGrid oceans;
    private final BiomeGrid middle;
    private final BiomeGrid middleVariant;
    private final BiomeGrid plateau;
    private final BiomeGrid plateauVariant;
    private final BiomeGrid shattered;

    public RegionBiomeBuilder() {
        OverworldBiomeGenAccessor defaults = (OverworldBiomeGenAccessor) (Object) new OverworldBiomeBuilder();

        this.middle = new BiomeGrid(defaults.getMiddleBiomes());
        this.middleVariant = new BiomeGrid(defaults.getMiddleBiomesVariant(), this.middle);
        this.plateau = new BiomeGrid(defaults.getPlateauBiomes(), this.middle);
        this.plateauVariant = new BiomeGrid(defaults.getPlateauBiomesVariant(), this.plateau, this.middleVariant, this.middle);
        this.shattered = new BiomeGrid(defaults.getShatteredBiomes(), this.plateau, this.middle);
        this.oceans = new BiomeGrid(defaults.getOceans());
    }

    // Vanilla biomes translated to grid
    private final BiomeGrid beaches = new BiomeGrid(fillRows(Biomes.SNOWY_BEACH, Biomes.BEACH, Biomes.BEACH, Biomes.BEACH, Biomes.DESERT));
    private final BiomeGrid badlands = new BiomeGrid(new ResourceKey[][]{row(Biomes.BADLANDS, Biomes.BADLANDS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS) });
    private final BiomeGrid badlandsVariant = new BiomeGrid(new ResourceKey[][]{row(Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, null, null) }, this.badlands);
    private final BiomeGrid rivers = new BiomeGrid(fillRows(Biomes.FROZEN_RIVER, Biomes.RIVER, Biomes.RIVER, Biomes.RIVER, Biomes.RIVER));
    private final BiomeGrid swamps = new BiomeGrid(fillRows(Biomes.FROZEN_RIVER, Biomes.SWAMP, Biomes.SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP));
    public BiomeGrid oceans() {
        return this.oceans;
    }
    public BiomeGrid middle() {
        return this.middle;
    }
    public BiomeGrid middleVariant() {
        return this.middleVariant;
    }
    public BiomeGrid plateau() {
        return this.plateau;
    }
    public BiomeGrid plateauVariant() {
        return this.plateauVariant;
    }
    public BiomeGrid shattered() {
        return this.shattered;
    }
    public BiomeGrid beaches() { return this.beaches; }
    public BiomeGrid badlands() { return this.badlands; }
    public BiomeGrid badlandsVariant() { return this.badlandsVariant; }
    public BiomeGrid rivers() { return this.rivers; }
    public BiomeGrid swamps() { return this.swamps; }

    @SafeVarargs
    private static ResourceKey<Biome>[] row(ResourceKey<Biome>... keys) { return keys; }

    private static ResourceKey<Biome>[][] fillRows(ResourceKey<Biome>... perTemperature) {
        ResourceKey<Biome>[][] g = new ResourceKey[perTemperature.length][5];
        for (int t = 0; t < g.length; t++) java.util.Arrays.fill(g[t], perTemperature[t]);
        return g;
    }

    public ResourceKey<Biome> pickBadlands(int humidityIndex, boolean variant) {
        return (variant ? this.badlandsVariant : this.badlands).get(0, humidityIndex);
    }

    public ResourceKey<Biome> pickBeach(int temperatureIndex, int humidityIndex) {
        ResourceKey<Biome> beach = this.beaches.get(temperatureIndex, humidityIndex);
        return MIDDLE.equals(beach) ? this.middle.get(temperatureIndex, humidityIndex) : beach;
    }

    @SuppressWarnings({"ConstantConditions"})
    public void addBiomes(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes
    ) {
        OverworldBiomeBuilder builder = new OverworldBiomeBuilder();
        ((Region.RegionHolder) (Object) builder).mirrorlib$setRegion(this);
        OverworldBiomeGenAccessor accessor = (OverworldBiomeGenAccessor) (Object) builder;

        Climate.Parameter[] temps = accessor.getTemperatures();
        Climate.Parameter[] hums = accessor.getHumidities();

        accessor.setMiddleBiomes(this.middle.values());
        accessor.setMiddleBiomesVariant(this.middleVariant.values());
        accessor.setPlateauBiomes(this.plateau.values());
        accessor.setPlateauBiomesVariant(this.plateauVariant.values());
        copyInto(accessor.getOceans(), this.oceans.values());
        copyInto(accessor.getShatteredBiomes(), this.shattered.values());

        Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> wrapped = pair -> {
            ResourceKey<Biome> key = pair.getSecond();
            if (key.equals(Biomes.SWAMP)) {
                Climate.ParameterPoint p = pair.getFirst();
                Climate.Parameter t = new Climate.Parameter(temps[0].min(), p.temperature().max());
                this.emitGrid(this.swamps, Pair.of(new Climate.ParameterPoint(t, p.humidity(),
                        p.continentalness(), p.erosion(), p.depth(), p.weirdness(), p.offset()), key), temps, hums, biomes);
            } else if (key.equals(Biomes.MANGROVE_SWAMP)) {
                this.emitGrid(this.swamps, pair, temps, hums, biomes);
            } else if (key.equals(Biomes.RIVER) || key.equals(Biomes.FROZEN_RIVER)) {
                this.emitGrid(this.rivers, pair, temps, hums, biomes);
            } else {
                biomes.accept(pair);
            }
        };

        int previous = BUILD_DEPTH.get();
        BUILD_DEPTH.set(previous + 1);

        try {
            accessor.invokeAddBiomes(wrapped);
        } finally {
            if (previous == 0) {
                BUILD_DEPTH.remove();
            } else {
                BUILD_DEPTH.set(previous);
            }
        }
    }

    public static boolean isBuildingLayout() {
        return BUILD_DEPTH.get() > 0;
    }

    private static void copyInto(
            ResourceKey<Biome>[][] destination,
            ResourceKey<Biome>[][] source
    ) {
        for (int i = 0; i < destination.length; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, source[i].length);
        }
    }

    private static Climate.Parameter overlap(Climate.Parameter a, Climate.Parameter b) {
        long lo = Math.max(a.min(), b.min());
        long hi = Math.min(a.max(), b.max());
        if (lo > hi) return null;
        if (lo == hi && a.min() != a.max()) return null; // only touching a band edge
        return new Climate.Parameter(lo, hi);
    }

    private ResourceKey<Biome> cell(BiomeGrid grid, int t, int h, ResourceKey<Biome> fallback) {
        ResourceKey<Biome> k = grid.get(t, h);
        return k != null ? k : fallback;
    }

    private void emitGrid(BiomeGrid grid, Pair<Climate.ParameterPoint, ResourceKey<Biome>> pair,
                          Climate.Parameter[] temps, Climate.Parameter[] hums,
                          Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> out) {
        Climate.ParameterPoint p = pair.getFirst();
        boolean any = false;

        for (int t = 0; t < temps.length; t++) {
            Climate.Parameter tr = overlap(p.temperature(), temps[t]);
            if (tr == null) continue;

            int h = 0;
            while (h < hums.length) {
                Climate.Parameter first = overlap(p.humidity(), hums[h]);
                if (first == null) { h++; continue; }

                ResourceKey<Biome> target = cell(grid, t, h, pair.getSecond());
                long min = first.min(), max = first.max();
                int next = h + 1;
                while (next < hums.length) {
                    Climate.Parameter nr = overlap(p.humidity(), hums[next]);
                    if (nr == null || !cell(grid, t, next, pair.getSecond()).equals(target)) break;
                    max = nr.max();
                    next++;
                }

                out.accept(Pair.of(new Climate.ParameterPoint(tr, new Climate.Parameter(min, max),
                        p.continentalness(), p.erosion(), p.depth(), p.weirdness(), p.offset()), target));
                any = true;
                h = next;
            }
        }
        if (!any) out.accept(pair);
    }
}
