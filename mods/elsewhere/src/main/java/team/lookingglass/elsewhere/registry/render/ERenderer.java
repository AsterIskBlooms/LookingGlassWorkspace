package team.lookingglass.elsewhere.registry.render;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.core.registries.BuiltInClientRegistries;
import net.minecraft.client.core.registries.ClientRegistries;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.EBlockEntityTypes;
import team.lookingglass.elsewhere.registry.EBlocks;
import team.lookingglass.elsewhere.registry.render.blocks.DungeonSpawnerRenderer;
import team.lookingglass.elsewhere.registry.render.blocks.DungeonVaultRenderer;
import team.lookingglass.elsewhere.registry.render.particles.*;
import team.lookingglass.elsewhere.registry.render.shields.QuickShieldSpecialRenderer;
import team.lookingglass.elsewhere.registry.render.shields.ReinforcedShieldSpecialRenderer;
import team.lookingglass.elsewhere.worldgen.BiomeColors;
import team.lookingglass.elsewhere.worldgen.EBiomes;
import team.lookingglass.mirrorlib.registry.color.BiomeColorRegistry;

import java.util.List;
import java.util.Objects;

public class ERenderer {

    public static void register() {
        registerRenderer(EBlockEntityTypes.DUNGEON_SPAWNER, DungeonSpawnerRenderer::new);
        registerRenderer(EBlockEntityTypes.DUNGEON_VAULT, DungeonVaultRenderer::new);

        ParticleProviderRegistry.getInstance().register(EParticles.SPARKLE, SparkleParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(EParticles.GILDED_BIRCH_LEAVES, FallingLeavesParticle.PaleOakProvider::new);
        ParticleProviderRegistry.getInstance().register(EParticles.PINE_LEAVES, FallingLeavesParticle.PoplarProvider::new);
        ParticleProviderRegistry.getInstance().register(EParticles.SUBSLIME_PARTICLE, CubeParticle.Provider::new);

        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), EBlocks.ARID_GRASS_BLOCK);
        BlockColorRegistry.register(List.of(BlockTintSources.foliage()), EBlocks.CEDAR_LEAVES);
        BlockColorRegistry.register(List.of(BlockTintSources.foliage()), EBlocks.MAHOGANY_LEAVES);
        BlockColorRegistry.register(List.of(BlockTintSources.foliage()), EBlocks.PINE_LEAVES);

        special("quick_shield", QuickShieldSpecialRenderer.Unbaked.MAP_CODEC);
        special("reinforced_shield", ReinforcedShieldSpecialRenderer.Unbaked.MAP_CODEC);

//        BiomeColorRegistry.register(EBiomes.TUNDRA, BiomeColors.TundraGrassColor::get);
        BiomeColorRegistry.register(EBiomes.SNOWY_TUNDRA, BiomeColors.TundraGrassColor::get);
    }

    private static void special(String name, MapCodec<? extends SpecialModelRenderer.Unbaked<?>> codec) {
        Registry.register(BuiltInClientRegistries.SPECIAL_MODEL_RENDERER_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name), codec);
    }

    private static <T extends BlockEntity, S extends BlockEntityRenderState> void registerRenderer(
            BlockEntityType<? extends T> type, BlockEntityRendererProvider<T, S> provider) {
        Identifier id = Objects.requireNonNull(BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(type));
        ResourceKey<BlockEntityRendererProvider<?, ?>> key =
                ResourceKey.create(ClientRegistries.BLOCK_ENTITY_RENDERER, id);
        Registry.register(BuiltInClientRegistries.BLOCK_ENTITY_RENDERER, key, provider);
    }
}