package fuzs.arcanelanterns.common.data.client;

import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.init.ModRegistry;
import fuzs.arcanelanterns.common.integration.LanternMakingRecipeHelper;
import fuzs.arcanelanterns.common.world.level.block.ArcaneLanternBlock;
import fuzs.arcanelanterns.common.world.level.block.LanternMakerBlock;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), ArcaneLanterns.MOD_NAME);
        this.add(ModRegistry.LANTERN_MAKER_BLOCK.value(), "Lantern Maker");
        this.add(ModRegistry.SPARK_BLOCK.value(), "Spark");
        this.add(ModRegistry.LIFE_LANTERN_BLOCK.value(), "Life Lantern");
        this.add(ModRegistry.FERAL_LANTERN_BLOCK.value(), "Feral Lantern");
        this.add(ModRegistry.LOVE_LANTERN_BLOCK.value(), "Love Lantern");
        this.add(ModRegistry.WAILING_LANTERN_BLOCK.value(), "Wailing Lantern");
        this.add(ModRegistry.BOREAL_LANTERN_BLOCK.value(), "Boreal Lantern");
        this.add(ModRegistry.BRILLIANT_LANTERN_BLOCK.value(), "Brilliant Lantern");
        this.add(ModRegistry.WARDING_LANTERN_BLOCK.value(), "Warding Lantern");
        this.add(ModRegistry.CONTAINING_LANTERN_BLOCK.value(), "Containment Lantern");
        this.add(ModRegistry.WITHERING_LANTERN_BLOCK.value(), "Withering Lantern");
        this.add(ModRegistry.CLOUD_LANTERN_BLOCK.value(), "Cloud Lantern");
        this.add(LanternMakingRecipeHelper.LANTERN_MAKING_COMPONENT, "Lantern Making");
        this.add(((LanternMakerBlock) ModRegistry.LANTERN_MAKER_BLOCK.value()).getDescriptionComponent(),
                "To imbue a lantern the lantern maker will require a number of catalysts. Finally, place a lantern block on top to fuse everything together.");
        this.add(((ArcaneLanternBlock) ModRegistry.LIFE_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Makes crops around the lantern grow faster.");
        this.add(((ArcaneLanternBlock) ModRegistry.FERAL_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Lights up a giant area around it by spawning temporary sparks, will stop eventually.");
        this.add(((ArcaneLanternBlock) ModRegistry.LOVE_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Makes nearby mobs fall in love.");
        this.add(((ArcaneLanternBlock) ModRegistry.WAILING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Cries if you get close, screams and applies nausea if you get even closer.");
        this.add(((ArcaneLanternBlock) ModRegistry.BOREAL_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Slows nearby mobs and players down, also extinguishes burning mobs.");
        this.add(((ArcaneLanternBlock) ModRegistry.BRILLIANT_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Turns nearby animals directly into experience.");
        this.add(((ArcaneLanternBlock) ModRegistry.WARDING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Pushes all living entities except players away.");
        this.add(((ArcaneLanternBlock) ModRegistry.CONTAINING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Keeps all living entities except the player confined to an area.");
        this.add(((ArcaneLanternBlock) ModRegistry.WITHERING_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Applies the wither effect in the surrounding area.");
        this.add(((ArcaneLanternBlock) ModRegistry.CLOUD_LANTERN_BLOCK.value()).getDescriptionComponent(),
                "Applies the slow fall effect in the surrounding area.");
    }
}
