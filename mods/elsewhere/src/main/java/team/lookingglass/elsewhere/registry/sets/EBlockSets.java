package team.lookingglass.elsewhere.registry.sets;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.sounds.BlockSoundSets;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBlockSets {
    BlockSetType AZALEA = BlockSetType.register(new BlockSetType(
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "azalea").toString(),
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            BlockSoundSets.CHERRY_WOOD,
            SoundEvents.CHERRY_WOOD_DOOR_CLOSE,
            SoundEvents.CHERRY_WOOD_DOOR_OPEN,
            SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE,
            SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN,
            SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF,
            SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON
    ));

    BlockSetType CEDAR = BlockSetType.register(new BlockSetType(
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cedar").toString(),
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            BlockSoundSets.WOOD,
            SoundEvents.WOODEN_DOOR_CLOSE,
            SoundEvents.WOODEN_DOOR_OPEN,
            SoundEvents.WOODEN_TRAPDOOR_CLOSE,
            SoundEvents.WOODEN_TRAPDOOR_OPEN,
            SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.WOODEN_BUTTON_CLICK_OFF,
            SoundEvents.WOODEN_BUTTON_CLICK_ON
    ));

    BlockSetType MAHOGANY = BlockSetType.register(new BlockSetType(
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mahogany").toString(),
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            BlockSoundSets.WOOD,
            SoundEvents.WOODEN_DOOR_CLOSE,
            SoundEvents.WOODEN_DOOR_OPEN,
            SoundEvents.WOODEN_TRAPDOOR_CLOSE,
            SoundEvents.WOODEN_TRAPDOOR_OPEN,
            SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.WOODEN_BUTTON_CLICK_OFF,
            SoundEvents.WOODEN_BUTTON_CLICK_ON
    ));

    static void initialize() {}
}
