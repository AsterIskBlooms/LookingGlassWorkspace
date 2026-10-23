package team.lookingglass.elsewhere.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBlockEntityTypeIds {

    ResourceKey<BlockEntityType<?>> DUNGEON_SPAWNER = create("dungeon_spawner");
    ResourceKey<BlockEntityType<?>> DUNGEON_VAULT = create("dungeon_vault");

    private static ResourceKey<BlockEntityType<?>> create(final String name) {
        return ResourceKey.create(Registries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }
}
