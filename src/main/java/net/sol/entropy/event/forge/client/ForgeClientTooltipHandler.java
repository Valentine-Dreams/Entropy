package net.sol.entropy.event.forge.client;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.sol.entropy.item.custom.EntropySwordItem;
import net.sol.entropy.util.ModRarities;
import net.sol.entropy.util.helpers.EntropyTooltipHelper;

import static net.sol.entropy.Entropy.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientTooltipHandler {

    @SubscribeEvent
    public static void tooltipColorHandler(RenderTooltipEvent.Color event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        Rarity rarity = item.getRarity(stack);

        if (item instanceof EntropySwordItem) {
            if (!(rarity.equals(Rarity.COMMON) | rarity.equals(Rarity.UNCOMMON) | rarity.equals(Rarity.RARE) | rarity.equals(Rarity.EPIC))) {
                int[][] colors = ModRarities.getGradientForRarity(rarity).colors;
                event.setBorderStart(EntropyTooltipHelper.argbToInt(
                        EntropyTooltipHelper.getCyclingColorFromGradient(0.05f, colors[0], colors[1])));
                event.setBorderEnd(EntropyTooltipHelper.argbToInt(
                        EntropyTooltipHelper.getCyclingColorFromGradient(0.05f, colors[1], colors[0])));
            }
        }
    }
}
