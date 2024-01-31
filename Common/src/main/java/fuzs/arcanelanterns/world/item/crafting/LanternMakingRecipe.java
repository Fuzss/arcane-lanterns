package fuzs.arcanelanterns.world.item.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fuzs.arcanelanterns.init.ModRegistry;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

/**
 * copied from {@link ShapelessRecipe} as it's basically the same, but cannot be extended for our purposes
 */
public class LanternMakingRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    final String group;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;

    public LanternMakingRecipe(ResourceLocation resourceLocation, String string, ItemStack itemStack, NonNullList<Ingredient> nonNullList) {
        this.id = resourceLocation;
        this.group = string;
        this.result = itemStack;
        this.ingredients = nonNullList;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRegistry.LANTERN_MAKING_RECIPE_TYPE.get();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRegistry.LANTERN_MAKING_RECIPE_SERIALIZER.get();
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean matches(Container inv, Level level) {
        StackedContents stackedContents = new StackedContents();
        int i = 0;

        for(int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemStack = inv.getItem(j);
            if (!itemStack.isEmpty()) {
                ++i;
                stackedContents.accountStack(itemStack, 1);
            }
        }

        return i == this.ingredients.size() && stackedContents.canCraft(this, (IntList)null);
    }

    @Override
    public ItemStack assemble(Container inv, RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }

    public static class Serializer implements RecipeSerializer<LanternMakingRecipe> {

        @Override
        public LanternMakingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String string = GsonHelper.getAsString(json, "group", "");
            NonNullList<Ingredient> nonNullList = itemsFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (nonNullList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonNullList.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
                return new LanternMakingRecipe(recipeId, string, itemStack, nonNullList);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonNullList = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonNullList.add(ingredient);
                }
            }

            return nonNullList;
        }

        @Override
        public LanternMakingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String string = buffer.readUtf();
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonNullList = NonNullList.withSize(i, Ingredient.EMPTY);

            nonNullList.replaceAll(ignored -> Ingredient.fromNetwork(buffer));

            ItemStack itemStack = buffer.readItem();
            return new LanternMakingRecipe(recipeId, string, itemStack, nonNullList);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, LanternMakingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.result);
        }
    }
}
