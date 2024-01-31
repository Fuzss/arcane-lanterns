package fuzs.arcanelanterns.integration.jei;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.world.item.crafting.LanternMakingRecipe;
import fuzs.puzzleslib.api.core.v1.Proxy;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Some of this code comes from Botania's runic altar, mainly the method for evenly placing recipe ingredients in a circle around a center point.
 * <p><a href="https://github.com/VazkiiMods/Botania/blob/1.19.x/Xplat/src/main/java/vazkii/botania/client/integration/jei/RunicAltarRecipeCategory.java">RunicAltarRecipeCategory.java</a>
 */
public class LanternMakingRecipeCategory implements IRecipeCategory<LanternMakingRecipe> {
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slotDrawable;
    private final ItemStack lanternMakerStack;
    private final List<ItemStack> lanternStacks;

    public LanternMakingRecipeCategory(IGuiHelper guiHelper) {
        this.title = Component.translatable("recipe_type.arcanelanterns.lantern_making");
        this.background = guiHelper.createDrawable(ArcaneLanterns.id("textures/gui/lantern_making_background.png"), 0, 0, 161, 86);
        this.lanternMakerStack = new ItemStack(ModRegistry.LANTERN_MAKER_BLOCK.get());
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, this.lanternMakerStack);
        this.slotDrawable = guiHelper.getSlotDrawable();
        this.lanternStacks = List.of(new ItemStack(Items.LANTERN), new ItemStack(Items.SOUL_LANTERN));
    }

    public static Vec2 rotatePointAbout(Vec2 in, Vec2 about, double degrees) {
        double rad = degrees * Math.PI / 180.0;
        double newX = Math.cos(rad) * (in.x - about.x) - Math.sin(rad) * (in.y - about.y) + about.x;
        double newY = Math.sin(rad) * (in.x - about.x) + Math.cos(rad) * (in.y - about.y) + about.y;
        return new Vec2((float) newX, (float) newY);
    }

    @NotNull
    @Override
    public RecipeType<LanternMakingRecipe> getRecipeType() {
        return ArcaneLanternsPlugin.LANTERN_MAKING_RECIPE_TYPE;
    }

    @NotNull
    @Override
    public Component getTitle() {
        return this.title;
    }

    @NotNull
    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @NotNull
    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull LanternMakingRecipe recipe, @NotNull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 42, 35).addItemStack(this.lanternMakerStack);
        builder.addSlot(RecipeIngredientRole.INPUT, 131, 14).addItemStacks(this.lanternStacks).setBackground(this.slotDrawable, -1, -1);

        double angleBetweenEach = 360.0 / recipe.getIngredients().size();
        Vec2 point = new Vec2(42, 3);
        Vec2 center = new Vec2(42, 35);

        for (Ingredient ingredient : recipe.getIngredients()) {
            builder.addSlot(RecipeIngredientRole.INPUT, (int) point.x, (int) point.y).addIngredients(ingredient).setBackground(this.slotDrawable, -1, -1);
            point = rotatePointAbout(point, center, angleBetweenEach);
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 131, 56).addItemStack(recipe.getResultItem(Proxy.INSTANCE.getClientLevel().registryAccess()));
    }
}
