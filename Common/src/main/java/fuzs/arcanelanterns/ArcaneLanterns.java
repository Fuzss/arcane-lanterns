package fuzs.arcanelanterns;

import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundBorealParticlesMessage;
import fuzs.arcanelanterns.network.ClientboundContainingSoundsMessage;
import fuzs.arcanelanterns.network.ClientboundCraftLanternParticlesMessage;
import fuzs.arcanelanterns.network.ClientboundWailingSoundsMessage;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.PayloadTypesContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArcaneLanterns implements ModConstructor {
    public static final String MOD_ID = "arcanelanterns";
    public static final String MOD_NAME = "Arcane Lanterns";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    @Override
    public void onRegisterPayloadTypes(PayloadTypesContext context) {
        context.playToClient(ClientboundBorealParticlesMessage.class, ClientboundBorealParticlesMessage.STREAM_CODEC);
        context.playToClient(ClientboundContainingSoundsMessage.class, ClientboundContainingSoundsMessage.STREAM_CODEC);
        context.playToClient(ClientboundCraftLanternParticlesMessage.class,
                ClientboundCraftLanternParticlesMessage.STREAM_CODEC);
        context.playToClient(ClientboundWailingSoundsMessage.class, ClientboundWailingSoundsMessage.STREAM_CODEC);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
