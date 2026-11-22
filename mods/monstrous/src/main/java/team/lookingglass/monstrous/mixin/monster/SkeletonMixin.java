package team.lookingglass.monstrous.mixin.monster;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.monstrous.goal.RepositionGoal;

@SuppressWarnings("ConstantConditions")
@Mixin(AbstractSkeleton.class)
public abstract class SkeletonMixin {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void monstrous$meleePlug(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        Mob self = (Mob) (Object) this;
        if (!((Object) this instanceof WitherSkeleton)) {
            if (random.nextFloat() < 0.15F) {
                int roll = random.nextInt(4);
                Item weapon;
                if (roll == 0) {
                    weapon = Items.COPPER_AXE;
                } else {
                    weapon = Items.COPPER_SWORD;
                }
                self.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(weapon));
            }
        }
    }
    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void monstrous$healthNerf(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (!((Object) this instanceof WitherSkeleton)) {
            ((Mob) (Object) this).getAttribute(Attributes.MAX_HEALTH).setBaseValue(16);
        }
    }
    @Inject(method = "createAttributes", at = @At("RETURN"))
    private static void monstrous$followRange(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(Attributes.FOLLOW_RANGE, 20.0F);
    }
    @ModifyConstant(method = "<init>", constant = @Constant(floatValue = 15.0F))
    private float monstrous$bowRange(float original) {
        return 18.0F;
    }
    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void monstrous$reposition(CallbackInfo ci) {
        AbstractSkeleton self = (AbstractSkeleton) (Object) this;
        self.getGoalSelector().addGoal(1, new RepositionGoal(self, 1.4F, 60, 12.0F));
    }
}