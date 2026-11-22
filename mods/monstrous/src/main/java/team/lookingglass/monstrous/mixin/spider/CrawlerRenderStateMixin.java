package team.lookingglass.monstrous.mixin.spider;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import team.lookingglass.monstrous.render.Crawler;

@Mixin(LivingEntityRenderState.class)
public abstract class CrawlerRenderStateMixin implements Crawler.State {
    @Unique private float monstrous$wall;
    @Unique private float monstrous$ceiling;
    @Unique private float monstrous$nx;
    @Unique private float monstrous$nz;
    @Unique private float monstrous$hx;
    @Unique private float monstrous$hz = 1.0F;
    @Unique private float monstrous$aim;

    @Override
    public void monstrous$setCling(float wall, float ceiling, float normalX, float normalZ, float headX, float headZ) {
        this.monstrous$wall = wall;
        this.monstrous$ceiling = ceiling;
        this.monstrous$nx = normalX;
        this.monstrous$nz = normalZ;
        this.monstrous$hx = headX;
        this.monstrous$hz = headZ;
    }

    @Override public float monstrous$wall() { return this.monstrous$wall; }
    @Override public float monstrous$ceiling() { return this.monstrous$ceiling; }
    @Override public float monstrous$normalX() { return this.monstrous$nx; }
    @Override public float monstrous$normalZ() { return this.monstrous$nz; }
    @Override public float monstrous$headX() { return this.monstrous$hx; }
    @Override public float monstrous$headZ() { return this.monstrous$hz; }
    @Override public void monstrous$setAim(float aim) { this.monstrous$aim = aim; }
    @Override public float monstrous$aim() { return this.monstrous$aim; }
}