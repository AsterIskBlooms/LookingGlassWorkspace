package team.lookingglass.monstrous.mixin.spider;

import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.monstrous.goal.spider.SurfaceChaseGoal;
import team.lookingglass.monstrous.goal.spider.SurfaceNavigation;
import team.lookingglass.monstrous.goal.spider.WebShotGoal;
import team.lookingglass.monstrous.render.Crawler;
import team.lookingglass.monstrous.render.WebAiming;

@Mixin(net.minecraft.world.entity.monster.spider.Spider.class)
public abstract class SpiderMixin implements Crawler, WebAiming {
    @Shadow @Final private static EntityDataAccessor<Byte> DATA_FLAGS_ID;
    @Unique private int monstrous$drivenTicks;
    @Unique private int monstrous$prevCode;
    @Unique private float monstrous$wall;
    @Unique private float monstrous$prevWall;
    @Unique private float monstrous$ceiling;
    @Unique private float monstrous$prevCeiling;
    @Unique private Vec3 monstrous$wallNormal = new Vec3(0.0D, 0.0D, 1.0D);
    @Unique private double monstrous$headX;
    @Unique private double monstrous$headZ = 1.0D;
    @Unique private float monstrous$animPos;
    @Unique private float monstrous$animSpeed;
    @Unique private float monstrous$animSpeedO;
    @Unique private double monstrous$lastX;
    @Unique private double monstrous$lastY;
    @Unique private double monstrous$lastZ;
    @Unique private boolean monstrous$hasLast;
    @Unique private float monstrous$aim;
    @Unique private float monstrous$prevAim;

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void monstrous$goals(CallbackInfo ci) {
        net.minecraft.world.entity.monster.spider.Spider self = (net.minecraft.world.entity.monster.spider.Spider) (Object) this;
        self.getGoalSelector().addGoal(2, new SurfaceChaseGoal(self));
        if ((Object) this instanceof CaveSpider) return;
        self.getGoalSelector().addGoal(2, new WebShotGoal(self, 3.0F, 10.0F, 30, 160));
    }

    @Inject(method = "createNavigation", at = @At("HEAD"), cancellable = true)
    private void monstrous$navigation(Level level, CallbackInfoReturnable<PathNavigation> cir) {
        cir.setReturnValue(new SurfaceNavigation((net.minecraft.world.entity.monster.spider.Spider) (Object) this, level));
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void monstrous$surfaceBehaviour(CallbackInfo ci) {
        net.minecraft.world.entity.monster.spider.Spider self = (net.minecraft.world.entity.monster.spider.Spider) (Object) this;
        if (self.level().isClientSide()) return;

        self.resetFallDistance();

        if (this.monstrous$drivenTicks > 0) {
            this.monstrous$drivenTicks--;
            int code = monstrous$readSurface(self);
            self.setClimbing(code >= 1 && code <= 5);
            return;
        }

        boolean wantsUp = self.getTarget() != null
                || (self.getMoveControl().hasWanted() && self.getMoveControl().getWantedY() > self.getY() + 0.5D);
        boolean climbing = self.horizontalCollision && wantsUp;
        self.setClimbing(climbing);
        monstrous$writeSurface(self, climbing ? monstrous$wallCode(self) : 0);
    }

    @Override
    public void monstrous$setSurface(@Nullable Direction face) {
        net.minecraft.world.entity.monster.spider.Spider self = (net.minecraft.world.entity.monster.spider.Spider) (Object) this;
        int code = 0;
        if (face == Direction.UP) {
            code = 5;
        } else if (face != null && face.getAxis().isHorizontal()) {
            code = face.get2DDataValue() + 1;
        }
        monstrous$writeSurface(self, code);
        this.monstrous$drivenTicks = 2;
    }

    @Override
    public boolean monstrous$isDriven() {
        return this.monstrous$drivenTicks > 0;
    }

    @Override
    public void monstrous$tickSurface() {
        net.minecraft.world.entity.monster.spider.Spider self = (net.minecraft.world.entity.monster.spider.Spider) (Object) this;
        if (!self.level().isClientSide()) return;

        this.monstrous$prevWall = this.monstrous$wall;
        this.monstrous$prevCeiling = this.monstrous$ceiling;

        double mx = this.monstrous$hasLast ? self.getX() - this.monstrous$lastX : 0.0D;
        double my = this.monstrous$hasLast ? self.getY() - this.monstrous$lastY : 0.0D;
        double mz = this.monstrous$hasLast ? self.getZ() - this.monstrous$lastZ : 0.0D;
        this.monstrous$lastX = self.getX();
        this.monstrous$lastY = self.getY();
        this.monstrous$lastZ = self.getZ();
        this.monstrous$hasLast = true;

        float dist = (float) Math.sqrt(mx * mx + my * my + mz * mz);
        this.monstrous$animSpeedO = this.monstrous$animSpeed;
        this.monstrous$animSpeed += (Math.min(dist * 4.0F, 1.0F) - this.monstrous$animSpeed) * 0.4F;
        this.monstrous$animPos += this.monstrous$animSpeed;

        int code = monstrous$readSurface(self);
        boolean onWall = code >= 1 && code <= 4;
        boolean onCeiling = code == 5;

        if (onWall) {
            Direction face = Direction.from2DDataValue(code - 1);
            this.monstrous$wallNormal = new Vec3(-face.getStepX(), 0.0D, -face.getStepZ());
            double nx = this.monstrous$wallNormal.x;
            double nz = this.monstrous$wallNormal.z;
            double ax = nz * 1; // just to make the ide shut up
            double az = -nx;
            double beta = mx * ax + mz * az;
            double len = Math.sqrt(my * my + beta * beta);

            if (code != this.monstrous$prevCode) {
                if (len > 0.005D) {
                    double alpha = my / len;
                    beta /= len;
                    this.monstrous$headX = -alpha * nx + beta * ax;
                    this.monstrous$headZ = -alpha * nz + beta * az;
                } else {
                    this.monstrous$headX = -nx;
                    this.monstrous$headZ = -nz;
                }
            } else if (len > 0.02D) {
                double alpha = my / len;
                beta /= len;
                double hx = -alpha * nx + beta * ax;
                double hz = -alpha * nz + beta * az;
                double sx = this.monstrous$headX + (hx - this.monstrous$headX) * 0.3D;
                double sz = this.monstrous$headZ + (hz - this.monstrous$headZ) * 0.3D;
                double sl = Math.sqrt(sx * sx + sz * sz);
                if (sl > 1.0E-3D) {
                    this.monstrous$headX = sx / sl;
                    this.monstrous$headZ = sz / sl;
                } else {
                    this.monstrous$headX = hx;
                    this.monstrous$headZ = hz;
                }
            }
        }

        this.monstrous$prevCode = code;
        this.monstrous$wall = Mth.lerp(0.25F, this.monstrous$wall, onWall ? 1.0F : 0.0F);
        this.monstrous$ceiling = Mth.lerp(0.25F, this.monstrous$ceiling, onCeiling ? 1.0F : 0.0F);
    }

    @Override
    public float monstrous$getWall(float partialTicks) {
        return Mth.lerp(partialTicks, this.monstrous$prevWall, this.monstrous$wall);
    }

    @Override
    public float monstrous$getCeiling(float partialTicks) {
        return Mth.lerp(partialTicks, this.monstrous$prevCeiling, this.monstrous$ceiling);
    }

    @Override
    public Vec3 monstrous$getWallNormal() {
        return this.monstrous$wallNormal;
    }

    @Override
    public Vec3 monstrous$getWallHeading() {
        return new Vec3(this.monstrous$headX, 0.0D, this.monstrous$headZ);
    }

    @Override
    public float monstrous$getClimbAnimPos(float partialTicks) {
        return this.monstrous$animPos - this.monstrous$animSpeed * (1.0F - partialTicks);
    }

    @Override
    public float monstrous$getClimbAnimSpeed(float partialTicks) {
        return Math.min(1.0F, Mth.lerp(partialTicks, this.monstrous$animSpeedO, this.monstrous$animSpeed));
    }

    @Unique
    private static int monstrous$readSurface(net.minecraft.world.entity.monster.spider.Spider s) {
        return (s.getEntityData().get(DATA_FLAGS_ID) >> 1) & 7;
    }

    @Unique
    private static void monstrous$writeSurface(net.minecraft.world.entity.monster.spider.Spider s, int code) {
        byte flags = s.getEntityData().get(DATA_FLAGS_ID);
        s.getEntityData().set(DATA_FLAGS_ID, (byte) ((flags & ~0b1110) | (code << 1)));
    }

    @Unique
    private static int monstrous$wallCode(net.minecraft.world.entity.monster.spider.Spider s) {
        AABB box = s.getBoundingBox();
        Direction best = null;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            AABB probe = box.expandTowards(dir.getStepX() * 0.1D, 0.0D, dir.getStepZ() * 0.1D);
            if (!s.level().noBlockCollision(s, probe)) {
                if (best == null || dir == s.getDirection()) best = dir;
            }
        }
        return best == null ? 0 : best.get2DDataValue() + 1;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void monstrous$aimTick(CallbackInfo ci) {
        Spider self = (Spider) (Object) this;
        if (!self.level().isClientSide()) return;

        this.monstrous$prevAim = this.monstrous$aim;
        this.monstrous$aim = Mth.lerp(0.3F, this.monstrous$aim, monstrous$readAiming(self) ? 1.0F : 0.0F);
    }

    @Unique
    public void monstrous$setAiming(boolean aiming) {
        Spider self = (Spider) (Object) this;
        byte flags = self.getEntityData().get(DATA_FLAGS_ID);
        self.getEntityData().set(DATA_FLAGS_ID, (byte) (aiming ? (flags | 0b10000) : (flags & ~0b10000)));
    }

    @Unique
    public float monstrous$getAim(float partialTicks) {
        return Mth.lerp(partialTicks, this.monstrous$prevAim, this.monstrous$aim);
    }

    @Unique
    private static boolean monstrous$readAiming(Spider s) {
        return (s.getEntityData().get(DATA_FLAGS_ID) & 0b10000) != 0;
    }
}