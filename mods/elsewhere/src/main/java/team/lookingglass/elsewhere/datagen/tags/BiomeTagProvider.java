package team.lookingglass.elsewhere.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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
        builder(BiomeTags.IS_BEACH).add(EBiomes.TIDEPOOLS, EBiomes.COLD_BEACH, EBiomes.FROZEN_BEACH);
        builder(BiomeTags.IS_RIVER).add(EBiomes.LUKEWARM_RIVER, EBiomes.COLD_RIVER);
        builder(BiomeTags.IS_CAVE).add(
                EBiomes.CAVES,
                EBiomes.CRYSTAL_CAVERNS, EBiomes.ARID_CAVES,
                EBiomes.VOLCANIC_DEPTHS
        );

        builder(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS).add(
                EBiomes.OUTBACK, EBiomes.LUSH_DESERT, EBiomes.TIDEPOOLS,
                EBiomes.STEPPE, EBiomes.SHRUBLAND, EBiomes.CEDAR_FOREST,
                EBiomes.CLOUD_FOREST
        );
        builder(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).add(
                EBiomes.OUTBACK, EBiomes.LUSH_DESERT, EBiomes.TIDEPOOLS,
                EBiomes.STEPPE, EBiomes.SHRUBLAND, EBiomes.CEDAR_FOREST,
                EBiomes.CLOUD_FOREST
        );

        builder(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS).add(
                EBiomes.TUNDRA, EBiomes.BOG,
                EBiomes.COLD_BEACH, EBiomes.FROZEN_BEACH
        );
        builder(BiomeTags.SPAWNS_COLD_VARIANT_FROGS).add(
                EBiomes.TUNDRA, EBiomes.BOG,
                EBiomes.COLD_BEACH, EBiomes.FROZEN_BEACH
        );

        builder(BiomeTags.SPAWNS_GOLD_RABBITS).add(EBiomes.LUSH_DESERT, EBiomes.STEPPE);
        builder(BiomeTags.SPAWNS_SNOW_FOXES).add(EBiomes.TUNDRA, EBiomes.BOG, EBiomes.FROZEN_BEACH);

        builder(BiomeTags.HAS_VILLAGE_DESERT).add(EBiomes.LUSH_DESERT);
        builder(BiomeTags.HAS_VILLAGE_SAVANNA).add(EBiomes.STEPPE, EBiomes.OUTBACK, EBiomes.SHRUBLAND);
        builder(BiomeTags.HAS_VILLAGE_SNOWY).add(EBiomes.TUNDRA);
        builder(BiomeTags.HAS_PILLAGER_OUTPOST).add(
                EBiomes.LUSH_DESERT, EBiomes.STEPPE,
                EBiomes.OUTBACK, EBiomes.SHRUBLAND,
                EBiomes.TUNDRA
        );

        builder(EBiomeTags.HAS_TRIAL_ROOM).addTag(BiomeTags.IS_CAVE);

        builder(BiomeTags.HAS_ABANDONED_CAMP_SAVANNA).add(EBiomes.STEPPE, EBiomes.OUTBACK);
        builder(BiomeTags.HAS_ABANDONED_CAMP_FOREST).add(EBiomes.CEDAR_FOREST);

        builder(BiomeTags.IS_OVERWORLD).addTag(BiomeTags.IS_BEACH).addTag(BiomeTags.IS_RIVER).addTag(BiomeTags.IS_CAVE).add(
                EBiomes.TUNDRA, EBiomes.CHERRY_FIELDS, EBiomes.BOG,
                EBiomes.LUSH_DESERT, EBiomes.OUTBACK,
                EBiomes.STEPPE, EBiomes.CLOUD_FOREST,
                EBiomes.SHRUBLAND, EBiomes.CEDAR_FOREST
        );
    }
}