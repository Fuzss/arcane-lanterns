package fuzs.arcanelanterns.mixin.accessor;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {

    @Invoker("dropAllDeathLoot")
    void arcanelanterns$dropAllDeathLoot(ServerLevel level, DamageSource damageSource);

    @Invoker("dropExperience")
    void arcanelanterns$dropExperience(ServerLevel level, @Nullable Entity entity);
}
