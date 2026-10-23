package team.lookingglass.elsewhere.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Matrix4fc;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.model.animation.VanguardAnimation;
import team.lookingglass.elsewhere.entity.renderer.states.VanguardRenderState;

public class VanguardModel extends EntityModel<VanguardRenderState> implements ArmedModel<VanguardRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "vanguard"), "main");

    private static final float WALK_POS_PER_CYCLE = 40.0F;

    private final ModelPart body;
    private final ModelPart chest;
    private final ModelPart head;
    private final ModelPart rArm;
    private final ModelPart lArm;
    private final ModelPart shield;
    private final ModelPart lLeg;
    private final ModelPart rLeg;
    private final ModelPart rHand;
    private final ModelPart lHand;

    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation walkAnimation;
    private final KeyframeAnimation blockStartAnimation;
    private final KeyframeAnimation blockAnimation;
    private final KeyframeAnimation singleSwingAnimation;
    private final KeyframeAnimation doubleSwingAnimation;
    private final KeyframeAnimation chargeAnimation;
    private final KeyframeAnimation stunAnimation;

    private final ModelPart[] poseParts;
    private final float[] savedPose;

    public VanguardModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.chest = this.body.getChild("chest");
        this.head = this.chest.getChild("head");
        this.rArm = this.chest.getChild("rArm");
        this.lArm = this.chest.getChild("lArm");
        this.shield = this.lArm.getChild("shield");
        this.lLeg = this.body.getChild("lLeg");
        this.rLeg = this.body.getChild("rLeg");
        this.rHand = this.rArm.getChild("hand");
        this.lHand = this.lArm.getChild("hand");

        this.idleAnimation = VanguardAnimation.idle.bake(root);
        this.walkAnimation = VanguardAnimation.walk.bake(root);
        this.blockStartAnimation = VanguardAnimation.blockStart.bake(root);
        this.blockAnimation = VanguardAnimation.block.bake(root);
        this.singleSwingAnimation = VanguardAnimation.singleSwing.bake(root);
        this.doubleSwingAnimation = VanguardAnimation.doubleSwing.bake(root);
        this.chargeAnimation = VanguardAnimation.charge.bake(root);
        this.stunAnimation = VanguardAnimation.stun.bake(root);

        this.poseParts = new ModelPart[]{body, chest, head, rArm, lArm, lLeg, rLeg};
        this.savedPose = new float[poseParts.length * 6];
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 35).addBox(-5.0F, -9.0F, -4.0F, 10.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition chest = body.addOrReplaceChild("chest", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
                        .texOffs(36, 35).addBox(-8.0F, 0.0F, -6.0F, 16.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -9.0F, 0.0F));

        PartDefinition head = chest.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 0).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                        .texOffs(24, 0).mirror().addBox(2.0F, -15.0F, -1.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                        .texOffs(24, 0).addBox(-8.0F, -15.0F, -1.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -8.0F, -1.0F));

        PartDefinition rArm = chest.addOrReplaceChild("rArm", CubeListBuilder.create()
                        .texOffs(64, 0).addBox(-2.0F, 0.0F, -3.0F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-10.0F, -6.0F, -1.0F));
        rArm.addOrReplaceChild("hand", CubeListBuilder.create(), PartPose.offset(0.5F, 18.0F, -0.5F));

        PartDefinition lArm = chest.addOrReplaceChild("lArm", CubeListBuilder.create()
                        .texOffs(64, 0).mirror().addBox(-3.0F, 0.0F, -3.0F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(10.0F, -6.0F, -1.0F));
        lArm.addOrReplaceChild("hand", CubeListBuilder.create(), PartPose.offset(-0.5F, 18.0F, -0.5F));

        PartDefinition shield = lArm.addOrReplaceChild("shield", CubeListBuilder.create()
                        .texOffs(0, 76).addBox(-6.0F, 0.0F, -4.0F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(2.0F, 9.0F, 0.0F));

        shield.addOrReplaceChild("shield_r1", CubeListBuilder.create()
                        .texOffs(0, 52).addBox(0.0F, -6.0F, -6.0F, 1.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition lLeg = body.addOrReplaceChild("lLeg", CubeListBuilder.create()
                        .texOffs(80, 19).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(4.0F, -2.0F, 0.0F));

        PartDefinition rLeg = body.addOrReplaceChild("rLeg", CubeListBuilder.create()
                        .texOffs(80, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-4.0F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    @Override
    public void setupAnim(VanguardRenderState state) {
        resetToBasePose();

        head.xRot = state.xRot * Mth.DEG_TO_RAD;
        head.yRot = state.yRot * Mth.DEG_TO_RAD;

        boolean swinging = state.singleSwingAnimationState.isStarted() || state.doubleSwingAnimationState.isStarted();
        float blockW = smoothstep(state.blockWeight);
        float combatW = swinging ? 1.0F : blockW;

        saveBase();
        walkAnimation.applyWalk(state.walkPhase * WALK_POS_PER_CYCLE, state.walkWeight, 1.0F, 1.0F);
        blendFromSaved(1.0F - combatW);

        saveBase();
        idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
        blendFromSaved(1.0F - state.walkWeight);

        if (blockW > 0.0F) {
            saveBase();
            if (state.blockAnimationState.isStarted()) {
                blockAnimation.apply(state.blockAnimationState, state.ageInTicks);
            } else {
                blockStartAnimation.apply(state.blockStartAnimationState, state.ageInTicks);
            }
            blendFromSaved(blockW);
        }

        singleSwingAnimation.apply(state.singleSwingAnimationState, state.ageInTicks);
        doubleSwingAnimation.apply(state.doubleSwingAnimationState, state.ageInTicks);
        chargeAnimation.apply(state.chargeAnimationState, state.ageInTicks);
        stunAnimation.apply(state.stunAnimationState, state.ageInTicks);
    }

    private static float smoothstep(float t) {
        t = Mth.clampedLerp(t, 0.0F, 1.0F);
        return t * t * (3.0F - 2.0F * t);
    }

    private void saveBase() {
        for (int i = 0; i < poseParts.length; i++) {
            ModelPart p = poseParts[i];
            int o = i * 6;
            savedPose[o] = p.x;
            savedPose[o + 1] = p.y;
            savedPose[o + 2] = p.z;
            savedPose[o + 3] = p.xRot;
            savedPose[o + 4] = p.yRot;
            savedPose[o + 5] = p.zRot;
        }
    }

    private void blendFromSaved(float amount) {
        if (amount >= 1.0F) {
            return;
        }
        for (int i = 0; i < poseParts.length; i++) {
            ModelPart p = poseParts[i];
            int o = i * 6;
            p.x = savedPose[o] + (p.x - savedPose[o]) * amount;
            p.y = savedPose[o + 1] + (p.y - savedPose[o + 1]) * amount;
            p.z = savedPose[o + 2] + (p.z - savedPose[o + 2]) * amount;
            p.xRot = savedPose[o + 3] + (p.xRot - savedPose[o + 3]) * amount;
            p.yRot = savedPose[o + 4] + (p.yRot - savedPose[o + 4]) * amount;
            p.zRot = savedPose[o + 5] + (p.zRot - savedPose[o + 5]) * amount;
        }
    }

    private void resetToBasePose() {
        body.xRot = 0.0F; body.yRot = 0.0F; body.zRot = 0.0F; body.x = 0.0F; body.y = 12.0F; body.z = 0.0F;
        chest.xRot = 0.0F; chest.yRot = 0.0F; chest.zRot = 0.0F; chest.x = 0.0F; chest.y = -9.0F; chest.z = 0.0F;
        head.xRot = 0.0F; head.yRot = 0.0F; head.zRot = 0.0F; head.x = 0.0F; head.y = -8.0F; head.z = -1.0F;
        rArm.xRot = 0.0F; rArm.yRot = 0.0F; rArm.zRot = 0.0F; rArm.x = -10.0F; rArm.y = -6.0F; rArm.z = -1.0F;
        lArm.xRot = 0.0F; lArm.yRot = 0.0F; lArm.zRot = 0.0F; lArm.x = 10.0F; lArm.y = -6.0F; lArm.z = -1.0F;
        lLeg.xRot = 0.0F; lLeg.yRot = 0.0F; lLeg.zRot = 0.0F; lLeg.x = 4.0F; lLeg.y = -2.0F; lLeg.z = 0.0F;
        rLeg.xRot = 0.0F; rLeg.yRot = 0.0F; rLeg.zRot = 0.0F; rLeg.x = -4.0F; rLeg.y = -2.0F; rLeg.z = 0.0F;
    }

    @Override
    public void translateToHand(VanguardRenderState state, HumanoidArm arm, PoseStack poseStack) {
        body.translateAndRotate(poseStack);
        chest.translateAndRotate(poseStack);
        ModelPart armPart = arm == HumanoidArm.RIGHT ? rArm : lArm;
        ModelPart handPart = arm == HumanoidArm.RIGHT ? rHand : lHand;
        armPart.translateAndRotate(poseStack);
        handPart.translateAndRotate(poseStack);

        poseStack.rotateDegrees(Axis.XP, -10.0F);
        poseStack.translate(0.05F, -0.75F, -0.15F);
    }
}