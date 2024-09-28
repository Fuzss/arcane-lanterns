package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundCraftLanternParticlesMessage;
import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import fuzs.puzzleslib.api.block.v1.entity.TickingBlockEntity;
import fuzs.puzzleslib.api.container.v1.ListBackedContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
    public void serverTick() {
        BlockPos posAbove = this.getBlockPos().above();
        BlockState stateAbove = this.getLevel().getBlockState(posAbove);
        if (stateAbove.is(Blocks.LANTERN) || stateAbove.is(Blocks.SOUL_LANTERN)) {
            ItemStack result = this.quickCheck.getRecipeFor(this.asCraftInput(), this.getLevel()).map(
                    recipe -> recipe.value().assemble(this.asCraftInput(), this.getLevel().registryAccess())).orElse(
                    ItemStack.EMPTY);
            if (!result.isEmpty()) {
                for (ItemStack stack : this.items) {
                    if (!stack.isEmpty()) stack.shrink(1);
                }
                this.setChanged();
                this.getLevel().destroyBlock(posAbove, false);
                dropItemStack(this.getLevel(), this.getBlockPos().getX() + 0.5, this.getBlockPos().getY() + 1.0,
                        this.getBlockPos().getZ() + 0.5, result
                );
                ArcaneLanterns.NETWORK.sendToAllNear(this.getBlockPos(), (ServerLevel) this.getLevel(),
                        new ClientboundCraftLanternParticlesMessage(this.getBlockPos())
                );
            } else {
                destroyBlockDropCentered(this.getLevel(), stateAbove, posAbove);
            }
        }
    }

    public static void dropItemStack(Level level, double posX, double posY, double posZ, ItemStack stack) {
        ItemEntity itemEntity = new ItemEntity(level, posX, posY, posZ, stack);
        itemEntity.setDeltaMovement(Vec3.ZERO);
        itemEntity.setDefaultPickUpDelay();
        level.addFreshEntity(itemEntity);
    }

    private static void destroyBlockDropCentered(Level level, BlockState state, BlockPos pos) {
        BlockEntity blockEntityAbove = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        level.destroyBlock(pos, false);
        Block.getDrops(state, (ServerLevel) level, pos, blockEntityAbove, null, ItemStack.EMPTY).forEach(
                (itemStack) -> {
                    dropItemStack(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, itemStack);
                });
        state.spawnAfterBreak((ServerLevel) level, pos, ItemStack.EMPTY, true);
    }

    @Override
    public void fillStackedContents(StackedContents contents) {
        for (ItemStack itemStack : this.items) {
            contents.accountSimpleStack(itemStack);
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
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, this.items, registries);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
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
