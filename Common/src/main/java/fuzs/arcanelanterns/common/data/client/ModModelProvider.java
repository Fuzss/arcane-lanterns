package fuzs.arcanelanterns.common.data.client;

import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v3.models.AbstractModelProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators generators) {
        generators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModRegistry.LANTERN_MAKER_BLOCK.value(),
                BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModRegistry.LANTERN_MAKER_BLOCK.value()))));
        generators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModRegistry.SPARK_BLOCK.value(),
                BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModRegistry.SPARK_BLOCK.value()))));
        generators.createLantern(ModRegistry.LIFE_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.FERAL_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.LOVE_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.WAILING_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.BOREAL_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.BRILLIANT_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.WARDING_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.CONTAINING_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.WITHERING_LANTERN_BLOCK.value());
        generators.createLantern(ModRegistry.CLOUD_LANTERN_BLOCK.value());
    }
}
