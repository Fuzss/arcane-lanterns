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
    public void serverTick() {
        ServerConfig.LanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).lifeLantern;
        if (++this.ticks <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        BlockPos targetPos = this.getBlockPos()
                .offset(this.getLevel().random.nextInt(horizontalRange * 2) - horizontalRange,
                        this.getLevel().random.nextInt(verticalRange * 2) - verticalRange,
                        this.getLevel().random.nextInt(horizontalRange * 2) - horizontalRange
                );
        while (!(this.getLevel()
                .getBlockState(targetPos)
                .getBlock() instanceof BonemealableBlock) && targetPos.closerThan(this.getBlockPos(), 6.0)) {
            targetPos = targetPos.subtract(new Vec3i(0, 1, 0));
        }
        BlockState targetState = this.getLevel().getBlockState(targetPos);
        if (targetState.getBlock() instanceof BonemealableBlock cropBlock && cropBlock.isValidBonemealTarget(this.getLevel(),
                targetPos,
                targetState
        ) && cropBlock.isBonemealSuccess(this.getLevel(), this.getLevel().random, targetPos, targetState)) {
            cropBlock.performBonemeal((ServerLevel) this.getLevel(), this.getLevel().random, targetPos, targetState);
            this.getLevel().levelEvent(LevelEvent.PARTICLES_PLANT_GROWTH, targetPos, 0);
        }
        this.ticks = 0;
    }
}
