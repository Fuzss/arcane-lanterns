package fuzs.arcanelanterns.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.arcanelanterns.client.renderer.blockentity.state.LanternMakerRenderState;
import fuzs.arcanelanterns.world.level.block.entity.LanternMakerBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Mostly copied from <a
 * href="https://github.com/VazkiiMods/Botania/blob/1.19.x/Xplat/src/main/java/vazkii/botania/client/render/block_entity/RunicAltarBlockEntityRenderer.java">Botania's
 * Runic Altar</a> rendering code, thanks!
 */
public class LanternMakerRenderer implements BlockEntityRenderer<LanternMakerBlockEntity, LanternMakerRenderState> {
    private final ItemModelResolver itemModelResolver;

    public LanternMakerRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public LanternMakerRenderState createRenderState() {
        return new LanternMakerRenderState();
    }

    @Override
    public void extractRenderState(LanternMakerBlockEntity blockEntity, LanternMakerRenderState renderState, float partialTick, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity,
                renderState,
                partialTick,
                cameraPosition,
                breakProgress);
        renderState.time = blockEntity.getLevel().getGameTime() + partialTick;
        int position = (int) blockEntity.getBlockPos().asLong();
        renderState.items = new ArrayList<>();
        for (int i = 0; i < blockEntity.getItems().size(); i++) {
            ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
            this.itemModelResolver.updateForTopItem(itemStackRenderState,
                    blockEntity.getItems().get(i),
                    ItemDisplayContext.GROUND,
                    blockEntity.getLevel(),
                    null,
                    position + i);
            renderState.items.add(itemStackRenderState);
        }
    }

    @Override
    public void submit(LanternMakerRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        float itemAngle =
                360.0F / renderState.items.stream().filter(Predicate.not(ItemStackRenderState::isEmpty)).count();
        for (int i = 0; i < renderState.items.size(); ++i) {
            ItemStackRenderState itemStackRenderState = renderState.items.get(i);
            if (!itemStackRenderState.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5F, 1.15F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(i * itemAngle + renderState.time));
                poseStack.translate(0.75F, 0.0F, 0.25F);
                poseStack.mulPose(Axis.YP.rotationDegrees(renderState.time % 360.0F));
                poseStack.translate(0.0, 0.075 * Math.sin((renderState.time + i * 10.0) / 5.0), 0.0F);
                itemStackRenderState.submit(poseStack,
                        nodeCollector,
                        renderState.lightCoords,
                        OverlayTexture.NO_OVERLAY,
                        0);
                poseStack.popPose();
            }
        }
    }
}
