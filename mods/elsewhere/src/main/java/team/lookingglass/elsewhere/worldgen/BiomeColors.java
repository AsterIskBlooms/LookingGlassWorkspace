package team.lookingglass.elsewhere.worldgen;

import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.Noise;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class BiomeColors {
    public static final class TundraGrassColor {
        private static final Noise NOISE = new SimplexNoise(new WorldgenRandom(new LegacyRandomSource(7319L)), true);
        private static final int COLOR_B = 0xd9b55f;
        private static final double SCALE = 0.0225;
        private static final double FADE_START = -0.15;
        private static final double FADE_END = 0.15;

        public static int get(double x, double z, int base) {
            double n = NOISE.get(x * SCALE, z * SCALE);
            double t = Mth.clampedLerp((n - FADE_START) / (FADE_END - FADE_START), 0.0, 1.0);
            t = t * t * (3.0 - 2.0 * t); // smoothstep

            int r = lerpChannel(base >> 16 & 0xFF, COLOR_B >> 16 & 0xFF, t);
            int g = lerpChannel(base >> 8 & 0xFF, COLOR_B >> 8 & 0xFF, t);
            int b = lerpChannel(base & 0xFF, COLOR_B & 0xFF, t);
            return 0xFF000000 | r << 16 | g << 8 | b;
        }
        private static int lerpChannel(int a, int b, double t) {
            return (int) Math.round(a + (b - a) * t);
        }
    }
}