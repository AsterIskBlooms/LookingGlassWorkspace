package team.lookingglass.elsewhere.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.joml.Vector3f;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.EEntities;
import team.lookingglass.elsewhere.registry.sound.ESounds;
import team.lookingglass.elsewhere.worldgen.features.EPlacements;

public interface  EBiomes {

    static void bootstrap(BootstrapContext<Biome> context) {
        context.register(OUTBACK, createOutback(context));
        context.register(LUSH_DESERT, createLushDesert(context));
        context.register(TUNDRA, createTundra(context));
        context.register(BOG, createBog(context));
        context.register(CHERRY_FIELDS, createCherryFields(context));
        context.register(SHRUBLAND, createShrubland(context));
        context.register(STEPPE, createSteppe(context));
        context.register(CEDAR_FOREST, createCedarForest(context));
        context.register(CLOUD_FOREST, createCloudForest(context));

        context.register(TIDEPOOLS, createTidepools(context));
        context.register(COLD_BEACH, createColdBeach(context));
        context.register(FROZEN_BEACH, createFrozenBeach(context));
        context.register(LUKEWARM_RIVER, createLukewarmRiver(context));
        context.register(COLD_RIVER, createColdRiver(context));

        context.register(CAVES, createDefaultCaves(context));
        context.register(CRYSTAL_CAVERNS, createCrystalCaverns(context));
        context.register(ARID_CAVES, createAridCaves(context));
        context.register(VOLCANIC_DEPTHS, createVolcanicDepths(context));
    }

    ResourceKey<Biome> OUTBACK = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "outback"));
    ResourceKey<Biome> LUSH_DESERT = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "lush_desert"));
    ResourceKey<Biome> TUNDRA = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "tundra"));
    ResourceKey<Biome> BOG = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "bog"));
    ResourceKey<Biome> CHERRY_FIELDS = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cherry_fields"));
    ResourceKey<Biome> SHRUBLAND = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "shrubland"));
    ResourceKey<Biome> STEPPE = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "steppe"));
    ResourceKey<Biome> CEDAR_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cedar_forest"));
    ResourceKey<Biome> CLOUD_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cloud_forest"));

    ResourceKey<Biome> TIDEPOOLS = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "tidepools"));
    ResourceKey<Biome> COLD_BEACH = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cold_beach"));
    ResourceKey<Biome> FROZEN_BEACH = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "frozen_beach"));

    ResourceKey<Biome> LUKEWARM_RIVER = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "lukewarm_river"));
    ResourceKey<Biome> COLD_RIVER = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cold_river"));

    ResourceKey<Biome> CAVES = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "caves"));
    ResourceKey<Biome> CRYSTAL_CAVERNS = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "crystal_caverns"));
    ResourceKey<Biome> ARID_CAVES = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "arid_caves"));
    ResourceKey<Biome> VOLCANIC_DEPTHS = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "volcanic_depths"));


    private static Biome createOutback(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.desertSpawns(spawnBuilder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addDefaultSprings(genBuilder);
        BiomeDefaultFeatures.addSavannaGrass(genBuilder);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(genBuilder);
        BiomeDefaultFeatures.addDesertVegetation(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.OUTBACK_ROCKS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.OUTBACK_ACACIA);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_SHORT_CACTUS);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0xD6A44D)
                        .waterColor(0x43D5EE)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041F33))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createLushDesert(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.desertSpawns(spawnBuilder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_BUSH);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_BADLANDS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DRY_GRASS_BADLANDS);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DRY_GRASS_DESERT);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        BiomeDefaultFeatures.addDesertExtraDecoration(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.LUSH_DESERT_ROCKS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.LUSH_SHORT_CACTUS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.LUSH_TALL_CACTUS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.AZALEA_SHRUB);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.LUSH_DIRT_PATCH);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DENSE_DRY_GRASS);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x43D5EE)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041F33))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createTundra(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        spawnBuilder.addSpawn(EntityTypes.CREEPER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 95, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.STRAY, 80, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 20, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
        spawnBuilder.addSpawn(EntityTypes.WITCH, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_HORSE, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 5, UniformInt.of(1, 1));

        spawnBuilder.addSpawn(EntityTypes.RABBIT, 8, UniformInt.of(2, 3));
        spawnBuilder.addSpawn(EntityTypes.GOAT, 4, UniformInt.of(1, 3));
        spawnBuilder.addSpawn(EntityTypes.FOX, 2, UniformInt.of(2, 4));
        spawnBuilder.addSpawn(EntityTypes.POLAR_BEAR, 1, UniformInt.of(1, 2));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addFrozenSprings(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_WINDSWEPT_HILLS);
        BiomeDefaultFeatures.addDefaultGrass(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_RED_SHRUB);
        BiomeDefaultFeatures.addNearWaterVegetation(genBuilder);
        BiomeDefaultFeatures.addRareBerryBushes(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TUNDRA_VEGETATION_PATCH);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DRY_GRASS_CLUMP);
        genBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.TUNDRA_ROCK);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.0F)
                .downfall(0.45F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0xa39658)
                        .waterColor(0x3D57D6)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SNOWY_SLOPES))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x050533))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createBog(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        spawnBuilder.addSpawn(EntityTypes.CREEPER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 95, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.BOGGED, 70, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 30, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
        spawnBuilder.addSpawn(EntityTypes.WITCH, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 5, UniformInt.of(1, 1));

        spawnBuilder.addSpawn(EntityTypes.RABBIT, 8, UniformInt.of(2, 3));
        spawnBuilder.addSpawn(EntityTypes.GOAT, 4, UniformInt.of(1, 3));
        spawnBuilder.addSpawn(EntityTypes.POLAR_BEAR, 1, UniformInt.of(1, 2));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addFrozenSprings(genBuilder);
        BiomeDefaultFeatures.addWaterTrees(genBuilder);
        BiomeDefaultFeatures.addNearWaterVegetation(genBuilder);
        BiomeDefaultFeatures.addRareBerryBushes(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_CATTAILS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SWAMP_GRASS);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.1F)
                .downfall(0.7F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3D57D6)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SWAMP))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x050533))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createCherryFields(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(EntityTypes.RABBIT, 10, UniformInt.of(2, 3));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_CHERRY);
        BiomeDefaultFeatures.addNearWaterVegetation(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.MEADOW_ROCK);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.SPARSE_CHERRY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.8F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0xb6db61)
                        .foliageColorOverride(0xb6db61)
                        .waterColor(0x3D57D6)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x050533))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createShrubland(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(EntityTypes.HORSE, 3, UniformInt.of(2, 6));
        spawnBuilder.addSpawn(EntityTypes.ARMADILLO, 8, UniformInt.of(2, 4));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addDefaultSprings(genBuilder);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DESERT_SHORT_CACTUS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.OAK_SHRUBS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CEDAR_TREES_SPARSE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(1.8F)
                .downfall(0.15F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x45ADF2)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041633))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createSteppe(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(EntityTypes.HORSE, 3, UniformInt.of(2, 6));
        spawnBuilder.addSpawn(EntityTypes.ARMADILLO, 8, UniformInt.of(2, 4));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addDefaultSprings(genBuilder);
        BiomeDefaultFeatures.addSavannaGrass(genBuilder);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.STEPPE_GRASS);
        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.STEPPE_PILLARS);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(2.0F)
                .downfall(0.05F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0xD6A44D)
                        .foliageColorOverride(0xa38b41)
                        .waterColor(0x45ADF2)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041633))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createCedarForest(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(EntityTypes.ARMADILLO, 6, UniformInt.of(1, 2));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addDefaultSprings(genBuilder);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);
        BiomeDefaultFeatures.addForestGrass(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CEDAR_FOREST_TREES);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(1.8F)
                .downfall(0.25F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x45ADF2)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041633))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createCloudForest(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(EntityTypes.CREEPER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.HUSK, 75, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 20, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.BOGGED, 80, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 20, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
        spawnBuilder.addSpawn(EntityTypes.WITCH, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_HORSE, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 5, UniformInt.of(1, 1));

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_BUSH);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_JUNGLE);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_MELON);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.JUNGLE_SHRUBS);
        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.WINDSWEPT_MAHOGANY);
        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.DESERT_DIRT_PATCH);
        genBuilder.addFeature(GenerationStep.Decoration.RAW_GENERATION, EPlacements.MOSSY_MUD_PATCH);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.6F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x43D5EE)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041F33))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 128F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createTidepools(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(EntityTypes.TURTLE, 1, UniformInt.of(2, 5));
        spawnBuilder.addSpawn(EntityTypes.FROG, 1, UniformInt.of(2, 3));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.BEACHSTONE_ROCKS);
        genBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.EXTRA_BEACHSTONE_ROCKS);
        genBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EPlacements.ROCKWEED_BASALT_PATCH);

        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.DENSE_DRY_GRASS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.TIDEPOOL);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(1.0F)
                .downfall(0.6F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x537C94)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041F33))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createColdBeach(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(EntityTypes.TURTLE, 1, UniformInt.of(2, 5));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.6F)
                .downfall(0.6F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3D57D6)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x050533))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createFrozenBeach(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(EntityTypes.CREEPER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
//        spawnBuilder.addSpawn(EEntities.FROSTBITE, 75, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 20, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.STRAY, 80, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 20, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.WITCH, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.TURTLE, 1, UniformInt.of(2, 5));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.1F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3938C9)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FROZEN_PEAKS))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x050533))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createLukewarmRiver(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        spawnBuilder.addSpawn(EntityTypes.CREEPER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.DROWNED, 100, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 95, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
        spawnBuilder.addSpawn(EntityTypes.WITCH, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 5, UniformInt.of(1, 1));

        spawnBuilder.addSpawn(EntityTypes.SQUID, 1, UniformInt.of(1, 4));
        spawnBuilder.addSpawn(EEntities.PERCH, 1, UniformInt.of(3, 5));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_SAND);
        genBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(1.5F)
                .downfall(0.7F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x45ADF2)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041633))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createColdRiver(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        spawnBuilder.addSpawn(EntityTypes.CREEPER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.DROWNED, 100, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 95, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
        spawnBuilder.addSpawn(EntityTypes.WITCH, 5, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 5, UniformInt.of(1, 1));

        spawnBuilder.addSpawn(EntityTypes.SQUID, 1, UniformInt.of(1, 4));
        spawnBuilder.addSpawn(EntityTypes.SALMON, 1, UniformInt.of(1, 5));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        genBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.6F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3D57D6)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x050533))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createDefaultCaves(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        createDefaultCaveSpawns(spawnBuilder);
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);
        BiomeDefaultFeatures.addInfestedStone(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CAVE_WEEDS);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CAVE_ROOTS);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0x323342))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 512F)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createCrystalCaverns(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        createCrystalCavernsSpawns(spawnBuilder);
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, false, false);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        genBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.AMETHYST_NODE);
        genBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, EPlacements.AMETHYST_SPIRE_CLUSTER);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EPlacements.CAVE_WEEDS);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.0F)
                .downfall(1.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x6B38C9)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(ESounds.MUSIC_BIOME_CRYSTAL_CAVERNS))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0x48344d))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 512F)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x140533))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createAridCaves(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        createHotCaveSpawns(spawnBuilder);
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, false, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x43D5EE)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0xFFE991))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041F33))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 256F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    private static Biome createVolcanicDepths(BootstrapContext<Biome> context) {
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings
                .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        createHotCaveSpawns(spawnBuilder);
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, false);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(3.0F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x43D5EE)
                        .build())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0x422619))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(0x041F33))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 256F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }



    static void createDefaultCaveSpawns(MobSpawnSettings.Builder spawnBuilder) {
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 95, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.CREEPER, 60, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.CAVE_SPIDER, 20, UniformInt.of(2, 4));

        spawnBuilder.addSpawn(EntityTypes.WITCH, 25, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
    }

    static void createHotCaveSpawns(MobSpawnSettings.Builder spawnBuilder) {
        spawnBuilder.addSpawn(EntityTypes.SLIME, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.PARCHED, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.HUSK, 95, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.CREEPER, 60, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.CAVE_SPIDER, 20, UniformInt.of(2, 4));

        spawnBuilder.addSpawn(EntityTypes.WITCH, 25, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
    }

    static void createCrystalCavernsSpawns(MobSpawnSettings.Builder spawnBuilder) {
        spawnBuilder.addSpawn(EEntities.SUBSLIME, 100, UniformInt.of(2, 2));
        spawnBuilder.addSpawn(EntityTypes.SPIDER, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.SKELETON, 100, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.ZOMBIE, 95, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.CREEPER, 60, UniformInt.of(4, 4));
        spawnBuilder.addSpawn(EntityTypes.CAVE_SPIDER, 20, UniformInt.of(2, 4));

        spawnBuilder.addSpawn(EntityTypes.WITCH, 25, UniformInt.of(1, 1));
        spawnBuilder.addSpawn(EntityTypes.ENDERMAN, 10, UniformInt.of(1, 4));
    }

    // Global features --- Don't use this, this is just for reference... -w-"
     static void globalOverworldGeneration(BiomeGenerationSettings.Builder genBuilder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);

        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDripstone(genBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(genBuilder);
        BiomeDefaultFeatures.addIcebergs(genBuilder);

        BiomeDefaultFeatures.addFossilDecoration(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);

        BiomeDefaultFeatures.addDesertExtraDecoration(genBuilder);
        BiomeDefaultFeatures.addBlueIce(genBuilder);

        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder, true, true);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addLushCavesSpecialOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addExtraGold(genBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(genBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(genBuilder);
        BiomeDefaultFeatures.addMangroveSwampDisks(genBuilder);

        BiomeDefaultFeatures.addDripstone(genBuilder);
        BiomeDefaultFeatures.addSculk(genBuilder);
        BiomeDefaultFeatures.addInfestedStone(genBuilder);
        BiomeDefaultFeatures.addNetherDefaultOres(genBuilder);
        BiomeDefaultFeatures.addAncientDebris(genBuilder);

        BiomeDefaultFeatures.addDefaultSprings(genBuilder);
        BiomeDefaultFeatures.addFrozenSprings(genBuilder);

        BiomeDefaultFeatures.addTaigaTrees(genBuilder);
        BiomeDefaultFeatures.addGroveTrees(genBuilder);
        BiomeDefaultFeatures.addWaterTrees(genBuilder);
        BiomeDefaultFeatures.addBirchTrees(genBuilder);
        BiomeDefaultFeatures.addOtherBirchTrees(genBuilder);
        BiomeDefaultFeatures.addTallBirchTrees(genBuilder);
        BiomeDefaultFeatures.addSavannaTrees(genBuilder);
        BiomeDefaultFeatures.addShatteredSavannaTrees(genBuilder);
        BiomeDefaultFeatures.addMountainTrees(genBuilder);
        BiomeDefaultFeatures.addMountainForestTrees(genBuilder);
        BiomeDefaultFeatures.addJungleTrees(genBuilder);
        BiomeDefaultFeatures.addSparseJungleTrees(genBuilder);
        BiomeDefaultFeatures.addBadlandsTrees(genBuilder);
        BiomeDefaultFeatures.addSnowyTrees(genBuilder);
        BiomeDefaultFeatures.addPlainVegetation(genBuilder);
        BiomeDefaultFeatures.addSwampVegetation(genBuilder);
        BiomeDefaultFeatures.addMangroveSwampVegetation(genBuilder);
        BiomeDefaultFeatures.addCherryGroveVegetation(genBuilder);
        BiomeDefaultFeatures.addMeadowVegetation(genBuilder);

        BiomeDefaultFeatures.addBushes(genBuilder);
        BiomeDefaultFeatures.addFerns(genBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(genBuilder);
        BiomeDefaultFeatures.addBambooVegetation(genBuilder);
        BiomeDefaultFeatures.addLushCavesVegetationFeatures(genBuilder);
        BiomeDefaultFeatures.addLeafLitterPatch(genBuilder);
        BiomeDefaultFeatures.addJungleVines(genBuilder);
        BiomeDefaultFeatures.addRareBerryBushes(genBuilder);
        BiomeDefaultFeatures.addCommonBerryBushes(genBuilder);

        BiomeDefaultFeatures.addJungleGrass(genBuilder);
        BiomeDefaultFeatures.addSavannaGrass(genBuilder);
        BiomeDefaultFeatures.addShatteredSavannaGrass(genBuilder);
        BiomeDefaultFeatures.addBadlandGrass(genBuilder);
        BiomeDefaultFeatures.addForestGrass(genBuilder);
        BiomeDefaultFeatures.addDesertVegetation(genBuilder);
        BiomeDefaultFeatures.addGiantTaigaVegetation(genBuilder);
        BiomeDefaultFeatures.addDefaultGrass(genBuilder);
        BiomeDefaultFeatures.addTaigaGrass(genBuilder);
        BiomeDefaultFeatures.addPlainGrass(genBuilder);

        BiomeDefaultFeatures.addForestFlowers(genBuilder);
        BiomeDefaultFeatures.addBirchForestFlowers(genBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(genBuilder);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);

        BiomeDefaultFeatures.addSavannaExtraGrass(genBuilder);

        BiomeDefaultFeatures.addMushroomFieldVegetation(genBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(genBuilder);

        BiomeDefaultFeatures.addColdOceanExtraVegetation(genBuilder);
        BiomeDefaultFeatures.addLukeWarmKelp(genBuilder);
        BiomeDefaultFeatures.addJungleMelons(genBuilder);
        BiomeDefaultFeatures.addSparseJungleMelons(genBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genBuilder, true);
        BiomeDefaultFeatures.addNearWaterVegetation(genBuilder);
        BiomeDefaultFeatures.addBadlandExtraVegetation(genBuilder);
        BiomeDefaultFeatures.addDesertExtraVegetation(genBuilder);
        BiomeDefaultFeatures.addSwampExtraVegetation(genBuilder);
        BiomeDefaultFeatures.addMangroveSwampExtraVegetation(genBuilder);

        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);
    }

    private static Vector3f rgb(int color) {
        return new Vector3f(
                ((color >> 16) & 0xFF) / 255.0F,
                ((color >> 8) & 0xFF) / 255.0F,
                (color & 0xFF) / 255.0F
        );
    }
}