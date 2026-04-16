package net.sparkshardmc.monstersvsgov.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlotGroup;

public class RepairedDaggerItem extends SwordItem {
    public RepairedDaggerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    // Modern 26.1.2 Attribute System
    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
            // 5 Damage total
            .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 5.0, AttributeModifier.Operation.ADDITION), EquipmentSlotGroup.MAINHAND)
            // -2.0 Speed (Fast for a dagger)
            .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.0, AttributeModifier.Operation.ADDITION), EquipmentSlotGroup.MAINHAND)
            .build();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Special Ability: 10% Chance to Blind for 5 seconds (100 ticks)
        if (!attacker.level().isClientSide() && attacker.getRandom().nextFloat() <= 0.10f) {
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
