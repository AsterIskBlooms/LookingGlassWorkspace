package team.lookingglass.elsewhere.mixin.entity;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Zombie.class)
public abstract class ZombieMixins {
    @Inject(method = "setBaby", at = @At("TAIL"))
    private void elsewhere$nerfBabyHealth(boolean baby, CallbackInfo ci) {
        Zombie self = (Zombie) (Object) this;
        AttributeInstance maxHealth = self.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) return;

        maxHealth.setBaseValue(baby ? 12.0D : 20.0D);
        if (self.getHealth() > maxHealth.getValue()) {
            self.setHealth((float) maxHealth.getValue());
        }
    }
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void elsewhere$meleePlug(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        Mob self = (Mob) (Object) this;
        // Wipe existing slots
        self.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);

        if (random.nextFloat() < 0.15F) {
            int roll = random.nextInt(10);
            Item weapon;
            if (roll == 0) {
                weapon = Items.COPPER_SPEAR;
            } else if (roll <= 5) {
                weapon = Items.COPPER_PICKAXE;
            } else {
                weapon = Items.COPPER_SHOVEL;
            }
            self.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(weapon));
        }
    }
}
