package net.sparkshardmc.monstersvsgov.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;
import net.sparkshardmc.monstersvsgov.client.screen.EntryScreen;

public class MonstersVsGovClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(MonstersVsGov.FACTION_SYNC_ID, (client, handler, buf, responseSender) -> {
            int factionId = buf.readInt();
            client.execute(() -> {
                if (factionId == 0) {
                    Minecraft.getInstance().setScreen(new EntryScreen());
                }
            });
        });
    }
}
