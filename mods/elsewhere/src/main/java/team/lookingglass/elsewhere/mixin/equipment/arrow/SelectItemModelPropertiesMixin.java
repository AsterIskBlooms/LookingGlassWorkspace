package team.lookingglass.elsewhere.mixin.equipment.arrow;

import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.util.bolt.LoadedBolt;

@Mixin(SelectItemModelProperties.class)
public class SelectItemModelPropertiesMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void elsewhere$register(WritableRegistry<SelectItemModelProperty.Type<?, ?>> registry, CallbackInfo ci) {
        Registry.register(registry, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "loaded_bolt"), LoadedBolt.TYPE);
    }
}
