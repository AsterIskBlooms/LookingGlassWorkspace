package team.lookingglass.monstrous.mixin.spider;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.monstrous.render.Crawler;

@Mixin(SpiderModel.class)
public abstract class SpiderModelMixin {
    @Unique private static final float MONSTROUS$LEAN = 0.3F;
    @Unique private static final float MONSTROUS$ABDOMEN_EXTRA = 0.45F;

    @Shadow @Final private ModelPart head;
    @Unique private ModelPart monstrous$body0;
    @Unique private ModelPart monstrous$body1;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void monstrous$grabBody(ModelPart root, CallbackInfo ci) {
        this.monstrous$body0 = root.getChild("body0");
        this.monstrous$body1 = root.getChild("body1");
    }

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)V", at = @At("TAIL"))
    private void monstrous$webAim(LivingEntityRenderState state, CallbackInfo ci) {
        if (!(state instanceof Crawler.State cling)) return;

        float aim = cling.monstrous$aim();
        if (aim <= 0.001F) return;

        float angle = aim * MONSTROUS$LEAN;
        float sin = Mth.sin(angle);
        float cos = Mth.cos(angle);

        this.monstrous$body0.xRot += angle;

        this.head.xRot += angle;
        this.head.y += 3.0F * sin;
        this.head.z += 3.0F * (1.0F - cos);

        this.monstrous$body1.xRot += angle + aim * MONSTROUS$ABDOMEN_EXTRA;
        this.monstrous$body1.y -= 9.0F * sin;
        this.monstrous$body1.z -= 9.0F * (1.0F - cos);
    }
}