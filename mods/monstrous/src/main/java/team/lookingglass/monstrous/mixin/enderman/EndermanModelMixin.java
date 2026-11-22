package team.lookingglass.monstrous.mixin.enderman;

import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.render.EndermanSlamAnimation;

@Mixin(EndermanModel.class)
public abstract class EndermanModelMixin {
    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/EndermanRenderState;)V", at = @At("TAIL"))
    private void monstrous$slamAnimation(EndermanRenderState state, CallbackInfo ci) {
        float progress = EndermanSlamAnimation.getProgress(state);
        if (progress < 0.0F) return;
        EndermanModel<?> model = (EndermanModel<?>)(Object)this;
        model.body.yRot = 0.0F;
        if (state.carriedBlock.isEmpty()) {
            model.rightArm.xRot = EndermanSlamAnimation.sweepPitch(progress);
            model.rightArm.yRot = EndermanSlamAnimation.sweepYaw(progress);
            model.rightArm.zRot = 0.0F;
            model.leftArm.yRot = 0.0F;
            return;
        }
        float armRotation = EndermanSlamAnimation.armAngle(progress, EndermanSlamAnimation.restAngle(state));
        model.rightArm.xRot = armRotation;
        model.leftArm.xRot = armRotation;
        model.rightArm.yRot = 0.0F;
        model.leftArm.yRot = 0.0F;
        model.rightArm.zRot = 0.10F;
        model.leftArm.zRot = -0.10F;
    }
}