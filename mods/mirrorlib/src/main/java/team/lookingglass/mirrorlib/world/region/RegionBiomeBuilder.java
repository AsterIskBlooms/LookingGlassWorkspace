
package team.lookingglass.mirrorlib.world.region;

import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.function.Consumer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import team.lookingglass.mirrorlib.mixin.world.OverworldBiomeGenAccessor;

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
    }


    private final BiomeGrid oceans;
    private final BiomeGrid middle;
    private final BiomeGrid middleVariant;
    private final BiomeGrid plateau;
    private final BiomeGrid plateauVariant;
    private final BiomeGrid shattered;

    public RegionBiomeBuilder() {
        OverworldBiomeGenAccessor defaults =
                (OverworldBiomeGenAccessor) (Object) new OverworldBiomeBuilder();

        this.middle = new BiomeGrid(defaults.getMiddleBiomes());
        this.middleVariant = new BiomeGrid(defaults.getMiddleBiomesVariant(), this.middle);
        this.plateau = new BiomeGrid(defaults.getPlateauBiomes(), this.middle);
        this.plateauVariant = new BiomeGrid(defaults.getPlateauBiomesVariant(), this.plateau, this.middleVariant, this.middle);
        this.shattered = new BiomeGrid(defaults.getShatteredBiomes(), this.plateau, this.middle);
        this.oceans = new BiomeGrid(defaults.getOceans());
    }

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

    public void addBiomes(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes
    ) {
        OverworldBiomeBuilder builder = new OverworldBiomeBuilder();
        OverworldBiomeGenAccessor accessor =
                (OverworldBiomeGenAccessor) (Object) builder;

        accessor.setMiddleBiomes(this.middle.values());
        accessor.setMiddleBiomesVariant(this.middleVariant.values());
        accessor.setPlateauBiomes(this.plateau.values());
        accessor.setPlateauBiomesVariant(this.plateauVariant.values());

        copyInto(accessor.getOceans(), this.oceans.values());
        copyInto(accessor.getShatteredBiomes(), this.shattered.values());

        int previous = BUILD_DEPTH.get();
        BUILD_DEPTH.set(previous + 1);

        try {
            accessor.invokeAddBiomes(biomes);
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
}
