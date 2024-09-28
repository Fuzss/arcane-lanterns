package fuzs.arcanelanterns.config;

import com.google.common.collect.Lists;
import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.puzzleslib.api.config.v3.serialization.ConfigDataSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;

import java.util.List;

public class ServerConfig implements ConfigCore {
    @Config
    public EffectLanternConfig borealLantern = new EffectLanternConfig(2, 10, 10, 5);
    @Config
    public BrilliantLanternConfig brilliantLantern = new BrilliantLanternConfig(20, 4, 4);
    @Config
    public EffectLanternConfig cloudLantern = new EffectLanternConfig(80, 10, 10, 9);
    @Config
    public LanternConfig containingLantern = new LanternConfig(5, 10, 3);
    @Config
    public FeralLanternConfig feralLantern = new FeralLanternConfig(20, 40, 15);
    @Config
    public LanternConfig lifeLantern = new LanternConfig(120, 5, 3);
    @Config
    public LoveLanternConfig loveLantern = new LoveLanternConfig(1200, 5, 3);
    @Config
    public EffectLanternConfig wailingLantern = new EffectLanternConfig(20, 10, 5, 5);
    @Config
    public LanternConfig wardingLantern = new LanternConfig(10, 10, 5);
    @Config
    public EffectLanternConfig witheringLantern = new EffectLanternConfig(20, 7, 3, 2);

    public static class LanternConfig implements ConfigCore {
        @Config(description = "Delay in ticks after which the lantern runs its effects again.")
        @Config.IntRange(min = 1)
        public int delay;
        @Config(description = "Range on the xz-plane where this lantern affects entities.")
        public int horizontalRange;
        @Config(description = "Range on the y-axis where this lantern affects entities.")
        public int verticalRange;

        public LanternConfig(int delay, int horizontalRange, int verticalRange) {
            this.delay = delay;
            this.horizontalRange = horizontalRange;
            this.verticalRange = verticalRange;
        }
    }

    public static class EffectLanternConfig extends LanternConfig {
        @Config(description = "Duration in seconds this lamp's potion effect should be applied for to nearby entities.")
        @Config.IntRange(min = 0)
        public int effectDuration;

        public EffectLanternConfig(int delay, int horizontalRange, int verticalRange, int effectDuration) {
            super(delay, horizontalRange, verticalRange);
            this.effectDuration = effectDuration;
        }
    }

    public static class BrilliantLanternConfig extends LanternConfig {
        @Config(name = "blacklist",
                description = {
                        "Animals the briliant lantern will not turn into experience.",
                        ConfigDataSet.CONFIG_DESCRIPTION
                }
        )
        List<String> blacklistRaw = Lists.newArrayList();

        public ConfigDataSet<EntityType<?>> blacklist;

        public BrilliantLanternConfig(int delay, int horizontalRange, int verticalRange) {
            super(delay, horizontalRange, verticalRange);
        }

        @Override
        public void afterConfigReload() {
            this.blacklist = ConfigDataSet.from(Registries.ENTITY_TYPE, this.blacklistRaw);
        }
    }

    public static class LoveLanternConfig extends LanternConfig {
        @Config(description = "Maximum animals of a certain type (e.g. sheep or pigs) before the love lamp stops breeding that type. All animals of a type are counted, also those that cannot breed (like babies).")
        @Config.IntRange(min = 2)
        public int maxAnimals = 12;

        public LoveLanternConfig(int delay, int horizontalRange, int verticalRange) {
            super(delay, horizontalRange, verticalRange);
        }
    }

    public static class FeralLanternConfig extends LanternConfig {
        @Config(description = "The maximum amount of flares the feral lamp will place before destroying itself.")
        @Config.IntRange(min = 1, max = 9999)
        public int maxPlacedFlares = 40;
        @Config(description = "The maximum light level at a checked position where the feral lamp will place a flare.")
        @Config.IntRange(min = 0, max = 15)
        public int maxLightLevel = 5;

        public FeralLanternConfig(int delay, int horizontalRange, int verticalRange) {
            super(delay, horizontalRange, verticalRange);
        }
    }
}
