package fuzs.arcanelanterns.data;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.data.v1.AbstractModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(PackOutput packOutput, String modId, ExistingFileHelper fileHelper) {
        super(packOutput, modId, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleExistingBlockWithItem(ModRegistry.LANTERN_MAKER_BLOCK.get());
        this.simpleExistingBlock(ModRegistry.SPARK_BLOCK.get());
        for (Map.Entry<ResourceKey<Block>, Block> entry : ForgeRegistries.BLOCKS.getEntries()) {
            if (entry.getKey().location().getNamespace().equals(ArcaneLanterns.MOD_ID)) {
                if (entry.getValue() instanceof LanternBlock block) {
                    this.lanternBlock(block);
                }
            }
        }
    }

    public void lanternBlock(Block block) {
        ModelFile lantern = this.lantern(this.name(block), this.blockTexture(block));
        ModelFile hangingLantern = this.hangingLantern("hanging_" + this.name(block), this.blockTexture(block));
        this.lanternBlock(block, lantern, hangingLantern);
        this.basicItem(block.asItem());
    }

    public void lanternBlock(Block block, ModelFile lantern, ModelFile hangingLantern) {
        this.getVariantBuilder(block)
                .partialState().with(LanternBlock.HANGING, true).addModels(new ConfiguredModel(hangingLantern))
                .partialState().with(LanternBlock.HANGING, false).addModels(new ConfiguredModel(lantern));
    }

    public BlockModelBuilder lantern(String fileName, ResourceLocation texture) {
        return this.models().singleTexture(fileName, this.mcLoc(ModelProvider.BLOCK_FOLDER + "/template_lantern"), "lantern", texture);
    }

    public BlockModelBuilder hangingLantern(String fileName, ResourceLocation texture) {
        return this.models().singleTexture(fileName, this.mcLoc(ModelProvider.BLOCK_FOLDER + "/template_hanging_lantern"), "lantern", texture);
    }
}
