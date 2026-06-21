package fuzs.arcanelanterns.common.integration.rei;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.arcanelanterns.common.world.item.crafting.LanternMakingRecipe;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;

public class ArcaneLanternsReiPlugin implements REICommonPlugin {

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(LanternMakingRecipe.class)
                .filterType(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value())
                .fill(LanternMakingDisplay::new);
    }

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(ArcaneLanterns.id("default/lantern_making"), LanternMakingDisplay.SERIALIZER);
    }
}
