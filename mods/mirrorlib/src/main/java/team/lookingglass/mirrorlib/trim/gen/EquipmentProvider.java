package team.lookingglass.mirrorlib.trim.gen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import team.lookingglass.mirrorlib.trim.TrimKit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/** Equipment client info for OWNED armor only. Writes trim_overrides for materials flagged darkerFor this asset. */
@Environment(EnvType.CLIENT)
public class EquipmentProvider implements DataProvider {
    private final PackOutput.PathProvider paths;
    private final TrimKit kit;

    public EquipmentProvider(FabricPackOutput output, TrimKit kit) {
        this.paths = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
        this.kit = kit;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> out = new HashMap<>();
        for (TrimKit.OwnedArmor armor : kit.ownedArmors()) {
            EquipmentClientInfo.Builder b = EquipmentClientInfo.builder().addHumanoidLayers(armor.layer());
            for (TrimKit.Trim t : kit.trims()) {
                if (t.darkerFor().contains(armor.asset())) b.replaceTrimPalette(t.key(), t.palette(true));
            }
            out.put(armor.asset(), b.build());
        }
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, paths::json, out);
    }

    @Override
    public String getName() { return "MirrorLib Equipment Assets"; }
}