package fuzs.arcanelanterns.fabric;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.ModLoaderEnvironment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;

public class ArcaneLanternsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanterns::new);
        if (ModLoaderEnvironment.INSTANCE.isModLoaded("jei")) {
            RecipeSynchronization.synchronizeRecipeSerializer(ModRegistry.LANTERN_MAKING_RECIPE_SERIALIZER.value());
        }
    }
}
