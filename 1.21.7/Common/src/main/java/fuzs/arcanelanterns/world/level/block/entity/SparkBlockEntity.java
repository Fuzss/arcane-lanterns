package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SparkBlockEntity extends BlockEntity {
    public static final String TAG_POSITION = ArcaneLanterns.id("position").toString();

    public BlockPos blockPos = BlockPos.ZERO;

    public SparkBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModRegistry.SPARK_BLOCK_ENTITY.value(), blockPos, blockState);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.blockPos = tag.read(TAG_POSITION, BlockPos.CODEC).orElse(BlockPos.ZERO);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (this.blockPos != BlockPos.ZERO) {
            tag.store(TAG_POSITION, BlockPos.CODEC, this.blockPos);
        }
    }
}
