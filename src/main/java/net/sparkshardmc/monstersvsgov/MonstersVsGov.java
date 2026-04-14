package net.sparkshardmc.monstersvsgov;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.sparkshardmc.monstersvsgov.item.ModItems;
import net.sparkshardmc.monstersvsgov.recipe.ModRecipes; // Import recipes
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonstersVsGov implements ModInitializer {

    public static final String MOD_ID = "monstersvsgov";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ResourceLocation FACTION_PACKET_ID = new ResourceLocation(MOD_ID, "faction_select");

    @Override
    public void onInitialize() {
        LOGGER.info("§8[SYSTEM] net.sparkshardmc.java core loading...");

        // Register Items
        ModItems.registerModItems();

        // Register Custom Recipes (The 3-Gold Stack logic)
        ModRecipes.register();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            CompoundTag data = player.getPersistentData();

            if (!data.contains("FactionID")) {
                data.putInt("FactionID", 0); // 0 = Neutral, 1 = Monster, 2 = Gov
                LOGGER.info("§e[!] Initializing new identity for: " + player.getName().getString());
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(FACTION_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            int factionId = buf.readInt();
            server.execute(() -> {
                player.getPersistentData().putInt("FactionID", factionId);
                LOGGER.info("§fPlayer " + player.getName().getString() + " confirmed as faction: " + factionId);
            });
        });
    }
}
