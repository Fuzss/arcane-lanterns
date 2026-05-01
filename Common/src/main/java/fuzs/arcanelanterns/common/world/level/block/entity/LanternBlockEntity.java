package fuzs.arcanelanterns.common.world.level.block.entity;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.puzzleslib.common.api.block.v1.entity.TickingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class LanternBlockEntity extends BlockEntity implements TickingBlockEntity {
    public static final String TAG_TICKS = ArcaneLanterns.id("ticks").toString();

    protected int ticks;

    protected LanternBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public abstract void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState);

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.ticks = valueInput.getIntOr(TAG_TICKS, 0);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.putInt(TAG_TICKS, this.ticks);
    }
}
