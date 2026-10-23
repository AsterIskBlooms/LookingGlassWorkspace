package team.lookingglass.elsewhere.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityTypeIds;
import org.jspecify.annotations.NonNull;
import team.lookingglass.elsewhere.entity.EEntities;
import team.lookingglass.elsewhere.registry.tags.EEntityTags;

import java.util.concurrent.CompletableFuture;

public class EntityTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public EntityTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        builder(EntityTypeTags.ZOMBIES).add(EEntities.GRAVEKEEPER_KEY);
        builder(EntityTypeTags.SKELETONS);
        builder(EntityTypeTags.UNDEAD)
                .add(EEntities.VANGUARD_KEY)
                .add(EEntities.WEREWOLF_KEY)
        ;

        builder(EEntityTags.POSSESSABLE)
                .add(EntityTypeIds.ZOMBIE).add(EntityTypeIds.HUSK).add(EntityTypeIds.DROWNED)
                .add(EntityTypeIds.SKELETON).add(EntityTypeIds.PARCHED).add(EntityTypeIds.BOGGED).add(EntityTypeIds.STRAY)
                .add(EntityTypeIds.WITHER_SKELETON)
        ;

        builder(EntityTypeTags.IMMUNE_TO_FREEZING)
                .add(EEntities.VANGUARD_KEY)
                .add(EEntities.WEREWOLF_KEY)
        ;

        builder(EntityTypeTags.AQUATIC).add(EEntities.PERCH_KEY);
        builder(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(EEntities.PERCH_KEY);
        builder(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(EEntities.PERCH_KEY);
    }
}
