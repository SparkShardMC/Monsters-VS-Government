package net.sparkshardmc.monstersvsgov.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record FactionPayload(int factionId) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation("monstersvsgov", "faction_select");
    public static final StreamCodec<FriendlyByteBuf, FactionPayload> CODEC = CustomPacketPayload.codec(FactionPayload::write, FactionPayload::new);

    public FactionPayload(FriendlyByteBuf buf) {
        this(buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(factionId);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
