package team.lookingglass.mirrorlib.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowyBlock;
import net.minecraft.world.level.block.SpreadingSnowyBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.mirrorlib.registry.BlockSpreadRegistry;

@Mixin(SpreadingSnowyBlock.class)
public abstract class SpreadingSnowyBlockMixin {
    @Shadow
    private static boolean canStayAlive(BlockState state, LevelReader level, BlockPos pos) {
        throw new AssertionError();
    }

    @Shadow
    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        throw new AssertionError();
    }

    @Inject(method = "randomTick", at = @At("TAIL"))
    private void mirrorlib$spreadOverrides(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (!canStayAlive(state, level, pos) || level.getMaxLocalRawBrightness(pos.above()) < 9) {
            return;
        }
        Block spreader = state.getBlock();
        for (int i = 0; i < 4; i++) {
            BlockPos testPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
            Block result = BlockSpreadRegistry.resolve(spreader, level.getBlockState(testPos).getBlock());
            if (result == null) {
                continue;
            }
            BlockState resultState = result.defaultBlockState();
            if (!canPropagate(resultState, level, testPos)) {
                continue;
            }
            boolean snowy = level.getBlockState(testPos.above()).is(BlockTags.SNOW);
            level.setBlockAndUpdate(testPos, resultState.setValue(SnowyBlock.SNOWY, snowy));
        }
    }
}
