package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SparkBlockEntity extends BlockEntity {
    public static final String TAG_POSITION = ArcaneLanterns.id("position").toString();

    public BlockPos pos = BlockPos.ZERO;

    public SparkBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModRegistry.SPARK_BLOCK_ENTITY.value(), blockPos, blockState);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.pos = NbtUtils.readBlockPos(tag.getCompound(TAG_POSITION));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(TAG_POSITION, NbtUtils.writeBlockPos(this.pos));
    }
}
