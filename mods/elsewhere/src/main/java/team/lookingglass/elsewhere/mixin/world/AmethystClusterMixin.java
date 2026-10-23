package team.lookingglass.elsewhere.mixin.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCrystalClusterBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import team.lookingglass.elsewhere.registry.render.EParticles;

@Mixin(AmethystClusterBlock.class)
public abstract class AmethystClusterMixin extends AbstractCrystalClusterBlock {
    public AmethystClusterMixin(final float height, final float width, final BlockBehaviour.Properties props) {
        super(height, width, props);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        if (random.nextInt(60) == 0) {
            Direction facing = state.getValue(AmethystClusterBlock.FACING);
            double along = -0.4 + random.nextDouble() * 0.3;
            double x = pos.getX() + 0.5 + (facing.getStepX() != 0 ? facing.getStepX() * along : (random.nextDouble() - 0.5) * 0.5);
            double y = pos.getY() + 0.5 + (facing.getStepY() != 0 ? facing.getStepY() * along : (random.nextDouble() - 0.5) * 0.5);
            double z = pos.getZ() + 0.5 + (facing.getStepZ() != 0 ? facing.getStepZ() * along : (random.nextDouble() - 0.5) * 0.5);

            level.playLocalSound(x, y, z,
                    SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.AMBIENT,
                    0.4f + random.nextFloat() * 0.2f,
                    0.6f + random.nextFloat() * 0.8f,
                    false
            );
            double speed = 0.03;
            double jitter = 0.006;
            level.addParticle(EParticles.SPARKLE, x, y, z,
                    facing.getStepX() * speed + (random.nextDouble() - 0.5) * jitter,
                    facing.getStepY() * speed + (random.nextDouble() - 0.5) * jitter,
                    facing.getStepZ() * speed + (random.nextDouble() - 0.5) * jitter
            );
        }
    }
}