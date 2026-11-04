package team.lookingglass.mirrorlib.world;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.util.ArrayList;
import java.util.List;

public final class OverworldBiomeEvents {
    public static final Event<WorldOptions.GridModifier> MODIFY_GRID = EventFactory.createArrayBacked(WorldOptions.GridModifier.class, listeners -> view -> {
        for (WorldOptions.GridModifier listener : listeners) listener.add(view);
    });

    public static final Event<WorldOptions.Surface> REWRITE_SURFACE = EventFactory.createArrayBacked(WorldOptions.Surface.class, listeners -> (view, entry) -> {
        List<WorldOptions.SurfaceEntry> current = List.of(entry);
        for (WorldOptions.Surface listener : listeners) {
            List<WorldOptions.SurfaceEntry> next = new ArrayList<>();
            for (WorldOptions.SurfaceEntry e : current) next.addAll(listener.rewrite(view, e));
            current = next;
        }
        return current;
    });

    public static final Event<WorldOptions.Biome> PICK_BEACH = EventFactory.createArrayBacked(WorldOptions.Biome.class, listeners -> (view, t, h, weirdness, current) -> {
        for (WorldOptions.Biome listener : listeners) current = listener.pick(view, t, h, weirdness, current);
        return current;
    });

    public static final Event<WorldOptions.Biome> PICK_BADLANDS = EventFactory.createArrayBacked(WorldOptions.Biome.class, listeners -> (view, t, h, weirdness, current) -> {
        for (WorldOptions.Biome listener : listeners) current = listener.pick(view, t, h, weirdness, current);
        return current;
    });

    public static final Event<WorldOptions.CaveModifier> ADD_UNDERGROUND = EventFactory.createArrayBacked(WorldOptions.CaveModifier.class, listeners -> (view, biomes) -> {
        for (WorldOptions.CaveModifier listener : listeners) listener.add(view, biomes);
    });
}
