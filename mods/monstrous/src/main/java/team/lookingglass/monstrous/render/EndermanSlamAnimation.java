package team.lookingglass.monstrous.render;

import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.util.Mth;

public final class EndermanSlamAnimation {
    private EndermanSlamAnimation() {}
    public static float getProgress(EndermanRenderState state) {
        if (state.currentSwing == null || state.currentSwing.durationTicks() < 11) {
            return -1.0F;
        }
        return Mth.clampedLerp(state.swingAnimation, 0.0F, 1.0F);
    }
    private static float easeOut(float t) {
        return 1.0F - (1.0F - t) * (1.0F - t);
    }
    private static float smooth(float t) {
        return t * t * (3.0F - 2.0F * t);
    }
    public static float restAngle(EndermanRenderState state) {
        return state.carriedBlock.isEmpty() ? 0.0F : -0.5F;
    }
    public static float sweepPitch(float progress) {
        if (progress < 0.45F) {
            return Mth.lerp(easeOut(progress / 0.45F), 0.0F, -1.4F);
        } else if (progress < 0.73F) {
            return -1.4F;
        }
        return Mth.lerp(smooth((progress - 0.73F) / 0.27F), -1.4F, 0.0F);
    }
    public static float sweepYaw(float progress) {
        if (progress < 0.45F) {
            return Mth.lerp(easeOut(progress / 0.45F), 0.0F, 1.0F);
        } else if (progress < 0.60F) {
            return 1.0F;
        } else if (progress < 0.73F) {
            float t = (progress - 0.60F) / 0.13F;
            return Mth.lerp(t * t, 1.0F, -1.0F);
        }
        return Mth.lerp(smooth((progress - 0.73F) / 0.27F), -1.0F, 0.0F);
    }
    public static float armAngle(float progress, float rest) {
        if (progress < 0.50F) {
            float t = progress / 0.50F;
            t = 1.0F - (1.0F - t) * (1.0F - t);
            return Mth.lerp(t, rest, -2.7F);
        } else if (progress < 0.62F) {
            return -2.7F;
        } else if (progress < 0.72F) {
            float t = (progress - 0.62F) / 0.10F;
            t = t * t * t;
            return Mth.lerp(t, -2.7F, -0.9F);
        } else {
            float t = (progress - 0.72F) / 0.28F;
            t = t * t * (3.0F - 2.0F * t);
            return Mth.lerp(t, -0.9F, rest);
        }
    }
}