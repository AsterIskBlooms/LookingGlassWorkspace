package team.lookingglass.elsewhere.mixin.equipment.shield;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.BlocksAttacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.elsewhere.registry.util.shield.ShieldCooldownAccess;
import team.lookingglass.elsewhere.registry.util.shield.ShieldProfiles;

@Mixin(LivingEntity.class)
public abstract class ShieldCooldownMixin implements ShieldCooldownAccess {
    @Unique private int elsewhere$shieldCooldownTicks;
    @Unique private boolean elsewhere$shieldDisabled;

    @Override
    public int elsewhere$getShieldCooldownTicks() {
        return elsewhere$shieldCooldownTicks;
    }

    @Override
    public boolean elsewhere$isShieldDisabled() {
        return elsewhere$shieldDisabled;
    }

    @Inject(method = "applyItemBlocking", at = @At("HEAD"), cancellable = true)
    private void elsewhere$applyShieldBlocking(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Float> cir) {
        if (damage <= 0.0F) { return; }

        LivingEntity self = (LivingEntity)(Object)this;
        ItemStack blockingWith = self.getItemBlockingWith();
        if (blockingWith == null || !(blockingWith.getItem() instanceof ShieldItem)) { return; }
        BlocksAttacks blocksAttacks = blockingWith.get(DataComponents.BLOCKS_ATTACKS);
        if (blocksAttacks == null) { return; }

        if (blocksAttacks.bypassedBy().map(t -> t.contains(source.typeHolder())).orElse(false)) {
            return;
        }

        Entity directEntity = source.getDirectEntity();
        if (directEntity instanceof AbstractArrow arrow && arrow.getPierceLevel() > 0) {
            cir.setReturnValue(0.0F);
            return;
        }

        float threshold = ShieldProfiles.blockThreshold(blockingWith);
        float damageBlocked = Math.min(damage, threshold);
        blocksAttacks.hurtBlockingItem(self.level(), blockingWith, self, self.getUsedItemHand(), damageBlocked);

        if (damageBlocked > 0.0F && !source.is(DamageTypeTags.IS_PROJECTILE)) {
            if (directEntity instanceof LivingEntity livingEntity) {
                self.blockUsingItem(level, livingEntity, source, damage, true);
            }
        }

        boolean disabled = damage >= threshold;
        elsewhere$shieldDisabled = disabled;
        int cooldown = disabled ? ShieldProfiles.disableCooldownTicks(blockingWith) : ShieldProfiles.releaseCooldownTicks(blockingWith);

        if (self instanceof Player player) {
            player.getCooldowns().addCooldown(blockingWith, cooldown);
        } else {
            elsewhere$shieldCooldownTicks = cooldown;
        }
        self.stopUsingItem();
        cir.setReturnValue(damageBlocked);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void elsewhere$tickShieldCooldown(CallbackInfo ci) {
        if (elsewhere$shieldCooldownTicks > 0) {
            elsewhere$shieldCooldownTicks--;
            if (elsewhere$shieldCooldownTicks == 0) {
                elsewhere$shieldDisabled = false;
            }
        }
    }
}