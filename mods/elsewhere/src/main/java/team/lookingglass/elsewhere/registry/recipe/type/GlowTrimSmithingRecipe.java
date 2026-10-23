package team.lookingglass.elsewhere.registry.recipe.type;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SmithingRecipeDisplay;
import team.lookingglass.elsewhere.registry.EDataComponents;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class GlowTrimSmithingRecipe extends SimpleSmithingRecipe {
    public static final MapCodec<GlowTrimSmithingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Recipe.CommonInfo.MAP_CODEC.forGetter(o -> o.info),
            Ingredient.CODEC.optionalFieldOf("template").forGetter(o -> o.template),
            Ingredient.CODEC.fieldOf("base").forGetter(o -> o.base),
            Ingredient.CODEC.fieldOf("addition").forGetter(o -> o.addition)
    ).apply(i, GlowTrimSmithingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, GlowTrimSmithingRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC, o -> o.info,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs::optional), o -> o.template,
            Ingredient.CONTENTS_STREAM_CODEC, o -> o.base,
            Ingredient.CONTENTS_STREAM_CODEC, o -> o.addition,
            GlowTrimSmithingRecipe::new);

    public static final RecipeSerializer<GlowTrimSmithingRecipe> SERIALIZER =
            new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    private final Recipe.CommonInfo info;
    private final Optional<Ingredient> template;
    private final Ingredient base;
    private final Ingredient addition;

    public GlowTrimSmithingRecipe(Recipe.CommonInfo info, Optional<Ingredient> template, Ingredient base, Ingredient addition) {
        super(info);
        this.info = info;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input) {
        ItemStack armor = input.base();
        if (!armor.has(DataComponents.TRIM) || Boolean.TRUE.equals(armor.get(EDataComponents.GLOWING_TRIM))) {
            return ItemStack.EMPTY;
        }
        ItemStack out = armor.copyWithCount(1);
        out.set(EDataComponents.GLOWING_TRIM, true);
        return out;
    }

    @Override public Optional<Ingredient> templateIngredient() { return template; }
    @Override public Ingredient baseIngredient() { return base; }
    @Override public Optional<Ingredient> additionIngredient() { return Optional.of(addition); }
    @Override public RecipeSerializer<GlowTrimSmithingRecipe> getSerializer() { return SERIALIZER; }

    @Override
    protected PlacementInfo createPlacementInfo() {
        return PlacementInfo.createFromOptionals(List.of(template, Optional.of(base), Optional.of(addition)));
    }


    @Override
    public List<RecipeDisplay> display() {
        SlotDisplay baseDisplay = base.display();
        return List.of(new SmithingRecipeDisplay(Ingredient.optionalIngredientToDisplay(template), baseDisplay, addition.display(), baseDisplay,
                new SlotDisplay.ItemSlotDisplay(Items.SMITHING_TABLE)));
    }
}
