package fuzs.arcanelanterns.data.client;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.arcanelanterns.integration.LanternMakingRecipeHelper;
import fuzs.arcanelanterns.world.level.block.ArcaneLanternBlock;
import fuzs.arcanelanterns.world.level.block.LanternMakerBlock;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.add(ModRegistry.CREATIVE_MODE_TAB.value(), ArcaneLanterns.MOD_NAME);
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
        builder.add(LanternMakingRecipeHelper.LANTERN_MAKING_COMPONENT, "Lantern Making");
        builder.add(((LanternMakerBlock) ModRegistry.LANTERN_MAKER_BLOCK.value()).getDescriptionComponent(),
                "To imbue a lantern the lantern maker will require a number of catalysts. Finally, place a lantern block on top to fuse everything together.");
        builder.add(((ArcaneLanternBlock) ModRegistry.LIFE_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Makes crops around the lantern grow faster.");
        builder.add(((ArcaneLanternBlock) ModRegistry.FERAL_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Lights up a giant area around it by spawning temporary sparks, will stop eventually.");
        builder.add(((ArcaneLanternBlock) ModRegistry.LOVE_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Makes nearby mobs fall in love.");
        builder.add(((ArcaneLanternBlock) ModRegistry.WAILING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Cries if you get close, screams and applies nausea if you get even closer.");
        builder.add(((ArcaneLanternBlock) ModRegistry.BOREAL_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Slows nearby mobs and players down, also extinguishes burning mobs.");
        builder.add(((ArcaneLanternBlock) ModRegistry.BRILLIANT_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Turns nearby animals directly into experience.");
        builder.add(((ArcaneLanternBlock) ModRegistry.WARDING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Pushes all living entities except players away.");
        builder.add(((ArcaneLanternBlock) ModRegistry.CONTAINING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Keeps all living entities except the player confined to an area.");
        builder.add(((ArcaneLanternBlock) ModRegistry.WITHERING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Applies the wither effect in the surrounding area.");
        builder.add(((ArcaneLanternBlock) ModRegistry.CLOUD_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Applies the slow fall effect in the surrounding area.");
    }
}
