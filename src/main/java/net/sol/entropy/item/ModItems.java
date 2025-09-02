package net.sol.entropy.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sol.entropy.Entropy;
import net.sol.entropy.item.custom.EntropySwordItem;
import net.sol.entropy.item.custom.LivingKatanaSwordItem;
import net.sol.entropy.util.ModRarities;
import net.sol.entropy.util.helpers.EntropyTooltipHelper;

import java.util.List;

import static net.sol.entropy.util.helpers.EntropyTooltipHelper.ItemDescriptionComponents.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Entropy.MOD_ID);

    public static final RegistryObject<Item> FROST_CLUMP = ITEMS.register("flash_frozen_clump",
            () -> new Item(new Item.Properties().rarity(ModRarities.ENTROPIC)));

    public static final RegistryObject<Item> FROST_INGOT = ITEMS.register("flash_frozen_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.ENTROPIC)));

    public static final RegistryObject<Item> THE_ENTROPY = ITEMS.register("the_entropy", () -> new EntropySwordItem(
                    ModToolTiers.FROST,
                    13,
                    -2.2F,
                    ModRarities.ENTROPIC));

    public static final RegistryObject<Item> MISFORTUNE = ITEMS.register("living_katana", () -> new LivingKatanaSwordItem(
            Tiers.NETHERITE,
            10,
            -1.6F,
            ModRarities.MISFORTUNATE));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
