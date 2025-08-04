package net.sol.entropy.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sol.entropy.Entropy;
import net.sol.entropy.item.custom.EntropySwordItem;
import net.sol.entropy.util.ModRarities;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Entropy.MOD_ID);

    public static final RegistryObject<Item> FROST_CLUMP = ITEMS.register("flash_frozen_clump",
            () -> new Item(new Item.Properties().rarity(ModRarities.ENTROPIC)));

    public static final RegistryObject<Item> FROST_INGOT = ITEMS.register("flash_frozen_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.ENTROPIC)));

    public static final RegistryObject<Item> THE_ENTROPY = ITEMS.register("the_entropy",
            () -> new EntropySwordItem(ModToolTiers.FROST, 10, -2.2F, new Item.Properties().rarity(ModRarities.ENTROPIC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
