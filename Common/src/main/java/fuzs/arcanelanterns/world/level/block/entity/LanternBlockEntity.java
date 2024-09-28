package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.puzzleslib.api.block.v1.entity.TickingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class LanternBlockEntity extends BlockEntity implements TickingBlockEntity {
    public static final String TAG_TICKS = ArcaneLanterns.id("ticks").toString();

    protected int ticks;

    protected LanternBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public abstract void serverTick();

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.ticks = tag.getInt(TAG_TICKS);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt(TAG_TICKS, this.ticks);
    }
}
