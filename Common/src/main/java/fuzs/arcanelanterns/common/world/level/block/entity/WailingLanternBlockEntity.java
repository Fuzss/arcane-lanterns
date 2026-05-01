package fuzs.arcanelanterns.common.world.level.block.entity;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.config.ServerConfig;
import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.arcanelanterns.common.network.ClientboundWailingSoundsMessage;
import fuzs.puzzleslib.common.api.network.v4.MessageSender;
import fuzs.puzzleslib.common.api.network.v4.PlayerSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WailingLanternBlockEntity extends LanternBlockEntity {

    public WailingLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.WAILING_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        ServerConfig.EffectLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).wailingLantern;
        if (++this.ticks <= config.delay) {
            return;
        }

        int horizontalRange = config.horizontalRange;
        int verticalRange = config.verticalRange;
        serverLevel.getEntitiesOfClass(Player.class,
                new AABB(blockPos.getX() + 0.5 - horizontalRange,
                        blockPos.getY() + 0.5 - verticalRange,
                        blockPos.getZ() + 0.5 - horizontalRange,
                        blockPos.getX() + 0.5 + horizontalRange,
                        blockPos.getY() + 0.5 + verticalRange,
                        blockPos.getZ() + 0.5 + horizontalRange),
                EntitySelector.NO_SPECTATORS).forEach((Player player) -> {
            if (!player.blockPosition().closerThan(blockPos, 5.0)) {
                MessageSender.broadcast(PlayerSet.nearBlockEntity(this),
                        new ClientboundWailingSoundsMessage(blockPos, false));
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, config.effectDuration * 20, 0));
                MessageSender.broadcast(PlayerSet.nearBlockEntity(this),
                        new ClientboundWailingSoundsMessage(blockPos, true));
            }
        });
        this.ticks = 0;
    }
}
