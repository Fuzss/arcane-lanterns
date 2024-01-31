package fuzs.arcanelanterns.world.level.block;

import com.mojang.serialization.MapCodec;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.world.level.block.entity.LanternMakerBlockEntity;
import fuzs.puzzleslib.api.block.v1.entity.TickingEntityBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LanternMakerBlock extends BaseEntityBlock implements TickingEntityBlock<LanternMakerBlockEntity> {
    public static final MapCodec<LanternMakerBlock> CODEC = simpleCodec(LanternMakerBlock::new);
    public static final VoxelShape TOP_SHAPE = Block.box(1.0, 12.0, 1.0, 15.0, 16.0, 15.0);
    public static final VoxelShape CENTER_SHAPE = Block.box(5.0, 2.0, 5.0, 11.0, 12.0, 11.0);
    public static final VoxelShape BASE_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 2.0, 13.0);
    public static final VoxelShape FULL_SHAPE = Shapes.or(TOP_SHAPE, CENTER_SHAPE, BASE_SHAPE);

    public LanternMakerBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<? extends LanternMakerBlockEntity> getBlockEntityType() {
        return ModRegistry.LANTERN_MAKER_BLOCK_ENTITY.value();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return FULL_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof LanternMakerBlockEntity blockEntity) {
            ItemStack itemInHand = player.getItemInHand(hand);
            if (!player.getItemInHand(hand).isEmpty()) {
                if (level.getBlockState(pos.above()).isAir()) {
                    if (itemInHand.is(Items.LANTERN) || itemInHand.is(Items.SOUL_LANTERN)) {
                        return InteractionResult.PASS;
                    }
                    for (int i = 0; i < blockEntity.getContainerSize(); i++) {
                        if (blockEntity.getItem(i).isEmpty()) {
                            if (!level.isClientSide) {
                                if (player.getAbilities().instabuild) {
                                    itemInHand = itemInHand.copy();
                                }
                                blockEntity.setItem(i, itemInHand.split(1));
                                blockEntity.setChanged();
                            }
                            return InteractionResult.sidedSuccess(level.isClientSide);
                        }
                    }
                }
                return InteractionResult.CONSUME_PARTIAL;
            } else if (player.isSecondaryUseActive()) {
                for (int i = blockEntity.getContainerSize() - 1; i >= 0; i--) {
                    if (!blockEntity.getItem(i).isEmpty()) {
                        if (!level.isClientSide) {
                            ItemStack stack = blockEntity.removeItem(i, 1);
                            blockEntity.setChanged();
                            LanternMakerBlockEntity.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, stack);
                        }
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
