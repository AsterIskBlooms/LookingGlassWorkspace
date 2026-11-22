package team.lookingglass.elsewhere.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import team.lookingglass.elsewhere.registry.tags.EBiomeTags;
import team.lookingglass.elsewhere.worldgen.EBiomes;

import java.util.concurrent.CompletableFuture;

public class BiomeTagProvider extends FabricTagsProvider<Biome> {
    public BiomeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.BIOME, registryLookupFuture);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        // Biome types
        builder(BiomeTags.IS_BEACH).add(EBiomes.FROZEN_BEACH, EBiomes.COLD_BEACH, EBiomes.TIDEPOOLS);
        builder(BiomeTags.IS_RIVER).add(EBiomes.COLD_RIVER, EBiomes.WARM_RIVER);
        builder(BiomeTags.IS_CAVE).add(EBiomes.CAVES, EBiomes.CRYSTAL_CAVERNS, EBiomes.ARID_CAVES, EBiomes.VOLCANIC_DEPTHS);

        builder(BiomeTags.IS_TAIGA).add(EBiomes.FIR_THICKET, EBiomes.ALPINE_GROVE, EBiomes.PINE_BARRENS);
        builder(BiomeTags.IS_JUNGLE).add(EBiomes.RAINFOREST, EBiomes.ROCKY_JUNGLE, EBiomes.TROPICAL_BASIN);
        builder(BiomeTags.IS_FOREST).add(EBiomes.OLD_GROWTH_FOREST, EBiomes.OAK_WOODLAND, EBiomes.CEDAR_FOREST);
        builder(BiomeTags.IS_SAVANNA).add(EBiomes.ROCKY_DESERT, EBiomes.ARID_SHRUBLAND);

        // Mob variants
        ResourceKey<Biome>[] coldVariants = new ResourceKey[]{
                EBiomes.TUNDRA, EBiomes.FIR_THICKET, EBiomes.ALPINE_PLAINS, EBiomes.ALPINE_GROVE,
                EBiomes.PINE_BARRENS, EBiomes.RAINFOREST, EBiomes.STEPPE,
                EBiomes.HIGHLANDS, EBiomes.HOWLING_PEAKS, EBiomes.COLD_DESERT,
                EBiomes.BOG,
                EBiomes.FROZEN_BEACH, EBiomes.COLD_BEACH
        };
        ResourceKey<Biome>[] warmVariants = new ResourceKey[]{
                EBiomes.SHRUBLAND, EBiomes.ARID_SHRUBLAND, EBiomes.CEDAR_FOREST, EBiomes.ROCKY_JUNGLE,
                EBiomes.ROCKY_DESERT, EBiomes.DRYLAND, EBiomes.OUTBACK, EBiomes.DUNES, EBiomes.LUSH_DUNES, EBiomes.CLOUD_FOREST,
                EBiomes.TROPICAL_BASIN, EBiomes.DESERT_WETLAND,
                EBiomes.TIDEPOOLS, EBiomes.SALT_FLATS
        };
        builder(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS).add(coldVariants);
        builder(BiomeTags.SPAWNS_COLD_VARIANT_FROGS).add(coldVariants);
        builder(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS).add(warmVariants);
        builder(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).add(warmVariants);

        // Mob spawns
        builder(BiomeTags.SPAWNS_SNOW_FOXES).add(
                EBiomes.FIR_THICKET, EBiomes.ALPINE_PLAINS, EBiomes.ALPINE_GROVE,
                EBiomes.BOG, EBiomes.FROZEN_BEACH
        );
        builder(BiomeTags.SPAWNS_WHITE_RABBITS).add(EBiomes.FIR_THICKET, EBiomes.ALPINE_PLAINS, EBiomes.ALPINE_GROVE);
        builder(BiomeTags.SPAWNS_GOLD_RABBITS).add(EBiomes.DUNES, EBiomes.LUSH_DUNES);
        builder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS).add(EBiomes.BOG, EBiomes.MARSH, EBiomes.TROPICAL_BASIN);

        // Villages
        builder(BiomeTags.HAS_VILLAGE_SNOWY).add(EBiomes.FIR_THICKET, EBiomes.ALPINE_PLAINS, EBiomes.ALPINE_GROVE);
        builder(BiomeTags.HAS_VILLAGE_TAIGA).add(EBiomes.STEPPE, EBiomes.PINE_BARRENS);
        builder(BiomeTags.HAS_VILLAGE_PLAINS).add(EBiomes.CHERRY_FIELDS, EBiomes.OAK_WOODLAND);
        builder(BiomeTags.HAS_VILLAGE_SAVANNA).add(EBiomes.SHRUBLAND, EBiomes.ROCKY_DESERT, EBiomes.OUTBACK);
        builder(BiomeTags.HAS_VILLAGE_DESERT).add(EBiomes.LUSH_DUNES);

        // Other structures
        builder(BiomeTags.HAS_IGLOO).add(EBiomes.ALPINE_PLAINS);
        builder(BiomeTags.HAS_JUNGLE_TEMPLE).add(EBiomes.RAINFOREST, EBiomes.ROCKY_JUNGLE, EBiomes.TROPICAL_BASIN);
        builder(BiomeTags.HAS_SWAMP_HUT).add(EBiomes.BOG, EBiomes.MARSH);
        builder(BiomeTags.HAS_PILLAGER_OUTPOST).add(
                EBiomes.TUNDRA, EBiomes.STEPPE,
                EBiomes.SHRUBLAND, EBiomes.ROCKY_DESERT, EBiomes.OUTBACK, EBiomes.LUSH_DUNES
        );
        builder(EBiomeTags.HAS_TRIAL_ROOM).addTag(BiomeTags.IS_CAVE);

        // Abandoned camps
        builder(BiomeTags.HAS_ABANDONED_CAMP_SNOWY_TAIGA).add(EBiomes.ALPINE_GROVE, EBiomes.ALPINE_PLAINS);
        builder(BiomeTags.HAS_ABANDONED_CAMP_TAIGA).add(EBiomes.TUNDRA, EBiomes.STEPPE, EBiomes.PINE_BARRENS);
        builder(BiomeTags.HAS_ABANDONED_CAMP_CHERRY_GROVE).add(EBiomes.CHERRY_FIELDS);
        builder(BiomeTags.HAS_ABANDONED_CAMP_FOREST).add(EBiomes.OLD_GROWTH_FOREST, EBiomes.OAK_WOODLAND, EBiomes.CEDAR_FOREST);
        builder(BiomeTags.HAS_ABANDONED_CAMP_SPARSE_JUNGLE).add(EBiomes.ROCKY_JUNGLE);
        builder(BiomeTags.HAS_ABANDONED_CAMP_SAVANNA).add(EBiomes.ROCKY_DESERT, EBiomes.OUTBACK);
        builder(BiomeTags.HAS_ABANDONED_CAMP_SWAMP).add(EBiomes.MARSH);

        // Overworld
        builder(BiomeTags.IS_OVERWORLD)
                .add(
                        EBiomes.TUNDRA, EBiomes.FIR_THICKET, EBiomes.ALPINE_PLAINS, EBiomes.ALPINE_GROVE,
                        EBiomes.STEPPE, EBiomes.PINE_BARRENS, EBiomes.RAINFOREST,
                        EBiomes.HIGHLANDS, EBiomes.HOWLING_PEAKS, EBiomes.COLD_DESERT,
                        EBiomes.CHERRY_FIELDS, EBiomes.OLD_GROWTH_FOREST, EBiomes.OAK_WOODLAND,
                        EBiomes.PRAIRIE, EBiomes.SHRUBLAND, EBiomes.DAPPLED_CLEARING,
                        EBiomes.ARID_SHRUBLAND, EBiomes.CEDAR_FOREST, EBiomes.ROCKY_JUNGLE,
                        EBiomes.ROCKY_DESERT, EBiomes.DRYLAND, EBiomes.OUTBACK, EBiomes.DUNES, EBiomes.LUSH_DUNES, EBiomes.CLOUD_FOREST,
                        EBiomes.BOG, EBiomes.MARSH, EBiomes.SALT_FLATS, EBiomes.TROPICAL_BASIN, EBiomes.DESERT_WETLAND
                )
                .addTag(BiomeTags.IS_RIVER).addTag(BiomeTags.IS_BEACH).addTag(BiomeTags.IS_CAVE);
    }
}