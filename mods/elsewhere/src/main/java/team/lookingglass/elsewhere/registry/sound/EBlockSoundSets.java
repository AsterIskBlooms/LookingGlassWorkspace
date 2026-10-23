package team.lookingglass.elsewhere.registry.sound;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.sounds.BlockSoundSet;
import team.lookingglass.elsewhere.Elsewhere;

import java.util.Optional;

public interface EBlockSoundSets {

    ResourceKey<BlockSoundSet> END_STONE = key("end_stone");
    ResourceKey<BlockSoundSet> STONE_BRICKS = key("stone_bricks");
    ResourceKey<BlockSoundSet> STONE_TILES = key("stone_tiles");
    ResourceKey<BlockSoundSet> SHALE = key("shale");
    ResourceKey<BlockSoundSet> SHALE_BRICKS = key("shale_bricks");
    ResourceKey<BlockSoundSet> SHALE_TILES = key("shale_tiles");
    ResourceKey<BlockSoundSet> CACTUS = key("cactus");
    ResourceKey<BlockSoundSet> ROCKWEED_BASALT = key("rockweed_basalt");
    ResourceKey<BlockSoundSet> SOUL_STONE = key("soul_stone");
    ResourceKey<BlockSoundSet> MOSSY_MUD = key("mossy_mud");
    ResourceKey<BlockSoundSet> IRON_GRATE = key("iron_grate");

    static void bootstrap(BootstrapContext<BlockSoundSet> context) {
        context.register(END_STONE, new BlockSoundSet(1F, 1F, ESounds.END_STONE));

        context.register(STONE_BRICKS, new BlockSoundSet(1F, 1F, ESounds.STONE_BRICKS));
        context.register(STONE_TILES, new BlockSoundSet(1F, 1.2F, ESounds.STONE_BRICKS));

        context.register(SHALE, new BlockSoundSet(1F, 1F, ESounds.SHALE));
        context.register(SHALE_BRICKS, new BlockSoundSet(1F, 1F, ESounds.SHALE_BRICKS));
        context.register(SHALE_TILES, new BlockSoundSet(1F, 1.2F, ESounds.SHALE_BRICKS));

        context.register(CACTUS, new BlockSoundSet(0.2F, 1F, ESounds.CACTUS));
        context.register(ROCKWEED_BASALT, new BlockSoundSet(1F, 1F, ESounds.ROCKWEED_BASALT));
        context.register(SOUL_STONE, new BlockSoundSet(1F, 1F, ESounds.SOUL_STONE));

        context.register(MOSSY_MUD, new BlockSoundSet(1F, 1F, SoundEvents.MUD
                .withStep(SoundEvents.MOSS.stepSound()).withHit(SoundEvents.MOSS.hitSound()).withFall(SoundEvents.MOSS.fallSound())));

        context.register(IRON_GRATE, new BlockSoundSet(1F, 0.75F, SoundEvents.COPPER_GRATE));
    }

    private static BlockSoundSet of(float volume, float pitch, Holder<SoundEvent> breakSound, Holder<SoundEvent> stepSound, Holder<SoundEvent> placeSound, Holder<SoundEvent> hitSound, Holder<SoundEvent> fallSound) {
        return new BlockSoundSet(volume, pitch,
                Optional.of(breakSound),
                Optional.of(stepSound),
                Optional.of(placeSound),
                Optional.of(hitSound),
                Optional.of(fallSound));
    }

    private static ResourceKey<BlockSoundSet> key(String id) {
        return ResourceKey.create(Registries.BLOCK_SOUND_SET, Identifier.fromNamespaceAndPath(Elsewhere.MODID, id));
    }
}