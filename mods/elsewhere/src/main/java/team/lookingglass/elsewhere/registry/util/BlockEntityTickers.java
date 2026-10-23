package team.lookingglass.elsewhere.registry.util;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

public class BlockEntityTickers {

    public static <T extends BlockEntity> BlockEntityTicker<T> andThen(
            BlockEntityTicker<T> first, BlockEntityTicker<? super T> second) {
        return (level, pos, state, entity) -> {
            first.tick(level, pos, state, entity);
            second.tick(level, pos, state, entity);
        };
    }
}
