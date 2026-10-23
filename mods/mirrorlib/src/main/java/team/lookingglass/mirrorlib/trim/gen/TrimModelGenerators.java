package team.lookingglass.mirrorlib.trim.gen;

import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import team.lookingglass.mirrorlib.trim.TrimKit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class TrimModelGenerators extends ItemModelGenerators {
    private static final int DYE_DEFAULT = -6265536;

    public TrimModelGenerators(ItemModelOutput out, BiConsumer<Identifier, ModelInstance> models) {
        super(out, models);
    }

    public final void generateTrimmableArmorSet(TrimKit kit, ResourceKey<EquipmentAsset> asset,
                                                Item helmet, Item chestplate, Item leggings, Item boots, boolean dyed) {
        generateTrimmableArmor(kit, asset, helmet, TRIM_PREFIX_HELMET, dyed);
        generateTrimmableArmor(kit, asset, chestplate, TRIM_PREFIX_CHESTPLATE, dyed);
        generateTrimmableArmor(kit, asset, leggings, TRIM_PREFIX_LEGGINGS, dyed);
        generateTrimmableArmor(kit, asset, boots, TRIM_PREFIX_BOOTS, dyed);
    }

    /** Full item definition for an item this mod owns: vanilla trims + this kit's trims. */
    public final void generateTrimmableArmor(TrimKit kit, ResourceKey<EquipmentAsset> asset,
                                             Item armor, Identifier slotPrefix, boolean dyed) {
        Identifier model = ModelLocationUtils.getModelLocation(armor);
        Material item = TextureMapping.getItemTexture(armor);
        Material overlay = TextureMapping.getItemTexture(armor, "_overlay");

        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases = new ArrayList<>();
        for (TrimMaterialData v : TRIM_MATERIAL_MODELS) {
            cases.add(trimCase(v.materialKey(), v.palette().suffix(), model, item, overlay, slotPrefix, dyed));
        }
        for (TrimKit.Trim t : kit.trims()) {
            cases.add(trimCase(t.key(), t.suffixFor(asset), model, item, overlay, slotPrefix, dyed));
        }
        ItemModel.Unbaked base = untrimmed(model, item, overlay, dyed);
        this.itemModelOutput.accept(armor, ItemModelUtils.select(new TrimMaterialProperty(), base, cases));
    }

    /**
     * Generates ONLY the models for this kit's trims on target items.
     *
     * <p>
     * Deliberately never calls {@code itemModelOutput.accept(...)}, so no {@code items/<target>.json} is written.
     *
     * <p>
     * The runtime patcher adds the select cases.
     */
    public final void generateTargetTrimModels(TrimKit kit) {
        for (TrimKit.TrimTarget target : kit.targets()) {
            Identifier texId = target.itemId().withPrefix("item/");
            Material item = new Material(texId);
            Material overlay = new Material(texId.withSuffix("_overlay"));
            for (TrimKit.Trim t : kit.trims()) {
                Identifier model = kit.targetModelId(target, t);
                Material trimOverlay = new Material(target.slotPrefix().withSuffix("_" + t.suffixFor(target.asset())));
                if (target.dyed()) generateLayeredItem(model, item, overlay, trimOverlay);
                else generateLayeredItem(model, item, trimOverlay);
            }
        }
    }

    // Helpers
    private ItemModel.Unbaked untrimmed(Identifier model, Material item, Material overlay, boolean dyed) {
        if (dyed) {
            generateLayeredItem(model, item, overlay);
            return ItemModelUtils.tintedModel(model, new Dye(DYE_DEFAULT));
        }
        ModelTemplates.FLAT_ITEM.create(model, TextureMapping.layer0(item), this.modelOutput);
        return ItemModelUtils.plainModel(model);
    }

    private SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>> trimCase(
            ResourceKey<TrimMaterial> material, String suffix, Identifier model,
            Material item, Material overlay, Identifier slotPrefix, boolean dyed) {
        Identifier trimModel = model.withSuffix("_" + suffix + "_trim");
        Material trimOverlay = new Material(slotPrefix.withSuffix("_" + suffix));
        ItemModel.Unbaked unbaked;
        if (dyed) {
            generateLayeredItem(trimModel, item, overlay, trimOverlay);
            unbaked = ItemModelUtils.tintedModel(trimModel, new Dye(DYE_DEFAULT));
        } else {
            generateLayeredItem(trimModel, item, trimOverlay);
            unbaked = ItemModelUtils.plainModel(trimModel);
        }
        return ItemModelUtils.when(material, unbaked);
    }
}