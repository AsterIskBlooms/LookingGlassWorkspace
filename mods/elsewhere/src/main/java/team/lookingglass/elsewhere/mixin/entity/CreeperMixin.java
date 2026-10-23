package team.lookingglass.elsewhere.mixin.entity;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level.ExplosionInteraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Creeper.class)
public abstract class CreeperMixin {
    @ModifyArg(method = "explodeCreeper", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Level$ExplosionInteraction;)V"), index = 5)
    private ExplosionInteraction elsewhere$noBlockDamage(final ExplosionInteraction interaction) {
        return ExplosionInteraction.NONE;
    }
}