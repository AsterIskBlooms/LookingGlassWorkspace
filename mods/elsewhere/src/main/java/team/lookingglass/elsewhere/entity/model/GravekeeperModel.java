package team.lookingglass.elsewhere.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.renderer.states.GravekeeperRenderState;

public class GravekeeperModel extends EntityModel<GravekeeperRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "gravekeeper"), "main");
    private final ModelPart head;
    private final ModelPart lArm;
    private final ModelPart rArm;
    private final ModelPart lLeg;
    private final ModelPart rLeg;

    public GravekeeperModel(ModelPart root) {
        super(root);
        ModelPart body = root.getChild("body");
        this.head = body.getChild("head");
        this.lArm = body.getChild("lArm");
        this.rArm = body.getChild("rArm");
        this.lLeg = body.getChild("lLeg");
        this.rLeg = body.getChild("rLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 2.0F));
        body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
        body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -11.0F, -3.0F));
        PartDefinition lArm = body.addOrReplaceChild("lArm", CubeListBuilder.create(), PartPose.offset(4.0F, -9.0F, -1.0F));
        lArm.addOrReplaceChild("orb", CubeListBuilder.create().texOffs(48, 35).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -1.0F, -10.0F, 0.5236F, 0.0F, 0.0F));
        lArm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(48, 16).addBox(-0.5F, -15.0F, -0.5F, 1.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 12.0F, -2.5F, 0.5236F, 0.0F, 0.0F));
        lArm.addOrReplaceChild("lArm_r1", CubeListBuilder.create().texOffs(32, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));
        body.addOrReplaceChild("rArm", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -9.0F, -1.0F));
        body.addOrReplaceChild("lLeg", CubeListBuilder.create().texOffs(32, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, 0.0F));
        body.addOrReplaceChild("rLeg", CubeListBuilder.create().texOffs(32, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -1.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(GravekeeperRenderState state) {
        super.setupAnim(state);
        this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
        this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
        float swing = state.walkAnimationPos * 0.6662F;
        float amount = state.walkAnimationSpeed;
        this.rLeg.xRot = Mth.cos(swing) * 1.4F * amount;
        this.lLeg.xRot = Mth.cos(swing + (float) Math.PI) * 1.4F * amount;
        if (state.casting) {
            this.rArm.xRot = -2.3F + Mth.sin(state.ageInTicks * 0.5F) * 0.15F;
            this.lArm.xRot -= 0.5236F;
        } else {
            this.rArm.xRot = Mth.cos(swing + (float) Math.PI) * amount + Mth.sin(state.ageInTicks * 0.067F) * 0.05F;
        }
        this.lArm.z -= Mth.sin(state.attackAnim * (float) Math.PI) * 5.0F;
    }
}