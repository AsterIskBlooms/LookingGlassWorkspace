package team.lookingglass.elsewhere.registry.render.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class CubeParticle extends SingleQuadParticle {
    private final float uo;
    private final float vo;
    protected CubeParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, SpriteSet sprites, RandomSource random) {
        super(level, x, y, z, xd, yd, zd, sprites.get(random));
        this.gravity = 1.0F;
        this.quadSize *= 0.5F;
        this.lifetime = 20;
        this.uo = this.random.nextFloat() * 3.0F;
        this.vo = this.random.nextFloat() * 3.0F;
    }

    @Override
    protected Layer getLayer() {
        return Layer.bySprite(this.sprite);
    }

    @Override
    protected float getU0() { return this.sprite.getU((this.uo + 1.0F) / 4.0F); }

    @Override
    protected float getU1() { return this.sprite.getU(this.uo / 4.0F); }

    @Override
    protected float getV0() { return this.sprite.getV(this.vo / 4.0F); }

    @Override
    protected float getV1() { return this.sprite.getV((this.vo + 1.0F) / 4.0F); }


    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            return new CubeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites, random);
        }
    }
}