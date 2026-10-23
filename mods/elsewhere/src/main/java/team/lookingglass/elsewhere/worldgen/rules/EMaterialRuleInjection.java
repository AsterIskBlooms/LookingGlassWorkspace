package team.lookingglass.elsewhere.worldgen.rules;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import team.lookingglass.mirrorlib.mixin.NoiseGeneratorSettingsAccessor;

public class EMaterialRuleInjection {

    public static void initialize() {
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            if (client) return;

            patch(registries, "overworld", true);
            patch(registries, "nether", false);
        });
    }

    private static void patch(HolderLookup.Provider registries, String name, boolean overworld) {
        Identifier id = Identifier.withDefaultNamespace(name);

        HolderGetter<Biome> biomes = registries.lookupOrThrow(Registries.BIOME);
        HolderGetter<MaterialCondition> conditions = registries.lookupOrThrow(Registries.MATERIAL_CONDITION);
        HolderGetter<MaterialRule> materialRules = registries.lookupOrThrow(Registries.MATERIAL_RULE);

        NoiseGeneratorSettings settings = registries.lookupOrThrow(Registries.NOISE_SETTINGS)
                .getOrThrow(ResourceKey.create(Registries.NOISE_SETTINGS, id)).value();

        MaterialRule existing = MaterialRules.getRule(materialRules, ResourceKey.create(Registries.MATERIAL_RULE, id));

        MaterialRule wrapped = overworld
                ? MaterialRules.sequence(EMaterialRules.buildOverworld(biomes, conditions), existing)
                : MaterialRules.sequence(EMaterialRules.buildNether(biomes, conditions), existing);

        ((NoiseGeneratorSettingsAccessor) (Object) settings).setMaterialRule(Holder.direct(wrapped));
    }
}