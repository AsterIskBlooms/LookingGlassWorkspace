package team.lookingglass.elsewhere.registry.render.shields;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import java.util.Objects;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.render.EModelLayers;

@Environment(EnvType.CLIENT)
public class QuickShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {
    private static final SpriteId QUICK_SHIELD_BASE_NO_PATTERN =
            Sheets.SHIELD_MAPPER.apply(Identifier.fromNamespaceAndPath(Elsewhere.MODID, "quick_shield_base_nopattern"));

    private final SpriteGetter sprites;
    private final QuickShieldModel model;

    public QuickShieldSpecialRenderer(final SpriteGetter sprites, final QuickShieldModel model) {
        this.sprites = sprites;
        this.model = model;
    }

    public @Nullable DataComponentMap extractArgument(final ItemStack stack) {
        return stack.immutableComponents();
    }

    public void submit(final @Nullable DataComponentMap components, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final int overlayCoords, final boolean hasFoil, final int outlineColor) {
        BannerPatternLayers patterns = components != null ? components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY) : BannerPatternLayers.EMPTY;
        DyeColor baseColor = components != null ? components.get(DataComponents.BASE_COLOR) : null;
        boolean hasPatterns = !patterns.layers().isEmpty() || baseColor != null;
        SpriteId base = hasPatterns ? Sheets.SHIELD_BASE : QUICK_SHIELD_BASE_NO_PATTERN;
        submitNodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, lightCoords, overlayCoords, -1, base, this.sprites, outlineColor);
        if (hasPatterns) {
            BannerRenderer.submitPatterns(this.sprites, poseStack, submitNodeCollector, lightCoords, overlayCoords, this.model, Unit.INSTANCE, false, Objects.requireNonNullElse(baseColor, DyeColor.WHITE), patterns);
        }

        if (hasFoil) {
            submitNodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, RenderTypes.patternedShieldGlint(), lightCoords, overlayCoords, -1, this.sprites.get(base), 0);
        }
    }

    public void getExtents(final Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked() implements SpecialModelRenderer.Unbaked<DataComponentMap> {
        public static final Unbaked INSTANCE = new Unbaked();
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        public QuickShieldSpecialRenderer bake(final SpecialModelRenderer.BakingContext context) {
            return new QuickShieldSpecialRenderer(context.sprites(), new QuickShieldModel(context.entityModelSet().bakeLayer(EModelLayers.QUICK_SHIELD)));
        }
    }
}
