package fuzs.arcanelanterns.data.client;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModRegistry.LANTERN_MAKER_BLOCK.value(),
                ModelLocationUtils.getModelLocation(ModRegistry.LANTERN_MAKER_BLOCK.value())));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModRegistry.SPARK_BLOCK.value(),
                ModelLocationUtils.getModelLocation(ModRegistry.SPARK_BLOCK.value())));
        blockModelGenerators.createLantern(ModRegistry.LIFE_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.FERAL_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.LOVE_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.WAILING_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.BOREAL_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.BRILLIANT_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.WARDING_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.CONTAINING_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.WITHERING_LANTERN_BLOCK.value());
        blockModelGenerators.createLantern(ModRegistry.CLOUD_LANTERN_BLOCK.value());
    }
}
