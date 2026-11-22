package team.lookingglass.elsewhere.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import team.lookingglass.elsewhere.Elsewhere;

public interface EDataComponents {
    DataComponentType<Boolean> GLOWING_TRIM = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID, "glowing_trim"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());

    static void initialize() {
        DefaultItemComponentEvents.MODIFY.register(context -> context.modify(Items.CROSSBOW, builder ->
                builder.set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath(Elsewhere.MODID, "crossbow"))));
    }
}