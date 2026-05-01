package fuzs.arcanelanterns.neoforge;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.data.ModBlockLootProvider;
import fuzs.arcanelanterns.common.data.ModBlockTagProvider;
import fuzs.arcanelanterns.common.data.recipes.ModRecipeProvider;
import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.ModLoaderEnvironment;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

@Mod(ArcaneLanterns.MOD_ID)
public class ArcaneLanternsNeoForge {

    public ArcaneLanternsNeoForge() {
        ModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanterns::new);
        registerEventHandlers(NeoForge.EVENT_BUS);
        DataProviderHelper.registerDataProviders(ArcaneLanterns.MOD_ID,
                ModBlockLootProvider::new,
                ModBlockTagProvider::new,
                ModRecipeProvider::new);
    }

    private static void registerEventHandlers(IEventBus eventBus) {
        if (ModLoaderEnvironment.INSTANCE.isModLoaded("jei")) {
            eventBus.addListener((final OnDatapackSyncEvent event) -> {
                event.sendRecipes(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value());
            });
        }
    }
}
