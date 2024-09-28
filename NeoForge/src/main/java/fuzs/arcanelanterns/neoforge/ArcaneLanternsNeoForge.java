package fuzs.arcanelanterns.neoforge;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.data.ModBlockLootProvider;
import fuzs.arcanelanterns.data.ModBlockTagProvider;
import fuzs.arcanelanterns.data.recipes.ModRecipeProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(ArcaneLanterns.MOD_ID)
public class ArcaneLanternsNeoForge {

    public ArcaneLanternsNeoForge() {
        ModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanterns::new);
        DataProviderHelper.registerDataProviders(ArcaneLanterns.MOD_ID, ModBlockLootProvider::new,
                ModBlockTagProvider::new, ModRecipeProvider::new
        );
    }
}
