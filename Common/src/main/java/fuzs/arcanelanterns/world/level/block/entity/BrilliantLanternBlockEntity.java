package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BrilliantLanternBlockEntity extends LanternBlockEntity {

    public BrilliantLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.BRILLIANT_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        ServerConfig.BrilliantLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).brilliantLantern;
        if (++this.ticks <= config.delay) {
            return;
        }

        int horizontalRange = config.horizontalRange;
        int verticalRange = config.verticalRange;
        List<Animal> animals = serverLevel.getEntitiesOfClass(Animal.class,
                new AABB(blockPos.getX() + 0.5 - horizontalRange,
                        blockPos.getY() + 0.5 - verticalRange,
                        blockPos.getZ() + 0.5 - horizontalRange,
                        blockPos.getX() + 0.5 + horizontalRange,
                        blockPos.getY() + 0.5 + verticalRange,
                        blockPos.getZ() + 0.5 + horizontalRange),
                BrilliantLanternBlockEntity::isValidAnimal);
        if (!animals.isEmpty()) {
            Animal animal = animals.getFirst();
            // make sure equipment still drops, but nothing else
            killWithoutLoot(serverLevel, animal);
            // allow experience to drop
            animal.setLastHurtByPlayer((EntityReference<Player>) null, 100);
            animal.dropExperience(serverLevel, null);
            animal.skipDropExperience();
        }

        this.ticks = 0;
    }

    private static boolean isValidAnimal(Animal animal) {
        return animal.shouldDropExperience() && (!(animal instanceof TamableAnimal tamableAnimal)
                || !tamableAnimal.isTame())
                && !ArcaneLanterns.CONFIG.get(ServerConfig.class).brilliantLantern.blacklist.contains(animal.getType());
    }

    private static void killWithoutLoot(ServerLevel serverLevel, LivingEntity entity) {
        boolean doMobLoot = serverLevel.getGameRules().get(GameRules.MOB_DROPS);
        serverLevel.getGameRules().set(GameRules.MOB_DROPS, false, serverLevel.getServer());
        entity.kill(serverLevel);
        serverLevel.getGameRules().set(GameRules.MOB_DROPS, doMobLoot, serverLevel.getServer());
    }
}
