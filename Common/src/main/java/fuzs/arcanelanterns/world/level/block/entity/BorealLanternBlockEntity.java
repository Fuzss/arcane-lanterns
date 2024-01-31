package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundBorealParticlesMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BorealLanternBlockEntity extends LanternBlockEntity {

    public BorealLanternBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModRegistry.BOREAL_LANTERN_BLOCK_ENTITY.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BorealLanternBlockEntity blockEntity) {
        ServerConfig.EffectLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).borealLantern;
        if (++blockEntity.count <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        level.getEntitiesOfClass(LivingEntity.class, new AABB(pos.getX() + 0.5 - horizontalRange, pos.getY() + 0.5 - verticalRange, pos.getZ() + 0.5 - horizontalRange, pos.getX() + 0.5 + horizontalRange, pos.getY() + 0.5 + verticalRange, pos.getZ() + 0.5 + horizontalRange), EntitySelector.NO_CREATIVE_OR_SPECTATOR).forEach((entity) -> {
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, config.effectDuration * 20, 3));
            entity.setRemainingFireTicks(0);
            ArcaneLanterns.NETWORK.sendToAllNear(entity.blockPosition(), level, new ClientboundBorealParticlesMessage(pos, entity.blockPosition()));
        });
        blockEntity.count = 0;
    }
}
