//package fuzs.arcanelanterns.integration.rei;
//
//import fuzs.arcanelanterns.init.ModRegistry;
//import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
//import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//
//public class ArcaneLanternsReiClientPlugin implements REIClientPlugin {
//
//    @Override
//    public void registerCategories(CategoryRegistry registry) {
//        registry.add(new LanternMakingCategory());
//        registry.addWorkstations(LanternMakingDisplay.CATEGORY, EntryStacks.of(ModRegistry.LANTERN_MAKER_ITEM.value()));
//        registry.configure(LanternMakingDisplay.CATEGORY,
//                (CategoryRegistry.CategoryConfiguration<LanternMakingDisplay> config) -> {
//                    config.setQuickCraftingEnabledByDefault(false);
//                });
//    }
//}
