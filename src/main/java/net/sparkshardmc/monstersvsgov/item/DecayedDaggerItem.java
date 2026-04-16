package net.sparkshardmc.monstersvsgov.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlotGroup;

public class DecayedDaggerItem extends SwordItem {
    
    // Updated constructor for 26.1.2
    public DecayedDaggerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    // Helper to set your specific 3 Damage / -2.0 Speed
    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
            .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 3.0, AttributeModifier.Operation.ADDITION), EquipmentSlotGroup.MAINHAND)
            .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.0, AttributeModifier.Operation.ADDITION), EquipmentSlotGroup.MAINHAND)
            .build();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Ensure we are on the server side to apply effects
        if (!attacker.level().isClientSide() && attacker instanceof Player player) {
            int targetFaction = 0;

            // Check the target's FactionID via Persistent Data
            if (target instanceof Player targetPlayer) {
                targetFaction = targetPlayer.getPersistentData().getInt("FactionID");
            }

            // If target is NOT a Monster (Faction 1), 5% chance to Poison
            if (targetFaction != 1) {
                if (attacker.getRandom().nextFloat() <= 0.05f) { 
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0)); // 10 seconds
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
