package fuzs.arcanelanterns.data.client;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.integration.jei.LanternMakingRecipeCategory;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.addCreativeModeTab(ArcaneLanterns.MOD_ID, ArcaneLanterns.MOD_NAME);
        builder.add(ModRegistry.LANTERN_MAKER_BLOCK.value(), "Lantern Maker");
        builder.add(ModRegistry.SPARK_BLOCK.value(), "Spark");
        builder.add(ModRegistry.LIFE_LANTERN_BLOCK.value(), "Life Lantern");
        builder.add(ModRegistry.FERAL_LANTERN_BLOCK.value(), "Feral Lantern");
        builder.add(ModRegistry.LOVE_LANTERN_BLOCK.value(), "Love Lantern");
        builder.add(ModRegistry.WAILING_LANTERN_BLOCK.value(), "Wailing Lantern");
        builder.add(ModRegistry.BOREAL_LANTERN_BLOCK.value(), "Boreal Lantern");
        builder.add(ModRegistry.BRILLIANT_LANTERN_BLOCK.value(), "Brilliant Lantern");
        builder.add(ModRegistry.WARDING_LANTERN_BLOCK.value(), "Warding Lantern");
        builder.add(ModRegistry.CONTAINING_LANTERN_BLOCK.value(), "Containment Lantern");
        builder.add(ModRegistry.WITHERING_LANTERN_BLOCK.value(), "Withering Lantern");
        builder.add(ModRegistry.CLOUD_LANTERN_BLOCK.value(), "Cloud Lantern");
        builder.add(LanternMakingRecipeCategory.LANTERN_MAKING_COMPONENT, "Lantern Making");
        builder.add(ModRegistry.LANTERN_MAKER_BLOCK.value(), "info", "To imbue a lantern the lantern maker will require a number of catalysts. Finally, place a lantern block on top to fuse everything together.");
        builder.add(ModRegistry.LIFE_LANTERN_BLOCK.value(), "info", "Makes crops around the lantern grow faster.");
        builder.add(ModRegistry.FERAL_LANTERN_BLOCK.value(), "info", "Lights up a giant area around it by spawning sparks. When enough sparks have been spawned the lantern stops. Sparks will stay in the world while the lantern exists at the original position.");
        builder.add(ModRegistry.LOVE_LANTERN_BLOCK.value(), "info", "Makes nearby mobs fall in love.");
        builder.add(ModRegistry.WAILING_LANTERN_BLOCK.value(), "info", "Cries if you get close, screams and applies nausea if you get even closer.");
        builder.add(ModRegistry.BOREAL_LANTERN_BLOCK.value(), "info", "Slows nearby mobs and players down, also extinguishes burning mobs.");
        builder.add(ModRegistry.BRILLIANT_LANTERN_BLOCK.value(), "info", "Turns nearby animals directly into experience.");
        builder.add(ModRegistry.WARDING_LANTERN_BLOCK.value(), "info", "Pushes all living entities except players away.");
        builder.add(ModRegistry.CONTAINING_LANTERN_BLOCK.value(), "info", "Keeps all living entities except the player confined to an area.");
        builder.add(ModRegistry.WITHERING_LANTERN_BLOCK.value(), "info", "Applies the wither effect in the surrounding area.");
        builder.add(ModRegistry.CLOUD_LANTERN_BLOCK.value(), "info", "Applies the slow fall effect in the surrounding area.");
    }
}
