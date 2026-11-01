package team.lookingglass.elsewhere.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.tags.ItemTags;
import team.lookingglass.elsewhere.registry.EBlockItemIds;
import team.lookingglass.elsewhere.registry.EItemIds;
import team.lookingglass.elsewhere.registry.tags.EBlockItemTags;
import team.lookingglass.elsewhere.registry.tags.EItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // BlockItem Tags
        new BlockItemTagProvider(id -> BlockItemTagsProvider.wrapForItems(tag(id.item()))).run();

        // Food
        builder(ItemTags.CAT_FOOD).add(EItemIds.RAW_PERCH);
        builder(ItemTags.OCELOT_FOOD).add(EItemIds.RAW_PERCH);
        builder(ItemTags.WOLF_FOOD).add(EItemIds.RAW_PERCH, EItemIds.COOKED_PERCH);
        builder(ItemTags.MEAT).add(EItemIds.RAW_PERCH, EItemIds.COOKED_PERCH);

        // Bronze
        builder(EItemTags.BRONZE_TOOLS).add(
                EItemIds.BRONZE_SWORD, EItemIds.BRONZE_AXE, EItemIds.BRONZE_SPEAR,
                EItemIds.BRONZE_PICKAXE, EItemIds.BRONZE_SHOVEL, EItemIds.BRONZE_HOE
        );
        builder(EItemTags.BRONZE_EQUIPMENT).add(
                EItemIds.BRONZE_HELMET, EItemIds.BRONZE_CHESTPLATE, EItemIds.BRONZE_LEGGINGS, EItemIds.BRONZE_BOOTS
        );

        // Soulsteel
        builder(EItemTags.SOULSTEEL_TOOLS).add(
                EItemIds.SOULSTEEL_SWORD, EItemIds.SOULSTEEL_AXE, EItemIds.SOULSTEEL_SPEAR,
                EItemIds.SOULSTEEL_PICKAXE, EItemIds.SOULSTEEL_SHOVEL, EItemIds.SOULSTEEL_HOE
        );
        builder(EItemTags.SOULSTEEL_EQUIPMENT).add(
                EItemIds.SOULSTEEL_HELMET, EItemIds.SOULSTEEL_CHESTPLATE, EItemIds.SOULSTEEL_LEGGINGS, EItemIds.SOULSTEEL_BOOTS
        );

        // Electrum
        builder(EItemTags.ELECTRUM_TOOLS).add(
                EItemIds.ELECTRUM_SWORD, EItemIds.ELECTRUM_AXE, EItemIds.ELECTRUM_SPEAR,
                EItemIds.ELECTRUM_PICKAXE, EItemIds.ELECTRUM_SHOVEL, EItemIds.ELECTRUM_HOE
        );
        builder(EItemTags.ELECTRUM_EQUIPMENT).add(
                EItemIds.ELECTRUM_HELMET, EItemIds.ELECTRUM_CHESTPLATE, EItemIds.ELECTRUM_LEGGINGS, EItemIds.ELECTRUM_BOOTS
        );

        builder(EItemTags.REPAIRS_VANGUARD_MASK).add(EItemIds.VANGUARD_MASK, EItemIds.SILVER_INGOT);

        // Repair tags
        builder(EItemTags.REPAIRS_BRONZE_ARMOR).add(EItemIds.BRONZE_ALLOY);
        builder(EItemTags.REPAIRS_SOULSTEEL_ARMOR).add(EItemIds.SOULSTEEL_ALLOY);
        builder(EItemTags.REPAIRS_ELECTRUM_ARMOR).add(EItemIds.ELECTRUM_ALLOY);

        // Tool type tags
        builder(ItemTags.SWORDS).add(EItemIds.BRONZE_SWORD, EItemIds.SOULSTEEL_SWORD, EItemIds.ELECTRUM_SWORD);
        builder(ItemTags.AXES).add(EItemIds.BRONZE_AXE, EItemIds.SOULSTEEL_AXE, EItemIds.ELECTRUM_AXE);
        builder(ItemTags.SPEARS).add(EItemIds.BRONZE_SPEAR, EItemIds.SOULSTEEL_SPEAR, EItemIds.ELECTRUM_SPEAR);
        builder(ItemTags.PICKAXES).add(EItemIds.BRONZE_PICKAXE, EItemIds.SOULSTEEL_PICKAXE, EItemIds.ELECTRUM_PICKAXE);
        builder(ItemTags.SHOVELS).add(EItemIds.BRONZE_SHOVEL, EItemIds.SOULSTEEL_SHOVEL, EItemIds.ELECTRUM_SHOVEL);
        builder(ItemTags.HOES).add(EItemIds.BRONZE_HOE, EItemIds.SOULSTEEL_HOE, EItemIds.ELECTRUM_HOE);

        // Armor type tags
        builder(ItemTags.HEAD_ARMOR).add(EItemIds.BRONZE_HELMET, EItemIds.SOULSTEEL_HELMET, EItemIds.ELECTRUM_HELMET);
        builder(ItemTags.CHEST_ARMOR).add(EItemIds.BRONZE_CHESTPLATE, EItemIds.SOULSTEEL_CHESTPLATE, EItemIds.ELECTRUM_CHESTPLATE);
        builder(ItemTags.LEG_ARMOR).add(EItemIds.BRONZE_LEGGINGS, EItemIds.SOULSTEEL_LEGGINGS, EItemIds.ELECTRUM_LEGGINGS);
        builder(ItemTags.FOOT_ARMOR).add(EItemIds.BRONZE_BOOTS, EItemIds.SOULSTEEL_BOOTS, EItemIds.ELECTRUM_BOOTS);

        // Trim tags
        builder(ItemTags.TRIM_MATERIALS).add(
                EItemIds.TIN_INGOT, EItemIds.BRONZE_ALLOY, EItemIds.SILVER_INGOT, EItemIds.SOULSTEEL_ALLOY, EItemIds.ELECTRUM_ALLOY
        );
        builder(ItemTags.TRIMMABLE_ARMOR)
                .addTag(EItemTags.BRONZE_EQUIPMENT)
                .addTag(EItemTags.SOULSTEEL_EQUIPMENT)
                .addTag(EItemTags.ELECTRUM_EQUIPMENT)
        ;

        // Sulfur Cube Archetypes
        builder(ItemTags.SULFUR_CUBE_ARCHETYPE_BOUNCY)
                .addTag(EBlockItemTags.MOSAICS.item()).addTag(EBlockItemTags.BOARDS.item()).addTag(EBlockItemTags.TRIMS.item())
        ;

        builder(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY).add(
                EBlockItemIds.SODALITE, EBlockItemIds.POLISHED_SODALITE, EBlockItemIds.SODALITE_BRICKS,
                EBlockItemIds.SODALITE_PILLAR, EBlockItemIds.CHISELED_SODALITE, EBlockItemIds.CHISELED_SODALITE_BRICKS,
                EBlockItemIds.SHALE, EBlockItemIds.POLISHED_SHALE, EBlockItemIds.SHALE_BRICKS,
                EBlockItemIds.SHALE_PILLAR, EBlockItemIds.CHISELED_SHALE, EBlockItemIds.CHISELED_SHALE_BRICKS,
                EBlockItemIds.ORPIMENT, EBlockItemIds.POLISHED_ORPIMENT, EBlockItemIds.ORPIMENT_BRICKS, EBlockItemIds.ORPIMENT_PILLAR, EBlockItemIds.CHISELED_ORPIMENT,
                EBlockItemIds.SOUL_STONE, EBlockItemIds.SMOOTH_SOUL_STONE, EBlockItemIds.CUT_SOUL_STONE, EBlockItemIds.POLISHED_SOUL_STONE, EBlockItemIds.CHISELED_SOUL_STONE,
                EBlockItemIds.BEACHSTONE, EBlockItemIds.POLISHED_BEACHSTONE, EBlockItemIds.BEACHSTONE_BRICKS, EBlockItemIds.CHISELED_BEACHSTONE,
                EBlockItemIds.LIMESTONE, EBlockItemIds.CUT_LIMESTONE,
                EBlockItemIds.POLISHED_STONE, EBlockItemIds.STONE_TILES, EBlockItemIds.MOSSY_STONE_TILES, EBlockItemIds.STONE_PILLAR, EBlockItemIds.CHISELED_STONE,
                EBlockItemIds.DEEPSLATE_PILLAR, EBlockItemIds.CHISELED_DEEPSLATE_BRICKS,
                EBlockItemIds.TUFF_PILLAR, EBlockItemIds.BLACKSTONE_PILLAR,
                EBlockItemIds.ANDESITE_BRICKS, EBlockItemIds.ANDESITE_PILLAR, EBlockItemIds.CHISELED_ANDESITE,
                EBlockItemIds.GRANITE_BRICKS, EBlockItemIds.DIORITE_BRICKS,
                EBlockItemIds.BEJEWELED_CALCITE, EBlockItemIds.POLISHED_CALCITE, EBlockItemIds.CALCITE_BRICKS,
                EBlockItemIds.CALCITE_PILLAR, EBlockItemIds.CHISELED_CALCITE, EBlockItemIds.CHISELED_CALCITE_BRICKS,
                EBlockItemIds.POLISHED_DRIPSTONE, EBlockItemIds.DRIPSTONE_TILES,
                EBlockItemIds.DRIPSTONE_PILLAR, EBlockItemIds.CHISELED_DRIPSTONE_TILES,
                EBlockItemIds.POLISHED_SANDSTONE, EBlockItemIds.POLISHED_RED_SANDSTONE,
                EBlockItemIds.PINK_SANDSTONE, EBlockItemIds.SMOOTH_PINK_SANDSTONE, EBlockItemIds.CUT_PINK_SANDSTONE,
                EBlockItemIds.POLISHED_PINK_SANDSTONE, EBlockItemIds.CHISELED_PINK_SANDSTONE,
                EBlockItemIds.POLISHED_PRISMARINE, EBlockItemIds.PRISMARINE_PILLAR, EBlockItemIds.CHISELED_PRISMARINE,
                EBlockItemIds.POLISHED_DARK_PRISMARINE, EBlockItemIds.DARK_PRISMARINE_SCALES, EBlockItemIds.CHISELED_DARK_PRISMARINE,
                EBlockItemIds.BASALT_VENT, EBlockItemIds.ROCKWEED_BASALT,
                EBlockItemIds.POLISHED_SMOOTH_BASALT, EBlockItemIds.SMOOTH_BASALT_BRICKS, EBlockItemIds.SMOOTH_BASALT_TILES,
                EBlockItemIds.POLISHED_NETHERRACK, EBlockItemIds.NETHERRACK_BRICKS, EBlockItemIds.NETHERRACK_PILLAR,
                EBlockItemIds.CHISELED_NETHERRACK, EBlockItemIds.CHISELED_NETHERRACK_BRICKS,
                EBlockItemIds.POLISHED_END_STONE, EBlockItemIds.END_STONE_PILLAR, EBlockItemIds.CHISELED_END_STONE,
                EBlockItemIds.OBSIDIAN_BRICKS, EBlockItemIds.OBSIDIAN_PILLAR, EBlockItemIds.CHISELED_OBSIDIAN,
                EBlockItemIds.AMETHYST_BRICKS, EBlockItemIds.AMETHYST_PILLAR, EBlockItemIds.CHISELED_AMETHYST,
                EBlockItemIds.LAPIS_BRICKS, EBlockItemIds.CHISELED_PURPUR,
                EBlockItemIds.CHISELED_MUD_BRICKS, EBlockItemIds.MUD_BRICK_PILLAR,
                EBlockItemIds.SULFUR_PILLAR, EBlockItemIds.CINNABAR_PILLAR,
                EBlockItemIds.CINNABAR_COAL_ORE, EBlockItemIds.CINNABAR_REDSTONE_ORE, EBlockItemIds.CINNABAR_DIAMOND_ORE,
                EBlockItemIds.CINNABAR_LAPIS_ORE, EBlockItemIds.CINNABAR_EMERALD_ORE, EBlockItemIds.SOUL_QUARTZ_ORE
        ).addTag(EBlockItemTags.TERRACOTTA_BRICKS.item()).addTag(EBlockItemTags.TERRACOTTA_BRICK_TILES.item());

        builder(ItemTags.SULFUR_CUBE_ARCHETYPE_REGULAR).add(
                EBlockItemIds.ARID_DIRT, EBlockItemIds.ARID_GRASS_BLOCK, EBlockItemIds.RED_GRASS_BLOCK, EBlockItemIds.LATERITE
        );

        builder(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_FLAT).add(
                EBlockItemIds.TIN_ORE, EBlockItemIds.DEEPSLATE_TIN_ORE, EBlockItemIds.CINNABAR_TIN_ORE,
                EBlockItemIds.SILVER_ORE, EBlockItemIds.DEEPSLATE_SILVER_ORE, EBlockItemIds.CINNABAR_SILVER_ORE,
                EBlockItemIds.SOUL_SILVER_ORE,
                EBlockItemIds.CINNABAR_COPPER_ORE, EBlockItemIds.CINNABAR_IRON_ORE, EBlockItemIds.CINNABAR_GOLD_ORE,
                EBlockItemIds.TIN_BLOCK, EBlockItemIds.RAW_TIN_BLOCK, EBlockItemIds.CUT_TIN, EBlockItemIds.CHISELED_TIN,
                EBlockItemIds.BRONZE_BLOCK,
                EBlockItemIds.SILVER_BLOCK, EBlockItemIds.RAW_SILVER_BLOCK, EBlockItemIds.POLISHED_SILVER,
                EBlockItemIds.CUT_SILVER, EBlockItemIds.SILVER_PILLAR,
                EBlockItemIds.WROUGHT_IRON_BLOCK, EBlockItemIds.CUT_WROUGHT_IRON, EBlockItemIds.WROUGHT_IRON_GRATE,
                EBlockItemIds.SOULSTEEL_BLOCK, EBlockItemIds.ELECTRUM_BLOCK, EBlockItemIds.URANIUM_BLOCK
        );

        builder(ItemTags.SULFUR_CUBE_ARCHETYPE_FAST_SLIDING).add(
                EBlockItemIds.CHISELED_PACKED_ICE_BRICKS, EBlockItemIds.CHISELED_SNOW_BRICKS
        );
    }
}