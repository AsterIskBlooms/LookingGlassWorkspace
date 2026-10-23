package team.lookingglass.elsewhere;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.equipment.EquipmentAssets;
import team.lookingglass.elsewhere.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import team.lookingglass.elsewhere.datagen.loot.EntityLootTableProvider;
import team.lookingglass.elsewhere.datagen.loot.BlockLootTableProvider;
import team.lookingglass.elsewhere.datagen.loot.StandardLootTableProvider;
import team.lookingglass.elsewhere.registry.equipment.ETrims;
import team.lookingglass.elsewhere.registry.sound.EBlockSoundSets;
import team.lookingglass.elsewhere.registry.sets.EDiscs;
import team.lookingglass.elsewhere.datagen.render.ModelProvider;
import team.lookingglass.elsewhere.datagen.render.ParticleProvider;
import team.lookingglass.elsewhere.datagen.tags.BiomeTagProvider;
import team.lookingglass.elsewhere.datagen.tags.BlockTagProvider;
import team.lookingglass.elsewhere.datagen.tags.EntityTagProvider;
import team.lookingglass.elsewhere.datagen.tags.ItemTagProvider;
import team.lookingglass.elsewhere.worldgen.*;
import team.lookingglass.elsewhere.worldgen.features.EFeatures;
import team.lookingglass.elsewhere.worldgen.features.EPlacements;
import team.lookingglass.elsewhere.worldgen.noise.EDensityFunctions;
import team.lookingglass.elsewhere.worldgen.structure.EStructures;
import team.lookingglass.mirrorlib.trim.gen.AtlasProvider;
import team.lookingglass.mirrorlib.trim.gen.EquipmentProvider;
import team.lookingglass.mirrorlib.trim.gen.TrimManifestProvider;

public class ElsewhereDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(RegistryDataGenerator::new);

		pack.addProvider(ModelProvider::new);
		pack.addProvider(ParticleProvider::new);

		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(EntityLootTableProvider::new);
		pack.addProvider(StandardLootTableProvider::new);

		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(EntityTagProvider::new);
		pack.addProvider(BiomeTagProvider::new);

		pack.addProvider(ERecipeProvider::new);
		pack.addProvider(EAdvancementProvider::new);
		pack.addProvider(LangProviderEN::new);

		pack.addProvider((FabricPackOutput o) -> new AtlasProvider(o, ETrims.KIT));
		pack.addProvider((FabricPackOutput o) -> new EquipmentProvider(o, ETrims.KIT));
		pack.addProvider((FabricPackOutput o) -> new TrimManifestProvider(o, ETrims.KIT));
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.FEATURE, EFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, EPlacements::bootstrap);
		registryBuilder.add(Registries.BIOME, EBiomes::bootstrap);
		registryBuilder.add(Registries.DENSITY_FUNCTION, EDensityFunctions::bootstrap);
		registryBuilder.add(Registries.JUKEBOX_SONG, EDiscs::bootstrap);
		registryBuilder.add(Registries.BLOCK_SOUND_SET, EBlockSoundSets::bootstrap);
		registryBuilder.add(Registries.TEMPLATE_POOL, EStructures::bootstrapTemplatePools);
		registryBuilder.add(Registries.STRUCTURE_SET, EStructures::bootstrapStructureSets);
		registryBuilder.add(Registries.STRUCTURE, EStructures::bootstrapStructures);
		registryBuilder.add(Registries.TRIM_MATERIAL, ETrims.KIT::bootstrapMaterials);
		registryBuilder.add(EquipmentAssets.ROOT_ID, ETrims.KIT::bootstrapEquipmentAssets);
	}
}