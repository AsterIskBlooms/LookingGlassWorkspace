package team.lookingglass.elsewhere.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.renderer.states.WerewolfRenderState;

@Environment(EnvType.CLIENT)
public class WerewolfModel extends EntityModel<WerewolfRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "werewolf"), "main");
    private final ModelPart upperBody;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart tail;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public WerewolfModel(final ModelPart root) {
        super(root);
        ModelPart werewolf = root.getChild("werewolf");
        this.upperBody = werewolf.getChild("upperBody");
        this.head = this.upperBody.getChild("head");
        this.rightArm = this.upperBody.getChild("rightArm");
        this.leftArm = this.upperBody.getChild("leftArm");
        this.tail = this.upperBody.getChild("tail");
        ModelPart legs = werewolf.getChild("legs");
        this.rightLeg = legs.getChild("rightLeg");
        this.leftLeg = legs.getChild("leftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition werewolf = partdefinition.addOrReplaceChild("werewolf", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 4.0F));

        PartDefinition upperBody = werewolf.addOrReplaceChild("upperBody", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -16.0F, -5.0F, 14.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(46, 18).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition fluff = upperBody.addOrReplaceChild("fluff", CubeListBuilder.create().texOffs(0, 18).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -5.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition head = upperBody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(88, 60).addBox(-3.0F, -3.0F, -1.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 1.0F, 0.0F, 0.0F, -0.3491F));
        PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(20, 85).addBox(-3.0F, -3.0F, -1.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -7.0F, 1.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0, 85).addBox(-2.5F, -18.0F, -6.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, -3.0F));
        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(78, 86).addBox(-2.0F, -1.0F, -5.5F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition rightArm = upperBody.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(46, 86).addBox(2.0F, 3.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -13.0F, 0.0F, 0.1745F, 0.0F, -0.0873F));

        PartDefinition rightHand = rightArm.addOrReplaceChild("rightHand", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 9.0F, 0.0F, -0.2618F, 0.0F, 0.0F));
        PartDefinition rightHandFinger = rightHand.addOrReplaceChild("rightHandFinger", CubeListBuilder.create().texOffs(88, 54).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.0F, -8.0F, 0.9599F, -0.6109F, 0.4363F));
        PartDefinition rightWrist = rightHand.addOrReplaceChild("rightWrist", CubeListBuilder.create().texOffs(68, 34).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition leftArm = upperBody.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(32, 55).addBox(-8.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(62, 86).addBox(-6.0F, 3.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -13.0F, 0.0F, 0.1745F, 0.0F, 0.0873F));

        PartDefinition leftHand = leftArm.addOrReplaceChild("leftHand", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 9.0F, 0.0F, -0.2618F, 0.0F, 0.0F));
        PartDefinition leftHandFinger = leftHand.addOrReplaceChild("leftHandFinger", CubeListBuilder.create().texOffs(82, 28).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 11.0F, -8.0F, 0.9599F, 0.6109F, -0.4363F));
        PartDefinition leftWrist = leftHand.addOrReplaceChild("leftWrist", CubeListBuilder.create().texOffs(64, 55).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition tail = upperBody.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition tailTip = tail.addOrReplaceChild("tailTip", CubeListBuilder.create().texOffs(80, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 11.0F, 0.0436F, 0.0F, 0.0F));
        PartDefinition tailStart = tail.addOrReplaceChild("tailStart", CubeListBuilder.create().texOffs(0, 71).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition legs = werewolf.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition rightLeg = legs.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(-5.0F, -6.0F, -3.0F));
        PartDefinition rightLegLower = rightLeg.addOrReplaceChild("rightLegLower", CubeListBuilder.create().texOffs(82, 12).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, 6.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition rightThigh = rightLeg.addOrReplaceChild("rightThigh", CubeListBuilder.create().texOffs(0, 35).addBox(-3.0F, 0.0F, -1.0F, 7.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition rightFoot = rightLeg.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(58, 75).addBox(-3.0F, 20.0F, -5.0F, 7.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(20, 91).addBox(0.5F, 21.0F, -8.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 0.0F, 0.0F));
        PartDefinition rightClaw3 = rightFoot.addOrReplaceChild("rightClaw3", CubeListBuilder.create().texOffs(92, 34).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 23.0F, -5.0F, 0.0F, -0.3491F, 0.0F));
        PartDefinition rightClaw1 = rightFoot.addOrReplaceChild("rightClaw1", CubeListBuilder.create().texOffs(88, 81).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 23.0F, -5.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition leftLeg = legs.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offset(5.0F, -6.0F, -3.0F));
        PartDefinition leftLegLower = leftLeg.addOrReplaceChild("leftLegLower", CubeListBuilder.create().texOffs(28, 82).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, 6.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition leftThigh = leftLeg.addOrReplaceChild("leftThigh", CubeListBuilder.create().texOffs(34, 35).addBox(-3.0F, 0.0F, -1.0F, 7.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition leftFoot = leftLeg.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(28, 71).addBox(-13.0F, 20.0F, -5.0F, 7.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(88, 71).addBox(-9.5F, 21.0F, -8.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition leftClaw3 = leftFoot.addOrReplaceChild("leftClaw3", CubeListBuilder.create().texOffs(88, 76).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 23.0F, -5.0F, 0.0F, -0.3491F, 0.0F));
        PartDefinition leftClaw1 = leftFoot.addOrReplaceChild("leftClaw1", CubeListBuilder.create().texOffs(88, 66).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, 23.0F, -5.0F, 0.0F, 0.3491F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(final WerewolfRenderState state) {
        super.setupAnim(state);

        this.head.yRot = state.yRot * ((float)Math.PI / 180F);
        this.head.xRot += state.xRot * ((float)Math.PI / 180F);

        float speed = state.walkAnimationSpeed;
        float pos = state.walkAnimationPos;

        if (state.preparingCharge) {
            animateChargeWindup(state);
        } else if (state.charging) {
            animateCharge(pos);
        } else {
            animateIdleWalk(pos, speed);
        }
    }

    private void animateIdleWalk(final float pos, final float speed) {
        float cycle = pos * 0.6662F;

        this.rightArm.xRot += Mth.cos(cycle + (float)Math.PI) * 0.6F * speed;
        this.leftArm.xRot += Mth.cos(cycle) * 0.6F * speed;

        this.rightLeg.xRot = Mth.cos(cycle) * 0.9F * speed;
        this.leftLeg.xRot = Mth.cos(cycle + (float)Math.PI) * 0.9F * speed;

        this.tail.yRot = Mth.sin(cycle * 0.5F) * 0.12F * speed;
    }

    private void animateChargeWindup(final WerewolfRenderState state) {
        float windup = state.chargeWindupProgress;

        this.upperBody.xRot += Mth.lerp(windup, 0.0F, 0.5F);
        this.head.xRot += Mth.lerp(windup, 0.0F, -0.35F);
        this.rightArm.xRot += Mth.lerp(windup, 0.0F, -1.8F);
        this.leftArm.xRot += Mth.lerp(windup, 0.0F, -1.8F);
        this.rightLeg.xRot = Mth.lerp(windup, 0.0F, 0.5F);
        this.leftLeg.xRot = Mth.lerp(windup, 0.0F, 0.5F);
        this.tail.yRot = Mth.lerp(windup, 0.0F, 0.1F);
    }

    private void animateCharge(final float pos) {
        float cycle = pos * 0.8F;
        float front = Mth.sin(cycle);
        float rear = Mth.sin(cycle + (float)Math.PI);

        this.upperBody.xRot += 0.5F;
        this.upperBody.y += Mth.sin(cycle * 2.0F) * 0.5F;
        this.head.xRot += -0.35F;

        this.rightArm.xRot += -1.7F + front * 0.7F;
        this.leftArm.xRot += -1.7F + rear * 0.7F;

        this.rightLeg.xRot = 0.65F + rear * 0.8F;
        this.leftLeg.xRot = 0.65F + front * 0.8F;

        this.tail.yRot = Mth.sin(cycle * 0.6F) * 0.35F;
    }
}