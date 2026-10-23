package team.lookingglass.mirrorlib.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.sounds.BlockSoundSets;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.function.Function;

public class BlockRegistry {
    /**
     * Class of shorthands for registering blocks on 26.4+ versions.
     *
     * <p> Methods included for whether a {@link BlockItemId} is used or not.
     */
    public static ResourceKey<Block> createBlockKey(String id, String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(id, name));
    }
    public static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = blockFactory.apply(properties.setId(id));
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }
    public static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = register(id.block(), blockFactory, properties);
        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);
        return block;
    }
    public static Block registerItemless(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        return register(id.block(), blockFactory, properties);
    }
    public static Block registerCopyBlock(final BlockItemId id, Block block) {
        return register(id, Block::new, BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerStair(final BlockItemId id, Block block) {
        return register(id, p -> new StairBlock(block.defaultBlockState(), p), BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerSlab(final BlockItemId id, Block block) {
        return register(id, SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerFence(final BlockItemId id, Block block) {
        return register(id, FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerFenceGate(final BlockItemId id, WoodType woodType, Block block) {
        return register(id, p -> new FenceGateBlock(woodType, p), BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerWall(final BlockItemId id, Block block) {
        return register(id, WallBlock::new, BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerDoor(final BlockItemId id, BlockSetType blockSetType, Block block) {
        return register(id, p -> new DoorBlock(blockSetType, p.noOcclusion()), BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerTrapdoor(final BlockItemId id, BlockSetType blockSetType, Block block) {
        return register(id, p -> new TrapDoorBlock(blockSetType, p.noOcclusion()), BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerButton(final BlockItemId id, BlockSetType blockSetType, Block block, Integer ticksToStayPressed) {
        return register(id, p -> new ButtonBlock(blockSetType, ticksToStayPressed, p), BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerPressurePlate(final BlockItemId id, BlockSetType blockSetType, Block block) {
        return register(id, p -> new PressurePlateBlock(blockSetType, p), BlockBehaviour.Properties.ofFullCopy(block));
    }
    public static Block registerShelf(final BlockItemId id, Block block) {
        return register(id, ShelfBlock::new, BlockBehaviour.Properties.ofFullCopy(block).sound(BlockSoundSets.SHELF));
    }
    public static Block registerSapling(final BlockItemId id, TreeGrower treeGrower) {
        return register(id, p -> new SaplingBlock(treeGrower, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    }
    public static Block registerPottedSapling(final ResourceKey<Block> id, Block block) {
        return register(id, p -> new FlowerPotBlock(block, p), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
    }
    public static Block registerSign(final BlockItemId id, WoodType woodType, Block block) {
        return registerItemless(id, p -> new StandingSignBlock(woodType, p), BlockBehaviour.Properties.ofFullCopy(block).noCollision());
    }
    public static Block registerWallSign(final BlockItemId id, WoodType woodType, Block block) {
        return registerItemless(id, p -> new WallSignBlock(woodType, p), BlockBehaviour.Properties.ofFullCopy(block).noCollision());
    }
    public static Block registerCeilingHangingSign(final BlockItemId id, WoodType woodType, Block block) {
        return registerItemless(id, p -> new CeilingHangingSignBlock(woodType, p), BlockBehaviour.Properties.ofFullCopy(block).noCollision());
    }
    public static Block registerWallHangingSign(final BlockItemId id, WoodType woodType, Block block) {
        return registerItemless(id, p -> new WallHangingSignBlock(woodType, p), BlockBehaviour.Properties.ofFullCopy(block));
    }
}