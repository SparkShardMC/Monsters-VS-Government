package net.sparkshardmc.monstersvsgov;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonstersVsGov implements ModInitializer {

    public static final String MOD_ID = "monstersvsgov";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ResourceLocation FACTION_PACKET_ID = new ResourceLocation(MOD_ID, "faction_select");

    @Override
    public void onInitialize() {
        LOGGER.info("§8[SYSTEM] net.sparkshardmc.java core loading...");

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            CompoundTag data = player.getPersistentData();

            if (!data.contains("FactionID")) {
                data.putInt("FactionID", 0);
            }
            
            LOGGER.info("§fSyncing data for: " + player.getName().getString() + " | Faction: " + data.getInt("FactionID"));
        });

        ServerPlayNetworking.registerGlobalReceiver(FACTION_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            int factionId = buf.readInt();
            server.execute(() -> {
                player.getPersistentData().putInt("FactionID", factionId);
                
                if (factionId == 1) {
                    giveMonsterStarterGear(player);
                } else if (factionId == 2) {
                    giveGovStarterGear(player);
                }

                LOGGER.info("§a[SUCCESS] §f" + player.getName().getString() + " is now assigned to " + factionId);
            });
        });
    }

    private void giveMonsterStarterGear(ServerPlayer player) {
        // Placeholder for Monster items
    }

    private void giveGovStarterGear(ServerPlayer player) {
        // Placeholder for Government items
    }
}v
