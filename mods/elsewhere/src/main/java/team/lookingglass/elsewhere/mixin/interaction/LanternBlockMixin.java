package team.lookingglass.elsewhere.mixin.interaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.lookingglass.elsewhere.registry.util.EBlockStateProperties;

@Mixin(LanternBlock.class)
public abstract class LanternBlockMixin extends Block {
    protected LanternBlockMixin(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void elsewhere$defaultWallState(BlockBehaviour.Properties properties, CallbackInfo ci) {
        this.registerDefaultState(this.defaultBlockState().setValue(EBlockStateProperties.WALL_HANGING, false));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    private void elsewhere$addWallProperties(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(EBlockStateProperties.WALL_HANGING, BlockStateProperties.HORIZONTAL_FACING);
    }

    @Inject(method = "getConnectedDirection", at = @At("HEAD"), cancellable = true)
    private static void elsewhere$wallConnectedDirection(BlockState state, CallbackInfoReturnable<Direction> cir) {
        if (state.getValue(EBlockStateProperties.WALL_HANGING)) {
            cir.setReturnValue(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
        }
    }

    @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
    private void elsewhere$wallPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
        Direction face = context.getClickedFace();
        if (face.getAxis() == Direction.Axis.Y) {
            return;
        }
        BlockState state = this.defaultBlockState()
                .setValue(EBlockStateProperties.WALL_HANGING, true)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, face);
        if (state.canSurvive(context.getLevel(), context.getClickedPos())) {
            FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
            cir.setReturnValue(state.setValue(BlockStateProperties.WATERLOGGED, fluid.is(Fluids.WATER)));
        }
    }

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void elsewhere$wallShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.getValue(EBlockStateProperties.WALL_HANGING)) {
            cir.setReturnValue(EBlockStateProperties.WALL_LANTERN_SHAPES.get(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
        }
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        if (!state.getValue(EBlockStateProperties.WALL_HANGING)) {
            return state;
        }
        return state.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        if (!state.getValue(EBlockStateProperties.WALL_HANGING)) {
            return state;
        }
        return state.rotate(mirror.getRotation(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }
}