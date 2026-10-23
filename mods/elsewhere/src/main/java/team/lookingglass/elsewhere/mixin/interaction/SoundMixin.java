package team.lookingglass.elsewhere.mixin.interaction;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.sounds.BlockSoundSet;
import net.minecraft.world.level.block.sounds.BlockSoundSets;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.elsewhere.registry.sound.EBlockSoundSets;
import team.lookingglass.elsewhere.registry.EBlocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Mixin(BlockBehaviour.class)
public class SoundMixin {

    @Unique
    private static Map<ResourceKey<BlockSoundSet>, Set<Block>> SOUND_OVERRIDES = null;

    @Unique
    private static Map<ResourceKey<BlockSoundSet>, Set<Block>> getSoundOverrides() {
        if (SOUND_OVERRIDES == null) {
            Map<ResourceKey<BlockSoundSet>, Set<Block>> map = new HashMap<>();
            map.put(BlockSoundSets.DRIPSTONE_BLOCK, Set.of(
                    Blocks.BLACKSTONE, Blocks.BLACKSTONE_STAIRS, Blocks.BLACKSTONE_SLAB, Blocks.BLACKSTONE_WALL,
                    Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE_WALL,
                    Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_WALL,
                    Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Blocks.CHISELED_POLISHED_BLACKSTONE,
                    Blocks.GILDED_BLACKSTONE
            ));
            map.put(EBlockSoundSets.END_STONE, Set.of(
                    Blocks.END_STONE,
                    Blocks.END_STONE_BRICKS, Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICK_WALL
            ));
            map.put(BlockSoundSets.DEEPSLATE_TILES, Set.of(
                    Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN, EBlocks.OBSIDIAN_STAIRS, EBlocks.OBSIDIAN_SLAB
            ));
            map.put(BlockSoundSets.NETHER_BRICKS, Set.of(
                    Blocks.BRICKS, Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, Blocks.BRICK_WALL
            ));
            map.put(EBlockSoundSets.STONE_BRICKS, Set.of(
                    Blocks.STONE_BRICKS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL, Blocks.CHISELED_STONE_BRICKS,
                    Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_WALL, Blocks.CRACKED_STONE_BRICKS,
                    EBlocks.GRANITE_BRICKS, EBlocks.GRANITE_BRICK_STAIRS, EBlocks.GRANITE_BRICK_SLAB, EBlocks.GRANITE_BRICK_WALL,
                    EBlocks.DIORITE_BRICKS, EBlocks.DIORITE_BRICK_STAIRS, EBlocks.DIORITE_BRICK_SLAB, EBlocks.DIORITE_BRICK_WALL,
                    EBlocks.ANDESITE_BRICKS, EBlocks.ANDESITE_BRICK_STAIRS, EBlocks.ANDESITE_BRICK_SLAB, EBlocks.ANDESITE_BRICK_WALL,
                    Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICK_SLAB, EBlocks.PRISMARINE_BRICK_WALL,
                    EBlocks.DARK_PRISMARINE_SCALES, EBlocks.DARK_PRISMARINE_SCALE_STAIRS, EBlocks.DARK_PRISMARINE_SCALE_SLAB, EBlocks.DARK_PRISMARINE_SCALE_WALL,
                    Blocks.QUARTZ_BRICKS, EBlocks.QUARTZ_BRICK_STAIRS, EBlocks.QUARTZ_BRICK_SLAB
            ));
            map.put(EBlockSoundSets.STONE_TILES, Set.of(
                    EBlocks.STONE_TILES, EBlocks.STONE_TILE_STAIRS, EBlocks.STONE_TILE_SLAB, EBlocks.STONE_TILE_WALL,
                    EBlocks.MOSSY_STONE_TILES, EBlocks.MOSSY_STONE_TILE_STAIRS, EBlocks.MOSSY_STONE_TILE_SLAB, EBlocks.MOSSY_STONE_TILE_WALL
            ));
            map.put(EBlockSoundSets.CACTUS, Set.of(
                    Blocks.CACTUS
            ));
            map.put(BlockSoundSets.ROOTED_DIRT, Set.of(
                    Blocks.CLAY
            ));
            map.put(BlockSoundSets.IRON, Set.of(
                    Blocks.SMITHING_TABLE, Blocks.BLAST_FURNACE
            ));
            map.put(BlockSoundSets.COPPER, Set.of(
                    Blocks.GOLD_BLOCK
            ));
            map.put(BlockSoundSets.AZALEA_LEAVES, Set.of(
                    Blocks.OAK_LEAVES,  Blocks.SPRUCE_LEAVES, Blocks.BIRCH_LEAVES,
                    Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES,
                    Blocks.MANGROVE_LEAVES, Blocks.CHERRY_LEAVES, Blocks.PALE_OAK_LEAVES
            ));


            SOUND_OVERRIDES = Map.copyOf(map);
        }
        return SOUND_OVERRIDES;
    }

    @Inject(method = "getSounds", at = @At("RETURN"), cancellable = true)
    private void modifyBlockSoundType(BlockState state, CallbackInfoReturnable<Optional<ResourceKey<BlockSoundSet>>> cir) {
        for (Map.Entry<ResourceKey<BlockSoundSet>, Set<Block>> entry : getSoundOverrides().entrySet()) {
            if (entry.getValue().contains(state.getBlock())) {
                cir.setReturnValue(Optional.of(entry.getKey()));
                return;
            }
        }
    }
}