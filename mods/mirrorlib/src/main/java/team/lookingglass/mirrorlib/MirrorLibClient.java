package team.lookingglass.mirrorlib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.core.registries.Registries;
import team.lookingglass.mirrorlib.registry.color.BiomeColorRegistry;

public class MirrorLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) ->
                BiomeColorRegistry.bind(handler.registryAccess().lookupOrThrow(Registries.BIOME)));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> BiomeColorRegistry.clear());
    }
}