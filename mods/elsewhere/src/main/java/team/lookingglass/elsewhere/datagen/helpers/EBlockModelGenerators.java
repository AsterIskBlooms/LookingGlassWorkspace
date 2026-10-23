package team.lookingglass.elsewhere.datagen.helpers;

import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.EBlocks;
import team.lookingglass.elsewhere.registry.util.EBlockStateProperties;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EBlockModelGenerators extends BlockModelGenerators {
    public EBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    // Grass Blocks
    public void createAridGrassBlock() {
        Material bottomTexture = TextureMapping.getBlockTexture(EBlocks.ARID_DIRT);
        TextureMapping snowyMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.SNOW))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(EBlocks.ARID_GRASS_BLOCK, "_snow"));

        MultiVariant aridSnowyGrass = createRotatedVariants(
                plainModel(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(EBlocks.ARID_GRASS_BLOCK, "_snow", snowyMapping, modelOutput))
        );

        Identifier aridPlainGrassModel = ModelLocationUtils.getModelLocation(EBlocks.ARID_GRASS_BLOCK);
        MultiVariant aridPlainGrass = createRotatedVariants(plainModel(aridPlainGrassModel));

        createGrassLikeBlock(EBlocks.ARID_GRASS_BLOCK, aridPlainGrass, aridSnowyGrass);
        registerSimpleTintedItemModel(EBlocks.ARID_GRASS_BLOCK, aridPlainGrassModel, new GrassColorSource(1.0F, 0.0F));
    }
    public void createTundraGrassBlock() {
        Material bottomTexture = TextureMapping.getBlockTexture(Blocks.DIRT);

        TextureMapping normalMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(EBlocks.RED_GRASS_BLOCK, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(EBlocks.RED_GRASS_BLOCK, "_side"));

        TextureMapping snowyMapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.SNOW))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(EBlocks.RED_GRASS_BLOCK, "_snow"));

        MultiVariant normalModel = createRotatedVariants(
                plainModel(ModelTemplates.CUBE_BOTTOM_TOP.create(EBlocks.RED_GRASS_BLOCK, normalMapping, this.modelOutput))
        );
        MultiVariant snowyModel = createRotatedVariants(
                plainModel(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(EBlocks.RED_GRASS_BLOCK, "_snow", snowyMapping, this.modelOutput))
        );

        createGrassLikeBlock(EBlocks.RED_GRASS_BLOCK, normalModel, snowyModel);
    }
    public void createSeagrassSandBlock() {
        Material bottomTexture = TextureMapping.getBlockTexture(Blocks.SAND);

        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottomTexture)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(EBlocks.SEAGRASS_SAND, "_top"))
                .copyForced(TextureSlot.TOP, TextureSlot.PARTICLE)
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(EBlocks.SEAGRASS_SAND, "_side"));

        Variant normal = plainModel(
                ModelTemplates.CUBE_BOTTOM_TOP.create(EBlocks.SEAGRASS_SAND, mapping, this.modelOutput)
        );
        blockStateOutput.accept(MultiVariantGenerator.dispatch(EBlocks.SEAGRASS_SAND, createRotatedVariants(normal)));
    }

    // Lanterns
    private static final ModelTemplate WALL_HANGING_LANTERN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "block/template_wall_hanging_lantern")),
            Optional.of("_wall_hanging"), TextureSlot.LANTERN);
    private static final ModelTemplate BIG_LANTERN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "block/template_big_lantern")),
            Optional.empty(), TextureSlot.LANTERN);
    private static final ModelTemplate BIG_HANGING_LANTERN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "block/template_big_hanging_lantern")),
            Optional.of("_hanging"), TextureSlot.LANTERN);

    private static final ModelTemplate BIG_WALL_HANGING_LANTERN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "block/template_big_wall_hanging_lantern")),
            Optional.of("_wall_hanging"), TextureSlot.LANTERN);

    private static VariantMutator wallRotation(final Direction facing) {
        return switch (facing) {
            case EAST -> Y_ROT_90;
            case SOUTH -> Y_ROT_180;
            case WEST -> Y_ROT_270;
            default -> NOP;
        };
    }
    private BlockModelDefinitionGenerator wallLanternStates(final Block block, final Identifier ground, final Identifier hanging, final Identifier wall) {
        return MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(BlockStateProperties.HANGING, EBlockStateProperties.WALL_HANGING, BlockStateProperties.HORIZONTAL_FACING).generate((isHanging, isWall, facing) -> {
            if (isWall) {
                return plainVariant(wall).with(wallRotation(facing));
            }
            return plainVariant(isHanging ? hanging : ground);
        }));
    }
    public void createWallLantern(final Block block, final Block... sameModel) {
        Identifier ground = TexturedModel.LANTERN.create(block, this.modelOutput);
        Identifier hanging = TexturedModel.HANGING_LANTERN.create(block, this.modelOutput);
        Identifier wall = WALL_HANGING_LANTERN.create(block, TexturedModel.LANTERN.get(block).getMapping(), this.modelOutput);
        this.registerSimpleFlatItemModel(block.asItem());
        this.blockStateOutput.accept(wallLanternStates(block, ground, hanging, wall));
        for (Block other : sameModel) {
            this.itemModelOutput.copy(block.asItem(), other.asItem());
            this.blockStateOutput.accept(wallLanternStates(other, ground, hanging, wall));
        }
    }
    public void createWallCopperLantern(final Block unwaxed, final Block waxed) {
        this.createWallLantern(unwaxed, waxed);
    }
    public void createRedstoneLantern(final Block block) {
        TextureMapping lit = new TextureMapping().put(TextureSlot.LANTERN, TextureMapping.getBlockTexture(block));
        TextureMapping unlit = new TextureMapping().put(TextureSlot.LANTERN, TextureMapping.getBlockTexture(block, "_off"));
        Identifier litGround = BIG_LANTERN.create(block, lit, this.modelOutput);
        Identifier litHanging = BIG_HANGING_LANTERN.create(block, lit, this.modelOutput);
        Identifier litWall = BIG_WALL_HANGING_LANTERN.create(block, lit, this.modelOutput);
        Identifier unlitGround = BIG_LANTERN.createWithSuffix(block, "_off", unlit, this.modelOutput);
        Identifier unlitHanging = BIG_HANGING_LANTERN.createWithSuffix(block, "_off", unlit, this.modelOutput);
        Identifier unlitWall = BIG_WALL_HANGING_LANTERN.createWithSuffix(block, "_off", unlit, this.modelOutput);
        this.registerSimpleFlatItemModel(block.asItem());
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(BlockStateProperties.HANGING, EBlockStateProperties.WALL_HANGING, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT).generate((hanging, wall, facing, isLit) -> {
            if (wall) {
                return plainVariant(isLit ? litWall : unlitWall).with(wallRotation(facing));
            }
            if (hanging) {
                return plainVariant(isLit ? litHanging : unlitHanging);
            }
            return plainVariant(isLit ? litGround : unlitGround);
        })));
    }
}