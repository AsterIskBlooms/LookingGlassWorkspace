package team.lookingglass.elsewhere.datagen.loot;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import team.lookingglass.elsewhere.registry.EBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import team.lookingglass.elsewhere.registry.EItems;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableProvider extends FabricBlockLootSubProvider {
    public BlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }



    @Override
    public void generate() {
        // Copper & Coal Ore Changes
        add(Blocks.COPPER_ORE, createMultipleOreDrops(Blocks.COPPER_ORE, Items.RAW_COPPER, 1, 2));
        add(Blocks.DEEPSLATE_COPPER_ORE, createMultipleOreDrops(Blocks.DEEPSLATE_COPPER_ORE, Items.RAW_COPPER, 1, 2));

        // Cinnabar Ores
        add(EBlocks.CINNABAR_COAL_ORE, createOreDrop(EBlocks.CINNABAR_COAL_ORE, Items.COAL));
        add(EBlocks.CINNABAR_COPPER_ORE, createMultipleOreDrops(EBlocks.CINNABAR_COPPER_ORE, Items.RAW_COPPER, 1, 2));
        add(EBlocks.CINNABAR_IRON_ORE, createOreDrop(EBlocks.CINNABAR_IRON_ORE, Items.RAW_IRON));
        add(EBlocks.CINNABAR_GOLD_ORE, createOreDrop(EBlocks.CINNABAR_GOLD_ORE, Items.RAW_GOLD));
        add(EBlocks.CINNABAR_REDSTONE_ORE, createRedstoneOreDrops(EBlocks.CINNABAR_REDSTONE_ORE));
        add(EBlocks.CINNABAR_DIAMOND_ORE, createOreDrop(EBlocks.CINNABAR_DIAMOND_ORE, Items.DIAMOND));
        add(EBlocks.CINNABAR_LAPIS_ORE, createLapisOreDrops(EBlocks.CINNABAR_LAPIS_ORE));
        add(EBlocks.CINNABAR_EMERALD_ORE, createOreDrop(EBlocks.CINNABAR_EMERALD_ORE, Items.EMERALD));

        // Tin
        add(EBlocks.TIN_ORE, createOreDrop(EBlocks.TIN_ORE, EItems.RAW_TIN));
        add(EBlocks.DEEPSLATE_TIN_ORE, createOreDrop(EBlocks.DEEPSLATE_TIN_ORE, EItems.RAW_TIN));
        add(EBlocks.CINNABAR_TIN_ORE, createOreDrop(EBlocks.CINNABAR_TIN_ORE, EItems.RAW_TIN));

        // Silver
        add(EBlocks.SILVER_ORE, createOreDrop(EBlocks.SILVER_ORE, EItems.RAW_SILVER));
        add(EBlocks.DEEPSLATE_SILVER_ORE, createOreDrop(EBlocks.DEEPSLATE_SILVER_ORE, EItems.RAW_SILVER));
        add(EBlocks.CINNABAR_SILVER_ORE, createOreDrop(EBlocks.CINNABAR_SILVER_ORE, EItems.RAW_SILVER));

        dropSelf(EBlocks.RAW_TIN_BLOCK);
        dropSelf(EBlocks.TIN_BLOCK);

        dropSelf(EBlocks.BRONZE_BLOCK);

        dropSelf(EBlocks.RAW_SILVER_BLOCK);
        dropSelf(EBlocks.SILVER_BLOCK);
        dropSelf(EBlocks.POLISHED_SILVER);
        dropSelf(EBlocks.POLISHED_SILVER_STAIRS);
        add(EBlocks.POLISHED_SILVER_SLAB, createSlabItemTable(EBlocks.POLISHED_SILVER_SLAB));
        dropSelf(EBlocks.CUT_SILVER);
        dropSelf(EBlocks.CUT_SILVER_STAIRS);
        add(EBlocks.CUT_SILVER_SLAB, createSlabItemTable(EBlocks.CUT_SILVER_SLAB));
        dropSelf(EBlocks.SILVER_PILLAR);
        dropSelf(EBlocks.SILVER_BARS);
        dropSelf(EBlocks.SILVER_CHAIN);

        dropSelf(EBlocks.GOLD_CHAIN);
        dropSelf(EBlocks.REDSTONE_LANTERN);

        dropSelf(EBlocks.WROUGHT_IRON_BLOCK);
        dropSelf(EBlocks.WROUGHT_IRON_STAIRS);
        add(EBlocks.WROUGHT_IRON_SLAB, createSlabItemTable(EBlocks.WROUGHT_IRON_SLAB));
        dropSelf(EBlocks.CUT_WROUGHT_IRON);
        dropSelf(EBlocks.CUT_WROUGHT_IRON_STAIRS);
        add(EBlocks.CUT_WROUGHT_IRON_SLAB, createSlabItemTable(EBlocks.CUT_WROUGHT_IRON_SLAB));
        dropSelf(EBlocks.WROUGHT_IRON_GRATE);
        dropSelf(EBlocks.WROUGHT_IRON_BARS);

        dropSelf(EBlocks.CUT_TIN);
        dropSelf(EBlocks.CUT_TIN_STAIRS);
        add(EBlocks.CUT_TIN_SLAB, createSlabItemTable(EBlocks.CUT_TIN_SLAB));
        dropSelf(EBlocks.CHISELED_TIN);
        dropSelf(EBlocks.TIN_BARS);

        dropSelf(EBlocks.SOULSTEEL_BLOCK);
        dropSelf(EBlocks.ELECTRUM_BLOCK);
        dropSelf(EBlocks.URANIUM_BLOCK);

        add(Blocks.ANCIENT_DEBRIS, createMultipleOreDrops(Blocks.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP, 1, 2));

        // Stones
        dropSelf(EBlocks.QUARTZ_BRICK_STAIRS);
        add(EBlocks.QUARTZ_BRICK_SLAB, createSlabItemTable(EBlocks.QUARTZ_BRICK_SLAB));

        dropSelf(EBlocks.AMETHYST_STAIRS);
        add(EBlocks.AMETHYST_SLAB, createSlabItemTable(EBlocks.AMETHYST_SLAB));
        dropSelf(EBlocks.AMETHYST_BRICKS);
        dropSelf(EBlocks.AMETHYST_BRICK_STAIRS);
        add(EBlocks.AMETHYST_BRICK_SLAB, createSlabItemTable(EBlocks.AMETHYST_BRICK_SLAB));
        dropSelf(EBlocks.AMETHYST_PILLAR);
        dropSelf(EBlocks.CHISELED_AMETHYST);

        dropSelf(EBlocks.OBSIDIAN_STAIRS);
        add(EBlocks.OBSIDIAN_SLAB, createSlabItemTable(EBlocks.OBSIDIAN_SLAB));
        dropSelf(EBlocks.OBSIDIAN_BRICKS);
        dropSelf(EBlocks.OBSIDIAN_BRICK_STAIRS);
        add(EBlocks.OBSIDIAN_BRICK_SLAB, createSlabItemTable(EBlocks.OBSIDIAN_BRICK_SLAB));
        dropSelf(EBlocks.OBSIDIAN_PILLAR);
        dropSelf(EBlocks.CHISELED_OBSIDIAN);

        dropSelf(EBlocks.STONE_WALL);
        dropSelf(EBlocks.SMOOTH_STONE_STAIRS);
        dropSelf(EBlocks.SMOOTH_STONE_WALL);
        dropSelf(EBlocks.POLISHED_STONE);
        dropSelf(EBlocks.POLISHED_STONE_STAIRS);
        add(EBlocks.POLISHED_STONE_SLAB, createSlabItemTable(EBlocks.POLISHED_STONE_SLAB));
        dropSelf(EBlocks.STONE_TILES);
        dropSelf(EBlocks.STONE_TILE_STAIRS);
        add(EBlocks.STONE_TILE_SLAB, createSlabItemTable(EBlocks.STONE_TILE_SLAB));
        dropSelf(EBlocks.STONE_TILE_WALL);
        dropSelf(EBlocks.MOSSY_STONE_TILES);
        dropSelf(EBlocks.MOSSY_STONE_TILE_STAIRS);
        add(EBlocks.MOSSY_STONE_TILE_SLAB, createSlabItemTable(EBlocks.MOSSY_STONE_TILE_SLAB));
        dropSelf(EBlocks.MOSSY_STONE_TILE_WALL);
        dropSelf(EBlocks.CHISELED_STONE);
        dropSelf(EBlocks.STONE_PILLAR);

        dropSelf(EBlocks.DEEPSLATE_STAIRS);
        dropSelf(EBlocks.DEEPSLATE_SLAB);
        dropSelf(EBlocks.DEEPSLATE_WALL);
        dropSelf(EBlocks.CHISELED_DEEPSLATE_BRICKS);
        dropSelf(EBlocks.DEEPSLATE_PILLAR);

        dropSelf(EBlocks.TUFF_PILLAR);
        dropSelf(EBlocks.BLACKSTONE_PILLAR);

        dropSelf(EBlocks.POLISHED_ANDESITE_WALL);
        dropSelf(EBlocks.ANDESITE_BRICKS);
        dropSelf(EBlocks.ANDESITE_BRICK_STAIRS);
        add(EBlocks.ANDESITE_BRICK_SLAB, createSlabItemTable(EBlocks.ANDESITE_BRICK_SLAB));
        dropSelf(EBlocks.ANDESITE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_ANDESITE);
        dropSelf(EBlocks.ANDESITE_PILLAR);

        dropSelf(EBlocks.POLISHED_GRANITE_WALL);
        dropSelf(EBlocks.GRANITE_BRICKS);
        dropSelf(EBlocks.GRANITE_BRICK_STAIRS);
        add(EBlocks.GRANITE_BRICK_SLAB, createSlabItemTable(EBlocks.GRANITE_BRICK_SLAB));
        dropSelf(EBlocks.GRANITE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_GRANITE);

        dropSelf(EBlocks.POLISHED_DIORITE_WALL);
        dropSelf(EBlocks.DIORITE_BRICKS);
        dropSelf(EBlocks.DIORITE_BRICK_STAIRS);
        add(EBlocks.DIORITE_BRICK_SLAB, createSlabItemTable(EBlocks.DIORITE_BRICK_SLAB));
        dropSelf(EBlocks.DIORITE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_DIORITE);

        dropSelf(EBlocks.CALCITE_STAIRS);
        add(EBlocks.CALCITE_SLAB, createSlabItemTable(EBlocks.CALCITE_SLAB));
        dropSelf(EBlocks.CALCITE_WALL);
        dropSelf(EBlocks.POLISHED_CALCITE);
        dropSelf(EBlocks.POLISHED_CALCITE_STAIRS);
        add(EBlocks.POLISHED_CALCITE_SLAB, createSlabItemTable(EBlocks.POLISHED_CALCITE_SLAB));
        dropSelf(EBlocks.POLISHED_CALCITE_WALL);
        dropSelf(EBlocks.CALCITE_BRICKS);
        dropSelf(EBlocks.CALCITE_BRICK_STAIRS);
        add(EBlocks.CALCITE_BRICK_SLAB, createSlabItemTable(EBlocks.CALCITE_BRICK_SLAB));
        dropSelf(EBlocks.CALCITE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_CALCITE);
        dropSelf(EBlocks.CHISELED_CALCITE_BRICKS);
        dropSelf(EBlocks.CALCITE_PILLAR);

        dropSelf(EBlocks.DRIPSTONE_STAIRS);
        add(EBlocks.DRIPSTONE_SLAB, createSlabItemTable(EBlocks.DRIPSTONE_SLAB));
        dropSelf(EBlocks.DRIPSTONE_WALL);
        dropSelf(EBlocks.POLISHED_DRIPSTONE);
        dropSelf(EBlocks.POLISHED_DRIPSTONE_STAIRS);
        add(EBlocks.POLISHED_DRIPSTONE_SLAB, createSlabItemTable(EBlocks.POLISHED_DRIPSTONE_SLAB));
        dropSelf(EBlocks.POLISHED_DRIPSTONE_WALL);
        dropSelf(EBlocks.DRIPSTONE_TILES);
        dropSelf(EBlocks.DRIPSTONE_TILE_STAIRS);
        add(EBlocks.DRIPSTONE_TILE_SLAB, createSlabItemTable(EBlocks.DRIPSTONE_TILE_SLAB));
        dropSelf(EBlocks.DRIPSTONE_TILE_WALL);
        dropSelf(EBlocks.CHISELED_DRIPSTONE_TILES);
        dropSelf(EBlocks.DRIPSTONE_PILLAR);

        dropSelf(EBlocks.POLISHED_SANDSTONE);
        dropSelf(EBlocks.POLISHED_SANDSTONE_STAIRS);
        add(EBlocks.POLISHED_SANDSTONE_SLAB, createSlabItemTable(EBlocks.POLISHED_SANDSTONE_SLAB));
        dropSelf(EBlocks.POLISHED_SANDSTONE_WALL);
        dropSelf(EBlocks.POLISHED_RED_SANDSTONE);
        dropSelf(EBlocks.POLISHED_RED_SANDSTONE_STAIRS);
        add(EBlocks.POLISHED_RED_SANDSTONE_SLAB, createSlabItemTable(EBlocks.POLISHED_RED_SANDSTONE_SLAB));
        dropSelf(EBlocks.POLISHED_RED_SANDSTONE_WALL);
        dropSelf(EBlocks.CUT_RED_SANDSTONE_WALL);
        dropSelf(EBlocks.CUT_RED_SANDSTONE_WALL);
        dropSelf(EBlocks.CUT_SANDSTONE_STAIRS);
        dropSelf(EBlocks.CUT_RED_SANDSTONE_STAIRS);
        dropSelf(EBlocks.CUT_SANDSTONE_WALL);
        dropSelf(EBlocks.CUT_RED_SANDSTONE_WALL);

        dropSelf(EBlocks.POLISHED_PRISMARINE);
        dropSelf(EBlocks.POLISHED_PRISMARINE_STAIRS);
        add(EBlocks.POLISHED_PRISMARINE_SLAB, createSlabItemTable(EBlocks.POLISHED_PRISMARINE_SLAB));
        dropSelf(EBlocks.POLISHED_PRISMARINE_WALL);
        dropSelf(EBlocks.PRISMARINE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_PRISMARINE);
        dropSelf(EBlocks.PRISMARINE_PILLAR);

        dropSelf(EBlocks.DARK_PRISMARINE_WALL);
        dropSelf(EBlocks.POLISHED_DARK_PRISMARINE);
        dropSelf(EBlocks.POLISHED_DARK_PRISMARINE_STAIRS);
        add(EBlocks.POLISHED_DARK_PRISMARINE_SLAB, createSlabItemTable(EBlocks.POLISHED_DARK_PRISMARINE_SLAB));
        dropSelf(EBlocks.POLISHED_DARK_PRISMARINE_WALL);
        dropSelf(EBlocks.DARK_PRISMARINE_SCALES);
        dropSelf(EBlocks.DARK_PRISMARINE_SCALE_STAIRS);
        add(EBlocks.DARK_PRISMARINE_SCALE_SLAB, createSlabItemTable(EBlocks.DARK_PRISMARINE_SCALE_SLAB));
        dropSelf(EBlocks.DARK_PRISMARINE_SCALE_WALL);
        dropSelf(EBlocks.CHISELED_DARK_PRISMARINE);

        add(EBlocks.BASALT_SLAB, createSlabItemTable(EBlocks.BASALT_SLAB));
        add(EBlocks.POLISHED_BASALT_SLAB, createSlabItemTable(EBlocks.POLISHED_BASALT_SLAB));
        dropSelf(EBlocks.SMOOTH_BASALT_STAIRS);
        add(EBlocks.SMOOTH_BASALT_SLAB, createSlabItemTable(EBlocks.SMOOTH_BASALT_SLAB));
        dropSelf(EBlocks.SMOOTH_BASALT_WALL);
        dropSelf(EBlocks.POLISHED_SMOOTH_BASALT);
        dropSelf(EBlocks.POLISHED_SMOOTH_BASALT_STAIRS);
        add(EBlocks.POLISHED_SMOOTH_BASALT_SLAB, createSlabItemTable(EBlocks.POLISHED_SMOOTH_BASALT_SLAB));
        dropSelf(EBlocks.POLISHED_SMOOTH_BASALT_WALL);
        dropSelf(EBlocks.SMOOTH_BASALT_BRICKS);
        dropSelf(EBlocks.SMOOTH_BASALT_BRICK_STAIRS);
        add(EBlocks.SMOOTH_BASALT_BRICK_SLAB, createSlabItemTable(EBlocks.SMOOTH_BASALT_BRICK_SLAB));
        dropSelf(EBlocks.SMOOTH_BASALT_BRICK_WALL);
        dropSelf(EBlocks.SMOOTH_BASALT_TILES);
        dropSelf(EBlocks.SMOOTH_BASALT_TILE_STAIRS);
        add(EBlocks.SMOOTH_BASALT_TILE_SLAB, createSlabItemTable(EBlocks.SMOOTH_BASALT_TILE_SLAB));
        dropSelf(EBlocks.SMOOTH_BASALT_TILE_WALL);

        dropSelf(EBlocks.END_STONE_STAIRS);
        add(EBlocks.END_STONE_SLAB, createSlabItemTable(EBlocks.END_STONE_SLAB));
        dropSelf(EBlocks.END_STONE_WALL);
        dropSelf(EBlocks.POLISHED_END_STONE);
        dropSelf(EBlocks.POLISHED_END_STONE_STAIRS);
        add(EBlocks.POLISHED_END_STONE_SLAB, createSlabItemTable(EBlocks.POLISHED_END_STONE_SLAB));
        dropSelf(EBlocks.POLISHED_END_STONE_WALL);
        dropSelf(EBlocks.CHISELED_END_STONE);
        dropSelf(EBlocks.END_STONE_PILLAR);

        dropSelf(EBlocks.SODALITE);
        dropSelf(EBlocks.SODALITE_STAIRS);
        add(EBlocks.SODALITE_SLAB, createSlabItemTable(EBlocks.SODALITE_SLAB));
        dropSelf(EBlocks.SODALITE_WALL);
        dropSelf(EBlocks.POLISHED_SODALITE);
        dropSelf(EBlocks.POLISHED_SODALITE_STAIRS);
        add(EBlocks.POLISHED_SODALITE_SLAB, createSlabItemTable(EBlocks.POLISHED_SODALITE_SLAB));
        dropSelf(EBlocks.POLISHED_SODALITE_WALL);
        dropSelf(EBlocks.SODALITE_BRICKS);
        dropSelf(EBlocks.SODALITE_BRICK_STAIRS);
        add(EBlocks.SODALITE_BRICK_SLAB, createSlabItemTable(EBlocks.SODALITE_BRICK_SLAB));
        dropSelf(EBlocks.SODALITE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_SODALITE);
        dropSelf(EBlocks.CHISELED_SODALITE_BRICKS);
        dropSelf(EBlocks.SODALITE_PILLAR);

        dropSelf(EBlocks.SHALE);
        dropSelf(EBlocks.SHALE_STAIRS);
        add(EBlocks.SHALE_SLAB, createSlabItemTable(EBlocks.SHALE_SLAB));
        dropSelf(EBlocks.SHALE_WALL);
        dropSelf(EBlocks.POLISHED_SHALE);
        dropSelf(EBlocks.POLISHED_SHALE_STAIRS);
        add(EBlocks.POLISHED_SHALE_SLAB, createSlabItemTable(EBlocks.POLISHED_SHALE_SLAB));
        dropSelf(EBlocks.POLISHED_SHALE_WALL);
        dropSelf(EBlocks.SHALE_BRICKS);
        dropSelf(EBlocks.SHALE_BRICK_STAIRS);
        add(EBlocks.SHALE_BRICK_SLAB, createSlabItemTable(EBlocks.SHALE_BRICK_SLAB));
        dropSelf(EBlocks.SHALE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_SHALE);
        dropSelf(EBlocks.CHISELED_SHALE_BRICKS);
        dropSelf(EBlocks.SHALE_PILLAR);

        dropSelf(EBlocks.SULFUR_PILLAR);
        dropSelf(EBlocks.CINNABAR_PILLAR);

        dropSelf(EBlocks.NETHERRACK_STAIRS);
        add(EBlocks.NETHERRACK_SLAB, createSlabItemTable(EBlocks.NETHERRACK_SLAB));
        dropSelf(EBlocks.NETHERRACK_WALL);
        dropSelf(EBlocks.POLISHED_NETHERRACK);
        dropSelf(EBlocks.POLISHED_NETHERRACK_STAIRS);
        add(EBlocks.POLISHED_NETHERRACK_SLAB, createSlabItemTable(EBlocks.POLISHED_NETHERRACK_SLAB));
        dropSelf(EBlocks.POLISHED_NETHERRACK_WALL);
        dropSelf(EBlocks.NETHERRACK_BRICKS);
        dropSelf(EBlocks.NETHERRACK_BRICK_STAIRS);
        add(EBlocks.NETHERRACK_BRICK_SLAB, createSlabItemTable(EBlocks.NETHERRACK_BRICK_SLAB));
        dropSelf(EBlocks.NETHERRACK_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_NETHERRACK);
        dropSelf(EBlocks.CHISELED_NETHERRACK_BRICKS);
        dropSelf(EBlocks.NETHERRACK_PILLAR);

        dropSelf(EBlocks.ORPIMENT);
        dropSelf(EBlocks.ORPIMENT_STAIRS);
        add(EBlocks.ORPIMENT_SLAB, createSlabItemTable(EBlocks.ORPIMENT_SLAB));
        dropSelf(EBlocks.ORPIMENT_WALL);
        dropSelf(EBlocks.POLISHED_ORPIMENT);
        dropSelf(EBlocks.POLISHED_ORPIMENT_STAIRS);
        add(EBlocks.POLISHED_ORPIMENT_SLAB, createSlabItemTable(EBlocks.POLISHED_ORPIMENT_SLAB));
        dropSelf(EBlocks.POLISHED_ORPIMENT_WALL);
        dropSelf(EBlocks.ORPIMENT_BRICKS);
        dropSelf(EBlocks.ORPIMENT_BRICK_STAIRS);
        add(EBlocks.ORPIMENT_BRICK_SLAB, createSlabItemTable(EBlocks.ORPIMENT_BRICK_SLAB));
        dropSelf(EBlocks.ORPIMENT_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_ORPIMENT);
        dropSelf(EBlocks.ORPIMENT_PILLAR);

        dropSelf(EBlocks.SOUL_STONE);
        dropSelf(EBlocks.SOUL_STONE_STAIRS);
        add(EBlocks.SOUL_STONE_SLAB, createSlabItemTable(EBlocks.SOUL_STONE_SLAB));
        dropSelf(EBlocks.SOUL_STONE_WALL);
        dropSelf(EBlocks.SMOOTH_SOUL_STONE);
        dropSelf(EBlocks.SMOOTH_SOUL_STONE_STAIRS);
        add(EBlocks.SMOOTH_SOUL_STONE_SLAB, createSlabItemTable(EBlocks.SMOOTH_SOUL_STONE_SLAB));
        dropSelf(EBlocks.SMOOTH_SOUL_STONE_WALL);
        dropSelf(EBlocks.CUT_SOUL_STONE);
        dropSelf(EBlocks.CUT_SOUL_STONE_STAIRS);
        add(EBlocks.CUT_SOUL_STONE_SLAB, createSlabItemTable(EBlocks.CUT_SOUL_STONE_SLAB));
        dropSelf(EBlocks.CUT_SOUL_STONE_WALL);
        dropSelf(EBlocks.POLISHED_SOUL_STONE);
        dropSelf(EBlocks.POLISHED_SOUL_STONE_STAIRS);
        add(EBlocks.POLISHED_SOUL_STONE_SLAB, createSlabItemTable(EBlocks.POLISHED_SOUL_STONE_SLAB));
        dropSelf(EBlocks.POLISHED_SOUL_STONE_WALL);
        dropSelf(EBlocks.CHISELED_SOUL_STONE);
        add(EBlocks.SOUL_SILVER_ORE, createMultipleOreDrops(EBlocks.SOUL_SILVER_ORE, EItems.SILVER_NUGGET, 2, 5));
        add(EBlocks.SOUL_QUARTZ_ORE, createOreDrop(EBlocks.SOUL_SILVER_ORE, Items.QUARTZ));

        add(EBlocks.SHORT_SOUL_SPROUT, createShearsOrSilkTouchOnlyDrop(EBlocks.RAW_SOULGLASS));
        add(EBlocks.TALL_SOUL_SPROUT, createShearsOrSilkTouchOnlyDrop(EBlocks.RAW_SOULGLASS));
        add(EBlocks.SOULGLASS_CLUSTER, createSingleItemTableWithSilkTouch(EBlocks.SOULGLASS_CLUSTER, EItems.SOULGLASS_SHARD));
        add(EBlocks.RAW_SOULGLASS, createMultipleOreDrops(EBlocks.RAW_SOULGLASS, EItems.SOULGLASS_SHARD, 2, 4));
        add(EBlocks.SOULGLASS, createSilkTouchOnlyTable(EBlocks.SOULGLASS));

        dropSelf(EBlocks.PINK_SAND);
        dropSelf(EBlocks.PINK_SANDSTONE);
        dropSelf(EBlocks.PINK_SANDSTONE_STAIRS);
        add(EBlocks.PINK_SANDSTONE_SLAB, createSlabItemTable(EBlocks.PINK_SANDSTONE_SLAB));
        dropSelf(EBlocks.PINK_SANDSTONE_WALL);
        dropSelf(EBlocks.SMOOTH_PINK_SANDSTONE);
        dropSelf(EBlocks.SMOOTH_PINK_SANDSTONE_STAIRS);
        add(EBlocks.SMOOTH_PINK_SANDSTONE_SLAB, createSlabItemTable(EBlocks.SMOOTH_PINK_SANDSTONE_SLAB));
        dropSelf(EBlocks.SMOOTH_PINK_SANDSTONE_WALL);
        dropSelf(EBlocks.CUT_PINK_SANDSTONE);
        dropSelf(EBlocks.CUT_PINK_SANDSTONE_STAIRS);
        add(EBlocks.CUT_PINK_SANDSTONE_SLAB, createSlabItemTable(EBlocks.CUT_PINK_SANDSTONE_SLAB));
        dropSelf(EBlocks.CUT_PINK_SANDSTONE_WALL);
        dropSelf(EBlocks.POLISHED_PINK_SANDSTONE);
        dropSelf(EBlocks.POLISHED_PINK_SANDSTONE_STAIRS);
        add(EBlocks.POLISHED_PINK_SANDSTONE_SLAB, createSlabItemTable(EBlocks.POLISHED_PINK_SANDSTONE_SLAB));
        dropSelf(EBlocks.POLISHED_PINK_SANDSTONE_WALL);
        dropSelf(EBlocks.CHISELED_PINK_SANDSTONE);

        dropSelf(EBlocks.BEACHSTONE);
        dropSelf(EBlocks.POLISHED_BEACHSTONE);
        dropSelf(EBlocks.POLISHED_BEACHSTONE_STAIRS);
        add(EBlocks.POLISHED_BEACHSTONE_SLAB, createSlabItemTable(EBlocks.POLISHED_BEACHSTONE_SLAB));
        dropSelf(EBlocks.POLISHED_BEACHSTONE_WALL);
        dropSelf(EBlocks.BEACHSTONE_BRICKS);
        dropSelf(EBlocks.BEACHSTONE_BRICK_STAIRS);
        add(EBlocks.BEACHSTONE_BRICK_SLAB, createSlabItemTable(EBlocks.BEACHSTONE_BRICK_SLAB));
        dropSelf(EBlocks.BEACHSTONE_BRICK_WALL);
        dropSelf(EBlocks.CHISELED_BEACHSTONE);

        dropSelf(EBlocks.LIMESTONE);
        dropSelf(EBlocks.LIMESTONE_STAIRS);
        add(EBlocks.LIMESTONE_SLAB, createSlabItemTable(EBlocks.LIMESTONE_SLAB));
        dropSelf(EBlocks.LIMESTONE_WALL);
        dropSelf(EBlocks.CUT_LIMESTONE);
        dropSelf(EBlocks.CUT_LIMESTONE_STAIRS);
        add(EBlocks.CUT_LIMESTONE_SLAB, createSlabItemTable(EBlocks.CUT_LIMESTONE_SLAB));
        dropSelf(EBlocks.CUT_LIMESTONE_WALL);

        dropSelf(EBlocks.ROCKWEED_BASALT);
        dropSelf(EBlocks.BASALT_VENT);
        dropSelf(EBlocks.SEAGRASS_SAND);

        dropSelf(EBlocks.CHISELED_PURPUR);

        dropSelf(EBlocks.LAPIS_BRICKS);
        dropSelf(EBlocks.LAPIS_BRICK_STAIRS);
        add(EBlocks.LAPIS_BRICK_SLAB, createSlabItemTable(EBlocks.LAPIS_BRICK_SLAB));

        add(EBlocks.AMETHYST_SPIRE, createSingleItemTableWithSilkTouch(EBlocks.AMETHYST_SPIRE, Items.AMETHYST_SHARD));

        dropSelf(EBlocks.PACKED_MUD_STAIRS);
        add(EBlocks.PACKED_MUD_SLAB, createSlabItemTable(EBlocks.PACKED_MUD_SLAB));
        dropSelf(EBlocks.PACKED_MUD_WALL);
        dropSelf(EBlocks.CHISELED_MUD_BRICKS);
        dropSelf(EBlocks.MUD_BRICK_PILLAR);

        dropSelf(EBlocks.ARID_DIRT);
        add(EBlocks.ARID_GRASS_BLOCK, createSingleItemTableWithSilkTouch(EBlocks.ARID_GRASS_BLOCK, EBlocks.ARID_DIRT));

        add(EBlocks.RED_GRASS_BLOCK, createSingleItemTableWithSilkTouch(EBlocks.RED_GRASS_BLOCK, Blocks.DIRT));
        add(EBlocks.SHORT_RED_GRASS, createShearsOrSilkTouchOnlyDrop(EBlocks.SHORT_RED_GRASS));
        add(EBlocks.TALL_RED_GRASS, createDoublePlantShearsOrSilkTouchDrop(EBlocks.TALL_RED_GRASS));

        add(EBlocks.GILDED_BIRCH_LEAVES, createLeavesDrops(EBlocks.GILDED_BIRCH_LEAVES, Blocks.BIRCH_SAPLING, 0.05F));

        dropSelf(EBlocks.RUSTY_MOSS_BLOCK);
        dropSelf(EBlocks.RUSTY_MOSS_CARPET);

        add(EBlocks.SHORT_MOSS_SPOROPHYTE, createShearsOrSilkTouchOnlyDrop(EBlocks.SHORT_MOSS_SPOROPHYTE));
        add(EBlocks.TALL_MOSS_SPOROPHYTE, createSegmentedBlockDrops(EBlocks.TALL_MOSS_SPOROPHYTE));

        add(EBlocks.SHORT_RUSTY_MOSS_SPOROPHYTE, createShearsOrSilkTouchOnlyDrop(EBlocks.SHORT_RUSTY_MOSS_SPOROPHYTE));
        add(EBlocks.TALL_RUSTY_MOSS_SPOROPHYTE, createSegmentedBlockDrops(EBlocks.TALL_RUSTY_MOSS_SPOROPHYTE));

        add(EBlocks.SHORT_PALE_MOSS_SPOROPHYTE, createShearsOrSilkTouchOnlyDrop(EBlocks.SHORT_PALE_MOSS_SPOROPHYTE));
        add(EBlocks.TALL_PALE_MOSS_SPOROPHYTE, createSegmentedBlockDrops(EBlocks.TALL_PALE_MOSS_SPOROPHYTE));

        dropSelf(EBlocks.AZALEA_LOG);
        dropSelf(EBlocks.STRIPPED_AZALEA_LOG);
        dropSelf(EBlocks.AZALEA_WOOD);
        dropSelf(EBlocks.STRIPPED_AZALEA_WOOD);
        dropSelf(EBlocks.AZALEA_PLANKS);
        dropSelf(EBlocks.AZALEA_MOSAIC);
        dropSelf(EBlocks.AZALEA_BOARDS);
        dropSelf(EBlocks.AZALEA_TRIM);
        dropSelf(EBlocks.AZALEA_STAIRS);
        dropSelf(EBlocks.AZALEA_MOSAIC_STAIRS);
        dropSelf(EBlocks.AZALEA_FENCE);
        dropSelf(EBlocks.AZALEA_FENCE_GATE);
        add(EBlocks.AZALEA_SLAB, createSlabItemTable(EBlocks.AZALEA_SLAB));
        add(EBlocks.AZALEA_MOSAIC_SLAB, createSlabItemTable(EBlocks.AZALEA_MOSAIC_SLAB));
        add(EBlocks.AZALEA_DOOR, createDoorTable(EBlocks.AZALEA_DOOR));
        dropSelf(EBlocks.AZALEA_TRAPDOOR);
        dropSelf(EBlocks.AZALEA_PRESSURE_PLATE);
        dropSelf(EBlocks.AZALEA_BUTTON);
        dropOther(EBlocks.AZALEA_SIGN, EItems.AZALEA_SIGN);
        dropOther(EBlocks.AZALEA_WALL_SIGN, EItems.AZALEA_SIGN);
        dropOther(EBlocks.AZALEA_HANGING_SIGN, EItems.AZALEA_HANGING_SIGN);
        dropOther(EBlocks.AZALEA_WALL_HANGING_SIGN, EItems.AZALEA_HANGING_SIGN);
        dropSelf(EBlocks.AZALEA_SHELF);

        dropSelf(EBlocks.CEDAR_LOG);
        dropSelf(EBlocks.STRIPPED_CEDAR_LOG);
        dropSelf(EBlocks.CEDAR_WOOD);
        dropSelf(EBlocks.STRIPPED_CEDAR_WOOD);
        dropSelf(EBlocks.CEDAR_PLANKS);
        dropSelf(EBlocks.CEDAR_MOSAIC);
        dropSelf(EBlocks.CEDAR_BOARDS);
        dropSelf(EBlocks.CEDAR_TRIM);
        dropSelf(EBlocks.CEDAR_STAIRS);
        dropSelf(EBlocks.CEDAR_MOSAIC_STAIRS);
        dropSelf(EBlocks.CEDAR_FENCE);
        dropSelf(EBlocks.CEDAR_FENCE_GATE);
        add(EBlocks.CEDAR_SLAB, createSlabItemTable(EBlocks.CEDAR_SLAB));
        add(EBlocks.CEDAR_MOSAIC_SLAB, createSlabItemTable(EBlocks.CEDAR_MOSAIC_SLAB));
        add(EBlocks.CEDAR_DOOR, createDoorTable(EBlocks.CEDAR_DOOR));
        dropSelf(EBlocks.CEDAR_TRAPDOOR);
        dropSelf(EBlocks.CEDAR_PRESSURE_PLATE);
        dropSelf(EBlocks.CEDAR_BUTTON);
        dropOther(EBlocks.CEDAR_SIGN, EItems.CEDAR_SIGN);
        dropOther(EBlocks.CEDAR_WALL_SIGN, EItems.CEDAR_SIGN);
        dropOther(EBlocks.CEDAR_HANGING_SIGN, EItems.CEDAR_HANGING_SIGN);
        dropOther(EBlocks.CEDAR_WALL_HANGING_SIGN, EItems.CEDAR_HANGING_SIGN);
        dropSelf(EBlocks.CEDAR_SHELF);
        dropSelf(EBlocks.CEDAR_SAPLING);
        add(EBlocks.POTTED_CEDAR_SAPLING, createPotFlowerItemTable(EBlocks.BLUEBONNET));
        add(EBlocks.CEDAR_LEAVES, createLeavesDrops(EBlocks.CEDAR_LEAVES, EBlocks.CEDAR_SAPLING, 0.05F));

        dropSelf(EBlocks.MAHOGANY_LOG);
        dropSelf(EBlocks.STRIPPED_MAHOGANY_LOG);
        dropSelf(EBlocks.MAHOGANY_WOOD);
        dropSelf(EBlocks.STRIPPED_MAHOGANY_WOOD);
        dropSelf(EBlocks.MAHOGANY_PLANKS);
        dropSelf(EBlocks.MAHOGANY_MOSAIC);
        dropSelf(EBlocks.MAHOGANY_BOARDS);
        dropSelf(EBlocks.MAHOGANY_TRIM);
        dropSelf(EBlocks.MAHOGANY_STAIRS);
        dropSelf(EBlocks.MAHOGANY_MOSAIC_STAIRS);
        dropSelf(EBlocks.MAHOGANY_FENCE);
        dropSelf(EBlocks.MAHOGANY_FENCE_GATE);
        add(EBlocks.MAHOGANY_SLAB, createSlabItemTable(EBlocks.MAHOGANY_SLAB));
        add(EBlocks.MAHOGANY_MOSAIC_SLAB, createSlabItemTable(EBlocks.MAHOGANY_MOSAIC_SLAB));
        add(EBlocks.MAHOGANY_DOOR, createDoorTable(EBlocks.MAHOGANY_DOOR));
        dropSelf(EBlocks.MAHOGANY_TRAPDOOR);
        dropSelf(EBlocks.MAHOGANY_PRESSURE_PLATE);
        dropSelf(EBlocks.MAHOGANY_BUTTON);
        dropOther(EBlocks.MAHOGANY_SIGN, EItems.MAHOGANY_SIGN);
        dropOther(EBlocks.MAHOGANY_WALL_SIGN, EItems.MAHOGANY_SIGN);
        dropOther(EBlocks.MAHOGANY_HANGING_SIGN, EItems.MAHOGANY_HANGING_SIGN);
        dropOther(EBlocks.MAHOGANY_WALL_HANGING_SIGN, EItems.MAHOGANY_HANGING_SIGN);
        dropSelf(EBlocks.MAHOGANY_SHELF);
        dropSelf(EBlocks.MAHOGANY_SAPLING);
        add(EBlocks.POTTED_MAHOGANY_SAPLING, createPotFlowerItemTable(EBlocks.BLUEBONNET));
        add(EBlocks.MAHOGANY_LEAVES, createLeavesDrops(EBlocks.MAHOGANY_LEAVES, EBlocks.MAHOGANY_SAPLING, 0.05F));

        dropSelf(EBlocks.LATERITE);

        add(EBlocks.BEJEWELED_CALCITE, createMultipleOreDrops(EBlocks.BEJEWELED_CALCITE, EItems.EMERALD_NUGGET, 3, 5));

        dropSelf(EBlocks.PEBBLE);

        dropSelf(EBlocks.BLUEBONNET);
        add(EBlocks.POTTED_BLUEBONNET, createPotFlowerItemTable(EBlocks.BLUEBONNET));

        dropSelf(EBlocks.RED_HIBISCUS);
        add(EBlocks.POTTED_RED_HIBISCUS, createPotFlowerItemTable(EBlocks.RED_HIBISCUS));
        dropSelf(EBlocks.ORANGE_HIBISCUS);
        add(EBlocks.POTTED_ORANGE_HIBISCUS, createPotFlowerItemTable(EBlocks.ORANGE_HIBISCUS));
        dropSelf(EBlocks.YELLOW_HIBISCUS);
        add(EBlocks.POTTED_YELLOW_HIBISCUS, createPotFlowerItemTable(EBlocks.YELLOW_HIBISCUS));
        dropSelf(EBlocks.BLUE_HIBISCUS);
        add(EBlocks.POTTED_BLUE_HIBISCUS, createPotFlowerItemTable(EBlocks.BLUE_HIBISCUS));
        dropSelf(EBlocks.PURPLE_HIBISCUS);
        add(EBlocks.POTTED_PURPLE_HIBISCUS, createPotFlowerItemTable(EBlocks.PURPLE_HIBISCUS));
        dropSelf(EBlocks.PINK_HIBISCUS);
        add(EBlocks.POTTED_PINK_HIBISCUS, createPotFlowerItemTable(EBlocks.PINK_HIBISCUS));
        dropSelf(EBlocks.WHITE_HIBISCUS);
        add(EBlocks.POTTED_WHITE_HIBISCUS, createPotFlowerItemTable(EBlocks.WHITE_HIBISCUS));

        dropSelf(EBlocks.YELLOW_CONEFLOWER);
        dropSelf(EBlocks.MAGENTA_CONEFLOWER);
        add(EBlocks.POTTED_YELLOW_CONEFLOWER, createPotFlowerItemTable(EBlocks.YELLOW_CONEFLOWER));
        add(EBlocks.POTTED_MAGENTA_CONEFLOWER, createPotFlowerItemTable(EBlocks.MAGENTA_CONEFLOWER));
        add(EBlocks.IRONWEED, createSegmentedBlockDrops(EBlocks.IRONWEED));
        add(EBlocks.CATTAILS, createSegmentedBlockDrops(EBlocks.CATTAILS));
        add(EBlocks.CAVE_WEED, createShearsOrSilkTouchOnlyDrop(EBlocks.CAVE_WEED));
        dropSelf(EBlocks.GLEAMSHROOM);

        add(EBlocks.MOSSY_MUD, createSingleItemTableWithSilkTouch(EBlocks.MOSSY_MUD, Blocks.MUD));
        dropSelf(EBlocks.MUDSTONE);

        dropSelf(EBlocks.OAK_MOSAIC);
        dropSelf(EBlocks.OAK_MOSAIC_STAIRS);
        add(EBlocks.OAK_MOSAIC_SLAB, createSlabItemTable(EBlocks.OAK_MOSAIC_SLAB));
        dropSelf(EBlocks.OAK_BOARDS);
        dropSelf(EBlocks.OAK_TRIM);
        dropSelf(EBlocks.DARK_OAK_MOSAIC);
        dropSelf(EBlocks.DARK_OAK_MOSAIC_STAIRS);
        add(EBlocks.DARK_OAK_MOSAIC_SLAB, createSlabItemTable(EBlocks.DARK_OAK_MOSAIC_SLAB));
        dropSelf(EBlocks.DARK_OAK_BOARDS);
        dropSelf(EBlocks.DARK_OAK_TRIM);
        dropSelf(EBlocks.PALE_OAK_MOSAIC);
        dropSelf(EBlocks.PALE_OAK_MOSAIC_STAIRS);
        add(EBlocks.PALE_OAK_MOSAIC_SLAB, createSlabItemTable(EBlocks.PALE_OAK_MOSAIC_SLAB));
        dropSelf(EBlocks.PALE_OAK_BOARDS);
        dropSelf(EBlocks.PALE_OAK_TRIM);
        dropSelf(EBlocks.BIRCH_MOSAIC);
        dropSelf(EBlocks.BIRCH_MOSAIC_STAIRS);
        add(EBlocks.BIRCH_MOSAIC_SLAB, createSlabItemTable(EBlocks.BIRCH_MOSAIC_SLAB));
        dropSelf(EBlocks.BIRCH_BOARDS);
        dropSelf(EBlocks.BIRCH_TRIM);
        dropSelf(EBlocks.SPRUCE_MOSAIC);
        dropSelf(EBlocks.SPRUCE_MOSAIC_STAIRS);
        add(EBlocks.SPRUCE_MOSAIC_SLAB, createSlabItemTable(EBlocks.SPRUCE_MOSAIC_SLAB));
        dropSelf(EBlocks.SPRUCE_BOARDS);
        dropSelf(EBlocks.SPRUCE_TRIM);
        dropSelf(EBlocks.JUNGLE_MOSAIC);
        dropSelf(EBlocks.JUNGLE_MOSAIC_STAIRS);
        add(EBlocks.JUNGLE_MOSAIC_SLAB, createSlabItemTable(EBlocks.JUNGLE_MOSAIC_SLAB));
        dropSelf(EBlocks.JUNGLE_BOARDS);
        dropSelf(EBlocks.JUNGLE_TRIM);
        dropSelf(EBlocks.ACACIA_MOSAIC);
        dropSelf(EBlocks.ACACIA_MOSAIC_STAIRS);
        add(EBlocks.ACACIA_MOSAIC_SLAB, createSlabItemTable(EBlocks.ACACIA_MOSAIC_SLAB));
        dropSelf(EBlocks.ACACIA_BOARDS);
        dropSelf(EBlocks.ACACIA_TRIM);
        dropSelf(EBlocks.CHERRY_MOSAIC);
        dropSelf(EBlocks.CHERRY_MOSAIC_STAIRS);
        add(EBlocks.CHERRY_MOSAIC_SLAB, createSlabItemTable(EBlocks.CHERRY_MOSAIC_SLAB));
        dropSelf(EBlocks.CHERRY_BOARDS);
        dropSelf(EBlocks.CHERRY_TRIM);
        dropSelf(EBlocks.MANGROVE_MOSAIC);
        dropSelf(EBlocks.MANGROVE_MOSAIC_STAIRS);
        add(EBlocks.MANGROVE_MOSAIC_SLAB, createSlabItemTable(EBlocks.MANGROVE_MOSAIC_SLAB));
        dropSelf(EBlocks.MANGROVE_BOARDS);
        dropSelf(EBlocks.MANGROVE_TRIM);
        dropSelf(EBlocks.CRIMSON_MOSAIC);
        dropSelf(EBlocks.CRIMSON_MOSAIC_STAIRS);
        add(EBlocks.CRIMSON_MOSAIC_SLAB, createSlabItemTable(EBlocks.CRIMSON_MOSAIC_SLAB));
        dropSelf(EBlocks.CRIMSON_BOARDS);
        dropSelf(EBlocks.CRIMSON_TRIM);
        dropSelf(EBlocks.WARPED_MOSAIC);
        dropSelf(EBlocks.WARPED_MOSAIC_STAIRS);
        add(EBlocks.WARPED_MOSAIC_SLAB, createSlabItemTable(EBlocks.WARPED_MOSAIC_SLAB));
        dropSelf(EBlocks.WARPED_BOARDS);
        dropSelf(EBlocks.WARPED_TRIM);

        dropSelf(EBlocks.BAMBOO_STEM);
        dropSelf(EBlocks.STRIPPED_BAMBOO_STEM);
        dropSelf(EBlocks.BAMBOO_BOARDS);
        dropSelf(EBlocks.BAMBOO_TRIM);

        dropSelf(EBlocks.TERRACOTTA_STAIRS);
        add(EBlocks.TERRACOTTA_SLAB, createSlabItemTable(EBlocks.TERRACOTTA_SLAB));
        dropSelf(EBlocks.TERRACOTTA_BRICKS);
        dropSelf(EBlocks.TERRACOTTA_BRICK_STAIRS);
        add(EBlocks.TERRACOTTA_BRICK_SLAB, createSlabItemTable(EBlocks.TERRACOTTA_BRICK_SLAB));
        dropSelf(EBlocks.TERRACOTTA_BRICK_TILES);
        dropSelf(EBlocks.TERRACOTTA_BRICK_TILE_STAIRS);
        add(EBlocks.TERRACOTTA_BRICK_TILE_SLAB, createSlabItemTable(EBlocks.TERRACOTTA_BRICK_TILE_SLAB));
        EBlocks.DYED_TERRACOTTA_STAIRS.forEach(this::dropSelf);
        EBlocks.DYED_TERRACOTTA_SLAB.forEach(slab -> add(slab, createSlabItemTable(slab)));
        EBlocks.DYED_TERRACOTTA_BRICKS.forEach(this::dropSelf);
        EBlocks.DYED_TERRACOTTA_BRICK_STAIRS.forEach(this::dropSelf);
        EBlocks.DYED_TERRACOTTA_BRICK_SLAB.forEach(slab -> add(slab, createSlabItemTable(slab)));
        EBlocks.DYED_TERRACOTTA_BRICK_TILES.forEach(this::dropSelf);
        EBlocks.DYED_TERRACOTTA_BRICK_TILE_STAIRS.forEach(this::dropSelf);
        EBlocks.DYED_TERRACOTTA_BRICK_TILE_SLAB.forEach(slab -> add(slab, createSlabItemTable(slab)));
    }

    // Secondary Ore Method
    public LootTable.Builder createMultipleOreDrops(final Block block, final Item drop, int min, int max) {
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(drop)
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(min, max)))
                        .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder createDoublePlantShearsOrSilkTouchDrop(final Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(this.hasShearsOrSilkTouch()).add(LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(2)))));
    }
}
