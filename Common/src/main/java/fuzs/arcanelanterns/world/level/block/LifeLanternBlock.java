package fuzs.arcanelanterns.world.level.block;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.world.level.block.entity.LifeLanternBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LifeLanternBlock extends LanternEntityBlock {

    public LifeLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide ? createTickerHelper(blockEntityType, ModRegistry.LIFE_LANTERN_BLOCK_ENTITY.get(), LifeLanternBlockEntity::tick) : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LifeLanternBlockEntity(pos, state);
    }
}
