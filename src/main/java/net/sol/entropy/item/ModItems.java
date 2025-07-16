package net.sol.entropy.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sol.entropy.Entropy;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Entropy.MOD_ID);

    public static final RegistryObject<Item> FROST = ITEMS.register("flash_frozen",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THE_ENTROPY = ITEMS.register("the_entropy",
            () -> new SwordItem(ModToolTiers.FROST, 10, 1.8F, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
