package team.lookingglass.mirrorlib.registry.color;

@FunctionalInterface
public interface GrassColorProvider {
    /** @param baseColor the biome's normal grass color (override or texture lookup) */
    int getColor(double x, double z, int baseColor);
}