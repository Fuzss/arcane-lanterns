package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WitheringLanternBlockEntity extends LanternBlockEntity {

    public WitheringLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.WITHERING_LANTERN_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WitheringLanternBlockEntity blockEntity) {
        ServerConfig.EffectLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).witheringLantern;
        if (++blockEntity.count <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        level.getEntitiesOfClass(LivingEntity.class, new AABB(pos.getX() + 0.5 - horizontalRange, pos.getY() + 0.5 - verticalRange, pos.getZ() + 0.5 - horizontalRange, pos.getX() + 0.5 + horizontalRange, pos.getY() + 0.5 + verticalRange, pos.getZ() + 0.5 + horizontalRange), entity -> !(entity instanceof Player) && !entity.isInvulnerableTo(level.damageSources().wither())).forEach((entity) -> {
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, config.effectDuration * 20, 0));
        });
        blockEntity.count = 0;
    }
}
