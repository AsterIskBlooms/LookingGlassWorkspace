package team.lookingglass.elsewhere.registry.util;

import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.minecraft.world.level.block.Blocks;
import team.lookingglass.elsewhere.registry.EBlocks;

public class EBlockTransformers {
    public static void initialize() {
        BlockTransformerHelper.registerStripping(EBlocks.AZALEA_LOG, EBlocks.STRIPPED_AZALEA_LOG);
        BlockTransformerHelper.registerStripping(EBlocks.AZALEA_WOOD, EBlocks.STRIPPED_AZALEA_WOOD);
        BlockTransformerHelper.registerStripping(EBlocks.CEDAR_LOG, EBlocks.STRIPPED_CEDAR_LOG);
        BlockTransformerHelper.registerStripping(EBlocks.CEDAR_WOOD, EBlocks.STRIPPED_CEDAR_WOOD);
        BlockTransformerHelper.registerStripping(EBlocks.MAHOGANY_LOG, EBlocks.STRIPPED_MAHOGANY_LOG);
        BlockTransformerHelper.registerStripping(EBlocks.MAHOGANY_WOOD, EBlocks.STRIPPED_MAHOGANY_WOOD);
        BlockTransformerHelper.registerStripping(EBlocks.BAMBOO_STEM, EBlocks.STRIPPED_BAMBOO_STEM);

        BlockTransformerHelper.registerFlattening(EBlocks.RED_GRASS_BLOCK, Blocks.DIRT_PATH);
        BlockTransformerHelper.registerTilling(EBlocks.RED_GRASS_BLOCK, Blocks.FARMLAND);
    }
}
