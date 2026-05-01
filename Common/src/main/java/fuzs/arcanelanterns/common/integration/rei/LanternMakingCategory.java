//package fuzs.arcanelanterns.integration.rei;
//
//import fuzs.arcanelanterns.init.ModRegistry;
//import fuzs.arcanelanterns.integration.LanternMakingRecipeHelper;
//import me.shedaniel.math.Point;
//import me.shedaniel.math.Rectangle;
//import me.shedaniel.rei.api.client.gui.Renderer;
//import me.shedaniel.rei.api.client.gui.widgets.Widget;
//import me.shedaniel.rei.api.client.gui.widgets.Widgets;
//import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
//import me.shedaniel.rei.api.common.category.CategoryIdentifier;
//import me.shedaniel.rei.api.common.entry.EntryIngredient;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//import net.minecraft.client.renderer.RenderPipelines;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.phys.Vec2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LanternMakingCategory implements DisplayCategory<LanternMakingDisplay> {
//
//    @Override
//    public CategoryIdentifier<? extends LanternMakingDisplay> getCategoryIdentifier() {
//        return LanternMakingDisplay.CATEGORY;
//    }
//
//    @Override
//    public Component getTitle() {
//        return LanternMakingRecipeHelper.LANTERN_MAKING_COMPONENT;
//    }
//
//    @Override
//    public Renderer getIcon() {
//        return EntryStacks.of(ModRegistry.LANTERN_MAKER_BLOCK.value());
//    }
//
//    @Override
//    public List<Widget> setupDisplay(LanternMakingDisplay display, Rectangle bounds) {
//        Point startPoint = new Point(bounds.getCenterX() - LanternMakingRecipeHelper.DISPLAY_CATEGORY_WIDTH / 2,
//                bounds.getCenterY() - LanternMakingRecipeHelper.DISPLAY_CATEGORY_HEIGHT / 2);
//        List<Widget> widgets = new ArrayList<>();
//
//        widgets.add(Widgets.createRecipeBase(bounds));
//        widgets.add(Widgets.createDrawableWidget((guiGraphics, mouseX, mouseY, partialTick) -> {
//            guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
//                    LanternMakingRecipeHelper.TEXTURE_LOCATION,
//                    startPoint.x,
//                    startPoint.y,
//                    0,
//                    0,
//                    LanternMakingRecipeHelper.DISPLAY_CATEGORY_WIDTH,
//                    LanternMakingRecipeHelper.DISPLAY_CATEGORY_HEIGHT,
//                    256,
//                    256);
//        }));
//        // add this on top for supporting dark mode
//        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 131, startPoint.y + 56)));
//
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 42, startPoint.y + 35))
//                .entry(EntryStacks.of(ModRegistry.LANTERN_MAKER_ITEM.value()))
//                .disableBackground()
//                .notInteractable());
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 131, startPoint.y + 14))
//                .entries(List.of(EntryStacks.of(Items.LANTERN), EntryStacks.of(Items.SOUL_LANTERN)))
//                .markInput());
//
//        double angleBetweenEach = 360.0 / display.getInputEntries().size();
//        Vec2 point = new Vec2(42, 3);
//        Vec2 center = new Vec2(42, 35);
//
//        for (EntryIngredient ingredient : display.getInputEntries()) {
//            widgets.add(Widgets.createSlot(new Point(startPoint.x + (int) point.x, startPoint.y + (int) point.y))
//                    .entries(ingredient)
//                    .markInput());
//            point = LanternMakingRecipeHelper.rotatePointAbout(point, center, angleBetweenEach);
//        }
//
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 131, startPoint.y + 56))
//                .entries(display.getOutputEntries().getFirst())
//                .disableBackground()
//                .markOutput());
//
//        return widgets;
//    }
//
//    @Override
//    public int getDisplayHeight() {
//        return LanternMakingRecipeHelper.DISPLAY_CATEGORY_HEIGHT + 7;
//    }
//
//    @Override
//    public int getDisplayWidth(LanternMakingDisplay display) {
//        return LanternMakingRecipeHelper.DISPLAY_CATEGORY_WIDTH + 7;
//    }
//}
