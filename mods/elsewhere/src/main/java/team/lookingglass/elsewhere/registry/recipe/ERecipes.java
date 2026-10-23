package team.lookingglass.elsewhere.registry.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.recipe.type.GlowTrimSmithingRecipe;

public class ERecipes {

    public static void initialize() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
                Identifier.fromNamespaceAndPath(Elsewhere.MODID, "glow_trim_smithing"),
                GlowTrimSmithingRecipe.SERIALIZER);
    }
}
