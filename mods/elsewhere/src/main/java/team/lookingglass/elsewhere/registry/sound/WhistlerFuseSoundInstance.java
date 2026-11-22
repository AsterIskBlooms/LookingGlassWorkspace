package team.lookingglass.elsewhere.registry.sound;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import team.lookingglass.elsewhere.entity.mob.monster.Whistler;

@Environment(EnvType.CLIENT)
public class WhistlerFuseSoundInstance extends AbstractTickableSoundInstance {
    private final Whistler whistler;

    public WhistlerFuseSoundInstance(Whistler whistler) {
        super(ESounds.WHISTLER_DETONATE, SoundSource.HOSTILE, SoundInstance.createUnseededRandom());
        this.whistler = whistler;
        this.volume = 1.0F;
        this.pitch = 1.0F;
        this.x = whistler.getX();
        this.y = whistler.getY();
        this.z = whistler.getZ();
    }

    @Override
    public void tick() {
        if (!whistler.isRemoved()) {
            this.x = whistler.getX();
            this.y = whistler.getY();
            this.z = whistler.getZ();
        }
    }

    public static void play(Whistler whistler) {
        Minecraft.getInstance().getSoundManager().play(new WhistlerFuseSoundInstance(whistler));
    }
}