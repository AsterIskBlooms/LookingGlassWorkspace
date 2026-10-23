package team.lookingglass.mirrorlib.trim.gen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import team.lookingglass.mirrorlib.trim.TrimKit;

import java.util.concurrent.CompletableFuture;

/** Writes {@code assets/<modid>/mirrorlib/trim_cases/trims.json}, mapping each target item to a list of {@code {material, model}} cases. */
public class TrimManifestProvider implements DataProvider {
    private final PackOutput.PathProvider paths;
    private final TrimKit kit;

    public TrimManifestProvider(FabricPackOutput out, TrimKit kit) {
        this.paths = out.createPathProvider(PackOutput.Target.RESOURCE_PACK, "mirrorlib/trim_cases");
        this.kit = kit;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        JsonObject items = new JsonObject();
        for (TrimKit.TrimTarget target : kit.targets()) {
            JsonArray cases = new JsonArray();
            for (TrimKit.Trim t : kit.trims()) {
                JsonObject c = new JsonObject();
                c.addProperty("material", t.key().identifier().toString());
                c.addProperty("model", kit.targetModelId(target, t).toString());
                cases.add(c);
            }
            items.add(target.itemId().toString(), cases);
        }
        JsonObject root = new JsonObject();
        root.add("items", items);
        return DataProvider.saveStable(cache, root,
                paths.json(Identifier.fromNamespaceAndPath(kit.modId(), "trims")));
    }

    @Override
    public String getName() { return "MirrorLib Trim Manifest"; }
}