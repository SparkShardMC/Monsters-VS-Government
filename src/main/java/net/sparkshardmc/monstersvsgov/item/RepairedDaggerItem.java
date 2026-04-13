package net.sparkshardmc.monstersvsgov.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class RepairedDaggerItem extends SwordItem {
    public RepairedDaggerItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 10% chance to blind for 5 seconds (100 ticks)
        if (attacker.getRandom().nextFloat() <= 0.10f) {
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
