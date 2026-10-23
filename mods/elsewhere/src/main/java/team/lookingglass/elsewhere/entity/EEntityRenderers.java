package team.lookingglass.elsewhere.entity;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.core.registries.BuiltInClientRegistries;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.model.*;
import team.lookingglass.elsewhere.entity.renderer.*;

public class EEntityRenderers {
    public static void register() {
        Registry<EntityRendererProvider<?>> registry = BuiltInClientRegistries.ENTITY_RENDERER;

        ModelLayerRegistry.registerModelLayer(WerewolfModel.LAYER_LOCATION, WerewolfModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.WEREWOLF, WerewolfRenderer::new);

        ModelLayerRegistry.registerModelLayer(VanguardModel.LAYER_LOCATION, VanguardModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.VANGUARD, VanguardRenderer::new);

        ModelLayerRegistry.registerModelLayer(SubslimeModel.LAYER_LOCATION, SubslimeModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.SUBSLIME, SubslimeRenderer::new);

        ModelLayerRegistry.registerModelLayer(SweeperModel.LAYER_LOCATION, SweeperModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.SWEEPER, SweeperRenderer::new);

        ModelLayerRegistry.registerModelLayer(MudGolemModel.LAYER_LOCATION, MudGolemModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.MUD_GOLEM, MudGolemRenderer::new);

        ModelLayerRegistry.registerModelLayer(LostSoulModel.LAYER_LOCATION, LostSoulModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.LOST_SOUL, LostSoulRenderer::new);

        ModelLayerRegistry.registerModelLayer(TrollModel.LAYER_LOCATION, TrollModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.TROLL, TrollRenderer::new);

        ModelLayerRegistry.registerModelLayer(GravekeeperModel.LAYER_LOCATION, GravekeeperModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.GRAVEKEEPER, GravekeeperRenderer::new);

        ModelLayerRegistry.registerModelLayer(WhistlerModel.LAYER_LOCATION, WhistlerModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.WHISTLER, WhistlerRenderer::new);

        ModelLayerRegistry.registerModelLayer(PerchModel.LAYER_LOCATION, PerchModel::createBodyLayer);
        EntityRenderers.register(registry, EEntities.PERCH, PerchRenderer::new);

        ModelLayerLocation AZALEA_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "boat/azalea"), "main");
        ModelLayerLocation AZALEA_CHEST_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "chest_boat/azalea"), "main");
        ModelLayerRegistry.registerModelLayer(AZALEA_BOAT_LAYER, BoatModel::createBoatModel);
        EntityRenderers.register(registry, EEntities.AZALEA_BOAT, context -> new BoatRenderer(context, AZALEA_BOAT_LAYER));
        ModelLayerRegistry.registerModelLayer(AZALEA_CHEST_BOAT_LAYER, BoatModel::createChestBoatModel);
        EntityRenderers.register(registry, EEntities.AZALEA_CHEST_BOAT, context -> new BoatRenderer(context, AZALEA_CHEST_BOAT_LAYER));

        ModelLayerLocation CEDAR_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "boat/cedar"), "main");
        ModelLayerLocation CEDAR_CHEST_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "chest_boat/cedar"), "main");
        ModelLayerRegistry.registerModelLayer(CEDAR_BOAT_LAYER, BoatModel::createBoatModel);
        EntityRenderers.register(registry, EEntities.CEDAR_BOAT, context -> new BoatRenderer(context, CEDAR_BOAT_LAYER));
        ModelLayerRegistry.registerModelLayer(CEDAR_CHEST_BOAT_LAYER, BoatModel::createChestBoatModel);
        EntityRenderers.register(registry, EEntities.CEDAR_CHEST_BOAT, context -> new BoatRenderer(context, CEDAR_CHEST_BOAT_LAYER));

        ModelLayerLocation MAHOGANY_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "boat/mahogany"), "main");
        ModelLayerLocation MAHOGANY_CHEST_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "chest_boat/mahogany"), "main");
        ModelLayerRegistry.registerModelLayer(MAHOGANY_BOAT_LAYER, BoatModel::createBoatModel);
        EntityRenderers.register(registry, EEntities.MAHOGANY_BOAT, context -> new BoatRenderer(context, MAHOGANY_BOAT_LAYER));
        ModelLayerRegistry.registerModelLayer(MAHOGANY_CHEST_BOAT_LAYER, BoatModel::createChestBoatModel);
        EntityRenderers.register(registry, EEntities.MAHOGANY_CHEST_BOAT, context -> new BoatRenderer(context, MAHOGANY_CHEST_BOAT_LAYER));
    }
}