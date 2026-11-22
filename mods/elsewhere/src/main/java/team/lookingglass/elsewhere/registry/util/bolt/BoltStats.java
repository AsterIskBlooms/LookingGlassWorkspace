package team.lookingglass.elsewhere.registry.util.bolt;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record BoltStats(double baseDamage, double gravity, int pierceLevel, boolean magic, Supplier<Item> item) {
    private static final Map<EntityType<?>, BoltStats> BY_TYPE = new HashMap<>();

    public static void register(EntityType<? extends Bolt> type, BoltStats stats) {
        BY_TYPE.put(type, stats);
    }

    public static BoltStats of(EntityType<?> type) {
        BoltStats stats = BY_TYPE.get(type);
        if (stats == null) {
            throw new IllegalStateException("No bolt stats registered for " + type);
        }
        return stats;
    }
}
