package fuzs.arcanelanterns.integration.jei;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import fuzs.puzzleslib.api.core.v1.Proxy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;

@JeiPlugin
public class ArcaneLanternsPlugin implements IModPlugin {
    static final RecipeType<LanternMakingRecipe> LANTERN_MAKING_RECIPE_TYPE = RecipeType.create(ArcaneLanterns.MOD_ID,
            "lantern_making",
            LanternMakingRecipe.class
    );

    @Override
    public ResourceLocation getPluginUid() {
        return ArcaneLanterns.id("main");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new LanternMakingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(LANTERN_MAKING_RECIPE_TYPE,
                Proxy.INSTANCE.getClientLevel()
                        .getRecipeManager()
                        .getAllRecipesFor(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value())
                        .stream()
                        .map(RecipeHolder::value)
                        .toList()
        );
        addLanternStackInfo(registration, ModRegistry.LANTERN_MAKER_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.LIFE_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.FERAL_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.LOVE_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.WAILING_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.BOREAL_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.BRILLIANT_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.WARDING_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.CONTAINING_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.WITHERING_LANTERN_BLOCK.value());
        addLanternStackInfo(registration, ModRegistry.CLOUD_LANTERN_BLOCK.value());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModRegistry.LANTERN_MAKER_BLOCK.value()),
                LANTERN_MAKING_RECIPE_TYPE
        );
    }

    private static void addLanternStackInfo(IRecipeRegistration registration, Block block) {
        registration.addItemStackInfo(new ItemStack(block), Component.translatable(block.getDescriptionId() + ".info"));
    }
}
