package team.lookingglass.elsewhere.worldgen.features.modifiers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;

public interface EPlacementModifiers {
    MapCodec<SeaLevelFilter> SEA_LEVEL_FILTER = Registry.register(
            BuiltInRegistries.PLACEMENT_MODIFIER_TYPE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "sea_level_filter"),
            SeaLevelFilter.CODEC);

    static void initialize() {}
}