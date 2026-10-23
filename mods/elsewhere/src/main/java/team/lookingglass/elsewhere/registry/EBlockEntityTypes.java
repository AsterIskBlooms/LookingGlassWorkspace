package team.lookingglass.elsewhere.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.blocktypes.entity.DungeonSpawnerBlockEntity;
import team.lookingglass.elsewhere.registry.blocktypes.entity.DungeonVaultBlockEntity;

public interface EBlockEntityTypes {

    BlockEntityType<DungeonSpawnerBlockEntity> DUNGEON_SPAWNER = register(EBlockEntityTypeIds.DUNGEON_SPAWNER, DungeonSpawnerBlockEntity::new, EBlocks.DUNGEON_SPAWNER);
    BlockEntityType<DungeonVaultBlockEntity> DUNGEON_VAULT = register(EBlockEntityTypeIds.DUNGEON_VAULT, DungeonVaultBlockEntity::new, EBlocks.DUNGEON_VAULT);


    static void initialize() {
        BlockEntityTypes.SIGN.addValidBlock(EBlocks.AZALEA_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(EBlocks.AZALEA_WALL_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(EBlocks.AZALEA_HANGING_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(EBlocks.AZALEA_WALL_HANGING_SIGN);
        BlockEntityTypes.SHELF.addValidBlock(EBlocks.AZALEA_SHELF);

        BlockEntityTypes.SIGN.addValidBlock(EBlocks.CEDAR_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(EBlocks.CEDAR_WALL_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(EBlocks.CEDAR_HANGING_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(EBlocks.CEDAR_WALL_HANGING_SIGN);
        BlockEntityTypes.SHELF.addValidBlock(EBlocks.CEDAR_SHELF);

        BlockEntityTypes.SIGN.addValidBlock(EBlocks.MAHOGANY_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(EBlocks.MAHOGANY_WALL_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(EBlocks.MAHOGANY_HANGING_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(EBlocks.MAHOGANY_WALL_HANGING_SIGN);
        BlockEntityTypes.SHELF.addValidBlock(EBlocks.MAHOGANY_SHELF);
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(ResourceKey<BlockEntityType<?>> id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... validBlocks) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.create(factory, validBlocks).build());
    }
}
