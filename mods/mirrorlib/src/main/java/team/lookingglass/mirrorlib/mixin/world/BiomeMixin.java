package team.lookingglass.mirrorlib.mixin.world;

import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.mirrorlib.registry.color.BiomeColorRegistry;
import team.lookingglass.mirrorlib.registry.color.GrassColorProvider;

@Mixin(Biome.class)
public abstract class BiomeMixin {
    @Shadow
    private int getBaseGrassColor() { throw new AssertionError(); }

    @Inject(method = "getGrassColor", at = @At("HEAD"), cancellable = true)
    private void mirrorlib$customGrassColor(double x, double z, CallbackInfoReturnable<Integer> cir) {
        GrassColorProvider provider = BiomeColorRegistry.get((Biome) (Object) this);
        if (provider != null) {
            cir.setReturnValue(provider.getColor(x, z, this.getBaseGrassColor()));
        }
    }
}
