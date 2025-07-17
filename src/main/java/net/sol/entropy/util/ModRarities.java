package net.sol.entropy.util;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.sol.entropy.Entropy;

public final class ModRarities {

    public static final Rarity ENTROPIC = createRarity("entropic", 0x616498);

    private ModRarities() {}

    private static Rarity createRarity(String name, int rgbColor) {
        return Rarity.create(Entropy.MOD_ID + "_" + name, style -> style.withColor(rgbColor));
    }

    public static int getRGBColor(ItemStack stack) {
        TextColor color = stack.getRarity().getStyleModifier().apply(Style.EMPTY).getColor();
        return color != null ? color.getValue() : -1;
    }

    public static int getARGBColor(ItemStack stack) { return getRGBColor(stack) | 0xFF_000000;}
}
