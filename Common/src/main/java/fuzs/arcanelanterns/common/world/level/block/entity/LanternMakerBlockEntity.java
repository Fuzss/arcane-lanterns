package fuzs.arcanelanterns.common.world.level.block.entity;

import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.arcanelanterns.common.network.ClientboundCraftLanternParticlesMessage;
import fuzs.arcanelanterns.common.world.item.crafting.LanternMakingRecipe;
import fuzs.puzzleslib.common.api.block.v1.entity.TickingBlockEntity;
import fuzs.puzzleslib.common.api.container.v1.ContainerSerializationHelper;
import fuzs.puzzleslib.common.api.container.v1.ListBackedContainer;
import fuzs.puzzleslib.common.api.network.v4.MessageSender;
import fuzs.puzzleslib.common.api.network.v4.PlayerSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LanternMakerBlockEntity extends BlockEntity implements CraftingContainer, ListBackedContainer, TickingBlockEntity {
    private final RecipeManager.CachedCheck<CraftingInput, LanternMakingRecipe> quickCheck;
    private final NonNullList<ItemStack> items = NonNullList.withSize(16, ItemStack.EMPTY);

    public LanternMakerBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.LANTERN_MAKER_BLOCK_ENTITY.value(), pos, state);
        this.quickCheck = RecipeManager.createCheck(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value());
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        BlockPos posAbove = blockPos.above();
        BlockState stateAbove = serverLevel.getBlockState(posAbove);
        if (stateAbove.is(Blocks.LANTERN) || stateAbove.is(Blocks.SOUL_LANTERN)) {
            ItemStack result = this.quickCheck.getRecipeFor(this.asCraftInput(), serverLevel)
                    .map((RecipeHolder<LanternMakingRecipe> recipe) -> recipe.value().assemble(this.asCraftInput()))
                    .orElse(ItemStack.EMPTY);
            if (!result.isEmpty()) {
                for (ItemStack stack : this.items) {
                    if (!stack.isEmpty()) stack.shrink(1);
                }

                this.setChanged();
                serverLevel.destroyBlock(posAbove, false);
                dropItemStack(serverLevel, blockPos.getX() + 0.5, blockPos.getY() + 1.0, blockPos.getZ() + 0.5, result);
                MessageSender.broadcast(PlayerSet.nearBlockEntity(this),
                        new ClientboundCraftLanternParticlesMessage(blockPos));
            } else {
                destroyBlockDropCentered(serverLevel, stateAbove, posAbove);
            }
        }
    }

    public static void dropItemStack(ServerLevel serverLevel, double posX, double posY, double posZ, ItemStack stack) {
        ItemEntity itemEntity = new ItemEntity(serverLevel, posX, posY, posZ, stack);
        itemEntity.setDeltaMovement(Vec3.ZERO);
        itemEntity.setDefaultPickUpDelay();
        serverLevel.addFreshEntity(itemEntity);
    }

    private static void destroyBlockDropCentered(ServerLevel serverLevel, BlockState state, BlockPos pos) {
        BlockEntity blockEntityAbove = state.hasBlockEntity() ? serverLevel.getBlockEntity(pos) : null;
        serverLevel.destroyBlock(pos, false);
        Block.getDrops(state, serverLevel, pos, blockEntityAbove, null, ItemStack.EMPTY).forEach((itemStack) -> {
            dropItemStack(serverLevel, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, itemStack);
        });
        state.spawnAfterBreak(serverLevel, pos, ItemStack.EMPTY, true);
    }

    @Override
    public void fillStackedContents(StackedItemContents stackedContents) {
        for (ItemStack itemStack : this.items) {
            stackedContents.accountSimpleStack(itemStack);
        }
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Override
    public List<ItemStack> getItems() {
        return this.getContainerItems();
    }

    @Override
    public NonNullList<ItemStack> getContainerItems() {
        return this.items;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        ContainerSerializationHelper.loadAllItems(valueInput, this.items);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        ContainerSerializationHelper.saveAllItems(valueOutput, this.items);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }
}
