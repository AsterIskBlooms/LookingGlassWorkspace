package team.lookingglass.elsewhere;

import team.lookingglass.elsewhere.registry.events.EFlammableBlocks;
import team.lookingglass.elsewhere.entity.EEntities;
import team.lookingglass.elsewhere.entity.EEntitySpawns;
import team.lookingglass.elsewhere.registry.*;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.lookingglass.elsewhere.registry.attribute.EAttributes;
import team.lookingglass.elsewhere.registry.recipe.ERecipes;
import team.lookingglass.elsewhere.registry.sound.ESounds;
import team.lookingglass.elsewhere.registry.EBlockEntityTypes;
import team.lookingglass.elsewhere.registry.events.EServerEvents;
import team.lookingglass.elsewhere.registry.sets.ETreeGrowers;
import team.lookingglass.elsewhere.registry.render.EParticles;
import team.lookingglass.elsewhere.registry.tags.*;
import team.lookingglass.elsewhere.registry.util.EBlockTransformers;
import team.lookingglass.elsewhere.worldgen.features.EBiomeModifications;
import team.lookingglass.elsewhere.worldgen.features.EFeatureTypes;
import team.lookingglass.elsewhere.worldgen.rules.EMaterialRuleInjection;
import team.lookingglass.elsewhere.worldgen.features.modifiers.EPlacementModifiers;

public class Elsewhere implements ModInitializer {
	public static final String MODID = "elsewhere";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Override
	public void onInitialize() {
		EBlockItemIds.initialize();
		EItemIds.initialize();
		EBlocks.initialize();
		EItems.initialize();
		ERecipes.initialize();
		ESounds.initialize();
		EParticles.initialize();
		ETabs.initialize();
		EBlockEntityTypes.initialize();
		EDataComponents.initialize();

		EBlockTransformers.initialize();
		EServerEvents.initialize();
		EFlammableBlocks.initialize();
		ELootModifications.initialize();

		EBlockTags.initialize();
		EItemTags.initialize();
		EBiomeTags.initialize();
		EEntityTags.initialize();

		ETreeGrowers.initialize();
		EPlacementModifiers.initialize();
		EFeatureTypes.initialize();
		EBiomeModifications.initialize();
		EMaterialRuleInjection.initialize();

		EAttributes.initialize();
		EEntities.initialize();
		EEntitySpawns.initialize();
	}
}