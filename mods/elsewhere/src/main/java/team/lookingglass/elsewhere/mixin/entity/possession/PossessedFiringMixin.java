package team.lookingglass.elsewhere.mixin.entity.possession;

import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.entity.util.PossessionAccess;

@Mixin(RangedBowAttackGoal.class)
public abstract class PossessedFiringMixin {
    @Shadow private int attackTime;
    @Shadow private int attackIntervalMin;
    @Final @Shadow private Monster mob;

    @Inject(method = "tick", at = @At("TAIL"))
    private void elsewhere$possessedFireRate(final CallbackInfo ci) {
        if (((PossessionAccess) this.mob).elsewhere$isPossessed() && this.attackTime == this.attackIntervalMin) {
            this.attackTime = (int)(this.attackIntervalMin / 2.0F);
        }
    }
}