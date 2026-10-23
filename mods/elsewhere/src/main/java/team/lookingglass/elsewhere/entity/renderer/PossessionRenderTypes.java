package team.lookingglass.elsewhere.entity.renderer;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.renderpearl.api.pipeline.*;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.pipeline.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import team.lookingglass.elsewhere.Elsewhere;

import java.util.function.Function;

public class PossessionRenderTypes {
    private static final RenderPipeline possessedEntityPipeline = RenderPipeline.builder(RenderPipelines.MATRICES_FOG_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "pipeline/possessed_entity"))
            .withVertexShader(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "core/possessed_entity"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "core/possessed_entity"))
            .withVertexBinding(0, DefaultVertexFormat.ENTITY)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0)
            .withBindGroupLayout(BindGroupLayouts.LIGHTING)
            .withShaderDefine("ALPHA_CUTOUT", 0.1F)
            .withCull(false)
            .withColorTargetState(ColorTargetState.DEFAULT)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .withBindGroupLayout(BindGroupLayouts.SAMPLER1)
            .withBindGroupLayout(BindGroupLayouts.SAMPLER2)
            .build();

    private static final Function<Identifier, RenderType> POSSESSED_ENTITY = Util.memoize(texture -> {
        RenderSetup setup = RenderSetup.nonOitBuilder(possessedEntityPipeline)
                .withTexture("Sampler0", texture)
                .useLightmap()
                .useOverlay()
                .affectsCrumbling()
                .createRenderSetup();
        return RenderType.create("possessed_entity", setup);
    });

    public static RenderType possessedEntity(Identifier texture) {
        return POSSESSED_ENTITY.apply(texture);
    }
}