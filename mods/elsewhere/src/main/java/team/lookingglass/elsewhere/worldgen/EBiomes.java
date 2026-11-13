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
    ResourceKey<Biome> GLACIAL_VALLEY = create("glacial_valley");
    ResourceKey<Biome> SNOWY_TUNDRA = create("snowy_tundra");
    ResourceKey<Biome> FIR_THICKET = create("fir_thicket");
    ResourceKey<Biome> ALPINE_PLAINS = create("alpine_plains");
    ResourceKey<Biome> ALPINE_GROVE = create("alpine_grove");

    ResourceKey<Biome> TUNDRA = create("tundra");
    ResourceKey<Biome> STEPPE = create("steppe");
    ResourceKey<Biome> PINE_BARRENS = create("pine_barrens");
    ResourceKey<Biome> RAINFOREST = create("rainforest");
    ResourceKey<Biome> HIGHLANDS = create("highlands");
    ResourceKey<Biome> HOWLING_PEAKS = create("howling_peaks");
    ResourceKey<Biome> COLD_DESERT = create("cold_desert");

    ResourceKey<Biome> CHERRY_FIELDS = create("cherry_fields");
    ResourceKey<Biome> OLD_GROWTH_FOREST = create("old_growth_forest");
    ResourceKey<Biome> OAK_WOODLAND = create("oak_woodland");
    ResourceKey<Biome> PRAIRIE = create("prairie");
    ResourceKey<Biome> SHRUBLAND = create("shrubland");
    ResourceKey<Biome> DAPPLED_CLEARING = create("dappled_clearing");

    ResourceKey<Biome> ARID_SHRUBLAND = create("arid_shrubland");
    ResourceKey<Biome> CEDAR_FOREST = create("cedar_forest");
    ResourceKey<Biome> ROCKY_JUNGLE = create("rocky_jungle");

    ResourceKey<Biome> ACACIA_FLATS = create("acacia_flats");
    ResourceKey<Biome> DRYLAND = create("dryland");
    ResourceKey<Biome> OUTBACK = create("outback");
    ResourceKey<Biome> DUNES = create("dunes");
    ResourceKey<Biome> LUSH_DUNES = create("lush_dunes");
    ResourceKey<Biome> CLOUD_FOREST = create("cloud_forest");

    ResourceKey<Biome> BOG = create("bog");
    ResourceKey<Biome> MARSH = create("marsh");
    ResourceKey<Biome> TROPICAL_BASIN = create("tropical_basin");
    ResourceKey<Biome> DESERT_WETLAND = create("desert_wetland");

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
        context.register(FIR_THICKET, createFirThicket(context));
        context.register(ALPINE_PLAINS, createAlpinePlains(context));
        context.register(ALPINE_GROVE, createAlpineGrove(context));

        context.register(TUNDRA, createTundra(context));
        context.register(STEPPE, createSteppe(context));
        context.register(PINE_BARRENS, createPineBarrens(context));
        context.register(RAINFOREST, createRainforest(context));
        context.register(HIGHLANDS, createHighlands(context));
        context.register(HOWLING_PEAKS, createHowlingPeaks(context));
        context.register(COLD_DESERT, createColdDesert(context));

        context.register(CHERRY_FIELDS, createCherryFields(context));
        context.register(OLD_GROWTH_FOREST, createOldGrowthForest(context));
        context.register(OAK_WOODLAND, createOakWoodland(context));
        context.register(PRAIRIE, createPrairie(context));
        context.register(SHRUBLAND, createShrubland(context));
        context.register(DAPPLED_CLEARING, createDappledClearing(context));

        context.register(ARID_SHRUBLAND, createAridShrubland(context));
        context.register(CEDAR_FOREST, createCedarForest(context));
        context.register(ROCKY_JUNGLE, createRockyJungle(context));

        context.register(ACACIA_FLATS, createAcaciaFlats(context));
        context.register(DRYLAND, createDryland(context));
        context.register(OUTBACK, createOutback(context));
        context.register(DUNES, createDunes(context));
        context.register(LUSH_DUNES, createLushDunes(context));
        context.register(CLOUD_FOREST, createCloudForest(context));

        context.register(BOG, createBog(context));
        context.register(MARSH, createMarsh(context));
        context.register(TROPICAL_BASIN, createTropicalBasin(context));
        context.register(DESERT_WETLAND, createDesertWetland(context));

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

        return biome(-0.5F, 0.4F, new Water(0x5FB8D6, 0x0A2F4A), Sky.of(0xC8DCF0).fog(0xE4EEF8), Music.FROZEN_PEAKS, spawns, gen).build();
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
        veg(gen, EPlacements.TREES_TUNDRA, EPlacements.OCHRE_MOSS_PATCH, EPlacements.TUNDRA_RUSTY_MOSS_PATCH);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);
        local(gen, EPlacements.DRY_GRASS_CLUMP_SPARSE, EPlacements.TUNDRA_ROCK);

        return biome(-0.2F, 0.2F, Water.FROZEN.grass(0xa9b564), Sky.of(0xE8EEF4).fog(0xF2F5F8), Music.SNOWY_SLOPES, spawns, gen).build();
    }

    private static Biome createFirThicket(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.WOLF, 6, 4, 4);
        spawns.addSpawn(EntityTypes.FOX, 4, 2, 4);
        spawns.addSpawn(EntityTypes.RABBIT, 6, 2, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addMossyStoneBlock(gen);
        veg(gen, EPlacements.TREES_FIR_THICKET);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addDefaultMushrooms(gen);
        BiomeDefaultFeatures.addCommonBerryBushes(gen);

        return biome(0.1F, 0.8F, new Water(0x2F5C7A, 0x08202E).grass(0x5b8f5e).foliage(0x3d6e4c), Sky.of(0x84A4C6), Music.FOREST, spawns, gen).build();
    }

    private static Biome createAlpinePlains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.GOAT, 6, 1, 3);
        spawns.addSpawn(EntityTypes.RABBIT, 8, 2, 3);
        spawns.addSpawn(EntityTypes.FOX, 2, 2, 4);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        local(gen, EPlacements.MEADOW_ROCK);
        veg(gen, EPlacements.TREES_ALPINE_PLAINS);
        BiomeDefaultFeatures.addDefaultFlowers(gen);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);

        return biome(0.1F, 0.4F, new Water(0x0E4ECF, 0x050533).grass(0x7ea86a).foliage(0x5f8a5c), Sky.of(0x5C93FF), Music.SNOWY_SLOPES, spawns, gen).build();
    }

    private static Biome createAlpineGrove(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.WOLF, 6, 4, 4);
        spawns.addSpawn(EntityTypes.FOX, 4, 2, 4);
        spawns.addSpawn(EntityTypes.GOAT, 3, 1, 2);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addMossyStoneBlock(gen);
        veg(gen, EPlacements.TREES_ALPINE_GROVE);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addDefaultMushrooms(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);

        return biome(0.1F, 0.6F, new Water(0x2C7DA0, 0x0A2A40).grass(0x6e9d68).foliage(0x4a7f58), Sky.of(0x6FA0F0), Music.FOREST, spawns, gen).build();
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
        veg(gen, EPlacements.TREES_TUNDRA, EPlacements.OCHRE_MOSS_PATCH, EPlacements.TUNDRA_RUSTY_MOSS_PATCH);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        veg(gen, VegetationPlacements.PATCH_RED_SHRUB);
        BiomeDefaultFeatures.addNearWaterVegetation(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);
        local(gen, EPlacements.DRY_GRASS_CLUMP_SPARSE, EPlacements.TUNDRA_ROCK);

        return biome(0.4F, 0.55F, Water.COLD.grass(0xe3dc7b), Sky.of(0xD2DCE6), Music.SNOWY_SLOPES, spawns, gen).build();
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

        return biome(0.4F, 0.15F, Water.COLD, Sky.of(0xA6BEE8), Music.BADLANDS, spawns, gen).build();
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

        return biome(0.3F, 0.25F, Water.COLD.grass(0x8aa05c).foliage(0x6e8a4f), Sky.of(0x93B0D8), Music.FOREST, spawns, gen).build();
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

        return biome(0.5F, 1.0F, new Water(0x6A4A2A, 0x2E1F10).grass(0x58a05a).foliage(0x3a8650), Sky.of(0x9DB3BE).fog(0xB4C6C4), Music.FOREST, spawns, gen).build();
    }

    private static Biome createHighlands(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        BiomeDefaultFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityTypes.GOAT, 4, 1, 3);
        spawns.addSpawn(EntityTypes.FOX, 3, 2, 4);
        spawns.addSpawn(EntityTypes.RABBIT, 6, 2, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addMossyStoneBlock(gen);
        local(gen, EPlacements.TUNDRA_ROCK);
        veg(gen, EPlacements.TREES_HIGHLANDS);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        BiomeDefaultFeatures.addDefaultFlowers(gen);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);

        return biome(0.35F, 0.65F, new Water(0x3E5F8A, 0x0A1630).grass(0x86a05e).foliage(0x6a8a52), Sky.of(0x8FA8C8), Music.FOREST, spawns, gen).build();
    }

    private static Biome createHowlingPeaks(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.GOAT, 6, 1, 3);
        spawns.addSpawn(EntityTypes.WOLF, 4, 2, 4);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        raw(gen, EPlacements.HOWLING_SPIRES);
        local(gen, EPlacements.TUNDRA_ROCK);
        veg(gen, EPlacements.TREES_HOWLING_PEAKS);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        veg(gen, VegetationPlacements.PATCH_RED_SHRUB);

        return biome(0.3F, 0.25F, Water.COLD.grass(0xa8482f), Sky.of(0xA3A8C4).fog(0xCBB8B0), Music.FROZEN_PEAKS, spawns, gen).build();
    }

    private static Biome createColdDesert(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 30, 30);
        spawns.addSpawn(EntityTypes.STRAY, 80, 4, 4);
        spawns.addSpawn(EntityTypes.PARCHED, 40, 4, 4);
        spawns.addSpawn(EntityTypes.RABBIT, 6, 2, 3);
        spawns.addSpawn(EntityTypes.GOAT, 3, 1, 2);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addDesertVegetation(gen);
        raw(gen, EPlacements.DESERT_DIRT_PATCH);
        local(gen, EPlacements.DRY_GRASS_CLUMP_SPARSE, EPlacements.TUNDRA_ROCK);

        return biome(0.1F, 0.0F, new Water(0x7FA6B8, 0x1E3A4A).grass(0x9aa070).foliage(0x8a9068), Sky.of(0xBFCDE0), Music.DESERT, spawns, gen)
                .hasPrecipitation(false).setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, false)
                .build();
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

        return biome(0.5F, 0.8F, new Water(0x5DB7EF, 0x5DB7EF).grass(0xb6db61).foliage(0xb6db61), Sky.of(0x9BB6FF), Music.CHERRY_GROVE, spawns, gen)
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

        return biome(1.2F, 0.25F, new Water(0x3b6cd1, 0x3b6cd1), Sky.of(0x6F9FEA), Music.FOREST, spawns, gen).build();
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

        return biome(0.9F, 0.35F, Water.TEMPERATE, Sky.of(0x78A7FF), Music.FOREST, spawns, gen).build();
    }

    private static Biome createPrairie(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns(spawns);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addSavannaGrass(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        veg(gen, EPlacements.BLUEBONNET_DENSE);

        return biome(0.8F, 0.4F, Water.TEMPERATE.grass(0x9cc558).foliage(0x82ad4c), Sky.of(0x6FB0FF), null, spawns, gen).build();
    }

    private static Biome createShrubland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        BiomeDefaultFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityTypes.HORSE, 3, 2, 6);
        spawns.addSpawn(EntityTypes.RABBIT, 6, 2, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        veg(gen, EPlacements.SHRUBLAND_SHRUBS);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addDefaultFlowers(gen);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addRareBerryBushes(gen);

        return biome(0.9F, 0.35F, Water.TEMPERATE.grass(0xa0b45e).foliage(0x8a9f4f), Sky.of(0x73AAF8), null, spawns, gen).build();
    }

    private static Biome createDappledClearing(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        BiomeDefaultFeatures.farmAnimals(spawns);
        spawns.addSpawn(EntityTypes.FOX, 4, 2, 4);
        spawns.addSpawn(EntityTypes.RABBIT, 6, 2, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        veg(gen, EPlacements.OLD_GROWTH_FLOWERS, EPlacements.TREES_DAPPLED_CLEARING);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addSavannaExtraGrass(gen);
        veg(gen, VegetationPlacements.PATCH_RED_SHRUB);
        BiomeDefaultFeatures.addNearWaterVegetation(gen);

        return biome(0.7F, 0.5F, new Water(0x5A6F73, 0x8A9A9C).grass(0xD4B445).foliage(0xC8922E), Sky.of(0xA9BCD6).fog(0xE0CB8A), Music.FOREST, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Warm
    // ------------------------------------------------------------------

    private static Biome createAridShrubland(BootstrapContext<Biome> context) {
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

        return biome(1.8F, 0.15F, Water.WARM, Sky.of(0x80B2F5), null, spawns, gen).build();
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

        return biome(1.8F, 0.25F, Water.WARM, Sky.of(0x7BAEF0), Music.FOREST, spawns, gen).build();
    }

    private static Biome createRockyJungle(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(spawns);
        spawns.addSpawn(EntityTypes.OCELOT, 2, 1, 3);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addMossyStoneBlock(gen);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addFerns(gen);
        veg(gen, EPlacements.TREES_ROCKY_JUNGLE);
        BiomeDefaultFeatures.addJungleGrass(gen);
        BiomeDefaultFeatures.addJungleVines(gen);
        BiomeDefaultFeatures.addSparseJungleMelons(gen);
        veg(gen, EPlacements.HIBISCUS_JUNGLE);

        return biome(1.3F, 0.7F, Water.WARM, Sky.of(0x78AEE0), Music.JUNGLE, spawns, gen).build();
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

        return biome(2.0F, 0.08F, Water.HOT, Sky.of(0x86B8F0), Music.DESERT, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    private static Biome createDryland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.HORSE, 4, 2, 6);
        spawns.addSpawn(EntityTypes.ARMADILLO, 4, 1, 3);
        spawns.addSpawn(EntityTypes.RABBIT, 6, 2, 3);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        BiomeDefaultFeatures.addDefaultGrass(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        BiomeDefaultFeatures.addDesertVegetation(gen);
        dryProduce(gen);
        veg(gen, EPlacements.OAK_SHRUBS);
        local(gen, EPlacements.DRY_GRASS_CLUMP);

        return biome(1.1F, 0.15F, Water.WARM.grass(0xb9a85c).foliage(0xa59a52), Sky.of(0x82B6F0), Music.DESERT, spawns, gen).build();
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

        return biome(2.0F, 0.0F, new Water(0x7A6A3A, 0x2A2410).grass(0xD6A44D), Sky.of(0x62A9FF), Music.DESERT, spawns, gen)
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

        return biome(2.0F, 0.0F, Water.HOT, Sky.of(0x8CC6FF), Music.DESERT, spawns, gen)
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

        return biome(2.0F, 0.4F, new Water(0x2BD3C4, 0x0A4A55), Sky.of(0x6ABFF2), Music.DESERT, spawns, gen)
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

        return biome(2.0F, 0.6F, new Water(0x5CC8D8, 0x2E5A66), Sky.of(0xC5D8E4).fog(0xDDE8EE), Music.DESERT, spawns, gen)
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

        return biome(0.0F, 0.85F, new Water(0x4F5A47, 0x1F241B), Sky.of(0x8A9AA8).fog(0xA3AEA6), Music.SWAMP, spawns, gen).build();
    }

    private static Biome createMarsh(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.swampSpawns(spawns, 30);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);
        BiomeDefaultFeatures.addDefaultSprings(gen);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
        BiomeDefaultFeatures.addSwampExtraVegetation(gen);
        veg(gen, EPlacements.SWAMP_CATTAILS);

        return biome(0.8F, 0.9F, new Water(0x3E8F8A, 0x0F3A36), Sky.of(0x8FB0B0), Music.SWAMP, spawns, gen).build();
    }

    private static Biome createTropicalBasin(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(spawns);
        spawns.addSpawn(EntityTypes.PARROT, 20, 1, 2);
        spawns.addSpawn(EntityTypes.FROG, 10, 2, 5);

        BiomeGenerationSettings.Builder gen = baseGen(context, false);
        BiomeDefaultFeatures.addMangroveSwampDisks(gen);
        BiomeDefaultFeatures.addLightBambooVegetation(gen);
        BiomeDefaultFeatures.addSparseJungleTrees(gen);
        BiomeDefaultFeatures.addJungleGrass(gen);
        veg(gen, VegetationPlacements.PATCH_WATERLILY);
        BiomeDefaultFeatures.addNearWaterVegetation(gen);
        BiomeDefaultFeatures.addJungleVines(gen);
        BiomeDefaultFeatures.addSparseJungleMelons(gen);
        veg(gen, EPlacements.MANGROVE_CATTAILS);

        return biome(1.6F, 0.9F, new Water(0x30C7B0, 0x0B4B45), Sky.of(0x62B6F0), Music.JUNGLE, spawns, gen).build();
    }

    private static Biome createDesertWetland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.swampSpawns(spawns, 30);

        BiomeGenerationSettings.Builder gen = goldGen(context);
        BiomeDefaultFeatures.addBushes(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        veg(gen, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        BiomeDefaultFeatures.addDesertExtraDecoration(gen);
        veg(gen, EPlacements.DUNE_DRY_GRASS);
        veg(gen, EPlacements.SWAMP_CATTAILS);

        return biome(1.8F, 0.9F, Water.HOT, Sky.of(0x0B4B45), Music.SWAMP, spawns, gen).build();
    }

    // ------------------------------------------------------------------
    // Beaches
    // ------------------------------------------------------------------

    private static Biome createFrozenBeach(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hostiles(spawns, 20, 20);
        spawns.addSpawn(EntityTypes.STRAY, 80, 4, 4);
        spawns.addSpawn(EntityTypes.TURTLE, 1, 2, 5);

        return biome(0.1F, 0.0F, Water.FROZEN, Sky.of(0xD0DCE8), Music.FROZEN_PEAKS, spawns, baseGen(context)).build();
    }

    private static Biome createColdBeach(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(EntityTypes.TURTLE, 1, 2, 5);

        return biome(0.6F, 0.6F, Water.COLD, Sky.of(0x98B4DC), null, spawns, baseGen(context))
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

        return biome(1.0F, 0.6F, new Water(0x537C94, 0x041F33), Sky.of(0x7DB5E0), null, spawns, gen)
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

        return biome(0.5F, 0.6F, Water.COLD, Sky.of(0x7FA8E8), Music.FOREST, spawns, gen)
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

        return biome(1.5F, 0.7F, Water.WARM, Sky.of(0x76B0EA), Music.JUNGLE, spawns, gen).build();
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

        return biome(0.8F, 0.4F, Water.TEMPERATE, Sky.of(0x78A7FF).fog(0x323342), Music.DRIPSTONE_CAVES, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 512F)
                .build();
    }

    private static Biome createCrystalCaverns(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        caveMobs(spawns);
        spawns.addSpawn(EEntities.SUBSLIME, 100, 2, 2);
        spawns.addSpawn(EntityTypes.SKELETON, 100, 4, 4);
        spawns.addSpawn(EntityTypes.ZOMBIE, 95, 4, 4);

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

        return biome(1.0F, 1.0F, new Water(0x6B38C9, 0x140533), Sky.of(0x8A6FD0).fog(0x48344d),
                new BackgroundMusic(ESounds.MUSIC_BIOME_CRYSTAL_CAVERNS), spawns, gen)
                .hasPrecipitation(false)
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

        return biome(2.0F, 0.0F, Water.HOT, Sky.of(0xE0C890).fog(0xFFE991), Music.DESERT, spawns, gen)
                .hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 256F)
                .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                .build();
    }

    private static Biome createVolcanicDepths(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        hotCaveMobs(spawns);

        BiomeGenerationSettings.Builder gen = baseGen(context);
        BiomeDefaultFeatures.addDefaultMonsterRoom(gen);

        return biome(3.0F, 0.0F, new Water(0x3B6A7A, 0x0F1A1F), Sky.of(0x6A3A28).fog(0x422619), Music.BADLANDS, spawns, gen)
                .hasPrecipitation(false)
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
    private static Biome.BiomeBuilder biome(float temperature, float downfall, Water water, Sky sky, BackgroundMusic music,
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
                .setAttribute(EnvironmentAttributes.SKY_COLOR, rgb(sky.color()))
                .mobSpawnSettings(spawns.build())
                .generationSettings(gen.build());

        if (sky.fog() != null) {
            builder.setAttribute(EnvironmentAttributes.FOG_COLOR, rgb(sky.fog()));
        }
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

    /**
     * Sky colour (+ optional fog colour). Only give a biome a fog colour when you want it to
     * override the dimension's fog; leave it off to keep the normal day/sunset fog.
     */
    record Sky(int color, Integer fog) {
        public static Sky of(int color) {
            return new Sky(color, null);
        }

        public Sky fog(int fog) {
            return new Sky(color, fog);
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