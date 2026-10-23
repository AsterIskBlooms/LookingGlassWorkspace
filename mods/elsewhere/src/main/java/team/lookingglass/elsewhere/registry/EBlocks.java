package team.lookingglass.elsewhere.registry;

import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.sounds.BlockSoundSets;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.blocktypes.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import team.lookingglass.elsewhere.registry.render.EParticles;
import team.lookingglass.elsewhere.registry.sets.EBlockSets;
import team.lookingglass.elsewhere.registry.sets.ETreeGrowers;
import team.lookingglass.elsewhere.registry.sets.EWoodTypes;
import team.lookingglass.elsewhere.registry.sound.EBlockSoundSets;
import team.lookingglass.elsewhere.registry.tags.EBlockTags;
import team.lookingglass.mirrorlib.registry.BlockSpreadRegistry;
import team.lookingglass.elsewhere.worldgen.features.EFeatures;
import team.lookingglass.mirrorlib.registry.BlockRegistry;

import java.util.Optional;

public interface EBlocks {


    // Quartz Brick Fill
    Block QUARTZ_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
    Block QUARTZ_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS);

    // Amethyst-Obsidian Set
    Block AMETHYST_STAIRS = BlockRegistry.register(EBlockItemIds.AMETHYST_STAIRS, p -> new AmethystStairBlock(Blocks.AMETHYST_BLOCK.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
    );
    Block AMETHYST_SLAB = BlockRegistry.register(EBlockItemIds.AMETHYST_SLAB, AmethystSlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
    );
    Block AMETHYST_BRICKS = BlockRegistry.register(EBlockItemIds.AMETHYST_BRICKS, AmethystBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
    );
    Block AMETHYST_BRICK_STAIRS = BlockRegistry.register(EBlockItemIds.AMETHYST_BRICK_STAIRS, p -> new AmethystStairBlock(Blocks.AMETHYST_BLOCK.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
    );
    Block AMETHYST_BRICK_SLAB = BlockRegistry.register(EBlockItemIds.AMETHYST_BRICK_SLAB, AmethystSlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
    );
    Block AMETHYST_PILLAR = BlockRegistry.register(EBlockItemIds.AMETHYST_PILLAR, AmethystPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
    );
    Block CHISELED_AMETHYST = BlockRegistry.register(EBlockItemIds.CHISELED_AMETHYST, AmethystBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
    );
    Block OBSIDIAN_STAIRS = BlockRegistry.registerStair(EBlockItemIds.OBSIDIAN_STAIRS, Blocks.OBSIDIAN);
    Block OBSIDIAN_SLAB = BlockRegistry.registerSlab(EBlockItemIds.OBSIDIAN_SLAB, Blocks.OBSIDIAN);
    Block OBSIDIAN_BRICKS = BlockRegistry.register(EBlockItemIds.OBSIDIAN_BRICKS, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(5.0F, 1200.0F).sound(BlockSoundSets.DEEPSLATE_BRICKS).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()
    );
    Block OBSIDIAN_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.OBSIDIAN_BRICK_STAIRS, OBSIDIAN_BRICKS);
    Block OBSIDIAN_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.OBSIDIAN_BRICK_SLAB, OBSIDIAN_BRICKS);
    Block OBSIDIAN_PILLAR = BlockRegistry.register(EBlockItemIds.OBSIDIAN_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(5.0F, 1200.0F).sound(BlockSoundSets.DEEPSLATE_BRICKS).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()
    );
    Block CHISELED_OBSIDIAN = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_OBSIDIAN, OBSIDIAN_BRICKS);



    // STOOOOOONE !!!
    Block STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.STONE_WALL, Blocks.STONE);
    Block SMOOTH_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SMOOTH_STONE_STAIRS, Blocks.SMOOTH_STONE);
    Block SMOOTH_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE);

    Block POLISHED_STONE = BlockRegistry.register(EBlockItemIds.POLISHED_STONE, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
    );
    Block POLISHED_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_STONE_STAIRS, POLISHED_STONE);
    Block POLISHED_STONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_STONE_SLAB, POLISHED_STONE);
    Block POLISHED_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_STONE_WALL, POLISHED_STONE);

    Block STONE_TILES = BlockRegistry.registerCopyBlock(EBlockItemIds.STONE_TILES, Blocks.STONE_BRICKS);
    Block STONE_TILE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.STONE_TILE_STAIRS, STONE_TILES);
    Block STONE_TILE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.STONE_TILE_SLAB, STONE_TILES);
    Block STONE_TILE_WALL = BlockRegistry.registerWall(EBlockItemIds.STONE_TILE_WALL, STONE_TILES);
    Block MOSSY_STONE_TILES = BlockRegistry.registerCopyBlock(EBlockItemIds.MOSSY_STONE_TILES, STONE_TILES);
    Block MOSSY_STONE_TILE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.MOSSY_STONE_TILE_STAIRS, STONE_TILES);
    Block MOSSY_STONE_TILE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.MOSSY_STONE_TILE_SLAB, STONE_TILES);
    Block MOSSY_STONE_TILE_WALL = BlockRegistry.registerWall(EBlockItemIds.MOSSY_STONE_TILE_WALL, STONE_TILES);

    Block CHISELED_STONE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_STONE, Blocks.STONE_BRICKS);
    Block STONE_PILLAR = BlockRegistry.register(EBlockItemIds.STONE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
    );

    Block DEEPSLATE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.DEEPSLATE_STAIRS, Blocks.DEEPSLATE);
    Block DEEPSLATE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.DEEPSLATE_SLAB, Blocks.DEEPSLATE);
    Block DEEPSLATE_WALL = BlockRegistry.registerWall(EBlockItemIds.DEEPSLATE_WALL, Blocks.DEEPSLATE);

    Block CHISELED_DEEPSLATE_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS);
    Block DEEPSLATE_PILLAR = BlockRegistry.register(EBlockItemIds.DEEPSLATE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );

    Block TUFF_PILLAR = BlockRegistry.register(EBlockItemIds.TUFF_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_TUFF)
    );

    Block BLACKSTONE_PILLAR = BlockRegistry.register(EBlockItemIds.BLACKSTONE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
    );

    Block POLISHED_ANDESITE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
    Block ANDESITE_BRICKS = BlockRegistry.register(EBlockItemIds.ANDESITE_BRICKS, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)
    );
    Block ANDESITE_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.ANDESITE_BRICK_STAIRS, ANDESITE_BRICKS);
    Block ANDESITE_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.ANDESITE_BRICK_SLAB, ANDESITE_BRICKS);
    Block ANDESITE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.ANDESITE_BRICK_WALL, ANDESITE_BRICKS);
    Block CHISELED_ANDESITE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_ANDESITE, ANDESITE_BRICKS);
    Block ANDESITE_PILLAR = BlockRegistry.register(EBlockItemIds.ANDESITE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(ANDESITE_BRICKS)
    );

    Block POLISHED_GRANITE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
    Block GRANITE_BRICKS = BlockRegistry.register(EBlockItemIds.GRANITE_BRICKS, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE)
    );
    Block GRANITE_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.GRANITE_BRICK_STAIRS, GRANITE_BRICKS);
    Block GRANITE_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.GRANITE_BRICK_SLAB, GRANITE_BRICKS);
    Block GRANITE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.GRANITE_BRICK_WALL, GRANITE_BRICKS);
    Block CHISELED_GRANITE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_GRANITE, GRANITE_BRICKS);

    Block POLISHED_DIORITE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
    Block DIORITE_BRICKS = BlockRegistry.register(EBlockItemIds.DIORITE_BRICKS, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE)
    );
    Block DIORITE_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.DIORITE_BRICK_STAIRS, DIORITE_BRICKS);
    Block DIORITE_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.DIORITE_BRICK_SLAB, DIORITE_BRICKS);
    Block DIORITE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.DIORITE_BRICK_WALL, DIORITE_BRICKS);
    Block CHISELED_DIORITE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_DIORITE, DIORITE_BRICKS);

    Block BEJEWELED_CALCITE = BlockRegistry.registerCopyBlock(EBlockItemIds.BEJEWELED_CALCITE, Blocks.CALCITE);
    Block CALCITE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CALCITE_STAIRS, Blocks.CALCITE);
    Block CALCITE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CALCITE_SLAB, Blocks.CALCITE);
    Block CALCITE_WALL = BlockRegistry.registerWall(EBlockItemIds.CALCITE_WALL, Blocks.CALCITE);
    Block POLISHED_CALCITE = BlockRegistry.register(EBlockItemIds.POLISHED_CALCITE, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)
    );
    Block POLISHED_CALCITE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_CALCITE_STAIRS, POLISHED_CALCITE);
    Block POLISHED_CALCITE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_CALCITE_SLAB, POLISHED_CALCITE);
    Block POLISHED_CALCITE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_CALCITE_WALL, POLISHED_CALCITE);
    Block CALCITE_BRICKS = BlockRegistry.register(EBlockItemIds.CALCITE_BRICKS, Block::new,
            BlockBehaviour.Properties.ofFullCopy(POLISHED_CALCITE)
    );
    Block CALCITE_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CALCITE_BRICK_STAIRS, CALCITE_BRICKS);
    Block CALCITE_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CALCITE_BRICK_SLAB, CALCITE_BRICKS);
    Block CALCITE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.CALCITE_BRICK_WALL, CALCITE_BRICKS);
    Block CHISELED_CALCITE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_CALCITE, CALCITE_BRICKS);
    Block CHISELED_CALCITE_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_CALCITE_BRICKS, CALCITE_BRICKS);
    Block CALCITE_PILLAR = BlockRegistry.register(EBlockItemIds.CALCITE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(CALCITE_BRICKS)
    );

    Block DRIPSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.DRIPSTONE_STAIRS, Blocks.DRIPSTONE_BLOCK);
    Block DRIPSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.DRIPSTONE_SLAB, Blocks.DRIPSTONE_BLOCK);
    Block DRIPSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.DRIPSTONE_WALL, Blocks.DRIPSTONE_BLOCK);
    Block POLISHED_DRIPSTONE = BlockRegistry.register(EBlockItemIds.POLISHED_DRIPSTONE, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)
    );
    Block POLISHED_DRIPSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_DRIPSTONE_STAIRS, POLISHED_DRIPSTONE);
    Block POLISHED_DRIPSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_DRIPSTONE_SLAB, POLISHED_DRIPSTONE);
    Block POLISHED_DRIPSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_DRIPSTONE_WALL, POLISHED_DRIPSTONE);
    Block DRIPSTONE_TILES = BlockRegistry.registerCopyBlock(EBlockItemIds.DRIPSTONE_TILES, POLISHED_DRIPSTONE);
    Block DRIPSTONE_TILE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.DRIPSTONE_TILE_STAIRS, DRIPSTONE_TILES);
    Block DRIPSTONE_TILE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.DRIPSTONE_TILE_SLAB, DRIPSTONE_TILES);
    Block DRIPSTONE_TILE_WALL = BlockRegistry.registerWall(EBlockItemIds.DRIPSTONE_TILE_WALL, DRIPSTONE_TILES);
    Block CHISELED_DRIPSTONE_TILES = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_DRIPSTONE_TILES, DRIPSTONE_TILES);
    Block DRIPSTONE_PILLAR = BlockRegistry.register(EBlockItemIds.DRIPSTONE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(DRIPSTONE_TILES)
    );



    // Sandstone Fills
    Block POLISHED_SANDSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_SANDSTONE, Blocks.CUT_SANDSTONE);
    Block POLISHED_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_SANDSTONE_STAIRS, POLISHED_SANDSTONE);
    Block POLISHED_SANDSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_SANDSTONE_SLAB, POLISHED_SANDSTONE);
    Block POLISHED_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_SANDSTONE_WALL, POLISHED_SANDSTONE);

    Block SMOOTH_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_SANDSTONE_WALL, Blocks.SMOOTH_SANDSTONE);

    Block CUT_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_SANDSTONE_STAIRS, Blocks.CUT_SANDSTONE);
    Block CUT_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.CUT_SANDSTONE_WALL, Blocks.CUT_SANDSTONE);

    Block POLISHED_RED_SANDSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_RED_SANDSTONE, Blocks.CUT_SANDSTONE);
    Block POLISHED_RED_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_RED_SANDSTONE_STAIRS, POLISHED_RED_SANDSTONE);
    Block POLISHED_RED_SANDSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_RED_SANDSTONE_SLAB, POLISHED_RED_SANDSTONE);
    Block POLISHED_RED_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_RED_SANDSTONE_WALL, POLISHED_RED_SANDSTONE);

    Block SMOOTH_RED_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_RED_SANDSTONE_WALL, Blocks.SMOOTH_RED_SANDSTONE);

    Block CUT_RED_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_RED_SANDSTONE_STAIRS, Blocks.CUT_RED_SANDSTONE);
    Block CUT_RED_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.CUT_RED_SANDSTONE_WALL, Blocks.CUT_RED_SANDSTONE);



    // Prismarine
    Block POLISHED_PRISMARINE = BlockRegistry.register(EBlockItemIds.POLISHED_PRISMARINE, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE)
    );
    Block POLISHED_PRISMARINE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_PRISMARINE_STAIRS, POLISHED_PRISMARINE);
    Block POLISHED_PRISMARINE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_PRISMARINE_SLAB, POLISHED_PRISMARINE);
    Block POLISHED_PRISMARINE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_PRISMARINE_WALL, POLISHED_PRISMARINE);
    Block PRISMARINE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.PRISMARINE_BRICK_WALL, Blocks.PRISMARINE_BRICKS);
    Block CHISELED_PRISMARINE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_PRISMARINE, Blocks.PRISMARINE_BRICKS);
    Block PRISMARINE_PILLAR = BlockRegistry.register(EBlockItemIds.PRISMARINE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)
    );

    Block DARK_PRISMARINE_WALL = BlockRegistry.registerWall(EBlockItemIds.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE);
    Block POLISHED_DARK_PRISMARINE = BlockRegistry.register(EBlockItemIds.POLISHED_DARK_PRISMARINE, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)
    );
    Block POLISHED_DARK_PRISMARINE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_DARK_PRISMARINE_STAIRS, POLISHED_DARK_PRISMARINE);
    Block POLISHED_DARK_PRISMARINE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_DARK_PRISMARINE_SLAB, POLISHED_DARK_PRISMARINE);
    Block POLISHED_DARK_PRISMARINE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_DARK_PRISMARINE_WALL, POLISHED_DARK_PRISMARINE);
    Block DARK_PRISMARINE_SCALES = BlockRegistry.register(EBlockItemIds.DARK_PRISMARINE_SCALES, Block::new,
            BlockBehaviour.Properties.ofFullCopy(POLISHED_DARK_PRISMARINE).sound(EBlockSoundSets.STONE_TILES)
    );
    Block DARK_PRISMARINE_SCALE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.DARK_PRISMARINE_SCALE_STAIRS, DARK_PRISMARINE_SCALES);
    Block DARK_PRISMARINE_SCALE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.DARK_PRISMARINE_SCALE_SLAB, DARK_PRISMARINE_SCALES);
    Block DARK_PRISMARINE_SCALE_WALL = BlockRegistry.registerWall(EBlockItemIds.DARK_PRISMARINE_SCALE_WALL, DARK_PRISMARINE_SCALES);
    Block CHISELED_DARK_PRISMARINE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_DARK_PRISMARINE, DARK_PRISMARINE_SCALES);


    // Basalt & Smooth Basalt
    Block BASALT_SLAB = BlockRegistry.registerSlab(EBlockItemIds.BASALT_SLAB, Blocks.BASALT);
    Block POLISHED_BASALT_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_BASALT_SLAB, Blocks.POLISHED_BASALT);

    Block SMOOTH_BASALT_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
    Block SMOOTH_BASALT_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT);
    Block SMOOTH_BASALT_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_BASALT_WALL, Blocks.SMOOTH_BASALT);
    Block POLISHED_SMOOTH_BASALT = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_SMOOTH_BASALT, Blocks.SMOOTH_BASALT);
    Block POLISHED_SMOOTH_BASALT_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
    Block POLISHED_SMOOTH_BASALT_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT);
    Block POLISHED_SMOOTH_BASALT_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_SMOOTH_BASALT_WALL, Blocks.SMOOTH_BASALT);
    Block SMOOTH_BASALT_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.SMOOTH_BASALT_BRICKS, Blocks.POLISHED_BASALT);
    Block SMOOTH_BASALT_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SMOOTH_BASALT_BRICK_STAIRS, SMOOTH_BASALT_BRICKS);
    Block SMOOTH_BASALT_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SMOOTH_BASALT_BRICK_SLAB, SMOOTH_BASALT_BRICKS);
    Block SMOOTH_BASALT_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_BASALT_BRICK_WALL, SMOOTH_BASALT_BRICKS);
    Block SMOOTH_BASALT_TILES = BlockRegistry.registerCopyBlock(EBlockItemIds.SMOOTH_BASALT_TILES, SMOOTH_BASALT_BRICKS);
    Block SMOOTH_BASALT_TILE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SMOOTH_BASALT_TILE_STAIRS, SMOOTH_BASALT_TILES);
    Block SMOOTH_BASALT_TILE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SMOOTH_BASALT_TILE_SLAB, SMOOTH_BASALT_TILES);
    Block SMOOTH_BASALT_TILE_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_BASALT_TILE_WALL, SMOOTH_BASALT_TILES);


    // End Stone
    Block POLISHED_END_STONE = BlockRegistry.register(EBlockItemIds.POLISHED_END_STONE, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
    );
    Block END_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.END_STONE_STAIRS, POLISHED_END_STONE);
    Block END_STONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.END_STONE_SLAB, POLISHED_END_STONE);
    Block END_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.END_STONE_WALL, POLISHED_END_STONE);
    Block POLISHED_END_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_END_STONE_STAIRS, POLISHED_END_STONE);
    Block POLISHED_END_STONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_END_STONE_SLAB, POLISHED_END_STONE);
    Block POLISHED_END_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_END_STONE_WALL, POLISHED_END_STONE);
    Block CHISELED_END_STONE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_END_STONE, Blocks.END_STONE_BRICKS);
    Block END_STONE_PILLAR = BlockRegistry.register(EBlockItemIds.END_STONE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS)
    );


    // Sodalite
    Block SODALITE = BlockRegistry.register(EBlockItemIds.SODALITE, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0F, 6.0F).sound(BlockSoundSets.TUFF)
    );
    Block SODALITE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SODALITE_STAIRS, SODALITE);
    Block SODALITE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SODALITE_SLAB, SODALITE);
    Block SODALITE_WALL = BlockRegistry.registerWall(EBlockItemIds.SODALITE_WALL, SODALITE);
    Block POLISHED_SODALITE = BlockRegistry.register(EBlockItemIds.POLISHED_SODALITE, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0F, 6.0F).sound(BlockSoundSets.POLISHED_TUFF)
    );
    Block POLISHED_SODALITE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_SODALITE_STAIRS, POLISHED_SODALITE);
    Block POLISHED_SODALITE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_SODALITE_SLAB, POLISHED_SODALITE);
    Block POLISHED_SODALITE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_SODALITE_WALL, POLISHED_SODALITE);
    Block SODALITE_BRICKS = BlockRegistry.register(EBlockItemIds.SODALITE_BRICKS, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.0F, 6.0F).sound(BlockSoundSets.TUFF_BRICKS)
    );
    Block SODALITE_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SODALITE_BRICK_STAIRS, SODALITE_BRICKS);
    Block SODALITE_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SODALITE_BRICK_SLAB, SODALITE_BRICKS);
    Block SODALITE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.SODALITE_BRICK_WALL, SODALITE_BRICKS);
    Block CHISELED_SODALITE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_SODALITE, POLISHED_SODALITE);
    Block CHISELED_SODALITE_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_SODALITE_BRICKS, SODALITE_BRICKS);
    Block SODALITE_PILLAR = BlockRegistry.register(EBlockItemIds.SODALITE_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SODALITE_BRICKS)
    );

    // SHAAAALE
    Block SHALE = BlockRegistry.register(EBlockItemIds.SHALE, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(EBlockSoundSets.SHALE)
    );
    Block SHALE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SHALE_STAIRS, SHALE);
    Block SHALE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SHALE_SLAB, SHALE);
    Block SHALE_WALL = BlockRegistry.registerWall(EBlockItemIds.SHALE_WALL, SHALE);
    Block POLISHED_SHALE = BlockRegistry.register(EBlockItemIds.POLISHED_SHALE, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(EBlockSoundSets.SHALE)
    );
    Block POLISHED_SHALE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_SHALE_STAIRS, POLISHED_SHALE);
    Block POLISHED_SHALE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_SHALE_SLAB, POLISHED_SHALE);
    Block POLISHED_SHALE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_SHALE_WALL, POLISHED_SHALE);
    Block SHALE_BRICKS = BlockRegistry.register(EBlockItemIds.SHALE_BRICKS, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(EBlockSoundSets.SHALE_BRICKS)
    );
    Block SHALE_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SHALE_BRICK_STAIRS, SHALE_BRICKS);
    Block SHALE_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SHALE_BRICK_SLAB, SHALE_BRICKS);
    Block SHALE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.SHALE_BRICK_WALL, SHALE_BRICKS);
    Block CHISELED_SHALE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_SHALE, POLISHED_SHALE);
    Block CHISELED_SHALE_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_SHALE_BRICKS, SHALE_BRICKS);
    Block SHALE_PILLAR = BlockRegistry.register(EBlockItemIds.SHALE_PILLAR, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(SHALE_BRICKS));

    Block SILVER_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.SILVER_ORE, Blocks.COAL_ORE);
    Block DEEPSLATE_SILVER_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.DEEPSLATE_SILVER_ORE, Blocks.DEEPSLATE_COAL_ORE);
    Block CINNABAR_SILVER_ORE = BlockRegistry.register(EBlockItemIds.CINNABAR_SILVER_ORE, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(BlockSoundSets.CINNABAR));
    Block TIN_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.TIN_ORE, Blocks.COAL_ORE);
    Block DEEPSLATE_TIN_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.DEEPSLATE_TIN_ORE, Blocks.DEEPSLATE_COAL_ORE);
    Block CINNABAR_TIN_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.CINNABAR_TIN_ORE, CINNABAR_SILVER_ORE);

    Block TIN_BLOCK = BlockRegistry.register(EBlockItemIds.TIN_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(BlockSoundSets.IRON));
    Block RAW_TIN_BLOCK = BlockRegistry.register(EBlockItemIds.RAW_TIN_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresCorrectToolForDrops().strength(4.0F, 6.0F).sound(BlockSoundSets.STONE));
    Block BRONZE_BLOCK = BlockRegistry.register(EBlockItemIds.BRONZE_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(BlockSoundSets.COPPER));
    Block SILVER_BLOCK = BlockRegistry.register(EBlockItemIds.SILVER_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(BlockSoundSets.COPPER));
    Block RAW_SILVER_BLOCK = BlockRegistry.register(EBlockItemIds.RAW_SILVER_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_CYAN).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(BlockSoundSets.STONE));
    Block SOULSTEEL_BLOCK = BlockRegistry.register(EBlockItemIds.SOULSTEEL_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresCorrectToolForDrops().strength(10.0F, 1200.0F).sound(BlockSoundSets.NETHERITE_BLOCK));
    Block ELECTRUM_BLOCK = BlockRegistry.register(EBlockItemIds.ELECTRUM_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.GOLD).requiresCorrectToolForDrops().strength(7.0F, 12.0F).sound(BlockSoundSets.COPPER));

    Block URANIUM_BLOCK = BlockRegistry.register(EBlockItemIds.URANIUM_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(BlockSoundSets.LODESTONE));


    Block SULFUR_PILLAR = BlockRegistry.register(EBlockItemIds.SULFUR_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_SULFUR));
    Block CINNABAR_PILLAR = BlockRegistry.register(EBlockItemIds.CINNABAR_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_CINNABAR));
    Block CINNABAR_COAL_ORE = BlockRegistry.register(EBlockItemIds.CINNABAR_COAL_ORE, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(BlockSoundSets.CINNABAR));
    Block CINNABAR_COPPER_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.CINNABAR_COPPER_ORE, CINNABAR_COAL_ORE);
    Block CINNABAR_IRON_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.CINNABAR_IRON_ORE, CINNABAR_COAL_ORE);
    Block CINNABAR_GOLD_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.CINNABAR_GOLD_ORE, CINNABAR_COAL_ORE);
    Block CINNABAR_REDSTONE_ORE = BlockRegistry.register(EBlockItemIds.CINNABAR_REDSTONE_ORE, p -> new RedStoneOreBlock(UniformInt.of(1, 5), p),
            BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().randomTicks().lightLevel(Blocks.litBlockEmission(9))
                    .strength(3.0F, 6.0F).sound(BlockSoundSets.CINNABAR));
    Block CINNABAR_DIAMOND_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.CINNABAR_DIAMOND_ORE, CINNABAR_COAL_ORE);
    Block CINNABAR_LAPIS_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.CINNABAR_LAPIS_ORE, CINNABAR_COAL_ORE);
    Block CINNABAR_EMERALD_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.CINNABAR_EMERALD_ORE, CINNABAR_COAL_ORE);

    // Netherrack
    Block NETHERRACK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.NETHERRACK_STAIRS, Blocks.NETHERRACK);
    Block NETHERRACK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.NETHERRACK_SLAB, Blocks.NETHERRACK);
    Block NETHERRACK_WALL = BlockRegistry.registerWall(EBlockItemIds.NETHERRACK_WALL, Blocks.NETHERRACK);
    Block POLISHED_NETHERRACK = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_NETHERRACK, Blocks.NETHERRACK);
    Block POLISHED_NETHERRACK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_NETHERRACK_STAIRS, POLISHED_NETHERRACK);
    Block POLISHED_NETHERRACK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_NETHERRACK_SLAB, POLISHED_NETHERRACK);
    Block POLISHED_NETHERRACK_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_NETHERRACK_WALL, POLISHED_NETHERRACK);
    Block NETHERRACK_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.NETHERRACK_BRICKS, POLISHED_NETHERRACK);
    Block NETHERRACK_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.NETHERRACK_BRICK_STAIRS, NETHERRACK_BRICKS);
    Block NETHERRACK_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.NETHERRACK_BRICK_SLAB, NETHERRACK_BRICKS);
    Block NETHERRACK_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.NETHERRACK_BRICK_WALL, NETHERRACK_BRICKS);
    Block CHISELED_NETHERRACK = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_NETHERRACK, NETHERRACK_BRICKS);
    Block CHISELED_NETHERRACK_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_NETHERRACK_BRICKS, NETHERRACK_BRICKS);
    Block NETHERRACK_PILLAR = BlockRegistry.register(EBlockItemIds.NETHERRACK_PILLAR, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(NETHERRACK_BRICKS));

    // Orpiment
    Block ORPIMENT = BlockRegistry.register(EBlockItemIds.ORPIMENT, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(BlockSoundSets.CALCITE)
    );
    Block ORPIMENT_STAIRS = BlockRegistry.registerStair(EBlockItemIds.ORPIMENT_STAIRS, ORPIMENT);
    Block ORPIMENT_SLAB = BlockRegistry.registerSlab(EBlockItemIds.ORPIMENT_SLAB, ORPIMENT);
    Block ORPIMENT_WALL = BlockRegistry.registerWall(EBlockItemIds.ORPIMENT_WALL, ORPIMENT);
    Block POLISHED_ORPIMENT = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_ORPIMENT, ORPIMENT);
    Block POLISHED_ORPIMENT_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_ORPIMENT_STAIRS, POLISHED_ORPIMENT);
    Block POLISHED_ORPIMENT_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_ORPIMENT_SLAB, POLISHED_ORPIMENT);
    Block POLISHED_ORPIMENT_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_ORPIMENT_WALL, POLISHED_ORPIMENT);
    Block ORPIMENT_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.ORPIMENT_BRICKS, POLISHED_ORPIMENT);
    Block ORPIMENT_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.ORPIMENT_BRICK_STAIRS, ORPIMENT_BRICKS);
    Block ORPIMENT_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.ORPIMENT_BRICK_SLAB, ORPIMENT_BRICKS);
    Block ORPIMENT_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.ORPIMENT_BRICK_WALL, ORPIMENT_BRICKS);
    Block CHISELED_ORPIMENT = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_ORPIMENT, POLISHED_ORPIMENT);
    Block ORPIMENT_PILLAR = BlockRegistry.register(EBlockItemIds.ORPIMENT_PILLAR, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(POLISHED_ORPIMENT));

    // Soul Sandstone
    Block SOUL_STONE = BlockRegistry.register(EBlockItemIds.SOUL_STONE, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()
            .strength(2.5F, 6.0F).sound(BlockSoundSets.BASALT));
    Block SOUL_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SOUL_STONE_STAIRS, SOUL_STONE);
    Block SOUL_STONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SOUL_STONE_SLAB, SOUL_STONE);
    Block SOUL_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.SOUL_STONE_WALL, SOUL_STONE);
    Block SMOOTH_SOUL_STONE = BlockRegistry.registerCopyBlock(EBlockItemIds.SMOOTH_SOUL_STONE, SOUL_STONE);
    Block SMOOTH_SOUL_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SMOOTH_SOUL_STONE_STAIRS, SMOOTH_SOUL_STONE);
    Block SMOOTH_SOUL_STONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SMOOTH_SOUL_STONE_SLAB, SMOOTH_SOUL_STONE);
    Block SMOOTH_SOUL_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_SOUL_STONE_WALL, SMOOTH_SOUL_STONE);
    Block CUT_SOUL_STONE = BlockRegistry.registerCopyBlock(EBlockItemIds.CUT_SOUL_STONE, SOUL_STONE);
    Block CUT_SOUL_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_SOUL_STONE_STAIRS, CUT_SOUL_STONE);
    Block CUT_SOUL_STONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CUT_SOUL_STONE_SLAB, CUT_SOUL_STONE);
    Block CUT_SOUL_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.CUT_SOUL_STONE_WALL, CUT_SOUL_STONE);
    Block POLISHED_SOUL_STONE = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_SOUL_STONE, SOUL_STONE);
    Block POLISHED_SOUL_STONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_SOUL_STONE_STAIRS, POLISHED_SOUL_STONE);
    Block POLISHED_SOUL_STONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_SOUL_STONE_SLAB, POLISHED_SOUL_STONE);
    Block POLISHED_SOUL_STONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_SOUL_STONE_WALL, POLISHED_SOUL_STONE);
    Block CHISELED_SOUL_STONE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_SOUL_STONE, SOUL_STONE);
    Block SOUL_SILVER_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.SOUL_SILVER_ORE, SOUL_STONE);
    Block SOUL_QUARTZ_ORE = BlockRegistry.registerCopyBlock(EBlockItemIds.SOUL_QUARTZ_ORE, SOUL_STONE);

    Block SHORT_SOUL_SPROUT = BlockRegistry.register(EBlockItemIds.SHORT_SOUL_SPROUT, p -> new NetherRootsBlock(EBlockTags.NETHER_SOIL, p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_BROWN));
    Block TALL_SOUL_SPROUT = BlockRegistry.register(EBlockItemIds.TALL_SOUL_SPROUT, p -> new NetherRootsBlock(EBlockTags.NETHER_SOIL, p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_BROWN));
    Block SOULGLASS_CLUSTER = BlockRegistry.register(EBlockItemIds.SOULGLASS_CLUSTER, SoulglassClusterBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ICE_CRYSTAL)
            .mapColor(MapColor.COLOR_LIGHT_BLUE).sound(BlockSoundSets.ICE_CRYSTAL).lightLevel(_ -> 5).emissiveRendering(_ -> true));
    Block RAW_SOULGLASS = BlockRegistry.register(EBlockItemIds.RAW_SOULGLASS, TransparentBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
            .mapColor(MapColor.COLOR_LIGHT_BLUE).sound(BlockSoundSets.BLUE_ICE).lightLevel(_ -> 3).emissiveRendering(_ -> true).friction(0.95F));
    Block SOULGLASS = BlockRegistry.register(EBlockItemIds.SOULGLASS, TransparentBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
            .mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(_ -> 5).emissiveRendering(_ -> true));

    // Singular Purpur Block
    Block CHISELED_PURPUR = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_PURPUR, Blocks.PURPUR_BLOCK);

    Block AMETHYST_SPIRE = BlockRegistry.register(EBlockItemIds.AMETHYST_SPIRE, p -> new AmethystSpireBlock(Blocks.AMETHYST_BLOCK.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).randomTicks());

    Block PACKED_MUD_STAIRS = BlockRegistry.registerStair(EBlockItemIds.PACKED_MUD_STAIRS, Blocks.PACKED_MUD);
    Block PACKED_MUD_SLAB = BlockRegistry.registerSlab(EBlockItemIds.PACKED_MUD_SLAB, Blocks.PACKED_MUD);
    Block PACKED_MUD_WALL = BlockRegistry.registerWall(EBlockItemIds.PACKED_MUD_WALL, Blocks.PACKED_MUD);
    Block CHISELED_MUD_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_MUD_BRICKS, Blocks.MUD_BRICKS);
    Block MUD_BRICK_PILLAR = BlockRegistry.register(EBlockItemIds.MUD_BRICK_PILLAR, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS));

    // Wood Blocks
    Block OAK_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.OAK_MOSAIC, Blocks.OAK_PLANKS);
    Block OAK_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.OAK_MOSAIC_STAIRS, OAK_MOSAIC);
    Block OAK_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.OAK_MOSAIC_SLAB, OAK_MOSAIC);
    Block OAK_BOARDS = BlockRegistry.register(EBlockItemIds.OAK_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    Block OAK_TRIM = BlockRegistry.register(EBlockItemIds.OAK_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    Block DARK_OAK_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.DARK_OAK_MOSAIC, Blocks.DARK_OAK_PLANKS);
    Block DARK_OAK_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.DARK_OAK_MOSAIC_STAIRS, DARK_OAK_MOSAIC);
    Block DARK_OAK_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.DARK_OAK_MOSAIC_SLAB, DARK_OAK_MOSAIC);
    Block DARK_OAK_BOARDS = BlockRegistry.register(EBlockItemIds.DARK_OAK_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));
    Block DARK_OAK_TRIM = BlockRegistry.register(EBlockItemIds.DARK_OAK_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));

    Block PALE_OAK_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.PALE_OAK_MOSAIC, Blocks.PALE_OAK_PLANKS);
    Block PALE_OAK_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.PALE_OAK_MOSAIC_STAIRS, PALE_OAK_MOSAIC);
    Block PALE_OAK_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.PALE_OAK_MOSAIC_SLAB, PALE_OAK_MOSAIC);
    Block PALE_OAK_BOARDS = BlockRegistry.register(EBlockItemIds.PALE_OAK_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS));
    Block PALE_OAK_TRIM = BlockRegistry.register(EBlockItemIds.PALE_OAK_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS));

    Block BIRCH_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.BIRCH_MOSAIC, Blocks.BIRCH_PLANKS);
    Block BIRCH_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.BIRCH_MOSAIC_STAIRS, BIRCH_MOSAIC);
    Block BIRCH_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.BIRCH_MOSAIC_SLAB, BIRCH_MOSAIC);
    Block BIRCH_BOARDS = BlockRegistry.register(EBlockItemIds.BIRCH_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
    Block BIRCH_TRIM = BlockRegistry.register(EBlockItemIds.BIRCH_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));

    Block SPRUCE_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.SPRUCE_MOSAIC, Blocks.SPRUCE_PLANKS);
    Block SPRUCE_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SPRUCE_MOSAIC_STAIRS, SPRUCE_MOSAIC);
    Block SPRUCE_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SPRUCE_MOSAIC_SLAB, SPRUCE_MOSAIC);
    Block SPRUCE_BOARDS = BlockRegistry.register(EBlockItemIds.SPRUCE_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    Block SPRUCE_TRIM = BlockRegistry.register(EBlockItemIds.SPRUCE_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));

    Block JUNGLE_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.JUNGLE_MOSAIC, Blocks.JUNGLE_PLANKS);
    Block JUNGLE_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.JUNGLE_MOSAIC_STAIRS, JUNGLE_MOSAIC);
    Block JUNGLE_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.JUNGLE_MOSAIC_SLAB, JUNGLE_MOSAIC);
    Block JUNGLE_BOARDS = BlockRegistry.register(EBlockItemIds.JUNGLE_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
    Block JUNGLE_TRIM = BlockRegistry.register(EBlockItemIds.JUNGLE_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));

    Block ACACIA_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.ACACIA_MOSAIC, Blocks.ACACIA_PLANKS);
    Block ACACIA_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.ACACIA_MOSAIC_STAIRS, ACACIA_MOSAIC);
    Block ACACIA_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.ACACIA_MOSAIC_SLAB, ACACIA_MOSAIC);
    Block ACACIA_BOARDS = BlockRegistry.register(EBlockItemIds.ACACIA_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
    Block ACACIA_TRIM = BlockRegistry.register(EBlockItemIds.ACACIA_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));

    Block CHERRY_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.CHERRY_MOSAIC, Blocks.CHERRY_PLANKS);
    Block CHERRY_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CHERRY_MOSAIC_STAIRS, CHERRY_MOSAIC);
    Block CHERRY_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CHERRY_MOSAIC_SLAB, CHERRY_MOSAIC);
    Block CHERRY_BOARDS = BlockRegistry.register(EBlockItemIds.CHERRY_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));
    Block CHERRY_TRIM = BlockRegistry.register(EBlockItemIds.CHERRY_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));

    Block MANGROVE_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.MANGROVE_MOSAIC, Blocks.MANGROVE_PLANKS);
    Block MANGROVE_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.MANGROVE_MOSAIC_STAIRS, MANGROVE_MOSAIC);
    Block MANGROVE_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.MANGROVE_MOSAIC_SLAB, MANGROVE_MOSAIC);
    Block MANGROVE_BOARDS = BlockRegistry.register(EBlockItemIds.MANGROVE_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    Block MANGROVE_TRIM = BlockRegistry.register(EBlockItemIds.MANGROVE_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));

    Block CRIMSON_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.CRIMSON_MOSAIC, Blocks.CRIMSON_PLANKS);
    Block CRIMSON_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CRIMSON_MOSAIC_STAIRS, CRIMSON_MOSAIC);
    Block CRIMSON_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CRIMSON_MOSAIC_SLAB, CRIMSON_MOSAIC);
    Block CRIMSON_BOARDS = BlockRegistry.register(EBlockItemIds.CRIMSON_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS));
    Block CRIMSON_TRIM = BlockRegistry.register(EBlockItemIds.CRIMSON_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS));

    Block WARPED_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.WARPED_MOSAIC, Blocks.WARPED_PLANKS);
    Block WARPED_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.WARPED_MOSAIC_STAIRS, WARPED_MOSAIC);
    Block WARPED_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.WARPED_MOSAIC_SLAB, WARPED_MOSAIC);
    Block WARPED_BOARDS = BlockRegistry.register(EBlockItemIds.WARPED_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS));
    Block WARPED_TRIM = BlockRegistry.register(EBlockItemIds.WARPED_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS));

    Block BAMBOO_STEM = BlockRegistry.register(EBlockItemIds.BAMBOO_STEM, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_BLOCK));
    Block STRIPPED_BAMBOO_STEM = BlockRegistry.register(EBlockItemIds.STRIPPED_BAMBOO_STEM, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_BAMBOO_BLOCK));
    Block BAMBOO_BOARDS = BlockRegistry.register(EBlockItemIds.BAMBOO_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));
    Block BAMBOO_TRIM = BlockRegistry.register(EBlockItemIds.BAMBOO_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));

    // Hibiscus
    Block RED_HIBISCUS = BlockRegistry.register(EBlockItemIds.RED_HIBISCUS, p -> new FlowerBlock(MobEffects.HASTE, 10.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block ORANGE_HIBISCUS = BlockRegistry.register(EBlockItemIds.ORANGE_HIBISCUS, p -> new FlowerBlock(MobEffects.HASTE, 10.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block YELLOW_HIBISCUS = BlockRegistry.register(EBlockItemIds.YELLOW_HIBISCUS, p -> new FlowerBlock(MobEffects.HASTE, 10.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block BLUE_HIBISCUS = BlockRegistry.register(EBlockItemIds.BLUE_HIBISCUS, p -> new FlowerBlock(MobEffects.HASTE, 10.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block PURPLE_HIBISCUS = BlockRegistry.register(EBlockItemIds.PURPLE_HIBISCUS, p -> new FlowerBlock(MobEffects.HASTE, 10.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block PINK_HIBISCUS = BlockRegistry.register(EBlockItemIds.PINK_HIBISCUS, p -> new FlowerBlock(MobEffects.HASTE, 10.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block WHITE_HIBISCUS = BlockRegistry.register(EBlockItemIds.WHITE_HIBISCUS, p -> new FlowerBlock(MobEffects.HASTE, 10.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );

    Block POTTED_RED_HIBISCUS = BlockRegistry.register(blockKey("potted_red_hibiscus"), p -> new FlowerPotBlock(RED_HIBISCUS, p),
            Blocks.flowerPotProperties()
    );
    Block POTTED_ORANGE_HIBISCUS = BlockRegistry.register(blockKey("potted_orange_hibiscus"), p -> new FlowerPotBlock(ORANGE_HIBISCUS, p),
            Blocks.flowerPotProperties()
    );
    Block POTTED_YELLOW_HIBISCUS = BlockRegistry.register(blockKey("potted_yellow_hibiscus"), p -> new FlowerPotBlock(YELLOW_HIBISCUS, p),
            Blocks.flowerPotProperties()
    );
    Block POTTED_BLUE_HIBISCUS = BlockRegistry.register(blockKey("potted_blue_hibiscus"), p -> new FlowerPotBlock(BLUE_HIBISCUS, p),
            Blocks.flowerPotProperties()
    );
    Block POTTED_PURPLE_HIBISCUS = BlockRegistry.register(blockKey("potted_purple_hibiscus"), p -> new FlowerPotBlock(PURPLE_HIBISCUS, p),
            Blocks.flowerPotProperties()
    );
    Block POTTED_PINK_HIBISCUS = BlockRegistry.register(blockKey("potted_pink_hibiscus"), p -> new FlowerPotBlock(PINK_HIBISCUS, p),
            Blocks.flowerPotProperties()
    );
    Block POTTED_WHITE_HIBISCUS = BlockRegistry.register(blockKey("potted_white_hibiscus"), p -> new FlowerPotBlock(WHITE_HIBISCUS, p),
            Blocks.flowerPotProperties()
    );

    // Natural blocks
    Block BLUEBONNET = BlockRegistry.register(EBlockItemIds.BLUEBONNET, p -> new FlowerBlock(MobEffects.RESISTANCE, 5.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block POTTED_BLUEBONNET = BlockRegistry.register(blockKey("potted_bluebonnet"), p -> new FlowerPotBlock(BLUEBONNET, p),
            Blocks.flowerPotProperties()
    );

    Block MAGENTA_CONEFLOWER = BlockRegistry.register(EBlockItemIds.MAGENTA_CONEFLOWER, p -> new FlowerBlock(MobEffects.POISON, 3.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block YELLOW_CONEFLOWER = BlockRegistry.register(EBlockItemIds.YELLOW_CONEFLOWER, p -> new FlowerBlock(MobEffects.POISON, 3.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block POTTED_MAGENTA_CONEFLOWER = BlockRegistry.register(blockKey("potted_magenta_coneflower"), p -> new FlowerPotBlock(MAGENTA_CONEFLOWER, p),
            Blocks.flowerPotProperties()
    );
    Block POTTED_YELLOW_CONEFLOWER = BlockRegistry.register(blockKey("potted_yellow_coneflower"), p -> new FlowerPotBlock(YELLOW_CONEFLOWER, p),
            Blocks.flowerPotProperties()
    );

    Block IRONWEED = BlockRegistry.register(EBlockItemIds.IRONWEED, TallFlowerBlock::new,
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );

    Block ROSE = BlockRegistry.register(EBlockItemIds.ROSE, p -> new FlowerBlock(MobEffects.RESISTANCE, 5.0F, p),
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS).mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block POTTED_ROSE = BlockRegistry.register(blockKey("potted_rose"), p -> new FlowerPotBlock(ROSE, p),
            Blocks.flowerPotProperties()
    );

    Block PEBBLE = BlockRegistry.register(EBlockItemIds.PEBBLE, PebbleBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instabreak().sound(BlockSoundSets.STONE).noOcclusion()
    );

    Block LATERITE = BlockRegistry.register(EBlockItemIds.LATERITE, Block::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.5F).sound(BlockSoundSets.WART_BLOCK)
    );

    Block RED_GRASS_BLOCK = BlockRegistry.register(EBlockItemIds.RED_GRASS_BLOCK, RedGrassBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5F).sound(BlockSoundSets.GRASS).randomTicks()
    );
    Block SHORT_RED_GRASS = BlockRegistry.register(EBlockItemIds.SHORT_RED_GRASS, BushBlock::new,
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).mapColor(MapColor.TERRACOTTA_RED).sound(BlockSoundSets.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block TALL_RED_GRASS = BlockRegistry.register(EBlockItemIds.TALL_RED_GRASS, DoublePlantBlock::new,
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).mapColor(MapColor.TERRACOTTA_RED).sound(BlockSoundSets.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
    );





    Block RUSTY_MOSS_BLOCK = BlockRegistry.register(EBlockItemIds.RUSTY_MOSS_BLOCK, p -> new BonemealableFeaturePlacerBlock(EFeatures.RUSTY_MOSS_PATCH_BONEMEAL, p),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.1F).sound(BlockSoundSets.MOSS)
    );
    Block RUSTY_MOSS_CARPET = BlockRegistry.register(EBlockItemIds.RUSTY_MOSS_CARPET, CarpetBlock::new,
            BlockBehaviour.Properties.ofFullCopy(RUSTY_MOSS_BLOCK).sound(BlockSoundSets.MOSS_CARPET)
    );

    // Sporophytes
    Block SHORT_MOSS_SPOROPHYTE = BlockRegistry.register(EBlockItemIds.SHORT_MOSS_SPOROPHYTE, BushBlock::new,
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).mapColor(MapColor.PLANT).sound(BlockSoundSets.MOSS_CARPET).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block TALL_MOSS_SPOROPHYTE = BlockRegistry.register(EBlockItemIds.TALL_MOSS_SPOROPHYTE, DoublePlantBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SHORT_MOSS_SPOROPHYTE)
    );

    Block SHORT_RUSTY_MOSS_SPOROPHYTE = BlockRegistry.register(EBlockItemIds.SHORT_RUSTY_MOSS_SPOROPHYTE, BushBlock::new,
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).mapColor(MapColor.COLOR_ORANGE).sound(BlockSoundSets.MOSS_CARPET).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block TALL_RUSTY_MOSS_SPOROPHYTE = BlockRegistry.register(EBlockItemIds.TALL_RUSTY_MOSS_SPOROPHYTE, DoublePlantBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SHORT_RUSTY_MOSS_SPOROPHYTE)
    );

    Block SHORT_PALE_MOSS_SPOROPHYTE = BlockRegistry.register(EBlockItemIds.SHORT_PALE_MOSS_SPOROPHYTE, BushBlock::new,
            BlockBehaviour.Properties.of().noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).mapColor(MapColor.COLOR_LIGHT_GRAY).sound(BlockSoundSets.MOSS_CARPET).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(_ -> 5)
    );
    Block TALL_PALE_MOSS_SPOROPHYTE = BlockRegistry.register(EBlockItemIds.TALL_PALE_MOSS_SPOROPHYTE, DoublePlantBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SHORT_PALE_MOSS_SPOROPHYTE)
    );

    Block GILDED_BIRCH_LEAVES = BlockRegistry.register(EBlockItemIds.GILDED_BIRCH_LEAVES,
            p -> new UntintedParticleLeavesBlock(0.01F, EParticles.GILDED_BIRCH_LEAVES, Optional.empty(), p),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.2F, 0.2F).sound(BlockSoundSets.AZALEA_LEAVES).noOcclusion().ignitedByLava()
    );

    Block SEAGRASS_SAND = BlockRegistry.register(EBlockItemIds.SEAGRASS_SAND, p -> new SandBlock(new ColorRGBA(14406560), p),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F, 0.5F).sound(BlockSoundSets.WET_GRASS)
    );



    // Azalea
    Block AZALEA_LOG = BlockRegistry.register(EBlockItemIds.AZALEA_LOG, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).strength(2.0F, 2.0F).sound(BlockSoundSets.CHERRY_WOOD));
    Block STRIPPED_AZALEA_LOG = BlockRegistry.register(EBlockItemIds.STRIPPED_AZALEA_LOG, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GREEN).strength(2.0F, 2.0F).sound(BlockSoundSets.CHERRY_WOOD));
    Block AZALEA_WOOD = BlockRegistry.register(EBlockItemIds.AZALEA_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).strength(2.0F, 2.0F).sound(BlockSoundSets.CHERRY_WOOD));
    Block STRIPPED_AZALEA_WOOD = BlockRegistry.register(EBlockItemIds.STRIPPED_AZALEA_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GREEN).strength(2.0F, 2.0F).sound(BlockSoundSets.CHERRY_WOOD));
    Block AZALEA_PLANKS = BlockRegistry.register(EBlockItemIds.AZALEA_PLANKS, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GREEN).strength(2.0F, 2.0F).sound(BlockSoundSets.CHERRY_WOOD));
    Block AZALEA_STAIRS = BlockRegistry.registerStair(EBlockItemIds.AZALEA_STAIRS, AZALEA_PLANKS);
    Block AZALEA_SLAB = BlockRegistry.registerSlab(EBlockItemIds.AZALEA_SLAB, AZALEA_PLANKS);
    Block AZALEA_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.AZALEA_MOSAIC, AZALEA_PLANKS);
    Block AZALEA_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.AZALEA_MOSAIC_STAIRS, AZALEA_MOSAIC);
    Block AZALEA_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.AZALEA_MOSAIC_SLAB, AZALEA_MOSAIC);
    Block AZALEA_BOARDS = BlockRegistry.register(EBlockItemIds.AZALEA_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(AZALEA_PLANKS));
    Block AZALEA_TRIM = BlockRegistry.register(EBlockItemIds.AZALEA_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(AZALEA_PLANKS));
    Block AZALEA_FENCE = BlockRegistry.registerFence(EBlockItemIds.AZALEA_FENCE, AZALEA_PLANKS);
    Block AZALEA_FENCE_GATE = BlockRegistry.registerFenceGate(EBlockItemIds.AZALEA_FENCE_GATE, EWoodTypes.AZALEA, AZALEA_PLANKS);
    Block AZALEA_DOOR = BlockRegistry.registerDoor(EBlockItemIds.AZALEA_DOOR, EBlockSets.AZALEA, AZALEA_PLANKS);
    Block AZALEA_TRAPDOOR = BlockRegistry.registerTrapdoor(EBlockItemIds.AZALEA_TRAPDOOR, EBlockSets.AZALEA, AZALEA_PLANKS);
    Block AZALEA_PRESSURE_PLATE = BlockRegistry.registerPressurePlate(EBlockItemIds.AZALEA_PRESSURE_PLATE, EBlockSets.AZALEA, AZALEA_PLANKS);
    Block AZALEA_BUTTON = BlockRegistry.registerButton(EBlockItemIds.AZALEA_BUTTON, EBlockSets.AZALEA, AZALEA_PLANKS, 30);
    Block AZALEA_SIGN = BlockRegistry.registerSign(EBlockItemIds.AZALEA_SIGN, EWoodTypes.AZALEA, AZALEA_PLANKS);
    Block AZALEA_WALL_SIGN = BlockRegistry.registerWallSign(EBlockItemIds.AZALEA_WALL_SIGN, EWoodTypes.AZALEA, AZALEA_PLANKS);
    Block AZALEA_HANGING_SIGN = BlockRegistry.registerCeilingHangingSign(EBlockItemIds.AZALEA_HANGING_SIGN, EWoodTypes.AZALEA, AZALEA_PLANKS);
    Block AZALEA_WALL_HANGING_SIGN = BlockRegistry.registerWallHangingSign(EBlockItemIds.AZALEA_WALL_HANGING_SIGN, EWoodTypes.AZALEA, AZALEA_PLANKS);
    Block AZALEA_SHELF = BlockRegistry.registerShelf(EBlockItemIds.AZALEA_SHELF, AZALEA_PLANKS);

    // Cedar
    Block CEDAR_LOG = BlockRegistry.register(EBlockItemIds.CEDAR_LOG, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block STRIPPED_CEDAR_LOG = BlockRegistry.register(EBlockItemIds.STRIPPED_CEDAR_LOG, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block CEDAR_WOOD = BlockRegistry.register(EBlockItemIds.CEDAR_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block STRIPPED_CEDAR_WOOD = BlockRegistry.register(EBlockItemIds.STRIPPED_CEDAR_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block CEDAR_PLANKS = BlockRegistry.register(EBlockItemIds.CEDAR_PLANKS, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block CEDAR_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CEDAR_STAIRS, CEDAR_PLANKS);
    Block CEDAR_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CEDAR_SLAB, CEDAR_PLANKS);
    Block CEDAR_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.CEDAR_MOSAIC, CEDAR_PLANKS);
    Block CEDAR_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CEDAR_MOSAIC_STAIRS, CEDAR_MOSAIC);
    Block CEDAR_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CEDAR_MOSAIC_SLAB, CEDAR_MOSAIC);
    Block CEDAR_BOARDS = BlockRegistry.register(EBlockItemIds.CEDAR_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(CEDAR_PLANKS));
    Block CEDAR_TRIM = BlockRegistry.register(EBlockItemIds.CEDAR_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(CEDAR_PLANKS));
    Block CEDAR_FENCE = BlockRegistry.registerFence(EBlockItemIds.CEDAR_FENCE, CEDAR_PLANKS);
    Block CEDAR_FENCE_GATE = BlockRegistry.registerFenceGate(EBlockItemIds.CEDAR_FENCE_GATE, EWoodTypes.CEDAR, CEDAR_PLANKS);
    Block CEDAR_DOOR = BlockRegistry.registerDoor(EBlockItemIds.CEDAR_DOOR, EBlockSets.CEDAR, CEDAR_PLANKS);
    Block CEDAR_TRAPDOOR = BlockRegistry.registerTrapdoor(EBlockItemIds.CEDAR_TRAPDOOR, EBlockSets.CEDAR, CEDAR_PLANKS);
    Block CEDAR_PRESSURE_PLATE = BlockRegistry.registerPressurePlate(EBlockItemIds.CEDAR_PRESSURE_PLATE, EBlockSets.CEDAR, CEDAR_PLANKS);
    Block CEDAR_BUTTON = BlockRegistry.registerButton(EBlockItemIds.CEDAR_BUTTON, EBlockSets.CEDAR, CEDAR_PLANKS, 30);
    Block CEDAR_SIGN = BlockRegistry.registerSign(EBlockItemIds.CEDAR_SIGN, EWoodTypes.CEDAR, CEDAR_PLANKS);
    Block CEDAR_WALL_SIGN = BlockRegistry.registerWallSign(EBlockItemIds.CEDAR_WALL_SIGN, EWoodTypes.CEDAR, CEDAR_PLANKS);
    Block CEDAR_HANGING_SIGN = BlockRegistry.registerCeilingHangingSign(EBlockItemIds.CEDAR_HANGING_SIGN, EWoodTypes.CEDAR, CEDAR_PLANKS);
    Block CEDAR_WALL_HANGING_SIGN = BlockRegistry.registerWallHangingSign(EBlockItemIds.CEDAR_WALL_HANGING_SIGN, EWoodTypes.CEDAR, CEDAR_PLANKS);
    Block CEDAR_SHELF = BlockRegistry.registerShelf(EBlockItemIds.CEDAR_SHELF, CEDAR_PLANKS);
    Block CEDAR_SAPLING = BlockRegistry.registerSapling(EBlockItemIds.CEDAR_SAPLING, ETreeGrowers.CEDAR);
    Block POTTED_CEDAR_SAPLING = BlockRegistry.registerPottedSapling(blockKey("potted_cedar_sapling"), CEDAR_SAPLING);
    Block CEDAR_LEAVES = BlockRegistry.register(EBlockItemIds.CEDAR_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p),
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F, 0.2F).sound(BlockSoundSets.AZALEA_LEAVES).noOcclusion());

    // Mahogany
    Block MAHOGANY_LOG = BlockRegistry.register(EBlockItemIds.MAHOGANY_LOG, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_PURPLE).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block STRIPPED_MAHOGANY_LOG = BlockRegistry.register(EBlockItemIds.STRIPPED_MAHOGANY_LOG, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_PURPLE).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block MAHOGANY_WOOD = BlockRegistry.register(EBlockItemIds.MAHOGANY_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_PURPLE).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block STRIPPED_MAHOGANY_WOOD = BlockRegistry.register(EBlockItemIds.STRIPPED_MAHOGANY_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_PURPLE).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block MAHOGANY_PLANKS = BlockRegistry.register(EBlockItemIds.MAHOGANY_PLANKS, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_RED).strength(2.0F, 2.0F).sound(BlockSoundSets.WOOD));
    Block MAHOGANY_STAIRS = BlockRegistry.registerStair(EBlockItemIds.MAHOGANY_STAIRS, MAHOGANY_PLANKS);
    Block MAHOGANY_SLAB = BlockRegistry.registerSlab(EBlockItemIds.MAHOGANY_SLAB, MAHOGANY_PLANKS);
    Block MAHOGANY_MOSAIC = BlockRegistry.registerCopyBlock(EBlockItemIds.MAHOGANY_MOSAIC, MAHOGANY_PLANKS);
    Block MAHOGANY_MOSAIC_STAIRS = BlockRegistry.registerStair(EBlockItemIds.MAHOGANY_MOSAIC_STAIRS, MAHOGANY_MOSAIC);
    Block MAHOGANY_MOSAIC_SLAB = BlockRegistry.registerSlab(EBlockItemIds.MAHOGANY_MOSAIC_SLAB, MAHOGANY_MOSAIC);
    Block MAHOGANY_BOARDS = BlockRegistry.register(EBlockItemIds.MAHOGANY_BOARDS, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(MAHOGANY_PLANKS));
    Block MAHOGANY_TRIM = BlockRegistry.register(EBlockItemIds.MAHOGANY_TRIM, HorizontalBlock::new, BlockBehaviour.Properties.ofFullCopy(MAHOGANY_PLANKS));
    Block MAHOGANY_FENCE = BlockRegistry.registerFence(EBlockItemIds.MAHOGANY_FENCE, MAHOGANY_PLANKS);
    Block MAHOGANY_FENCE_GATE = BlockRegistry.registerFenceGate(EBlockItemIds.MAHOGANY_FENCE_GATE, EWoodTypes.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_DOOR = BlockRegistry.registerDoor(EBlockItemIds.MAHOGANY_DOOR, EBlockSets.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_TRAPDOOR = BlockRegistry.registerTrapdoor(EBlockItemIds.MAHOGANY_TRAPDOOR, EBlockSets.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_PRESSURE_PLATE = BlockRegistry.registerPressurePlate(EBlockItemIds.MAHOGANY_PRESSURE_PLATE, EBlockSets.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_BUTTON = BlockRegistry.registerButton(EBlockItemIds.MAHOGANY_BUTTON, EBlockSets.MAHOGANY, MAHOGANY_PLANKS, 30);
    Block MAHOGANY_SIGN = BlockRegistry.registerSign(EBlockItemIds.MAHOGANY_SIGN, EWoodTypes.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_WALL_SIGN = BlockRegistry.registerWallSign(EBlockItemIds.MAHOGANY_WALL_SIGN, EWoodTypes.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_HANGING_SIGN = BlockRegistry.registerCeilingHangingSign(EBlockItemIds.MAHOGANY_HANGING_SIGN, EWoodTypes.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_WALL_HANGING_SIGN = BlockRegistry.registerWallHangingSign(EBlockItemIds.MAHOGANY_WALL_HANGING_SIGN, EWoodTypes.MAHOGANY, MAHOGANY_PLANKS);
    Block MAHOGANY_SHELF = BlockRegistry.registerShelf(EBlockItemIds.MAHOGANY_SHELF, MAHOGANY_PLANKS);
    Block MAHOGANY_SAPLING = BlockRegistry.registerSapling(EBlockItemIds.MAHOGANY_SAPLING, ETreeGrowers.MAHOGANY);
    Block POTTED_MAHOGANY_SAPLING = BlockRegistry.registerPottedSapling(blockKey("potted_mahogany_sapling"), MAHOGANY_SAPLING);
    Block MAHOGANY_LEAVES = BlockRegistry.register(EBlockItemIds.MAHOGANY_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p),
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F, 0.2F).sound(BlockSoundSets.AZALEA_LEAVES).noOcclusion());

    // Arid Dirt
    ResourceKey<Block> ARID_DIRT_KEY = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "arid_dirt"));
    Block ARID_DIRT = BlockRegistry.register(EBlockItemIds.ARID_DIRT, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD).strength(0.5F).sound(BlockSoundSets.ROOTED_DIRT));
    Block ARID_GRASS_BLOCK = BlockRegistry.register(EBlockItemIds.ARID_GRASS_BLOCK, AridGrassBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_BROWN).strength(0.5F).sound(BlockSoundSets.ROOTED_DIRT).randomTicks());

    Block CATTAILS = BlockRegistry.register(EBlockItemIds.CATTAILS, AmphibiousDoublePlantBlock::new, BlockBehaviour.Properties.of()
            .noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.WET_GRASS)
            .mapColor(MapColor.GRASS).offsetType(BlockBehaviour.OffsetType.XZ));

    // Cave Flora
    Block CAVE_WEED = BlockRegistry.register(EBlockItemIds.CAVE_WEED, CaveWeedBlock::new, BlockBehaviour.Properties.of()
            .noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED)
            .sound(BlockSoundSets.GRASS).mapColor(MapColor.GLOW_LICHEN).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    Block GLEAMSHROOM = BlockRegistry.register(EBlockItemIds.GLEAMSHROOM, GleamshroomBlock::new, BlockBehaviour.Properties.of()
            .noCollision().instabreak().noOcclusion().pushReaction(PushReaction.POPPED).sound(BlockSoundSets.GRASS)
            .mapColor(MapColor.GLOW_LICHEN).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(_ -> 5).emissiveRendering((_) -> true));


    // Terracotta
    Block TERRACOTTA_STAIRS = BlockRegistry.registerStair(EBlockItemIds.TERRACOTTA_STAIRS, Blocks.TERRACOTTA);
    Block TERRACOTTA_SLAB = BlockRegistry.registerSlab(EBlockItemIds.TERRACOTTA_SLAB, Blocks.TERRACOTTA);
    Block TERRACOTTA_BRICKS = BlockRegistry.register(EBlockItemIds.TERRACOTTA_BRICKS, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TERRACOTTA).sound(EBlockSoundSets.STONE_BRICKS));
    Block TERRACOTTA_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.TERRACOTTA_BRICK_STAIRS, TERRACOTTA_BRICKS);
    Block TERRACOTTA_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.TERRACOTTA_BRICK_SLAB, TERRACOTTA_BRICKS);
    Block TERRACOTTA_BRICK_TILES = BlockRegistry.register(EBlockItemIds.TERRACOTTA_BRICK_TILES, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TERRACOTTA).sound(EBlockSoundSets.STONE_TILES));
    Block TERRACOTTA_BRICK_TILE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.TERRACOTTA_BRICK_TILE_STAIRS, TERRACOTTA_BRICK_TILES);
    Block TERRACOTTA_BRICK_TILE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.TERRACOTTA_BRICK_TILE_SLAB, TERRACOTTA_BRICK_TILES);
    ColorCollection<Block> DYED_TERRACOTTA_STAIRS = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_STAIRS, BlockRegistry::register,
            (color, p) -> new StairBlock(Blocks.DYED_TERRACOTTA.pick(color).defaultBlockState(), p),
            color -> BlockBehaviour.Properties.ofFullCopy(Blocks.DYED_TERRACOTTA.pick(color)));
    ColorCollection<Block> DYED_TERRACOTTA_SLAB = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_SLAB, BlockRegistry::register,
            (color, p) -> new SlabBlock(p),
            color -> BlockBehaviour.Properties.ofFullCopy(Blocks.DYED_TERRACOTTA.pick(color)));
    ColorCollection<Block> DYED_TERRACOTTA_BRICKS = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_BRICKS, BlockRegistry::register,
            (color, p) -> new Block(p),
            color -> BlockBehaviour.Properties.ofFullCopy(Blocks.DYED_TERRACOTTA.pick(color)).sound(EBlockSoundSets.STONE_BRICKS));
    ColorCollection<Block> DYED_TERRACOTTA_BRICK_STAIRS = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_BRICK_STAIRS, BlockRegistry::register,
            (color, p) -> new StairBlock(Blocks.DYED_TERRACOTTA.pick(color).defaultBlockState(), p),
            color -> BlockBehaviour.Properties.ofFullCopy(DYED_TERRACOTTA_BRICKS.pick(color)));
    ColorCollection<Block> DYED_TERRACOTTA_BRICK_SLAB = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_BRICK_SLAB, BlockRegistry::register,
            (color, p) -> new SlabBlock(p),
            color -> BlockBehaviour.Properties.ofFullCopy(DYED_TERRACOTTA_BRICKS.pick(color)));
    ColorCollection<Block> DYED_TERRACOTTA_BRICK_TILES = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_BRICK_TILES, BlockRegistry::register,
            (color, p) -> new Block(p),
            color -> BlockBehaviour.Properties.ofFullCopy(Blocks.DYED_TERRACOTTA.pick(color)).sound(EBlockSoundSets.STONE_TILES));
    ColorCollection<Block> DYED_TERRACOTTA_BRICK_TILE_STAIRS = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_BRICK_TILE_STAIRS, BlockRegistry::register,
            (color, p) -> new StairBlock(Blocks.DYED_TERRACOTTA.pick(color).defaultBlockState(), p),
            color -> BlockBehaviour.Properties.ofFullCopy(DYED_TERRACOTTA_BRICK_TILES.pick(color)));
    ColorCollection<Block> DYED_TERRACOTTA_BRICK_TILE_SLAB = ColorCollection.registerBlocks(EBlockItemIds.DYED_TERRACOTTA_BRICK_TILE_SLAB, BlockRegistry::register,
            (color, p) -> new SlabBlock(p),
            color -> BlockBehaviour.Properties.ofFullCopy(DYED_TERRACOTTA_BRICK_TILES.pick(color)));


    Block BEACHSTONE = BlockRegistry.register(EBlockItemIds.BEACHSTONE, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_WHITE).strength(1.5F, 1.0F).sound(BlockSoundSets.DRIPSTONE_BLOCK));
    Block BEACHSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.BEACHSTONE_STAIRS, BEACHSTONE);
    Block BEACHSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.BEACHSTONE_SLAB, BEACHSTONE);
    Block BEACHSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.BEACHSTONE_WALL, BEACHSTONE);
    Block POLISHED_BEACHSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_BEACHSTONE, BEACHSTONE);
    Block POLISHED_BEACHSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_BEACHSTONE_STAIRS, POLISHED_BEACHSTONE);
    Block POLISHED_BEACHSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_BEACHSTONE_SLAB, POLISHED_BEACHSTONE);
    Block POLISHED_BEACHSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_BEACHSTONE_WALL, POLISHED_BEACHSTONE);
    Block BEACHSTONE_BRICKS = BlockRegistry.registerCopyBlock(EBlockItemIds.BEACHSTONE_BRICKS, BEACHSTONE);
    Block BEACHSTONE_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.BEACHSTONE_BRICK_STAIRS, BEACHSTONE_BRICKS);
    Block BEACHSTONE_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.BEACHSTONE_BRICK_SLAB, BEACHSTONE_BRICKS);
    Block BEACHSTONE_BRICK_WALL = BlockRegistry.registerWall(EBlockItemIds.BEACHSTONE_BRICK_WALL, BEACHSTONE_BRICKS);
    Block CHISELED_BEACHSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_BEACHSTONE, BEACHSTONE_BRICKS);

    Block LIMESTONE = BlockRegistry.register(EBlockItemIds.LIMESTONE, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_YELLOW).strength(1.5F, 1.0F).sound(EBlockSoundSets.SHALE));
    Block LIMESTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.LIMESTONE_STAIRS, LIMESTONE);
    Block LIMESTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.LIMESTONE_SLAB, LIMESTONE);
    Block LIMESTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.LIMESTONE_WALL, LIMESTONE);
    Block CUT_LIMESTONE = BlockRegistry.register(EBlockItemIds.CUT_LIMESTONE, Block::new, BlockBehaviour.Properties.ofFullCopy(LIMESTONE).sound(EBlockSoundSets.SHALE_TILES));
    Block CUT_LIMESTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_LIMESTONE_STAIRS, CUT_LIMESTONE);
    Block CUT_LIMESTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CUT_LIMESTONE_SLAB, CUT_LIMESTONE);
    Block CUT_LIMESTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.CUT_LIMESTONE_WALL, CUT_LIMESTONE);

    Block ROCKWEED_BASALT = BlockRegistry.register(EBlockItemIds.ROCKWEED_BASALT, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)
            .sound(EBlockSoundSets.ROCKWEED_BASALT));
    Block BASALT_VENT = BlockRegistry.register(EBlockItemIds.BASALT_VENT, BasaltVentBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)
                    .lightLevel(state -> state.getValue(BasaltVentBlock.ACTIVE) ? 3 : 0));

    Block PINK_SAND = BlockRegistry.register(EBlockItemIds.PINK_SAND, p -> new SandBlock(new ColorRGBA(0xFFECCFBD), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).mapColor(MapColor.TERRACOTTA_PINK));
    Block PINK_SANDSTONE = BlockRegistry.register(EBlockItemIds.PINK_SANDSTONE, Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).mapColor(MapColor.TERRACOTTA_PINK));
    Block PINK_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.PINK_SANDSTONE_STAIRS, PINK_SANDSTONE);
    Block PINK_SANDSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.PINK_SANDSTONE_SLAB, PINK_SANDSTONE);
    Block PINK_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.PINK_SANDSTONE_WALL, PINK_SANDSTONE);
    Block SMOOTH_PINK_SANDSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.SMOOTH_PINK_SANDSTONE, PINK_SANDSTONE);
    Block SMOOTH_PINK_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.SMOOTH_PINK_SANDSTONE_STAIRS, SMOOTH_PINK_SANDSTONE);
    Block SMOOTH_PINK_SANDSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.SMOOTH_PINK_SANDSTONE_SLAB, SMOOTH_PINK_SANDSTONE);
    Block SMOOTH_PINK_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.SMOOTH_PINK_SANDSTONE_WALL, SMOOTH_PINK_SANDSTONE);
    Block CUT_PINK_SANDSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.CUT_PINK_SANDSTONE, PINK_SANDSTONE);
    Block CUT_PINK_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_PINK_SANDSTONE_STAIRS, CUT_PINK_SANDSTONE);
    Block CUT_PINK_SANDSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CUT_PINK_SANDSTONE_SLAB, CUT_PINK_SANDSTONE);
    Block CUT_PINK_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.CUT_PINK_SANDSTONE_WALL, CUT_PINK_SANDSTONE);
    Block POLISHED_PINK_SANDSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_PINK_SANDSTONE, PINK_SANDSTONE);
    Block POLISHED_PINK_SANDSTONE_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_PINK_SANDSTONE_STAIRS, POLISHED_PINK_SANDSTONE);
    Block POLISHED_PINK_SANDSTONE_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_PINK_SANDSTONE_SLAB, POLISHED_PINK_SANDSTONE);
    Block POLISHED_PINK_SANDSTONE_WALL = BlockRegistry.registerWall(EBlockItemIds.POLISHED_PINK_SANDSTONE_WALL, POLISHED_PINK_SANDSTONE);
    Block CHISELED_PINK_SANDSTONE = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_PINK_SANDSTONE, PINK_SANDSTONE);


    // Mud
    Block MOSSY_MUD = BlockRegistry.register(EBlockItemIds.MOSSY_MUD, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)
            .sound(EBlockSoundSets.MOSSY_MUD).mapColor(MapColor.COLOR_LIGHT_GREEN));
    Block MUDSTONE = BlockRegistry.register(EBlockItemIds.MUDSTONE, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).sound(BlockSoundSets.CINNABAR));

    Block CUT_TIN = BlockRegistry.registerCopyBlock(EBlockItemIds.CUT_TIN, TIN_BLOCK);
    Block CUT_TIN_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_TIN_STAIRS, CUT_TIN);
    Block CUT_TIN_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CUT_TIN_SLAB, CUT_TIN);
    Block CHISELED_TIN = BlockRegistry.registerCopyBlock(EBlockItemIds.CHISELED_TIN, TIN_BLOCK);
    Block TIN_BARS = BlockRegistry.register(EBlockItemIds.TIN_BARS, IronBarsBlock::new,
            BlockBehaviour.Properties.ofFullCopy(TIN_BLOCK).strength(1.0F));

    Block POLISHED_SILVER = BlockRegistry.registerCopyBlock(EBlockItemIds.POLISHED_SILVER, SILVER_BLOCK);
    Block POLISHED_SILVER_STAIRS = BlockRegistry.registerStair(EBlockItemIds.POLISHED_SILVER_STAIRS, SILVER_BLOCK);
    Block POLISHED_SILVER_SLAB = BlockRegistry.registerSlab(EBlockItemIds.POLISHED_SILVER_SLAB, SILVER_BLOCK);
    Block CUT_SILVER = BlockRegistry.registerCopyBlock(EBlockItemIds.CUT_SILVER, SILVER_BLOCK);
    Block CUT_SILVER_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_SILVER_STAIRS, SILVER_BLOCK);
    Block CUT_SILVER_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CUT_SILVER_SLAB, SILVER_BLOCK);
    Block SILVER_PILLAR = BlockRegistry.register(EBlockItemIds.SILVER_PILLAR, RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SILVER_BLOCK));
    Block SILVER_BARS = BlockRegistry.register(EBlockItemIds.SILVER_BARS, IronBarsBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SILVER_BLOCK).strength(1.0F));
    Block SILVER_CHAIN = BlockRegistry.register(EBlockItemIds.SILVER_CHAIN, ChainBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SILVER_BLOCK).forceSolidOn().sound(BlockSoundSets.CHAIN).noOcclusion());

    Block GOLD_CHAIN = BlockRegistry.register(EBlockItemIds.GOLD_CHAIN, ChainBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).forceSolidOn().sound(BlockSoundSets.CHAIN).noOcclusion());
    Block REDSTONE_LANTERN = BlockRegistry.register(EBlockItemIds.REDSTONE_LANTERN, RedstoneLanternBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).mapColor(MapColor.GOLD)
                    .lightLevel((state) -> (Boolean)state.getValue(BlockStateProperties.LIT) ? 7 : 0));

    Block WROUGHT_IRON_BLOCK = BlockRegistry.register(EBlockItemIds.WROUGHT_IRON_BLOCK, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F, 8.0F).sound(BlockSoundSets.IRON));
    Block WROUGHT_IRON_STAIRS = BlockRegistry.registerStair(EBlockItemIds.WROUGHT_IRON_STAIRS, WROUGHT_IRON_BLOCK);
    Block WROUGHT_IRON_SLAB = BlockRegistry.registerSlab(EBlockItemIds.WROUGHT_IRON_SLAB, WROUGHT_IRON_BLOCK);
    Block CUT_WROUGHT_IRON = BlockRegistry.register(EBlockItemIds.CUT_WROUGHT_IRON, Block::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F, 8.0F).sound(BlockSoundSets.IRON));
    Block CUT_WROUGHT_IRON_STAIRS = BlockRegistry.registerStair(EBlockItemIds.CUT_WROUGHT_IRON_STAIRS, CUT_WROUGHT_IRON);
    Block CUT_WROUGHT_IRON_SLAB = BlockRegistry.registerSlab(EBlockItemIds.CUT_WROUGHT_IRON_SLAB, CUT_WROUGHT_IRON);
    Block WROUGHT_IRON_GRATE = BlockRegistry.register(EBlockItemIds.WROUGHT_IRON_GRATE, WaterloggedTransparentBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(1.5F, 4.0F).sound(EBlockSoundSets.IRON_GRATE).noOcclusion());
    Block WROUGHT_IRON_BARS = BlockRegistry.register(EBlockItemIds.WROUGHT_IRON_BARS, IronBarsBlock::new,
            BlockBehaviour.Properties.ofFullCopy(WROUGHT_IRON_BLOCK).strength(1.0F));

    Block LAPIS_BRICKS = BlockRegistry.register(EBlockItemIds.LAPIS_BRICKS, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK).sound(EBlockSoundSets.STONE_BRICKS));
    Block LAPIS_BRICK_STAIRS = BlockRegistry.registerStair(EBlockItemIds.LAPIS_BRICK_STAIRS, LAPIS_BRICKS);
    Block LAPIS_BRICK_SLAB = BlockRegistry.registerSlab(EBlockItemIds.LAPIS_BRICK_SLAB, LAPIS_BRICKS);

    Block DUNGEON_SPAWNER = BlockRegistry.register(EBlockItemIds.DUNGEON_SPAWNER, DungeonSpawnerBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TRIAL_SPAWNER));
    Block DUNGEON_VAULT = BlockRegistry.register(EBlockItemIds.DUNGEON_VAULT, DungeonVaultBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.VAULT));























    // Key Registry
    private static ResourceKey<Block> blockKey(String name) {
        return BlockRegistry.createBlockKey(Elsewhere.MODID, name);
    }

    // Initialize
    static void initialize() {
        BlockSpreadRegistry.register(EBlocks.ARID_GRASS_BLOCK, Blocks.DIRT, Blocks.GRASS_BLOCK);
        BlockSpreadRegistry.register(Blocks.GRASS_BLOCK, EBlocks.ARID_DIRT, EBlocks.ARID_GRASS_BLOCK);
    }
}