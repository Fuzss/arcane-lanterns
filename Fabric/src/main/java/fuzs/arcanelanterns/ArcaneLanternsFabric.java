package fuzs.arcanelanterns;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class ArcaneLanternsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(ArcaneLanterns.MOD_ID, ArcaneLanterns::new);
    }
}
