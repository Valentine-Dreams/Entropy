package net.sol.entropy.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.sol.entropy.Entropy;
import net.sol.entropy.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier FROST = TierSortingRegistry.registerTier(
            new ForgeTier(4, -1, 0.6f, -2f, 25,
                    ModTags.Blocks.NEEDS_FROST_TOOL, () -> Ingredient.of(ModItems.FROST.get())),
            new ResourceLocation(Entropy.MOD_ID, "frozen"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
}
