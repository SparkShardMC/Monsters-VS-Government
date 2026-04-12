package net.sparkshardmc.monstersvsgov.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.sparkshardmc.monstersvsgov.client.gui.EntryScreen;

public class MonstersVsGovClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            client.execute(() -> {
                if (client.player != null) {
                    Minecraft.getInstance().setScreen(new EntryScreen());
                }
            });
        });
    }
}
