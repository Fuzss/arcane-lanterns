package fuzs.arcanelanterns.neoforge.client;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.client.ArcaneLanternsClient;
import fuzs.arcanelanterns.data.client.ModLanguageProvider;
import fuzs.arcanelanterns.data.client.ModModelProvider;
import fuzs.arcanelanterns.integration.LanternMakingRecipeHelper;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.core.v1.ModLoaderEnvironment;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = ArcaneLanterns.MOD_ID, dist = Dist.CLIENT)
public class ArcaneLanternsNeoForgeClient {

    public ArcaneLanternsNeoForgeClient() {
        ClientModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanternsClient::new);
        registerEventHandlers(NeoForge.EVENT_BUS);
        DataProviderHelper.registerDataProviders(ArcaneLanterns.MOD_ID,
                ModLanguageProvider::new,
                ModModelProvider::new);
    }

    private static void registerEventHandlers(IEventBus eventBus) {
        if (ModLoaderEnvironment.INSTANCE.isModLoaded("jei")) {
            eventBus.addListener((final RecipesReceivedEvent event) -> {
                LanternMakingRecipeHelper.setRecipeMap(event.getRecipeMap());
            });
            eventBus.addListener((final ClientPlayerNetworkEvent.LoggingOut event) -> {
                LanternMakingRecipeHelper.setRecipeMap(RecipeMap.EMPTY);
            });
        }
    }
}
