package team.lookingglass.elsewhere.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.tags.EBiomeTags;

public interface EStructures {
    ResourceKey<Structure> TRIAL_ROOM = structure("trial_room");
    ResourceKey<Structure> CATACOMBS = structure("catacombs");

    interface PoolKeys {
        ResourceKey<StructureTemplatePool> TRIAL_ROOM_BASE = pool("trial_room/room");
        ResourceKey<StructureTemplatePool> TRIAL_ROOM_MELEE_SPAWNER = pool("trial_room/spawner/melee");
        ResourceKey<StructureTemplatePool> TRIAL_ROOM_RANGED_SPAWNER = pool("trial_room/spawner/ranged");

        ResourceKey<StructureTemplatePool> CATACOMBS_TRIAL = pool("catacombs/trial");
        ResourceKey<StructureTemplatePool> CATACOMBS_HALL = pool("catacombs/hall");
        ResourceKey<StructureTemplatePool> CATACOMBS_END = pool("catacombs/end");
    }

    interface SetKeys {
        ResourceKey<StructureSet> TRIAL_ROOM = set("trial_room");
        ResourceKey<StructureSet> CATACOMBS = set("catacombs");
    }

    static void bootstrapTemplatePools(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> empty = pools.getOrThrow(Pools.EMPTY);

        // Trial Rooms
        context.register(PoolKeys.TRIAL_ROOM_BASE, new StructureTemplatePool(empty, List.of(
                room("trial_room/room/double")
        )));
        context.register(PoolKeys.TRIAL_ROOM_MELEE_SPAWNER, new StructureTemplatePool(empty, List.of(
                room("trial_room/spawner/melee/zombie", 4),
                room("trial_room/spawner/melee/frostbite", 3),
                room("trial_room/spawner/melee/cave_spider", 1),
                room("trial_room/spawner/melee/slime", 2)
        )));
        context.register(PoolKeys.TRIAL_ROOM_RANGED_SPAWNER, new StructureTemplatePool(empty, List.of(
                room("trial_room/spawner/ranged/skeleton", 4),
                room("trial_room/spawner/ranged/stray", 3),
                room("trial_room/spawner/ranged/breeze", 1)
        )));


        // Catacombs
        Holder<StructureTemplatePool> end = context.register(PoolKeys.CATACOMBS_END, new StructureTemplatePool(
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY),
                List.of(room("catacombs/end"))
        ));
        context.register(PoolKeys.CATACOMBS_HALL, new StructureTemplatePool(end, List.of(
                room("catacombs/trial/straight1", 3),
                room("catacombs/trial/straight2", 3),
                room("catacombs/trial/drop1")
        )));
        context.register(PoolKeys.CATACOMBS_TRIAL, new StructureTemplatePool(end, List.of(
                room("catacombs/trial/staircase"),
                room("catacombs/trial/spider_basement"),
                room("catacombs/trial/double_side"),
                room("catacombs/trial/zombie_bridge")
        )));
    }

    static void bootstrapStructures(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(TRIAL_ROOM, new JigsawStructure(
                new Structure.StructureSettings.Builder(biomes.getOrThrow(EBiomeTags.HAS_TRIAL_ROOM))
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .build(),
                pools.getOrThrow(PoolKeys.TRIAL_ROOM_BASE),
                2,
                UniformHeight.of(VerticalAnchor.absolute(-48), VerticalAnchor.seaLevel()),
                false
        ));

        context.register(CATACOMBS, new JigsawStructure(
                new Structure.StructureSettings.Builder(biomes.getOrThrow(BiomeTags.IS_CAVE))
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .build(),
                pools.getOrThrow(PoolKeys.CATACOMBS_TRIAL),
                5,
                UniformHeight.of(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(128)),
                false
        ));
    }

    static void bootstrapStructureSets(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(SetKeys.TRIAL_ROOM, new StructureSet(
                structures.getOrThrow(TRIAL_ROOM),
                new RandomSpreadStructurePlacement(10, 3, RandomSpreadType.LINEAR, 14357621)
        ));

        context.register(SetKeys.CATACOMBS, new StructureSet(
                structures.getOrThrow(CATACOMBS),
                new RandomSpreadStructurePlacement(12, 4, RandomSpreadType.LINEAR, 91827364)
        ));
    }




    private static ResourceKey<Structure> structure(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    private static ResourceKey<StructureTemplatePool> pool(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    private static ResourceKey<StructureSet> set(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name));
    }

    private static Pair<StructurePoolElement, Integer> room(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Elsewhere.MODID, name);
        return Pair.of(StructurePoolElement.single(id.toString()).apply(StructureTemplatePool.Projection.RIGID), 1);
    }

    private static Pair<StructurePoolElement, Integer> room(String name, int weight) {
        Identifier id = Identifier.fromNamespaceAndPath(Elsewhere.MODID, name);
        return Pair.of(StructurePoolElement.single(id.toString()).apply(StructureTemplatePool.Projection.RIGID), weight);
    }
}