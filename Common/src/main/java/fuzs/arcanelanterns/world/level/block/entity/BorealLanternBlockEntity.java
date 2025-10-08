package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundBorealParticlesMessage;
import fuzs.puzzleslib.api.network.v4.MessageSender;
import fuzs.puzzleslib.api.network.v4.PlayerSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BorealLanternBlockEntity extends LanternBlockEntity {

    public BorealLanternBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModRegistry.BOREAL_LANTERN_BLOCK_ENTITY.value(), pos, blockState);
    }

    @Override
    public void serverTick() {
        ServerConfig.EffectLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).borealLantern;
        if (++this.ticks <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        this.getLevel()
                .getEntitiesOfClass(LivingEntity.class,
                        new AABB(this.getBlockPos().getX() + 0.5 - horizontalRange,
                                this.getBlockPos().getY() + 0.5 - verticalRange,
                                this.getBlockPos().getZ() + 0.5 - horizontalRange,
                                this.getBlockPos().getX() + 0.5 + horizontalRange,
                                this.getBlockPos().getY() + 0.5 + verticalRange,
                                this.getBlockPos().getZ() + 0.5 + horizontalRange),
                        EntitySelector.NO_SPECTATORS)
                .forEach((entity) -> {
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, config.effectDuration * 20, 3));
                    entity.setRemainingFireTicks(0);
                    MessageSender.broadcast(PlayerSet.nearBlockEntity(this),
                            new ClientboundBorealParticlesMessage(this.getBlockPos(), entity.blockPosition()));
                });
        this.ticks = 0;
    }
}
