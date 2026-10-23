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
import team.lookingglass.elsewhere.entity.renderer.states.SweeperRenderState;

public class SweeperModel extends EntityModel<SweeperRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "sweeper"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public SweeperModel(final ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.rightFrontLeg = this.body.getChild("rightFrontLeg");
        this.leftFrontLeg = this.body.getChild("leftFrontLeg");
        this.rightHindLeg = this.body.getChild("rightHindLeg");
        this.leftHindLeg = this.body.getChild("leftHindLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(34, 12).addBox(-4.0F, -13.0F, -3.0F, 8.0F, 14.0F, 6.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 15.0F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 8.0F, 10.0F, CubeDeformation.NONE), PartPose.offset(0.0F, -13.0F, 0.0F));
        PartDefinition nub = head.addOrReplaceChild("nub", CubeListBuilder.create().texOffs(30, 6).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F, CubeDeformation.NONE)
                .texOffs(30, 0).addBox(0.0F, -4.0F, -3.0F, 0.0F, 4.0F, 6.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        PartDefinition rightFrontLeg = body.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(-1.0F, 2.0F, -4.0F, 5.0F, 8.0F, 4.0F, CubeDeformation.NONE).mirror(false), PartPose.offset(-4.0F, -1.0F, -1.0F));
        PartDefinition leftFrontLeg = body.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(42, 0).addBox(-4.0F, 2.0F, -4.0F, 5.0F, 8.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(4.0F, -1.0F, -1.0F));
        PartDefinition rightHindLeg = body.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(-1.0F, 2.0F, -3.5F, 5.0F, 8.0F, 4.0F, CubeDeformation.NONE).mirror(false), PartPose.offset(-4.0F, -1.0F, 4.5F));
        PartDefinition leftHindLeg = body.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(42, 0).addBox(-4.0F, 2.0F, -3.5F, 5.0F, 8.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(4.0F, -1.0F, 4.5F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void setupAnim(final SweeperRenderState state) {
        super.setupAnim(state);
        this.head.yRot = state.yRot * ((float)Math.PI / 180F);
        this.head.xRot = state.xRot * ((float)Math.PI / 180F);
        float speed = state.walkAnimationSpeed;
        float pos = state.walkAnimationPos;

        this.rightHindLeg.xRot = Mth.cos(pos * 0.6662F) * 1.4F * speed;
        this.leftHindLeg.xRot = Mth.cos(pos * 0.6662F + (float)Math.PI) * 1.4F * speed;
        this.rightFrontLeg.xRot = Mth.cos(pos * 0.6662F + (float)Math.PI) * 1.4F * speed;
        this.leftFrontLeg.xRot = Mth.cos(pos * 0.6662F) * 1.4F * speed;
        this.body.xRot += 0.14F * speed;
    }
}