package team.lookingglass.elsewhere.registry.render;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;

import java.util.function.Function;

public interface EParticles {
    SimpleParticleType SPARKLE = register("sparkle", false);
    SimpleParticleType GILDED_BIRCH_LEAVES = register("gilded_birch_leaves", false);
    SimpleParticleType SUBSLIME_PARTICLE = register("subslime_particle", false);

    private static SimpleParticleType register(String name, boolean overrideLimiter) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(Elsewhere.MODID, name),
                FabricParticleTypes.simple(overrideLimiter));
    }
    private static <T extends ParticleOptions> ParticleType<T> register(
            String name,
            boolean overrideLimiter,
            Function<ParticleType<T>, MapCodec<T>> codec,
            Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> streamCodec) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(Elsewhere.MODID, name),
                FabricParticleTypes.complex(overrideLimiter, codec, streamCodec));
    }

    static void initialize() {}
}