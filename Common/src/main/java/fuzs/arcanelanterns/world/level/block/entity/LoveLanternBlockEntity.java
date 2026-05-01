package fuzs.arcanelanterns.world.level.block.entity;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.config.ServerConfig;
import fuzs.arcanelanterns.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LoveLanternBlockEntity extends LanternBlockEntity {

    public LoveLanternBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.LOVE_LANTERN_BLOCK_ENTITY.value(), pos, state);
    }

    @Override
    public void serverTick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        ServerConfig.LoveLanternConfig config = ArcaneLanterns.CONFIG.get(ServerConfig.class).loveLantern;
        if (++this.ticks <= config.delay) {
            return;
        }

        int horizontalRange = config.horizontalRange;
        int verticalRange = config.verticalRange;
        List<Animal> nearbyAnimals = serverLevel.getEntitiesOfClass(Animal.class,
                new AABB(blockPos.getX() - horizontalRange,
                        blockPos.getY() - verticalRange,
                        blockPos.getZ() - horizontalRange,
                        blockPos.getX() + horizontalRange,
                        blockPos.getY() + verticalRange,
                        blockPos.getZ() + horizontalRange));
        Collection<List<Animal>> animalsByType = nearbyAnimals.stream()
                .collect(Collectors.groupingBy(Entity::getType))
                .values();
        for (List<Animal> animals : animalsByType) {
            if (animals.size() <= config.maxAnimals) {
                animals.removeIf((Animal animal) -> {
                    return animal.getAge() != 0 || !animal.canFallInLove();
                });
                if (animals.size() >= 2) {
                    Collections.shuffle(animals);
                    for (int i = 0; i < 2; i++) {
                        animals.get(i).setInLove(null);
                    }
                }
            }
        }

        this.ticks = 0;
    }
}
