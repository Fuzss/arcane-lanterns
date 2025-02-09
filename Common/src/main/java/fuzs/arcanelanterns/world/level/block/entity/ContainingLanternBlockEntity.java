package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundContainingSoundsMessage;
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
    public void serverTick() {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).containingLantern;
        if (++this.ticks <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        this.getLevel().getEntitiesOfClass(LivingEntity.class,
                new AABB(this.getBlockPos().getX() + 0.5 - horizontalRange,
                        this.getBlockPos().getY() + 0.5 - verticalRange,
                        this.getBlockPos().getZ() + 0.5 - horizontalRange,
                        this.getBlockPos().getX() + 0.5 + horizontalRange,
                        this.getBlockPos().getY() + 0.5 + verticalRange,
                        this.getBlockPos().getZ() + 0.5 + horizontalRange
                ), entity -> !(entity instanceof Player)
        ).forEach((entity) -> {
            if (!entity.blockPosition().closerThan(this.getBlockPos(), horizontalRange / 2 + 1)) {
                if (this.getLevel().getBlockState(this.getBlockPos().above()).isAir()) {
                    entity.teleportTo(this.getBlockPos().getX(), this.getBlockPos().getY() + 1,
                            this.getBlockPos().getZ()
                    );
                } else {
                    entity.teleportTo(this.getBlockPos().getX(), this.getBlockPos().getY() - 1,
                            this.getBlockPos().getZ()
                    );
                }
                ArcaneLanterns.NETWORK.sendToAllNear(this.getBlockPos(), (ServerLevel) this.getLevel(),
                        new ClientboundContainingSoundsMessage(this.getBlockPos())
                );
            }
        });
        this.ticks = 0;
    }
}
