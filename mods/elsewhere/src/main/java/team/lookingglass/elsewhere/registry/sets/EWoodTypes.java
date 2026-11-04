package team.lookingglass.elsewhere.registry.sets;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import team.lookingglass.elsewhere.Elsewhere;

public interface EWoodTypes {
    WoodType AZALEA = new WoodType(createKey("azalea"), EBlockSets.AZALEA);
    WoodType CEDAR = new WoodType(createKey("cedar"), BlockSetType.DEFAULT_WOOD);
    WoodType MAHOGANY = new WoodType(createKey("mahogany"), BlockSetType.DEFAULT_WOOD);
    WoodType PINE = new WoodType(createKey("pine"), BlockSetType.DEFAULT_WOOD);

    private static String createKey(String name) {
        return Identifier.fromNamespaceAndPath(Elsewhere.MODID, name).toString();
    }

    static void initialize() {}
}
