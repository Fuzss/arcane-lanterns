package fuzs.arcanelanterns.world.item.crafting;

import com.mojang.serialization.Codec;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.function.Function;

public class LanternMakingRecipe extends ShapelessRecipe {

    public LanternMakingRecipe(ShapelessRecipe recipe) {
        this(recipe.getGroup(), recipe.getResultItem(null), recipe.getIngredients());
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
        public static final Codec<LanternMakingRecipe> CODEC = RecipeSerializer.SHAPELESS_RECIPE.codec()
                .xmap(LanternMakingRecipe::new, Function.identity());

        @Override
        public Codec<LanternMakingRecipe> codec() {
            return CODEC;
        }

        @Override
        public LanternMakingRecipe fromNetwork(FriendlyByteBuf buffer) {
            String string = buffer.readUtf();
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonNullList = NonNullList.withSize(i, Ingredient.EMPTY);

            nonNullList.replaceAll($ -> Ingredient.fromNetwork(buffer));

            ItemStack itemStack = buffer.readItem();
            return new LanternMakingRecipe(string, itemStack, nonNullList);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, LanternMakingRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.getResultItem(null));
        }
    }
}
