package net.sol.entropy.damagesource;


import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.sol.entropy.Entropy;


public class DamageVar {
    public static final ResourceKey<DamageType> FREEZE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Entropy.MOD_ID, "freeze"));

    public static DamageSource Freeze(RegistryAccess registryAccess) {
        return new DamageSourceRandomMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(FREEZE), 5);
    }
    private static class DamageSourceRandomMessages extends DamageSource {

        private final int messageCount;

        public DamageSourceRandomMessages(Holder.Reference<DamageType> message, int messageCount) {
            super(message);
            this.messageCount = messageCount;
        }
    }
}

