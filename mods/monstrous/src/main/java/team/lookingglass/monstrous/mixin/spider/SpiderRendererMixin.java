package team.lookingglass.monstrous.mixin.spider;

import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.render.Crawler;
import team.lookingglass.monstrous.render.WebAiming;

@Mixin(SpiderRenderer.class)
public abstract class SpiderRendererMixin {
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/monster/spider/Spider;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("TAIL"))
    private void monstrous$extractCling(Spider entity, LivingEntityRenderState state, float partialTicks, CallbackInfo ci) {
        if (state instanceof Crawler.State cling && entity instanceof Crawler clinger) {
            Vec3 n = clinger.monstrous$getWallNormal();
            Vec3 h = clinger.monstrous$getWallHeading();
            float wall = clinger.monstrous$getWall(partialTicks);
            float ceiling = clinger.monstrous$getCeiling(partialTicks);

            cling.monstrous$setCling(wall, ceiling, (float) n.x, (float) n.z, (float) h.x, (float) h.z);

            if (entity instanceof WebAiming aiming) {
                cling.monstrous$setAim(aiming.monstrous$getAim(partialTicks));
            }

            state.walkAnimationPos = clinger.monstrous$getClimbAnimPos(partialTicks);
            state.walkAnimationSpeed = clinger.monstrous$getClimbAnimSpeed(partialTicks);
        }
    }
}