package fuzs.arcanelanterns.data;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.data.v1.AbstractLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(PackOutput packOutput, String modId) {
        super(packOutput, modId);
    }

    @Override
    public void generate() {
        this.dropSelf(ModRegistry.LANTERN_MAKER_BLOCK.get());
        this.dropSelf(ModRegistry.LIFE_LANTERN_BLOCK.get());
        this.dropOther(ModRegistry.FERAL_LANTERN_BLOCK.get(), Blocks.LANTERN);
        this.dropSelf(ModRegistry.LOVE_LANTERN_BLOCK.get());
        this.dropSelf(ModRegistry.WAILING_LANTERN_BLOCK.get());
        this.dropSelf(ModRegistry.BOREAL_LANTERN_BLOCK.get());
        this.dropSelf(ModRegistry.BRILLIANT_LANTERN_BLOCK.get());
        this.dropSelf(ModRegistry.WARDING_LANTERN_BLOCK.get());
        this.dropSelf(ModRegistry.CONTAINING_LANTERN_BLOCK.get());
        this.dropSelf(ModRegistry.WITHERING_LANTERN_BLOCK.get());
        this.dropSelf(ModRegistry.CLOUD_LANTERN_BLOCK.get());
    }
}
