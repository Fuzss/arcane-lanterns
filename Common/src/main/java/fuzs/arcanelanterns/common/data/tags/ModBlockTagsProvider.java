package fuzs.arcanelanterns.common.data.tags;

import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagsProvider extends AbstractTagProvider<Block> {

    public ModBlockTagsProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModRegistry.LANTERN_MAKER_BLOCK,
                        ModRegistry.LIFE_LANTERN_BLOCK,
                        ModRegistry.FERAL_LANTERN_BLOCK,
                        ModRegistry.LOVE_LANTERN_BLOCK,
                        ModRegistry.WAILING_LANTERN_BLOCK,
                        ModRegistry.BOREAL_LANTERN_BLOCK,
                        ModRegistry.BRILLIANT_LANTERN_BLOCK,
                        ModRegistry.WARDING_LANTERN_BLOCK,
                        ModRegistry.CONTAINING_LANTERN_BLOCK,
                        ModRegistry.WITHERING_LANTERN_BLOCK,
                        ModRegistry.CLOUD_LANTERN_BLOCK);
        this.tag(BlockTags.REPLACEABLE).add(ModRegistry.SPARK_BLOCK);
    }
}
