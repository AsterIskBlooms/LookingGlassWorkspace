package team.lookingglass.elsewhere.worldgen.rules;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.material.VanillaMaterialConditions;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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

    public static MaterialRule buildOverworld(HolderGetter<Biome> biomes, HolderGetter<MaterialCondition> conditions) {
        MaterialCondition onFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR);
        MaterialCondition underFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_FLOOR);
        MaterialCondition deepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.DEEP_UNDER_FLOOR);
        MaterialCondition veryDeepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.VERY_DEEP_UNDER_FLOOR);

        MaterialRule stoneCliffsRule = MaterialRules.ifTrue(AbovePreliminarySurfaceCondition.INSTANCE,
                MaterialRules.ifTrue(SteepCondition.INSTANCE, new BlockRule(Blocks.STONE.defaultBlockState())));

        MaterialRule limestoneCliffsRule = MaterialRules.ifTrue(AbovePreliminarySurfaceCondition.INSTANCE,
                MaterialRules.ifTrue(SteepCondition.INSTANCE, new BlockRule(EBlocks.LIMESTONE.defaultBlockState())));

        MaterialRule sandstoneCliffsRule = MaterialRules.ifTrue(
                AbovePreliminarySurfaceCondition.INSTANCE,
                MaterialRules.ifTrue(
                        SteepCondition.INSTANCE,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(Blocks.SAND.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        veryDeepUnderFloor,
                                        new BlockRule(Blocks.SANDSTONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule sandstoneRedCliffsRule = MaterialRules.ifTrue(
                AbovePreliminarySurfaceCondition.INSTANCE,
                MaterialRules.ifTrue(
                        SteepCondition.INSTANCE,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(Blocks.RED_SAND.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        veryDeepUnderFloor,
                                        new BlockRule(Blocks.RED_SANDSTONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule sandstonePinkCliffsRule = MaterialRules.ifTrue(
                AbovePreliminarySurfaceCondition.INSTANCE,
                MaterialRules.ifTrue(
                        SteepCondition.INSTANCE,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(EBlocks.PINK_SAND.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        veryDeepUnderFloor,
                                        new BlockRule(EBlocks.PINK_SANDSTONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule badlandsCliffRule = MaterialRules.ifTrue(
                AbovePreliminarySurfaceCondition.INSTANCE,
                MaterialRules.ifTrue(
                        SteepCondition.INSTANCE,
                        MaterialRules.bandlands()
                )
        );

        MaterialRule biomeCliffRules = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(
                                biomes,
                                Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.MEADOW, Biomes.SNOWY_PLAINS,
                                Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.DARK_FOREST, Biomes.CHERRY_GROVE,
                                Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.GROVE,
                                Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA,
                                Biomes.DAPPLED_FOREST,
                                Biomes.SWAMP, Biomes.MANGROVE_SWAMP,
                                Biomes.JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE,
                                Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA,
                                Biomes.STONY_SHORE, Biomes.BEACH, Biomes.SNOWY_BEACH,
                                Biomes.ICE_SPIKES
                        ),
                        stoneCliffsRule
                ),
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(biomes, Biomes.DESERT),
                        sandstoneCliffsRule
                ),
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(
                                biomes,
                                Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS
                        ),
                        badlandsCliffRule
                )
        );

        MaterialRule overworldChanges = prelimAndWaterCheck(
                MaterialRules.sequence(
                        MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, Biomes.DESERT),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(
                                                onFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.2),
                                                        new BlockRule(EBlocks.ARID_DIRT.defaultBlockState())
                                                )
                                        ),
                                        MaterialRules.ifTrue(
                                                underFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.2),
                                                        new BlockRule(EBlocks.ARID_DIRT.defaultBlockState())
                                                )
                                        )
                                )
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(
                                                onFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -1.0, 0.0),
                                                        new BlockRule(EBlocks.ARID_GRASS_BLOCK.defaultBlockState())
                                                )
                                        ),
                                        MaterialRules.ifTrue(
                                                underFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -1.0, 0.0),
                                                        new BlockRule(EBlocks.ARID_DIRT.defaultBlockState())
                                                )
                                        )
                                )
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, Biomes.OLD_GROWTH_BIRCH_FOREST),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(
                                                onFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                        new BlockRule(Blocks.PODZOL.defaultBlockState())
                                                )
                                        )
                                )
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(
                                                onFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                        new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                                )
                                        ),
                                        MaterialRules.ifTrue(
                                                onFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.3),
                                                        new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                                )
                                        ),
                                        MaterialRules.ifTrue(
                                                underFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                        new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                                )
                                        ),
                                        MaterialRules.ifTrue(
                                                underFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.3),
                                                        new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                                )
                                        )
                                )
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, Biomes.SPARSE_JUNGLE),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(
                                                onFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, 0.5, 1.0),
                                                        new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                                )
                                        ),
                                        MaterialRules.ifTrue(
                                                underFloor,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.noiseCondition2d(Noises.PATCH, 0.5, 1.0),
                                                        new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                                )
                                        )
                                )
                        ),
                        MaterialRules.ifTrue(MaterialRules.isBiome(biomes, Biomes.SULFUR_CAVES),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.4F, -0.1F),
                                                new BlockRule(Blocks.CINNABAR.defaultBlockState())),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.1F, 0.325F),
                                                new BlockRule(Blocks.SULFUR.defaultBlockState())),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.325F, 0.4F),
                                                new BlockRule(EBlocks.ORPIMENT.defaultBlockState())),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.4F),
                                                new BlockRule(Blocks.CINNABAR.defaultBlockState()))
                                )
                        )
                )
        );

        MaterialRule swampRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, Biomes.SWAMP),
                MaterialRules.sequence(
                        prelimAndWaterCheck(
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.SURFACE_SECONDARY, 0.0, 1.0),
                                                new BlockRule(Blocks.GRASS_BLOCK.defaultBlockState())
                                        )
                                )
                        ),
                        prelimAndWaterCheck(
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.SURFACE_SECONDARY, 0.0, 1.0),
                                                new BlockRule(Blocks.DIRT.defaultBlockState())
                                        )
                                )
                        ),
                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.MUD.defaultBlockState())),
                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.MUD.defaultBlockState()))
                )
        );

        MaterialRule outbackRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.OUTBACK),
                prelimAndWaterCheck(
                        MaterialRules.sequence(
                                sandstoneRedCliffsRule,
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.3),
                                                new BlockRule(Blocks.RED_SAND.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                new BlockRule(Blocks.RED_SAND.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(EBlocks.ARID_GRASS_BLOCK.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.3),
                                                new BlockRule(Blocks.RED_SAND.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                new BlockRule(Blocks.RED_SAND.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        new BlockRule(EBlocks.ARID_DIRT.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        deepUnderFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.3),
                                                new BlockRule(Blocks.RED_SANDSTONE.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        deepUnderFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                new BlockRule(Blocks.RED_SANDSTONE.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        deepUnderFloor,
                                        new BlockRule(Blocks.GRANITE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule shrublandRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.SHRUBLAND),
                prelimAndWaterCheck(
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.3),
                                                new BlockRule(EBlocks.ARID_GRASS_BLOCK.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                new BlockRule(EBlocks.ARID_GRASS_BLOCK.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(Blocks.GRASS_BLOCK.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.3),
                                                new BlockRule(EBlocks.ARID_DIRT.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, 0.3, 1.0),
                                                new BlockRule(EBlocks.ARID_DIRT.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        new BlockRule(Blocks.DIRT.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        deepUnderFloor,
                                        new BlockRule(Blocks.STONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule cedarForestRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.CEDAR_FOREST),
                prelimAndWaterCheck(
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, 0.45, 1.0),
                                                new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.45),
                                                new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, 0.45, 1.0),
                                                new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.45),
                                                new BlockRule(EBlocks.LATERITE.defaultBlockState())
                                        )
                                )
                        )
                )
        );

        MaterialRule steppeRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.STEPPE),
                prelimAndWaterCheck(
                        MaterialRules.sequence(
                                limestoneCliffsRule,
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.SURFACE, 0.4, 0.6),
                                                new BlockRule(Blocks.GRASS_BLOCK.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.SURFACE_SECONDARY, -0.2, 0.2),
                                                new BlockRule(Blocks.GRASS_BLOCK.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(EBlocks.ARID_GRASS_BLOCK.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.SURFACE, 0.4, 0.6),
                                                new BlockRule(Blocks.DIRT.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.SURFACE_SECONDARY, -0.2, 0.2),
                                                new BlockRule(Blocks.DIRT.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        new BlockRule(EBlocks.ARID_DIRT.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        veryDeepUnderFloor,
                                        new BlockRule(EBlocks.LIMESTONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule lushDesertRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.LUSH_DESERT),
                prelimAndWaterCheck(
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -0.5, 0.0),
                                        sandstonePinkCliffsRule
                                ),
                                sandstoneCliffsRule,
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -0.5, 0.0),
                                                new BlockRule(EBlocks.PINK_SAND.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(Blocks.SAND.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -0.5, 0.0),
                                                new BlockRule(EBlocks.PINK_SAND.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        underFloor,
                                        new BlockRule(Blocks.SAND.defaultBlockState())
                                ),
                                MaterialRules.ifTrue(
                                        veryDeepUnderFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -0.5, 0.0),
                                                new BlockRule(EBlocks.PINK_SANDSTONE.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        veryDeepUnderFloor,
                                        new BlockRule(Blocks.SANDSTONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule cloudForestRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.CLOUD_FOREST),
                prelimAndWaterCheck(
                        MaterialRules.sequence(
                                badlandsCliffRule,
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, 0.15, 1.0),
                                                new BlockRule(Blocks.MUD.defaultBlockState())
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        onFloor,
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -1.0, -0.15),
                                                new BlockRule(Blocks.MUD.defaultBlockState())
                                        )
                                ),
                                MaterialRules.bandlands()
                        )
                )
        );

        MaterialRule tundraRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.TUNDRA),
                prelimAndWaterCheck(
                        MaterialRules.sequence(
                                stoneCliffsRule,
                                MaterialRules.ifTrue(onFloor,
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, 0.35, 1.0),
                                        new BlockRule(Blocks.COARSE_DIRT.defaultBlockState()))),
                                MaterialRules.ifTrue(onFloor,
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -0.05, 0.1),
                                                new BlockRule(Blocks.COARSE_DIRT.defaultBlockState()))),
                                MaterialRules.ifTrue(onFloor,
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.BADLANDS_SURFACE, -0.35, -0.15),
                                                new BlockRule(Blocks.COARSE_DIRT.defaultBlockState()))),
                                MaterialRules.ifTrue(onFloor,
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.35),
                                        new BlockRule(Blocks.SNOW_BLOCK.defaultBlockState())))
                        )
                ));

        MaterialRule tidepoolRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.TIDEPOOLS),
                MaterialRules.sequence(
                        sandstoneCliffsRule,
                        MaterialRules.ifTrue(onFloor,
                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.45),
                                        new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState()))),
                        MaterialRules.ifTrue(onFloor,
                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -0.075, 0.075),
                                        new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState()))),
                        MaterialRules.ifTrue(onFloor,
                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, 0.45, 1.0),
                                        new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState()))),
                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.SAND.defaultBlockState())),
                        MaterialRules.ifTrue(underFloor,
                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -1.0, -0.35),
                                        new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState()))),
                        MaterialRules.ifTrue(
                                underFloor,
                                MaterialRules.ifTrue(
                                        MaterialRules.noiseCondition2d(Noises.PATCH, -0.05, 0.05),
                                        new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState())
                                )
                        ),
                        MaterialRules.ifTrue(
                                underFloor,
                                MaterialRules.ifTrue(
                                        MaterialRules.noiseCondition2d(Noises.PATCH, 0.35, 1.0),
                                        new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState())
                                )
                        ),
                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.SAND.defaultBlockState())),
                        MaterialRules.ifTrue(
                                deepUnderFloor,
                                new BlockRule(Blocks.SANDSTONE.defaultBlockState())
                        )
                )
        );

        MaterialRule coldBeachRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.COLD_BEACH),
                MaterialRules.sequence(
                        stoneCliffsRule,
                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.GRAVEL.defaultBlockState())),
                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.GRAVEL.defaultBlockState()))
                )
        );

        MaterialRule frozenBeachRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.FROZEN_BEACH),
                MaterialRules.sequence(
                        stoneCliffsRule,
                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.PACKED_ICE.defaultBlockState())),
                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.STONE.defaultBlockState()))
                )
        );

        MaterialRule riverRules = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(biomes, EBiomes.LUKEWARM_RIVER),
                        MaterialRules.sequence(
                                prelimAndWaterCheck(
                                        MaterialRules.sequence(
                                                stoneCliffsRule,
                                                MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.MUD.defaultBlockState())),
                                                MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.DIRT.defaultBlockState()))
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.MUD.defaultBlockState()))
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.MUD.defaultBlockState()))
                                )
                        )
                ),
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(biomes, Biomes.RIVER),
                        MaterialRules.sequence(
                                prelimAndWaterCheck(
                                        MaterialRules.sequence(
                                                stoneCliffsRule,
                                                MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.COARSE_DIRT.defaultBlockState())),
                                                MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.COARSE_DIRT.defaultBlockState()))
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.MUD.defaultBlockState()))
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.DIRT.defaultBlockState()))
                                )
                        )
                ),
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(biomes, EBiomes.COLD_RIVER),
                        MaterialRules.sequence(
                                prelimAndWaterCheck(
                                        MaterialRules.sequence(
                                                stoneCliffsRule,
                                                MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.GRAVEL.defaultBlockState())),
                                                MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.STONE.defaultBlockState()))
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.MUD.defaultBlockState()))
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.GRAVEL.defaultBlockState()))
                                )
                        )
                ),
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(biomes, Biomes.FROZEN_RIVER),
                        MaterialRules.sequence(
                                prelimAndWaterCheck(
                                        MaterialRules.sequence(
                                                stoneCliffsRule,
                                                MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.GRAVEL.defaultBlockState())),
                                                MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.STONE.defaultBlockState()))
                                        )
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(onFloor, new BlockRule(Blocks.MUD.defaultBlockState()))
                                ),
                                MaterialRules.ifTrue(
                                        MaterialRules.not(MaterialRules.waterBlockCheck(0, 0)),
                                        MaterialRules.ifTrue(underFloor, new BlockRule(Blocks.STONE.defaultBlockState()))
                                )
                        )
                )
        );

        MaterialRule crystalCavernRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.CRYSTAL_CAVERNS),
                MaterialRules.sequence(
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.25, -0.2),
                                new BlockRule(EBlocks.SODALITE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.0, 0.05),
                                new BlockRule(EBlocks.SODALITE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.25, 0.3),
                                new BlockRule(EBlocks.SODALITE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.2, -0.1),
                                new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.1, 0.2),
                                new BlockRule(Blocks.CALCITE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.2, 0.4),
                                new BlockRule(Blocks.SMOOTH_BASALT.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.4),
                                new BlockRule(Blocks.CALCITE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                AbovePreliminarySurfaceCondition.INSTANCE,
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(Blocks.STONE.defaultBlockState())
                                )
                        ),
                        MaterialRules.ifTrue(
                                AbovePreliminarySurfaceCondition.INSTANCE,
                                MaterialRules.ifTrue(
                                        underFloor,
                                        new BlockRule(Blocks.STONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule sulfurCaveRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, Biomes.SULFUR_CAVES), MaterialRules.ifTrue(
                        MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.325F, 0.4F),
                        new BlockRule(EBlocks.ORPIMENT.defaultBlockState())));

        MaterialRule aridCaveRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.ARID_CAVES),
                MaterialRules.sequence(
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.4F, -0.1F),
                                new BlockRule(Blocks.SANDSTONE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.0F, 0.2F),
                                new BlockRule(Blocks.SAND.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.2F, 0.5F),
                                new BlockRule(Blocks.SANDSTONE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.5F),
                                new BlockRule(Blocks.SAND.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                AbovePreliminarySurfaceCondition.INSTANCE,
                                MaterialRules.ifTrue(
                                        onFloor,
                                        new BlockRule(Blocks.STONE.defaultBlockState())
                                )
                        ),
                        MaterialRules.ifTrue(
                                AbovePreliminarySurfaceCondition.INSTANCE,
                                MaterialRules.ifTrue(
                                        underFloor,
                                        new BlockRule(Blocks.STONE.defaultBlockState())
                                )
                        )
                )
        );

        MaterialRule volcanicDepthsRules = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, EBiomes.VOLCANIC_DEPTHS),
                MaterialRules.sequence(
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.5F, -0.2F),
                                new BlockRule(Blocks.BLACKSTONE.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.0F, 0.2F),
                                new BlockRule(Blocks.TUFF.defaultBlockState())
                        ),
                        MaterialRules.ifTrue(
                                MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.3F),
                                new BlockRule(Blocks.BLACKSTONE.defaultBlockState())
                        )
                )
        );

        MaterialRule oceanRules = MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, Biomes.LUKEWARM_OCEAN), MaterialRules.sequence(
                        prelimAndWaterCheck(MaterialRules.sequence(
                                MaterialRules.ifTrue(onFloor,
                                        new BlockRule(Blocks.SAND.defaultBlockState())),
                                MaterialRules.ifTrue(underFloor,
                                        new BlockRule(Blocks.SAND.defaultBlockState())),
                                MaterialRules.ifTrue(veryDeepUnderFloor,
                                        new BlockRule(Blocks.SANDSTONE.defaultBlockState()))
                        )),
                        MaterialRules.ifTrue(onFloor, MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -0.5F, 0.2F),
                                new BlockRule(EBlocks.SEAGRASS_SAND.defaultBlockState()))),
                        MaterialRules.ifTrue(onFloor,
                                new BlockRule(Blocks.SAND.defaultBlockState())),
                        MaterialRules.ifTrue(underFloor,
                                new BlockRule(Blocks.SAND.defaultBlockState())),
                        MaterialRules.ifTrue(veryDeepUnderFloor,
                                new BlockRule(Blocks.SANDSTONE.defaultBlockState()))
                        )
                )
        );

        return MaterialRules.sequence(
                biomeCliffRules, overworldChanges,
                swampRules, riverRules, oceanRules,
                tidepoolRules, coldBeachRules, frozenBeachRules,
                shrublandRules, cedarForestRules,
                steppeRules,
                outbackRules, lushDesertRules,
                cloudForestRules,
                tundraRules,
                crystalCavernRules, sulfurCaveRules,
                aridCaveRules, volcanicDepthsRules
        );
    }

    public static MaterialRule buildNether(HolderGetter<Biome> biomes, HolderGetter<MaterialCondition> conditions) {
        MaterialCondition onFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR);
        MaterialCondition underFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_FLOOR);
        MaterialCondition deepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.DEEP_UNDER_FLOOR);
        MaterialCondition veryDeepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.VERY_DEEP_UNDER_FLOOR);

        MaterialRule soulSandValleyDeepRule = MaterialRules.ifTrue(MaterialRules.isBiome(biomes, Biomes.SOUL_SAND_VALLEY),
                MaterialRules.sequence(
                        MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.1F, 0.0F),
                                MaterialRules.ifTrue(onFloor, new BlockRule(EBlocks.SOUL_STONE.defaultBlockState()))),
                        MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.2F, 0.35F),
                                MaterialRules.ifTrue(onFloor, new BlockRule(EBlocks.SOUL_STONE.defaultBlockState()))),
                        MaterialRules.ifTrue(veryDeepUnderFloor, MaterialRules.ifTrue(MaterialRules.not(underFloor),
                                new BlockRule(EBlocks.SOUL_STONE.defaultBlockState()))),
                        MaterialRules.ifTrue(SteepCondition.INSTANCE, new BlockRule(EBlocks.SOUL_STONE.defaultBlockState()))
                ));

        return MaterialRules.sequence(soulSandValleyDeepRule);
    }

    private static MaterialRule prelimAndWaterCheck(MaterialRule inner){
        return MaterialRules.ifTrue(
                MaterialRules.waterBlockCheck(0, 0),
                MaterialRules.ifTrue(
                        AbovePreliminarySurfaceCondition.INSTANCE,
                        inner
                )
        );
    }
}