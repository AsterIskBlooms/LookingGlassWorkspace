package team.lookingglass.mirrorlib.trim;

import net.minecraft.client.color.item.Dye;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adds merged trim cases to a loaded item model.
 *
 * <p>
 * Call it from the mixin that post-processes loaded {@code ClientItem} definitions:
 * <pre>{@code
 * if (TrimCaseRegistry.hasCases(id)) {
 *     item = item.withModel(TrimItemPatcher.patch(item.model(), TrimCaseRegistry.casesFor(id)));
 * }
 * }</pre>
 */
@SuppressWarnings("unchecked")
public final class TrimItemPatcher {
    private static final int DYE_DEFAULT = -6265536;
    /**
     * Adds this kit's trim cases to an item model.
     *
     * <p>
     * The original model is wrapped in a {@code trim_material} select. Our cases are checked
     * first, and anything else, including vanilla's own trim cases, falls through to the
     * original model. This works whether or not the original already has a trim select,
     * and avoids rebuilding vanilla's select record.
     *
     * @param original the item's loaded model
     * @param extra    the merged cases for this item from {@link TrimCaseRegistry}
     * @return the patched model
     */
    public static ItemModel.Unbaked patch(ItemModel.Unbaked original, List<TrimCaseRegistry.Case> extra) {
        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> add = new ArrayList<>();
        for (TrimCaseRegistry.Case c : extra) {
            add.add(ItemModelUtils.when(
                    ResourceKey.create(Registries.TRIM_MATERIAL, c.material()),
                    c.dyed() ? ItemModelUtils.tintedModel(c.model(), new Dye(DYE_DEFAULT)) : ItemModelUtils.plainModel(c.model())));
        }
        if (original instanceof SelectItemModel.Unbaked sel
                && sel.unbakedSwitch().property() instanceof TrimMaterialProperty property) {
            List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> existing =
                    (List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>>) (List<?>) sel.unbakedSwitch().cases();
            List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases = new ArrayList<>(existing);
            Set<ResourceKey<TrimMaterial>> have = new HashSet<>();
            cases.forEach(c -> have.addAll(c.values()));
            for (var c : add) {
                if (Collections.disjoint(have, c.values())) cases.add(c);
            }
            return ItemModelUtils.select(new TrimMaterialProperty(), original, add);
        }
        return ItemModelUtils.select(new TrimMaterialProperty(), original, add);
    }

    private TrimItemPatcher() {}
}