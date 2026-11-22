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
import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import team.lookingglass.elsewhere.Elsewhere;

@Environment(EnvType.CLIENT)
public class BoltModel extends EntityModel<ArrowRenderState> {
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "bolt"), "main");

    public BoltModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutCull);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F),
                PartPose.offsetAndRotation(-12.0F, 0.0F, 0.0F, ((float) Math.PI / 4F), 0.0F, 0.0F).withScale(0.8F));
        CubeListBuilder cross = CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.0F, 0.0F, 16.0F, 4.0F, 0.0F, CubeDeformation.NONE);
        root.addOrReplaceChild("cross_1", cross, PartPose.rotation(((float) Math.PI / 4F), 0.0F, 0.0F));
        root.addOrReplaceChild("cross_2", cross, PartPose.rotation(2.3561945F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh.apply(ArrowModel.ARROW_SCALE), 32, 16);
    }

    @Override
    public void setupAnim(ArrowRenderState state) {
        super.setupAnim(state);
        if (state.shake > 0.0F) {
            this.root.zRot += -Mth.sin(state.shake * 3.0F) * state.shake * ((float) Math.PI / 180F);
        }
    }
}