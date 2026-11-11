package team.lookingglass.mirrorlib.world.region;

/**
 * Picks a region index for a block position. Region 0 is carved out first, then region 1
 * from the remainder, and so on. Each split uses its own independent noise field, and the
 * noise is normalized to a roughly uniform distribution, so the area of each region
 * matches its weight.
 */
public final class RegionSelector {
    public static final double DEFAULT_SCALE = 4096.0D;

    private static final double GAIN = 2.3D;
    private static final double SIGMA = 0.40D;
    private static final int[] GRAD_X = {1, -1, 1, -1, 1, -1, 0, 0};
    private static final int[] GRAD_Z = {1, 1, -1, -1, 0, 0, 1, -1};

    private final long[] seeds;
    private final double[] thresholds;
    private final double scale;

    public RegionSelector(long worldSeed, double scale, float... weights) {
        if (weights.length == 0) {
            throw new IllegalArgumentException("At least one region is required");
        }
        if (!(scale > 0.0D)) {
            throw new IllegalArgumentException("Region scale must be positive: " + scale);
        }

        double remaining = 0.0D;
        for (float weight : weights) {
            if (!Float.isFinite(weight) || weight <= 0.0F) {
                throw new IllegalArgumentException("Region weight must be finite and positive: " + weight);
            }
            remaining += weight;
        }

        this.scale = scale;
        this.seeds = new long[weights.length - 1];
        this.thresholds = new double[weights.length - 1];

        for (int i = 0; i < this.thresholds.length; i++) {
            this.seeds[i] = mix(worldSeed + (i + 1) * 0x9E3779B97F4A7C15L);
            this.thresholds[i] = weights[i] / remaining;
            remaining -= weights[i];
        }
    }

    /** Returns the index of the region at this position, in the order the weights were given. */
    public int select(int blockX, int blockZ) {
        double x = blockX / this.scale;
        double z = blockZ / this.scale;

        for (int i = 0; i < this.thresholds.length; i++) {
            if (uniform(fractal(this.seeds[i], x, z) * GAIN) < this.thresholds[i]) {
                return i;
            }
        }

        return this.thresholds.length;
    }

    private static double uniform(double value) {
        return 0.5D * (1.0D + erf(value / (SIGMA * Math.sqrt(2.0D))));
    }

    private static double erf(double x) {
        double sign = Math.signum(x);
        double a = Math.abs(x);
        double t = 1.0D / (1.0D + 0.3275911D * a);
        double poly = ((((1.061405429D * t - 1.453152027D) * t) + 1.421413741D) * t - 0.284496736D) * t + 0.254829592D;
        return sign * (1.0D - poly * t * Math.exp(-a * a));
    }

    private static double fractal(long seed, double x, double z) {
        double sum = 0.0D;
        double amp = 1.0D;
        double freq = 1.0D;
        double total = 0.0D;
        for (int o = 0; o < 3; o++) {
            sum += amp * perlin(seed + o * 7919L, x * freq, z * freq);
            total += amp;
            amp *= 0.5D;
            freq *= 2.0D;
        }
        return sum / total;
    }

    private static double perlin(long seed, double x, double z) {
        int x0 = (int) Math.floor(x);
        int z0 = (int) Math.floor(z);
        double fx = x - x0;
        double fz = z - z0;
        double n00 = dot(seed, x0, z0, fx, fz);
        double n10 = dot(seed, x0 + 1, z0, fx - 1.0D, fz);
        double n01 = dot(seed, x0, z0 + 1, fx, fz - 1.0D);
        double n11 = dot(seed, x0 + 1, z0 + 1, fx - 1.0D, fz - 1.0D);
        double u = fade(fx);
        double v = fade(fz);
        return lerp(v, lerp(u, n00, n10), lerp(u, n01, n11));
    }

    private static double dot(long seed, int ix, int iz, double dx, double dz) {
        long h = mix(seed ^ (ix * 0x9E3779B97F4A7C15L) ^ (iz * 0xC2B2AE3D27D4EB4FL));
        int g = (int) (h >>> 32) & 7;
        return GRAD_X[g] * dx + GRAD_Z[g] * dz;
    }

    private static double fade(double t) {
        return t * t * t * (t * (t * 6.0D - 15.0D) + 10.0D);
    }

    private static double lerp(double t, double a, double b) {
        return a + (b - a) * t;
    }

    private static long mix(long h) {
        h ^= h >>> 30;
        h *= 0xBF58476D1CE4E5B9L;
        h ^= h >>> 27;
        h *= 0x94D049BB133111EBL;
        return h ^ (h >>> 31);
    }
}