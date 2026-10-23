package team.lookingglass.elsewhere.registry.blocktypes;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TrialSpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import team.lookingglass.elsewhere.registry.blocktypes.entity.DungeonSpawnerBlockEntity;
import team.lookingglass.elsewhere.registry.EBlockEntityTypes;

public class DungeonSpawnerBlock extends TrialSpawnerBlock {
    public DungeonSpawnerBlock(final BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        return new DungeonSpawnerBlockEntity(worldPosition, blockState);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(final Level level, final BlockState blockState, final BlockEntityType<T> type) {
        if (level instanceof ServerLevel serverLevel) {
            return createTickerHelper(type, EBlockEntityTypes.DUNGEON_SPAWNER, (innerLevel, pos, state, entity) -> entity.getTrialSpawner().tickServer(serverLevel, pos, state.getValue(OMINOUS)));
        }
        return createTickerHelper(type, EBlockEntityTypes.DUNGEON_SPAWNER, (innerLevel, pos, state, entity) -> entity.getTrialSpawner().tickClient(innerLevel, pos, state.getValue(OMINOUS)));
    }
}
