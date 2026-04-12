package net.sparkshardmc.monstersvsgov.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;

public class ModItems {

    public static final Item DECAYED_DAGGER = registerItem("decayed_dagger", 
        new DecayedDaggerItem(Tiers.STONE, 3, -2.0f, new Item.Properties()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, 
            new ResourceLocation(MonstersVsGov.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MonstersVsGov.LOGGER.info("§a[ARMORY] Registering " + MonstersVsGov.MOD_ID + " equipment.");
    }
}
