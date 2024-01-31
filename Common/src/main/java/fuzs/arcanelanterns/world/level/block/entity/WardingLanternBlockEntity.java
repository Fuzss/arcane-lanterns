package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WardingLanternBlockEntity extends LanternBlockEntity {

    public WardingLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.WARDING_LANTERN_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WardingLanternBlockEntity blockEntity) {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).wardingLantern;
        if (++blockEntity.count <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        level.getEntitiesOfClass(LivingEntity.class, new AABB(pos.getX() - horizontalRange, pos.getY() - verticalRange, pos.getZ() - horizontalRange, pos.getX() + horizontalRange, pos.getY() + verticalRange, pos.getZ() + horizontalRange), entity -> !(entity instanceof Player)).forEach((entity) -> {
            entity.push((double) (-pos.getX() + entity.blockPosition().getX()) / 10, (double) (-pos.getY() + entity.blockPosition().getY()) / 10, (double) (-pos.getZ() + entity.blockPosition().getZ()) / 10);
        });
        blockEntity.count = 0;
    }
}
