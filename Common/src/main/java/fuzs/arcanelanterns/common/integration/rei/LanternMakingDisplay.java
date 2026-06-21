package fuzs.arcanelanterns.common.integration.rei;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.arcanelanterns.common.ArcaneLanterns;
import fuzs.arcanelanterns.common.world.item.crafting.LanternMakingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.CollectionUtils;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class LanternMakingDisplay extends BasicDisplay {
    public static final CategoryIdentifier<LanternMakingDisplay> CATEGORY = CategoryIdentifier.of(ArcaneLanterns.id(
            "plugins/lantern_making"));
    public static final DisplaySerializer<LanternMakingDisplay> SERIALIZER = DisplaySerializer.of(RecordCodecBuilder.mapCodec(
                    instance -> instance.group(EntryIngredient.codec()
                                            .listOf()
                                            .fieldOf("inputs")
                                            .forGetter(LanternMakingDisplay::getInputEntries),
                                    EntryIngredient.codec()
                                            .listOf()
                                            .fieldOf("outputs")
                                            .forGetter(LanternMakingDisplay::getOutputEntries),
                                    Identifier.CODEC.optionalFieldOf("location").forGetter(LanternMakingDisplay::getDisplayLocation))
                            .apply(instance, LanternMakingDisplay::new)),
            StreamCodec.composite(EntryIngredient.streamCodec().apply(ByteBufCodecs.list()),
                    LanternMakingDisplay::getInputEntries,
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()),
                    LanternMakingDisplay::getOutputEntries,
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC),
                    LanternMakingDisplay::getDisplayLocation,
                    LanternMakingDisplay::new));

    public LanternMakingDisplay(RecipeHolder<LanternMakingRecipe> recipe) {
        this(CollectionUtils.map(recipe.value().placementInfo().ingredients(), EntryIngredients::ofIngredient),
                List.of(EntryIngredients.of(recipe.value().assemble(CraftingInput.EMPTY))),
                Optional.of(recipe.id().identifier()));
    }

    public LanternMakingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location) {
        super(inputs, outputs, location);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CATEGORY;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
