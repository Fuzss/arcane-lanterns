package fuzs.arcanelanterns.world.item.crafting;

import com.mojang.serialization.MapCodec;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;
import java.util.function.Function;

public class LanternMakingRecipe extends ShapelessRecipe {

    public LanternMakingRecipe(ShapelessRecipe recipe) {
        this(recipe.group(),
                recipe.category(),
                recipe.assemble(CraftingInput.EMPTY, RegistryAccess.EMPTY),
                recipe.placementInfo().ingredients());
    }

    public LanternMakingRecipe(String group, CraftingBookCategory category, ItemStack result, List<Ingredient> ingredients) {
        super(group, category, result, ingredients);
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
                new SlotDisplay.ItemStackSlotDisplay(this.assemble(CraftingInput.EMPTY, RegistryAccess.EMPTY)),
                new SlotDisplay.ItemSlotDisplay(ModRegistry.LANTERN_MAKER_ITEM.value())));
    }

    public static class Serializer implements RecipeSerializer<LanternMakingRecipe> {

        @Override
        public MapCodec<LanternMakingRecipe> codec() {
            return RecipeSerializer.SHAPELESS_RECIPE.codec().xmap(LanternMakingRecipe::new, Function.identity());
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, LanternMakingRecipe> streamCodec() {
            return RecipeSerializer.SHAPELESS_RECIPE.streamCodec().map(LanternMakingRecipe::new, Function.identity());
        }
    }
}
