package net.sparkshardmc.monstersvsgov.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;

public class ModItems {

    // 1. Spirit Blood (Crafting Ingredient)
    public static final Item SPIRIT_BLOOD = registerItem("spirit_blood", 
        new Item(new Item.Properties()));

    // 2. Decayed Dagger (Tier: Stone equivalent, but low durability)
    public static final Item DECAYED_DAGGER = registerItem("decayed_dagger", 
        new SwordItem(Tiers.STONE, new Item.Properties().durability(100)));

    // 3. Repaired Dagger (Tier: Iron equivalent + Blindness Ability)
    public static final Item REPAIRED_DAGGER = registerItem("repaired_dagger", 
        new RepairedDaggerItem(Tiers.IRON, new Item.Properties().attributes(RepairedDaggerItem.createAttributes())));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MonstersVsGov.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonstersVsGov.LOGGER.info("§6[MvG] Registering Items for 26.1.2...");
    }
}
