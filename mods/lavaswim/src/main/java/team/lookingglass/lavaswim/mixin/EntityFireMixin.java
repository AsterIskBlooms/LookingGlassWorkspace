package team.lookingglass.lavaswim.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class EntityFireMixin {
    @ModifyReturnValue(method = "displayFireAnimation", at = @At("RETURN"))
    private boolean lavaswim$hideFireWhenResistant(boolean original) {
        if (original) {
            return !lavaswim$isFireResistant((Entity) (Object) this);
        }
        return false;
    }

    @Unique
    private static boolean lavaswim$isFireResistant(Entity entity) {
        return entity instanceof LivingEntity living && living.hasEffect(MobEffects.FIRE_RESISTANCE);
    }
}