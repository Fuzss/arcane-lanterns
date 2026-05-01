package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

public class LifeLanternBlockEntity extends LanternBlockEntity {

    public LifeLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.LIFE_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).lifeLantern;
        if (++this.ticks <= config.delay) {
            return;
        }

        int horizontalRange = config.horizontalRange;
        int verticalRange = config.verticalRange;
        BlockPos targetPos = blockPos.offset(serverLevel.getRandom().nextInt(horizontalRange * 2) - horizontalRange,
                serverLevel.getRandom().nextInt(verticalRange * 2) - verticalRange,
                serverLevel.getRandom().nextInt(horizontalRange * 2) - horizontalRange);
        while (!(serverLevel.getBlockState(targetPos).getBlock() instanceof BonemealableBlock) && targetPos.closerThan(
                blockPos,
                6.0)) {
            targetPos = targetPos.subtract(new Vec3i(0, 1, 0));
        }

        BlockState targetState = serverLevel.getBlockState(targetPos);
        if (targetState.getBlock() instanceof BonemealableBlock cropBlock
                && cropBlock.isValidBonemealTarget(serverLevel, targetPos, targetState) && cropBlock.isBonemealSuccess(
                serverLevel,
                serverLevel.getRandom(),
                targetPos,
                targetState)) {
            cropBlock.performBonemeal(serverLevel, serverLevel.getRandom(), targetPos, targetState);
            serverLevel.levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, targetPos, 0);
        }

        this.ticks = 0;
    }
}
