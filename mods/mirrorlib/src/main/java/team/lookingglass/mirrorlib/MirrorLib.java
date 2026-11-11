package team.lookingglass.mirrorlib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.lookingglass.mirrorlib.world.density.DensityModifiers;

public class MirrorLib implements ModInitializer {
	public static final String MODID = "mirrorlib";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final boolean DATAGEN = System.getProperty("fabric-api.datagen") != null;

    @Override
	public void onInitialize() {
		DensityModifiers.initialize();
	}
}