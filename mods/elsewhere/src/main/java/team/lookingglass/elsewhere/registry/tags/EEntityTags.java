package team.lookingglass.elsewhere.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import team.lookingglass.elsewhere.Elsewhere;

public interface EEntityTags {

    TagKey<EntityType<?>> POSSESSABLE = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "possessable"));

    static void initialize() {}
}