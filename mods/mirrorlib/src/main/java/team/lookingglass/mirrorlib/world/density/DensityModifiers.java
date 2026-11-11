package team.lookingglass.mirrorlib.world.density;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import team.lookingglass.mirrorlib.mixin.world.NoiseGeneratorSettingsAccessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DensityModifiers {
    public static final int DEFAULT_PRIORITY = 1000;
    private static final Map<ResourceKey<DensityFunction>, List<Entry>> MODIFIERS = new HashMap<>();
    private static final Set<NoiseRouter> PATCHED = Collections.newSetFromMap(new IdentityHashMap<>());

    private DensityModifiers() {
    }

    public static void register(ResourceKey<DensityFunction> target, Modifier modifier) {
        register(target, DEFAULT_PRIORITY, modifier);
    }

    public static void register(ResourceKey<DensityFunction> target, int priority, Modifier modifier) {
        List<Entry> entries = MODIFIERS.computeIfAbsent(target, key -> new ArrayList<>());
        entries.add(new Entry(priority, modifier));
        entries.sort(Comparator.comparingInt(Entry::priority));
    }

    public static void initialize() {
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            if (client || MODIFIERS.isEmpty()) return;

            registries.lookupOrThrow(Registries.NOISE_SETTINGS).listElements()
                    .forEach(holder -> patch(registries, holder.value()));
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> PATCHED.clear());
    }

    private static void patch(HolderLookup.Provider registries, NoiseGeneratorSettings settings) {
        NoiseRouter router = settings.noiseRouter();
        if (PATCHED.contains(router)) return;

        DensityModifierRule rule = new DensityModifierRule(MODIFIERS, registries);
        NoiseRouter rewritten = router.rewrite(rule);
        if (!rule.modified()) return;

        ((NoiseGeneratorSettingsAccessor) (Object) settings).setNoiseRouter(rewritten);
        PATCHED.add(rewritten);
    }

    @FunctionalInterface
    public interface Modifier {
        DensityFunction modify(DensityFunction current, HolderLookup.Provider registries);
    }

    record Entry(int priority, Modifier modifier) {
    }
}
