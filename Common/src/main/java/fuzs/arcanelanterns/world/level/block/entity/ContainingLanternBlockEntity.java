package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundContainingSoundsMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class ContainingLanternBlockEntity extends LanternBlockEntity {

    public ContainingLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.CONTAINING_LANTERN_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ContainingLanternBlockEntity blockEntity) {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).containingLantern;
        if (++blockEntity.count <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        level.getEntitiesOfClass(LivingEntity.class, new AABB(pos.getX() + 0.5 - horizontalRange, pos.getY() + 0.5 - verticalRange, pos.getZ() + 0.5 - horizontalRange, pos.getX() + 0.5 + horizontalRange, pos.getY() + 0.5 + verticalRange, pos.getZ() + 0.5 + horizontalRange), entity -> !(entity instanceof Player)).forEach((entity) -> {
            if (!entity.blockPosition().closerThan(pos, horizontalRange / 2 + 1)) {
                if (level.getBlockState(pos.above()).isAir()) {
                    entity.teleportToWithTicket(pos.getX(), pos.getY() + 1, pos.getZ());
                } else {
                    entity.teleportToWithTicket(pos.getX(), pos.getY() - 1, pos.getZ());
                }
                ArcaneLanterns.NETWORK.sendToAllNear(pos, level, new ClientboundContainingSoundsMessage(pos));
            }
        });
        blockEntity.count = 0;
    }
}
