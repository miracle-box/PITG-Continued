package tv.mongotheelder.pitg.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import tv.mongotheelder.pitg.Pitg;

public class PacketHandler {
    private static SimpleChannel NETWORK_CHANNEL;
    private static int messageId = 0;

    public static void registerMessages() {
        NETWORK_CHANNEL = createChannel(Pitg.NETWORK_CHANNEL);
        NETWORK_CHANNEL.messageBuilder(ModePacket.class, nextMessageId())
                .encoder(ModePacket::encode)
                .decoder(ModePacket::decode)
                .consumerMainThread(ModePacket::handle)
                .add();
    }

    private static int nextMessageId() {
        return messageId++;
    }

    private static SimpleChannel createChannel(ResourceLocation channelName) {
        return NetworkRegistry.ChannelBuilder.named(channelName)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .networkProtocolVersion(() -> "1.0")
                .simpleChannel();
    }

    public static void sendToPlayer(Object pkt, ServerPlayer player) {
        NETWORK_CHANNEL.sendTo(pkt, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}
