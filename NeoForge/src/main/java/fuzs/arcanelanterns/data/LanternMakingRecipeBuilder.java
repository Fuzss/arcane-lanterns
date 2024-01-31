package fuzs.arcanelanterns.data;

import com.google.common.collect.Lists;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

class LanternMakingRecipeBuilder extends ShapelessRecipeBuilder {
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;

    public LanternMakingRecipeBuilder(ItemLike result, int count) {
        super(RecipeCategory.MISC, result, count);
        this.result = result.asItem();
        this.count = count;
    }

    public static LanternMakingRecipeBuilder recipe(ItemLike result) {
        return new LanternMakingRecipeBuilder(result, 1);
    }

    public static LanternMakingRecipeBuilder recipe(ItemLike result, int count) {
        return new LanternMakingRecipeBuilder(result, count);
    }

    @Override
    public ShapelessRecipeBuilder requires(Ingredient ingredient, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    public ShapelessRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
        this.advancement.addCriterion(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public ShapelessRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> finishedRecipeConsumer, ResourceLocation recipeId) {
        this.ensureValid(recipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(recipeId)).requirements(RequirementsStrategy.OR);
        finishedRecipeConsumer.accept(new Result(recipeId, this.result, this.count, this.group == null ? "" : this.group, this.ingredients, this.advancement, new ResourceLocation(recipeId.getNamespace(), "recipes/" + RecipeCategory.MISC.getFolderName() + "/" + recipeId.getPath())));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    static class Result extends ShapelessRecipeBuilder.Result {

        public Result(ResourceLocation id, Item result, int count, String group, List<Ingredient> ingredients, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, result, count, group, CraftingBookCategory.MISC, ingredients, advancement, advancementId);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRegistry.LANTERN_MAKING_RECIPE_SERIALIZER.get();
        }
    }
}
