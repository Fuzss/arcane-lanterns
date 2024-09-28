package fuzs.arcanelanterns.neoforge;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.data.ModBlockLootProvider;
import fuzs.arcanelanterns.data.ModBlockTagProvider;
import fuzs.arcanelanterns.data.client.ModLanguageProvider;
import fuzs.arcanelanterns.data.client.ModModelProvider;
import fuzs.arcanelanterns.data.recipes.ModRecipeProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;

@Mod(ArcaneLanterns.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArcaneLanternsNeoForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanterns::new);
        DataProviderHelper.registerDataProviders(ArcaneLanterns.MOD_ID,
                ModBlockLootProvider::new,
                ModBlockTagProvider::new,
                ModLanguageProvider::new,
                ModModelProvider::new,
                ModRecipeProvider::new
        );
    }
}
