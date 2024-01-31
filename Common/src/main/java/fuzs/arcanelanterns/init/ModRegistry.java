package fuzs.arcanelanterns.init;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import fuzs.arcanelanterns.world.level.block.ArcaneLanternBlock;
import fuzs.arcanelanterns.world.level.block.LanternMakerBlock;
import fuzs.arcanelanterns.world.level.block.SparkBlock;
import fuzs.arcanelanterns.world.level.block.entity.*;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModRegistry {
    static final RegistryManager REGISTRY = RegistryManager.from(ArcaneLanterns.MOD_ID);
    public static final Holder.Reference<Block> LANTERN_MAKER_BLOCK = REGISTRY.registerBlock("lantern_maker", () -> new LanternMakerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(4.0F).noOcclusion()));
    public static final Holder.Reference<Block> SPARK_BLOCK = REGISTRY.registerBlock("spark", () -> new SparkBlock(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).replaceable().sound(SoundType.WOOL).randomTicks().instabreak().noLootTable().lightLevel(
            (BlockState state) -> {
        return 15;
    }).noCollission().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Holder.Reference<Block> LIFE_LANTERN_BLOCK = REGISTRY.registerBlock("life_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.LIFE_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> FERAL_LANTERN_BLOCK = REGISTRY.registerBlock("feral_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.FERAL_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> LOVE_LANTERN_BLOCK = REGISTRY.registerBlock("love_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.LOVE_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> WAILING_LANTERN_BLOCK = REGISTRY.registerBlock("wailing_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.WAILING_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> BOREAL_LANTERN_BLOCK = REGISTRY.registerBlock("boreal_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.BOREAL_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> BRILLIANT_LANTERN_BLOCK = REGISTRY.registerBlock("brilliant_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.BRILLIANT_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> WARDING_LANTERN_BLOCK = REGISTRY.registerBlock("warding_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.WARDING_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> CONTAINING_LANTERN_BLOCK = REGISTRY.registerBlock("containing_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.CONTAINING_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> WITHERING_LANTERN_BLOCK = REGISTRY.registerBlock("withering_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.WITHERING_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Block> CLOUD_LANTERN_BLOCK = REGISTRY.registerBlock("cloud_lantern", () -> new ArcaneLanternBlock(
            () -> ModRegistry.CLOUD_LANTERN_BLOCK_ENTITY.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Holder.Reference<Item> LANTERN_MAKER_ITEM = REGISTRY.registerBlockItem(LANTERN_MAKER_BLOCK);
    public static final Holder.Reference<Item> LIFE_LANTERN_ITEM = REGISTRY.registerBlockItem(LIFE_LANTERN_BLOCK);
    public static final Holder.Reference<Item> FERAL_LANTERN_ITEM = REGISTRY.registerBlockItem(FERAL_LANTERN_BLOCK);
    public static final Holder.Reference<Item> LOVE_LANTERN_ITEM = REGISTRY.registerBlockItem(LOVE_LANTERN_BLOCK);
    public static final Holder.Reference<Item> WAILING_LANTERN_ITEM = REGISTRY.registerBlockItem(WAILING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> BOREAL_LANTERN_ITEM = REGISTRY.registerBlockItem(BOREAL_LANTERN_BLOCK);
    public static final Holder.Reference<Item> BRILLIANT_LANTERN_ITEM = REGISTRY.registerBlockItem(BRILLIANT_LANTERN_BLOCK);
    public static final Holder.Reference<Item> WARDING_LANTERN_ITEM = REGISTRY.registerBlockItem(WARDING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> CONTAINING_LANTERN_ITEM = REGISTRY.registerBlockItem(CONTAINING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> WITHERING_LANTERN_ITEM = REGISTRY.registerBlockItem(WITHERING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> CLOUD_LANTERN_ITEM = REGISTRY.registerBlockItem(CLOUD_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<LanternMakerBlockEntity>> LANTERN_MAKER_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("lantern_maker", () -> BlockEntityType.Builder.of(
            LanternMakerBlockEntity::new, LANTERN_MAKER_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<SparkBlockEntity>> SPARK_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("spark", () -> BlockEntityType.Builder.of(
            SparkBlockEntity::new, SPARK_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<LifeLanternBlockEntity>> LIFE_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("life_lantern", () -> BlockEntityType.Builder.of(
            LifeLanternBlockEntity::new, LIFE_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<FeralLanternBlockEntity>> FERAL_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("feral_lantern", () -> BlockEntityType.Builder.of(
            FeralLanternBlockEntity::new, FERAL_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<LoveLanternBlockEntity>> LOVE_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("love_lantern", () -> BlockEntityType.Builder.of(
            LoveLanternBlockEntity::new, LOVE_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<WailingLanternBlockEntity>> WAILING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("wailing_lantern", () -> BlockEntityType.Builder.of(
            WailingLanternBlockEntity::new, WAILING_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<BorealLanternBlockEntity>> BOREAL_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("boreal_lantern", () -> BlockEntityType.Builder.of(
            BorealLanternBlockEntity::new, BOREAL_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<BrilliantLanternBlockEntity>> BRILLIANT_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("brilliant_lantern", () -> BlockEntityType.Builder.of(
            BrilliantLanternBlockEntity::new, BRILLIANT_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<WardingLanternBlockEntity>> WARDING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("warding_lantern", () -> BlockEntityType.Builder.of(
            WardingLanternBlockEntity::new, WARDING_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<ContainingLanternBlockEntity>> CONTAINING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("containing_lantern", () -> BlockEntityType.Builder.of(
            ContainingLanternBlockEntity::new, CONTAINING_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<WitheringLanternBlockEntity>> WITHERING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("withering_lantern", () -> BlockEntityType.Builder.of(
            WitheringLanternBlockEntity::new, WITHERING_LANTERN_BLOCK.value()));
    public static final Holder.Reference<BlockEntityType<CloudLanternBlockEntity>> CLOUD_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("cloud_lantern", () -> BlockEntityType.Builder.of(
            CloudLanternBlockEntity::new, CLOUD_LANTERN_BLOCK.value()));
    public static final Holder.Reference<RecipeType<LanternMakingRecipe>> LANTERN_MAKING_RECIPE_TYPE = REGISTRY.registerRecipeType("lantern_making");
    public static final Holder.Reference<RecipeSerializer<LanternMakingRecipe>> LANTERN_MAKING_RECIPE_SERIALIZER = REGISTRY.register(Registries.RECIPE_SERIALIZER, "lantern_making",
            () -> new LanternMakingRecipe.Serializer()
    );

    public static void touch() {

    }
}
