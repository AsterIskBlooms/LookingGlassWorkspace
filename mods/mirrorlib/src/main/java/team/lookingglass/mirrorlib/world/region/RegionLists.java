package team.lookingglass.mirrorlib.world.region;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

/**
 * regions: one parameter list per region, in snapshot order (same index the RegionSelector returns).
 * union:   every point of every region, so possible-biome queries still see all biomes.
 */
public record RegionLists(List<Climate.ParameterList<Holder<Biome>>> regions, Climate.ParameterList<Holder<Biome>> union) {

    public static RegionLists build(final RegionRegistry.Snapshot snapshot, final Function<ResourceKey<Biome>, Holder<Biome>> lookup) {
        List<Climate.ParameterList<Holder<Biome>>> regions = new ArrayList<>();
        List<Pair<Climate.ParameterPoint, Holder<Biome>>> union = new ArrayList<>();

        for (RegionRegistry.RegionInfo info : snapshot.regions()) {
            RegionRegistry.Provider provider = info.provider() != null ? info.provider() : biomes -> new RegionBiomeBuilder().addBiomes(biomes);

            List<Pair<Climate.ParameterPoint, Holder<Biome>>> points = new ArrayList<>();
            provider.addBiomes(pair -> points.add(pair.mapSecond(lookup)));

            regions.add(new Climate.ParameterList<>(points));
            union.addAll(points);
        }

        return new RegionLists(List.copyOf(regions), new Climate.ParameterList<>(union));
    }

    public Climate.ParameterList<Holder<Biome>> region(final int index) {
        return this.regions.get(index);
    }
}