package fuzs.arcanelanterns.world.level.block;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.world.level.block.entity.LoveLanternBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LoveLanternBlock extends LanternEntityBlock {

    public LoveLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide ? createTickerHelper(blockEntityType, ModRegistry.LOVE_LANTERN_BLOCK_ENTITY.get(), LoveLanternBlockEntity::tick) : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LoveLanternBlockEntity(pos, state);
    }
}
