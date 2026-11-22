package team.lookingglass.monstrous.render;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public interface Crawler {
    void monstrous$tickSurface();
    float monstrous$getWall(float partialTicks);
    float monstrous$getCeiling(float partialTicks);
    Vec3 monstrous$getWallNormal();
    void monstrous$setSurface(@Nullable Direction face);
    boolean monstrous$isDriven();
    Vec3 monstrous$getWallHeading();
    float monstrous$getClimbAnimPos(float partialTicks);
    float monstrous$getClimbAnimSpeed(float partialTicks);

    interface State {
        void monstrous$setCling(float wall, float ceiling, float normalX, float normalZ, float headX, float headZ);
        float monstrous$wall();
        float monstrous$ceiling();
        float monstrous$normalX();
        float monstrous$normalZ();
        float monstrous$headX();
        float monstrous$headZ();
        void monstrous$setAim(float aim);
        float monstrous$aim();
    }
}