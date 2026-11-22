package team.lookingglass.elsewhere.registry.util.bolt;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Bolt extends AbstractArrow {
    public Bolt(EntityType<? extends Bolt> type, Level level) {
        super(type, level);
    }

    public Bolt(EntityType<? extends Bolt> type, Level level, double x, double y, double z,
                ItemStack pickupItem, @Nullable ItemStack firedFromWeapon) {
        super(type, x, y, z, level, pickupItem, firedFromWeapon);
        BoltStats stats = this.stats();
        this.setBaseDamage(stats.baseDamage());
        this.setPierceLevel((byte) (this.getPierceLevel() + stats.pierceLevel()));
    }

    private BoltStats stats() {
        return BoltStats.of(this.getType());
    }

    @Override
    protected double getDefaultGravity() {
        return this.stats().gravity();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(this.stats().item().get());
    }

    public DamageSource createDamageSource(DamageSources sources, @Nullable Entity owner) {
        Entity cause = owner != null ? owner : this;
        return this.stats().magic() ? sources.indirectMagic(this, cause) : sources.arrow(this, cause);
    }
}
