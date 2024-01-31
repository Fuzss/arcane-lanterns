package fuzs.arcanelanterns.client;

import fuzs.arcanelanterns.client.renderer.blockentity.LanternMakerRenderer;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.BlockEntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class ArcaneLanternsClient implements ClientModConstructor {

    @Override
    public void onRegisterBlockEntityRenderers(BlockEntityRenderersContext context) {
        context.registerBlockEntityRenderer(ModRegistry.LANTERN_MAKER_BLOCK_ENTITY.value(), LanternMakerRenderer::new);
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        context.registerRenderType(RenderType.cutout(), ModRegistry.LIFE_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.FERAL_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.LOVE_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.WAILING_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.BOREAL_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.BRILLIANT_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.WARDING_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.CONTAINING_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.WITHERING_LANTERN_BLOCK.value());
        context.registerRenderType(RenderType.cutout(), ModRegistry.CLOUD_LANTERN_BLOCK.value());
    }
}
