package team.lookingglass.elsewhere.registry.sets;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.sounds.BlockSoundSets;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBlockSets {
    BlockSetType AZALEA = new BlockSetType(
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
    );

    static void initialize() {}
}
