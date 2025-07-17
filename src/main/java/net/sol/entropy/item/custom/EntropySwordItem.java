package net.sol.entropy.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;




public class EntropySwordItem extends SwordItem {
    public int usetime;
    public int usetick;
    public EntropySwordItem(Tier p_40521_, int atkBase, float spd, Properties p_40524_) {

        super(p_40521_,atkBase,spd,p_40524_);
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
