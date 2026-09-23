package fuzs.arcanelanterns.neoforge.client;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.client.ArcaneLanternsClient;
import fuzs.arcanelanterns.common.data.client.ModLanguageProvider;
import fuzs.arcanelanterns.common.data.client.ModModelProvider;
import fuzs.arcanelanterns.common.integration.LanternMakingRecipeHelper;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.core.v1.ModLoaderEnvironment;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.Collections;

@Mod(value = ArcaneLanterns.MOD_ID, dist = Dist.CLIENT)
public class ArcaneLanternsNeoForgeClient {

    public ArcaneLanternsNeoForgeClient() {
        ClientModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanternsClient::new);
        registerEventHandlers(NeoForge.EVENT_BUS);
        DataProviderBuilder.of(ArcaneLanterns.MOD_ID)
                .addProvider(ModLanguageProvider::new, ModModelProvider::new);
    }

    private static void registerEventHandlers(IEventBus eventBus) {
        if (ModLoaderEnvironment.INSTANCE.isModLoaded("jei")) {
            eventBus.addListener((final RecipesReceivedEvent event) -> {
                LanternMakingRecipeHelper.setRecipes(event.getRecipeMap().values());
            });
            eventBus.addListener((final ClientPlayerNetworkEvent.LoggingOut event) -> {
                LanternMakingRecipeHelper.setRecipes(Collections.emptyList());
            });
        }
    }
}
