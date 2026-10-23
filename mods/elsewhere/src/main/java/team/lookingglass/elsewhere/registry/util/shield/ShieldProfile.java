package team.lookingglass.elsewhere.registry.util.shield;

public interface ShieldProfile {
    int releaseCooldownTicks();
    int disableCooldownTicks();
    float blockThreshold();
}