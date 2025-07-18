package fuzs.arcanelanterns.integration;

import fuzs.arcanelanterns.ArcaneLanterns;
import fuzs.arcanelanterns.init.ModRegistry;
import fuzs.puzzleslib.api.init.v3.registry.ResourceKeyHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

public final class LanternMakingRecipeHelper {
    public static final Component LANTERN_MAKING_COMPONENT = ResourceKeyHelper.getComponent(ModRegistry.LANTERN_MAKING_RECIPE_TYPE.key());
    public static final ResourceLocation TEXTURE_LOCATION = ArcaneLanterns.id(
            "textures/gui/lantern_making_background.png");
    public static final int DISPLAY_CATEGORY_HEIGHT = 86;
    public static final int DISPLAY_CATEGORY_WIDTH = 161;

    private LanternMakingRecipeHelper() {
        // NO-OP
    }

    public static Vec2 rotatePointAbout(Vec2 in, Vec2 about, double degrees) {
        double rad = degrees * Math.PI / 180.0;
        double newX = Math.cos(rad) * (in.x - about.x) - Math.sin(rad) * (in.y - about.y) + about.x;
        double newY = Math.sin(rad) * (in.x - about.x) + Math.cos(rad) * (in.y - about.y) + about.y;
        return new Vec2((float) newX, (float) newY);
    }
}
