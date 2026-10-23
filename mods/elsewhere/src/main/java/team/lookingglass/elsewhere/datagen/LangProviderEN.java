package team.lookingglass.elsewhere.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LangProviderEN extends FabricLanguageProvider {
    public LangProviderEN(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider wrapperLookup, TranslationBuilder translationBuilder) {
        // Translation Changes
        translationBuilder.add("item.minecraft.quartz", "Quartz");
        translationBuilder.add("item.minecraft.netherite_ingot", "Netherite Alloy");
        translationBuilder.add("item.minecraft.smithing_template.netherite_upgrade.additions_slot_description", "Add Netherite Alloy");
        translationBuilder.add("item.minecraft.smithing_template.netherite_upgrade.ingredients", "Netherite Alloy");

        // Quartz Brick Fill
        translationBuilder.add("block.elsewhere.quartz_brick_stairs", "Quartz Brick Stairs");
        translationBuilder.add("block.elsewhere.quartz_brick_slab", "Quartz Brick Slab");

        // Amethyst-Obsidian Set
        translationBuilder.add("block.elsewhere.amethyst_spire", "Amethyst Spire");
        translationBuilder.add("block.elsewhere.amethyst_stairs", "Amethyst Stairs");
        translationBuilder.add("block.elsewhere.amethyst_slab", "Amethyst Slab");
        translationBuilder.add("block.elsewhere.amethyst_bricks", "Amethyst Bricks");
        translationBuilder.add("block.elsewhere.amethyst_brick_stairs", "Amethyst Brick Stairs");
        translationBuilder.add("block.elsewhere.amethyst_brick_slab", "Amethyst Brick Slab");
        translationBuilder.add("block.elsewhere.amethyst_pillar", "Amethyst Pillar");
        translationBuilder.add("block.elsewhere.chiseled_amethyst", "Chiseled Amethyst");

        translationBuilder.add("block.elsewhere.obsidian_stairs", "Obsidian Stairs");
        translationBuilder.add("block.elsewhere.obsidian_slab", "Obsidian Slab");
        translationBuilder.add("block.elsewhere.obsidian_bricks", "Obsidian Bricks");
        translationBuilder.add("block.elsewhere.obsidian_brick_stairs", "Obsidian Brick Stairs");
        translationBuilder.add("block.elsewhere.obsidian_brick_slab", "Obsidian Brick Slab");
        translationBuilder.add("block.elsewhere.obsidian_pillar", "Obsidian Pillar");
        translationBuilder.add("block.elsewhere.chiseled_obsidian", "Chiseled Obsidian");

        // Stone Sets
        translationBuilder.add("block.elsewhere.stone_wall", "Stone Wall");
        translationBuilder.add("block.elsewhere.smooth_stone_stairs", "Smooth Stone Stairs");
        translationBuilder.add("block.elsewhere.smooth_stone_wall", "Smooth Stone Wall");
        translationBuilder.add("block.elsewhere.polished_stone", "Polished Stone");
        translationBuilder.add("block.elsewhere.polished_stone_stairs", "Polished Stone Stairs");
        translationBuilder.add("block.elsewhere.polished_stone_slab", "Polished Stone Slab");
        translationBuilder.add("block.elsewhere.polished_stone_wall", "Polished Stone Wall");
        translationBuilder.add("block.elsewhere.stone_tiles", "Stone Tiles");
        translationBuilder.add("block.elsewhere.stone_tile_stairs", "Stone Tile Stairs");
        translationBuilder.add("block.elsewhere.stone_tile_slab", "Stone Tile Slab");
        translationBuilder.add("block.elsewhere.stone_tile_wall", "Stone Tile Wall");
        translationBuilder.add("block.elsewhere.mossy_stone_tiles", "Mossy Stone Tiles");
        translationBuilder.add("block.elsewhere.mossy_stone_tile_stairs", "Mossy Stone Tile Stairs");
        translationBuilder.add("block.elsewhere.mossy_stone_tile_slab", "Mossy Stone Tile Slab");
        translationBuilder.add("block.elsewhere.mossy_stone_tile_wall", "Mossy Stone Tile Wall");
        translationBuilder.add("block.elsewhere.chiseled_stone", "Chiseled Stone");
        translationBuilder.add("block.elsewhere.stone_pillar", "Stone Pillar");

        translationBuilder.add("block.elsewhere.deepslate_stairs", "Deepslate Stairs");
        translationBuilder.add("block.elsewhere.deepslate_slab", "Deepslate Slab");
        translationBuilder.add("block.elsewhere.deepslate_wall", "Deepslate Wall");
        translationBuilder.add("block.elsewhere.chiseled_deepslate_bricks", "Chiseled Deepslate Bricks");
        translationBuilder.add("block.elsewhere.deepslate_pillar", "Deepslate Pillar");

        translationBuilder.add("block.elsewhere.tuff_pillar", "Tuff Pillar");
        translationBuilder.add("block.elsewhere.blackstone_pillar", "Blackstone Pillar");

        translationBuilder.add("block.elsewhere.polished_andesite_wall", "Polished Andesite Wall");
        translationBuilder.add("block.elsewhere.andesite_bricks", "Andesite Bricks");
        translationBuilder.add("block.elsewhere.andesite_brick_stairs", "Andesite Brick Stairs");
        translationBuilder.add("block.elsewhere.andesite_brick_slab", "Andesite Brick Slab");
        translationBuilder.add("block.elsewhere.andesite_brick_wall", "Andesite Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_andesite", "Chiseled Andesite");
        translationBuilder.add("block.elsewhere.andesite_pillar", "Andesite Pillar");

        translationBuilder.add("block.elsewhere.polished_granite_wall", "Polished Granite Wall");
        translationBuilder.add("block.elsewhere.granite_bricks", "Granite Bricks");
        translationBuilder.add("block.elsewhere.granite_brick_stairs", "Granite Brick Stairs");
        translationBuilder.add("block.elsewhere.granite_brick_slab", "Granite Brick Slab");
        translationBuilder.add("block.elsewhere.granite_brick_wall", "Granite Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_granite", "Chiseled Granite");

        translationBuilder.add("block.elsewhere.polished_diorite_wall", "Polished Diorite Wall");
        translationBuilder.add("block.elsewhere.diorite_bricks", "Diorite Bricks");
        translationBuilder.add("block.elsewhere.diorite_brick_stairs", "Diorite Brick Stairs");
        translationBuilder.add("block.elsewhere.diorite_brick_slab", "Diorite Brick Slab");
        translationBuilder.add("block.elsewhere.diorite_brick_wall", "Diorite Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_diorite", "Chiseled Diorite");

        translationBuilder.add("block.elsewhere.calcite_stairs", "Calcite Stairs");
        translationBuilder.add("block.elsewhere.calcite_slab", "Calcite Slab");
        translationBuilder.add("block.elsewhere.calcite_wall", "Calcite Wall");
        translationBuilder.add("block.elsewhere.polished_calcite", "Polished Calcite");
        translationBuilder.add("block.elsewhere.polished_calcite_stairs", "Polished Calcite Stairs");
        translationBuilder.add("block.elsewhere.polished_calcite_slab", "Polished Calcite Slab");
        translationBuilder.add("block.elsewhere.polished_calcite_wall", "Polished Calcite Wall");
        translationBuilder.add("block.elsewhere.calcite_bricks", "Calcite Bricks");
        translationBuilder.add("block.elsewhere.calcite_brick_stairs", "Calcite Brick Stairs");
        translationBuilder.add("block.elsewhere.calcite_brick_slab", "Calcite Brick Slab");
        translationBuilder.add("block.elsewhere.calcite_brick_wall", "Calcite Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_calcite", "Chiseled Calcite");
        translationBuilder.add("block.elsewhere.chiseled_calcite_bricks", "Chiseled Calcite Bricks");
        translationBuilder.add("block.elsewhere.calcite_pillar", "Calcite Pillar");

        translationBuilder.add("block.elsewhere.dripstone_stairs", "Dripstone Stairs");
        translationBuilder.add("block.elsewhere.dripstone_slab", "Dripstone Slab");
        translationBuilder.add("block.elsewhere.dripstone_wall", "Dripstone Wall");
        translationBuilder.add("block.elsewhere.polished_dripstone", "Polished Dripstone");
        translationBuilder.add("block.elsewhere.polished_dripstone_stairs", "Polished Dripstone Stairs");
        translationBuilder.add("block.elsewhere.polished_dripstone_slab", "Polished Dripstone Slab");
        translationBuilder.add("block.elsewhere.polished_dripstone_wall", "Polished Dripstone Wall");
        translationBuilder.add("block.elsewhere.dripstone_tiles", "Dripstone Tiles");
        translationBuilder.add("block.elsewhere.dripstone_tile_stairs", "Dripstone Tile Stairs");
        translationBuilder.add("block.elsewhere.dripstone_tile_slab", "Dripstone Tile Slab");
        translationBuilder.add("block.elsewhere.dripstone_tile_wall", "Dripstone Tile Wall");
        translationBuilder.add("block.elsewhere.chiseled_dripstone_tiles", "Chiseled Dripstone Tiles");
        translationBuilder.add("block.elsewhere.dripstone_pillar", "Dripstone Pillar");

        translationBuilder.add("block.elsewhere.polished_sandstone", "Polished Sandstone");
        translationBuilder.add("block.elsewhere.polished_sandstone_stairs", "Polished Sandstone Stairs");
        translationBuilder.add("block.elsewhere.polished_sandstone_slab", "Polished Sandstone Slab");
        translationBuilder.add("block.elsewhere.polished_sandstone_wall", "Polished Sandstone Wall");
        translationBuilder.add("block.elsewhere.polished_red_sandstone", "Polished Red Sandstone");
        translationBuilder.add("block.elsewhere.polished_red_sandstone_stairs", "Polished Red Sandstone Stairs");
        translationBuilder.add("block.elsewhere.polished_red_sandstone_slab", "Polished Red Sandstone Slab");
        translationBuilder.add("block.elsewhere.polished_red_sandstone_wall", "Polished Red Sandstone Wall");

        translationBuilder.add("block.elsewhere.smooth_sandstone_wall", "Smooth Sandstone Wall");
        translationBuilder.add("block.elsewhere.smooth_red_sandstone_wall", "Smooth Red Sandstone Wall");

        translationBuilder.add("block.elsewhere.cut_sandstone_stairs", "Cut Sandstone Stairs");
        translationBuilder.add("block.elsewhere.cut_sandstone_wall", "Cut Sandstone Wall");
        translationBuilder.add("block.elsewhere.cut_red_sandstone_stairs", "Cut Red Sandstone Stairs");
        translationBuilder.add("block.elsewhere.cut_red_sandstone_wall", "Cut Red Sandstone Wall");

        translationBuilder.add("block.elsewhere.polished_prismarine", "Polished Prismarine");
        translationBuilder.add("block.elsewhere.polished_prismarine_stairs", "Polished Prismarine Stairs");
        translationBuilder.add("block.elsewhere.polished_prismarine_slab", "Polished Prismarine Slab");
        translationBuilder.add("block.elsewhere.polished_prismarine_wall", "Polished Prismarine Wall");
        translationBuilder.add("block.elsewhere.prismarine_brick_wall", "Prismarine Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_prismarine", "Chiseled Prismarine");
        translationBuilder.add("block.elsewhere.prismarine_pillar", "Prismarine Pillar");

        translationBuilder.add("block.elsewhere.dark_prismarine_wall", "Dark Prismarine Wall");
        translationBuilder.add("block.elsewhere.polished_dark_prismarine", "Polished Dark Prismarine");
        translationBuilder.add("block.elsewhere.polished_dark_prismarine_stairs", "Polished Dark Prismarine Stairs");
        translationBuilder.add("block.elsewhere.polished_dark_prismarine_slab", "Polished Dark Prismarine Slab");
        translationBuilder.add("block.elsewhere.polished_dark_prismarine_wall", "Polished Dark Prismarine Wall");
        translationBuilder.add("block.elsewhere.dark_prismarine_scales", "Dark Prismarine Scales");
        translationBuilder.add("block.elsewhere.dark_prismarine_scale_stairs", "Dark Prismarine Scale Stairs");
        translationBuilder.add("block.elsewhere.dark_prismarine_scale_slab", "Dark Prismarine Scale Slab");
        translationBuilder.add("block.elsewhere.dark_prismarine_scale_wall", "Dark Prismarine Scale Wall");
        translationBuilder.add("block.elsewhere.chiseled_dark_prismarine", "Chiseled Dark Prismarine");

        translationBuilder.add("block.elsewhere.basalt_slab", "Basalt Slab");
        translationBuilder.add("block.elsewhere.smooth_basalt_stairs", "Smooth Basalt Stairs");
        translationBuilder.add("block.elsewhere.smooth_basalt_slab", "Smooth Basalt Slab");
        translationBuilder.add("block.elsewhere.smooth_basalt_wall", "Smooth Basalt Wall");
        translationBuilder.add("block.elsewhere.polished_basalt_slab", "Polished Basalt Slab");

        translationBuilder.add("block.elsewhere.polished_smooth_basalt", "Polished Smooth Basalt");
        translationBuilder.add("block.elsewhere.polished_smooth_basalt_stairs", "Polished Smooth Basalt Stairs");
        translationBuilder.add("block.elsewhere.polished_smooth_basalt_slab", "Polished Smooth Basalt Slab");
        translationBuilder.add("block.elsewhere.polished_smooth_basalt_wall", "Polished Smooth Basalt Wall");
        translationBuilder.add("block.elsewhere.smooth_basalt_bricks", "Smooth Basalt Bricks");
        translationBuilder.add("block.elsewhere.smooth_basalt_brick_stairs", "Smooth Basalt Brick Stairs");
        translationBuilder.add("block.elsewhere.smooth_basalt_brick_slab", "Smooth Basalt Brick Slab");
        translationBuilder.add("block.elsewhere.smooth_basalt_brick_wall", "Smooth Basalt Brick Wall");
        translationBuilder.add("block.elsewhere.smooth_basalt_tiles", "Smooth Basalt Tiles");
        translationBuilder.add("block.elsewhere.smooth_basalt_tile_stairs", "Smooth Basalt Tile Stairs");
        translationBuilder.add("block.elsewhere.smooth_basalt_tile_slab", "Smooth Basalt Tile Slab");
        translationBuilder.add("block.elsewhere.smooth_basalt_tile_wall", "Smooth Basalt Tile Wall");



        translationBuilder.add("block.elsewhere.end_stone_stairs", "End Stone Stairs");
        translationBuilder.add("block.elsewhere.end_stone_slab", "End Stone Slab");
        translationBuilder.add("block.elsewhere.end_stone_wall", "End Stone Wall");
        translationBuilder.add("block.elsewhere.polished_end_stone", "Polished End Stone");
        translationBuilder.add("block.elsewhere.polished_end_stone_stairs", "Polished End Stone Stairs");
        translationBuilder.add("block.elsewhere.polished_end_stone_slab", "Polished End Stone Slab");
        translationBuilder.add("block.elsewhere.polished_end_stone_wall", "Polished End Stone Wall");
        translationBuilder.add("block.elsewhere.chiseled_end_stone", "Chiseled End Stone");
        translationBuilder.add("block.elsewhere.end_stone_pillar", "End Stone Pillar");

        translationBuilder.add("block.elsewhere.sodalite", "Sodalite");
        translationBuilder.add("block.elsewhere.sodalite_stairs", "Sodalite Stairs");
        translationBuilder.add("block.elsewhere.sodalite_slab", "Sodalite Slab");
        translationBuilder.add("block.elsewhere.sodalite_wall", "Sodalite Wall");
        translationBuilder.add("block.elsewhere.polished_sodalite", "Polished Sodalite");
        translationBuilder.add("block.elsewhere.polished_sodalite_stairs", "Polished Sodalite Stairs");
        translationBuilder.add("block.elsewhere.polished_sodalite_slab", "Polished Sodalite Slab");
        translationBuilder.add("block.elsewhere.polished_sodalite_wall", "Polished Sodalite Wall");
        translationBuilder.add("block.elsewhere.sodalite_bricks", "Sodalite Bricks");
        translationBuilder.add("block.elsewhere.sodalite_brick_stairs", "Sodalite Brick Stairs");
        translationBuilder.add("block.elsewhere.sodalite_brick_slab", "Sodalite Brick Slab");
        translationBuilder.add("block.elsewhere.sodalite_brick_wall", "Sodalite Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_sodalite", "Chiseled Sodalite");
        translationBuilder.add("block.elsewhere.chiseled_sodalite_bricks", "Chiseled Sodalite Bricks");
        translationBuilder.add("block.elsewhere.sodalite_pillar", "Sodalite Pillar");

        translationBuilder.add("block.elsewhere.shale", "Shale");
        translationBuilder.add("block.elsewhere.shale_stairs", "Shale Stairs");
        translationBuilder.add("block.elsewhere.shale_slab", "Shale Slab");
        translationBuilder.add("block.elsewhere.shale_wall", "Shale Wall");
        translationBuilder.add("block.elsewhere.polished_shale", "Polished Shale");
        translationBuilder.add("block.elsewhere.polished_shale_stairs", "Polished Shale Stairs");
        translationBuilder.add("block.elsewhere.polished_shale_slab", "Polished Shale Slab");
        translationBuilder.add("block.elsewhere.polished_shale_wall", "Polished Shale Wall");
        translationBuilder.add("block.elsewhere.shale_bricks", "Shale Bricks");
        translationBuilder.add("block.elsewhere.shale_brick_stairs", "Shale Brick Stairs");
        translationBuilder.add("block.elsewhere.shale_brick_slab", "Shale Brick Slab");
        translationBuilder.add("block.elsewhere.shale_brick_wall", "Shale Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_shale", "Chiseled Shale");
        translationBuilder.add("block.elsewhere.chiseled_shale_bricks", "Chiseled Shale Bricks");
        translationBuilder.add("block.elsewhere.shale_pillar", "Shale Pillar");

        translationBuilder.add("block.elsewhere.sulfur_pillar", "Sulfur Pillar");
        translationBuilder.add("block.elsewhere.cinnabar_pillar", "Cinnabar Pillar");

        translationBuilder.add("block.elsewhere.cinnabar_coal_ore", "Cinnabar Coal Ore");
        translationBuilder.add("block.elsewhere.cinnabar_copper_ore", "Cinnabar Copper Ore");
        translationBuilder.add("block.elsewhere.cinnabar_iron_ore", "Cinnabar Iron Ore");
        translationBuilder.add("block.elsewhere.cinnabar_gold_ore", "Cinnabar Gold Ore");
        translationBuilder.add("block.elsewhere.cinnabar_redstone_ore", "Cinnabar Redstone Ore");
        translationBuilder.add("block.elsewhere.cinnabar_diamond_ore", "Cinnabar Diamond Ore");
        translationBuilder.add("block.elsewhere.cinnabar_lapis_ore", "Cinnabar Lapis Lazuli Ore");
        translationBuilder.add("block.elsewhere.cinnabar_emerald_ore", "Cinnabar Emerald Ore");

        translationBuilder.add("block.elsewhere.orpiment", "Orpiment");
        translationBuilder.add("block.elsewhere.orpiment_stairs", "Orpiment Stairs");
        translationBuilder.add("block.elsewhere.orpiment_slab", "Orpiment Slab");
        translationBuilder.add("block.elsewhere.orpiment_wall", "Orpiment Wall");
        translationBuilder.add("block.elsewhere.polished_orpiment", "Polished Orpiment");
        translationBuilder.add("block.elsewhere.polished_orpiment_stairs", "Polished Orpiment Stairs");
        translationBuilder.add("block.elsewhere.polished_orpiment_slab", "Polished Orpiment Slab");
        translationBuilder.add("block.elsewhere.polished_orpiment_wall", "Polished Orpiment Wall");
        translationBuilder.add("block.elsewhere.orpiment_bricks", "Orpiment Bricks");
        translationBuilder.add("block.elsewhere.orpiment_brick_stairs", "Orpiment Brick Stairs");
        translationBuilder.add("block.elsewhere.orpiment_brick_slab", "Orpiment Brick Slab");
        translationBuilder.add("block.elsewhere.orpiment_brick_wall", "Orpiment Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_orpiment", "Chiseled Orpiment");
        translationBuilder.add("block.elsewhere.orpiment_pillar", "Orpiment Pillar");

        translationBuilder.add("block.elsewhere.netherrack_stairs", "Netherrack Stairs");
        translationBuilder.add("block.elsewhere.netherrack_slab", "Netherrack Slab");
        translationBuilder.add("block.elsewhere.netherrack_wall", "Netherrack Wall");
        translationBuilder.add("block.elsewhere.polished_netherrack", "Polished Netherrack");
        translationBuilder.add("block.elsewhere.polished_netherrack_stairs", "Polished Netherrack Stairs");
        translationBuilder.add("block.elsewhere.polished_netherrack_slab", "Polished Netherrack Slab");
        translationBuilder.add("block.elsewhere.polished_netherrack_wall", "Polished Netherrack Wall");
        translationBuilder.add("block.elsewhere.netherrack_bricks", "Netherrack Bricks");
        translationBuilder.add("block.elsewhere.netherrack_brick_stairs", "Netherrack Brick Stairs");
        translationBuilder.add("block.elsewhere.netherrack_brick_slab", "Netherrack Brick Slab");
        translationBuilder.add("block.elsewhere.netherrack_brick_wall", "Netherrack Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_netherrack", "Chiseled Netherrack");
        translationBuilder.add("block.elsewhere.chiseled_netherrack_bricks", "Chiseled Netherrack Bricks");
        translationBuilder.add("block.elsewhere.netherrack_pillar", "Netherrack Pillar");

        translationBuilder.add("block.elsewhere.soul_stone", "Soul Stone");
        translationBuilder.add("block.elsewhere.soul_stone_stairs", "Soul Stone Stairs");
        translationBuilder.add("block.elsewhere.soul_stone_slab", "Soul Stone Slab");
        translationBuilder.add("block.elsewhere.soul_stone_wall", "Soul Stone Wall");
        translationBuilder.add("block.elsewhere.smooth_soul_stone", "Smooth Soul Stone");
        translationBuilder.add("block.elsewhere.smooth_soul_stone_stairs", "Smooth Soul Stone Stairs");
        translationBuilder.add("block.elsewhere.smooth_soul_stone_slab", "Smooth Soul Stone Slab");
        translationBuilder.add("block.elsewhere.smooth_soul_stone_wall", "Smooth Soul Stone Wall");
        translationBuilder.add("block.elsewhere.cut_soul_stone", "Cut Soul Stone");
        translationBuilder.add("block.elsewhere.cut_soul_stone_stairs", "Cut Soul Stone Stairs");
        translationBuilder.add("block.elsewhere.cut_soul_stone_slab", "Cut Soul Stone Slab");
        translationBuilder.add("block.elsewhere.cut_soul_stone_wall", "Cut Soul Stone Wall");
        translationBuilder.add("block.elsewhere.polished_soul_stone", "Polished Soul Stone");
        translationBuilder.add("block.elsewhere.polished_soul_stone_stairs", "Polished Soul Stone Stairs");
        translationBuilder.add("block.elsewhere.polished_soul_stone_slab", "Polished Soul Stone Slab");
        translationBuilder.add("block.elsewhere.polished_soul_stone_wall", "Polished Soul Stone Wall");
        translationBuilder.add("block.elsewhere.chiseled_soul_stone", "Chiseled Soul Stone");
        translationBuilder.add("block.elsewhere.soul_silver_ore", "Soul Silver Ore");
        translationBuilder.add("block.elsewhere.soul_quartz_ore", "Soul Quartz Ore");

        translationBuilder.add("block.elsewhere.short_soul_sprout", "Short Soul Sprout");
        translationBuilder.add("block.elsewhere.tall_soul_sprout", "Tall Soul Sprout");
        translationBuilder.add("item.elsewhere.soulglass_shard", "Soulglass Shard");
        translationBuilder.add("block.elsewhere.soulglass_cluster", "Soulglass Cluster");
        translationBuilder.add("block.elsewhere.raw_soulglass", "Raw Soulglass");
        translationBuilder.add("block.elsewhere.soulglass", "Soulglass");

        translationBuilder.add("block.elsewhere.pink_sand", "Pink Sand");
        translationBuilder.add("block.elsewhere.pink_sandstone", "Pink Sandstone");
        translationBuilder.add("block.elsewhere.pink_sandstone_stairs", "Pink Sandstone Stairs");
        translationBuilder.add("block.elsewhere.pink_sandstone_slab", "Pink Sandstone Slab");
        translationBuilder.add("block.elsewhere.pink_sandstone_wall", "Pink Sandstone Wall");
        translationBuilder.add("block.elsewhere.smooth_pink_sandstone", "Smooth Pink Sandstone");
        translationBuilder.add("block.elsewhere.smooth_pink_sandstone_stairs", "Smooth Pink Sandstone Stairs");
        translationBuilder.add("block.elsewhere.smooth_pink_sandstone_slab", "Smooth Pink Sandstone Slab");
        translationBuilder.add("block.elsewhere.smooth_pink_sandstone_wall", "Smooth Pink Sandstone Wall");
        translationBuilder.add("block.elsewhere.cut_pink_sandstone", "Cut Pink Sandstone");
        translationBuilder.add("block.elsewhere.cut_pink_sandstone_stairs", "Cut Pink Sandstone Stairs");
        translationBuilder.add("block.elsewhere.cut_pink_sandstone_slab", "Cut Pink Sandstone Slab");
        translationBuilder.add("block.elsewhere.cut_pink_sandstone_wall", "Cut Pink Sandstone Wall");
        translationBuilder.add("block.elsewhere.polished_pink_sandstone", "Polished Pink Sandstone");
        translationBuilder.add("block.elsewhere.polished_pink_sandstone_stairs", "Polished Pink Sandstone Stairs");
        translationBuilder.add("block.elsewhere.polished_pink_sandstone_slab", "Polished Pink Sandstone Slab");
        translationBuilder.add("block.elsewhere.polished_pink_sandstone_wall", "Polished Pink Sandstone Wall");
        translationBuilder.add("block.elsewhere.chiseled_pink_sandstone", "Chiseled Pink Sandstone");

        translationBuilder.add("block.elsewhere.beachstone", "Beachstone");
        translationBuilder.add("block.elsewhere.beachstone_stairs", "Beachstone Stairs");
        translationBuilder.add("block.elsewhere.beachstone_slab", "Beachstone Slab");
        translationBuilder.add("block.elsewhere.beachstone_wall", "Beachstone Wall");
        translationBuilder.add("block.elsewhere.polished_beachstone", "Polished Beachstone");
        translationBuilder.add("block.elsewhere.polished_beachstone_stairs", "Polished Beachstone Stairs");
        translationBuilder.add("block.elsewhere.polished_beachstone_slab", "Polished Beachstone Slab");
        translationBuilder.add("block.elsewhere.polished_beachstone_wall", "Polished Beachstone Wall");
        translationBuilder.add("block.elsewhere.beachstone_bricks", "Beachstone Bricks");
        translationBuilder.add("block.elsewhere.beachstone_brick_stairs", "Beachstone Brick Stairs");
        translationBuilder.add("block.elsewhere.beachstone_brick_slab", "Beachstone Brick Slab");
        translationBuilder.add("block.elsewhere.beachstone_brick_wall", "Beachstone Brick Wall");
        translationBuilder.add("block.elsewhere.chiseled_beachstone", "Chiseled Beachstone");

        translationBuilder.add("block.elsewhere.limestone", "Limestone");
        translationBuilder.add("block.elsewhere.limestone_stairs", "Limestone Stairs");
        translationBuilder.add("block.elsewhere.limestone_slab", "Limestone Slab");
        translationBuilder.add("block.elsewhere.limestone_wall", "Limestone Wall");
        translationBuilder.add("block.elsewhere.cut_limestone", "Cut Limestone");
        translationBuilder.add("block.elsewhere.cut_limestone_stairs", "Cut Limestone Stairs");
        translationBuilder.add("block.elsewhere.cut_limestone_slab", "Cut Limestone Slab");
        translationBuilder.add("block.elsewhere.cut_limestone_wall", "Cut Limestone Wall");

        translationBuilder.add("block.elsewhere.rockweed_basalt", "Rockweed Basalt");
        translationBuilder.add("block.elsewhere.basalt_vent", "Basalt Vent");
        translationBuilder.add("block.elsewhere.seagrass_sand", "Seagrass Sand");

        translationBuilder.add("block.elsewhere.chiseled_purpur", "Chiseled Purpur");

        translationBuilder.add("block.elsewhere.lapis_bricks", "Lapis Bricks");
        translationBuilder.add("block.elsewhere.lapis_brick_stairs", "Lapis Brick Stairs");
        translationBuilder.add("block.elsewhere.lapis_brick_slab", "Lapis Brick Slab");

        translationBuilder.add("block.elsewhere.packed_mud_stairs", "Packed Mud Stairs");
        translationBuilder.add("block.elsewhere.packed_mud_slab", "Packed Mud Slab");
        translationBuilder.add("block.elsewhere.packed_mud_wall", "Packed Mud Wall");
        translationBuilder.add("block.elsewhere.chiseled_mud_bricks", "Chiseled Mud Bricks");
        translationBuilder.add("block.elsewhere.mud_brick_pillar", "Mud Brick Pillar");

        translationBuilder.add("block.elsewhere.mossy_mud", "Mossy Mud");
        translationBuilder.add("block.elsewhere.mudstone", "Mudstone");

        translationBuilder.add("block.elsewhere.dungeon_spawner", "Dungeon Spawner");
        translationBuilder.add("block.elsewhere.dungeon_vault", "Dungeon Vault");

        translationBuilder.add("item.elsewhere.dungeon_key", "Dungeon Key");
        translationBuilder.add("item.elsewhere.ominous_dungeon_key", "Ominous Dungeon Key");

        translationBuilder.add("block.elsewhere.oak_mosaic", "Oak Mosaic");
        translationBuilder.add("block.elsewhere.oak_mosaic_stairs", "Oak Mosaic Stairs");
        translationBuilder.add("block.elsewhere.oak_mosaic_slab", "Oak Mosaic Slab");
        translationBuilder.add("block.elsewhere.oak_boards", "Oak Boards");
        translationBuilder.add("block.elsewhere.oak_trim", "Oak Trim");

        translationBuilder.add("block.elsewhere.dark_oak_mosaic", "Dark Oak Mosaic");
        translationBuilder.add("block.elsewhere.dark_oak_mosaic_stairs", "Dark Oak Mosaic Stairs");
        translationBuilder.add("block.elsewhere.dark_oak_mosaic_slab", "Dark Oak Mosaic Slab");
        translationBuilder.add("block.elsewhere.dark_oak_boards", "Dark Oak Boards");
        translationBuilder.add("block.elsewhere.dark_oak_trim", "Dark Oak Trim");

        translationBuilder.add("block.elsewhere.pale_oak_mosaic", "Pale Oak Mosaic");
        translationBuilder.add("block.elsewhere.pale_oak_mosaic_stairs", "Pale Oak Mosaic Stairs");
        translationBuilder.add("block.elsewhere.pale_oak_mosaic_slab", "Pale Oak Mosaic Slab");
        translationBuilder.add("block.elsewhere.pale_oak_boards", "Pale Oak Boards");
        translationBuilder.add("block.elsewhere.pale_oak_trim", "Pale Oak Trim");

        translationBuilder.add("block.elsewhere.birch_mosaic", "Birch Mosaic");
        translationBuilder.add("block.elsewhere.birch_mosaic_stairs", "Birch Mosaic Stairs");
        translationBuilder.add("block.elsewhere.birch_mosaic_slab", "Birch Mosaic Slab");
        translationBuilder.add("block.elsewhere.birch_boards", "Birch Boards");
        translationBuilder.add("block.elsewhere.birch_trim", "Birch Trim");

        translationBuilder.add("block.elsewhere.spruce_mosaic", "Spruce Mosaic");
        translationBuilder.add("block.elsewhere.spruce_mosaic_stairs", "Spruce Mosaic Stairs");
        translationBuilder.add("block.elsewhere.spruce_mosaic_slab", "Spruce Mosaic Slab");
        translationBuilder.add("block.elsewhere.spruce_boards", "Spruce Boards");
        translationBuilder.add("block.elsewhere.spruce_trim", "Spruce Trim");

        translationBuilder.add("block.elsewhere.jungle_mosaic", "Jungle Mosaic");
        translationBuilder.add("block.elsewhere.jungle_mosaic_stairs", "Jungle Mosaic Stairs");
        translationBuilder.add("block.elsewhere.jungle_mosaic_slab", "Jungle Mosaic Slab");
        translationBuilder.add("block.elsewhere.jungle_boards", "Jungle Boards");
        translationBuilder.add("block.elsewhere.jungle_trim", "Jungle Trim");

        translationBuilder.add("block.elsewhere.acacia_mosaic", "Acacia Mosaic");
        translationBuilder.add("block.elsewhere.acacia_mosaic_stairs", "Acacia Mosaic Stairs");
        translationBuilder.add("block.elsewhere.acacia_mosaic_slab", "Acacia Mosaic Slab");
        translationBuilder.add("block.elsewhere.acacia_boards", "Acacia Boards");
        translationBuilder.add("block.elsewhere.acacia_trim", "Acacia Trim");

        translationBuilder.add("block.elsewhere.cherry_mosaic", "Cherry Mosaic");
        translationBuilder.add("block.elsewhere.cherry_mosaic_stairs", "Cherry Mosaic Stairs");
        translationBuilder.add("block.elsewhere.cherry_mosaic_slab", "Cherry Mosaic Slab");
        translationBuilder.add("block.elsewhere.cherry_boards", "Cherry Boards");
        translationBuilder.add("block.elsewhere.cherry_trim", "Cherry Trim");

        translationBuilder.add("block.elsewhere.mangrove_mosaic", "Mangrove Mosaic");
        translationBuilder.add("block.elsewhere.mangrove_mosaic_stairs", "Mangrove Mosaic Stairs");
        translationBuilder.add("block.elsewhere.mangrove_mosaic_slab", "Mangrove Mosaic Slab");
        translationBuilder.add("block.elsewhere.mangrove_boards", "Mangrove Boards");
        translationBuilder.add("block.elsewhere.mangrove_trim", "Mangrove Trim");

        translationBuilder.add("block.elsewhere.crimson_mosaic", "Crimson Mosaic");
        translationBuilder.add("block.elsewhere.crimson_mosaic_stairs", "Crimson Mosaic Stairs");
        translationBuilder.add("block.elsewhere.crimson_mosaic_slab", "Crimson Mosaic Slab");
        translationBuilder.add("block.elsewhere.crimson_boards", "Crimson Boards");
        translationBuilder.add("block.elsewhere.crimson_trim", "Crimson Trim");

        translationBuilder.add("block.elsewhere.warped_mosaic", "Warped Mosaic");
        translationBuilder.add("block.elsewhere.warped_mosaic_stairs", "Warped Mosaic Stairs");
        translationBuilder.add("block.elsewhere.warped_mosaic_slab", "Warped Mosaic Slab");
        translationBuilder.add("block.elsewhere.warped_boards", "Warped Boards");
        translationBuilder.add("block.elsewhere.warped_trim", "Warped Trim");

        translationBuilder.add("block.elsewhere.bamboo_stem", "Bamboo Stem");
        translationBuilder.add("block.elsewhere.stripped_bamboo_stem", "Stripped Bamboo Stem");
        translationBuilder.add("block.elsewhere.bamboo_boards", "Bamboo Boards");
        translationBuilder.add("block.elsewhere.bamboo_trim", "Bamboo Trim");




        translationBuilder.add("block.elsewhere.bluebonnet", "Bluebonnet");
        translationBuilder.add("block.elsewhere.potted_bluebonnet", "Potted Bluebonnet");

        translationBuilder.add("block.elsewhere.red_hibiscus", "Red Hibiscus");
        translationBuilder.add("block.elsewhere.potted_red_hibiscus", "Potted Red Hibiscus");
        translationBuilder.add("block.elsewhere.orange_hibiscus", "Orange Hibiscus");
        translationBuilder.add("block.elsewhere.potted_orange_hibiscus", "Potted Orange Hibiscus");
        translationBuilder.add("block.elsewhere.yellow_hibiscus", "Yellow Hibiscus");
        translationBuilder.add("block.elsewhere.potted_yellow_hibiscus", "Potted Yellow Hibiscus");
        translationBuilder.add("block.elsewhere.blue_hibiscus", "Blue Hibiscus");
        translationBuilder.add("block.elsewhere.potted_blue_hibiscus", "Potted Blue Hibiscus");
        translationBuilder.add("block.elsewhere.purple_hibiscus", "Purple Hibiscus");
        translationBuilder.add("block.elsewhere.potted_purple_hibiscus", "Potted Purple Hibiscus");
        translationBuilder.add("block.elsewhere.pink_hibiscus", "Pink Hibiscus");
        translationBuilder.add("block.elsewhere.potted_pink_hibiscus", "Potted Pink Hibiscus");
        translationBuilder.add("block.elsewhere.white_hibiscus", "White Hibiscus");
        translationBuilder.add("block.elsewhere.potted_white_hibiscus", "Potted White Hibiscus");

        translationBuilder.add("block.elsewhere.magenta_coneflower", "Magenta Coneflower");
        translationBuilder.add("block.elsewhere.yellow_coneflower", "Magenta Coneflower");
        translationBuilder.add("block.elsewhere.ironweed", "Ironweed");
        translationBuilder.add("block.elsewhere.rose", "Rose");
        translationBuilder.add("block.elsewhere.cattails", "Cattails");

        translationBuilder.add("block.elsewhere.cave_weed", "Cave Weed");
        translationBuilder.add("block.elsewhere.gleamshroom", "Gleamshroom");

        translationBuilder.add("block.elsewhere.arid_dirt", "Arid Dirt");
        translationBuilder.add("block.elsewhere.arid_grass_block", "Arid Grass Block");

        translationBuilder.add("block.elsewhere.red_grass_block", "Red Grass Block");
        translationBuilder.add("block.elsewhere.short_red_grass", "Short Red Grass");
        translationBuilder.add("block.elsewhere.tall_red_grass", "Tall Red Grass");
        translationBuilder.add("block.elsewhere.red_shrub", "Red Shrub");

        translationBuilder.add("block.elsewhere.gilded_birch_leaves", "Gilded Birch Leaves");

        translationBuilder.add("block.elsewhere.poplar_log", "Poplar Log");
        translationBuilder.add("block.elsewhere.stripped_poplar_log", "Stripped Poplar Log");
        translationBuilder.add("block.elsewhere.poplar_wood", "Poplar Wood");
        translationBuilder.add("block.elsewhere.stripped_poplar_wood", "Stripped Poplar Wood");

        translationBuilder.add("block.elsewhere.poplar_planks", "Poplar Planks");
        translationBuilder.add("block.elsewhere.poplar_stairs", "Poplar Stairs");
        translationBuilder.add("block.elsewhere.poplar_slab", "Poplar Slab");
        translationBuilder.add("block.elsewhere.poplar_mosaic", "Poplar Mosaic");
        translationBuilder.add("block.elsewhere.poplar_mosaic_stairs", "Poplar Mosaic Stairs");
        translationBuilder.add("block.elsewhere.poplar_mosaic_slab", "Poplar Mosaic Slab");
        translationBuilder.add("block.elsewhere.poplar_boards", "Poplar Boards");
        translationBuilder.add("block.elsewhere.poplar_trim", "Poplar Trim");

        translationBuilder.add("block.elsewhere.poplar_fence", "Poplar Fence");
        translationBuilder.add("block.elsewhere.poplar_fence_gate", "Poplar Fence Gate");
        translationBuilder.add("block.elsewhere.poplar_door", "Poplar Door");
        translationBuilder.add("block.elsewhere.poplar_trapdoor", "Poplar Trapdoor");
        translationBuilder.add("block.elsewhere.poplar_pressure_plate", "Poplar Pressure Plate");
        translationBuilder.add("block.elsewhere.poplar_button", "Poplar Button");
        translationBuilder.add("block.elsewhere.poplar_shelf", "Poplar Shelf");

        translationBuilder.add("block.elsewhere.poplar_sign", "Poplar Sign");
        translationBuilder.add("block.elsewhere.poplar_wall_sign", "Poplar Wall Sign");
        translationBuilder.add("block.elsewhere.poplar_hanging_sign", "Poplar Hanging Sign");
        translationBuilder.add("block.elsewhere.poplar_wall_hanging_sign", "Poplar Wall Hanging Sign");

        translationBuilder.add("item.elsewhere.poplar_sign", "Poplar Sign");
        translationBuilder.add("item.elsewhere.poplar_hanging_sign", "Poplar Hanging Sign");
        translationBuilder.add("item.elsewhere.poplar_boat", "Poplar Boat");
        translationBuilder.add("item.elsewhere.poplar_chest_boat", "Poplar Boat with Chest");

        translationBuilder.add("entity.elsewhere.poplar_boat", "Poplar Boat");
        translationBuilder.add("entity.elsewhere.poplar_chest_boat", "Poplar Boat with Chest");

        translationBuilder.add("block.elsewhere.poplar_sapling", "Poplar Sapling");
        translationBuilder.add("block.elsewhere.potted_poplar_sapling", "Potted Poplar Sapling");
        translationBuilder.add("block.elsewhere.red_poplar_leaves", "Red Poplar Leaves");
        translationBuilder.add("block.elsewhere.orange_poplar_leaves", "Orange Poplar Leaves");
        translationBuilder.add("block.elsewhere.yellow_poplar_leaves", "Yellow Poplar Leaves");

        translationBuilder.add("block.elsewhere.azalea_log", "Azalea Log");
        translationBuilder.add("block.elsewhere.stripped_azalea_log", "Stripped Azalea Log");
        translationBuilder.add("block.elsewhere.azalea_wood", "Azalea Wood");
        translationBuilder.add("block.elsewhere.stripped_azalea_wood", "Stripped Azalea Wood");

        translationBuilder.add("block.elsewhere.azalea_planks", "Azalea Planks");
        translationBuilder.add("block.elsewhere.azalea_stairs", "Azalea Stairs");
        translationBuilder.add("block.elsewhere.azalea_slab", "Azalea Slab");
        translationBuilder.add("block.elsewhere.azalea_mosaic", "Azalea Mosaic");
        translationBuilder.add("block.elsewhere.azalea_mosaic_stairs", "Azalea Mosaic Stairs");
        translationBuilder.add("block.elsewhere.azalea_mosaic_slab", "Azalea Mosaic Slab");
        translationBuilder.add("block.elsewhere.azalea_boards", "Azalea Boards");
        translationBuilder.add("block.elsewhere.azalea_trim", "Azalea Trim");

        translationBuilder.add("block.elsewhere.azalea_fence", "Azalea Fence");
        translationBuilder.add("block.elsewhere.azalea_fence_gate", "Azalea Fence Gate");
        translationBuilder.add("block.elsewhere.azalea_door", "Azalea Door");
        translationBuilder.add("block.elsewhere.azalea_trapdoor", "Azalea Trapdoor");
        translationBuilder.add("block.elsewhere.azalea_pressure_plate", "Azalea Pressure Plate");
        translationBuilder.add("block.elsewhere.azalea_button", "Azalea Button");
        translationBuilder.add("block.elsewhere.azalea_shelf", "Azalea Shelf");

        translationBuilder.add("block.elsewhere.azalea_sign", "Azalea Sign");
        translationBuilder.add("block.elsewhere.azalea_wall_sign", "Azalea Wall Sign");
        translationBuilder.add("block.elsewhere.azalea_hanging_sign", "Azalea Hanging Sign");
        translationBuilder.add("block.elsewhere.azalea_wall_hanging_sign", "Azalea Wall Hanging Sign");

        translationBuilder.add("item.elsewhere.azalea_sign", "Azalea Sign");
        translationBuilder.add("item.elsewhere.azalea_hanging_sign", "Azalea Hanging Sign");
        translationBuilder.add("item.elsewhere.azalea_boat", "Azalea Boat");
        translationBuilder.add("item.elsewhere.azalea_chest_boat", "Azalea Boat with Chest");

        translationBuilder.add("entity.elsewhere.azalea_boat", "Azalea Boat");
        translationBuilder.add("entity.elsewhere.azalea_chest_boat", "Azalea Boat with Chest");

        translationBuilder.add("block.elsewhere.cedar_log", "Cedar Log");
        translationBuilder.add("block.elsewhere.stripped_cedar_log", "Stripped Cedar Log");
        translationBuilder.add("block.elsewhere.cedar_wood", "Cedar Wood");
        translationBuilder.add("block.elsewhere.stripped_cedar_wood", "Stripped Cedar Wood");

        translationBuilder.add("block.elsewhere.cedar_planks", "Cedar Planks");
        translationBuilder.add("block.elsewhere.cedar_stairs", "Cedar Stairs");
        translationBuilder.add("block.elsewhere.cedar_slab", "Cedar Slab");
        translationBuilder.add("block.elsewhere.cedar_mosaic", "Cedar Mosaic");
        translationBuilder.add("block.elsewhere.cedar_mosaic_stairs", "Cedar Mosaic Stairs");
        translationBuilder.add("block.elsewhere.cedar_mosaic_slab", "Cedar Mosaic Slab");
        translationBuilder.add("block.elsewhere.cedar_boards", "Cedar Boards");
        translationBuilder.add("block.elsewhere.cedar_trim", "Cedar Trim");

        translationBuilder.add("block.elsewhere.cedar_fence", "Cedar Fence");
        translationBuilder.add("block.elsewhere.cedar_fence_gate", "Cedar Fence Gate");
        translationBuilder.add("block.elsewhere.cedar_door", "Cedar Door");
        translationBuilder.add("block.elsewhere.cedar_trapdoor", "Cedar Trapdoor");
        translationBuilder.add("block.elsewhere.cedar_pressure_plate", "Cedar Pressure Plate");
        translationBuilder.add("block.elsewhere.cedar_button", "Cedar Button");
        translationBuilder.add("block.elsewhere.cedar_shelf", "Cedar Shelf");

        translationBuilder.add("block.elsewhere.cedar_sign", "Cedar Sign");
        translationBuilder.add("block.elsewhere.cedar_wall_sign", "Cedar Wall Sign");
        translationBuilder.add("block.elsewhere.cedar_hanging_sign", "Cedar Hanging Sign");
        translationBuilder.add("block.elsewhere.cedar_wall_hanging_sign", "Cedar Wall Hanging Sign");

        translationBuilder.add("item.elsewhere.cedar_sign", "Cedar Sign");
        translationBuilder.add("item.elsewhere.cedar_hanging_sign", "Cedar Hanging Sign");
        translationBuilder.add("item.elsewhere.cedar_boat", "Cedar Boat");
        translationBuilder.add("item.elsewhere.cedar_chest_boat", "Cedar Boat with Chest");

        translationBuilder.add("entity.elsewhere.cedar_boat", "Cedar Boat");
        translationBuilder.add("entity.elsewhere.cedar_chest_boat", "Cedar Boat with Chest");

        translationBuilder.add("block.elsewhere.cedar_sapling", "Cedar Sapling");
        translationBuilder.add("block.elsewhere.potted_cedar_sapling", "Potted Cedar Sapling");
        translationBuilder.add("block.elsewhere.cedar_leaves", "Cedar Leaves");

        translationBuilder.add("block.elsewhere.mahogany_log", "Mahogany Log");
        translationBuilder.add("block.elsewhere.stripped_mahogany_log", "Stripped Mahogany Log");
        translationBuilder.add("block.elsewhere.mahogany_wood", "Mahogany Wood");
        translationBuilder.add("block.elsewhere.stripped_mahogany_wood", "Stripped Mahogany Wood");

        translationBuilder.add("block.elsewhere.mahogany_planks", "Mahogany Planks");
        translationBuilder.add("block.elsewhere.mahogany_stairs", "Mahogany Stairs");
        translationBuilder.add("block.elsewhere.mahogany_slab", "Mahogany Slab");
        translationBuilder.add("block.elsewhere.mahogany_mosaic", "Mahogany Mosaic");
        translationBuilder.add("block.elsewhere.mahogany_mosaic_stairs", "Mahogany Mosaic Stairs");
        translationBuilder.add("block.elsewhere.mahogany_mosaic_slab", "Mahogany Mosaic Slab");
        translationBuilder.add("block.elsewhere.mahogany_boards", "Mahogany Boards");
        translationBuilder.add("block.elsewhere.mahogany_trim", "Mahogany Trim");

        translationBuilder.add("block.elsewhere.mahogany_fence", "Mahogany Fence");
        translationBuilder.add("block.elsewhere.mahogany_fence_gate", "Mahogany Fence Gate");
        translationBuilder.add("block.elsewhere.mahogany_door", "Mahogany Door");
        translationBuilder.add("block.elsewhere.mahogany_trapdoor", "Mahogany Trapdoor");
        translationBuilder.add("block.elsewhere.mahogany_pressure_plate", "Mahogany Pressure Plate");
        translationBuilder.add("block.elsewhere.mahogany_button", "Mahogany Button");
        translationBuilder.add("block.elsewhere.mahogany_shelf", "Mahogany Shelf");

        translationBuilder.add("block.elsewhere.mahogany_sign", "Mahogany Sign");
        translationBuilder.add("block.elsewhere.mahogany_wall_sign", "Mahogany Wall Sign");
        translationBuilder.add("block.elsewhere.mahogany_hanging_sign", "Mahogany Hanging Sign");
        translationBuilder.add("block.elsewhere.mahogany_wall_hanging_sign", "Mahogany Wall Hanging Sign");

        translationBuilder.add("item.elsewhere.mahogany_sign", "Mahogany Sign");
        translationBuilder.add("item.elsewhere.mahogany_hanging_sign", "Mahogany Hanging Sign");
        translationBuilder.add("item.elsewhere.mahogany_boat", "Mahogany Boat");
        translationBuilder.add("item.elsewhere.mahogany_chest_boat", "Mahogany Boat with Chest");

        translationBuilder.add("entity.elsewhere.mahogany_boat", "Mahogany Boat");
        translationBuilder.add("entity.elsewhere.mahogany_chest_boat", "Mahogany Boat with Chest");

        translationBuilder.add("block.elsewhere.mahogany_sapling", "Mahogany Sapling");
        translationBuilder.add("block.elsewhere.potted_mahogany_sapling", "Potted Mahogany Sapling");
        translationBuilder.add("block.elsewhere.mahogany_leaves", "Mahogany Leaves");

        translationBuilder.add("block.elsewhere.rusty_moss_block", "Rusty Moss Block");
        translationBuilder.add("block.elsewhere.rusty_moss_carpet", "Rusty Moss Carpet");

        translationBuilder.add("block.elsewhere.short_moss_sporophyte", "Short Moss Sporophyte");
        translationBuilder.add("block.elsewhere.tall_moss_sporophyte", "Tall Moss Sporophyte");

        translationBuilder.add("block.elsewhere.short_rusty_moss_sporophyte", "Short Rusty Moss Sporophyte");
        translationBuilder.add("block.elsewhere.tall_rusty_moss_sporophyte", "Tall Rusty Moss Sporophyte");

        translationBuilder.add("block.elsewhere.short_pale_moss_sporophyte", "Short Pale Moss Sporophyte");
        translationBuilder.add("block.elsewhere.tall_pale_moss_sporophyte", "Tall Pale Moss Sporophyte");

        translationBuilder.add("item.elsewhere.diamond_nugget", "Diamond Nugget");
        translationBuilder.add("item.elsewhere.emerald_nugget", "Emerald Nugget");
        translationBuilder.add("block.elsewhere.bejeweled_calcite", "Bejeweled Calcite");

        translationBuilder.add("block.elsewhere.laterite", "Laterite");

        translationBuilder.add("block.elsewhere.pebble", "Pebble");

        translationBuilder.add("block.elsewhere.white_wool_stairs", "White Wool Stairs");
        translationBuilder.add("block.elsewhere.white_wool_slab", "White Wool Slab");
        translationBuilder.add("block.elsewhere.light_gray_wool_stairs", "Light Gray Wool Stairs");
        translationBuilder.add("block.elsewhere.light_gray_wool_slab", "Light Gray Wool Slab");
        translationBuilder.add("block.elsewhere.gray_wool_stairs", "Gray Wool Stairs");
        translationBuilder.add("block.elsewhere.gray_wool_slab", "Gray Wool Slab");
        translationBuilder.add("block.elsewhere.black_wool_stairs", "Black Wool Stairs");
        translationBuilder.add("block.elsewhere.black_wool_slab", "Black Wool Slab");
        translationBuilder.add("block.elsewhere.brown_wool_stairs", "Brown Wool Stairs");
        translationBuilder.add("block.elsewhere.brown_wool_slab", "Brown Wool Slab");
        translationBuilder.add("block.elsewhere.red_wool_stairs", "Red Wool Stairs");
        translationBuilder.add("block.elsewhere.red_wool_slab", "Red Wool Slab");
        translationBuilder.add("block.elsewhere.orange_wool_stairs", "Orange Wool Stairs");
        translationBuilder.add("block.elsewhere.orange_wool_slab", "Orange Wool Slab");
        translationBuilder.add("block.elsewhere.yellow_wool_stairs", "Yellow Wool Stairs");
        translationBuilder.add("block.elsewhere.yellow_wool_slab", "Yellow Wool Slab");
        translationBuilder.add("block.elsewhere.lime_wool_stairs", "Lime Wool Stairs");
        translationBuilder.add("block.elsewhere.lime_wool_slab", "Lime Wool Slab");
        translationBuilder.add("block.elsewhere.green_wool_stairs", "Green Wool Stairs");
        translationBuilder.add("block.elsewhere.green_wool_slab", "Green Wool Slab");
        translationBuilder.add("block.elsewhere.cyan_wool_stairs", "Cyan Wool Stairs");
        translationBuilder.add("block.elsewhere.cyan_wool_slab", "Cyan Wool Slab");
        translationBuilder.add("block.elsewhere.light_blue_wool_stairs", "Light Blue Wool Stairs");
        translationBuilder.add("block.elsewhere.light_blue_wool_slab", "Light Blue Wool Slab");
        translationBuilder.add("block.elsewhere.blue_wool_stairs", "Blue Wool Stairs");
        translationBuilder.add("block.elsewhere.blue_wool_slab", "Blue Wool Slab");
        translationBuilder.add("block.elsewhere.purple_wool_stairs", "Purple Wool Stairs");
        translationBuilder.add("block.elsewhere.purple_wool_slab", "Purple Wool Slab");
        translationBuilder.add("block.elsewhere.magenta_wool_stairs", "Magenta Wool Stairs");
        translationBuilder.add("block.elsewhere.magenta_wool_slab", "Magenta Wool Slab");
        translationBuilder.add("block.elsewhere.pink_wool_stairs", "Pink Wool Stairs");
        translationBuilder.add("block.elsewhere.pink_wool_slab", "Pink Wool Slab");

        translationBuilder.add("block.elsewhere.terracotta_stairs", "Terracotta Stairs");
        translationBuilder.add("block.elsewhere.terracotta_slab", "Terracotta Slab");
        translationBuilder.add("block.elsewhere.white_terracotta_stairs", "White Terracotta Stairs");
        translationBuilder.add("block.elsewhere.white_terracotta_slab", "White Terracotta Slab");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_stairs", "Light Gray Terracotta Stairs");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_slab", "Light Gray Terracotta Slab");
        translationBuilder.add("block.elsewhere.gray_terracotta_stairs", "Gray Terracotta Stairs");
        translationBuilder.add("block.elsewhere.gray_terracotta_slab", "Gray Terracotta Slab");
        translationBuilder.add("block.elsewhere.black_terracotta_stairs", "Black Terracotta Stairs");
        translationBuilder.add("block.elsewhere.black_terracotta_slab", "Black Terracotta Slab");
        translationBuilder.add("block.elsewhere.brown_terracotta_stairs", "Brown Terracotta Stairs");
        translationBuilder.add("block.elsewhere.brown_terracotta_slab", "Brown Terracotta Slab");
        translationBuilder.add("block.elsewhere.red_terracotta_stairs", "Red Terracotta Stairs");
        translationBuilder.add("block.elsewhere.red_terracotta_slab", "Red Terracotta Slab");
        translationBuilder.add("block.elsewhere.orange_terracotta_stairs", "Orange Terracotta Stairs");
        translationBuilder.add("block.elsewhere.orange_terracotta_slab", "Orange Terracotta Slab");
        translationBuilder.add("block.elsewhere.yellow_terracotta_stairs", "Yellow Terracotta Stairs");
        translationBuilder.add("block.elsewhere.yellow_terracotta_slab", "Yellow Terracotta Slab");
        translationBuilder.add("block.elsewhere.lime_terracotta_stairs", "Lime Terracotta Stairs");
        translationBuilder.add("block.elsewhere.lime_terracotta_slab", "Lime Terracotta Slab");
        translationBuilder.add("block.elsewhere.green_terracotta_stairs", "Green Terracotta Stairs");
        translationBuilder.add("block.elsewhere.green_terracotta_slab", "Green Terracotta Slab");
        translationBuilder.add("block.elsewhere.cyan_terracotta_stairs", "Cyan Terracotta Stairs");
        translationBuilder.add("block.elsewhere.cyan_terracotta_slab", "Cyan Terracotta Slab");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_stairs", "Light Blue Terracotta Stairs");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_slab", "Light Blue Terracotta Slab");
        translationBuilder.add("block.elsewhere.blue_terracotta_stairs", "Blue Terracotta Stairs");
        translationBuilder.add("block.elsewhere.blue_terracotta_slab", "Blue Terracotta Slab");
        translationBuilder.add("block.elsewhere.purple_terracotta_stairs", "Purple Terracotta Stairs");
        translationBuilder.add("block.elsewhere.purple_terracotta_slab", "Purple Terracotta Slab");
        translationBuilder.add("block.elsewhere.magenta_terracotta_stairs", "Magenta Terracotta Stairs");
        translationBuilder.add("block.elsewhere.magenta_terracotta_slab", "Magenta Terracotta Slab");
        translationBuilder.add("block.elsewhere.pink_terracotta_stairs", "Pink Terracotta Stairs");
        translationBuilder.add("block.elsewhere.pink_terracotta_slab", "Pink Terracotta Slab");

        translationBuilder.add("block.elsewhere.terracotta_bricks", "Terracotta Bricks");
        translationBuilder.add("block.elsewhere.terracotta_brick_stairs", "Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.terracotta_brick_slab", "Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.white_terracotta_bricks", "White Terracotta Bricks");
        translationBuilder.add("block.elsewhere.white_terracotta_brick_stairs", "White Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.white_terracotta_brick_slab", "White Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_bricks", "Light Gray Terracotta Bricks");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_brick_stairs", "Light Gray Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_brick_slab", "Light Gray Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.gray_terracotta_bricks", "Gray Terracotta Bricks");
        translationBuilder.add("block.elsewhere.gray_terracotta_brick_stairs", "Gray Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.gray_terracotta_brick_slab", "Gray Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.black_terracotta_bricks", "Black Terracotta Bricks");
        translationBuilder.add("block.elsewhere.black_terracotta_brick_stairs", "Black Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.black_terracotta_brick_slab", "Black Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.brown_terracotta_bricks", "Brown Terracotta Bricks");
        translationBuilder.add("block.elsewhere.brown_terracotta_brick_stairs", "Brown Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.brown_terracotta_brick_slab", "Brown Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.red_terracotta_bricks", "Red Terracotta Bricks");
        translationBuilder.add("block.elsewhere.red_terracotta_brick_stairs", "Red Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.red_terracotta_brick_slab", "Red Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.orange_terracotta_bricks", "Orange Terracotta Bricks");
        translationBuilder.add("block.elsewhere.orange_terracotta_brick_stairs", "Orange Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.orange_terracotta_brick_slab", "Orange Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.yellow_terracotta_bricks", "Yellow Terracotta Bricks");
        translationBuilder.add("block.elsewhere.yellow_terracotta_brick_stairs", "Yellow Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.yellow_terracotta_brick_slab", "Yellow Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.lime_terracotta_bricks", "Lime Terracotta Bricks");
        translationBuilder.add("block.elsewhere.lime_terracotta_brick_stairs", "Lime Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.lime_terracotta_brick_slab", "Lime Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.green_terracotta_bricks", "Green Terracotta Bricks");
        translationBuilder.add("block.elsewhere.green_terracotta_brick_stairs", "Green Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.green_terracotta_brick_slab", "Green Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.cyan_terracotta_bricks", "Cyan Terracotta Bricks");
        translationBuilder.add("block.elsewhere.cyan_terracotta_brick_stairs", "Cyan Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.cyan_terracotta_brick_slab", "Cyan Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_bricks", "Light Blue Terracotta Bricks");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_brick_stairs", "Light Blue Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_brick_slab", "Light Blue Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.blue_terracotta_bricks", "Blue Terracotta Bricks");
        translationBuilder.add("block.elsewhere.blue_terracotta_brick_stairs", "Blue Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.blue_terracotta_brick_slab", "Blue Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.purple_terracotta_bricks", "Purple Terracotta Bricks");
        translationBuilder.add("block.elsewhere.purple_terracotta_brick_stairs", "Purple Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.purple_terracotta_brick_slab", "Purple Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.magenta_terracotta_bricks", "Magenta Terracotta Bricks");
        translationBuilder.add("block.elsewhere.magenta_terracotta_brick_stairs", "Magenta Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.magenta_terracotta_brick_slab", "Magenta Terracotta Brick Slab");
        translationBuilder.add("block.elsewhere.pink_terracotta_bricks", "Pink Terracotta Bricks");
        translationBuilder.add("block.elsewhere.pink_terracotta_brick_stairs", "Pink Terracotta Brick Stairs");
        translationBuilder.add("block.elsewhere.pink_terracotta_brick_slab", "Pink Terracotta Brick Slab");

        translationBuilder.add("block.elsewhere.terracotta_brick_tiles", "Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.terracotta_brick_tile_stairs", "Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.terracotta_brick_tile_slab", "Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.white_terracotta_brick_tiles", "White Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.white_terracotta_brick_tile_stairs", "White Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.white_terracotta_brick_tile_slab", "White Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_brick_tiles", "Light Gray Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_brick_tile_stairs", "Light Gray Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.light_gray_terracotta_brick_tile_slab", "Light Gray Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.gray_terracotta_brick_tiles", "Gray Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.gray_terracotta_brick_tile_stairs", "Gray Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.gray_terracotta_brick_tile_slab", "Gray Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.black_terracotta_brick_tiles", "Black Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.black_terracotta_brick_tile_stairs", "Black Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.black_terracotta_brick_tile_slab", "Black Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.brown_terracotta_brick_tiles", "Brown Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.brown_terracotta_brick_tile_stairs", "Brown Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.brown_terracotta_brick_tile_slab", "Brown Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.red_terracotta_brick_tiles", "Red Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.red_terracotta_brick_tile_stairs", "Red Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.red_terracotta_brick_tile_slab", "Red Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.orange_terracotta_brick_tiles", "Orange Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.orange_terracotta_brick_tile_stairs", "Orange Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.orange_terracotta_brick_tile_slab", "Orange Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.yellow_terracotta_brick_tiles", "Yellow Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.yellow_terracotta_brick_tile_stairs", "Yellow Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.yellow_terracotta_brick_tile_slab", "Yellow Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.lime_terracotta_brick_tiles", "Lime Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.lime_terracotta_brick_tile_stairs", "Lime Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.lime_terracotta_brick_tile_slab", "Lime Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.green_terracotta_brick_tiles", "Green Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.green_terracotta_brick_tile_stairs", "Green Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.green_terracotta_brick_tile_slab", "Green Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.cyan_terracotta_brick_tiles", "Cyan Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.cyan_terracotta_brick_tile_stairs", "Cyan Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.cyan_terracotta_brick_tile_slab", "Cyan Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_brick_tiles", "Light Blue Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_brick_tile_stairs", "Light Blue Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.light_blue_terracotta_brick_tile_slab", "Light Blue Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.blue_terracotta_brick_tiles", "Blue Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.blue_terracotta_brick_tile_stairs", "Blue Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.blue_terracotta_brick_tile_slab", "Blue Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.purple_terracotta_brick_tiles", "Purple Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.purple_terracotta_brick_tile_stairs", "Purple Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.purple_terracotta_brick_tile_slab", "Purple Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.magenta_terracotta_brick_tiles", "Magenta Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.magenta_terracotta_brick_tile_stairs", "Magenta Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.magenta_terracotta_brick_tile_slab", "Magenta Terracotta Brick Tile Slab");
        translationBuilder.add("block.elsewhere.pink_terracotta_brick_tiles", "Pink Terracotta Brick Tiles");
        translationBuilder.add("block.elsewhere.pink_terracotta_brick_tile_stairs", "Pink Terracotta Brick Tile Stairs");
        translationBuilder.add("block.elsewhere.pink_terracotta_brick_tile_slab", "Pink Terracotta Brick Tile Slab");

        translationBuilder.add("block.elsewhere.white_concrete_stairs", "White Concrete Stairs");
        translationBuilder.add("block.elsewhere.white_concrete_slab", "White Concrete Slab");
        translationBuilder.add("block.elsewhere.light_gray_concrete_stairs", "Light Gray Concrete Stairs");
        translationBuilder.add("block.elsewhere.light_gray_concrete_slab", "Light Gray Concrete Slab");
        translationBuilder.add("block.elsewhere.gray_concrete_stairs", "Gray Concrete Stairs");
        translationBuilder.add("block.elsewhere.gray_concrete_slab", "Gray Concrete Slab");
        translationBuilder.add("block.elsewhere.black_concrete_stairs", "Black Concrete Stairs");
        translationBuilder.add("block.elsewhere.black_concrete_slab", "Black Concrete Slab");
        translationBuilder.add("block.elsewhere.brown_concrete_stairs", "Brown Concrete Stairs");
        translationBuilder.add("block.elsewhere.brown_concrete_slab", "Brown Concrete Slab");
        translationBuilder.add("block.elsewhere.red_concrete_stairs", "Red Concrete Stairs");
        translationBuilder.add("block.elsewhere.red_concrete_slab", "Red Concrete Slab");
        translationBuilder.add("block.elsewhere.orange_concrete_stairs", "Orange Concrete Stairs");
        translationBuilder.add("block.elsewhere.orange_concrete_slab", "Orange Concrete Slab");
        translationBuilder.add("block.elsewhere.yellow_concrete_stairs", "Yellow Concrete Stairs");
        translationBuilder.add("block.elsewhere.yellow_concrete_slab", "Yellow Concrete Slab");
        translationBuilder.add("block.elsewhere.lime_concrete_stairs", "Lime Concrete Stairs");
        translationBuilder.add("block.elsewhere.lime_concrete_slab", "Lime Concrete Slab");
        translationBuilder.add("block.elsewhere.green_concrete_stairs", "Green Concrete Stairs");
        translationBuilder.add("block.elsewhere.green_concrete_slab", "Green Concrete Slab");
        translationBuilder.add("block.elsewhere.cyan_concrete_stairs", "Cyan Concrete Stairs");
        translationBuilder.add("block.elsewhere.cyan_concrete_slab", "Cyan Concrete Slab");
        translationBuilder.add("block.elsewhere.light_blue_concrete_stairs", "Light Blue Concrete Stairs");
        translationBuilder.add("block.elsewhere.light_blue_concrete_slab", "Light Blue Concrete Slab");
        translationBuilder.add("block.elsewhere.blue_concrete_stairs", "Blue Concrete Stairs");
        translationBuilder.add("block.elsewhere.blue_concrete_slab", "Blue Concrete Slab");
        translationBuilder.add("block.elsewhere.purple_concrete_stairs", "Purple Concrete Stairs");
        translationBuilder.add("block.elsewhere.purple_concrete_slab", "Purple Concrete Slab");
        translationBuilder.add("block.elsewhere.magenta_concrete_stairs", "Magenta Concrete Stairs");
        translationBuilder.add("block.elsewhere.magenta_concrete_slab", "Magenta Concrete Slab");
        translationBuilder.add("block.elsewhere.pink_concrete_stairs", "Pink Concrete Stairs");
        translationBuilder.add("block.elsewhere.pink_concrete_slab", "Pink Concrete Slab");

        translationBuilder.add("block.elsewhere.tin_ore", "Tin Ore");
        translationBuilder.add("block.elsewhere.deepslate_tin_ore", "Deepslate Tin Ore");
        translationBuilder.add("block.elsewhere.cinnabar_tin_ore", "Cinnabar Tin Ore");
        translationBuilder.add("item.elsewhere.raw_tin", "Raw Tin");
        translationBuilder.add("item.elsewhere.tin_ingot", "Tin Ingot");
        translationBuilder.add("item.elsewhere.tin_nugget", "Tin Nugget");
        translationBuilder.add("block.elsewhere.tin_block", "Block of Tin");
        translationBuilder.add("block.elsewhere.raw_tin_block", "Block of Raw Tin");
        translationBuilder.add("block.elsewhere.cut_tin", "Cut Tin");
        translationBuilder.add("block.elsewhere.cut_tin_stairs", "Cut Tin Stairs");
        translationBuilder.add("block.elsewhere.cut_tin_slab", "Cut Tin Slab");
        translationBuilder.add("block.elsewhere.chiseled_tin", "Chiseled Tin");
        translationBuilder.add("block.elsewhere.tin_bars", "Tin Bars");
        translationBuilder.add("block.elsewhere.tin_chain", "Tin Chain");

        translationBuilder.add("item.elsewhere.bronze_alloy", "Bronze Alloy");
        translationBuilder.add("block.elsewhere.bronze_block", "Block of Bronze");
        translationBuilder.add("item.elsewhere.bronze_helmet", "Bronze Helmet");
        translationBuilder.add("item.elsewhere.bronze_chestplate", "Bronze Chestplate");
        translationBuilder.add("item.elsewhere.bronze_leggings", "Bronze Leggings");
        translationBuilder.add("item.elsewhere.bronze_boots", "Bronze Boots");
        translationBuilder.add("item.elsewhere.bronze_sword", "Bronze Sword");
        translationBuilder.add("item.elsewhere.bronze_axe", "Bronze Axe");
        translationBuilder.add("item.elsewhere.bronze_spear", "Bronze Spear");
        translationBuilder.add("item.elsewhere.bronze_pickaxe", "Bronze Pickaxe");
        translationBuilder.add("item.elsewhere.bronze_shovel", "Bronze Shovel");
        translationBuilder.add("item.elsewhere.bronze_hoe", "Bronze Hoe");

        translationBuilder.add("item.elsewhere.bronze_upgrade_smithing_template", "Bronze Upgrade");
        translationBuilder.add("item.elsewhere.smithing_template.bronze_upgrade.applies_to", "Iron Equipment");
        translationBuilder.add("item.elsewhere.smithing_template.bronze_upgrade.ingredients", "Bronze Alloy");
        translationBuilder.add("item.elsewhere.smithing_template.bronze_upgrade.base_slot_description", "Add iron armor, weapon, or tool");
        translationBuilder.add("item.elsewhere.smithing_template.bronze_upgrade.additions_slot_description", "Add Bronze Alloy");

        translationBuilder.add("block.elsewhere.silver_ore", "Silver Ore");
        translationBuilder.add("block.elsewhere.deepslate_silver_ore", "Deepslate Silver Ore");
        translationBuilder.add("block.elsewhere.cinnabar_silver_ore", "Cinnabar Silver Ore");
        translationBuilder.add("item.elsewhere.raw_silver", "Raw Silver");
        translationBuilder.add("item.elsewhere.silver_ingot", "Silver Ingot");
        translationBuilder.add("item.elsewhere.silver_nugget", "Silver Nugget");
        translationBuilder.add("block.elsewhere.silver_block", "Block of Silver");
        translationBuilder.add("block.elsewhere.raw_silver_block", "Block of Raw Silver");
        translationBuilder.add("block.elsewhere.polished_silver", "Polished Silver");
        translationBuilder.add("block.elsewhere.polished_silver_stairs", "Polished Silver Stairs");
        translationBuilder.add("block.elsewhere.polished_silver_slab", "Polished Silver Slab");
        translationBuilder.add("block.elsewhere.cut_silver", "Cut Silver");
        translationBuilder.add("block.elsewhere.cut_silver_stairs", "Cut Silver Stairs");
        translationBuilder.add("block.elsewhere.cut_silver_slab", "Cut Silver Slab");
        translationBuilder.add("block.elsewhere.silver_pillar", "Silver Pillar");
        translationBuilder.add("block.elsewhere.silver_bars", "Silver Bars");
        translationBuilder.add("block.elsewhere.silver_chain", "Silver Chain");

        translationBuilder.add("block.elsewhere.gold_chain", "Gold Chain");
        translationBuilder.add("block.elsewhere.redstone_lantern", "Redstone Lantern");

        translationBuilder.add("block.elsewhere.wrought_iron_block", "Wrought Iron Block");
        translationBuilder.add("block.elsewhere.wrought_iron_stairs", "Wrought Iron Stairs");
        translationBuilder.add("block.elsewhere.wrought_iron_slab", "Wrought Iron Slab");
        translationBuilder.add("block.elsewhere.cut_wrought_iron", "Cut Wrought Iron");
        translationBuilder.add("block.elsewhere.cut_wrought_iron_stairs", "Cut Wrought Iron Stairs");
        translationBuilder.add("block.elsewhere.cut_wrought_iron_slab", "Cut Wrought Iron Slab");
        translationBuilder.add("block.elsewhere.wrought_iron_grate", "Wrought Iron Grate");
        translationBuilder.add("block.elsewhere.wrought_iron_bars", "Wrought Iron Bars");

        translationBuilder.add("item.elsewhere.soulsteel_alloy", "Soulsteel Alloy");
        translationBuilder.add("block.elsewhere.soulsteel_block", "Block of Soulsteel");
        translationBuilder.add("item.elsewhere.soulsteel_helmet", "Soulsteel Helmet");
        translationBuilder.add("item.elsewhere.soulsteel_chestplate", "Soulsteel Chestplate");
        translationBuilder.add("item.elsewhere.soulsteel_leggings", "Soulsteel Leggings");
        translationBuilder.add("item.elsewhere.soulsteel_boots", "Soulsteel Boots");
        translationBuilder.add("item.elsewhere.soulsteel_sword", "Soulsteel Sword");
        translationBuilder.add("item.elsewhere.soulsteel_axe", "Soulsteel Axe");
        translationBuilder.add("item.elsewhere.soulsteel_spear", "Soulsteel Spear");
        translationBuilder.add("item.elsewhere.soulsteel_pickaxe", "Soulsteel Pickaxe");
        translationBuilder.add("item.elsewhere.soulsteel_shovel", "Soulsteel Shovel");
        translationBuilder.add("item.elsewhere.soulsteel_hoe", "Soulsteel Hoe");

        translationBuilder.add("item.elsewhere.soulsteel_upgrade_smithing_template", "Soulsteel Upgrade");
        translationBuilder.add("item.elsewhere.smithing_template.soulsteel_upgrade.applies_to", "Iron Equipment");
        translationBuilder.add("item.elsewhere.smithing_template.soulsteel_upgrade.ingredients", "Soulsteel Alloy");
        translationBuilder.add("item.elsewhere.smithing_template.soulsteel_upgrade.base_slot_description", "Add iron armor, weapon, or tool");
        translationBuilder.add("item.elsewhere.smithing_template.soulsteel_upgrade.additions_slot_description", "Add Soulsteel Alloy");

        translationBuilder.add("item.elsewhere.electrum_alloy", "Electrum Alloy");
        translationBuilder.add("block.elsewhere.electrum_block", "Block of Electrum");
        translationBuilder.add("item.elsewhere.electrum_helmet", "Electrum Helmet");
        translationBuilder.add("item.elsewhere.electrum_chestplate", "Electrum Chestplate");
        translationBuilder.add("item.elsewhere.electrum_leggings", "Electrum Leggings");
        translationBuilder.add("item.elsewhere.electrum_boots", "Electrum Boots");
        translationBuilder.add("item.elsewhere.electrum_sword", "Electrum Sword");
        translationBuilder.add("item.elsewhere.electrum_axe", "Electrum Axe");
        translationBuilder.add("item.elsewhere.electrum_spear", "Electrum Spear");
        translationBuilder.add("item.elsewhere.electrum_pickaxe", "Electrum Pickaxe");
        translationBuilder.add("item.elsewhere.electrum_shovel", "Electrum Shovel");
        translationBuilder.add("item.elsewhere.electrum_hoe", "Electrum Hoe");

        translationBuilder.add("item.elsewhere.electrum_upgrade_smithing_template", "Electrum Upgrade");
        translationBuilder.add("item.elsewhere.smithing_template.electrum_upgrade.applies_to", "Gold Equipment");
        translationBuilder.add("item.elsewhere.smithing_template.electrum_upgrade.ingredients", "Electrum Alloy");
        translationBuilder.add("item.elsewhere.smithing_template.electrum_upgrade.base_slot_description", "Add gold armor, weapon, or tool");
        translationBuilder.add("item.elsewhere.smithing_template.electrum_upgrade.additions_slot_description", "Add Electrum Alloy");

        translationBuilder.add("item.elsewhere.uranium", "Uranium");
        translationBuilder.add("item.elsewhere.uranium_shard", "Uranium Shard");
        translationBuilder.add("block.elsewhere.uranium_block", "Block of Uranium");

        translationBuilder.add("item.elsewhere.reinforced_shield", "Reinforced Shield");
        translationBuilder.add("item.elsewhere.quick_shield", "Quick Shield");



        translationBuilder.add("item.elsewhere.music_disc_fox", "Music Disc");
        translationBuilder.add("jukebox_song.elsewhere.fox", "SunVee - fox");

        translationBuilder.add("elsewhere.music.game.spires", "AsterIskBlooms - Spires");


        translationBuilder.add("entity.elsewhere.frostbite", "Frostbite");
        translationBuilder.add("item.elsewhere.frostbite_spawn_egg", "Frostbite Spawn Egg");

        translationBuilder.add("entity.elsewhere.werewolf", "Werewolf");
        translationBuilder.add("item.elsewhere.werewolf_spawn_egg", "Werewolf Spawn Egg");

        translationBuilder.add("entity.elsewhere.vanguard", "Vanguard");
        translationBuilder.add("item.elsewhere.vanguard_spawn_egg", "Vanguard Spawn Egg");
        translationBuilder.add("item.elsewhere.vanguard_mask", "Vanguard Mask");
        translationBuilder.add("item.elsewhere.vanguard_mask.night_vision", "Night Vision (∞)");

        translationBuilder.add("entity.elsewhere.sulfur_cube", "Sulfur Cube");
        translationBuilder.add("item.elsewhere.sulfur_cube_spawn_egg", "Sulfur Cube Spawn Egg");

        translationBuilder.add("entity.elsewhere.subslime", "Subslime");
        translationBuilder.add("item.elsewhere.subslime_spawn_egg", "Subslime Spawn Egg");

        translationBuilder.add("entity.elsewhere.sweeper", "Sweeper");
        translationBuilder.add("item.elsewhere.sweeper_spawn_egg", "Sweeper Spawn Egg");

        translationBuilder.add("entity.elsewhere.mud_golem", "Mud Golem");
        translationBuilder.add("item.elsewhere.mud_golem_spawn_egg", "Mud Golem Spawn Egg");

        translationBuilder.add("entity.elsewhere.lost_soul", "Lost Soul");
        translationBuilder.add("item.elsewhere.lost_soul_spawn_egg", "Lost Soul Spawn Egg");

        translationBuilder.add("entity.elsewhere.troll", "Troll");
        translationBuilder.add("item.elsewhere.troll_spawn_egg", "Troll Spawn Egg");

        translationBuilder.add("entity.elsewhere.gravekeeper", "Gravekeeper");
        translationBuilder.add("item.elsewhere.gravekeeper_spawn_egg", "Gravekeeper Spawn Egg");

        translationBuilder.add("entity.elsewhere.whistler", "Whistler");
        translationBuilder.add("item.elsewhere.whistler_spawn_egg", "Whistler Spawn Egg");

        translationBuilder.add("entity.elsewhere.perch", "Perch");
        translationBuilder.add("item.elsewhere.perch_spawn_egg", "Perch Spawn Egg");
        translationBuilder.add("item.elsewhere.raw_perch", "Raw Perch");
        translationBuilder.add("item.elsewhere.cooked_perch", "Cooked Perch");
        translationBuilder.add("item.elsewhere.shining_perch", "Shining Perch");
        translationBuilder.add("item.elsewhere.perch_bucket", "Bucket of Perch");



        // Tags
        translationBuilder.add("tag.elsewhere.is_cave", "Is Cave");

        translationBuilder.add("tag.elsewhere.mosaics", "Mosaics");
        translationBuilder.add("tag.elsewhere.mosaic_stairs", "Mosaic Stairs");
        translationBuilder.add("tag.elsewhere.mosaic_slabs", "Mosaic Slabs");
        translationBuilder.add("tag.elsewhere.boards", "Boards");
        translationBuilder.add("tag.elsewhere.trims", "Trims");

        translationBuilder.add("tag.elsewhere.poplar_logs", "Poplar Logs");
        translationBuilder.add("tag.elsewhere.azalea_logs", "Azalea Logs");
        translationBuilder.add("tag.elsewhere.cedar_logs", "Cedar Logs");
        translationBuilder.add("tag.elsewhere.mahogany_logs", "Mahogany Logs");
        translationBuilder.add("tag.elsewhere.bamboo_stems", "Bamboo Stems");

        translationBuilder.add("tag.elsewhere.natural_stone_blocks", "Natural Stone Blocks");
        translationBuilder.add("tag.elsewhere.soil", "Soil");

        translationBuilder.add("tag.elsewhere.cinnabar_ore_replaceables", "Cinnabar Ore Replaceables");
        translationBuilder.add("tag.elsewhere.calcite_ore_replaceables", "Calcite Ore Replaceables");
        translationBuilder.add("tag.elsewhere.sulfur_spike_replaceable", "Sulfur Spike Replaceable");
        translationBuilder.add("tag.elsewhere.icicle_replaceable", "Icicle Replaceable");

        translationBuilder.add("tag.elsewhere.tundra_red_grass", "Tundra Red Grass");
        translationBuilder.add("tag.elsewhere.tundra_green_grass", "Tundra Green Grass");

        translationBuilder.add("tag.elsewhere.wool_blocks", "Wool Blocks");
        translationBuilder.add("tag.elsewhere.wool_stairs", "Wool Stairs");
        translationBuilder.add("tag.elsewhere.wool_slabs", "Wool Slabs");

        translationBuilder.add("tag.elsewhere.terracotta_blocks", "Terracotta Blocks");
        translationBuilder.add("tag.elsewhere.terracotta_stairs", "Terracotta Stairs");
        translationBuilder.add("tag.elsewhere.terracotta_slabs", "Terracotta Slabs");

        translationBuilder.add("tag.elsewhere.terracotta_bricks", "Terracotta Bricks");
        translationBuilder.add("tag.elsewhere.terracotta_brick_stairs", "Terracotta Brick Stairs");
        translationBuilder.add("tag.elsewhere.terracotta_brick_slabs", "Terracotta Brick Slabs");

        translationBuilder.add("tag.elsewhere.terracotta_brick_tiles", "Terracotta Brick Tiles");
        translationBuilder.add("tag.elsewhere.terracotta_brick_tile_stairs", "Terracotta Brick Tile Stairs");
        translationBuilder.add("tag.elsewhere.terracotta_brick_tile_slabs", "Terracotta Brick Tile Slabs");

        translationBuilder.add("tag.elsewhere.concrete", "Concrete");
        translationBuilder.add("tag.elsewhere.concrete_blocks", "Concrete Blocks");
        translationBuilder.add("tag.elsewhere.concrete_stairs", "Concrete Stairs");
        translationBuilder.add("tag.elsewhere.concrete_slabs", "Concrete Slabs");

        translationBuilder.add("tag.elsewhere.needs_copper_tool", "Needs Copper Tool");
        translationBuilder.add("tag.elsewhere.needs_bronze_tool", "Needs Bronze Tool");
        translationBuilder.add("tag.elsewhere.incorrect_for_bronze_tool", "Incorrect For Bronze Tool");

        translationBuilder.add("tag.elsewhere.repairs_bronze_armor", "Repairs Bronze Armor");
        translationBuilder.add("tag.elsewhere.bronze_tools", "Bronze Tools");
        translationBuilder.add("tag.elsewhere.bronze_equipment", "Bronze Equipment");

        translationBuilder.add("tag.elsewhere.repairs_soulsteel_armor", "Repairs Soulsteel Armor");
        translationBuilder.add("tag.elsewhere.soulsteel_tools", "Soulsteel Tools");
        translationBuilder.add("tag.elsewhere.soulsteel_equipment", "Soulsteel Equipment");

        translationBuilder.add("tag.elsewhere.repairs_electrum_armor", "Repairs Electrum Armor");
        translationBuilder.add("tag.elsewhere.electrum_tools", "Electrum Tools");
        translationBuilder.add("tag.elsewhere.electrum_equipment", "Electrum Equipment");

        translationBuilder.add("tag.elsewhere.repairs_vanguard_mask", "Repairs Vanguard Mask");

        translationBuilder.add("tag.elsewhere.possessable", "Possessable");



        // Biomes
        translationBuilder.add("biome.elsewhere.outback", "Outback");
        translationBuilder.add("biome.elsewhere.tundra", "Tundra");
        translationBuilder.add("biome.elsewhere.dappled_forest", "Dappled Forest");
        translationBuilder.add("biome.elsewhere.lush_desert", "Lush Desert");
        translationBuilder.add("biome.elsewhere.glass_desert", "Glass Desert");
        translationBuilder.add("biome.elsewhere.cedar_forest", "Cedar Forest");
        translationBuilder.add("biome.elsewhere.shrubland", "Shrubland");
        translationBuilder.add("biome.elsewhere.steppe", "Steppe");

        translationBuilder.add("biome.elsewhere.tidepools", "Tidepools");
        translationBuilder.add("biome.elsewhere.cold_beach", "Cold Beach");
        translationBuilder.add("biome.elsewhere.frozen_beach", "Frozen Beach");

        translationBuilder.add("biome.elsewhere.cold_river", "Cold River");
        translationBuilder.add("biome.elsewhere.lukewarm_river", "Lukewarm River");
        translationBuilder.add("biome.elsewhere.warm_river", "Warm River");

        translationBuilder.add("biome.elsewhere.sulfur_caves", "Sulfur Caves");
        translationBuilder.add("biome.elsewhere.crystalline_caves", "Crystalline Caves");
        translationBuilder.add("biome.elsewhere.arid_caves", "Arid Caves");
        translationBuilder.add("biome.elsewhere.frigid_caves", "Frigid Caves");



        // Trims
        translationBuilder.add("trim_material.elsewhere.tin", "Tin Material");
        translationBuilder.add("trim_material.elsewhere.bronze", "Bronze Material");
        translationBuilder.add("trim_material.elsewhere.silver", "Silver Material");
        translationBuilder.add("trim_material.elsewhere.soulsteel", "Soulsteel Material");
        translationBuilder.add("trim_material.elsewhere.electrum", "Electrum Material");



        // Sulfur Pool Sounds
        translationBuilder.add("subtitles.elsewhere.block.sulfur.noxious_gas", "Noxious gas bubbles");
        translationBuilder.add("subtitles.elsewhere.block.geyser.eruption_start", "Geyser erupts");
        translationBuilder.add("subtitles.elsewhere.block.geyser.eruption_active", "Geyser spraying");

        // Mob Sounds
        translationBuilder.add("subtitles.elsewhere.entity.frostbite.idle", "Frostbite groans");
        translationBuilder.add("subtitles.elsewhere.entity.frostbite.hurt", "Frostbite hurts");
        translationBuilder.add("subtitles.elsewhere.entity.frostbite.death", "Frostbite dies");

        translationBuilder.add("subtitles.elsewhere.entity.sulfur_cube.jump", "Sulfur Cube jumps");
        translationBuilder.add("subtitles.elsewhere.entity.sulfur_cube.squish", "Sulfur Cube squishes");
        translationBuilder.add("subtitles.elsewhere.entity.sulfur_cube.attack", "Sulfur Cube attacks");
        translationBuilder.add("subtitles.elsewhere.entity.sulfur_cube.hurt", "Sulfur Cube hurts");
        translationBuilder.add("subtitles.elsewhere.entity.sulfur_cube.absorb", "Sulfur Cubes reform");
        translationBuilder.add("subtitles.elsewhere.entity.sulfur_cube.death", "Sulfur Cube dies");

        translationBuilder.add("subtitles.elsewhere.entity.subslime.jump", "Subslime jumps");
        translationBuilder.add("subtitles.elsewhere.entity.subslime.squish", "Subslime squishes");
        translationBuilder.add("subtitles.elsewhere.entity.subslime.attack", "Subslime spins");
        translationBuilder.add("subtitles.elsewhere.entity.subslime.hurt", "Subslime hurts");
        translationBuilder.add("subtitles.elsewhere.entity.subslime.death", "Subslime dies");

        translationBuilder.add("subtitles.elsewhere.entity.vanguard.blabber", "Vanguard mumbles");
        translationBuilder.add("subtitles.elsewhere.entity.vanguard.swing", "Vanguard swings");
        translationBuilder.add("subtitles.elsewhere.entity.vanguard.lunge", "Vanguard lunges");
        translationBuilder.add("subtitles.elsewhere.entity.vanguard.charge", "Vanguard charges");
        translationBuilder.add("subtitles.elsewhere.entity.vanguard.stun", "Vanguard gets stunned");
        translationBuilder.add("subtitles.elsewhere.entity.vanguard.hurt", "Vanguard hurts");
        translationBuilder.add("subtitles.elsewhere.entity.vanguard.death", "Vanguard dies");

        translationBuilder.add("subtitles.elsewhere.entity.whistler.ambient", "Whistler calls");
        translationBuilder.add("subtitles.elsewhere.entity.whistler.detonate", "Whistler primes");

        translationBuilder.add("subtitles.elsewhere.entity.perch.flop", "Perch flops");
        translationBuilder.add("subtitles.elsewhere.entity.perch.hurt", "Perch hurts");
        translationBuilder.add("subtitles.elsewhere.entity.perch.death", "Perch dies");


        // Advancements
        translationBuilder.add("advancements.elsewhere.stone_pickaxe.title", "Tumbled Beginnings");
        translationBuilder.add("advancements.elsewhere.stone_pickaxe.description", "Craft a stone pickaxe using pebbles.");
        translationBuilder.add("advancements.elsewhere.copper_pickaxe.title", "Getting an Upgrade");
        translationBuilder.add("advancements.elsewhere.copper_pickaxe.description", "Craft a copper pickaxe using your newfound riches.");
        translationBuilder.add("advancements.elsewhere.copper_armor.title", "Suit Up");
        translationBuilder.add("advancements.elsewhere.copper_armor.description", "Craft a piece of copper armor.");
        translationBuilder.add("advancements.elsewhere.blast_furnace.title", "In for a Blast!");
        translationBuilder.add("advancements.elsewhere.blast_furnace.description", "Craft a blast furnace to smelt iron with.");
        translationBuilder.add("advancements.elsewhere.smelt_iron.title", "Pure Irony");
        translationBuilder.add("advancements.elsewhere.smelt_iron.description", "Get an iron ingot");
        translationBuilder.add("advancements.elsewhere.iron_pickaxe.title", "How Do You Pronounce It!?");
        translationBuilder.add("advancements.elsewhere.iron_pickaxe.description", "Craft an iron pickaxe.");
        translationBuilder.add("advancements.story.obtain_armor.title", "Jarvis, Clip That");
        translationBuilder.add("advancements.story.obtain_armor.description", "Craft a piece of iron armor.");
        translationBuilder.add("advancements.elsewhere.upgrade_bronze.title", "Rome Wasn't Built- WHAT.");
        translationBuilder.add("advancements.elsewhere.upgrade_bronze.description", "Upgrade your rusty iron into some shiny bronze.");



        // Attributes
        translationBuilder.add("attribute.elsewhere.lifesteal", "Lifesteal");







    }
}
