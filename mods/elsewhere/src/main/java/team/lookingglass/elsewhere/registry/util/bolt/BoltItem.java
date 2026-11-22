package team.lookingglass.elsewhere.registry.util.bolt;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class BoltItem extends ArrowItem {
    private final EntityType<? extends Bolt> type;

    public BoltItem(Properties properties, EntityType<? extends Bolt> type) {
        super(properties);
        this.type = type;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity owner, @Nullable ItemStack firedFromWeapon) {
        Bolt bolt = new Bolt(type, level, owner.getX(), owner.getEyeY() - 0.1F, owner.getZ(), itemStack.copyWithCount(1), firedFromWeapon);
        bolt.setOwner(owner);
        return bolt;
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        Bolt bolt = new Bolt(type, level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), null);
        bolt.pickup = AbstractArrow.Pickup.ALLOWED;
        return bolt;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag flag) {
        BoltStats stats = BoltStats.of(type);
        BoltTooltips.addDamageLines(textConsumer, stats.baseDamage(), stats.magic());
    }
}