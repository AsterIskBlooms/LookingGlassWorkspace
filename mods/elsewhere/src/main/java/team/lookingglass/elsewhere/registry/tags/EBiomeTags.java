package team.lookingglass.elsewhere.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import team.lookingglass.elsewhere.Elsewhere;

public interface EBiomeTags {

    TagKey<Biome> HAS_TRIAL_ROOM = create("has_trial_room");

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    static void initialize() {}
}
