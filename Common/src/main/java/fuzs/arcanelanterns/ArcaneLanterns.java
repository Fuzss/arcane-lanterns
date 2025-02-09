package fuzs.arcanelanterns;

import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundBorealParticlesMessage;
import fuzs.arcanelanterns.network.ClientboundContainingSoundsMessage;
import fuzs.arcanelanterns.network.ClientboundCraftLanternParticlesMessage;
import fuzs.arcanelanterns.network.ClientboundWailingSoundsMessage;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.CreativeModeTabContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.item.v2.CreativeModeTabConfigurator;
import fuzs.puzzleslib.api.network.v3.NetworkHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    @Override
    public void onRegisterCreativeModeTabs(CreativeModeTabContext context) {
        context.registerCreativeModeTab(CreativeModeTabConfigurator.from(MOD_ID)
                .icon(() -> new ItemStack(Items.LANTERN))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModRegistry.LANTERN_MAKER_ITEM.value());
                    output.accept(ModRegistry.LIFE_LANTERN_ITEM.value());
                    output.accept(ModRegistry.FERAL_LANTERN_ITEM.value());
                    output.accept(ModRegistry.LOVE_LANTERN_ITEM.value());
                    output.accept(ModRegistry.WAILING_LANTERN_ITEM.value());
                    output.accept(ModRegistry.BOREAL_LANTERN_ITEM.value());
                    output.accept(ModRegistry.BRILLIANT_LANTERN_ITEM.value());
                    output.accept(ModRegistry.WARDING_LANTERN_ITEM.value());
                    output.accept(ModRegistry.CONTAINING_LANTERN_ITEM.value());
                    output.accept(ModRegistry.WITHERING_LANTERN_ITEM.value());
                    output.accept(ModRegistry.CLOUD_LANTERN_ITEM.value());
                }));
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
