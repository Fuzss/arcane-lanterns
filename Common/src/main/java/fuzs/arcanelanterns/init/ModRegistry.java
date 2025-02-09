package fuzs.arcanelanterns.init;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import fuzs.arcanelanterns.world.level.block.ArcaneLanternBlock;
import fuzs.arcanelanterns.world.level.block.LanternMakerBlock;
import fuzs.arcanelanterns.world.level.block.SparkBlock;
import fuzs.arcanelanterns.world.level.block.entity.*;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
    static final RegistryManager REGISTRIES = RegistryManager.from(ArcaneLanterns.MOD_ID);
    public static final Holder.Reference<Block> LANTERN_MAKER_BLOCK = REGISTRIES.registerBlock("lantern_maker",
            LanternMakerBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(4.0F).noOcclusion());
    public static final Holder.Reference<Block> SPARK_BLOCK = REGISTRIES.registerBlock("spark",
            SparkBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .replaceable()
                    .sound(SoundType.WOOL)
                    .randomTicks()
                    .instabreak()
                    .noLootTable()
                    .lightLevel((BlockState blockState) -> {
                        return 15;
                    })
                    .noCollission()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY));
    public static final Holder.Reference<Block> LIFE_LANTERN_BLOCK = REGISTRIES.registerBlock("life_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.LIFE_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> FERAL_LANTERN_BLOCK = REGISTRIES.registerBlock("feral_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.FERAL_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> LOVE_LANTERN_BLOCK = REGISTRIES.registerBlock("love_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.LOVE_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> WAILING_LANTERN_BLOCK = REGISTRIES.registerBlock("wailing_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.WAILING_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> BOREAL_LANTERN_BLOCK = REGISTRIES.registerBlock("boreal_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.BOREAL_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> BRILLIANT_LANTERN_BLOCK = REGISTRIES.registerBlock("brilliant_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.BRILLIANT_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> WARDING_LANTERN_BLOCK = REGISTRIES.registerBlock("warding_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.WARDING_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> CONTAINING_LANTERN_BLOCK = REGISTRIES.registerBlock("containing_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.CONTAINING_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> WITHERING_LANTERN_BLOCK = REGISTRIES.registerBlock("withering_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.WITHERING_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Block> CLOUD_LANTERN_BLOCK = REGISTRIES.registerBlock("cloud_lantern",
            (BlockBehaviour.Properties properties) -> new ArcaneLanternBlock(() -> ModRegistry.CLOUD_LANTERN_BLOCK_ENTITY.value(),
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN));
    public static final Holder.Reference<Item> LANTERN_MAKER_ITEM = REGISTRIES.registerBlockItem(LANTERN_MAKER_BLOCK);
    public static final Holder.Reference<Item> LIFE_LANTERN_ITEM = REGISTRIES.registerBlockItem(LIFE_LANTERN_BLOCK);
    public static final Holder.Reference<Item> FERAL_LANTERN_ITEM = REGISTRIES.registerBlockItem(FERAL_LANTERN_BLOCK);
    public static final Holder.Reference<Item> LOVE_LANTERN_ITEM = REGISTRIES.registerBlockItem(LOVE_LANTERN_BLOCK);
    public static final Holder.Reference<Item> WAILING_LANTERN_ITEM = REGISTRIES.registerBlockItem(WAILING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> BOREAL_LANTERN_ITEM = REGISTRIES.registerBlockItem(BOREAL_LANTERN_BLOCK);
    public static final Holder.Reference<Item> BRILLIANT_LANTERN_ITEM = REGISTRIES.registerBlockItem(
            BRILLIANT_LANTERN_BLOCK);
    public static final Holder.Reference<Item> WARDING_LANTERN_ITEM = REGISTRIES.registerBlockItem(WARDING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> CONTAINING_LANTERN_ITEM = REGISTRIES.registerBlockItem(
            CONTAINING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> WITHERING_LANTERN_ITEM = REGISTRIES.registerBlockItem(
            WITHERING_LANTERN_BLOCK);
    public static final Holder.Reference<Item> CLOUD_LANTERN_ITEM = REGISTRIES.registerBlockItem(CLOUD_LANTERN_BLOCK);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(() -> new ItemStack(
            Items.LANTERN));
    public static final Holder.Reference<BlockEntityType<LanternMakerBlockEntity>> LANTERN_MAKER_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "lantern_maker",
            LanternMakerBlockEntity::new,
            LANTERN_MAKER_BLOCK);
    public static final Holder.Reference<BlockEntityType<SparkBlockEntity>> SPARK_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "spark",
            SparkBlockEntity::new,
            SPARK_BLOCK);
    public static final Holder.Reference<BlockEntityType<LifeLanternBlockEntity>> LIFE_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "life_lantern",
            LifeLanternBlockEntity::new,
            LIFE_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<FeralLanternBlockEntity>> FERAL_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "feral_lantern",
            FeralLanternBlockEntity::new,
            FERAL_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<LoveLanternBlockEntity>> LOVE_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "love_lantern",
            LoveLanternBlockEntity::new,
            LOVE_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<WailingLanternBlockEntity>> WAILING_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "wailing_lantern",
            WailingLanternBlockEntity::new,
            WAILING_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<BorealLanternBlockEntity>> BOREAL_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "boreal_lantern",
            BorealLanternBlockEntity::new,
            BOREAL_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<BrilliantLanternBlockEntity>> BRILLIANT_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "brilliant_lantern",
            BrilliantLanternBlockEntity::new,
            BRILLIANT_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<WardingLanternBlockEntity>> WARDING_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "warding_lantern",
            WardingLanternBlockEntity::new,
            WARDING_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<ContainingLanternBlockEntity>> CONTAINING_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "containing_lantern",
            ContainingLanternBlockEntity::new,
            CONTAINING_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<WitheringLanternBlockEntity>> WITHERING_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "withering_lantern",
            WitheringLanternBlockEntity::new,
            WITHERING_LANTERN_BLOCK);
    public static final Holder.Reference<BlockEntityType<CloudLanternBlockEntity>> CLOUD_LANTERN_BLOCK_ENTITY = REGISTRIES.registerBlockEntityType(
            "cloud_lantern",
            CloudLanternBlockEntity::new,
            CLOUD_LANTERN_BLOCK);
    public static final Holder.Reference<RecipeType<LanternMakingRecipe>> LANTERN_MAKING_RECIPE_TYPE = REGISTRIES.registerRecipeType(
            "lantern_making");
    public static final Holder.Reference<RecipeSerializer<LanternMakingRecipe>> LANTERN_MAKING_RECIPE_SERIALIZER = REGISTRIES.register(
            Registries.RECIPE_SERIALIZER,
            "lantern_making",
            LanternMakingRecipe.Serializer::new);

    public static void bootstrap() {
        // NO-OP
    }
}
