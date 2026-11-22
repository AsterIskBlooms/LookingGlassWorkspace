package team.lookingglass.monstrous.mixin.monster;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.goal.PickaxeLeapGoal;

@Mixin(Zombie.class)
public abstract class ZombieMixin {
    @Inject(method = "setBaby", at = @At("TAIL"))
    private void monstrous$nerfBabyHealth(boolean baby, CallbackInfo ci) {
        Zombie self = (Zombie) (Object) this;
        AttributeInstance maxHealth = self.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) return;
        maxHealth.setBaseValue(baby ? 12.0D : 20.0D);
        if (self.getHealth() > maxHealth.getValue()) {
            self.setHealth((float) maxHealth.getValue());
        }
    }
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void monstrous$meleePlug(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        Mob self = (Mob) (Object) this;
        self.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);

        if (random.nextFloat() < 0.35F) {
            int roll = random.nextInt(10);
            Item weapon;
            if (roll == 0) weapon = Items.COPPER_SPEAR;
            else if (roll <= 6) weapon = Items.COPPER_PICKAXE;
            else weapon = Items.COPPER_SHOVEL;
            self.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(weapon));
        }
        if (random.nextFloat() < 0.075F) {
            self.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
            self.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
            self.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
            self.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
        } else if (random.nextFloat() < 0.65F) {
            Item helmet = 0 == random.nextInt(20) ? Items.IRON_HELMET : Items.COPPER_HELMET;
            Item chestplate = 0 == random.nextInt(40) ? Items.IRON_CHESTPLATE : Items.COPPER_CHESTPLATE;
            Item leggings = 0 == random.nextInt(30) ? Items.IRON_LEGGINGS : Items.COPPER_LEGGINGS;
            Item boots = 0 == random.nextInt(20) ? Items.IRON_BOOTS : Items.COPPER_BOOTS;
            if (random.nextFloat() < 0.25F) self.setItemSlot(EquipmentSlot.HEAD, new ItemStack(helmet));
            if (random.nextFloat() < 0.15F) self.setItemSlot(EquipmentSlot.CHEST, new ItemStack(chestplate));
            if (random.nextFloat() < 0.32F) self.setItemSlot(EquipmentSlot.LEGS, new ItemStack(leggings));
            if (random.nextFloat() < 0.55F) self.setItemSlot(EquipmentSlot.FEET, new ItemStack(boots));
        }
    }
    @Inject(method = "addBehaviourGoals", at = @At("TAIL"))
    private void monstrous$pickaxeLeap(CallbackInfo ci) {
        Zombie self = (Zombie) (Object) this;
//        self.getGoalSelector().addGoal(2, new PickaxeLeapGoal(self, 0.5F, 2.5F, 10, 100));
    }
}