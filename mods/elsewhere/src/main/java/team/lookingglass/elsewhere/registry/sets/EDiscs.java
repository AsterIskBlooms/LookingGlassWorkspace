package team.lookingglass.elsewhere.registry.sets;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.sound.ESounds;

public interface EDiscs {
    ResourceKey<JukeboxSong> FOX = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "fox"));

    static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, FOX, ESounds.FOX_DISC,
                "fox", 196, 9);
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, SoundEvent sound,
                                     String translationKey, float lengthInSeconds, int comparatorOutput) {
        HolderGetter<SoundEvent> soundEvents = context.lookup(Registries.SOUND_EVENT);
        context.register(key, new JukeboxSong(soundEvents.getOrThrow(ResourceKey.create(Registries.SOUND_EVENT, sound.location())),
                Component.translatable("jukebox_song." + Elsewhere.MODID + "." + translationKey), lengthInSeconds, comparatorOutput));
    }
}
