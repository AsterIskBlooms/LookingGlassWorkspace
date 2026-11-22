package team.lookingglass.elsewhere.mixin.equipment.arrow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import team.lookingglass.elsewhere.registry.util.bolt.Bolt;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @WrapOperation(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;arrow(Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource elsewhere$boltDamageSource(DamageSources sources, AbstractArrow arrow, Entity owner, Operation<DamageSource> original) {
        if (arrow instanceof Bolt bolt) {
            return bolt.createDamageSource(sources, owner);
        }
        return original.call(sources, arrow, owner);
    }
}