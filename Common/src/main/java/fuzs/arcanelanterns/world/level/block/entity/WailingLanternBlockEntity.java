package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.network.ClientboundWailingSoundsMessage;
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
    public void serverTick() {
        ServerConfig.EffectLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).wailingLantern;
        if (++this.ticks <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        this.getLevel()
                .getEntitiesOfClass(Player.class,
                        new AABB(this.getBlockPos().getX() + 0.5 - horizontalRange,
                                this.getBlockPos().getY() + 0.5 - verticalRange,
                                this.getBlockPos().getZ() + 0.5 - horizontalRange,
                                this.getBlockPos().getX() + 0.5 + horizontalRange,
                                this.getBlockPos().getY() + 0.5 + verticalRange,
                                this.getBlockPos().getZ() + 0.5 + horizontalRange
                        ),
                        EntitySelector.NO_CREATIVE_OR_SPECTATOR
                )
                .forEach((player) -> {
                    if (!player.blockPosition().closerThan(this.getBlockPos(), 5)) {
                        ArcaneLanterns.NETWORK.sendToAllNear(this.getBlockPos(),
                                (ServerLevel) this.getLevel(),
                                new ClientboundWailingSoundsMessage(this.getBlockPos(), false)
                        );
                    } else {
                        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, config.effectDuration * 20, 0));
                        ArcaneLanterns.NETWORK.sendToAllNear(this.getBlockPos(),
                                (ServerLevel) this.getLevel(),
                                new ClientboundWailingSoundsMessage(this.getBlockPos(), true)
                        );
                    }
                });
        this.ticks = 0;
    }
}
