package team.lookingglass.monstrous.mixin.spider;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.render.Crawler;

@Mixin(LivingEntityRenderer.class)
public abstract class CrawlerRendererMixin {
    @ModifyVariable(method = "setupRotations", at = @At("HEAD"), argsOnly = true, name = "bodyRot")
    private float monstrous$surfaceBodyRot(float bodyRot, LivingEntityRenderState state) {
        if (!(state instanceof Crawler.State cling)) return bodyRot;
        float wall = cling.monstrous$wall();
        float ceiling = cling.monstrous$ceiling();
        if (wall < 0.001F && ceiling < 0.001F) return bodyRot;

        float targetYaw = bodyRot;

        if (wall > 0.001F) {
            float hx = cling.monstrous$headX();
            float hz = cling.monstrous$headZ();
            if (hx * hx + hz * hz > 1.0E-4F) {
                float wallYaw = (float) (Mth.atan2(hz, hx) * Mth.RAD_TO_DEG) - 90.0F;
                targetYaw = Mth.rotLerp(wall, targetYaw, wallYaw);
            }
        }

        if (ceiling > 0.001F) {
            float nx = cling.monstrous$normalX();
            float nz = cling.monstrous$normalZ();
            float axisYaw = (float) (Mth.atan2(-nx, nz) * Mth.RAD_TO_DEG) - 90.0F;
            float mirroredYaw = 2.0F * axisYaw - targetYaw;
            targetYaw = Mth.rotLerp(ceiling, targetYaw, mirroredYaw);
        }
        return targetYaw;
    }

    @Inject(method = "setupRotations", at = @At("HEAD"))
    private void monstrous$surfaceTilt(LivingEntityRenderState state, PoseStack poseStack, float bodyRot, float entityScale, CallbackInfo ci) {
        if (!(state instanceof Crawler.State cling)) return;
        float wall = cling.monstrous$wall();
        float ceiling = cling.monstrous$ceiling();
        if (wall < 0.001F && ceiling < 0.001F) return;

        float nx = cling.monstrous$normalX();
        float nz = cling.monstrous$normalZ();

        float angle = (wall * 0.5F + ceiling) * (float) Math.PI;
        float halfHeight = state.boundingBoxHeight * 0.5F / entityScale;
        float push = (state.boundingBoxWidth - state.boundingBoxHeight) * 0.5F * wall / entityScale;

        poseStack.translate(-nx * push, 0.0F, -nz * push);
        poseStack.translate(0.0F, halfHeight, 0.0F);
        poseStack.rotateAround(new Quaternionf().rotationAxis(angle, nz, 0.0F, -nx), 0.0F, 0.0F, 0.0F);
        poseStack.translate(0.0F, -halfHeight, 0.0F);
    }
}