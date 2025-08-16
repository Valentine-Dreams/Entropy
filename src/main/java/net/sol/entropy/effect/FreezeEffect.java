package net.sol.entropy.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.sol.entropy.damagesource.DamageVar;


public class FreezeEffect extends MobEffect {
    public FreezeEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.isAlive() && !pLivingEntity.isDeadOrDying()) {
            if (pAmplifier > 2)
                pLivingEntity.hurt(DamageVar.Freeze(pLivingEntity.level().registryAccess()), 999f);
            Vec3 initialVec = pLivingEntity.getDeltaMovement();
            double amp = pAmplifier + 1;
            double scale = Math.pow(amp, amp);
            pLivingEntity.setDeltaMovement(1D / scale * initialVec.x, initialVec.y > 0D ? 1D / scale * initialVec.y : initialVec.y, 1D / scale * initialVec.z);
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}