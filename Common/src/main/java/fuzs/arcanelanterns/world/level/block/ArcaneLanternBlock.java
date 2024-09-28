package fuzs.arcanelanterns.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.arcanelanterns.world.level.block.entity.LanternBlockEntity;
import fuzs.puzzleslib.api.block.v1.entity.TickingEntityBlock;
import fuzs.puzzleslib.api.core.v1.Proxy;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Supplier;

public class ArcaneLanternBlock extends LanternBlock implements TickingEntityBlock<LanternBlockEntity> {
    public static final MapCodec<LanternBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(BuiltInRegistries.BLOCK_ENTITY_TYPE.byNameCodec()
                        .fieldOf("lantern_type")
                        .forGetter(block -> ((ArcaneLanternBlock) block).getBlockEntityType()), propertiesCodec())
                .apply(instance, (BlockEntityType<?> blockEntityType, Properties properties) -> {
                    return new ArcaneLanternBlock(() -> (BlockEntityType<? extends LanternBlockEntity>) blockEntityType,
                            properties
                    );
                });
    });
    ;

    private final Supplier<BlockEntityType<? extends LanternBlockEntity>> blockEntityType;

    public ArcaneLanternBlock(Supplier<BlockEntityType<? extends LanternBlockEntity>> blockEntityType, Properties properties) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public MapCodec<LanternBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<? extends LanternBlockEntity> getBlockEntityType() {
        return this.blockEntityType.get();
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        super.triggerEvent(state, level, pos, id, param);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return blockEntity != null && blockEntity.triggerEvent(id, param);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.addAll(Proxy.INSTANCE.splitTooltipLines(this.getDescriptionComponent()));
    }

    public Component getDescriptionComponent() {
        return Component.translatable(this.getDescriptionId() + ".description").withStyle(ChatFormatting.GOLD);
    }
}
