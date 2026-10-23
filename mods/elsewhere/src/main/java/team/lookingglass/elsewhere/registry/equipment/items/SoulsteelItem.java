package team.lookingglass.elsewhere.registry.equipment.items;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;
import java.util.WeakHashMap;

public class SoulsteelItem extends Item {
    public SoulsteelItem(Properties properties) {
        super(properties);
    }

    private static final float LIFESTEAL_RATIO = 0.15F;
    private static final Map<LivingEntity, Float> PENDING = new WeakHashMap<>();

    private static LivingEntity getSoulsteelAttacker(DamageSource source) {
        if (!(source.getEntity() instanceof LivingEntity attacker)) return null;
        if (source.getDirectEntity() != attacker) return null;
        if (!(attacker.getMainHandItem().getItem() instanceof SoulsteelItem)) return null;
        return attacker;
    }

    public static void registerEvents() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (getSoulsteelAttacker(source) != null) {
                PENDING.put(entity, Math.min(amount, entity.getHealth()));
            }
            return true;
        });

        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            PENDING.remove(entity);
            if (blocked || damageTaken <= 0.0F) return;
            LivingEntity attacker = getSoulsteelAttacker(source);
            if (attacker == null) return;

            attacker.heal(damageTaken * LIFESTEAL_RATIO);
        });

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            Float dealt = PENDING.remove(entity);
            if (dealt == null) return;
            LivingEntity attacker = getSoulsteelAttacker(source);
            if (attacker == null) return;

            attacker.heal(dealt * LIFESTEAL_RATIO);
        });
    }
}