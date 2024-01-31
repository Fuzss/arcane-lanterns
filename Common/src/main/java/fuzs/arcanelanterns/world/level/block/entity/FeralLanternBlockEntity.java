package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FeralLanternBlockEntity extends LanternBlockEntity {
    private static final String TAG_FLARES = "PlacedFlares";

    private int placedFlares;

    public FeralLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.FERAL_LANTERN_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FeralLanternBlockEntity blockEntity) {
        ServerConfig.FeralLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).feralLantern;
        if (++blockEntity.count > config.delay && !blockEntity.isDonePlacing()) {
            BlockPos.MutableBlockPos mutable = pos.mutable();
            mutable.move(-config.horizontalRange, -config.verticalRange, -config.horizontalRange);
            mutable.move(level.random.nextInt(config.horizontalRange * 2), level.random.nextInt(config.verticalRange * 2), level.random.nextInt(config.horizontalRange * 2));
            // max manhattan distance approximation
            int maxDistance = 5 * (config.horizontalRange + config.verticalRange) / 7;
            while (mutable.closerThan(pos, maxDistance) && !level.isOutsideBuildHeight(mutable) && level.getBlockState(mutable).getCollisionShape(level, mutable).isEmpty()) {
                mutable.move(Direction.DOWN);
            }
            while (mutable.closerThan(pos, maxDistance) && !level.isOutsideBuildHeight(mutable) && !level.getBlockState(mutable).getCollisionShape(level, mutable).isEmpty()) {
                mutable.move(Direction.UP);
            }
            if (level.getMaxLocalRawBrightness(mutable) < config.maxLightLevel) {
                if (!level.getBlockState(mutable.below()).getCollisionShape(level, mutable.below()).isEmpty()) {
                    mutable.move(Direction.UP, 3);
                    for (int i = 0; i < 3 && mutable.closerThan(pos, maxDistance) && !level.getBlockState(mutable).isAir(); i++) {
                        mutable.move(Direction.DOWN);
                    }
                    if (level.getBlockState(mutable).isAir()) {
                        level.setBlockAndUpdate(mutable, ModRegistry.SPARK_BLOCK.get().defaultBlockState());
                        if (level.getBlockEntity(mutable) instanceof SparkBlockEntity sparkBlockEntity) {
                            sparkBlockEntity.pos = pos;
                        }
                        blockEntity.placedFlares++;
                    }
                }
            }
            blockEntity.count = 0;
        }
    }

    public boolean isDonePlacing() {
        return this.placedFlares >= ArcaneLanterns.CONFIG.get(ServerConfig.class).feralLantern.maxPlacedFlares;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt(TAG_FLARES, this.placedFlares);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.placedFlares = tag.getInt(TAG_FLARES);
    }
}
