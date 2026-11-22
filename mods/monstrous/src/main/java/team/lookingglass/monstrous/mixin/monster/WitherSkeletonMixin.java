package team.lookingglass.monstrous.mixin.monster;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherSkeleton.class)
public class WitherSkeletonMixin {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void monstrous$replaceSword(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        Mob self = (Mob) (Object) this;
        self.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        self.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }
}
