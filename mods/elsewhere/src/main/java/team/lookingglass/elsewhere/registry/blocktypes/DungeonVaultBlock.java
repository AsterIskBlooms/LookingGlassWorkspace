package team.lookingglass.elsewhere.registry.blocktypes;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultConfig;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;
import team.lookingglass.elsewhere.registry.EBlockEntityTypes;
import team.lookingglass.elsewhere.registry.EItems;
import team.lookingglass.elsewhere.registry.blocktypes.entity.DungeonVaultBlockEntity;

public class DungeonVaultBlock extends VaultBlock {
    public DungeonVaultBlock(final BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
        if (!itemStack.isEmpty() && state.getValue(STATE) == VaultState.ACTIVE) {
            if (level instanceof ServerLevel serverLevel) {
                if (!(serverLevel.getBlockEntity(pos) instanceof DungeonVaultBlockEntity vault)) {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                }
                VaultBlockEntity.Server.tryInsertKey(serverLevel, pos, state, vault.getConfig(), vault.getServerData(), vault.getSharedData(), player, itemStack);
            }
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos pos, final BlockState state) {
        return new DungeonVaultBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(final Level level, final BlockState blockState, final BlockEntityType<T> type) {
        if (level instanceof ServerLevel serverLevel) {
            return createTickerHelper(type, EBlockEntityTypes.DUNGEON_VAULT, (innerLevel, pos, state, entity) -> VaultBlockEntity.Server.tick(serverLevel, pos, state, entity.getConfig(), entity.getServerData(), entity.getSharedData()));
        }
        return createTickerHelper(type, EBlockEntityTypes.DUNGEON_VAULT, (innerLevel, pos, state, entity) -> VaultBlockEntity.Client.tick(innerLevel, pos, state, entity.getClientData(), entity.getSharedData()));
    }
}
