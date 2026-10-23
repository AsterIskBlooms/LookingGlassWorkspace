package team.lookingglass.elsewhere.mixin.entity;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import team.lookingglass.elsewhere.registry.EItems;

@Mixin(WitherSkeleton.class)
public class WitherSkeletonSwordsMixin {
    /**
     * @author Aster-Isk
     * @reason Stone Swords are too easy lol ,,,
     */
    @Overwrite
    protected void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
        ((Mob) (Object) this).setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }
}
