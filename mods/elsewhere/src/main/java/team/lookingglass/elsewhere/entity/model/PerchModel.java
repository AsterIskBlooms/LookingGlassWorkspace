package team.lookingglass.elsewhere.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.SalmonRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import team.lookingglass.elsewhere.Elsewhere;

public class PerchModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "perch"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftFin;
    private final ModelPart rightFin;
    private final ModelPart tailFin;

    public PerchModel(ModelPart root) {
        super(root);
        body = root.getChild("body");
        head = body.getChild("head");
        leftFin = body.getChild("leftFin");
        rightFin = body.getChild("rightFin");
        tailFin = body.getChild("tailfin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(0.0F, -7.0F, -1.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(28, 0).addBox(0.0F, 0.0F, 3.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9992F, -3.0008F, -4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(13, 1).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(24, 4).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(24, 1).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition tailFin = body.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(21, 2).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public void setupAnim(final LivingEntityRenderState state) {
        super.setupAnim(state);

        float amplitudeMultiplier = state.isInWater ? 1.0F : 1.5F;
        tailFin.yRot = -amplitudeMultiplier * 0.45F * Mth.sin(0.6F * state.ageInTicks);

        body.yRot = -amplitudeMultiplier * 0.15F * Mth.sin(0.6F * state.ageInTicks + 0.3F);

        if (!state.isInWater) {
            body.zRot = Mth.HALF_PI;
        } else {
            body.zRot = 0.0F;
        }
    }
}