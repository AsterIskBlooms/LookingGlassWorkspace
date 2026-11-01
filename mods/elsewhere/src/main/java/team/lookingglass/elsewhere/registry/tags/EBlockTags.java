package team.lookingglass.elsewhere.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBlockTags {
    TagKey<Block> NATURAL_STONE_BLOCKS = create("natural_stone_blocks");
    TagKey<Block> SOIL = create("soil");
    TagKey<Block> NETHER_SOIL = create("nether_soil");

    TagKey<Block> CINNABAR_ORE_REPLACEABLES = create("cinnabar_ore_replaceables");
    TagKey<Block> CALCITE_ORE_REPLACEABLES = create("calcite_ore_replaceables");
    TagKey<Block> SOUL_STONE_ORE_REPLACEABLES = create("soul_stone_ore_replaceables");

    TagKey<Block> AMETHYST_SPIRE_REPLACEABLE = create("amethyst_spire_replaceable");

    TagKey<Block> NEEDS_COPPER_TOOL = create("needs_copper_tool");
    TagKey<Block> INCORRECT_FOR_BRONZE_TOOL = create("incorrect_for_bronze_tool");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    static void initialize() {}
}