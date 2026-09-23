package fuzs.arcanelanterns.common.data.loot;

import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractBlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.block.Blocks;

public class ModBlockLootProvider extends AbstractBlockLootSubProvider {

    public ModBlockLootProvider(LootTableSubProvider.Context context) {
        super(context);
    }

    @Override
    public void generate() {
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
