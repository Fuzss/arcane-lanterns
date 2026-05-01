package fuzs.arcanelanterns.world.item.crafting;

import com.mojang.serialization.MapCodec;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;
import java.util.function.Function;

public class LanternMakingRecipe extends ShapelessRecipe {
    public static final StreamCodec<RegistryFriendlyByteBuf, LanternMakingRecipe> STREAM_CODEC = ShapelessRecipe.STREAM_CODEC.map(
            LanternMakingRecipe::new,
            Function.identity());
    public static final MapCodec<LanternMakingRecipe> MAP_CODEC = ShapelessRecipe.MAP_CODEC.xmap(LanternMakingRecipe::new,
            Function.identity());
    public static final RecipeSerializer<LanternMakingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC,
            STREAM_CODEC);

    public LanternMakingRecipe(ShapelessRecipe recipe) {
        this(recipe.commonInfo, recipe.bookInfo, recipe.result, recipe.ingredients);
    }

    public LanternMakingRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo, ItemStackTemplate result, List<Ingredient> ingredients) {
        super(commonInfo, bookInfo, result, ingredients);
    }

    @Override
    public RecipeType<CraftingRecipe> getType() {
        return (RecipeType<CraftingRecipe>) (RecipeType<?>) ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value();
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer<ShapelessRecipe>) (RecipeSerializer<?>) ModRegistry.LANTERN_MAKING_RECIPE_SERIALIZER.value();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(new ShapelessCraftingRecipeDisplay(this.placementInfo()
                .ingredients()
                .stream()
                .map(Ingredient::display)
                .toList(),
                new SlotDisplay.ItemStackSlotDisplay(this.result),
                new SlotDisplay.ItemSlotDisplay(ModRegistry.LANTERN_MAKER_ITEM.value())));
    }
}
