package team.lookingglass.elsewhere.registry.render.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SparkleParticle extends SimpleAnimatedParticle {
    protected SparkleParticle(ClientLevel level, double x, double y, double z,
                              double xd, double yd, double zd, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);
        this.lifetime = 20 + this.random.nextInt(10);
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xd, double yd, double zd,
                                       RandomSource random) {
            return new SparkleParticle(level, x, y, z, xd, yd, zd, this.sprites);
        }
    }
}