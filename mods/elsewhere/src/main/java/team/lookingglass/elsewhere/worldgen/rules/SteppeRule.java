package team.lookingglass.elsewhere.worldgen.rules;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.material.MaterialRuleContext;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.material.rule.RuleEvaluator;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.Arrays;

public enum SteppeRule implements MaterialRule {
    INSTANCE;

    public static final MapCodec<SteppeRule> CODEC = MapCodec.unit(INSTANCE);

    @Override
    public MapCodec<SteppeRule> codec() {
        return CODEC;
    }

    @Override
    public RuleEvaluator compile(final MaterialRuleContext context) {
        return Bands::get;
    }

    private static final class Bands {
        private static final long SEED = 0x57E99EL;
        private static final int SIZE = 192;
        private static final double NOISE_SCALE = 1.0 / 128.0;
        private static final double OFFSET_AMPLITUDE = 4.0;

        private static final BlockState BASE = Blocks.DYED_TERRACOTTA.white().defaultBlockState();
        private static final BlockState REGULAR = Blocks.TERRACOTTA.defaultBlockState();
        private static final BlockState CYAN = Blocks.DYED_TERRACOTTA.cyan().defaultBlockState();
        private static final BlockState MAGENTA = Blocks.DYED_TERRACOTTA.magenta().defaultBlockState();
        private static final BlockState ORANGE = Blocks.DYED_TERRACOTTA.orange().defaultBlockState();
        private static final BlockState LIGHT_GRAY = Blocks.DYED_TERRACOTTA.lightGray().defaultBlockState();

        private static final BlockState[] BANDS;
        private static final SimplexNoise OFFSET;

        static {
            RandomSource random = RandomSource.create(SEED);
            BANDS = generate(random);
            OFFSET = new SimplexNoise(random);
        }

        static BlockState get(int x, int y, int z) {
            int offset = (int) Math.round(OFFSET.get(x * NOISE_SCALE, z * NOISE_SCALE) * OFFSET_AMPLITUDE);
            return BANDS[Math.floorMod(y + offset, SIZE)];
        }

        private static BlockState[] generate(RandomSource random) {
            BlockState[] bands = new BlockState[SIZE];
            Arrays.fill(bands, BASE);

            for (int i = 0; i < SIZE; i++) {
                i += random.nextInt(5) + 1;
                if (i < SIZE) bands[i] = REGULAR;
            }

            makeBands(random, bands, 1, 8, 12, CYAN);
            makeBands(random, bands, 2, 5, 9, MAGENTA);
            makeBands(random, bands, 1, 6, 10, ORANGE);

            int fringes = random.nextIntBetweenInclusive(9, 15);
            for (int i = 0, at = 0; i < fringes && at < SIZE; i++, at += random.nextInt(16) + 4) {
                if (at - 1 > 0 && random.nextBoolean()) bands[at - 1] = LIGHT_GRAY;
                if (at + 1 < SIZE && random.nextBoolean()) bands[at + 1] = LIGHT_GRAY;
            }
            return bands;
        }

        private static void makeBands(RandomSource random, BlockState[] bands, int minWidth, int minCount, int maxCount, BlockState state) {
            int count = random.nextIntBetweenInclusive(minCount, maxCount);
            for (int i = 0; i < count; i++) {
                int width = minWidth + random.nextInt(3);
                int start = random.nextInt(SIZE);
                for (int p = 0; start + p < SIZE && p < width; p++) {
                    bands[start + p] = state;
                }
            }
        }
    }
}