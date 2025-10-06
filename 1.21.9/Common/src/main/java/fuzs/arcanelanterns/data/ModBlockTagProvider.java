package fuzs.arcanelanterns.data;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagProvider extends AbstractTagProvider<Block> {

    public ModBlockTagProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModRegistry.LANTERN_MAKER_BLOCK.value(),
                        ModRegistry.LIFE_LANTERN_BLOCK.value(),
                        ModRegistry.FERAL_LANTERN_BLOCK.value(),
                        ModRegistry.LOVE_LANTERN_BLOCK.value(),
                        ModRegistry.WAILING_LANTERN_BLOCK.value(),
                        ModRegistry.BOREAL_LANTERN_BLOCK.value(),
                        ModRegistry.BRILLIANT_LANTERN_BLOCK.value(),
                        ModRegistry.WARDING_LANTERN_BLOCK.value(),
                        ModRegistry.CONTAINING_LANTERN_BLOCK.value(),
                        ModRegistry.WITHERING_LANTERN_BLOCK.value(),
                        ModRegistry.CLOUD_LANTERN_BLOCK.value());
        this.tag(BlockTags.REPLACEABLE).add(ModRegistry.SPARK_BLOCK.value());
    }
}
