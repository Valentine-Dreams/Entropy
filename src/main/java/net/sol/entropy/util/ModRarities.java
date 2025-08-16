package net.sol.entropy.util;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

import java.util.HashMap;
import java.util.Map;

public final class ModRarities {

    public static final Rarity ENTROPIC = Rarity.create("entropic", ChatFormatting.DARK_AQUA);

    public static final Map<Rarity, RarityData> RARITY_TO_GRADIENT = new HashMap<>();

    static {
        RARITY_TO_GRADIENT.put(ENTROPIC, RarityData.ENTROPIC);
    }


    public enum RarityData {
        ENTROPIC(new int[][]{{97, 100, 152}, {114, 129, 166}, {154, 188, 197}}, 0.35f, 2.0f, false); //


        public final int[][] colors;
        public final float waveSpeed;
        public final float spread;
        public final boolean bold;

        RarityData(int[][] colors, float waveSpeed, float spread, boolean bold) {
            this.colors = colors;
            this.waveSpeed = waveSpeed;
            this.spread = spread;
            this.bold = bold;
        }
    }

    public static RarityData getGradientForRarity(Rarity rarity) {
        return RARITY_TO_GRADIENT.get(rarity);
    }
}

//    private ModRarities() {}
//
//    private static Rarity createRarity(String name, ChatFormatting rgbColor) {
//        return Rarity.create(Entropy.MOD_ID + "_" + name, style -> style.withColor(rgbColor));
//    }
//
//    public static int getRGBColor(ItemStack stack) {
//        TextColor color = stack.getRarity().getStyleModifier().apply(Style.EMPTY).getColor();
//        return color != null ? color.getValue() : -1;
//    }
//
//    public static int getARGBColor(ItemStack stack) { return getRGBColor(stack) | 0xFF_000000;}
