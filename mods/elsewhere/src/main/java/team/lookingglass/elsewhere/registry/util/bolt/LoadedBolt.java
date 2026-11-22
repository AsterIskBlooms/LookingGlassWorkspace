package team.lookingglass.elsewhere.registry.util.bolt;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import org.jetbrains.annotations.Nullable;
import team.lookingglass.elsewhere.registry.EItems;

@Environment(EnvType.CLIENT)
public record LoadedBolt() implements SelectItemModelProperty<String> {
    public static final Codec<String> VALUE_CODEC = Codec.STRING;
    public static final SelectItemModelProperty.Type<LoadedBolt, String> TYPE =
            SelectItemModelProperty.Type.create(MapCodec.unit(new LoadedBolt()), VALUE_CODEC);

    @Override
    public String get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity owner, int seed, ItemDisplayContext ctx) {
        ChargedProjectiles projectiles = stack.get(DataComponents.CHARGED_PROJECTILES);
        if (projectiles == null || projectiles.isEmpty()) return "none";
        if (projectiles.contains(EItems.IRON_BOLT)) return "iron_bolt";
        if (projectiles.contains(EItems.SILVER_BOLT)) return "silver_bolt";
        return "other";
    }

    @Override public SelectItemModelProperty.Type<LoadedBolt, String> type() { return TYPE; }
    @Override public Codec<String> valueCodec() { return VALUE_CODEC; }
}
