package net.sparkshardmc.monstersvsgov.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.sparkshardmc.monstersvsgov.client.screen.EntryScreen;

public class MonstersVsGovClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Logic: When the client joins a server, check if they need to see the Entry Screen
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            // We use a small delay or a check to see if the player has a faction
            // In 26.1.2, the easiest way is to check the local player's data
            client.execute(() -> {
                if (client.player != null) {
                    // This opens the GUI if they are currently Faction 0 (Neutral)
                    // Note: PersistentData is server-side, so usually you'd trigger this via a packet
                    // For now, this is where you'd put the screen trigger:
                    // Minecraft.getInstance().setScreen(new EntryScreen());
                }
            });
        });
    }
}
