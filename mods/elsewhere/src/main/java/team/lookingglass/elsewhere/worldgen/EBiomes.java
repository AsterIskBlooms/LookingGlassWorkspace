package team.lookingglass.elsewhere.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.joml.Vector3f;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.EEntities;
import team.lookingglass.elsewhere.registry.sound.ESounds;
import team.lookingglass.elsewhere.worldgen.features.EPlacements;

public interface EBiomes {
    // Order: snowy, cold, temperate, warm, hot, swamp | beaches | rivers | caves
    ResourceKey<Biome> GLACIAL_VALLEY = create("glacial_valley");
    ResourceKey<Biome> SNOWY_TUNDRA = create("snowy_tundra");

    ResourceKey<Biome> TUNDRA = create("tundra");
    ResourceKey<Biome> STEPPE = create("steppe");
    ResourceKey<Biome> PINE_BARRENS = create("pine_barrens");
    ResourceKey<Biome> RAINFOREST = create("rainforest");

    ResourceKey<Biome> CHERRY_FIELDS = create("cherry_fields");
    ResourceKey<Biome> OLD_GROWTH_FOREST = create("old_growth_forest");
    ResourceKey<Biome> OAK_WOODLAND = create("oak_woodland");

    ResourceKey<Biome> SHRUBLAND = create("shrubland");
    ResourceKey<Biome> CEDAR_FOREST = create("cedar_forest");
    ResourceKey<Biome> ROCKY_JUNGLE = create("rocky_jungle");
    ResourceKey<Biome> TROPICAL_BASIN = create("tropical_basin");

    ResourceKey<Biome> ACACIA_FLATS = create("acacia_flats");
    ResourceKey<Biome> OUTBACK = create("outback");
    ResourceKey<Biome> DUNES = create("dunes");
    ResourceKey<Biome> LUSH_DUNES = create("lush_dunes");
    ResourceKey<Biome> CLOUD_FOREST = create("cloud_forest");

    ResourceKey<Biome> BOG = create("bog");
    ResourceKey<Biome> MARSH = create("marsh");

    ResourceKey<Biome> FROZEN_BEACH = create("frozen_beach");
    ResourceKey<Biome> COLD_BEACH = create("cold_beach");
    ResourceKey<Biome> TIDEPOOLS = create("tidepools");

    ResourceKey<Biome> COLD_RIVER = create("cold_river");
    ResourceKey<Biome> WARM_RIVER = create("warm_river");

    ResourceKey<Biome> CAVES = create("caves");
    ResourceKey<Biome> CRYSTAL_CAVERNS = create("crystal_caverns");
    ResourceKey<Biome> ARID_CAVES = create("arid_caves");
    ResourceKey<Biome> VOLCANIC_DEPTHS = create("volcanic_depths");

    static void bootstrap(BootstrapContext<Biome> context) {
        context.register(GLACIAL_VALLEY, createGlacialValley(context));
        context.register(SNOWY_TUNDRA, createSnowyTundra(context));

        context.register(TUNDRA, createTundra(context));
        context.register(STEPPE, createSteppe(context));
        context.register(PINE_BARRENS, createPineBarrens(context));
        context.register(RAINFOREST, createRainforest(context));

        context.register(CHERRY_FIELDS, createCherryFields(context));
        context.register(OLD_GROWTH_FOREST, createOldGrowthForest(context));
        context.register(OAK_WOODLAND, createOakWoodland(context));

        context.register(SHRUBLAND, createShrubland(context));
        context.register(CEDAR_FOREST, createCedarForest(context));
        context.register(ROCKY_JUNGLE, createRockyJungle(context));
        context.register(TROPICAL_BASIN, createTropicalBasin(context));

        context.register(ACACIA_FLATS, createAcaciaFlats(context));
        context.register(OUTBACK, createOutback(context));
        context.register(DUNES, createDunes(context));
        context.register(LUSH_DUNES, createLushDunes(context));
        context.register(CLOUD_FOREST, createCloudForest(context));

        context.register(BOG, createBog(context));
        context.register(MARSH, createMarsh(context));

        context.register(FROZEN_BEACH, createFrozenBeach(context));
        context.register(COLD_BEACH, createColdBeach(context));
        context.register(TIDEPOOLS, createTidepools(context));

        context.register(COLD_RIVER, createColdRiver(context));
        context.register(WARM_RIVER, createWarmRiver(context));

        context.register(CAVES, createDefaultCaves(context));
        context.register(CRYSTAL_CAVERNS, createCrystalCaverns(context));
        context.register(ARID_CAVES, createAridCaves(context));
        context.register(VOLCANIC_DEPTHS, createVolcanicDepths(context));
    }

    // ------------------------------------------------------------------
    // Snowy
    // ------------------------------------------------------------------

    private static Biome createGlacialValley(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.snowySpawns(spawns, false);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addFrozenSprings(gen);
        BiomeDefaultFeatures.addSnowyTrees(gen);
        BiomeDefaultFeatures.addBlueIce(gen);

        return biome(-0.5F, 0.4F, Water.FROZEN, Music.FROZEN_PEAKS, spawns, gen).build();
    }

    private static Biome createSnowyTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 15, 20);
        spawns.addSpawn(EntityTypes.FROSTBITE, 80, 4, 4);
        spawns.addSpawn(EntityTypes.STRAY, 80, 4, 4);
        spawns.addSpawn(EntityTypes.ZOMBIE_HORSE, 5, 1, 1);
        spawns.addSpawn(EntityTypes.RABBIT, 8, 2, 3);
        spawns.addSpawn(EntityTypes.GOAT, 4, 1, 3);
        spawns.addSpawn(EntityTypes.FOX, 2, 2, 4);
        spawns.addSpawn(EntityTypes.POLAR_BEAR, 1, 1, 2);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addFrozenSprings(gen);
        BiomeDefaultFeatures.addSnowyTrees(gen);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);

        return biome(-0.2F, 0.35F, Water.FROZEN, Music.SNOWY_SLOPES, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Cold
    // ------------------------------------------------------------------

    private static Biome createTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 95, 20);
        spawns.addSpawn(EntityTypes.STRAY, 80, 4, 4);
        spawns.addSpawn(EntityTypes.ZOMBIE_HORSE, 5, 1, 1);
        spawns.addSpawn(EntityTypes.RABBIT, 8, 2, 3);
        spawns.addSpawn(EntityTypes.GOAT, 4, 1, 3);
        spawns.addSpawn(EntityTypes.FOX, 2, 2, 4);
        spawns.addSpawn(EntityTypes.POLAR_BEAR, 1, 1, 2);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addFrozenSprings(gen);
        veg(gen, EPlacements.TREES_TUNDRA, EPlacements.TUNDRA_RUSTY_MOSS_PATCH);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        veg(gen, VegetationPlacements.PATCH_RED_SHRUB);
        BiomeDefaultFeatures.addNearWaterVegetation(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);
        local(gen, EPlacements.DRY_GRASS_CLUMP_SPARSE, EPlacements.TUNDRA_ROCK);

        return biome(0.4F, 0.55F, Water.COLD.grass(0xa9b564), Music.SNOWY_SLOPES, spawns, gen).build();
    }

    private static Biome createSteppe(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.HORSE, 3, 2, 6);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addSavannaGrass(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        veg(gen, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        raw(gen, EPlacements.STEPPE_GRASS, EPlacements.STEPPE_PILLARS);

        return biome(0.4F, 0.35F, Water.COLD, Music.BADLANDS, spawns, gen).build();
    }

    private static Biome createPineBarrens(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.RABBIT, 8, 2, 3);
        spawns.addSpawn(EntityTypes.FOX, 4, 2, 4);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        veg(gen, EPlacements.TREES_PINE_BARRENS);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addTaigaGrass(gen);
        veg(gen, VegetationPlacements.PATCH_DEAD_BUSH);
        BiomeDefaultFeatures.addRareBerryBushes(gen);

        return biome(0.3F, 0.25F, Water.COLD, Music.FOREST, spawns, gen).build();
    }

    private static Biome createRainforest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.FOX, 4, 2, 4);
        spawns.addSpawn(EntityTypes.FROG, 5, 2, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addMossyStoneBlock(gen);
        veg(gen, EPlacements.TREES_RAINFOREST);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addGiantTaigaVegetation(gen);
        BiomeDefaultFeatures.addLeafLitterPatch(gen);

        return biome(0.5F, 1.0F, Water.COLD, Music.FOREST, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Temperate
    // ------------------------------------------------------------------

    private static Biome createCherryFields(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        BiomeDefaultFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityTypes.RABBIT, 10, 2, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        veg(gen, VegetationPlacements.PATCH_GRASS_PLAIN, VegetationPlacements.FLOWER_CHERRY);
        BiomeDefaultFeatures.addNearWaterVegetation(gen);
        local(gen, EPlacements.MEADOW_ROCK);
        veg(gen, EPlacements.SPARSE_CHERRY);

        return biome(0.5F, 0.8F, Water.COLD.grass(0xb6db61).foliage(0xb6db61), Music.CHERRY_GROVE, spawns, gen)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .build();
    }

    private static Biome createOldGrowthForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        BiomeDefaultFeatures.farmAnimals(spawns);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addMossyStoneBlock(gen);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        veg(gen, EPlacements.OLD_GROWTH_FLOWERS);
        BiomeDefaultFeatures.addBirchForestFlowers(gen);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        veg(gen, EPlacements.TREES_OLD_GROWTH_FOREST);
        BiomeDefaultFeatures.addTaigaGrass(gen);
        BiomeDefaultFeatures.addSavannaGrass(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        dryProduce(gen);
        raw(gen, EPlacements.FOREST_POND);

        return biome(1.2F, 0.25F, new Water(0x3b6cd1, 0x3b6cd1), Music.FOREST, spawns, gen).build();
    }

    private static Biome createOakWoodland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        BiomeDefaultFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityTypes.FOX, 4, 2, 4);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addForestFlowers(gen);
        veg(gen, EPlacements.TREES_OAK_WOODLAND);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addForestGrass(gen);
        BiomeDefaultFeatures.addDefaultMushrooms(gen);
        BiomeDefaultFeatures.addLeafLitterPatch(gen);

        return biome(0.9F, 0.35F, Water.TEMPERATE, Music.FOREST, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Warm
    // ------------------------------------------------------------------

    private static Biome createShrubland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.HORSE, 3, 2, 6);
        spawns.addSpawn(EntityTypes.ARMADILLO, 8, 2, 4);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        dryProduce(gen);
        veg(gen, EPlacements.DESERT_SHORT_CACTUS, EPlacements.OAK_SHRUBS, EPlacements.CEDAR_TREES_SPARSE);

        return biome(1.8F, 0.15F, Water.WARM, null, spawns, gen).build();
    }

    private static Biome createCedarForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        BiomeDefaultFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityTypes.ARMADILLO, 6, 1, 2);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        veg(gen, EPlacements.CEDAR_FOREST_TREES);
        BiomeDefaultFeatures.addForestGrass(gen);
        dryProduce(gen);

        return biome(1.8F, 0.25F, Water.WARM, Music.FOREST, spawns, gen).build();
    }

    private static Biome createRockyJungle(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(spawns);
        spawns.addSpawn(EntityTypes.OCELOT, 2, 1, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addMossyStoneBlock(gen);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addJungleGrass(gen);
        BiomeDefaultFeatures.addJungleVines(gen);
        BiomeDefaultFeatures.addSparseJungleMelons(gen);
        veg(gen, EPlacements.TREES_ROCKY_JUNGLE);

        return biome(1.3F, 0.7F, Water.WARM, Music.JUNGLE, spawns, gen).build();
    }

    private static Biome createTropicalBasin(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(spawns);
        spawns.addSpawn(EntityTypes.PARROT, 20, 1, 2);
        spawns.addSpawn(EntityTypes.FROG, 10, 2, 5);

        BiomeGenerationSettings.Builder gen = baseGen(context, false);
        BiomeDefaultFeatures.addMangroveSwampDisks(gen);
        BiomeDefaultFeatures.addSparseJungleTrees(gen);
        BiomeDefaultFeatures.addJungleGrass(gen);
        BiomeDefaultFeatures.addLightBambooVegetation(gen);
        BiomeDefaultFeatures.addJungleVines(gen);
        BiomeDefaultFeatures.addSparseJungleMelons(gen);
        veg(gen, VegetationPlacements.PATCH_WATERLILY);
        BiomeDefaultFeatures.addNearWaterVegetation(gen);

        return biome(1.6F, 0.9F, Water.WARM, Music.JUNGLE, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Hot
    // ------------------------------------------------------------------

    private static Biome createAcaciaFlats(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawns);
        spawns.addSpawn(EntityTypes.ARMADILLO, 6, 1, 2);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addSavannaGrass(gen);
        BiomeDefaultFeatures.addSavannaTrees(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);

        return biome(2.0F, 0.08F, Water.HOT, Music.DESERT, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    private static Biome createOutback(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(spawns);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addSavannaGrass(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        BiomeDefaultFeatures.addDesertVegetation(gen);
        dryProduce(gen);
        raw(gen, EPlacements.OUTBACK_ROCKS);
        veg(gen, EPlacements.OUTBACK_ACACIA, EPlacements.DESERT_SHORT_CACTUS);

        return biome(2.0F, 0.0F, Water.HOT.grass(0xD6A44D), Music.DESERT, spawns, gen)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    private static Biome createDunes(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(spawns);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        veg(gen, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        BiomeDefaultFeatures.addDesertExtraDecoration(gen);
        veg(gen, EPlacements.DUNE_DRY_GRASS);

        return biome(2.0F, 0.0F, Water.HOT, Music.DESERT, spawns, gen)
                .hasPrecipitation(false)
                .build();
    }

    private static Biome createLushDunes(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(spawns);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addBushes(gen);
        veg(gen, VegetationPlacements.PATCH_GRASS_BADLANDS, VegetationPlacements.PATCH_DRY_GRASS_BADLANDS);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        veg(gen, VegetationPlacements.PATCH_DRY_GRASS_DESERT, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        BiomeDefaultFeatures.addDesertExtraDecoration(gen);
        raw(gen, EPlacements.LUSH_DESERT_ROCKS);
        veg(gen, EPlacements.LUSH_SHORT_CACTUS, EPlacements.LUSH_TALL_CACTUS, EPlacements.AZALEA_SHRUB,
                EPlacements.LUSH_DIRT_PATCH, EPlacements.DENSE_DRY_GRASS);

        return biome(2.0F, 0.4F, Water.HOT, Music.DESERT, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    private static Biome createCloudForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 20, 20);
        spawns.addSpawn(EntityTypes.HUSK, 75, 4, 4);
        spawns.addSpawn(EntityTypes.BOGGED, 80, 4, 4);
        spawns.addSpawn(EntityTypes.ZOMBIE_HORSE, 5, 1, 1);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        veg(gen, VegetationPlacements.PATCH_GRASS_JUNGLE, VegetationPlacements.PATCH_SUGAR_CANE, VegetationPlacements.PATCH_MELON);
        raw(gen, EPlacements.JUNGLE_SHRUBS, EPlacements.WINDSWEPT_MAHOGANY, EPlacements.DESERT_DIRT_PATCH, EPlacements.MOSSY_MUD_PATCH);

        return biome(2.0F, 0.6F, Water.HOT, Music.DESERT, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 128F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    // ------------------------------------------------------------------
    // Swamp
    // ------------------------------------------------------------------

    private static Biome createBog(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 25, 30);
        spawns.addSpawn(EntityTypes.FROSTBITE, 70, 4, 4);
        spawns.addSpawn(EntityTypes.BOGGED, 70, 4, 4);
        spawns.addSpawn(EntityTypes.RABBIT, 8, 2, 3);
        spawns.addSpawn(EntityTypes.FROG, 1, 2, 5);
        spawns.addSpawn(EntityTypes.FOX, 2, 2, 4);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addFrozenSprings(gen);
        veg(gen, EPlacements.TREES_BOG, VegetationPlacements.PATCH_GRASS_TAIGA, VegetationPlacements.PATCH_DEAD_BUSH,
                VegetationPlacements.PATCH_WATERLILY, VegetationPlacements.BROWN_MUSHROOM_SWAMP, VegetationPlacements.RED_MUSHROOM_SWAMP);
        BiomeDefaultFeatures.addNearWaterVegetation(gen);
        BiomeDefaultFeatures.addCommonBerryBushes(gen);

        return biome(0.0F, 0.85F, Water.COLD, Music.SWAMP, spawns, gen).build();
    }

    private static Biome createMarsh(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.swampSpawns(spawns, 30);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addSwampVegetation(gen);
        BiomeDefaultFeatures.addSwampExtraVegetation(gen);

        return biome(0.8F, 0.9F, new Water(0x3E8F8A, 0x041F33), Music.SWAMP, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Beaches
    // ------------------------------------------------------------------

    private static Biome createFrozenBeach(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 20, 20);
        spawns.addSpawn(EntityTypes.STRAY, 80, 4, 4);
        spawns.addSpawn(EntityTypes.TURTLE, 1, 2, 5);
        // spawns.addSpawn(EEntities.FROSTBITE, 75, 4, 4);

        return biome(0.1F, 0.0F, Water.FROZEN, Music.FROZEN_PEAKS, spawns, baseGen(context)).build();
    }

    private static Biome createColdBeach(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.TURTLE, 1, 2, 5);

        return biome(0.6F, 0.6F, Water.COLD, null, spawns, baseGen(context))
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .build();
    }

    private static Biome createTidepools(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.TURTLE, 1, 2, 5);
        spawns.addSpawn(EntityTypes.FROG, 1, 2, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        local(gen, EPlacements.BEACHSTONE_ROCKS, EPlacements.EXTRA_BEACHSTONE_ROCKS, EPlacements.ROCKWEED_BASALT_PATCH);
        veg(gen, EPlacements.DENSE_DRY_GRASS, EPlacements.TIDEPOOL);

        return biome(1.0F, 0.6F, new Water(0x537C94, 0x041F33), null, spawns, gen)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    // ------------------------------------------------------------------
    // Rivers
    // ------------------------------------------------------------------

    private static Biome createColdRiver(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 95, 100);
        spawns.addSpawn(EntityTypes.DROWNED, 100, 1, 1);
        spawns.addSpawn(EntityTypes.SQUID, 1, 1, 4);
        spawns.addSpawn(EntityTypes.SALMON, 1, 1, 5);

        BiomeGenerationSettings.Builder gen = baseGen(context, false);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        underground(gen, MiscOverworldPlacements.DISK_CLAY, MiscOverworldPlacements.DISK_GRAVEL);
        mudDisks(gen, true);

        return biome(0.5F, 0.6F, Water.COLD, Music.FOREST, spawns, gen)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .build();
    }

    private static Biome createWarmRiver(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 95, 100);
        spawns.addSpawn(EntityTypes.DROWNED, 100, 1, 1);
        spawns.addSpawn(EntityTypes.SQUID, 1, 1, 4);
        spawns.addSpawn(EEntities.PERCH, 1, 3, 5);

        BiomeGenerationSettings.Builder gen = baseGen(context, false);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        underground(gen, MiscOverworldPlacements.DISK_SAND, MiscOverworldPlacements.DISK_CLAY);
        mudDisks(gen, false);

        return biome(1.5F, 0.7F, Water.WARM, Music.JUNGLE, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Caves
    // ------------------------------------------------------------------

    private static Biome createDefaultCaves(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        caveMobs(spawns);
        spawns.addSpawn(EntityTypes.SLIME, 100, 4, 4);
        spawns.addSpawn(EntityTypes.SKELETON, 100, 4, 4);
        spawns.addSpawn(EntityTypes.ZOMBIE, 95, 4, 4);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addInfestedStone(gen);
        veg(gen, EPlacements.CAVE_WEEDS, EPlacements.CAVE_ROOTS);

        return biome(0.8F, 0.4F, Water.TEMPERATE, Music.DRIPSTONE_CAVES, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0x323342))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 512F)
                .build();
    }

    private static Biome createCrystalCaverns(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        caveMobs(spawns);
        spawns.addSpawn(EEntities.SUBSLIME, 100, 2, 2);
        spawns.addSpawn(EntityTypes.SKELETON, 100, 4, 4);
        spawns.addSpawn(EntityTypes.ZOMBIE, 95, 4, 4);

        // Custom underground mix instead of the default variety
        BiomeGenerationSettings.Builder gen = gen(context);
        caveBase(gen);
        underground(gen, OrePlacements.ORE_GRAVEL, OrePlacements.ORE_ANDESITE_UPPER, OrePlacements.ORE_ANDESITE_LOWER, OrePlacements.ORE_TUFF);
        veg(gen, CavePlacements.GLOW_LICHEN);
        BiomeDefaultFeatures.addDefaultOres(gen);
        BiomeDefaultFeatures.addDefaultSoftDisks(gen);
        BiomeDefaultFeatures.addExtraEmeralds(gen);
        BiomeDefaultFeatures.addSurfaceFreezing(gen);
        decoration(gen, EPlacements.AMETHYST_NODE, EPlacements.AMETHYST_SPIRE_CLUSTER);
        veg(gen, EPlacements.CAVE_WEEDS);

        return biome(1.0F, 1.0F, new Water(0x6B38C9, 0x140533),
                new BackgroundMusic(ESounds.MUSIC_BIOME_CRYSTAL_CAVERNS), spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0x48344d))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 512F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .build();
    }

    private static Biome createAridCaves(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hotCaveMobs(spawns);

        BiomeGenerationSettings.Builder gen = gen(context);
        caveBase(gen);
        underground(gen, OrePlacements.ORE_GRAVEL, OrePlacements.ORE_GRANITE_UPPER, OrePlacements.ORE_GRANITE_LOWER, OrePlacements.ORE_TUFF);
        veg(gen, CavePlacements.GLOW_LICHEN);
        BiomeDefaultFeatures.addDefaultOres(gen);
        BiomeDefaultFeatures.addExtraGold(gen);
        BiomeDefaultFeatures.addDefaultSoftDisks(gen);
        BiomeDefaultFeatures.addSurfaceFreezing(gen);

        return biome(2.0F, 0.0F, Water.HOT, Music.DESERT, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0xFFE991))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 256F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    private static Biome createVolcanicDepths(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hotCaveMobs(spawns);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);

        return biome(3.0F, 0.0F, Water.HOT, Music.BADLANDS, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(0x422619))
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 256F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    // ==================================================================
    //  SHORTHANDS
    // ==================================================================

    // ---- Biome builder -----------------------------------------------

    /**
     * Returns an un-built BiomeBuilder with the stuff every biome needs.
     * Precipitation defaults to true; chain extras (.hasPrecipitation(false), .setAttribute(...)) then .build().
     * Pass null for music to leave the biome with no background music override.
     */
    private static Biome.BiomeBuilder biome(float temperature, float downfall, Water water, BackgroundMusic music,
                                            MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder gen) {
        BiomeSpecialEffects.Builder fx = new BiomeSpecialEffects.Builder().waterColor(water.color());
        if (water.grass() != null) fx.grassColorOverride(water.grass());
        if (water.foliage() != null) fx.foliageColorOverride(water.foliage());

        Biome.BiomeBuilder builder = new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(fx.build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, rgb(water.fog()))
                .mobSpawnSettings(spawns.build())
                .generationSettings(gen.build());

        if (music != null) {
            builder.setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, music);
        }
        return builder;
    }

    /** Water colour + water fog colour (+ optional grass/foliage override). */
    record Water(int color, int fog, Integer grass, Integer foliage) {
        public static final Water FROZEN = new Water(0x3938C9, 0x050533);
        public static final Water COLD = new Water(0x3D57D6, 0x050533);
        public static final Water TEMPERATE = new Water(0x3F76E4, 0x050533);
        public static final Water WARM = new Water(0x45ADF2, 0x041633);  // lukewarm ocean/river
        public static final Water HOT = new Water(0x43D5EE, 0x041F33);   // warm ocean

        public Water(int color, int fog) {
            this(color, fog, null, null);
        }

        public Water grass(int grass) {
            return new Water(color, fog, grass, foliage);
        }

        public Water foliage(int foliage) {
            return new Water(color, fog, grass, foliage);
        }
    }

    /** Lazy holder so sound events are only touched when a biome actually uses them. */
    final class Music {
        public static final BackgroundMusic DESERT = new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT);
        public static final BackgroundMusic SNOWY_SLOPES = new BackgroundMusic(SoundEvents.MUSIC_BIOME_SNOWY_SLOPES);
        public static final BackgroundMusic FROZEN_PEAKS = new BackgroundMusic(SoundEvents.MUSIC_BIOME_FROZEN_PEAKS);
        public static final BackgroundMusic SWAMP = new BackgroundMusic(SoundEvents.MUSIC_BIOME_SWAMP);
        public static final BackgroundMusic CHERRY_GROVE = new BackgroundMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE);
        public static final BackgroundMusic BADLANDS = new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS);
        public static final BackgroundMusic FOREST = new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST);
        public static final BackgroundMusic JUNGLE = new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE);
        public static final BackgroundMusic DRIPSTONE_CAVES = new BackgroundMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES);

        private Music() {
        }
    }

    // ---- Generation --------------------------------------------------

    private static BiomeGenerationSettings.Builder gen(BootstrapContext<Biome> context) {
        return new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));
    }

    /** Carvers/lakes, geodes, underground variety, ores, soft disks, surface freezing. In nearly every biome. */
    private static BiomeGenerationSettings.Builder baseGen(BootstrapContext<Biome> context) {
        return baseGen(context, true, false);
    }

    /** Same as baseGen, plus extra gold between the ores and the soft disks (vanilla badlands/savanna order). */
    private static BiomeGenerationSettings.Builder goldGen(BootstrapContext<Biome> context) {
        return baseGen(context, true, true);
    }

    private static BiomeGenerationSettings.Builder baseGen(BootstrapContext<Biome> context, boolean softDisks) {
        return baseGen(context, softDisks, false);
    }

    private static BiomeGenerationSettings.Builder baseGen(BootstrapContext<Biome> context, boolean softDisks, boolean extraGold) {
        BiomeGenerationSettings.Builder gen = gen(context);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(gen);
        BiomeDefaultFeatures.addDefaultCrystalFormations(gen);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(gen);
        BiomeDefaultFeatures.addDefaultOres(gen);
        if (extraGold) BiomeDefaultFeatures.addExtraGold(gen);
        if (softDisks) BiomeDefaultFeatures.addDefaultSoftDisks(gen);
        BiomeDefaultFeatures.addSurfaceFreezing(gen);
        return gen;
    }

    /** Carvers, geodes, monster rooms: the start of every cave biome that builds its own underground mix. */
    private static void caveBase(BiomeGenerationSettings.Builder gen) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(gen);
        BiomeDefaultFeatures.addDefaultCrystalFormations(gen);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
    }

    /** Sugar cane (desert) + pumpkins: the dry-land produce combo used by several biomes. */
    private static void dryProduce(BiomeGenerationSettings.Builder gen) {
        veg(gen, VegetationPlacements.PATCH_SUGAR_CANE_DESERT, VegetationPlacements.PATCH_PUMPKIN);
    }

    static void mudDisks(BiomeGenerationSettings.Builder gen, boolean generateGravel) {
        if (generateGravel) {
            underground(gen, EPlacements.MUD_DISK_GRAVEL);
        }
        underground(gen, EPlacements.MUD_DISK_CLAY, EPlacements.MUD_DISK_SAND);
    }

    // Per-step feature adders. Pick the step by name instead of typing GenerationStep.Decoration.X every time.
    @SafeVarargs
    private static void veg(BiomeGenerationSettings.Builder gen, ResourceKey<PlacedFeature>... features) {
        add(gen, GenerationStep.Decoration.VEGETAL_DECORATION, features);
    }

    @SafeVarargs
    private static void raw(BiomeGenerationSettings.Builder gen, ResourceKey<PlacedFeature>... features) {
        add(gen, GenerationStep.Decoration.RAW_GENERATION, features);
    }

    @SafeVarargs
    private static void local(BiomeGenerationSettings.Builder gen, ResourceKey<PlacedFeature>... features) {
        add(gen, GenerationStep.Decoration.LOCAL_MODIFICATIONS, features);
    }

    @SafeVarargs
    private static void underground(BiomeGenerationSettings.Builder gen, ResourceKey<PlacedFeature>... features) {
        add(gen, GenerationStep.Decoration.UNDERGROUND_ORES, features);
    }

    @SafeVarargs
    private static void decoration(BiomeGenerationSettings.Builder gen, ResourceKey<PlacedFeature>... features) {
        add(gen, GenerationStep.Decoration.UNDERGROUND_DECORATION, features);
    }

    @SafeVarargs
    private static void add(BiomeGenerationSettings.Builder gen, GenerationStep.Decoration step, ResourceKey<PlacedFeature>... features) {
        for (ResourceKey<PlacedFeature> feature : features) {
            gen.addFeature(step, feature);
        }
    }

    // ---- Spawns ------------------------------------------------------

    /**
     * The standard surface monster set (creeper/slime/spider/zombie/zombie villager/skeleton/enderman/witch)
     * plus bats and glow squid. Add variants (stray, husk, bogged...) after calling this.
     */
    private static void hostiles(MobSpawnSettings.Builder spawns, int zombieWeight, int skeletonWeight) {
        BiomeDefaultFeatures.caveSpawns(spawns);
        BiomeDefaultFeatures.monsters(spawns, zombieWeight, 5, 0, skeletonWeight, false);
    }

    /** Shared by every cave biome; add the slime/skeleton/zombie (or hot/crystal) variants after. */
    private static void caveMobs(MobSpawnSettings.Builder spawns) {
        spawns.addSpawn(EntityTypes.SPIDER, 100, 4, 4);
        spawns.addSpawn(EntityTypes.CREEPER, 60, 4, 4);
        spawns.addSpawn(EntityTypes.CAVE_SPIDER, 20, 2, 4);
        spawns.addSpawn(EntityTypes.WITCH, 25, 1, 1);
        spawns.addSpawn(EntityTypes.ENDERMAN, 10, 1, 4);
        BiomeDefaultFeatures.caveSpawns(spawns);
    }

    private static void hotCaveMobs(MobSpawnSettings.Builder spawns) {
        caveMobs(spawns);
        spawns.addSpawn(EntityTypes.SLIME, 100, 4, 4);
        spawns.addSpawn(EntityTypes.PARCHED, 100, 4, 4);
        spawns.addSpawn(EntityTypes.HUSK, 95, 4, 4);
    }

    // ---- Misc --------------------------------------------------------
    private static Vector3f rgb(int color) {
        return new Vector3f(((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F, (color & 0xFF) / 255.0F);
    }

    private static ResourceKey<Biome> create(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }
}