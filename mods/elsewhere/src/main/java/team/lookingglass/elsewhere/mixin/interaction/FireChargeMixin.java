package team.lookingglass.elsewhere.mixin.interaction;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(FireChargeItem.class)
public abstract class FireChargeMixin {
    @Unique
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        RandomSource random = level.getRandom();
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.5F,
                (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);

        if (!level.isClientSide()) {
            Vec3 look = player.getLookAngle();
            SmallFireball fireball = new SmallFireball(level, player.getX(), player.getEyeY() - 0.1, player.getZ(), look.normalize());
            fireball.setItem(stack);
            fireball.setOwner(player);
            level.addFreshEntity(fireball);
        }

        player.getCooldowns().addCooldown(((FireChargeItem) (Object) this).getDefaultInstance(), 10);
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}