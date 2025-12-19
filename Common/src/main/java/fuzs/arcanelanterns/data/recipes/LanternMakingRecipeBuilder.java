package fuzs.arcanelanterns.data.recipes;

import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import fuzs.puzzleslib.api.data.v2.recipes.TransformingRecipeOutput;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;

public class LanternMakingRecipeBuilder extends ShapelessRecipeBuilder {

    private LanternMakingRecipeBuilder(HolderGetter<Item> items, ItemStack result) {
        super(items, RecipeCategory.MISC, result);
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
        super.save(TransformingRecipeOutput.transformed(recipeOutput, (Recipe<?> recipe) -> {
            return new LanternMakingRecipe((ShapelessRecipe) recipe);
        }), resourceKey);
    }

    public static LanternMakingRecipeBuilder recipe(HolderGetter<Item> items, ItemLike result) {
        return recipe(items, result, 1);
    }

    public static LanternMakingRecipeBuilder recipe(HolderGetter<Item> items, ItemLike result, int count) {
        return new LanternMakingRecipeBuilder(items, result.asItem().getDefaultInstance().copyWithCount(count));
    }
}
