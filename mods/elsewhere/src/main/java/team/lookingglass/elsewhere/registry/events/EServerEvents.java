package team.lookingglass.elsewhere.registry.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import team.lookingglass.elsewhere.registry.EItems;
import team.lookingglass.elsewhere.registry.equipment.items.SoulsteelItem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EServerEvents {
    private static final Map<UUID, Boolean> WEARING_MASK = new HashMap<>();
    private static final Map<UUID, Integer> MASK_TIMER = new HashMap<>();

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                boolean wearing = player.getItemBySlot(EquipmentSlot.HEAD).is(EItems.VANGUARD_MASK);
                boolean wasWearing = WEARING_MASK.getOrDefault(player.getUUID(), false);

                if (wearing && !wasWearing) {
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1, 0, true, false, false));
                } else if (!wearing && wasWearing) {
                    player.removeEffect(MobEffects.NIGHT_VISION);
                }

                if (wearing) {
                    int timer = MASK_TIMER.getOrDefault(player.getUUID(), 0) + 1;
                    if (timer >= 20) {
                        player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(1, player, EquipmentSlot.HEAD);
                        timer = 0;
                    }
                    MASK_TIMER.put(player.getUUID(), timer);
                } else {
                    MASK_TIMER.remove(player.getUUID());
                }
                WEARING_MASK.put(player.getUUID(), wearing);
            }
        });
        SoulsteelItem.registerEvents();
    }
}
