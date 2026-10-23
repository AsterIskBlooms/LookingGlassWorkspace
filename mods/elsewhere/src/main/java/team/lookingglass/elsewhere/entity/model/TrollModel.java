package team.lookingglass.elsewhere.entity.model;

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
import team.lookingglass.elsewhere.entity.renderer.states.TrollRenderState;

public class TrollModel extends EntityModel<TrollRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "troll"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public TrollModel(final ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.rightLeg = this.body.getChild("rLeg");
        this.leftLeg = this.body.getChild("lLeg");
        this.rightArm = this.body.getChild("rArm");
        this.leftArm = this.body.getChild("lArm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 14).addBox(-5.5F, -11.0F, -2.0F, 11.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 17.0F, 0.0F));
        body.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-6.5F, -8.0F, -3.0F, 13.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(38, 8).addBox(-10.5F, -5.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(38, 8).mirror().addBox(6.5F, -5.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(0.0F, -11.0F, 0.0F));
        body.addOrReplaceChild("rLeg", CubeListBuilder.create()
                        .texOffs(30, 20).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.5F, 0.0F, 0.0F));
        body.addOrReplaceChild("lLeg", CubeListBuilder.create()
                        .texOffs(30, 20).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(3.5F, 0.0F, 0.0F));
        body.addOrReplaceChild("rArm", CubeListBuilder.create()
                        .texOffs(38, 12).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-6.5F, -9.0F, 0.0F));
        body.addOrReplaceChild("lArm", CubeListBuilder.create()
                        .texOffs(38, 12).mirror().addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(6.5F, -9.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(final TrollRenderState state) {
        this.body.resetPose();
        this.head.resetPose();
        this.rightLeg.resetPose();
        this.leftLeg.resetPose();
        this.rightArm.resetPose();
        this.leftArm.resetPose();

        this.head.yRot = state.yRot * ((float) Math.PI / 180F);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);

        if (state.angry) {
            this.head.zRot = Mth.sin(state.ageInTicks * 3.0F) * 0.05F;
            this.head.xRot += Mth.sin(state.ageInTicks * 2.5F) * 0.04F;
        }

        float walkLegAmount = 0.8F;
        this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * walkLegAmount * state.walkAnimationSpeed;
        this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI) * walkLegAmount * state.walkAnimationSpeed;

        boolean holdingItem = !state.stolenItem.isEmpty();
        if (holdingItem) {
            this.rightArm.xRot = -2.7F;
            this.leftArm.xRot = -2.7F;
            this.rightArm.yRot = 0.3F;
            this.leftArm.yRot = -0.3F;
        } else {
            float swing = Mth.sin(state.attackTime * (float) Math.PI);
            float walkArmAmount = 0.8F * (1.0F - swing);

            float walkArmSwing = Mth.cos(state.walkAnimationPos * 0.6662F) * walkArmAmount * state.walkAnimationSpeed;
            this.leftArm.xRot = walkArmSwing;
            this.rightArm.xRot = -walkArmSwing;

            if (state.angry) {
                float flingAmount = -swing * 2.4F;
                this.rightArm.xRot += flingAmount;
                this.leftArm.xRot += flingAmount;
                this.body.xRot = swing * 0.25F;
            } else {
                this.rightArm.xRot += -swing * 1.2F;
            }
        }
    }
}