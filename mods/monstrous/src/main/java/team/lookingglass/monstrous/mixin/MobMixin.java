package team.lookingglass.monstrous.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.goal.CreeperFleeGoal;

@Mixin(Mob.class)
public abstract class MobMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void monstrous$creeperFlee(EntityType<? extends Mob> type, Level level, CallbackInfo ci) {
        Mob self = (Mob) (Object) this;
        if ((Object) this instanceof Creeper) return;
        self.getGoalSelector().addGoal(1, new CreeperFleeGoal(self));
    }
}

