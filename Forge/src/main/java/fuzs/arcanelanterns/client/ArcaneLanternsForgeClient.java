package fuzs.arcanelanterns.client;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.minecraft.client.RecipeBookCategories;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = ArcaneLanterns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ArcaneLanternsForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ClientModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanternsClient::new);
    }

    @SubscribeEvent
    public static void onRegisterRecipeBookCategories(final RegisterRecipeBookCategoriesEvent evt) {
        // just skip this on Fabric, it's not very important (just prints a warning to the log) and Fabric has no api for it
        RecipeBookCategories category = RecipeBookCategories.create(ArcaneLanterns.MOD_NAME.toUpperCase(Locale.ROOT).replace(" ", "_") + "_LANTERN_MAKER");
        evt.registerRecipeCategoryFinder(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.get(), recipe -> category);
    }
}
