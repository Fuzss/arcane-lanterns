package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

public class LifeLanternBlockEntity extends LanternBlockEntity {

    public LifeLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.LIFE_LANTERN_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LifeLanternBlockEntity blockEntity) {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).lifeLantern;
        if (++blockEntity.count <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        BlockPos targetPos = pos.offset(level.random.nextInt(horizontalRange * 2) - horizontalRange, level.random.nextInt(verticalRange * 2) - verticalRange, level.random.nextInt(horizontalRange * 2) - horizontalRange);
        while (!(level.getBlockState(targetPos).getBlock() instanceof BonemealableBlock) && targetPos.closerThan(pos, 6.0)) {
            targetPos = targetPos.subtract(new Vec3i(0, 1, 0));
        }
        BlockState targetState = level.getBlockState(targetPos);
        if (targetState.getBlock() instanceof BonemealableBlock cropBlock && cropBlock.isValidBonemealTarget(level, targetPos, targetState, false) && cropBlock.isBonemealSuccess(level, level.random, targetPos, targetState)) {
            cropBlock.performBonemeal((ServerLevel) level, level.random, targetPos, targetState);
            level.levelEvent(LevelEvent.PARTICLES_PLANT_GROWTH, targetPos, 0);
        }
        blockEntity.count = 0;
    }
}
