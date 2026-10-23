package team.lookingglass.elsewhere.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.entity.mob.creature.Perch;
import team.lookingglass.elsewhere.entity.mob.monster.*;
import team.lookingglass.elsewhere.registry.EItems;

import java.util.function.Supplier;

public interface EEntities {
    ResourceKey<EntityType<?>> PERCH_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "perch"));
    EntityType<Perch> PERCH = Registry.register(BuiltInRegistries.ENTITY_TYPE, PERCH_KEY,
            EntityType.Builder.of(Perch::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4F, 0.4F)
                    .build(PERCH_KEY)
    );

    ResourceKey<EntityType<?>> WEREWOLF_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "werewolf"));
    EntityType<Werewolf> WEREWOLF = Registry.register(BuiltInRegistries.ENTITY_TYPE, WEREWOLF_KEY,
            EntityType.Builder.of(Werewolf::new, MobCategory.MONSTER)
                    .sized(1.2F, 2.95F)
                    .clientTrackingRange(8)
                    .build(WEREWOLF_KEY)
    );

    ResourceKey<EntityType<?>> VANGUARD_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "vanguard"));
    EntityType<Vanguard> VANGUARD = Registry.register(BuiltInRegistries.ENTITY_TYPE, VANGUARD_KEY,
            EntityType.Builder.of(Vanguard::new, MobCategory.MONSTER)
                    .sized(0.95F, 2.15F)
                    .clientTrackingRange(8)
                    .build(VANGUARD_KEY)
    );

    ResourceKey<EntityType<?>> SUBSLIME_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "subslime"));
    EntityType<Subslime> SUBSLIME = Registry.register(BuiltInRegistries.ENTITY_TYPE, SUBSLIME_KEY,
            EntityType.Builder.of(Subslime::new, MobCategory.MONSTER)
                    .sized(0.5625F, 0.5625F)
                    .clientTrackingRange(8)
                    .build(SUBSLIME_KEY)
    );

    ResourceKey<EntityType<?>> SWEEPER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "sweeper"));
    EntityType<Sweeper> SWEEPER = Registry.register(BuiltInRegistries.ENTITY_TYPE, SWEEPER_KEY,
            EntityType.Builder.of(Sweeper::new, MobCategory.MONSTER)
                    .sized(0.75F, 2.4F)
                    .clientTrackingRange(8)
                    .build(SWEEPER_KEY)
    );

    ResourceKey<EntityType<?>> MUD_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mud_golem"));
    EntityType<MudGolem> MUD_GOLEM = Registry.register(BuiltInRegistries.ENTITY_TYPE, MUD_GOLEM_KEY,
            EntityType.Builder.of(MudGolem::new, MobCategory.MONSTER)
                    .sized(1.4F, 2.6F)
                    .clientTrackingRange(8)
                    .build(MUD_GOLEM_KEY)
    );

    ResourceKey<EntityType<?>> LOST_SOUL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "lost_soul"));
    EntityType<LostSoul> LOST_SOUL = Registry.register(BuiltInRegistries.ENTITY_TYPE, LOST_SOUL_KEY,
            EntityType.Builder.of(LostSoul::new, MobCategory.MONSTER)
                    .sized(0.35F, 0.55F)
                    .clientTrackingRange(8)
                    .build(LOST_SOUL_KEY)
    );

    ResourceKey<EntityType<?>> TROLL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "troll"));
    EntityType<Troll> TROLL = Registry.register(BuiltInRegistries.ENTITY_TYPE, TROLL_KEY,
            EntityType.Builder.of(Troll::new, MobCategory.MONSTER)
                    .sized(0.75F, 1.6F)
                    .clientTrackingRange(8)
                    .build(TROLL_KEY)
    );

    ResourceKey<EntityType<?>> GRAVEKEEPER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "gravekeeper"));
    EntityType<Gravekeeper> GRAVEKEEPER = Registry.register(BuiltInRegistries.ENTITY_TYPE, GRAVEKEEPER_KEY,
            EntityType.Builder.of(Gravekeeper::new, MobCategory.MONSTER)
                    .sized(0.7F, 1.8F)
                    .clientTrackingRange(8)
                    .build(GRAVEKEEPER_KEY)
    );

    ResourceKey<EntityType<?>> WHISTLER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "whistler"));
    EntityType<Whistler> WHISTLER = Registry.register(BuiltInRegistries.ENTITY_TYPE, WHISTLER_KEY,
            EntityType.Builder.of(Whistler::new, MobCategory.MONSTER)
                    .sized(0.6F, 2.0F)
                    .clientTrackingRange(8)
                    .build(WHISTLER_KEY)
    );


    // Boats
    ResourceKey<EntityType<?>> AZALEA_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "azalea_boat"));
    ResourceKey<EntityType<?>> AZALEA_CHEST_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "azalea_chest_boat"));
    ResourceKey<EntityType<?>> CEDAR_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cedar_boat"));
    ResourceKey<EntityType<?>> CEDAR_CHEST_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "cedar_chest_boat"));
    ResourceKey<EntityType<?>> MAHOGANY_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mahogany_boat"));
    ResourceKey<EntityType<?>> MAHOGANY_CHEST_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "mahogany_chest_boat"));

    EntityType<Boat> AZALEA_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, AZALEA_BOAT_KEY, EntityType.Builder.of(boatFactory(() -> EItems.AZALEA_BOAT), MobCategory.MISC)
            .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(AZALEA_BOAT_KEY));
    EntityType<ChestBoat> AZALEA_CHEST_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, AZALEA_CHEST_BOAT_KEY, EntityType.Builder.of(chestBoatFactory(() -> EItems.AZALEA_CHEST_BOAT), MobCategory.MISC)
            .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(AZALEA_CHEST_BOAT_KEY));

    EntityType<Boat> CEDAR_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, CEDAR_BOAT_KEY, EntityType.Builder.of(boatFactory(() -> EItems.CEDAR_BOAT), MobCategory.MISC)
            .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(CEDAR_BOAT_KEY));
    EntityType<ChestBoat> CEDAR_CHEST_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, CEDAR_CHEST_BOAT_KEY, EntityType.Builder.of(chestBoatFactory(() -> EItems.CEDAR_CHEST_BOAT), MobCategory.MISC)
            .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(CEDAR_CHEST_BOAT_KEY));

    EntityType<Boat> MAHOGANY_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, MAHOGANY_BOAT_KEY, EntityType.Builder.of(boatFactory(() -> EItems.MAHOGANY_BOAT), MobCategory.MISC)
            .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(MAHOGANY_BOAT_KEY));
    EntityType<ChestBoat> MAHOGANY_CHEST_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, MAHOGANY_CHEST_BOAT_KEY, EntityType.Builder.of(chestBoatFactory(() -> EItems.MAHOGANY_CHEST_BOAT), MobCategory.MISC)
            .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(MAHOGANY_CHEST_BOAT_KEY));

    @SuppressWarnings("DataFlowIssue")
    static void initialize() {
        FabricDefaultAttributeRegistry.register(PERCH, Perch.createAttributes());
        SpawnPlacements.register(PERCH, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Perch::checkMobSpawnRules);

        FabricDefaultAttributeRegistry.register(WEREWOLF, Werewolf.createAttributes());
        SpawnPlacements.register(WEREWOLF, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Werewolf::checkWerewolfSpawnRules);

        FabricDefaultAttributeRegistry.register(VANGUARD, Vanguard.createAttributes());
        SpawnPlacements.register(VANGUARD, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Vanguard::checkVanguardSpawnRules);

        FabricDefaultAttributeRegistry.register(SUBSLIME, Subslime.createAttributes());
        SpawnPlacements.register(SUBSLIME, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Subslime::checkSubslimeSpawnRules);

        FabricDefaultAttributeRegistry.register(SWEEPER, Sweeper.createAttributes());
        SpawnPlacements.register(SWEEPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Sweeper::checkMonsterSpawnRules);

        FabricDefaultAttributeRegistry.register(MUD_GOLEM, MudGolem.createAttributes());
        SpawnPlacements.register(MUD_GOLEM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MudGolem::checkMudGolemSpawnRules);

        FabricDefaultAttributeRegistry.register(LOST_SOUL, LostSoul.createAttributes());
        SpawnPlacements.register(LOST_SOUL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LostSoul::checkMonsterSpawnRules);

        FabricDefaultAttributeRegistry.register(TROLL, Troll.createAttributes());
        SpawnPlacements.register(TROLL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Troll::checkMonsterSpawnRules);

        FabricDefaultAttributeRegistry.register(GRAVEKEEPER, Gravekeeper.createAttributes());
        SpawnPlacements.register(GRAVEKEEPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Gravekeeper::checkMonsterSpawnRules);

        FabricDefaultAttributeRegistry.register(WHISTLER, Whistler.createAttributes());
        SpawnPlacements.register(WHISTLER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Whistler::checkWhistlerSpawnRules);
    }

    private static EntityType.EntityFactory<Boat> boatFactory(final Supplier<Item> boatItem) {
        return (entityType, level) -> new Boat(entityType, level, boatItem);
    }
    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(final Supplier<Item> boatItem) {
        return (entityType, level) -> new ChestBoat(entityType, level, boatItem);
    }
}
