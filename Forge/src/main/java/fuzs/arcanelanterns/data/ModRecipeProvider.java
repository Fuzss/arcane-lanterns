package fuzs.arcanelanterns.data;

import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.data.v1.AbstractRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModRegistry.LANTERN_MAKER_BLOCK.get())
                .define('X', Items.DIAMOND)
                .define('#', Items.POLISHED_BASALT)
                .pattern("#X#")
                .pattern(" # ")
                .pattern("###")
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.LIFE_LANTERN_BLOCK.get())
                .requires(Items.EGG)
                .requires(Items.MELON)
                .requires(Items.GOLDEN_APPLE)
                .requires(Items.SUGAR_CANE)
                .requires(Items.BONE_MEAL)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.FERAL_LANTERN_BLOCK.get())
                .requires(Items.GLOWSTONE)
                .requires(Items.JACK_O_LANTERN)
                .requires(Items.FIRE_CHARGE)
                .requires(Items.BLAZE_POWDER)
                .requires(Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.LOVE_LANTERN_BLOCK.get())
                .requires(Items.DIAMOND)
                .requires(Items.RABBIT_FOOT)
                .requires(Items.GOLDEN_CARROT)
                .requires(Items.BEETROOT)
                .requires(ModRegistry.LIFE_LANTERN_BLOCK.get())
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.WAILING_LANTERN_BLOCK.get())
                .requires(Items.GHAST_TEAR)
                .requires(Items.WARPED_ROOTS)
                .requires(Items.FLINT)
                .requires(Items.PUFFERFISH)
                .requires(Items.INK_SAC)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.BOREAL_LANTERN_BLOCK.get())
                .requires(Items.COBWEB)
                .requires(Items.SNOWBALL)
                .requires(Items.PACKED_ICE)
                .requires(Items.QUARTZ)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.BRILLIANT_LANTERN_BLOCK.get())
                .requires(Items.SHULKER_SHELL)
                .requires(Items.PAPER)
                .requires(Items.SNOWBALL)
                .requires(Items.PHANTOM_MEMBRANE)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.WARDING_LANTERN_BLOCK.get())
                .requires(Items.WARPED_FUNGUS)
                .requires(Items.PUFFERFISH)
                .requires(Items.IRON_DOOR)
                .requires(Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.CONTAINING_LANTERN_BLOCK.get())
                .requires(ModRegistry.WARDING_LANTERN_BLOCK.get())
                .requires(Items.FISHING_ROD)
                .requires(Items.COBWEB)
                .requires(Items.CHAIN)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.WITHERING_LANTERN_BLOCK.get())
                .requires(Items.WITHER_ROSE)
                .requires(Items.SOUL_SAND)
                .requires(Items.FIREWORK_STAR)
                .requires(Items.COAL)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
        LanternMakingRecipeBuilder.recipe(ModRegistry.CLOUD_LANTERN_BLOCK.get())
                .requires(Items.PHANTOM_MEMBRANE)
                .requires(Items.SOUL_TORCH)
                .requires(Items.SNOW_BLOCK)
                .requires(Items.WHITE_WOOL)
                .unlockedBy(getHasName(Items.LANTERN), has(Items.LANTERN, Items.SOUL_LANTERN))
                .save(consumer);
    }

    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike... itemLikes) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(itemLikes).build());
    }
}
