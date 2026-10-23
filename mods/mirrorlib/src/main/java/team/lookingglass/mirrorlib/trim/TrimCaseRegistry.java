package team.lookingglass.mirrorlib.trim;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.*;

/** Merges every mod's trim_cases manifest into one map keyed by target item id. */
public final class TrimCaseRegistry {
    public record Case(Identifier material, Identifier model, boolean dyed) {}

    private static volatile Map<Identifier, List<Case>> CASES = Map.of();

    public static void load(ResourceManager rm) {
        Map<Identifier, Map<Identifier, Case>> merged = new HashMap<>();
        for (var e : rm.listResources("mirrorlib/trim_cases", id -> id.getPath().endsWith(".json")).entrySet()) {
            try (var reader = e.getValue().openAsReader()) {
                JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
                for (var item : root.getAsJsonObject("items").entrySet()) {
                    Identifier itemId = Identifier.parse(item.getKey());
                    for (JsonElement el : item.getValue().getAsJsonArray()) {
                        JsonObject o = el.getAsJsonObject();
                        Identifier mat = Identifier.parse(o.get("material").getAsString());
                        Identifier model = Identifier.parse(o.get("model").getAsString());
                        boolean dyed = o.has("dyed") && o.get("dyed").getAsBoolean();
                        // keyed by material: first writer wins, deterministic per pack order
                        merged.computeIfAbsent(itemId, k -> new LinkedHashMap<>())
                                .putIfAbsent(mat, new Case(mat, model, dyed));
                    }
                }
            } catch (Exception ex) {
                // TODO: log with your logger and skip this file
            }
        }
        Map<Identifier, List<Case>> out = new HashMap<>();
        merged.forEach((k, v) -> out.put(k, List.copyOf(v.values())));
        CASES = out;
    }

    public static List<Case> casesFor(Identifier item) {
        return CASES.getOrDefault(item, List.of());
    }

    public static boolean hasCases(Identifier item) {
        return !casesFor(item).isEmpty();
    }

    private TrimCaseRegistry() {}
}