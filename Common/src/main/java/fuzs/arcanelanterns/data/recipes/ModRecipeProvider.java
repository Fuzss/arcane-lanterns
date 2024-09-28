package fuzs.arcanelanterns.data.recipes;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModRegistry.LANTERN_MAKER_BLOCK.value())
                .define('X', Items.DIAMOND)
                .define('#', Items.POLISHED_BASALT)
                .pattern("#X#")
                .pattern(" # ")
                .pattern("###")
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.LIFE_LANTERN_BLOCK.value())
                .requires(Items.EGG)
                .requires(Items.MELON)
                .requires(Items.GOLDEN_APPLE)
                .requires(Items.SUGAR_CANE)
                .requires(Items.BONE_MEAL)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.FERAL_LANTERN_BLOCK.value())
                .requires(Items.GLOWSTONE)
                .requires(Items.JACK_O_LANTERN)
                .requires(Items.FIRE_CHARGE)
                .requires(Items.BLAZE_POWDER)
                .requires(Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.LOVE_LANTERN_BLOCK.value())
                .requires(Items.DIAMOND)
                .requires(Items.RABBIT_FOOT)
                .requires(Items.GOLDEN_CARROT)
                .requires(Items.BEETROOT)
                .requires(ModRegistry.LIFE_LANTERN_BLOCK.value())
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.WAILING_LANTERN_BLOCK.value())
                .requires(Items.GHAST_TEAR)
                .requires(Items.WARPED_ROOTS)
                .requires(Items.FLINT)
                .requires(Items.PUFFERFISH)
                .requires(Items.INK_SAC)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.BOREAL_LANTERN_BLOCK.value())
                .requires(Items.COBWEB)
                .requires(Items.SNOWBALL)
                .requires(Items.PACKED_ICE)
                .requires(Items.QUARTZ)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.BRILLIANT_LANTERN_BLOCK.value())
                .requires(Items.SHULKER_SHELL)
                .requires(Items.PAPER)
                .requires(Items.SNOWBALL)
                .requires(Items.PHANTOM_MEMBRANE)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.WARDING_LANTERN_BLOCK.value())
                .requires(Items.WARPED_FUNGUS)
                .requires(Items.PUFFERFISH)
                .requires(Items.IRON_DOOR)
                .requires(Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.CONTAINING_LANTERN_BLOCK.value())
                .requires(ModRegistry.WARDING_LANTERN_BLOCK.value())
                .requires(Items.FISHING_ROD)
                .requires(Items.COBWEB)
                .requires(Items.CHAIN)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.WITHERING_LANTERN_BLOCK.value())
                .requires(Items.WITHER_ROSE)
                .requires(Items.SOUL_SAND)
                .requires(Items.FIREWORK_STAR)
                .requires(Items.COAL)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
        LanternMakingRecipeBuilder.recipe(ModRegistry.CLOUD_LANTERN_BLOCK.value())
                .requires(Items.PHANTOM_MEMBRANE)
                .requires(Items.SOUL_TORCH)
                .requires(Items.SNOW_BLOCK)
                .requires(Items.WHITE_WOOL)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(recipeOutput);
    }
}
