package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundContainingSoundsMessage;
import fuzs.puzzleslib.common.api.network.v4.MessageSender;
import fuzs.puzzleslib.common.api.network.v4.PlayerSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class ContainingLanternBlockEntity extends LanternBlockEntity {

    public ContainingLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.CONTAINING_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).containingLantern;
        if (++this.ticks <= config.delay) {
            return;
        }

        int horizontalRange = config.horizontalRange;
        int verticalRange = config.verticalRange;
        serverLevel.getEntitiesOfClass(LivingEntity.class,
                new AABB(blockPos.getX() + 0.5 - horizontalRange,
                        blockPos.getY() + 0.5 - verticalRange,
                        blockPos.getZ() + 0.5 - horizontalRange,
                        blockPos.getX() + 0.5 + horizontalRange,
                        blockPos.getY() + 0.5 + verticalRange,
                        blockPos.getZ() + 0.5 + horizontalRange),
                (LivingEntity entity) -> {
                    return !(entity instanceof Player);
                }).forEach((LivingEntity entity) -> {
            if (!entity.blockPosition().closerThan(blockPos, horizontalRange / 2 + 1)) {
                if (serverLevel.getBlockState(blockPos.above()).isAir()) {
                    entity.teleportTo(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
                } else {
                    entity.teleportTo(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
                }

                MessageSender.broadcast(PlayerSet.nearBlockEntity(this),
                        new ClientboundContainingSoundsMessage(blockPos));
            }
        });
        this.ticks = 0;
    }
}
