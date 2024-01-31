package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WardingLanternBlockEntity extends LanternBlockEntity {

    public WardingLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.WARDING_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick() {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).wardingLantern;
        if (++this.ticks <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        this.getLevel()
                .getEntitiesOfClass(LivingEntity.class,
                        new AABB(this.getBlockPos().getX() - horizontalRange,
                                this.getBlockPos().getY() - verticalRange,
                                this.getBlockPos().getZ() - horizontalRange,
                                this.getBlockPos().getX() + horizontalRange,
                                this.getBlockPos().getY() + verticalRange,
                                this.getBlockPos().getZ() + horizontalRange
                        ),
                        entity -> !(entity instanceof Player)
                )
                .forEach((entity) -> {
                    entity.push((double) (-this.getBlockPos().getX() + entity.blockPosition().getX()) / 10,
                            (double) (-this.getBlockPos().getY() + entity.blockPosition().getY()) / 10,
                            (double) (-this.getBlockPos().getZ() + entity.blockPosition().getZ()) / 10
                    );
                });
        this.ticks = 0;
    }
}
