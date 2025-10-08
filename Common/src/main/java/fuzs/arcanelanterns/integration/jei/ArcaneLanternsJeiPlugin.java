//package fuzs.arcanelanterns.integration.jei;
//
//import fuzs.arcanelanterns.ArcaneLanterns;
//import fuzs.arcanelanterns.init.ModRegistry;
//import fuzs.arcanelanterns.integration.LanternMakingRecipeHelper;
//import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
//import mezz.jei.api.IModPlugin;
//import mezz.jei.api.JeiPlugin;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import mezz.jei.api.registration.IRecipeCatalystRegistration;
//import mezz.jei.api.registration.IRecipeCategoryRegistration;
//import mezz.jei.api.registration.IRecipeRegistration;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.world.level.block.Block;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//
//@JeiPlugin
//public class ArcaneLanternsJeiPlugin implements IModPlugin {
//    static final ResourceLocation PLUGIN_ID = ArcaneLanterns.id("main");
//
//    @Nullable
//    private IRecipeCategory<RecipeHolder<LanternMakingRecipe>> lanternMakingCategory;
//
//    @Override
//    public ResourceLocation getPluginUid() {
//        return PLUGIN_ID;
//    }
//
//    @Override
//    public void registerCategories(IRecipeCategoryRegistration registration) {
//        registration.addRecipeCategories(this.lanternMakingCategory = new LanternMakingRecipeCategory(registration.getJeiHelpers()
//                .getGuiHelper()));
//    }
//
//    @Override
//    public void registerRecipes(IRecipeRegistration registration) {
//        registration.addRecipes(LanternMakingRecipeCategory.LANTERN_MAKING_RECIPE_HOLDER_TYPE,
//                this.getLanternMakingRecipes());
//        this.registerLanternItemStack(registration, ModRegistry.LANTERN_MAKER_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.LIFE_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.FERAL_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.LOVE_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.WAILING_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.BOREAL_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.BRILLIANT_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.WARDING_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.CONTAINING_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.WITHERING_LANTERN_BLOCK.value());
//        this.registerLanternItemStack(registration, ModRegistry.CLOUD_LANTERN_BLOCK.value());
//    }
//
//    private List<RecipeHolder<LanternMakingRecipe>> getLanternMakingRecipes() {
//        return this.getHandledRecipes(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value(), this.lanternMakingCategory);
//    }
//
//    private <C extends RecipeInput, T extends Recipe<C>> List<RecipeHolder<T>> getHandledRecipes(RecipeType<T> recipeType, IRecipeCategory<RecipeHolder<T>> recipeCategory) {
//        RecipeMap recipeMap = LanternMakingRecipeHelper.getRecipeMap();
//        if (recipeMap.values().isEmpty()) {
//            return Collections.emptyList();
//        } else {
//            Objects.requireNonNull(recipeCategory, "recipe category is null");
//            return recipeMap.byType(recipeType).stream().filter(recipeCategory::isHandled).toList();
//        }
//    }
//
//    private void registerLanternItemStack(IRecipeRegistration registration, Block block) {
//        registration.addItemStackInfo(new ItemStack(block),
//                Component.translatable(block.getDescriptionId() + ".description"));
//    }
//
//    @Override
//    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
//        registration.addCraftingStation(LanternMakingRecipeCategory.LANTERN_MAKING_RECIPE_HOLDER_TYPE,
//                ModRegistry.LANTERN_MAKER_BLOCK.value());
//    }
//}
