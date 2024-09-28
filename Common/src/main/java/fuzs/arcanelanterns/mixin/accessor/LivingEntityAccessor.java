package fuzs.arcanelanterns.mixin.accessor;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {

    @Invoker("dropAllDeathLoot")
    void arcanelanterns$dropAllDeathLoot(DamageSource damageSource);

    @Invoker("dropExperience")
    void arcanelanterns$dropExperience();
}
