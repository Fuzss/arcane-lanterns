package fuzs.arcanelanterns.client;

import fuzs.arcanelanterns.client.renderer.blockentity.LanternMakerRenderer;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.world.level.block.ArcaneLanternBlock;
import fuzs.arcanelanterns.world.level.block.LanternMakerBlock;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.BlockEntityRenderersContext;
import fuzs.puzzleslib.common.api.client.gui.v2.tooltip.ItemTooltipRegistry;

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
}
