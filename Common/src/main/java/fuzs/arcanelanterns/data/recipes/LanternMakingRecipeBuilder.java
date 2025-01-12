package fuzs.arcanelanterns.data.recipes;

import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

public class LanternMakingRecipeBuilder extends ShapelessRecipeBuilder {

    private LanternMakingRecipeBuilder(ItemLike result, int count) {
        super(RecipeCategory.MISC, result, count);
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        super.save(new RecipeOutput() {
            @Override
            public void accept(ResourceLocation location, Recipe<?> recipe, @Nullable AdvancementHolder advancement) {
                recipeOutput.accept(location, new LanternMakingRecipe((ShapelessRecipe) recipe), advancement);
            }

            @Override
            public Advancement.Builder advancement() {
                return recipeOutput.advancement();
            }
        }, id);
    }

    public static LanternMakingRecipeBuilder recipe(ItemLike result) {
        return new LanternMakingRecipeBuilder(result, 1);
    }

    public static LanternMakingRecipeBuilder recipe(ItemLike result, int count) {
        return new LanternMakingRecipeBuilder(result, count);
    }
}
