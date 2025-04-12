package fuzs.arcanelanterns;

import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundBorealParticlesMessage;
import fuzs.arcanelanterns.network.ClientboundContainingSoundsMessage;
import fuzs.arcanelanterns.network.ClientboundCraftLanternParticlesMessage;
import fuzs.arcanelanterns.network.ClientboundWailingSoundsMessage;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.network.v3.NetworkHandler;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArcaneLanterns implements ModConstructor {
    public static final String MOD_ID = "arcanelanterns";
    public static final String MOD_NAME = "Arcane Lanterns";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.builder(MOD_ID)
            .registerClientbound(ClientboundBorealParticlesMessage.class)
            .registerClientbound(ClientboundContainingSoundsMessage.class)
            .registerClientbound(ClientboundCraftLanternParticlesMessage.class)
            .registerClientbound(ClientboundWailingSoundsMessage.class);
    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
