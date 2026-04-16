package net.sparkshardmc.monstersvsgov;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.sparkshardmc.monstersvsgov.item.ModItems;
import net.sparkshardmc.monstersvsgov.recipe.ModRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonstersVsGov implements ModInitializer {
    public static final String MOD_ID = "monstersvsgov";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("§b[MvG] Loading Monsters Vs Government for 26.1.2...");

        ModItems.registerModItems();
        ModRecipes.register();

        // 1. Register the Payload Type (Required in 26.1.2)
        PayloadTypeRegistry.serverboundPlay().register(FactionPayload.ID, FactionPayload.CODEC);

        // 2. Handle Player Join
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var player = handler.getPlayer();
            if (!player.getPersistentData().contains("FactionID")) {
                player.getPersistentData().putInt("FactionID", 0); 
                LOGGER.info("§e[!] New identity assigned to: " + player.getName().getString());
            }
        });

        // 3. Register Global Receiver using the new Payload system
        ServerPlayNetworking.registerGlobalReceiver(FactionPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                context.player().getPersistentData().putInt("FactionID", payload.factionId());
                LOGGER.info("§a[MvG] " + context.player().getName().getString() + " joined Faction: " + payload.factionId());
            });
        });
    }

    /**
     * New Requirement for 26.1.2: A Payload Record
     * This defines what data is being sent (the int) and how to read/write it.
     */
    public record FactionPayload(int factionId) implements CustomPacketPayload {
        public static final Id<FactionPayload> ID = new Id<>(new ResourceLocation(MOD_ID, "faction_select"));
        public static final StreamCodec<RegistryFriendlyByteBuf, FactionPayload> CODEC = StreamCodec.composite(
                StreamCodec.unit(0), // Placeholder if needed
                p -> p.factionId,
                FactionPayload::new
        );

        @Override
        public Id<? extends CustomPacketPayload> type() {
            return ID;
        }
    }
}
