package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.GameRules;
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
                                this.getBlockPos().getZ() + 0.5 + horizontalRange),
                        BrilliantLanternBlockEntity::isValidAnimal);
        if (!animals.isEmpty()) {
            Animal animal = animals.getFirst();
            // make sure equipment still drops, but nothing else
            killWithoutLoot((ServerLevel) this.getLevel(), animal);
            // allow experience to drop
            animal.lastHurtByPlayer = null;
            animal.dropExperience((ServerLevel) this.getLevel(), null);
            animal.skipDropExperience();
        }
        this.ticks = 0;
    }

    private static boolean isValidAnimal(Animal animal) {
        return animal.shouldDropExperience() &&
                (!(animal instanceof TamableAnimal tamableAnimal) || !tamableAnimal.isTame()) &&
                !ArcaneLanterns.CONFIG.get(ServerConfig.class).brilliantLantern.blacklist.contains(animal.getType());
    }

    private static void killWithoutLoot(ServerLevel serverLevel, LivingEntity entity) {
        boolean doMobLoot = serverLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
        serverLevel.getGameRules().getRule(GameRules.RULE_DOMOBLOOT).set(false, serverLevel.getServer());
        entity.kill(serverLevel);
        serverLevel.getGameRules().getRule(GameRules.RULE_DOMOBLOOT).set(doMobLoot, serverLevel.getServer());
    }
}
