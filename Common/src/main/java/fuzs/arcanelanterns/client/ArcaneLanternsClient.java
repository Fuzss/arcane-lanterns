package fuzs.arcanelanterns.client;

import fuzs.arcanelanterns.client.renderer.blockentity.LanternMakerRenderer;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.world.level.block.ArcaneLanternBlock;
import fuzs.arcanelanterns.world.level.block.LanternMakerBlock;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.BlockEntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import fuzs.puzzleslib.api.client.gui.v2.tooltip.ItemTooltipRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;

public class ArcaneLanternsClient implements ClientModConstructor {

    @Override
    public void onClientSetup() {
        ItemTooltipRegistry.BLOCK.registerItemTooltip(ArcaneLanternBlock.class,
                ArcaneLanternBlock::getDescriptionComponent);
        ItemTooltipRegistry.BLOCK.registerItemTooltip(LanternMakerBlock.class,
                LanternMakerBlock::getDescriptionComponent);
    }

    @Override
    public void onRegisterBlockEntityRenderers(BlockEntityRenderersContext context) {
        context.registerBlockEntityRenderer(ModRegistry.LANTERN_MAKER_BLOCK_ENTITY.value(), LanternMakerRenderer::new);
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        context.registerChunkRenderType(ModRegistry.LIFE_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.FERAL_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.LOVE_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.WAILING_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.BOREAL_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.BRILLIANT_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.WARDING_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.CONTAINING_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.WITHERING_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
        context.registerChunkRenderType(ModRegistry.CLOUD_LANTERN_BLOCK.value(), ChunkSectionLayer.CUTOUT);
    }
}
