package fuzs.arcanelanterns.data;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.world.level.block.Blocks;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        this.dropSelf(ModRegistry.LANTERN_MAKER_BLOCK.value());
        this.dropSelf(ModRegistry.LIFE_LANTERN_BLOCK.value());
        this.dropOther(ModRegistry.FERAL_LANTERN_BLOCK.value(), Blocks.LANTERN);
        this.dropSelf(ModRegistry.LOVE_LANTERN_BLOCK.value());
        this.dropSelf(ModRegistry.WAILING_LANTERN_BLOCK.value());
        this.dropSelf(ModRegistry.BOREAL_LANTERN_BLOCK.value());
        this.dropSelf(ModRegistry.BRILLIANT_LANTERN_BLOCK.value());
        this.dropSelf(ModRegistry.WARDING_LANTERN_BLOCK.value());
        this.dropSelf(ModRegistry.CONTAINING_LANTERN_BLOCK.value());
        this.dropSelf(ModRegistry.WITHERING_LANTERN_BLOCK.value());
        this.dropSelf(ModRegistry.CLOUD_LANTERN_BLOCK.value());
    }
}
