package fuzs.arcanelanterns.common.world.level.block.entity;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.config.ServerConfig;
import fuzs.arcanelanterns.common.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class FeralLanternBlockEntity extends LanternBlockEntity {
    public static final String TAG_PLACED_FLARES = ArcaneLanterns.id("placed_flares").toString();

    private int placedFlares;

    public FeralLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.FERAL_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        ServerConfig.FeralLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).feralLantern;
        if (++this.ticks > config.delay && !this.isDonePlacing()) {
            BlockPos.MutableBlockPos mutable = blockPos.mutable();
            mutable.move(-config.horizontalRange, -config.verticalRange, -config.horizontalRange);
            mutable.move(serverLevel.getRandom().nextInt(config.horizontalRange * 2),
                    serverLevel.getRandom().nextInt(config.verticalRange * 2),
                    serverLevel.getRandom().nextInt(config.horizontalRange * 2));
            // max manhattan distance approximation
            int maxDistance = 5 * (config.horizontalRange + config.verticalRange) / 7;
            while (mutable.closerThan(blockPos, maxDistance) && !serverLevel.isOutsideBuildHeight(mutable)
                    && serverLevel.getBlockState(mutable).getCollisionShape(serverLevel, mutable).isEmpty()) {
                mutable.move(Direction.DOWN);
            }

            while (mutable.closerThan(blockPos, maxDistance) && !serverLevel.isOutsideBuildHeight(mutable)
                    && !serverLevel.getBlockState(mutable).getCollisionShape(serverLevel, mutable).isEmpty()) {
                mutable.move(Direction.UP);
            }

            if (serverLevel.getMaxLocalRawBrightness(mutable) < config.maxLightLevel) {
                if (!serverLevel.getBlockState(mutable.below())
                        .getCollisionShape(serverLevel, mutable.below())
                        .isEmpty()) {
                    mutable.move(Direction.UP, 3);
                    for (int i = 0;
                         i < 3 && mutable.closerThan(blockPos, maxDistance) && !serverLevel.getBlockState(mutable)
                                 .isAir(); i++) {
                        mutable.move(Direction.DOWN);
                    }

                    if (serverLevel.getBlockState(mutable).isAir()) {
                        serverLevel.setBlockAndUpdate(mutable, ModRegistry.SPARK_BLOCK.value().defaultBlockState());
                        if (serverLevel.getBlockEntity(mutable) instanceof SparkBlockEntity sparkBlockEntity) {
                            sparkBlockEntity.blockPos = blockPos;
                        }

                        this.placedFlares++;
                    }
                }
            }

            this.ticks = 0;
        }
    }

    public boolean isDonePlacing() {
        return this.placedFlares >= ArcaneLanterns.CONFIG.get(ServerConfig.class).feralLantern.maxPlacedFlares;
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.placedFlares = valueInput.getIntOr(TAG_PLACED_FLARES, 0);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.putInt(TAG_PLACED_FLARES, this.placedFlares);
    }
}
