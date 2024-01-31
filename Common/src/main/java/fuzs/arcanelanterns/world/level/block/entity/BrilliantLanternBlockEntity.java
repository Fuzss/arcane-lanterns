package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.mixin.accessor.LivingEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BrilliantLanternBlockEntity extends LanternBlockEntity {

    public BrilliantLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.BRILLIANT_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick() {
        ServerConfig.BrilliantLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).brilliantLantern;
        if (++this.ticks <= config.delay) return;
        final int horizontalRange = config.horizontalRange;
        final int verticalRange = config.verticalRange;
        List<Animal> animals = this.getLevel()
                .getEntitiesOfClass(Animal.class,
                        new AABB(this.getBlockPos().getX() + 0.5 - horizontalRange,
                                this.getBlockPos().getY() + 0.5 - verticalRange,
                                this.getBlockPos().getZ() + 0.5 - horizontalRange,
                                this.getBlockPos().getX() + 0.5 + horizontalRange,
                                this.getBlockPos().getY() + 0.5 + verticalRange,
                                this.getBlockPos().getZ() + 0.5 + horizontalRange
                        ),
                        BrilliantLanternBlockEntity::isValidAnimal
                );
        if (!animals.isEmpty()) {
            Animal animal = animals.get(0);
            // make sure equipment still drops, but nothing else
            killWithoutLoot(this.getLevel(), animal);
            // allow experience to drop
            animal.setLastHurtByPlayer(null);
            ((LivingEntityAccessor) animal).arcanelanterns$dropExperience();
        }
        this.ticks = 0;
    }

    private static boolean isValidAnimal(Animal animal) {
        return animal.shouldDropExperience() && (!(animal instanceof TamableAnimal tamableAnimal) || !tamableAnimal.isTame()) && !ArcaneLanterns.CONFIG.get(
                ServerConfig.class).brilliantLantern.blacklist.contains(animal.getType());
    }

    private static void killWithoutLoot(Level level, LivingEntity entity) {
        boolean doMobLoot = level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
        level.getGameRules().getRule(GameRules.RULE_DOMOBLOOT).set(false, level.getServer());
        entity.kill();
        level.getGameRules().getRule(GameRules.RULE_DOMOBLOOT).set(doMobLoot, level.getServer());
    }
}
