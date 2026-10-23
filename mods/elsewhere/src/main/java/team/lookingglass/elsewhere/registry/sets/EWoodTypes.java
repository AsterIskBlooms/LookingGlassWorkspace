package team.lookingglass.elsewhere.registry.sets;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.sounds.BlockSoundSets;
import net.minecraft.world.level.block.state.properties.WoodType;
import team.lookingglass.elsewhere.Elsewhere;

public interface EWoodTypes {
    WoodType AZALEA = WoodType.register(new WoodType(
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "azalea").toString(),
            EBlockSets.AZALEA,
            BlockSoundSets.CHERRY_WOOD,
            BlockSoundSets.CHERRY_WOOD_HANGING_SIGN,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN
    ));

    WoodType CEDAR = WoodType.register(new WoodType(
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cedar").toString(),
            EBlockSets.CEDAR,
            BlockSoundSets.CHERRY_WOOD,
            BlockSoundSets.CHERRY_WOOD_HANGING_SIGN,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN
    ));

    WoodType MAHOGANY = WoodType.register(new WoodType(
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mahogany").toString(),
            EBlockSets.MAHOGANY,
            BlockSoundSets.CHERRY_WOOD,
            BlockSoundSets.CHERRY_WOOD_HANGING_SIGN,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN
    ));


    static void initialize() {}
}
