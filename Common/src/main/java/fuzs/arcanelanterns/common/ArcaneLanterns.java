package fuzs.arcanelanterns.common;

import fuzs.arcanelanterns.common.config.ServerConfig;
import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.arcanelanterns.common.network.ClientboundBorealParticlesMessage;
import fuzs.arcanelanterns.common.network.ClientboundContainingSoundsMessage;
import fuzs.arcanelanterns.common.network.ClientboundCraftLanternParticlesMessage;
import fuzs.arcanelanterns.common.network.ClientboundWailingSoundsMessage;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.PayloadTypesContext;
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
