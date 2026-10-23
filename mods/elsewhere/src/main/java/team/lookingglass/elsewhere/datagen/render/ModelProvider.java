package team.lookingglass.elsewhere.datagen.render;

import net.minecraft.client.data.models.model.*;
import net.minecraft.world.level.block.ColorCollection;
import team.lookingglass.elsewhere.datagen.helpers.EBlockModelGenerators;
import team.lookingglass.elsewhere.registry.EBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.level.block.Blocks;
import team.lookingglass.elsewhere.registry.EItems;
import team.lookingglass.elsewhere.registry.equipment.EEquipmentAssets;
import team.lookingglass.elsewhere.registry.equipment.ETrims;
import team.lookingglass.mirrorlib.registry.MBlockModelGenerators;
import team.lookingglass.mirrorlib.trim.gen.TrimModelGenerators;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricPackOutput output) {
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockModelGenerators gen) {
        MBlockModelGenerators mGen = new MBlockModelGenerators(gen.blockStateOutput, gen.itemModelOutput, gen.modelOutput);
        EBlockModelGenerators eGen = new EBlockModelGenerators(gen.blockStateOutput, gen.itemModelOutput, gen.modelOutput);

        // Quartz Brick Fill
        gen.family(Blocks.QUARTZ_BRICKS)
                .stairs(EBlocks.QUARTZ_BRICK_STAIRS)
                .slab(EBlocks.QUARTZ_BRICK_SLAB)
        ;
        // Amethyst-Obsidian Set
        gen.family(Blocks.AMETHYST_BLOCK)
                .stairs(EBlocks.AMETHYST_STAIRS)
                .slab(EBlocks.AMETHYST_SLAB)
        ;
        gen.family(EBlocks.AMETHYST_BRICKS)
                .stairs(EBlocks.AMETHYST_BRICK_STAIRS)
                .slab(EBlocks.AMETHYST_BRICK_SLAB)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_AMETHYST);
        gen.createAxisAlignedPillarBlock(EBlocks.AMETHYST_PILLAR, TexturedModel.COLUMN);

        gen.family(Blocks.OBSIDIAN)
                .stairs(EBlocks.OBSIDIAN_STAIRS)
                .slab(EBlocks.OBSIDIAN_SLAB)
        ;
        gen.family(EBlocks.OBSIDIAN_BRICKS)
                .stairs(EBlocks.OBSIDIAN_BRICK_STAIRS)
                .slab(EBlocks.OBSIDIAN_BRICK_SLAB)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_OBSIDIAN, TexturedModel.COLUMN);
        gen.createAxisAlignedPillarBlock(EBlocks.OBSIDIAN_PILLAR, TexturedModel.COLUMN);

        // Stone Fills
        gen.family(Blocks.STONE)
                .wall(EBlocks.STONE_WALL)
        ;
        gen.family(Blocks.SMOOTH_STONE)
                .stairs(EBlocks.SMOOTH_STONE_STAIRS)
                .wall(EBlocks.SMOOTH_STONE_WALL)
        ;
        gen.family(EBlocks.POLISHED_STONE)
                .stairs(EBlocks.POLISHED_STONE_STAIRS)
                .slab(EBlocks.POLISHED_STONE_SLAB)
                .wall(EBlocks.POLISHED_STONE_WALL)
        ;
        gen.family(EBlocks.STONE_TILES)
                .stairs(EBlocks.STONE_TILE_STAIRS)
                .slab(EBlocks.STONE_TILE_SLAB)
                .wall(EBlocks.STONE_TILE_WALL)
        ;
        gen.family(EBlocks.MOSSY_STONE_TILES)
                .stairs(EBlocks.MOSSY_STONE_TILE_STAIRS)
                .slab(EBlocks.MOSSY_STONE_TILE_SLAB)
                .wall(EBlocks.MOSSY_STONE_TILE_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_STONE);
        gen.createAxisAlignedPillarBlock(EBlocks.STONE_PILLAR, TexturedModel.COLUMN);
        gen.family(Blocks.DEEPSLATE)
                .stairs(EBlocks.DEEPSLATE_STAIRS)
                .slab(EBlocks.DEEPSLATE_SLAB)
                .wall(EBlocks.DEEPSLATE_WALL)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_DEEPSLATE_BRICKS, TexturedModel.COLUMN);
        gen.createAxisAlignedPillarBlock(EBlocks.DEEPSLATE_PILLAR, TexturedModel.COLUMN);

        gen.createAxisAlignedPillarBlock(EBlocks.TUFF_PILLAR, TexturedModel.COLUMN);
        gen.createAxisAlignedPillarBlock(EBlocks.BLACKSTONE_PILLAR, TexturedModel.COLUMN);


        // Stone Sets
        gen.family(Blocks.POLISHED_ANDESITE)
                .wall(EBlocks.POLISHED_ANDESITE_WALL)
        ;
        gen.family(EBlocks.ANDESITE_BRICKS)
                .stairs(EBlocks.ANDESITE_BRICK_STAIRS)
                .slab(EBlocks.ANDESITE_BRICK_SLAB)
                .wall(EBlocks.ANDESITE_BRICK_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_ANDESITE);
        gen.createAxisAlignedPillarBlock(EBlocks.ANDESITE_PILLAR, TexturedModel.COLUMN);

        gen.family(Blocks.POLISHED_GRANITE)
                .wall(EBlocks.POLISHED_GRANITE_WALL)
        ;
        gen.family(EBlocks.GRANITE_BRICKS)
                .stairs(EBlocks.GRANITE_BRICK_STAIRS)
                .slab(EBlocks.GRANITE_BRICK_SLAB)
                .wall(EBlocks.GRANITE_BRICK_WALL)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_GRANITE, TexturedModel.COLUMN);

        gen.family(Blocks.POLISHED_DIORITE)
                .wall(EBlocks.POLISHED_DIORITE_WALL)
        ;
        gen.family(EBlocks.DIORITE_BRICKS)
                .stairs(EBlocks.DIORITE_BRICK_STAIRS)
                .slab(EBlocks.DIORITE_BRICK_SLAB)
                .wall(EBlocks.DIORITE_BRICK_WALL)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_DIORITE, TexturedModel.COLUMN);

        gen.family(Blocks.CALCITE)
                .stairs(EBlocks.CALCITE_STAIRS)
                .slab(EBlocks.CALCITE_SLAB)
                .wall(EBlocks.CALCITE_WALL)
        ;
        gen.family(EBlocks.POLISHED_CALCITE)
                .stairs(EBlocks.POLISHED_CALCITE_STAIRS)
                .slab(EBlocks.POLISHED_CALCITE_SLAB)
                .wall(EBlocks.POLISHED_CALCITE_WALL)
        ;
        gen.family(EBlocks.CALCITE_BRICKS)
                .stairs(EBlocks.CALCITE_BRICK_STAIRS)
                .slab(EBlocks.CALCITE_BRICK_SLAB)
                .wall(EBlocks.CALCITE_BRICK_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_CALCITE);
        gen.createTrivialBlock(EBlocks.CHISELED_CALCITE_BRICKS, TexturedModel.COLUMN);
        gen.createAxisAlignedPillarBlock(EBlocks.CALCITE_PILLAR, TexturedModel.COLUMN);

        gen.family(Blocks.DRIPSTONE_BLOCK)
                .stairs(EBlocks.DRIPSTONE_STAIRS)
                .slab(EBlocks.DRIPSTONE_SLAB)
                .wall(EBlocks.DRIPSTONE_WALL)
        ;
        gen.family(EBlocks.POLISHED_DRIPSTONE)
                .stairs(EBlocks.POLISHED_DRIPSTONE_STAIRS)
                .slab(EBlocks.POLISHED_DRIPSTONE_SLAB)
                .wall(EBlocks.POLISHED_DRIPSTONE_WALL)
        ;
        gen.family(EBlocks.DRIPSTONE_TILES)
                .stairs(EBlocks.DRIPSTONE_TILE_STAIRS)
                .slab(EBlocks.DRIPSTONE_TILE_SLAB)
                .wall(EBlocks.DRIPSTONE_TILE_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_DRIPSTONE_TILES);
        gen.createAxisAlignedPillarBlock(EBlocks.DRIPSTONE_PILLAR, TexturedModel.COLUMN);


        // Sandstone Sets
        gen.family(EBlocks.POLISHED_SANDSTONE)
                .stairs(EBlocks.POLISHED_SANDSTONE_STAIRS)
                .slab(EBlocks.POLISHED_SANDSTONE_SLAB)
                .wall(EBlocks.POLISHED_SANDSTONE_WALL)
        ;
        gen.family(EBlocks.POLISHED_RED_SANDSTONE)
                .stairs(EBlocks.POLISHED_RED_SANDSTONE_STAIRS)
                .slab(EBlocks.POLISHED_RED_SANDSTONE_SLAB)
                .wall(EBlocks.POLISHED_RED_SANDSTONE_WALL)
        ;

        gen.createTrivialBlock(Blocks.CHISELED_SANDSTONE, TexturedModel.COLUMN);
        gen.createTrivialBlock(Blocks.CHISELED_RED_SANDSTONE, TexturedModel.COLUMN);

        gen.family(Blocks.SMOOTH_SANDSTONE)
                .wall(EBlocks.SMOOTH_SANDSTONE_WALL)
        ;
        gen.family(Blocks.SMOOTH_RED_SANDSTONE)
                .wall(EBlocks.SMOOTH_RED_SANDSTONE_WALL)
        ;

        mGen.columnFamily(Blocks.CUT_SANDSTONE)
                .stairs(EBlocks.CUT_SANDSTONE_STAIRS)
                .slab(Blocks.CUT_SANDSTONE_SLAB)
                .wall(EBlocks.CUT_SANDSTONE_WALL)
        ;
        mGen.columnFamily(Blocks.CUT_RED_SANDSTONE)
                .stairs(EBlocks.CUT_RED_SANDSTONE_STAIRS)
                .slab(Blocks.CUT_RED_SANDSTONE_SLAB)
                .wall(EBlocks.CUT_RED_SANDSTONE_WALL)
        ;


        // Prismarine
        gen.family(EBlocks.POLISHED_PRISMARINE)
                .stairs(EBlocks.POLISHED_PRISMARINE_STAIRS)
                .slab(EBlocks.POLISHED_PRISMARINE_SLAB)
                .wall(EBlocks.POLISHED_PRISMARINE_WALL)
        ;
        gen.family(Blocks.PRISMARINE_BRICKS)
                .wall(EBlocks.PRISMARINE_BRICK_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_PRISMARINE);
        gen.createAxisAlignedPillarBlock(EBlocks.PRISMARINE_PILLAR, TexturedModel.COLUMN);

        gen.family(Blocks.DARK_PRISMARINE)
                .wall(EBlocks.DARK_PRISMARINE_WALL)
        ;
        gen.family(EBlocks.POLISHED_DARK_PRISMARINE)
                .stairs(EBlocks.POLISHED_DARK_PRISMARINE_STAIRS)
                .slab(EBlocks.POLISHED_DARK_PRISMARINE_SLAB)
                .wall(EBlocks.POLISHED_DARK_PRISMARINE_WALL)
        ;
        gen.family(EBlocks.DARK_PRISMARINE_SCALES)
                .stairs(EBlocks.DARK_PRISMARINE_SCALE_STAIRS)
                .slab(EBlocks.DARK_PRISMARINE_SCALE_SLAB)
                .wall(EBlocks.DARK_PRISMARINE_SCALE_WALL)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_DARK_PRISMARINE, TexturedModel.COLUMN);

        mGen.axisAlignedPillarFamily(Blocks.BASALT, TexturedModel.COLUMN)
                .slab(EBlocks.BASALT_SLAB)
        ;
        gen.family(Blocks.SMOOTH_BASALT)
                .stairs(EBlocks.SMOOTH_BASALT_STAIRS)
                .slab(EBlocks.SMOOTH_BASALT_SLAB)
                .wall(EBlocks.SMOOTH_BASALT_WALL)
        ;
        mGen.axisAlignedPillarFamily(Blocks.POLISHED_BASALT, TexturedModel.COLUMN)
                .slab(EBlocks.POLISHED_BASALT_SLAB)
        ;
        gen.family(EBlocks.POLISHED_SMOOTH_BASALT)
                .stairs(EBlocks.POLISHED_SMOOTH_BASALT_STAIRS)
                .slab(EBlocks.POLISHED_SMOOTH_BASALT_SLAB)
                .wall(EBlocks.POLISHED_SMOOTH_BASALT_WALL)
        ;
        gen.family(EBlocks.SMOOTH_BASALT_BRICKS)
                .stairs(EBlocks.SMOOTH_BASALT_BRICK_STAIRS)
                .slab(EBlocks.SMOOTH_BASALT_BRICK_SLAB)
                .wall(EBlocks.SMOOTH_BASALT_BRICK_WALL)
        ;
        gen.family(EBlocks.SMOOTH_BASALT_TILES)
                .stairs(EBlocks.SMOOTH_BASALT_TILE_STAIRS)
                .slab(EBlocks.SMOOTH_BASALT_TILE_SLAB)
                .wall(EBlocks.SMOOTH_BASALT_TILE_WALL)
        ;

        gen.family(Blocks.END_STONE)
                .stairs(EBlocks.END_STONE_STAIRS)
                .slab(EBlocks.END_STONE_SLAB)
                .wall(EBlocks.END_STONE_WALL)
        ;
        gen.family(EBlocks.POLISHED_END_STONE)
                .stairs(EBlocks.POLISHED_END_STONE_STAIRS)
                .slab(EBlocks.POLISHED_END_STONE_SLAB)
                .wall(EBlocks.POLISHED_END_STONE_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_END_STONE);
        gen.createAxisAlignedPillarBlock(EBlocks.END_STONE_PILLAR, TexturedModel.COLUMN);

        gen.family(EBlocks.SODALITE)
                .stairs(EBlocks.SODALITE_STAIRS)
                .slab(EBlocks.SODALITE_SLAB)
                .wall(EBlocks.SODALITE_WALL)
        ;
        gen.family(EBlocks.POLISHED_SODALITE)
                .stairs(EBlocks.POLISHED_SODALITE_STAIRS)
                .slab(EBlocks.POLISHED_SODALITE_SLAB)
                .wall(EBlocks.POLISHED_SODALITE_WALL)
        ;
        gen.family(EBlocks.SODALITE_BRICKS)
                .stairs(EBlocks.SODALITE_BRICK_STAIRS)
                .slab(EBlocks.SODALITE_BRICK_SLAB)
                .wall(EBlocks.SODALITE_BRICK_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_SODALITE);
        gen.createTrivialBlock(EBlocks.CHISELED_SODALITE_BRICKS, TexturedModel.COLUMN);
        gen.createAxisAlignedPillarBlock(EBlocks.SODALITE_PILLAR, TexturedModel.COLUMN);

        gen.family(EBlocks.SHALE)
                .stairs(EBlocks.SHALE_STAIRS)
                .slab(EBlocks.SHALE_SLAB)
                .wall(EBlocks.SHALE_WALL)
        ;
        gen.family(EBlocks.POLISHED_SHALE)
                .stairs(EBlocks.POLISHED_SHALE_STAIRS)
                .slab(EBlocks.POLISHED_SHALE_SLAB)
                .wall(EBlocks.POLISHED_SHALE_WALL)
        ;
        gen.family(EBlocks.SHALE_BRICKS)
                .stairs(EBlocks.SHALE_BRICK_STAIRS)
                .slab(EBlocks.SHALE_BRICK_SLAB)
                .wall(EBlocks.SHALE_BRICK_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_SHALE);
        gen.createTrivialBlock(EBlocks.CHISELED_SHALE_BRICKS, TexturedModel.COLUMN);
        gen.createAxisAlignedPillarBlock(EBlocks.SHALE_PILLAR, TexturedModel.COLUMN);

        // Sulfur & Cinnabar
        gen.createAxisAlignedPillarBlock(EBlocks.SULFUR_PILLAR, TexturedModel.COLUMN);
        gen.createAxisAlignedPillarBlock(EBlocks.CINNABAR_PILLAR, TexturedModel.COLUMN);
        gen.createTrivialCube(EBlocks.CINNABAR_COAL_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_COPPER_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_IRON_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_GOLD_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_REDSTONE_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_DIAMOND_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_LAPIS_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_EMERALD_ORE);

        // Netherrack
        mGen.netherrackFamily(Blocks.NETHERRACK)
                .stairs(EBlocks.NETHERRACK_STAIRS)
                .slab(EBlocks.NETHERRACK_SLAB)
                .wall(EBlocks.NETHERRACK_WALL);
        gen.family(EBlocks.POLISHED_NETHERRACK)
                .stairs(EBlocks.POLISHED_NETHERRACK_STAIRS)
                .slab(EBlocks.POLISHED_NETHERRACK_SLAB)
                .wall(EBlocks.POLISHED_NETHERRACK_WALL)
        ;
        gen.family(EBlocks.NETHERRACK_BRICKS)
                .stairs(EBlocks.NETHERRACK_BRICK_STAIRS)
                .slab(EBlocks.NETHERRACK_BRICK_SLAB)
                .wall(EBlocks.NETHERRACK_BRICK_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_NETHERRACK);
        gen.createTrivialCube(EBlocks.CHISELED_NETHERRACK_BRICKS);
        gen.createAxisAlignedPillarBlock(EBlocks.NETHERRACK_PILLAR, TexturedModel.COLUMN);

        // Orpiment
        gen.family(EBlocks.ORPIMENT)
                .stairs(EBlocks.ORPIMENT_STAIRS)
                .slab(EBlocks.ORPIMENT_SLAB)
                .wall(EBlocks.ORPIMENT_WALL)
        ;
        gen.family(EBlocks.POLISHED_ORPIMENT)
                .stairs(EBlocks.POLISHED_ORPIMENT_STAIRS)
                .slab(EBlocks.POLISHED_ORPIMENT_SLAB)
                .wall(EBlocks.POLISHED_ORPIMENT_WALL)
        ;
        gen.family(EBlocks.ORPIMENT_BRICKS)
                .stairs(EBlocks.ORPIMENT_BRICK_STAIRS)
                .slab(EBlocks.ORPIMENT_BRICK_SLAB)
                .wall(EBlocks.ORPIMENT_BRICK_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_ORPIMENT);
        gen.createAxisAlignedPillarBlock(EBlocks.ORPIMENT_PILLAR, TexturedModel.COLUMN);

        // Soul Sandstone
        mGen.columnFamily(EBlocks.SOUL_STONE)
                .stairs(EBlocks.SOUL_STONE_STAIRS)
                .slab(EBlocks.SOUL_STONE_SLAB)
                .wall(EBlocks.SOUL_STONE_WALL)
        ;
        gen.family(EBlocks.SMOOTH_SOUL_STONE)
                .stairs(EBlocks.SMOOTH_SOUL_STONE_STAIRS)
                .slab(EBlocks.SMOOTH_SOUL_STONE_SLAB)
                .wall(EBlocks.SMOOTH_SOUL_STONE_WALL)
        ;
        mGen.columnFamily(EBlocks.CUT_SOUL_STONE)
                .stairs(EBlocks.CUT_SOUL_STONE_STAIRS)
                .slab(EBlocks.CUT_SOUL_STONE_SLAB)
                .wall(EBlocks.CUT_SOUL_STONE_WALL)
        ;
        gen.family(EBlocks.POLISHED_SOUL_STONE)
                .stairs(EBlocks.POLISHED_SOUL_STONE_STAIRS)
                .slab(EBlocks.POLISHED_SOUL_STONE_SLAB)
                .wall(EBlocks.POLISHED_SOUL_STONE_WALL)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_SOUL_STONE, TexturedModel.COLUMN);
        gen.createTrivialCube(EBlocks.SOUL_SILVER_ORE);
        gen.createTrivialCube(EBlocks.SOUL_QUARTZ_ORE);

        gen.createCrossBlockWithDefaultItem(EBlocks.SHORT_SOUL_SPROUT, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createCrossBlockWithDefaultItem(EBlocks.TALL_SOUL_SPROUT, BlockModelGenerators.PlantType.NOT_TINTED);
        mGen.createCrystalClusterWithItem(EBlocks.SOULGLASS_CLUSTER);
        gen.createTrivialCube(EBlocks.RAW_SOULGLASS);
        gen.createTrivialCube(EBlocks.SOULGLASS);

        gen.createRotatedVariantBlock(EBlocks.PINK_SAND);
        mGen.cubeTopFamily(EBlocks.PINK_SANDSTONE)
                .stairs(EBlocks.PINK_SANDSTONE_STAIRS)
                .slab(EBlocks.PINK_SANDSTONE_SLAB)
                .wall(EBlocks.PINK_SANDSTONE_WALL)
        ;
        gen.family(EBlocks.SMOOTH_PINK_SANDSTONE)
                .stairs(EBlocks.SMOOTH_PINK_SANDSTONE_STAIRS)
                .slab(EBlocks.SMOOTH_PINK_SANDSTONE_SLAB)
                .wall(EBlocks.SMOOTH_PINK_SANDSTONE_WALL)
        ;
        mGen.columnFamily(EBlocks.CUT_PINK_SANDSTONE)
                .stairs(EBlocks.CUT_PINK_SANDSTONE_STAIRS)
                .slab(EBlocks.CUT_PINK_SANDSTONE_SLAB)
                .wall(EBlocks.CUT_PINK_SANDSTONE_WALL)
        ;
        gen.family(EBlocks.POLISHED_PINK_SANDSTONE)
                .stairs(EBlocks.POLISHED_PINK_SANDSTONE_STAIRS)
                .slab(EBlocks.POLISHED_PINK_SANDSTONE_SLAB)
                .wall(EBlocks.POLISHED_PINK_SANDSTONE_WALL)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_PINK_SANDSTONE, TexturedModel.COLUMN);


        // Beachstone
        gen.family(EBlocks.BEACHSTONE)
                .stairs(EBlocks.BEACHSTONE_STAIRS)
                .slab(EBlocks.BEACHSTONE_SLAB)
                .wall(EBlocks.BEACHSTONE_WALL)
        ;
        gen.family(EBlocks.POLISHED_BEACHSTONE)
                .stairs(EBlocks.POLISHED_BEACHSTONE_STAIRS)
                .slab(EBlocks.POLISHED_BEACHSTONE_SLAB)
                .wall(EBlocks.POLISHED_BEACHSTONE_WALL)
        ;
        gen.family(EBlocks.BEACHSTONE_BRICKS)
                .stairs(EBlocks.BEACHSTONE_BRICK_STAIRS)
                .slab(EBlocks.BEACHSTONE_BRICK_SLAB)
                .wall(EBlocks.BEACHSTONE_BRICK_WALL)
        ;
        gen.createTrivialBlock(EBlocks.CHISELED_BEACHSTONE, TexturedModel.COLUMN);

        // Limestone
        gen.family(EBlocks.LIMESTONE)
                .stairs(EBlocks.LIMESTONE_STAIRS)
                .slab(EBlocks.LIMESTONE_SLAB)
                .wall(EBlocks.LIMESTONE_WALL)
        ;
        mGen.columnFamily(EBlocks.CUT_LIMESTONE)
                .stairs(EBlocks.CUT_LIMESTONE_STAIRS)
                .slab(EBlocks.CUT_LIMESTONE_SLAB)
                .wall(EBlocks.CUT_LIMESTONE_WALL)
        ;

        gen.createTrivialCube(EBlocks.ROCKWEED_BASALT);

        eGen.createSeagrassSandBlock();

        mGen.generateStalactite(gen, EBlocks.AMETHYST_SPIRE);

        // Mud
        gen.family(Blocks.PACKED_MUD)
                .stairs(EBlocks.PACKED_MUD_STAIRS)
                .slab(EBlocks.PACKED_MUD_SLAB)
                .wall(EBlocks.PACKED_MUD_WALL)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_MUD_BRICKS);
        gen.createRotatedPillarWithHorizontalVariant(EBlocks.MUD_BRICK_PILLAR, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);

        gen.createTrivialCube(EBlocks.MUDSTONE);

        gen.createTrivialBlock(EBlocks.CHISELED_PURPUR, TexturedModel.COLUMN);

        gen.family(EBlocks.LAPIS_BRICKS)
                .stairs(EBlocks.LAPIS_BRICK_STAIRS)
                .slab(EBlocks.LAPIS_BRICK_SLAB)
        ;

        // Wood Sets
        gen.family(EBlocks.OAK_MOSAIC).stairs(EBlocks.OAK_MOSAIC_STAIRS).slab(EBlocks.OAK_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.OAK_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.OAK_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.DARK_OAK_MOSAIC).stairs(EBlocks.DARK_OAK_MOSAIC_STAIRS).slab(EBlocks.DARK_OAK_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.DARK_OAK_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.DARK_OAK_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.PALE_OAK_MOSAIC).stairs(EBlocks.PALE_OAK_MOSAIC_STAIRS).slab(EBlocks.PALE_OAK_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.PALE_OAK_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.PALE_OAK_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.BIRCH_MOSAIC).stairs(EBlocks.BIRCH_MOSAIC_STAIRS).slab(EBlocks.BIRCH_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.BIRCH_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.BIRCH_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.SPRUCE_MOSAIC).stairs(EBlocks.SPRUCE_MOSAIC_STAIRS).slab(EBlocks.SPRUCE_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.SPRUCE_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.SPRUCE_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.JUNGLE_MOSAIC).stairs(EBlocks.JUNGLE_MOSAIC_STAIRS).slab(EBlocks.JUNGLE_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.JUNGLE_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.JUNGLE_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.ACACIA_MOSAIC).stairs(EBlocks.ACACIA_MOSAIC_STAIRS).slab(EBlocks.ACACIA_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.ACACIA_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.ACACIA_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.CHERRY_MOSAIC).stairs(EBlocks.CHERRY_MOSAIC_STAIRS).slab(EBlocks.CHERRY_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.CHERRY_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.CHERRY_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.MANGROVE_MOSAIC).stairs(EBlocks.MANGROVE_MOSAIC_STAIRS).slab(EBlocks.MANGROVE_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.MANGROVE_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.MANGROVE_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.CRIMSON_MOSAIC).stairs(EBlocks.CRIMSON_MOSAIC_STAIRS).slab(EBlocks.CRIMSON_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.CRIMSON_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.CRIMSON_TRIM, TexturedModel.CUBE);

        gen.family(EBlocks.WARPED_MOSAIC).stairs(EBlocks.WARPED_MOSAIC_STAIRS).slab(EBlocks.WARPED_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.WARPED_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.WARPED_TRIM, TexturedModel.CUBE);

        gen.createRotatedPillarWithHorizontalVariant(EBlocks.BAMBOO_STEM, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        gen.createRotatedPillarWithHorizontalVariant(EBlocks.STRIPPED_BAMBOO_STEM, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        mGen.createWoodenBoards(EBlocks.BAMBOO_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.BAMBOO_TRIM, TexturedModel.CUBE);


        mGen.createPlantWithUniquePottedTexture(EBlocks.BLUEBONNET, EBlocks.POTTED_BLUEBONNET, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createPlantWithDefaultItem(EBlocks.RED_HIBISCUS, EBlocks.POTTED_RED_HIBISCUS, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.ORANGE_HIBISCUS, EBlocks.POTTED_ORANGE_HIBISCUS, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.YELLOW_HIBISCUS, EBlocks.POTTED_YELLOW_HIBISCUS, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.BLUE_HIBISCUS, EBlocks.POTTED_BLUE_HIBISCUS, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.PURPLE_HIBISCUS, EBlocks.POTTED_PURPLE_HIBISCUS, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.PINK_HIBISCUS, EBlocks.POTTED_PINK_HIBISCUS, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.WHITE_HIBISCUS, EBlocks.POTTED_WHITE_HIBISCUS, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createPlantWithDefaultItem(EBlocks.MAGENTA_CONEFLOWER, EBlocks.POTTED_MAGENTA_CONEFLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.YELLOW_CONEFLOWER, EBlocks.POTTED_YELLOW_CONEFLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(EBlocks.ROSE, EBlocks.POTTED_ROSE, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createDoublePlantWithDefaultItem(EBlocks.IRONWEED, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createDoublePlantWithDefaultItem(EBlocks.CATTAILS, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createCrossBlockWithDefaultItem(EBlocks.CAVE_WEED, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createCrossBlockWithDefaultItem(EBlocks.GLEAMSHROOM, BlockModelGenerators.PlantType.NOT_TINTED);

        mGen.createRotatedVariantColumn(EBlocks.ARID_DIRT);
        eGen.createAridGrassBlock();
        eGen.createTundraGrassBlock();
        gen.createCrossBlockWithDefaultItem(EBlocks.SHORT_RED_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createDoublePlantWithDefaultItem(EBlocks.TALL_RED_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createTrivialCube(EBlocks.GILDED_BIRCH_LEAVES);

        gen.createFullAndCarpetBlocks(EBlocks.RUSTY_MOSS_BLOCK, EBlocks.RUSTY_MOSS_CARPET);

        gen.createCrossBlockWithDefaultItem(EBlocks.SHORT_MOSS_SPOROPHYTE, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createDoublePlantWithDefaultItem(EBlocks.TALL_MOSS_SPOROPHYTE, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createCrossBlockWithDefaultItem(EBlocks.SHORT_RUSTY_MOSS_SPOROPHYTE, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createDoublePlantWithDefaultItem(EBlocks.TALL_RUSTY_MOSS_SPOROPHYTE, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createCrossBlockWithDefaultItem(EBlocks.SHORT_PALE_MOSS_SPOROPHYTE, BlockModelGenerators.PlantType.EMISSIVE_NOT_TINTED);
        mGen.createEmissiveDoublePlantWithDefaultItem(EBlocks.TALL_PALE_MOSS_SPOROPHYTE, BlockModelGenerators.PlantType.EMISSIVE_NOT_TINTED);

        gen.woodProvider(EBlocks.AZALEA_LOG).logWithHorizontal(EBlocks.AZALEA_LOG).wood(EBlocks.AZALEA_WOOD);
        gen.woodProvider(EBlocks.STRIPPED_AZALEA_LOG).logWithHorizontal(EBlocks.STRIPPED_AZALEA_LOG).wood(EBlocks.STRIPPED_AZALEA_WOOD);
        gen.family(EBlocks.AZALEA_PLANKS)
                .stairs(EBlocks.AZALEA_STAIRS)
                .slab(EBlocks.AZALEA_SLAB)
                .fence(EBlocks.AZALEA_FENCE)
                .fenceGate(EBlocks.AZALEA_FENCE_GATE)
                .pressurePlate(EBlocks.AZALEA_PRESSURE_PLATE)
                .button(EBlocks.AZALEA_BUTTON)
        ;
        gen.createDoor(EBlocks.AZALEA_DOOR);
        gen.createOrientableTrapdoor(EBlocks.AZALEA_TRAPDOOR);
        mGen.createSignModel(EBlocks.AZALEA_SIGN, EBlocks.AZALEA_WALL_SIGN, EBlocks.AZALEA_PLANKS);
        mGen.createHangingSignModel(EBlocks.AZALEA_HANGING_SIGN, EBlocks.AZALEA_WALL_HANGING_SIGN, EBlocks.STRIPPED_AZALEA_LOG);
        gen.family(EBlocks.AZALEA_MOSAIC).stairs(EBlocks.AZALEA_MOSAIC_STAIRS).slab(EBlocks.AZALEA_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.AZALEA_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.AZALEA_TRIM, TexturedModel.CUBE);
        gen.createShelf(EBlocks.AZALEA_SHELF, EBlocks.STRIPPED_AZALEA_LOG);

        gen.woodProvider(EBlocks.CEDAR_LOG).logWithHorizontal(EBlocks.CEDAR_LOG).wood(EBlocks.CEDAR_WOOD);
        gen.woodProvider(EBlocks.STRIPPED_CEDAR_LOG).logWithHorizontal(EBlocks.STRIPPED_CEDAR_LOG).wood(EBlocks.STRIPPED_CEDAR_WOOD);
        gen.family(EBlocks.CEDAR_PLANKS)
                .stairs(EBlocks.CEDAR_STAIRS)
                .slab(EBlocks.CEDAR_SLAB)
                .fence(EBlocks.CEDAR_FENCE)
                .fenceGate(EBlocks.CEDAR_FENCE_GATE)
                .pressurePlate(EBlocks.CEDAR_PRESSURE_PLATE)
                .button(EBlocks.CEDAR_BUTTON)
        ;
        gen.createDoor(EBlocks.CEDAR_DOOR);
        gen.createOrientableTrapdoor(EBlocks.CEDAR_TRAPDOOR);
        mGen.createSignModel(EBlocks.CEDAR_SIGN, EBlocks.CEDAR_WALL_SIGN, EBlocks.CEDAR_PLANKS);
        mGen.createHangingSignModel(EBlocks.CEDAR_HANGING_SIGN, EBlocks.CEDAR_WALL_HANGING_SIGN, EBlocks.STRIPPED_CEDAR_LOG);
        gen.family(EBlocks.CEDAR_MOSAIC).stairs(EBlocks.CEDAR_MOSAIC_STAIRS).slab(EBlocks.CEDAR_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.CEDAR_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.CEDAR_TRIM, TexturedModel.CUBE);
        gen.createShelf(EBlocks.CEDAR_SHELF, EBlocks.STRIPPED_CEDAR_LOG);
        gen.createTintedLeaves(EBlocks.CEDAR_LEAVES, TexturedModel.LEAVES, 0x71AD4E);
        gen.createPlantWithDefaultItem(EBlocks.CEDAR_SAPLING, EBlocks.POTTED_CEDAR_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.woodProvider(EBlocks.MAHOGANY_LOG).logWithHorizontal(EBlocks.MAHOGANY_LOG).wood(EBlocks.MAHOGANY_WOOD);
        gen.woodProvider(EBlocks.STRIPPED_MAHOGANY_LOG).logWithHorizontal(EBlocks.STRIPPED_MAHOGANY_LOG).wood(EBlocks.STRIPPED_MAHOGANY_WOOD);
        gen.family(EBlocks.MAHOGANY_PLANKS)
                .stairs(EBlocks.MAHOGANY_STAIRS)
                .slab(EBlocks.MAHOGANY_SLAB)
                .fence(EBlocks.MAHOGANY_FENCE)
                .fenceGate(EBlocks.MAHOGANY_FENCE_GATE)
                .pressurePlate(EBlocks.MAHOGANY_PRESSURE_PLATE)
                .button(EBlocks.MAHOGANY_BUTTON)
        ;
        gen.createDoor(EBlocks.MAHOGANY_DOOR);
        gen.createOrientableTrapdoor(EBlocks.MAHOGANY_TRAPDOOR);
        mGen.createSignModel(EBlocks.MAHOGANY_SIGN, EBlocks.MAHOGANY_WALL_SIGN, EBlocks.MAHOGANY_PLANKS);
        mGen.createHangingSignModel(EBlocks.MAHOGANY_HANGING_SIGN, EBlocks.MAHOGANY_WALL_HANGING_SIGN, EBlocks.STRIPPED_MAHOGANY_LOG);
        gen.family(EBlocks.MAHOGANY_MOSAIC).stairs(EBlocks.MAHOGANY_MOSAIC_STAIRS).slab(EBlocks.MAHOGANY_MOSAIC_SLAB);
        mGen.createWoodenBoards(EBlocks.MAHOGANY_BOARDS);
        mGen.createSimpleHorizontallyRotatedBlock(EBlocks.MAHOGANY_TRIM, TexturedModel.CUBE);
        gen.createShelf(EBlocks.MAHOGANY_SHELF, EBlocks.STRIPPED_MAHOGANY_LOG);
        gen.createTintedLeaves(EBlocks.MAHOGANY_LEAVES, TexturedModel.LEAVES, 0x62F728);
        gen.createPlantWithDefaultItem(EBlocks.MAHOGANY_SAPLING, EBlocks.POTTED_MAHOGANY_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createTrivialBlock(EBlocks.LATERITE, TexturedModel.COLUMN);

        gen.createTrivialCube(EBlocks.BEJEWELED_CALCITE);

        gen.createTrivialCube(EBlocks.TIN_ORE);
        gen.createTrivialCube(EBlocks.DEEPSLATE_TIN_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_TIN_ORE);
        gen.createTrivialCube(EBlocks.RAW_TIN_BLOCK);
        gen.createTrivialCube(EBlocks.TIN_BLOCK);

        gen.createTrivialCube(EBlocks.BRONZE_BLOCK);

        gen.createTrivialCube(EBlocks.SILVER_ORE);
        gen.createTrivialCube(EBlocks.DEEPSLATE_SILVER_ORE);
        gen.createTrivialCube(EBlocks.CINNABAR_SILVER_ORE);
        gen.createTrivialCube(EBlocks.RAW_SILVER_BLOCK);
        gen.createTrivialCube(EBlocks.SILVER_BLOCK);
        gen.family(EBlocks.POLISHED_SILVER)
                .stairs(EBlocks.POLISHED_SILVER_STAIRS)
                .slab(EBlocks.POLISHED_SILVER_SLAB)
        ;
        gen.family(EBlocks.CUT_SILVER)
                .stairs(EBlocks.CUT_SILVER_STAIRS)
                .slab(EBlocks.CUT_SILVER_SLAB)
        ;
        gen.createRotatedPillarWithHorizontalVariant(EBlocks.SILVER_PILLAR, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        gen.createBarsAndItem(EBlocks.SILVER_BARS);
        mGen.createChain(EBlocks.SILVER_CHAIN);

        mGen.createChain(EBlocks.GOLD_CHAIN);

        gen.family(EBlocks.WROUGHT_IRON_BLOCK)
                .stairs(EBlocks.WROUGHT_IRON_STAIRS)
                .slab(EBlocks.WROUGHT_IRON_SLAB)
        ;
        gen.family(EBlocks.CUT_WROUGHT_IRON)
                .stairs(EBlocks.CUT_WROUGHT_IRON_STAIRS)
                .slab(EBlocks.CUT_WROUGHT_IRON_SLAB)
        ;
        gen.createTrivialCube(EBlocks.WROUGHT_IRON_GRATE);
        gen.createBarsAndItem(EBlocks.WROUGHT_IRON_BARS);

        gen.family(EBlocks.CUT_TIN)
                .stairs(EBlocks.CUT_TIN_STAIRS)
                .slab(EBlocks.CUT_TIN_SLAB)
        ;
        gen.createTrivialCube(EBlocks.CHISELED_TIN);
        gen.createBarsAndItem(EBlocks.TIN_BARS);

        gen.createTrivialCube(EBlocks.SOULSTEEL_BLOCK);
        gen.createTrivialCube(EBlocks.ELECTRUM_BLOCK);
        gen.createTrivialCube(EBlocks.URANIUM_BLOCK);

        gen.family(Blocks.TERRACOTTA).stairs(EBlocks.TERRACOTTA_STAIRS).slab(EBlocks.TERRACOTTA_SLAB);
        gen.family(EBlocks.TERRACOTTA_BRICKS).stairs(EBlocks.TERRACOTTA_BRICK_STAIRS).slab(EBlocks.TERRACOTTA_BRICK_SLAB);
        gen.family(EBlocks.TERRACOTTA_BRICK_TILES).stairs(EBlocks.TERRACOTTA_BRICK_TILE_STAIRS).slab(EBlocks.TERRACOTTA_BRICK_TILE_SLAB);

        ColorCollection.VALUES.forEach(color ->
                gen.family(Blocks.DYED_TERRACOTTA.pick(color))
                        .stairs(EBlocks.DYED_TERRACOTTA_STAIRS.pick(color))
                        .slab(EBlocks.DYED_TERRACOTTA_SLAB.pick(color)));
        ColorCollection.VALUES.forEach(color ->
                gen.family(EBlocks.DYED_TERRACOTTA_BRICKS.pick(color))
                        .stairs(EBlocks.DYED_TERRACOTTA_BRICK_STAIRS.pick(color))
                        .slab(EBlocks.DYED_TERRACOTTA_BRICK_SLAB.pick(color)));
        ColorCollection.VALUES.forEach(color ->
                gen.family(EBlocks.DYED_TERRACOTTA_BRICK_TILES.pick(color))
                        .stairs(EBlocks.DYED_TERRACOTTA_BRICK_TILE_STAIRS.pick(color))
                        .slab(EBlocks.DYED_TERRACOTTA_BRICK_TILE_SLAB.pick(color)));


        eGen.createWallLantern(Blocks.LANTERN);
        eGen.createWallLantern(Blocks.SOUL_LANTERN);
        Blocks.COPPER_LANTERN.zipUnwaxedWaxed(eGen::createWallCopperLantern);
        eGen.createRedstoneLantern(EBlocks.REDSTONE_LANTERN);
    }



    @Override
    public void generateItemModels(ItemModelGenerators gen) {
        TrimModelGenerators armorGen = new TrimModelGenerators(gen.itemModelOutput, gen.modelOutput);

        gen.generateFlatItem(EBlocks.AMETHYST_SPIRE.asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);

        gen.generateFlatItem(EBlocks.PEBBLE.asItem(), ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.DIAMOND_NUGGET, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.EMERALD_NUGGET, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.RAW_TIN, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.TIN_INGOT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.TIN_NUGGET, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.BRONZE_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.BRONZE_ALLOY, ModelTemplates.FLAT_ITEM);
        armorGen.generateTrimmableArmorSet(ETrims.KIT, EEquipmentAssets.BRONZE,
                EItems.BRONZE_HELMET, EItems.BRONZE_CHESTPLATE, EItems.BRONZE_LEGGINGS, EItems.BRONZE_BOOTS, false);
        gen.generateFlatItem(EItems.BRONZE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.BRONZE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateSpear(EItems.BRONZE_SPEAR);
        gen.generateFlatItem(EItems.BRONZE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.BRONZE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.BRONZE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        gen.generateFlatItem(EItems.RAW_SILVER, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.SILVER_INGOT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.SILVER_NUGGET, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.SOULSTEEL_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.SOULSTEEL_ALLOY, ModelTemplates.FLAT_ITEM);
        armorGen.generateTrimmableArmorSet(ETrims.KIT, EEquipmentAssets.SOULSTEEL,
                EItems.SOULSTEEL_HELMET, EItems.SOULSTEEL_CHESTPLATE, EItems.SOULSTEEL_LEGGINGS, EItems.SOULSTEEL_BOOTS, false);
        gen.generateFlatItem(EItems.SOULSTEEL_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.SOULSTEEL_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateSpear(EItems.SOULSTEEL_SPEAR);
        gen.generateFlatItem(EItems.SOULSTEEL_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.SOULSTEEL_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.SOULSTEEL_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        gen.generateFlatItem(EItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.ELECTRUM_ALLOY, ModelTemplates.FLAT_ITEM);
        armorGen.generateTrimmableArmorSet(ETrims.KIT, EEquipmentAssets.ELECTRUM,
                EItems.ELECTRUM_HELMET, EItems.ELECTRUM_CHESTPLATE, EItems.ELECTRUM_LEGGINGS, EItems.ELECTRUM_BOOTS, false);
        gen.generateFlatItem(EItems.ELECTRUM_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.ELECTRUM_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateSpear(EItems.ELECTRUM_SPEAR);
        gen.generateFlatItem(EItems.ELECTRUM_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.ELECTRUM_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        gen.generateFlatItem(EItems.ELECTRUM_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        armorGen.generateTargetTrimModels(ETrims.KIT);

        gen.generateFlatItem(EItems.URANIUM, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.URANIUM_SHARD, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.SOULGLASS_SHARD, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.MUSIC_DISC_FOX, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.DUNGEON_KEY, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.OMINOUS_DUNGEON_KEY, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.WEREWOLF_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.VANGUARD_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.SUBSLIME_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.SWEEPER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.MUD_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.LOST_SOUL_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.TROLL_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.GRAVEKEEPER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.WHISTLER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.VANGUARD_MASK, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.PERCH_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.RAW_PERCH, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.COOKED_PERCH, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.SHINING_PERCH, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.PERCH_BUCKET, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.AZALEA_SIGN, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.AZALEA_HANGING_SIGN, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.AZALEA_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.AZALEA_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.CEDAR_SIGN, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.CEDAR_HANGING_SIGN, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.CEDAR_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.CEDAR_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(EItems.MAHOGANY_SIGN, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.MAHOGANY_HANGING_SIGN, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.MAHOGANY_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(EItems.MAHOGANY_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }



    @Override
    public String getName() {
        return "BlockModelProvider";
    }
}
