package net.sol.entropy.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.sol.entropy.item.base.EntropyBaseItems;
import org.jetbrains.annotations.NotNull;

public class LivingKatanaSwordItem extends SwordItem implements EntropyBaseItems {
    public Rarity rarity;
    public LivingKatanaSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Rarity rarity) {

        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, new Properties());
        this.rarity = rarity;
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack pStack) {
        return this.rarity;
    }

    @Override
    public @NotNull Component getName(ItemStack pStack) {
        return entropyNameHandler(pStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level plevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!plevel.isClientSide){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1, false, false));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0, false, false));
        }

        return super.use(plevel, pPlayer, pUsedHand);
    }
}
