package net.sparkshardmc.monstersvsgov.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class DecayedDaggerItem extends SwordItem {
    public DecayedDaggerItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Only run logic if a Player is the one attacking
        if (attacker instanceof Player player) {
            int targetFaction = 0;

            // Check if the target is a player and get their FactionID
            if (target instanceof Player targetPlayer) {
                targetFaction = targetPlayer.getPersistentData().getInt("FactionID");
            }

            // Logic: If target is NOT a Monster (Faction 1), roll for poison
            if (targetFaction != 1) {
                if (attacker.getRandom().nextFloat() <= 0.05f) { // 5% chance
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0)); // 10 seconds
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
