package net.sparkshardmc.monstersvsgov;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.resources.ResourceLocation;
import net.sparkshardmc.monstersvsgov.item.ModItems;
import net.sparkshardmc.monstersvsgov.recipe.ModRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonstersVsGov implements ModInitializer {
    public static final String MOD_ID = "monstersvsgov";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // 26.1.2 uses a more strict Packet Identification system
    public static final ResourceLocation FACTION_PACKET_ID = new ResourceLocation(MOD_ID, "faction_select");

    @Override
    public void onInitialize() {
        LOGGER.info("§b[MvG] Loading Monsters Vs Government for 26.1.2...");

        // Registering Content
        ModItems.registerModItems();
        ModRecipes.register();

        // Handle Player Joining (Faction Initialization)
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var player = handler.getPlayer();
            // Checking persistent data (26.1.2 internal NBT access)
            if (!player.getPersistentData().contains("FactionID")) {
                player.getPersistentData().putInt("FactionID", 0); // 0 = Neutral
                LOGGER.info("§e[!] New identity assigned to: " + player.getName().getString());
            }
        });

        // Networking Receiver
        ServerPlayNetworking.registerGlobalReceiver(FACTION_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            int factionId = buf.readInt();
            server.execute(() -> {
                player.getPersistentData().putInt("FactionID", factionId);
                LOGGER.info("§a[MvG] " + player.getName().getString() + " is now Faction: " + factionId);
            });
        });
    }
}
