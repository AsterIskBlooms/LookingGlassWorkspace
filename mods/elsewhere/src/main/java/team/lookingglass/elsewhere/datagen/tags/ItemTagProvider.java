package team.lookingglass.elsewhere.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import team.lookingglass.elsewhere.registry.EBlockItemIds;
import team.lookingglass.elsewhere.registry.tags.EItemTags;
import team.lookingglass.elsewhere.registry.EItemIds;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }



    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Food
        builder(ItemTags.CAT_FOOD).add(EItemIds.RAW_PERCH);
        builder(ItemTags.OCELOT_FOOD).add(EItemIds.RAW_PERCH);
        builder(ItemTags.WOLF_FOOD).add(EItemIds.RAW_PERCH, EItemIds.COOKED_PERCH);
        builder(ItemTags.MEAT).add(EItemIds.RAW_PERCH, EItemIds.COOKED_PERCH);

        // Wood
        builder(EItemTags.AZALEA_LOGS).add(
                EBlockItemIds.AZALEA_LOG, EBlockItemIds.STRIPPED_AZALEA_LOG,
                EBlockItemIds.AZALEA_WOOD, EBlockItemIds.STRIPPED_AZALEA_WOOD
        );
        builder(EItemTags.CEDAR_LOGS).add(
                EBlockItemIds.CEDAR_LOG, EBlockItemIds.STRIPPED_CEDAR_LOG,
                EBlockItemIds.CEDAR_WOOD, EBlockItemIds.STRIPPED_CEDAR_WOOD
        );
        builder(EItemTags.MAHOGANY_LOGS).add(
                EBlockItemIds.MAHOGANY_LOG, EBlockItemIds.STRIPPED_MAHOGANY_LOG,
                EBlockItemIds.MAHOGANY_WOOD, EBlockItemIds.STRIPPED_MAHOGANY_WOOD
        );
        builder(ItemTags.LOGS).add(
                EBlockItemIds.AZALEA_LOG, EBlockItemIds.STRIPPED_AZALEA_LOG,
                EBlockItemIds.AZALEA_WOOD, EBlockItemIds.STRIPPED_AZALEA_WOOD,
                EBlockItemIds.CEDAR_LOG, EBlockItemIds.STRIPPED_CEDAR_LOG,
                EBlockItemIds.CEDAR_WOOD, EBlockItemIds.STRIPPED_CEDAR_WOOD,
                EBlockItemIds.MAHOGANY_LOG, EBlockItemIds.STRIPPED_MAHOGANY_LOG,
                EBlockItemIds.MAHOGANY_WOOD, EBlockItemIds.STRIPPED_MAHOGANY_WOOD
        );
        builder(ItemTags.LOGS_THAT_BURN).add(
                EBlockItemIds.AZALEA_LOG, EBlockItemIds.STRIPPED_AZALEA_LOG,
                EBlockItemIds.AZALEA_WOOD, EBlockItemIds.STRIPPED_AZALEA_WOOD,
                EBlockItemIds.CEDAR_LOG, EBlockItemIds.STRIPPED_CEDAR_LOG,
                EBlockItemIds.CEDAR_WOOD, EBlockItemIds.STRIPPED_CEDAR_WOOD,
                EBlockItemIds.MAHOGANY_LOG, EBlockItemIds.STRIPPED_MAHOGANY_LOG,
                EBlockItemIds.MAHOGANY_WOOD, EBlockItemIds.STRIPPED_MAHOGANY_WOOD
        );
        builder(ItemTags.PLANKS).add(
                EBlockItemIds.AZALEA_PLANKS, EBlockItemIds.CEDAR_PLANKS, EBlockItemIds.MAHOGANY_PLANKS
        );
        builder(ItemTags.WOODEN_STAIRS).add(
                EBlockItemIds.AZALEA_STAIRS, EBlockItemIds.CEDAR_STAIRS, EBlockItemIds.MAHOGANY_STAIRS
        );
        builder(ItemTags.WOODEN_SLABS).add(
                EBlockItemIds.AZALEA_SLAB, EBlockItemIds.CEDAR_SLAB, EBlockItemIds.MAHOGANY_SLAB
        );
        builder(ItemTags.WOODEN_FENCES).add(
                EBlockItemIds.AZALEA_FENCE, EBlockItemIds.CEDAR_FENCE, EBlockItemIds.MAHOGANY_FENCE
        );
        builder(ItemTags.FENCE_GATES).add(
                EBlockItemIds.AZALEA_FENCE_GATE, EBlockItemIds.CEDAR_FENCE_GATE, EBlockItemIds.MAHOGANY_FENCE_GATE
        );

        // Sand
        builder(ItemTags.SAND).add(EBlockItemIds.PINK_SAND);

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

        // Soulsteel
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


        // Trim Tags
        builder(ItemTags.TRIM_MATERIALS).add(
                EItemIds.TIN_INGOT, EItemIds.BRONZE_ALLOY, EItemIds.SILVER_INGOT, EItemIds.SOULSTEEL_ALLOY, EItemIds.ELECTRUM_ALLOY
        );
        builder(ItemTags.TRIMMABLE_ARMOR)
                .addTag(EItemTags.BRONZE_EQUIPMENT)
                .addTag(EItemTags.SOULSTEEL_EQUIPMENT)
                .addTag(EItemTags.ELECTRUM_EQUIPMENT)
        ;

















    }

}