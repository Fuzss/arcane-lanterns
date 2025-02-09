package fuzs.arcanelanterns.neoforge.client;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.client.ArcaneLanternsClient;
import fuzs.arcanelanterns.data.client.ModLanguageProvider;
import fuzs.arcanelanterns.data.client.ModModelProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = ArcaneLanterns.MOD_ID, dist = Dist.CLIENT)
public class ArcaneLanternsNeoForgeClient {

    public ArcaneLanternsNeoForgeClient() {
        ClientModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanternsClient::new);
        DataProviderHelper.registerDataProviders(ArcaneLanterns.MOD_ID, ModLanguageProvider::new,
                ModModelProvider::new
        );
    }
}
