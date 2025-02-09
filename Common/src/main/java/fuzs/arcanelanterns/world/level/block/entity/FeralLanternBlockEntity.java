package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class FeralLanternBlockEntity extends LanternBlockEntity {
    public static final String TAG_PLACED_FLARES = ArcaneLanterns.id("placed_flares").toString();

    private int placedFlares;

    public FeralLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.FERAL_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick() {
        ServerConfig.FeralLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).feralLantern;
        if (++this.ticks > config.delay && !this.isDonePlacing()) {
            BlockPos.MutableBlockPos mutable = this.getBlockPos().mutable();
            mutable.move(-config.horizontalRange, -config.verticalRange, -config.horizontalRange);
            mutable.move(this.getLevel().random.nextInt(config.horizontalRange * 2),
                    this.getLevel().random.nextInt(config.verticalRange * 2),
                    this.getLevel().random.nextInt(config.horizontalRange * 2)
            );
            // max manhattan distance approximation
            int maxDistance = 5 * (config.horizontalRange + config.verticalRange) / 7;
            while (mutable.closerThan(this.getBlockPos(), maxDistance) && !this.getLevel()
                    .isOutsideBuildHeight(mutable) && this.getLevel()
                    .getBlockState(mutable)
                    .getCollisionShape(this.getLevel(), mutable)
                    .isEmpty()) {
                mutable.move(Direction.DOWN);
            }
            while (mutable.closerThan(this.getBlockPos(), maxDistance) && !this.getLevel()
                    .isOutsideBuildHeight(mutable) && !this.getLevel()
                    .getBlockState(mutable)
                    .getCollisionShape(this.getLevel(), mutable)
                    .isEmpty()) {
                mutable.move(Direction.UP);
            }
            if (this.getLevel().getMaxLocalRawBrightness(mutable) < config.maxLightLevel) {
                if (!this.getLevel()
                        .getBlockState(mutable.below())
                        .getCollisionShape(this.getLevel(), mutable.below())
                        .isEmpty()) {
                    mutable.move(Direction.UP, 3);
                    for (int i = 0; i < 3 && mutable.closerThan(this.getBlockPos(), maxDistance) && !this.getLevel()
                            .getBlockState(mutable)
                            .isAir(); i++) {
                        mutable.move(Direction.DOWN);
                    }
                    if (this.getLevel().getBlockState(mutable).isAir()) {
                        this.getLevel().setBlockAndUpdate(mutable, ModRegistry.SPARK_BLOCK.value().defaultBlockState());
                        if (this.getLevel().getBlockEntity(mutable) instanceof SparkBlockEntity sparkBlockEntity) {
                            sparkBlockEntity.pos = this.getBlockPos();
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
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.placedFlares = tag.getInt(TAG_PLACED_FLARES);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt(TAG_PLACED_FLARES, this.placedFlares);
    }
}
