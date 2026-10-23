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
import team.lookingglass.elsewhere.entity.renderer.states.WhistlerRenderState;

public class WhistlerModel extends EntityModel<WhistlerRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "whistler"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart lbFoot;
    private final ModelPart rbFoot;
    private final ModelPart lfFoot;
    private final ModelPart rfFoot;

    public WhistlerModel(final ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.lbFoot = this.body.getChild("lbFoot");
        this.rbFoot = this.body.getChild("rbFoot");
        this.lfFoot = this.body.getChild("lfFoot");
        this.rfFoot = this.body.getChild("rfFoot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(40, 0).addBox(-3.0F, -26.0F, -3.0F, 6.0F, 12.0F, 6.0F, CubeDeformation.NONE)
                .texOffs(0, 19).addBox(-4.0F, -14.0F, -4.0F, 8.0F, 12.0F, 8.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 20.0F, 0.0F));
        body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 9.0F, 10.0F, CubeDeformation.NONE), PartPose.offset(0.0F, -24.0F, 0.0F));
        body.addOrReplaceChild("lbFoot", CubeListBuilder.create().texOffs(32, 19).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(2.0F, 0.0F, 4.0F));
        body.addOrReplaceChild("rbFoot", CubeListBuilder.create().texOffs(32, 19).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(-2.0F, 0.0F, 4.0F));
        body.addOrReplaceChild("lfFoot", CubeListBuilder.create().texOffs(32, 19).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(2.0F, 0.0F, -4.0F));
        body.addOrReplaceChild("rfFoot", CubeListBuilder.create().texOffs(32, 19).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(-2.0F, 0.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(final WhistlerRenderState state) {
        super.setupAnim(state);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) + state.headSpin * ((float) Math.PI / 180F);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);

        float speed = state.walkAnimationSpeed;
        float pos = state.walkAnimationPos;
        this.rbFoot.xRot = Mth.cos(pos * 0.6662F) * 1.4F * speed;
        this.lfFoot.xRot = Mth.cos(pos * 0.6662F) * 1.4F * speed;
        this.lbFoot.xRot = Mth.cos(pos * 0.6662F + (float) Math.PI) * 1.4F * speed;
        this.rfFoot.xRot = Mth.cos(pos * 0.6662F + (float) Math.PI) * 1.4F * speed;
    }
}