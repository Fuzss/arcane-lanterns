package fuzs.arcanelanterns.common.world.level.block.entity;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.config.ServerConfig;
import fuzs.arcanelanterns.common.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WardingLanternBlockEntity extends LanternBlockEntity {

    public WardingLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.WARDING_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).wardingLantern;
        if (++this.ticks <= config.delay) {
            return;
        }

        int horizontalRange = config.horizontalRange;
        int verticalRange = config.verticalRange;
        serverLevel.getEntitiesOfClass(LivingEntity.class,
                new AABB(blockPos.getX() - horizontalRange,
                        blockPos.getY() - verticalRange,
                        blockPos.getZ() - horizontalRange,
                        blockPos.getX() + horizontalRange,
                        blockPos.getY() + verticalRange,
                        blockPos.getZ() + horizontalRange),
                (LivingEntity entity) -> {
                    return !(entity instanceof Player);
                }).forEach((LivingEntity entity) -> {
            entity.push((double) (-blockPos.getX() + entity.blockPosition().getX()) / 10,
                    (double) (-blockPos.getY() + entity.blockPosition().getY()) / 10,
                    (double) (-blockPos.getZ() + entity.blockPosition().getZ()) / 10);
        });
        this.ticks = 0;
    }
}
