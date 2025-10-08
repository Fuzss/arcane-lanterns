//package fuzs.arcanelanterns.integration.jei;
//
//import com.mojang.serialization.Codec;
//import fuzs.arcanelanterns.init.ModRegistry;
//import fuzs.arcanelanterns.integration.LanternMakingRecipeHelper;
//import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
//import mezz.jei.api.helpers.ICodecHelper;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.IFocusGroup;
//import mezz.jei.api.recipe.IRecipeManager;
//import mezz.jei.api.recipe.RecipeIngredientRole;
//import mezz.jei.api.recipe.category.AbstractRecipeCategory;
//import mezz.jei.api.recipe.types.IRecipeHolderType;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
//import net.minecraft.world.item.crafting.display.SlotDisplay;
//import net.minecraft.world.phys.Vec2;
//
//import java.util.List;
//
///**
// * Some of this code comes from <a
// * href="https://github.com/VazkiiMods/Botania/blob/1.19.x/Xplat/src/main/java/vazkii/botania/client/integration/jei/RunicAltarRecipeCategory.java">Botania's
// * Runic Altar</a>, mainly the method for evenly placing recipe ingredients in a circle around a center point.
// */
//public class LanternMakingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<LanternMakingRecipe>> {
//    public static final IRecipeHolderType<LanternMakingRecipe> LANTERN_MAKING_RECIPE_HOLDER_TYPE = IRecipeHolderType.create(
//            ModRegistry.LANTERN_MAKING_RECIPE_TYPE.value());
//    private static final List<ItemStack> LANTERN_ITEM_STACKS = List.of(new ItemStack(Items.LANTERN),
//            new ItemStack(Items.SOUL_LANTERN));
//
//    private final IDrawable background;
//    private final IDrawable slotDrawable;
//
//    public LanternMakingRecipeCategory(IGuiHelper guiHelper) {
//        super(LANTERN_MAKING_RECIPE_HOLDER_TYPE,
//                LanternMakingRecipeHelper.LANTERN_MAKING_COMPONENT,
//                guiHelper.createDrawableItemLike(ModRegistry.LANTERN_MAKER_BLOCK.value()),
//                LanternMakingRecipeHelper.DISPLAY_CATEGORY_WIDTH,
//                LanternMakingRecipeHelper.DISPLAY_CATEGORY_HEIGHT);
//        this.background = guiHelper.createDrawable(LanternMakingRecipeHelper.TEXTURE_LOCATION,
//                0,
//                0,
//                this.getWidth(),
//                this.getHeight());
//        this.slotDrawable = guiHelper.getSlotDrawable();
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<LanternMakingRecipe> recipe, IFocusGroup focuses) {
//        if (recipe.value().display().getFirst() instanceof ShapelessCraftingRecipeDisplay recipeDisplay) {
//            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 42, 35)
//                    .add(new ItemStack(ModRegistry.LANTERN_MAKER_BLOCK.value()));
//            builder.addSlot(RecipeIngredientRole.INPUT, 131, 14)
//                    .addItemStacks(LANTERN_ITEM_STACKS)
//                    .setBackground(this.slotDrawable, -1, -1);
//
//            double angleBetweenEach = 360.0 / recipeDisplay.ingredients().size();
//            Vec2 point = new Vec2(42, 3);
//            Vec2 center = new Vec2(42, 35);
//            for (SlotDisplay slotDisplay : recipeDisplay.ingredients()) {
//                builder.addSlot(RecipeIngredientRole.INPUT, (int) point.x, (int) point.y)
//                        .add(slotDisplay)
//                        .setBackground(this.slotDrawable, -1, -1);
//                point = LanternMakingRecipeHelper.rotatePointAbout(point, center, angleBetweenEach);
//            }
//
//            builder.addSlot(RecipeIngredientRole.OUTPUT, 131, 56).add(recipeDisplay.result());
//        }
//    }
//
//    @Override
//    public void draw(RecipeHolder<LanternMakingRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
//        this.background.draw(guiGraphics);
//    }
//
//    @Override
//    public Codec<RecipeHolder<LanternMakingRecipe>> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {
//        return codecHelper.getRecipeHolderCodec();
//    }
//}
