package fuzs.arcanelanterns.init;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import fuzs.arcanelanterns.world.level.block.*;
import fuzs.arcanelanterns.world.level.block.entity.*;
import fuzs.puzzleslib.api.init.v2.RegistryManager;
import fuzs.puzzleslib.api.init.v2.RegistryReference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModRegistry {
    static final RegistryManager REGISTRY = RegistryManager.instant(ArcaneLanterns.MOD_ID);
    public static final RegistryReference<Block> LANTERN_MAKER_BLOCK = REGISTRY.registerBlock("lantern_maker", () -> new LanternMakerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(4.0F).noOcclusion()));
    public static final RegistryReference<Block> SPARK_BLOCK = REGISTRY.registerBlock("spark", () -> new SparkBlock(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).replaceable().sound(SoundType.WOOL).randomTicks().instabreak().noLootTable().lightLevel(state -> {
        return 15;
    }).noCollission().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryReference<Block> LIFE_LANTERN_BLOCK = REGISTRY.registerBlock("life_lantern", () -> new LifeLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> FERAL_LANTERN_BLOCK = REGISTRY.registerBlock("feral_lantern", () -> new FeralLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> LOVE_LANTERN_BLOCK = REGISTRY.registerBlock("love_lantern", () -> new LoveLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> WAILING_LANTERN_BLOCK = REGISTRY.registerBlock("wailing_lantern", () -> new WailingLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> BOREAL_LANTERN_BLOCK = REGISTRY.registerBlock("boreal_lantern", () -> new BorealLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> BRILLIANT_LANTERN_BLOCK = REGISTRY.registerBlock("brilliant_lantern", () -> new BrilliantLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> WARDING_LANTERN_BLOCK = REGISTRY.registerBlock("warding_lantern", () -> new WardingLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> CONTAINING_LANTERN_BLOCK = REGISTRY.registerBlock("containing_lantern", () -> new ContainingLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> WITHERING_LANTERN_BLOCK = REGISTRY.registerBlock("withering_lantern", () -> new WitheringLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Block> CLOUD_LANTERN_BLOCK = REGISTRY.registerBlock("cloud_lantern", () -> new CloudLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));
    public static final RegistryReference<Item> LANTERN_MAKER_ITEM = REGISTRY.registerBlockItem(LANTERN_MAKER_BLOCK);
    public static final RegistryReference<Item> LIFE_LANTERN_ITEM = REGISTRY.registerBlockItem(LIFE_LANTERN_BLOCK);
    public static final RegistryReference<Item> FERAL_LANTERN_ITEM = REGISTRY.registerBlockItem(FERAL_LANTERN_BLOCK);
    public static final RegistryReference<Item> LOVE_LANTERN_ITEM = REGISTRY.registerBlockItem(LOVE_LANTERN_BLOCK);
    public static final RegistryReference<Item> WAILING_LANTERN_ITEM = REGISTRY.registerBlockItem(WAILING_LANTERN_BLOCK);
    public static final RegistryReference<Item> BOREAL_LANTERN_ITEM = REGISTRY.registerBlockItem(BOREAL_LANTERN_BLOCK);
    public static final RegistryReference<Item> BRILLIANT_LANTERN_ITEM = REGISTRY.registerBlockItem(BRILLIANT_LANTERN_BLOCK);
    public static final RegistryReference<Item> WARDING_LANTERN_ITEM = REGISTRY.registerBlockItem(WARDING_LANTERN_BLOCK);
    public static final RegistryReference<Item> CONTAINING_LANTERN_ITEM = REGISTRY.registerBlockItem(CONTAINING_LANTERN_BLOCK);
    public static final RegistryReference<Item> WITHERING_LANTERN_ITEM = REGISTRY.registerBlockItem(WITHERING_LANTERN_BLOCK);
    public static final RegistryReference<Item> CLOUD_LANTERN_ITEM = REGISTRY.registerBlockItem(CLOUD_LANTERN_BLOCK);
    public static final RegistryReference<BlockEntityType<LanternMakerBlockEntity>> LANTERN_MAKER_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("lantern_maker", () -> BlockEntityType.Builder.of(LanternMakerBlockEntity::new, LANTERN_MAKER_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<SparkBlockEntity>> SPARK_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("spark", () -> BlockEntityType.Builder.of(SparkBlockEntity::new, SPARK_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<LifeLanternBlockEntity>> LIFE_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("life_lantern", () -> BlockEntityType.Builder.of(LifeLanternBlockEntity::new, LIFE_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<FeralLanternBlockEntity>> FERAL_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("feral_lantern", () -> BlockEntityType.Builder.of(FeralLanternBlockEntity::new, FERAL_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<LoveLanternBlockEntity>> LOVE_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("love_lantern", () -> BlockEntityType.Builder.of(LoveLanternBlockEntity::new, LOVE_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<WailingLanternBlockEntity>> WAILING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("wailing_lantern", () -> BlockEntityType.Builder.of(WailingLanternBlockEntity::new, WAILING_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<BorealLanternBlockEntity>> BOREAL_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("boreal_lantern", () -> BlockEntityType.Builder.of(BorealLanternBlockEntity::new, BOREAL_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<BrilliantLanternBlockEntity>> BRILLIANT_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("brilliant_lantern", () -> BlockEntityType.Builder.of(BrilliantLanternBlockEntity::new, BRILLIANT_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<WardingLanternBlockEntity>> WARDING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("warding_lantern", () -> BlockEntityType.Builder.of(WardingLanternBlockEntity::new, WARDING_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<ContainingLanternBlockEntity>> CONTAINING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("containing_lantern", () -> BlockEntityType.Builder.of(ContainingLanternBlockEntity::new, CONTAINING_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<WitheringLanternBlockEntity>> WITHERING_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("withering_lantern", () -> BlockEntityType.Builder.of(WitheringLanternBlockEntity::new, WITHERING_LANTERN_BLOCK.get()));
    public static final RegistryReference<BlockEntityType<CloudLanternBlockEntity>> CLOUD_LANTERN_BLOCK_ENTITY = REGISTRY.registerBlockEntityType("cloud_lantern", () -> BlockEntityType.Builder.of(CloudLanternBlockEntity::new, CLOUD_LANTERN_BLOCK.get()));
    public static final RegistryReference<RecipeType<LanternMakingRecipe>> LANTERN_MAKING_RECIPE_TYPE = REGISTRY.registerRecipeType("lantern_making");
    public static final RegistryReference<RecipeSerializer<LanternMakingRecipe>> LANTERN_MAKING_RECIPE_SERIALIZER = REGISTRY.register(Registries.RECIPE_SERIALIZER, "lantern_making", () -> new LanternMakingRecipe.Serializer());

    public static void touch() {

    }
}
