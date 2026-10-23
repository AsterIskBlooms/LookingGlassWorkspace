package team.lookingglass.mirrorlib.registry;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class BlockSpreadRegistry {
    private static final Map<Block, Map<Block, Block>> OVERRIDES = new HashMap<>();

    /**
     * Registers that when {@code spreader} random-tick fires and finds {@code target} nearby,
     * it should convert said {@code target} to {@code result} instead of the default (self-spread onto its own baseBlock).
     */
    public static void register(Block spreader, Block target, Block result) {
        OVERRIDES.computeIfAbsent(spreader, k -> new HashMap<>()).put(target, result);
    }

    /**
     * Returns the block {@code spreader} should place at a tested position that currently holds {@code target},
     * or null if this spreader has no opinion on that target.
     */
    public static Block resolve(Block spreader, Block target) {
        Map<Block, Block> perSpreader = OVERRIDES.get(spreader);
        return perSpreader != null ? perSpreader.get(target) : null;
    }

    private static void registerMutualCompetition(Block grassA, Block dirtA, Block grassB, Block dirtB) {
        BlockSpreadRegistry.register(grassA, dirtB, grassB);
        BlockSpreadRegistry.register(grassB, dirtA, grassA);
    }
}