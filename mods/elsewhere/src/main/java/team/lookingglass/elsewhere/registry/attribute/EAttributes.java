package team.lookingglass.elsewhere.registry.attribute;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import team.lookingglass.elsewhere.Elsewhere;

public interface EAttributes {
    Holder<Attribute> LIFESTEAL = register("lifesteal", new RangedAttribute(
            translation("lifesteal"), 0.0, 0.0, 1.0).setSentiment(Attribute.Sentiment.POSITIVE));

    private static Holder<Attribute> register(final String name, final Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(Elsewhere.MODID, name), attribute);
    }
    private static String translation(final String name) {
        return "attribute." + Elsewhere.MODID + "." + name;
    }

    static void initialize() {}
}
