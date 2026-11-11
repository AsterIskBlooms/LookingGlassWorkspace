package team.lookingglass.mirrorlib.world.region;

import com.google.common.collect.MapMaker;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.world.level.biome.Climate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RegionSelectors {
    private static final Logger LOGGER = LoggerFactory.getLogger("MirrorLib");
    private static final ConcurrentMap<Climate.Sampler, RegionSelector> SELECTORS = new MapMaker().weakKeys().makeMap();
    private static final AtomicBoolean WARNED = new AtomicBoolean();

    private RegionSelectors() {
    }

    public static void bind(final Climate.Sampler sampler, final long seed) {
        SELECTORS.put(sampler, RegionRegistry.freezeAndGet().selector(seed));
    }

    public static RegionSelector get(final Climate.Sampler sampler) {
        RegionSelector selector = SELECTORS.get(sampler);
        if (selector == null && WARNED.compareAndSet(false, true)) {
            LOGGER.warn("A Climate.Sampler was used for overworld biomes without a bound region selector; falling back to the combined biome list. The sampler was not created through RandomState.createClimateSampler.");
        }
        return selector;
    }
}