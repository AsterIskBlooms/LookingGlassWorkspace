package team.lookingglass.mirrorlib.trim;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * One declaration per mod. Drives material registration, atlas, equipment client info,
 * item models, and the runtime trim-case manifest.
 *
 * <p>
 * There are two kinds of armor:
 * <ul>
 *   <li>{@link OwnedArmor}: armor this mod defines. The kit generates everything for it.</li>
 *   <li>{@link TrimTarget}: armor owned by someone else (vanilla, other mods). The kit only adds its own trims to it.</li>
 * </ul>
 */
public final class TrimKit {
    private final String modId;
    private final List<Trim> trims;
    private final List<OwnedArmor> ownedArmors;
    private final List<TrimTarget> targets;

    /** {@code darkerFor} lists the equipment assets (vanilla or modded) on which this material uses the {@code _darker} palette. */
    public record Trim(ResourceKey<TrimMaterial> key, int color, Set<ResourceKey<EquipmentAsset>> darkerFor) {
        public String modId() { return key.identifier().getNamespace(); }
        public String name() { return key.identifier().getPath(); }

        /** Atlas permutation key / sprite suffix. Namespaced so mods can't collide. */
        public String permutation(boolean darker) {
            return modId() + "_" + name() + (darker ? "_darker" : "");
        }

        /** Palette texture: {@code <modid>:trim/<name>[_darker]}. */
        public Identifier palette(boolean darker) {
            return Identifier.fromNamespaceAndPath(modId(), "trim/" + name() + (darker ? "_darker" : ""));
        }

        public String suffixFor(ResourceKey<EquipmentAsset> asset) {
            return permutation(darkerFor.contains(asset));
        }

        public boolean hasDarker() { return !darkerFor.isEmpty(); }
    }

    /** Armor defined by this mod. */
    public record OwnedArmor(ResourceKey<EquipmentAsset> asset, Identifier layer) {}

    /** An armor item (any namespace) that receives this kit's trims. */
    public record TrimTarget(Identifier itemId, Identifier slotPrefix,
                             ResourceKey<EquipmentAsset> asset, boolean dyed) {}

    private TrimKit(String modId, List<Trim> trims, List<OwnedArmor> owned, List<TrimTarget> targets) {
        this.modId = modId;
        this.trims = List.copyOf(trims);
        this.ownedArmors = List.copyOf(owned);
        this.targets = List.copyOf(targets);
    }

    public String modId() { return modId; }
    public List<Trim> trims() { return trims; }
    public List<OwnedArmor> ownedArmors() { return ownedArmors; }
    public List<TrimTarget> targets() { return targets; }

    public static ResourceKey<TrimMaterial> trimKey(String modId, String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(modId, name));
    }

    public static ResourceKey<EquipmentAsset> armorKey(String modId, String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(modId, name));
    }

    /**
     * Model id for a trimmed variant of a target item. Always lives in THIS mod's namespace.
     *
     * <p>
     * Examples:
     * <ul>
     *   <li>{@code minecraft:iron_chestplate} becomes {@code <mod>:item/iron_chestplate_<mod>_<trim>_trim}</li>
     *   <li>{@code other:foo_helmet} becomes {@code <mod>:item/other/foo_helmet_<mod>_<trim>_trim}</li>
     * </ul>
     */
    public Identifier targetModelId(TrimTarget target, Trim trim) {
        String ns = target.itemId().getNamespace();
        String dir = ns.equals("minecraft") ? "item/" : "item/" + ns + "/";
        return Identifier.fromNamespaceAndPath(modId,
                dir + target.itemId().getPath() + "_" + trim.suffixFor(target.asset()) + "_trim");
    }

    public void bootstrapMaterials(BootstrapContext<TrimMaterial> ctx) {
        for (Trim t : trims) {
            Component name = Component.translatable("trim_material." + t.modId() + "." + t.name())
                    .withStyle(s -> s.withColor(TextColor.fromRgb(t.color())));
            ctx.register(t.key(), new TrimMaterial(t.palette(false), name));
        }
    }

    public void bootstrapEquipmentAssets(BootstrapContext<EquipmentAsset> ctx) {
        for (OwnedArmor a : ownedArmors) ctx.register(a.asset(), new EquipmentAsset());
    }

    public static Builder builder(String modId) { return new Builder(modId); }

    public static final class Builder {
        private final String modId;
        private final List<Trim> trims = new ArrayList<>();
        private final List<OwnedArmor> owned = new ArrayList<>();
        private final List<TrimTarget> targets = new ArrayList<>();

        private Builder(String modId) { this.modId = modId; }

        @SafeVarargs
        public final Builder trim(ResourceKey<TrimMaterial> key, int rgb, ResourceKey<EquipmentAsset>... darkerFor) {
            trims.add(new Trim(key, rgb, Set.of(darkerFor)));
            return this;
        }

        /** Humanoid layer id defaults to {@code <modid>:<asset path>}. */
        public Builder ownedArmor(ResourceKey<EquipmentAsset> asset) {
            owned.add(new OwnedArmor(asset, Identifier.fromNamespaceAndPath(modId, asset.identifier().getPath())));
            return this;
        }

        public Builder target(Identifier itemId, Identifier slotPrefix, ResourceKey<EquipmentAsset> asset, boolean dyed) {
            targets.add(new TrimTarget(itemId, slotPrefix, asset, dyed));
            return this;
        }

        /** Registers {@code <ns>:<material>_helmet}, {@code _chestplate}, {@code _leggings} and {@code _boots} as targets. */
        public Builder targetSet(String ns, String material, ResourceKey<EquipmentAsset> asset, boolean dyed) {
            target(Identifier.fromNamespaceAndPath(ns, material + "_helmet"), ItemModelGenerators.TRIM_PREFIX_HELMET, asset, dyed);
            target(Identifier.fromNamespaceAndPath(ns, material + "_chestplate"), ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, asset, dyed);
            target(Identifier.fromNamespaceAndPath(ns, material + "_leggings"), ItemModelGenerators.TRIM_PREFIX_LEGGINGS, asset, dyed);
            target(Identifier.fromNamespaceAndPath(ns, material + "_boots"), ItemModelGenerators.TRIM_PREFIX_BOOTS, asset, dyed);
            return this;
        }

        public TrimKit build() { return new TrimKit(modId, trims, owned, targets); }
    }
}