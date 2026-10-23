package team.lookingglass.elsewhere.registry.blocktypes;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import team.lookingglass.elsewhere.registry.util.EBlockStateProperties;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class RedstoneLanternBlock extends LanternBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final Map<BlockGetter, List<Toggle>> RECENT_TOGGLES = new WeakHashMap<>();
    public static final int RECENT_TOGGLE_TIMER = 60;
    public static final int MAX_RECENT_TOGGLES = 8;
    public static final int RESTART_DELAY = 160;
    private static final int TOGGLE_DELAY = 2;

    private static final VoxelShape SHAPE_STANDING = Shapes.or(
            Block.column(6.0D, 0.0D, 9.0D),
            Block.column(4.0D, 9.0D, 11.0D)
    );

    private static final VoxelShape SHAPE_HANGING = SHAPE_STANDING
            .move(0.0D, 0.125D, 0.0D)
            .optimize();

    private static final Map<Direction, VoxelShape> SHAPE_WALL = Shapes.rotateHorizontal(Shapes.or(
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 9.0D, 11.0D),
            Block.box(6.0D, 9.0D, 6.0D, 10.0D, 11.0D, 10.0D),
            Block.box(7.0, 13.0, 5.0, 9.0, 15.0, 15.0),
            Block.box(6.0, 9.0, 15.0, 10.0, 16.0, 16.0),
            Block.box(6.0, 12.0, 4.0, 10.0, 16.0, 5.0)
    ));

    public RedstoneLanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(HANGING, false)
                .setValue(WATERLOGGED, false)
                .setValue(LIT, true));
    }

    private VoxelShape getLanternShape(BlockState state) {
        if (state.getValue(EBlockStateProperties.WALL_HANGING)) {
            return SHAPE_WALL.get(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
        }
        return state.getValue(HANGING) ? SHAPE_HANGING : SHAPE_STANDING;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getLanternShape(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getLanternShape(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        notifyNeighbors(level, pos, state);
    }

    private void notifyNeighbors(Level level, BlockPos pos, BlockState state) {
        Orientation orientation = randomOrientation(level, state);

        for (Direction direction : Direction.values()) {
            level.updateNeighborsAt(
                    pos.relative(direction),
                    this,
                    ExperimentalRedstoneUtils.withFront(orientation, direction)
            );
        }
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        if (!movedByPiston) {
            notifyNeighbors(level, pos, state);
        }
    }

    protected boolean hasNeighborSignal(Level level, BlockPos pos, BlockState state) {
        return level.hasSignal(pos.below(), Direction.DOWN);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean neighborSignal = hasNeighborSignal(level, pos, state);
        List<Toggle> toggles = RECENT_TOGGLES.get(level);

        while (toggles != null && !toggles.isEmpty()
                && level.getGameTime() - toggles.getFirst().when > RECENT_TOGGLE_TIMER) {
            toggles.removeFirst();
        }

        if (state.getValue(LIT)) {
            if (neighborSignal) {
                level.setBlockAndUpdate(pos, state.setValue(LIT, false));

                if (isToggledTooFrequently(level, pos, true)) {
                    level.levelEvent(1502, pos, 0);
                    level.scheduleTick(pos, this, RESTART_DELAY);
                }
            }
        } else if (!neighborSignal && !isToggledTooFrequently(level, pos, false)) {
            level.setBlockAndUpdate(pos, state.setValue(LIT, true));
        }
    }

    @Override
    protected void neighborChanged(
            BlockState state,
            Level level,
            BlockPos pos,
            Block block,
            @Nullable Orientation orientation,
            boolean movedByPiston
    ) {
        if (state.getValue(LIT) == hasNeighborSignal(level, pos, state)
                && !level.getBlockTicks().willTickThisTick(pos, this)) {
            level.scheduleTick(pos, this, TOGGLE_DELAY);
        }
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction == Direction.DOWN ? state.getSignal(level, pos, direction) : 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int ownSignal(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(LIT) ? 10 : 0;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction != Direction.UP ? ownSignal(state, level, pos) : 0;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double x = pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
            double y = pos.getY() + 0.4D + (random.nextDouble() - 0.5D) * 0.2D;
            double z = pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;

            level.addParticle(
                    DustParticleOptions.REDSTONE,
                    x, y, z,
                    0.0D, 0.0D, 0.0D
            );
        }
    }

    private static boolean isToggledTooFrequently(Level level, BlockPos pos, boolean add) {
        List<Toggle> toggles =
                RECENT_TOGGLES.computeIfAbsent(level, k -> Lists.newArrayList());

        if (add) {
            toggles.add(new Toggle(pos.immutable(), level.getGameTime()));
        }

        int count = 0;

        for (Toggle toggle : toggles) {
            if (toggle.pos.equals(pos)) {
                ++count;

                if (count >= MAX_RECENT_TOGGLES) {
                    return true;
                }
            }
        }

        return false;
    }

    protected @Nullable Orientation randomOrientation(Level level, BlockState state) {
        return ExperimentalRedstoneUtils.initialOrientation(level, null, Direction.UP);
    }

    private record Toggle(BlockPos pos, long when) {}
}