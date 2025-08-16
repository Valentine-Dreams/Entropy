package net.sol.entropy.util.helpers;


import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.sol.entropy.util.ModRarities;


import java.awt.*;

import static net.minecraft.util.CommonColors.WHITE;

public class EntropyTooltipHelper {

    public static int[] changeBrightness(int[] colors, float factor) {
        int[] result = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            result[i] = (int) (colors[i] * factor);
        }
        return result;
    }

    public static Component getRarityName(String translationKey, Rarity rarity) {
        assert Minecraft.getInstance().player != null;
        ModRarities.RarityData rarityData = ModRarities.getGradientForRarity(rarity);
        return EntropyTooltipHelper.tooltipHelper(translationKey, rarityData.bold, null, rarityData.waveSpeed, rarityData.spread, rarityData.colors);
    }

    public static Component tooltipHelper(String localeKey, boolean bold, ResourceLocation font, float waveSpeed, float spreadMultiplier, int[]... colors) {
        if(font == null) {
            if(colors.length == 1) {
                return Component.translatable(localeKey).withStyle(Style.EMPTY.withBold(bold).withColor(rgbToInt(colors[0])));
            } else {
                return addColorGradientText(Component.translatable(localeKey), waveSpeed, spreadMultiplier, colors).withStyle(Style.EMPTY.withBold(bold));
            }
        } else {
            if(colors.length == 1) {
                return Component.translatable(localeKey).withStyle(Style.EMPTY.withBold(bold).withFont(font).withColor(rgbToInt(colors[0])));
            } else {
                return addColorGradientText(Component.translatable(localeKey), waveSpeed, spreadMultiplier, colors).withStyle(Style.EMPTY.withBold(bold).withFont(font));
            }
        }
    }

    public static MutableComponent tooltipHelper(String localeKey, boolean bold, ResourceLocation font, int[]... colors) {
        if(font == null) {
            if(colors.length == 1) {
                return Component.translatable(localeKey).withStyle(Style.EMPTY.withBold(bold).withColor(rgbToInt(colors[0])));
            } else {
                return addColorGradientText(Component.translatable(localeKey), colors).withStyle(Style.EMPTY.withBold(bold));
            }
        } else {
            if (colors.length == 1) {
                return Component.translatable(localeKey).withStyle(Style.EMPTY.withBold(bold).withFont(font).withColor(rgbToInt(colors[0])));
            } else {
                return addColorGradientText(Component.translatable(localeKey), colors).withStyle(Style.EMPTY.withBold(bold).withFont(font));
            }
        }
    }

    public static int[] ensureVisible(int[] rgb) {
        float[] hsv = new float[3];
        Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsv);

        float minBrightness = 25;
        float boost = 0.25f;

        if(hsv[2] < minBrightness) {
            hsv[2] = Math.min(1.0f, hsv[2] + boost);
        }

        int rgbInt = Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]);
        return new int[]{(rgbInt >> 16) & 0xFF, (rgbInt >> 8) & 0xFF, rgbInt & 0xFF};
    }

    public static int getColorFromGradient(int percentage, int[]... rgbColors) {
        if(rgbColors.length < 2){
            return rgbToInt(rgbColors[0]);
        }

        float ratio = percentage / 100f;
        int totalSegments = rgbColors.length - 1;


        int segment = Math.min((int) (ratio * totalSegments), totalSegments - 1);
        float localRatio = (ratio * totalSegments) - segment;


        int[] color1 = rgbColors[segment];
        int[] color2 = rgbColors[segment + 1];


        int r = (int) ((1 - localRatio) * color1[0] + localRatio * color2[0]);
        int g = (int) ((1 - localRatio) * color1[1] + localRatio * color2[1]);
        int b = (int) ((1 - localRatio) * color1[2] + localRatio * color2[2]);

        return (r << 16) | (g << 8) | b;
    }

    public static MutableComponent addColorGradientText(Component text, float speed, float spreadMultiplier, int[]... rgbColors) {
        MutableComponent gradientTextComponent = net.minecraft.network.chat.Component.empty();
        String string = text.getString();
        int length = string.length();
        int numColors = rgbColors.length;

        int tickCount = DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
            if (Minecraft.getInstance().player != null) return Minecraft.getInstance().player.tickCount;
            return ((int) (System.currentTimeMillis() / 50L));
        });


        if (numColors == 0 || length == 0) {
            return gradientTextComponent;
        }

        int[][] adjustedColors = new int[numColors + 1][3];
        System.arraycopy(rgbColors, 0, adjustedColors, 0, numColors);
        adjustedColors[numColors] = rgbColors[0];

        speed = 1 / speed;
        float effectiveTickCount = tickCount % (speed * 20);
        float ratio = effectiveTickCount / (speed * 20);


        spreadMultiplier = 1 / spreadMultiplier;
        int effectiveLength = (int) (length * spreadMultiplier);

        for (int i = 0; i < length; i++) {
            float adjustedIndex = (((float) i * spreadMultiplier) / length + ratio) * effectiveLength;


            adjustedIndex = adjustedIndex % effectiveLength;


            int percentage = (int) ((adjustedIndex / effectiveLength) * 100);


            int color = getColorFromGradient(percentage, adjustedColors);


            MutableComponent letterComponent = net.minecraft.network.chat.Component.literal(String.valueOf(string.charAt(i))).withStyle(Style.EMPTY.withColor(color));

            gradientTextComponent = gradientTextComponent.append(letterComponent);

        }
        return gradientTextComponent;
    }

    public static MutableComponent addColorGradientText(Component text, int[]... rgbColors) {
        MutableComponent gradientTextComponent = net.minecraft.network.chat.Component.empty();

        String string = text.getString();
        int length = string.length();
        int numColors = rgbColors.length;

        if(numColors == 0 || length == 0) {
            return gradientTextComponent;
        }

        for (int i = 0; i < length; i++) {
            int percentage = (i * 100) / (length - 1);

            int color = getColorFromGradient(percentage, rgbColors);

            MutableComponent letterComponent = net.minecraft.network.chat.Component.literal(String.valueOf(string.charAt(i))).withStyle(Style.EMPTY.withColor(color));

            gradientTextComponent = gradientTextComponent.append(letterComponent);
        }
        return gradientTextComponent;
    }

    public static int rgbToInt(int[] rgb) {
        return (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
    }

    public static int rgbToInt(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    public static int[] intToRgb(int color) {
        return new int[]{color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF};
    }


    public static int argbToInt(int[] rgb, int... alpha) {
        int a = alpha.length > 0 ? alpha[0] : 255;
        rgb[0] = Math.max(0, Math.min(255, rgb[0]));
        rgb[1] = Math.max(0, Math.min(255, rgb[1]));
        rgb[2] = Math.max(0, Math.min(255, rgb[2]));
        return (a << 24) | (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
    }

    public static int argbToInt(int r, int g, int b, int a) {
        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));
        return (a << 24) | (r << 16) | (g << 8) | b;
    }


    public static int[] argbToRgb(int argb) {
        return new int[]{(argb >> 16) & 0xFF,
        (argb >> 8) & 0xFF,
        argb & 0xFF};
    }


    public static int getCyclingColors(float speed, int[]... rgbColors) {
        int tickCount = DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
            if(Minecraft.getInstance().player != null) return Minecraft.getInstance().player.tickCount;
            return ((int) (System.currentTimeMillis() / 50L));
        });


        if(rgbColors.length == 0) return WHITE;


        int[][] gradientColors;
        if (rgbColors.length > 1) {
            gradientColors = new int[rgbColors.length + 1][];
            System.arraycopy(rgbColors, 0, gradientColors, 0, rgbColors.length);
            gradientColors[rgbColors.length] = rgbColors[0];
        } else {
            gradientColors = rgbColors;
        }

        float progress = tickCount % (100 * speed) / 100f;

        int percentage = (int) (progress * 100);

        return getColorFromGradient(percentage, gradientColors);
    }

    public static int[] getCyclingColorFromGradient(float speed, int[]... rgbColors) {
        assert Minecraft.getInstance().player != null;
        int tickCount = Minecraft.getInstance().player.tickCount;

        if (rgbColors.length == 0) return new int[]{255, 255, 0};

        float t = (tickCount * speed) % rgbColors.length;
        int index = (int) Math.floor(t);
        float lerpFactor = t - index;

        int[] colorA = rgbColors[index % rgbColors.length];
        int[] colorB = rgbColors[(index + 1) % rgbColors.length];

        return lerpColor(colorA, colorB, lerpFactor);
    }

    private static int[] lerpColor(int[] a, int[] b, float t) {
        int r = (int) (a[0] + (b[0] - a[0]) * t);
        int g = (int) (a[1] + (b[1] - a[1]) * t);
        int b_ = (int) (a[2] + (b[2] - a[2]) * t);
        return new int[]{r, g, b_};
    }

    public record ItemDescriptionComponent(ItemDescriptionComponents type) {
    }

    public enum ItemDescriptionComponents {
        ON_RIGHT_CLICK
    }
}


