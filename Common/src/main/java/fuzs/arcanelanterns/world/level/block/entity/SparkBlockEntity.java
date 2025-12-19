package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class SparkBlockEntity extends BlockEntity {
    public static final String TAG_POSITION = ArcaneLanterns.id("position").toString();

    public BlockPos blockPos = BlockPos.ZERO;

    public SparkBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModRegistry.SPARK_BLOCK_ENTITY.value(), blockPos, blockState);
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.blockPos = valueInput.read(TAG_POSITION, BlockPos.CODEC).orElse(BlockPos.ZERO);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        if (this.blockPos != BlockPos.ZERO) {
            valueOutput.store(TAG_POSITION, BlockPos.CODEC, this.blockPos);
        }
    }
}
