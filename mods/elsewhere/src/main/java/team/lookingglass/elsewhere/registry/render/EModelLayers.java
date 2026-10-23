package team.lookingglass.elsewhere.registry.render;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.render.shields.QuickShieldModel;
import team.lookingglass.elsewhere.registry.render.shields.ReinforcedShieldModel;

public final class EModelLayers {
    public static final ModelLayerLocation QUICK_SHIELD =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "quick_shield"), "main");
    public static final ModelLayerLocation REINFORCED_SHIELD =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "reinforced_shield"), "main");

    public static void register() {
        ModelLayerRegistry.registerModelLayer(QUICK_SHIELD, QuickShieldModel::createLayer);
        ModelLayerRegistry.registerModelLayer(REINFORCED_SHIELD, ReinforcedShieldModel::createLayer);
    }
}