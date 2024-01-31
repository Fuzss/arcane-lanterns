package fuzs.arcanelanterns.neoforge.client;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.client.ArcaneLanternsClient;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.minecraft.client.RecipeBookCategories;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = ArcaneLanterns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ArcaneLanternsNeoForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ClientModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanternsClient::new);
    }

    @SubscribeEvent
    public static void onRegisterRecipeBookCategories(final RegisterRecipeBookCategoriesEvent evt) {
        evt.registerRecipeCategoryFinder(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value(), $ -> {
            String internalName = ArcaneLanterns.id("lantern_maker").toDebugFileName().toUpperCase(Locale.ROOT);
            return RecipeBookCategories.create(internalName);
        });
    }
}
