package team.lookingglass.monstrous.mixin.enderman;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.render.EndermanSlamAnimation;

@Mixin(CarriedBlockLayer.class)
public abstract class CarriedBlockLayerMixin {
    @Unique private static final float SHOULDER_Y = -0.75F;
    @Inject(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/EndermanRenderState;FF)V", at = @At("HEAD"))
    private void monstrous$raiseBlock(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, EndermanRenderState state, float yRot, float xRot, CallbackInfo ci) {
        float progress = EndermanSlamAnimation.getProgress(state);
        if (progress < 0.0F) return;
        float delta = EndermanSlamAnimation.armAngle(progress, EndermanSlamAnimation.restAngle(state)) + 0.5F;
        poseStack.pushPose();
        poseStack.rotateAround(Axis.XP.rotation(delta), 0.0F, SHOULDER_Y, 0.0F);
    }
    @Inject(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/EndermanRenderState;FF)V", at = @At("RETURN"))
    private void monstrous$restoreBlock(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, EndermanRenderState state, float yRot, float xRot, CallbackInfo ci) {
        if (EndermanSlamAnimation.getProgress(state) < 0.0F) return;
        poseStack.popPose();
    }
}