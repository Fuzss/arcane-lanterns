package fuzs.arcanelanterns.fabric.client;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.client.ArcaneLanternsClient;
import fuzs.arcanelanterns.integration.LanternMakingRecipeHelper;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.core.v1.ModLoaderEnvironment;
import fuzs.puzzleslib.fabric.api.client.event.v1.FabricClientPlayerEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;
import net.minecraft.world.item.crafting.RecipeMap;

public class ArcaneLanternsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanternsClient::new);
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        if (ModLoaderEnvironment.INSTANCE.isModLoaded("jei")) {
            ClientRecipeSynchronizedEvent.EVENT.register((Minecraft client, SynchronizedRecipes recipes) -> {
                LanternMakingRecipeHelper.setRecipeMap(RecipeMap.create(recipes.recipes()));
            });
            FabricClientPlayerEvents.PLAYER_LEAVE.register((LocalPlayer player, MultiPlayerGameMode multiPlayerGameMode, Connection connection) -> {
                LanternMakingRecipeHelper.setRecipeMap(RecipeMap.EMPTY);
            });
        }
    }
}
