package team.lookingglass.mirrorlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.resources.model.ClientItemInfoLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import team.lookingglass.mirrorlib.trim.TrimCaseRegistry;
import team.lookingglass.mirrorlib.trim.TrimItemPatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(ClientItemInfoLoader.class)
public class ClientItemInfoLoaderMixin {
    @ModifyReturnValue(method = "scheduleLoad", at = @At("RETURN"))
    private static CompletableFuture<ClientItemInfoLoader.LoadedClientInfos> mirrorlib$patchTrims(
            CompletableFuture<ClientItemInfoLoader.LoadedClientInfos> original,
            ResourceManager manager, Executor executor) {
        return original.thenApply(loaded -> {
            TrimCaseRegistry.load(manager);
            Map<Identifier, ClientItem> patched = new HashMap<>(loaded.contents());
            patched.replaceAll((id, item) -> TrimCaseRegistry.hasCases(id)
                    ? new ClientItem(
                    TrimItemPatcher.patch(item.model(), TrimCaseRegistry.casesFor(id)),
                    item.properties())
                    : item);
            return new ClientItemInfoLoader.LoadedClientInfos(patched);
        });
    }
}