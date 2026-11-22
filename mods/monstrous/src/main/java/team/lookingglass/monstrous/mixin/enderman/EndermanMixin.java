package team.lookingglass.monstrous.mixin.enderman;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Enderman;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.goal.EndermanCombatGoal;

@SuppressWarnings("ConstantConditions")
@Mixin(Enderman.class)
public class EndermanMixin {
    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void monstrous$combatGoal(CallbackInfo ci) {
        Enderman self = (Enderman) (Object) this;
        self.getGoalSelector().removeGoal(new MeleeAttackGoal(self, 1.0, false));
        self.getGoalSelector().addGoal(2, new EndermanCombatGoal(self));
    }
}