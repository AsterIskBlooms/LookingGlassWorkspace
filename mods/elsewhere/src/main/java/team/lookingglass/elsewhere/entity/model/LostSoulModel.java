package team.lookingglass.elsewhere.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.renderer.states.LostSoulRenderState;

public class LostSoulModel extends EntityModel<LostSoulRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "lost_soul"), "main");
    private static final float HOVER_PIVOT_Y = 20.0F;
    private static final float SPIN_RADIANS_PER_TICK = 0.1F;

    private final ModelPart pivot;
    private final ModelPart body;

    public LostSoulModel(final ModelPart root) {
        super(root);
        this.pivot = root.getChild("pivot");
        this.body = this.pivot.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition pivot = root.addOrReplaceChild(
                "pivot",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, HOVER_PIVOT_Y, 0.0F)
        );
        pivot.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -5.0F, -2.5F, 5.0F, 10.0F, 5.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 32, 16);
    }

    @Override
    public void setupAnim(final LostSoulRenderState state) {
        this.pivot.resetPose();
        this.body.resetPose();

        this.pivot.xRot = state.facingPitch;
        this.pivot.yRot = state.facingYaw;

        this.body.yRot = state.ageInTicks * SPIN_RADIANS_PER_TICK;
    }
}