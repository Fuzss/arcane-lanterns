package fuzs.arcanelanterns.data;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.data.v1.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends AbstractTagProvider.Blocks {

    public ModBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, ExistingFileHelper fileHelper) {
        super(packOutput, lookupProvider, modId, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.LANTERN_MAKER_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.LIFE_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.FERAL_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.LOVE_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.WAILING_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.BOREAL_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.BRILLIANT_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.WARDING_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.CONTAINING_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.WITHERING_LANTERN_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.CLOUD_LANTERN_BLOCK.get());
        this.tag(BlockTags.REPLACEABLE).add(ModRegistry.SPARK_BLOCK.get());
    }
}
