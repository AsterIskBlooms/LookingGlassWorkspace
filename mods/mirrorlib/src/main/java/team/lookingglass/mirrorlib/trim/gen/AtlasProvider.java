package team.lookingglass.mirrorlib.trim.gen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import team.lookingglass.mirrorlib.trim.TrimKit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/** Writes assets/minecraft/atlases/items.json containing ONLY this mod's permutations (atlases merge across packs). */
public class AtlasProvider implements DataProvider {
    private final PackOutput.PathProvider paths;
    private final List<TrimKit> kits;

    public AtlasProvider(FabricPackOutput output, TrimKit... kits) {
        this.paths = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "atlases");
        this.kits = List.of(kits);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        JsonObject permutations = new JsonObject();
        for (TrimKit kit : kits) {
            for (TrimKit.Trim t : kit.trims()) {
                permutations.addProperty(t.permutation(false), t.palette(false).toString());
                if (t.hasDarker()) permutations.addProperty(t.permutation(true), t.palette(true).toString());
            }
        }

        JsonArray textures = new JsonArray();
        for (Identifier id : List.of(ItemModelGenerators.TRIM_PREFIX_HELMET, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_BOOTS)) {
            textures.add(id.toString());
        }

        JsonObject source = new JsonObject();
        source.addProperty("type", "minecraft:paletted_permutations");
        source.addProperty("palette_key", "minecraft:trim_base");
        source.add("permutations", permutations);
        source.add("textures", textures);

        JsonArray sources = new JsonArray();
        sources.add(source);
        JsonObject root = new JsonObject();
        root.add("sources", sources);

        return DataProvider.saveStable(cache, root, paths.json(Identifier.withDefaultNamespace("items")));
    }

    @Override
    public String getName() { return "MirrorLib Atlases"; }
}