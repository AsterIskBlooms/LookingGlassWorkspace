package team.lookingglass.elsewhere.registry.attribute;

import net.minecraft.resources.Identifier;
import team.lookingglass.elsewhere.Elsewhere;

public interface AttributeIDs {
    Identifier SOULSTEEL_LIFESTEAL_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "soulsteel_lifesteal");
    Identifier SOULSTEEL_REACH_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "soulsteel_reach");

    Identifier ELECTRUM_HELMET_SPEED_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "electrum_helmet_speed");
    Identifier ELECTRUM_CHESTPLATE_SPEED_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "electrum_chestplate_speed");
    Identifier ELECTRUM_LEGGINGS_SPEED_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "electrum_leggings_speed");
    Identifier ELECTRUM_BOOTS_SPEED_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "electrum_boots_speed");
}
