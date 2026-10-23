package team.lookingglass.elsewhere.registry.sound;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.sounds.BlockSoundCollection;
import net.minecraft.world.level.block.sounds.BlockSoundSet;
import team.lookingglass.elsewhere.Elsewhere;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import java.util.Optional;

public interface ESounds {

    // Block Sound Sets
    BlockSoundCollection<Optional<Holder<SoundEvent>>> END_STONE = registerForBlockSoundSet(EBlockSoundSets.END_STONE);
    BlockSoundCollection<Optional<Holder<SoundEvent>>> STONE_BRICKS = registerForBlockSoundSet(EBlockSoundSets.STONE_BRICKS);
    BlockSoundCollection<Optional<Holder<SoundEvent>>> SHALE = registerForBlockSoundSet(EBlockSoundSets.SHALE);
    BlockSoundCollection<Optional<Holder<SoundEvent>>> SHALE_BRICKS = registerForBlockSoundSet(EBlockSoundSets.SHALE_BRICKS);
    BlockSoundCollection<Optional<Holder<SoundEvent>>> CACTUS = registerForBlockSoundSet(EBlockSoundSets.CACTUS);
    BlockSoundCollection<Optional<Holder<SoundEvent>>> ROCKWEED_BASALT = registerForBlockSoundSet(EBlockSoundSets.ROCKWEED_BASALT);
    BlockSoundCollection<Optional<Holder<SoundEvent>>> SOUL_STONE = registerForBlockSoundSet(EBlockSoundSets.SOUL_STONE);

    // Subslime
    SoundEvent SUBSLIME_JUMP = register("entity.subslime.jump");
    SoundEvent SUBSLIME_SQUISH = register("entity.subslime.squish");
    SoundEvent SUBSLIME_ATTACK = register("entity.subslime.attack");
    SoundEvent SUBSLIME_HURT = register("entity.subslime.hurt");
    SoundEvent SUBSLIME_DEATH = register("entity.subslime.death");

    // Vanguard
    SoundEvent VANGUARD_BLABBER = register("entity.vanguard.blabber");
    SoundEvent VANGUARD_SINGLE_SWING = register("entity.vanguard.single_swing");
    SoundEvent VANGUARD_DOUBLE_SWING = register("entity.vanguard.double_swing");
    SoundEvent VANGUARD_CHARGE = register("entity.vanguard.charge");
    SoundEvent VANGUARD_STUN = register("entity.vanguard.stun");

    // Whistler
    SoundEvent WHISTLER_AMBIENT = register("entity.whistler.ambient");
    SoundEvent WHISTLER_DETONATE = register("entity.whistler.detonate");

    // Perch :3
    SoundEvent PERCH_FLOP = register("entity.perch.flop");
    SoundEvent PERCH_HURT = register("entity.perch.hurt");
    SoundEvent PERCH_DEATH = register("entity.perch.death");

    SoundEvent FOX_DISC = register("disc.fox");

    Holder.Reference<SoundEvent> MUSIC_BIOME_CRYSTAL_CAVERNS = registerForHolder("music.overworld.crystal_caverns");




    // Method
    private static SoundEvent register(String id) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Elsewhere.MODID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }
    private static Holder.Reference<SoundEvent> registerForHolder(final Identifier id) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
    private static Holder.Reference<SoundEvent> registerForHolder(final String id) {
        return registerForHolder(Identifier.fromNamespaceAndPath(Elsewhere.MODID, id));
    }
    private static BlockSoundCollection<Optional<Holder<SoundEvent>>> registerForBlockSoundSet(ResourceKey<BlockSoundSet> blockSoundSet) {
        return BlockSoundCollection.createIdsForBlockSoundSet(blockSoundSet).map(id -> Optional.of(registerForHolder(id)));
    }

    // Initialize
    static void initialize() {}
}