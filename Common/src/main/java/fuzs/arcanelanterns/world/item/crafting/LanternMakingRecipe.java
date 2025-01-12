package fuzs.arcanelanterns.world.item.crafting;

import com.mojang.serialization.MapCodec;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.function.Function;

public class LanternMakingRecipe extends ShapelessRecipe {

    public LanternMakingRecipe(ShapelessRecipe recipe) {
        this(recipe.getGroup(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.getIngredients());
    }

    public LanternMakingRecipe(String string, ItemStack itemStack, NonNullList<Ingredient> nonNullList) {
        super(string, CraftingBookCategory.MISC, itemStack, nonNullList);
    }

    @Override
    public RecipeType<?> getType() {
        return ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRegistry.LANTERN_MAKING_RECIPE_SERIALIZER.value();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getToastSymbol() {
        return ModRegistry.LANTERN_MAKER_ITEM.value().getDefaultInstance();
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
