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
import net.minecraft.util.Mth;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.renderer.states.SubslimeRenderState;

public class SubslimeModel extends EntityModel<SubslimeRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "subslime"), "main");

    protected final ModelPart cube;
    protected final ModelPart innerCube;
    protected final ModelPart outerCube;

    public SubslimeModel(ModelPart root) {
        super(root);
        this.cube = root.getChild("cube");
        this.innerCube = cube.getChild("inner_cube");
        this.outerCube = cube.getChild("outer_cube");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition cube = root.addOrReplaceChild(
                "cube",
                CubeListBuilder.create(),
                PartPose.ZERO
        );

        cube.addOrReplaceChild(
                "inner_cube",
                CubeListBuilder.create()
                        .texOffs(0, 36)
                        .addBox(-7.0F, -16.0F, -7.0F, 14.0F, 14.0F, 14.0F),
                PartPose.ZERO
        );

        cube.addOrReplaceChild(
                "outer_cube",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-9.0F, -18.0F, -9.0F, 18.0F, 18.0F, 18.0F),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 96, 96);
    }

    @Override
    public void setupAnim(SubslimeRenderState state) {
        float squish = gentleEase(state.squish);

        float yScale = 1.0F + squish * 0.5F;
        float xzScale = 1.0F - squish * 0.22F;

        float stretchUp = Mth.sin(Mth.clampedLerp(state.attackTime / 0.15F, 0.0F, 1.0F) * Mth.PI);
        float squashDown = (1.0F - Mth.cos(Mth.clampedLerp((state.attackTime - 0.08F) / 0.2F, 0.0F, 1.0F) * Mth.PI)) * 0.5F;

        xzScale *= 1.0F - stretchUp * 0.35F + squashDown * 0.6F;
        yScale *= 1.0F + stretchUp * 0.5F - squashDown * 0.3F;

        cube.y = 24.0F;
        cube.yScale = yScale;
        cube.xScale = xzScale;
        cube.zScale = xzScale;

        cube.yRot = state.attackTime * Mth.TWO_PI * 2.0F;
    }

    private static float gentleEase(float t) {
        float c1 = 0.8F;
        float c3 = c1 + 1.0F;
        float t1 = t - 1.0F;
        return 1.0F + c3 * t1 * t1 * t1 + c1 * t1 * t1;
    }
}