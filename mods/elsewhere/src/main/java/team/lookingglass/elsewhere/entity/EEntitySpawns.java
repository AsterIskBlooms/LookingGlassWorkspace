package team.lookingglass.elsewhere.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import team.lookingglass.elsewhere.worldgen.EBiomes;

import java.util.List;

public class EEntitySpawns {
    public static void initialize() {
        BiomeModifications.addSpawn(ctx -> ctx.getBiomeKey() == Biomes.MEADOW,
                MobCategory.MONSTER, EntityTypes.ZOMBIE_HORSE, 5, 1, 1);

        // Surface Mobs
        BiomeModifications.addSpawn(ctx -> ctx.hasTag(BiomeTags.IS_OVERWORLD),
                MobCategory.MONSTER, EEntities.GRAVEKEEPER, 60, 1, 1);

        // Cave Mobs
        BiomeModifications.addSpawn(ctx -> ctx.hasTag(BiomeTags.IS_CAVE),
                MobCategory.MONSTER, EEntities.SWEEPER, 50, 1, 2);
        BiomeModifications.addSpawn(ctx -> ctx.hasTag(BiomeTags.IS_CAVE),
                MobCategory.MONSTER, EEntities.VANGUARD, 35, 1, 1);
        BiomeModifications.addSpawn(ctx -> ctx.hasTag(BiomeTags.IS_CAVE),
                MobCategory.MONSTER, EEntities.WHISTLER, 20, 1, 1);

        // Mud Golem
        List<ResourceKey<Biome>> mudGolemBiomes = List.of(Biomes.SWAMP, Biomes.MANGROVE_SWAMP, EBiomes.BOG, Biomes.LUSH_CAVES);
        BiomeModifications.addSpawn(ctx -> mudGolemBiomes.contains(ctx.getBiomeKey()),
                MobCategory.MONSTER, EEntities.MUD_GOLEM, 65, 1, 2);

        // Lost Soul
        BiomeModifications.addSpawn(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                MobCategory.MONSTER, EEntities.LOST_SOUL, 4, 2, 2);

        // New Natural Spawns
        List<ResourceKey<Biome>> breezeBiomes = List.of(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS, Biomes.STONY_PEAKS);
        BiomeModifications.addSpawn(ctx -> breezeBiomes.contains(ctx.getBiomeKey()),
                MobCategory.MONSTER, EntityTypes.BREEZE, 45, 2, 2);
        BiomeModifications.addSpawn(ctx -> ctx.getBiomeKey() == Biomes.NETHER_WASTES,
                MobCategory.MONSTER, EntityTypes.BLAZE, 5, 2, 2);
        BiomeModifications.addSpawn(ctx -> ctx.getBiomeKey() == Biomes.SOUL_SAND_VALLEY,
                MobCategory.MONSTER, EntityTypes.WITHER_SKELETON, 8, 2, 2);




        BiomeModifications.addSpawn(ctx -> ctx.getBiomeKey() == Biomes.RIVER,
                MobCategory.WATER_AMBIENT, EEntities.PERCH, 1, 3, 5);
        BiomeModifications.addSpawn(ctx -> ctx.getBiomeKey() == Biomes.SWAMP,
                MobCategory.WATER_AMBIENT, EEntities.PERCH, 2, 3, 5);
        BiomeModifications.addSpawn(ctx -> ctx.getBiomeKey() == Biomes.MANGROVE_SWAMP,
                MobCategory.WATER_AMBIENT, EEntities.PERCH, 2, 3, 5);
    }
}
