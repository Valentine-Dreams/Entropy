package net.sol.entropy.item.base;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.ForgeRegistries;
import net.sol.entropy.Entropy;
import net.sol.entropy.util.helpers.EntropyTooltipHelper;

import java.util.Locale;
import java.util.Objects;

public interface EntropyBaseItems {
    default String getRegistryName() {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey((Item) this)).getPath().toLowerCase(Locale.ROOT);
    }

    default Component entropyNameHandler(ItemStack stack) {
        Rarity rarity = stack.getRarity();
        String identifier = getRegistryName();
        if (rarity.equals(Rarity.COMMON) || rarity.equals(Rarity.UNCOMMON) || rarity.equals(Rarity.RARE) || rarity.equals(Rarity.EPIC))
            return Component.translatable("item." + Entropy.MOD_ID + "." + identifier);
        else
            return EntropyTooltipHelper.getRarityName("item." + Entropy.MOD_ID + "." + identifier, rarity);
    }
}
