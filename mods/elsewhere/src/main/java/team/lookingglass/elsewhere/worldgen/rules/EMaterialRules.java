package team.lookingglass.elsewhere.worldgen.rules;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.material.VanillaMaterialConditions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.AbovePreliminarySurfaceCondition;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.condition.SteepCondition;
import net.minecraft.world.level.levelgen.material.rule.BlockRule;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import team.lookingglass.elsewhere.registry.EBlocks;
import team.lookingglass.elsewhere.worldgen.EBiomes;

public class EMaterialRules {

    private final HolderGetter<Biome> biomes;
    private final MaterialCondition onFloor;
    private final MaterialCondition underFloor;
    private final MaterialCondition deepUnderFloor;
    private final MaterialCondition veryDeepUnderFloor;

    // Cliff presets (exposed on steep slopes above the preliminary surface)
    private final MaterialRule stoneCliffs = cliffs(block(Blocks.STONE));
    private final MaterialRule terracottaCliffs = cliffs(block(Blocks.TERRACOTTA));
    private final MaterialRule steppeCliffs = cliffs(SteppeRule.INSTANCE);
    private final MaterialRule badlandsCliffs = cliffs(MaterialRules.bandlands());
    private final MaterialRule sandstoneCliffs;
    private final MaterialRule redSandstoneCliffs;
    private final MaterialRule pinkSandstoneCliffs;
    private final MaterialRule aridSandstoneCliffs;

    private EMaterialRules(HolderGetter<Biome> biomes, HolderGetter<MaterialCondition> conditions) {
        this.biomes = biomes;
        this.onFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR);
        this.underFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_FLOOR);
        this.deepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.DEEP_UNDER_FLOOR);
        this.veryDeepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.VERY_DEEP_UNDER_FLOOR);

        this.sandstoneCliffs = sandCliffs(Blocks.SAND, Blocks.SANDSTONE);
        this.redSandstoneCliffs = sandCliffs(Blocks.RED_SAND, Blocks.RED_SANDSTONE);
        this.pinkSandstoneCliffs = sandCliffs(EBlocks.PINK_SAND, EBlocks.PINK_SANDSTONE);
        this.aridSandstoneCliffs = sandCliffs(EBlocks.ARID_SAND, Blocks.SANDSTONE);
    }

    public static MaterialRule buildOverworld(HolderGetter<Biome> biomes, HolderGetter<MaterialCondition> conditions) {
        EMaterialRules rules = new EMaterialRules(biomes, conditions);
        return seq(rules.vanillaRules(), rules.elsewhereRules());
    }

    public static MaterialRule buildNether(HolderGetter<Biome> biomes, HolderGetter<MaterialCondition> conditions) {
        return new EMaterialRules(biomes, conditions).netherRules();
    }

    // ==================================================================
    //  VANILLA BIOMES
    // ==================================================================

    private MaterialRule vanillaRules() {
        return seq(
                // ---- cliffs ----
                when(in(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.MEADOW, Biomes.SNOWY_PLAINS,
                                Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.DARK_FOREST, Biomes.CHERRY_GROVE,
                                Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.GROVE,
                                Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA,
                                Biomes.DAPPLED_FOREST, Biomes.SWAMP, Biomes.MANGROVE_SWAMP,
                                Biomes.JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE,
                                Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA,
                                Biomes.STONY_SHORE, Biomes.BEACH, Biomes.SNOWY_BEACH, Biomes.ICE_SPIKES),
                        stoneCliffs),
                when(in(Biomes.DESERT), sandstoneCliffs),
                when(in(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS), badlandsCliffs),

                // ---- surface patches ----
                aboveWater(seq(
                        when(in(Biomes.DESERT), patch(badlandsNoise(0.0, 0.15), EBlocks.ARID_SAND, EBlocks.ARID_SAND)),
                        when(in(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU), patch(badlandsNoise(-1.0, 0.0), EBlocks.ARID_GRASS_BLOCK, EBlocks.ARID_DIRT)),
                        when(in(Biomes.TAIGA, Biomes.SNOWY_TAIGA), patch(smallPatchNoise(1.2), Blocks.COARSE_DIRT)),
                        when(in(Biomes.DARK_FOREST), patch(badlandsNoise(-1.0, 0.0), EBlocks.PEAT)),
                        when(in(Biomes.OLD_GROWTH_BIRCH_FOREST),
                                patch(smallPatchNoise(1.15), Blocks.COARSE_DIRT),
                                bothSides(0.35, Blocks.PODZOL)),
                        when(in(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE),
                                patch(smallPatchNoise(1.2), Blocks.MUD, Blocks.MUD),
                                bothSides(0.3, EBlocks.LATERITE, EBlocks.LATERITE)),
                        when(in(Biomes.SPARSE_JUNGLE), patch(patchNoise(0.5, 1.0), EBlocks.LATERITE, EBlocks.LATERITE))
                )),

                // ---- water / wetlands ----
                when(in(Biomes.SWAMP), swampSurface()),
                when(in(Biomes.RIVER), river(Blocks.COARSE_DIRT, Blocks.COARSE_DIRT, Blocks.DIRT)),
                when(in(Biomes.FROZEN_RIVER), river(Blocks.GRAVEL, Blocks.STONE, Blocks.STONE)),
                when(in(Biomes.LUKEWARM_OCEAN),
                        aboveWater(deepestLayers(Blocks.SAND, Blocks.SAND, Blocks.SANDSTONE)),
                        patch(patchNoise(-0.5, 0.2), EBlocks.SEAGRASS_SAND),
                        deepestLayers(Blocks.SAND, Blocks.SAND, Blocks.SANDSTONE)),

                // ---- caves ----
                when(in(Biomes.DRIPSTONE_CAVES),
                        gradientBand(EBlocks.SHALE, -0.15F, 0.08F),
                        gradientBand(EBlocks.SHALE, 0.5F)),
                when(in(Biomes.SULFUR_CAVES), gradientBand(EBlocks.ORPIMENT, 0.325F, 0.4F))
        );
    }

    // ==================================================================
    //  ELSEWHERE BIOMES  (same order as EBiomes)
    // ==================================================================

    private MaterialRule elsewhereRules() {
        MaterialCondition pinkZone = badlandsNoise(-0.5, 0.0);

        return seq(
                // ---- land (only above the waterline) ----
                aboveWater(seq(
                        // snowy
                        when(in(EBiomes.GLACIAL_VALLEY), stoneCliffs,
                                patch(smallPatchNoise(1.1), Blocks.COARSE_DIRT),
                                patch(patchNoise(0.4, 1.0), Blocks.GRAVEL, Blocks.GRAVEL)),
                        when(in(EBiomes.SNOWY_TUNDRA, EBiomes.TUNDRA), stoneCliffs,
                                patch(smallPatchNoise(1.15), Blocks.COARSE_DIRT)),

                        // cold
                        when(in(EBiomes.STEPPE), steppeCliffs,
                                patch(surfaceNoise(0.4, 0.6), Blocks.GRASS_BLOCK),
                                patch(secondaryNoise(-0.2, 0.0), Blocks.GRASS_BLOCK),
                                floor(EBlocks.ARID_GRASS_BLOCK),
                                when(underFloor, SteppeRule.INSTANCE),
                                when(deepUnderFloor, SteppeRule.INSTANCE),
                                when(patchNoise(1.0, 1.2), SteppeRule.INSTANCE)),
                        when(in(EBiomes.PINE_BARRENS), stoneCliffs,
                                patch(badlandsNoise(-0.05, 0.15), Blocks.GRANITE, Blocks.GRANITE),
                                patch(smallPatchNoise(0.9), EBlocks.SALT)),
                        when(in(EBiomes.RAINFOREST), stoneCliffs,
                                patch(patchNoise(0.7, 1.0), Blocks.MUD, Blocks.MUD),
                                patch(smallPatchNoise(1.0), EBlocks.MOSSY_MUD, Blocks.MUD),
                                layers(EBlocks.PEAT, EBlocks.PEAT)),

                        // temperate
                        when(in(EBiomes.OLD_GROWTH_FOREST), stoneCliffs,
                                patch(smallPatchNoise(1.15), Blocks.COARSE_DIRT),
                                bothSides(0.35, Blocks.PODZOL)),
                        when(in(EBiomes.OAK_WOODLAND), stoneCliffs,
                                patch(smallPatchNoise(1.15), EBlocks.SILT)),

                        // warm
                        when(in(EBiomes.SHRUBLAND),
                                bothSides(0.3, EBlocks.ARID_GRASS_BLOCK, EBlocks.ARID_DIRT),
                                layers(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.STONE)),
                        when(in(EBiomes.CEDAR_FOREST), bothSides(0.45, EBlocks.LATERITE, EBlocks.LATERITE)),
                        when(in(EBiomes.ROCKY_JUNGLE), terracottaCliffs,
                                patch(smallPatchNoise(0.9), EBlocks.LATERITE),
                                bothSides(0.15, Blocks.GRASS_BLOCK, Blocks.DIRT),
                                deepestLayers(Blocks.TERRACOTTA, Blocks.TERRACOTTA, Blocks.TERRACOTTA)),
                        when(in(EBiomes.TROPICAL_BASIN),
                                patch(smallPatchNoise(1.0), Blocks.MUD, Blocks.MUD),
                                bothSides(0.45, EBlocks.LATERITE, EBlocks.LATERITE)),

                        // hot
                        when(in(EBiomes.ACACIA_FLATS), terracottaCliffs,
                                patch(badlandsNoise(-1.0, 0.0), EBlocks.ARID_GRASS_BLOCK, EBlocks.ARID_DIRT),
                                patch(patchNoise(0.5, 1.0), Blocks.COARSE_DIRT),
                                deepestLayers(Blocks.RED_SAND, Blocks.SAND, Blocks.TERRACOTTA)),
                        when(in(EBiomes.OUTBACK), redSandstoneCliffs,
                                bothSides(0.3, Blocks.RED_SAND, Blocks.RED_SAND, Blocks.RED_SANDSTONE),
                                layers(EBlocks.ARID_GRASS_BLOCK, EBlocks.ARID_DIRT, Blocks.GRANITE)),
                        when(in(EBiomes.DUNES), aridSandstoneCliffs,
                                layers(EBlocks.ARID_SAND, EBlocks.ARID_SAND, Blocks.SANDSTONE)),
                        when(in(EBiomes.LUSH_DUNES),
                                when(pinkZone, pinkSandstoneCliffs),
                                sandstoneCliffs,
                                when(pinkZone, layers(EBlocks.PINK_SAND, EBlocks.PINK_SAND), veryDeep(EBlocks.PINK_SANDSTONE)),
                                deepestLayers(Blocks.SAND, Blocks.SAND, Blocks.SANDSTONE)),
                        when(in(EBiomes.CLOUD_FOREST), badlandsCliffs,
                                patch(badlandsNoise(0.15, 1.0), Blocks.MUD),
                                patch(badlandsNoise(-1.0, -0.15), Blocks.MUD),
                                MaterialRules.bandlands()),

                        // swamp
                        when(in(EBiomes.BOG, EBiomes.MARSH), swampSurface())
                )),

                // ---- beaches ----
                when(in(EBiomes.FROZEN_BEACH), stoneCliffs, layers(Blocks.PACKED_ICE, Blocks.STONE)),
                when(in(EBiomes.COLD_BEACH), stoneCliffs, layers(Blocks.GRAVEL, Blocks.GRAVEL)),
                when(in(EBiomes.TIDEPOOLS), sandstoneCliffs,
                        patch(smallPatchNoise(1.15), Blocks.SMOOTH_BASALT, Blocks.SMOOTH_BASALT),
                        under(Blocks.SAND),
                        deep(Blocks.SANDSTONE)),

                // ---- rivers ----
                when(in(EBiomes.COLD_RIVER), river(Blocks.GRAVEL, Blocks.STONE, Blocks.GRAVEL)),
                when(in(EBiomes.WARM_RIVER), river(Blocks.SAND, Blocks.SAND, Blocks.MUD)),

                // ---- caves ----
                when(in(EBiomes.CAVES), caveStone()),
                when(in(EBiomes.CRYSTAL_CAVERNS),
                        gradientBand(EBlocks.SODALITE, -0.3F, -0.2F),
                        gradientBand(Blocks.CALCITE, -0.35F, -0.15F),
                        gradientBand(EBlocks.SODALITE, 0.1F, 0.2F),
                        gradientBand(Blocks.CALCITE, 0.05F, 0.3F),
                        caveStone()),
                when(in(EBiomes.ARID_CAVES),
                        gradientBand(Blocks.SANDSTONE, -0.4F, -0.1F),
                        gradientBand(Blocks.SAND, 0.0F, 0.2F),
                        gradientBand(Blocks.SANDSTONE, 0.2F, 0.5F),
                        gradientBand(Blocks.SAND, 0.5F),
                        caveStone()),
                when(in(EBiomes.VOLCANIC_DEPTHS),
                        gradientBand(Blocks.BLACKSTONE, -0.5F, -0.2F),
                        gradientBand(Blocks.TUFF, 0.0F, 0.2F),
                        gradientBand(Blocks.BLACKSTONE, 0.3F))
        );
    }

    private MaterialRule netherRules() {
        return when(in(Biomes.SOUL_SAND_VALLEY),
                when(gradient(-0.1F, 0.0F), floor(EBlocks.SOUL_STONE)),
                when(gradient(0.2F, 0.35F), floor(EBlocks.SOUL_STONE)),
                when(veryDeepUnderFloor, when(MaterialRules.not(underFloor), block(EBlocks.SOUL_STONE))),
                when(SteepCondition.INSTANCE, block(EBlocks.SOUL_STONE)));
    }

    // ==================================================================
    //  SHARED SURFACES
    // ==================================================================

    /** Grass/dirt patches on dry ground, mud everywhere else. Used by swamp, bog and marsh. */
    private MaterialRule swampSurface() {
        return seq(
                aboveWater(patch(secondaryNoise(0.0, 1.0), Blocks.GRASS_BLOCK, Blocks.DIRT)),
                layers(Blocks.MUD, Blocks.MUD));
    }

    /** Dry banks use bankTop/bankSub (plus stone cliffs), the underwater bed is mud over bedSub. */
    private MaterialRule river(Block bankTop, Block bankSub, Block bedSub) {
        return seq(
                aboveWater(seq(stoneCliffs, layers(bankTop, bankSub))),
                underwater(layers(Blocks.MUD, bedSub)));
    }

    private MaterialRule caveStone() {
        return MaterialRules.ifTrue(AbovePreliminarySurfaceCondition.INSTANCE, layers(Blocks.STONE, Blocks.STONE));
    }

    private MaterialRule sandCliffs(Block sand, Block sandstone) {
        return cliffs(seq(floor(sand), veryDeep(sandstone)));
    }

    // ==================================================================
    //  BUILDING BLOCKS
    // ==================================================================

    // ---- layers ----
    private MaterialRule floor(Block block) {
        return MaterialRules.ifTrue(onFloor, block(block));
    }

    private MaterialRule under(Block block) {
        return MaterialRules.ifTrue(underFloor, block(block));
    }

    private MaterialRule deep(Block block) {
        return MaterialRules.ifTrue(deepUnderFloor, block(block));
    }

    private MaterialRule veryDeep(Block block) {
        return MaterialRules.ifTrue(veryDeepUnderFloor, block(block));
    }

    private MaterialRule layers(Block top, Block sub) {
        return seq(floor(top), under(sub));
    }

    private MaterialRule layers(Block top, Block sub, Block base) {
        return seq(floor(top), under(sub), deep(base));
    }

    /** Like layers(), but the base block starts at the "very deep" depth. */
    private MaterialRule deepestLayers(Block top, Block sub, Block base) {
        return seq(floor(top), under(sub), veryDeep(base));
    }

    // ---- noise patches: the layer blocks, but only where the noise condition holds ----
    private MaterialRule patch(MaterialCondition noise, Block top) {
        return when(noise, floor(top));
    }

    private MaterialRule patch(MaterialCondition noise, Block top, Block sub) {
        return when(noise, layers(top, sub));
    }

    /** Patches on both tails of the PATCH noise (below -t and above t). */
    private MaterialRule bothSides(double t, Block top) {
        return seq(patch(patchNoise(-1.0, -t), top), patch(patchNoise(t, 1.0), top));
    }

    private MaterialRule bothSides(double t, Block top, Block sub) {
        return seq(patch(patchNoise(-1.0, -t), top, sub), patch(patchNoise(t, 1.0), top, sub));
    }

    private MaterialRule bothSides(double t, Block top, Block sub, Block base) {
        return seq(
                when(patchNoise(-1.0, -t), layers(top, sub, base)),
                when(patchNoise(t, 1.0), layers(top, sub, base)));
    }

    private MaterialRule gradientBand(Block block, float min, float max) {
        return when(gradient(min, max), block(block));
    }

    private MaterialRule gradientBand(Block block, float min) {
        return when(gradient(min), block(block));
    }

    // ---- conditions ----
    @SafeVarargs
    private MaterialCondition in(ResourceKey<Biome>... keys) {
        return MaterialRules.isBiome(biomes, keys);
    }

    private static MaterialCondition patchNoise(double min, double max) {
        return MaterialRules.noiseCondition2d(Noises.PATCH, min, max);
    }

    private static MaterialCondition smallPatchNoise(double min) {
        return MaterialRules.noiseCondition2d(Noises.SMALL_PATCH, min);
    }

    private static MaterialCondition badlandsNoise(double min, double max) {
        return MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, min, max);
    }

    private static MaterialCondition surfaceNoise(double min, double max) {
        return MaterialRules.noiseCondition2d(Noises.SURFACE, min, max);
    }

    private static MaterialCondition secondaryNoise(double min, double max) {
        return MaterialRules.noiseCondition2d(Noises.SURFACE_SECONDARY, min, max);
    }

    private static MaterialCondition gradient(float min, float max) {
        return MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, min, max);
    }

    private static MaterialCondition gradient(float min) {
        return MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, min);
    }

    // ---- wrappers ----
    private static MaterialRule seq(MaterialRule... rules) {
        return MaterialRules.sequence(rules);
    }

    private static MaterialRule when(MaterialCondition condition, MaterialRule... rules) {
        return MaterialRules.ifTrue(condition, seq(rules));
    }

    private static MaterialRule block(Block block) {
        return new BlockRule(block.defaultBlockState());
    }

    private static MaterialRule cliffs(MaterialRule rule) {
        return MaterialRules.ifTrue(AbovePreliminarySurfaceCondition.INSTANCE, MaterialRules.ifTrue(SteepCondition.INSTANCE, rule));
    }

    /** Dry land only (not submerged), above the preliminary surface. */
    private static MaterialRule aboveWater(MaterialRule inner) {
        return MaterialRules.ifTrue(MaterialRules.waterBlockCheck(0, 0),
                MaterialRules.ifTrue(AbovePreliminarySurfaceCondition.INSTANCE, inner));
    }

    private static MaterialRule underwater(MaterialRule inner) {
        return MaterialRules.ifTrue(MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)), inner);
    }
}