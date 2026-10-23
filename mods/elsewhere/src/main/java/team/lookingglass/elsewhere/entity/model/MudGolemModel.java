package team.lookingglass.elsewhere.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.KeyframeAnimation;
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
import team.lookingglass.elsewhere.entity.renderer.states.MudGolemRenderState;

@Environment(EnvType.CLIENT)
public class MudGolemModel extends EntityModel<MudGolemRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mud_golem"), "main");
    private final ModelPart upperBody;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public MudGolemModel(final ModelPart root) {
        super(root);
        ModelPart body = root.getChild("body");
        this.upperBody = body.getChild("upperBody");
        this.head = this.upperBody.getChild("head");
        this.rightArm = this.upperBody.getChild("rightArm");
        this.leftArm = this.upperBody.getChild("leftArm");
        this.leftLeg = body.getChild("leftLeg");
        this.rightLeg = body.getChild("rightLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));
        PartDefinition upperBody = body.addOrReplaceChild("upperBody", CubeListBuilder.create().texOffs(0, 16).addBox(-8.0F, -20.0F, -3.0F, 16.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 32).addBox(-6.0F, -10.0F, -3.0F, 12.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition head = upperBody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 1).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
        PartDefinition shrub = head.addOrReplaceChild("shrub", CubeListBuilder.create(), PartPose.offset(-2.5F, -8.0F, 0.0F));
        PartDefinition shrubRight = shrub.addOrReplaceChild("shrubRight", CubeListBuilder.create().texOffs(24, 2).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition shrubLeft = shrub.addOrReplaceChild("shrubLeft", CubeListBuilder.create().texOffs(24, 2).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        PartDefinition rightArm = upperBody.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(44, 0).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 26.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -18.0F, 0.0F));
        PartDefinition leftArm = upperBody.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(44, 0).mirror().addBox(0.0F, 0.0F, -3.0F, 4.0F, 26.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -18.0F, 0.0F));
        PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-2.5F, 0.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.5F, 0.0F, 0.0F));
        PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(final MudGolemRenderState state) {
        super.setupAnim(state);
        this.head.yRot = state.yRot * ((float)Math.PI / 180F);
        this.head.xRot = state.xRot * ((float)Math.PI / 180F);

        float speed = state.walkAnimationSpeed;
        float pos = state.walkAnimationPos;

        this.rightArm.xRot = Mth.cos(pos * 0.6662F + (float)Math.PI) * 1.0F * speed;
        this.leftArm.xRot = Mth.cos(pos * 0.6662F) * 1.0F * speed;
        this.rightLeg.xRot = Mth.cos(pos * 0.6662F) * 1.4F * speed;
        this.leftLeg.xRot = Mth.cos(pos * 0.6662F + (float)Math.PI) * 1.4F * speed;

        this.animateThrowSwing(state.attackAnim);
    }

    private void animateThrowSwing(final float attackAnim) {
        this.animateArm(this.rightArm, attackAnim, 0.0F);
        this.animateArm(this.leftArm, attackAnim, 0.06F);

        float torsoT = Mth.clampedLerp(attackAnim / 0.35F, 0.0F, 1.0F);
        this.upperBody.xRot += Mth.sin(torsoT * (float)Math.PI) * -0.3F;
    }

    private void animateArm(final ModelPart arm, final float rawAnim, final float phaseOffset) {
        float attackAnim = Mth.clampedLerp(rawAnim - phaseOffset, 0.0F, 1.0F);
        if (attackAnim <= 0.0F) {
            return;
        }

        if (attackAnim < 0.35F) {
            float t = attackAnim / 0.35F;
            float eased = 1.0F - (1.0F - t) * (1.0F - t) * (1.0F - t);
            arm.xRot += Mth.lerp(eased, 0.4F, -2.6F);
        } else {
            float t = (attackAnim - 0.35F) / 0.65F;
            float eased = t * t * (3.0F - 2.0F * t);
            arm.xRot += Mth.lerp(eased, -2.6F, 0.0F);
        }
    }
}