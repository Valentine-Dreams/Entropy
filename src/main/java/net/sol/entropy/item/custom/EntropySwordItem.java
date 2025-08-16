package net.sol.entropy.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.sol.entropy.Entropy;
import net.sol.entropy.item.base.EntropyBaseItems;
import net.sol.entropy.util.helpers.EntropyTooltipHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class EntropySwordItem extends SwordItem implements EntropyBaseItems {
    public int usetime;
    public int usetick;
    public Rarity rarity;
    public EntropySwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Rarity rarity) {

        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, new Properties());
        this.rarity = rarity;
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack pStack) {
        return this.rarity;
    }

    @Override
    public @NotNull Component getName(ItemStack pStack){
        return entropyNameHandler(pStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()) {
            usetime = 20;
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9600, 1, false, false));
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }


}
