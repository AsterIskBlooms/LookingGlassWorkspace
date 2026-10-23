package team.lookingglass.elsewhere.registry.render.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SpawnerRenderer;
import net.minecraft.client.renderer.blockentity.TrialSpawnerRenderer;
import net.minecraft.client.renderer.blockentity.state.SpawnerRenderState;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerStateData;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import team.lookingglass.elsewhere.registry.blocktypes.entity.DungeonSpawnerBlockEntity;

@Environment(EnvType.CLIENT)
public class DungeonSpawnerRenderer implements BlockEntityRenderer<DungeonSpawnerBlockEntity, SpawnerRenderState> {
    private final EntityRenderDispatcher entityRenderer;

    public DungeonSpawnerRenderer(final BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.entityRenderer();
    }

    @Override
    public SpawnerRenderState createRenderState() {
        return new SpawnerRenderState();
    }

    @Override
    public void extractRenderState(final DungeonSpawnerBlockEntity blockEntity, final SpawnerRenderState state, final float partialTicks, final Vec3 cameraPosition, final ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        if (blockEntity.getLevel() != null) {
            TrialSpawner spawner = blockEntity.getTrialSpawner();
            TrialSpawnerStateData data = spawner.getStateData();
            Entity displayEntity = data.getOrCreateDisplayEntity(spawner, blockEntity.getLevel(), spawner.getState());
            TrialSpawnerRenderer.extractSpawnerData(state, partialTicks, displayEntity, this.entityRenderer, data.getOSpin(), data.getSpin());
        }
    }

    @Override
    public void submit(final SpawnerRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        if (state.displayEntity != null) {
            SpawnerRenderer.submitEntityInSpawner(poseStack, submitNodeCollector, state.displayEntity, this.entityRenderer, state.spin, state.scale, camera);
        }
    }
}