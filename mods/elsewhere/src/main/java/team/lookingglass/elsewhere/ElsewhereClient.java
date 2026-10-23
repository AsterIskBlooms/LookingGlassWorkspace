package team.lookingglass.elsewhere;

import net.fabricmc.api.ClientModInitializer;
import team.lookingglass.elsewhere.entity.EEntityRenderers;
import team.lookingglass.elsewhere.registry.render.ERenderer;
import team.lookingglass.elsewhere.registry.render.EModelLayers;

public class ElsewhereClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EModelLayers.register();
        ERenderer.register();
        EEntityRenderers.register();
    }
}
