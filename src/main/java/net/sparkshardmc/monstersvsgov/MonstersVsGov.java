package net.sparkshardmc.monstersvsgov;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.sparkshardmc.monstersvsgov.item.ModItems;
import net.sparkshardmc.monstersvsgov.recipe.ModRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonstersVsGov implements ModInitializer {

    public static final String MOD_ID = "monstersvsgov";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ResourceLocation FACTION_PACKET_ID = new ResourceLocation(MOD_ID, "faction_select");
    public static final ResourceLocation FACTION_SYNC_ID = new ResourceLocation(MOD_ID, "faction_sync");

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModRecipes.register();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            CompoundTag data = player.getPersistentData();

            if (!data.contains("FactionID")) {
                data.putInt("FactionID", 0);
            }

            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeInt(data.getInt("FactionID"));
            ServerPlayNetworking.send(player, FACTION_SYNC_ID, buf);
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
            });
        });
    }

    private void giveMonsterStarterGear(ServerPlayer player) {
        player.getInventory().add(new ItemStack(ModItems.DECAYED_DAGGER));
        player.getInventory().add(new ItemStack(ModItems.SPIRIT_BLOOD, 2));
        player.getInventory().add(new ItemStack(Items.ROTTEN_FLESH, 16));
    }

    private void giveGovStarterGear(ServerPlayer player) {
        player.getInventory().add(new ItemStack(Items.IRON_SWORD));
        player.getInventory().add(new ItemStack(Items.IRON_CHESTPLATE));
        player.getInventory().add(new ItemStack(Items.COOKED_BEEF, 16));
        player.getInventory().add(new ItemStack(Items.SHIELD));
    }
}
