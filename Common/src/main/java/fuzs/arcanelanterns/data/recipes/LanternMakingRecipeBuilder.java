package fuzs.arcanelanterns.data.recipes;

import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
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
import org.jetbrains.annotations.Nullable;

public class LanternMakingRecipeBuilder extends ShapelessRecipeBuilder {

    private LanternMakingRecipeBuilder(HolderGetter<Item> items, ItemStack result) {
        super(items, RecipeCategory.MISC, result);
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
        super.save(new RecipeOutput() {
            @Override
            public void accept(ResourceKey<Recipe<?>> key, Recipe<?> recipe, @Nullable AdvancementHolder advancement) {
                recipeOutput.accept(key, new LanternMakingRecipe((ShapelessRecipe) recipe), advancement);
            }

            @Override
            public Advancement.Builder advancement() {
                return recipeOutput.advancement();
            }

            @Override
            public void includeRootAdvancement() {
                // NO-OP
            }
        }, resourceKey);
    }

    public static LanternMakingRecipeBuilder recipe(HolderGetter<Item> items, ItemLike result) {
        return recipe(items, result, 1);
    }

    public static LanternMakingRecipeBuilder recipe(HolderGetter<Item> items, ItemLike result, int count) {
        return new LanternMakingRecipeBuilder(items, result.asItem().getDefaultInstance().copyWithCount(count));
    }
}
