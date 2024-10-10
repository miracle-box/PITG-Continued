package tv.mongotheelder.pitg.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.items.GlazingTool;
import tv.mongotheelder.pitg.items.GlazingToolMode;

import java.util.function.Supplier;

public class ModePacket {
    private static final Logger LOGGER = LogManager.getLogger();

    private final GlazingToolMode mode;
    private final EquipmentSlot slot;

    public ModePacket(GlazingToolMode mode, EquipmentSlot slot) {
        this.mode = mode;
        this.slot = slot;
    }

    public static void handle(ModePacket msg, Supplier<NetworkEvent.Context> context) {
        Player player = Pitg.proxy.getPlayer(context);
        if (player == null) {
            LOGGER.error("Received a Glazing Tool mode change packet from a null player");
            return;
        }
        context.get().enqueueWork(() -> {
            ItemStack stack = player.getItemBySlot(msg.slot);
            if (!stack.isEmpty() && stack.getItem() instanceof GlazingTool) {
                ((GlazingTool) stack.getItem()).setMode(stack, msg.mode);
                LOGGER.debug("Setting mode on client to " + ((GlazingTool) (stack.getItem())).getMode(stack));
            } else {
                LOGGER.warn("Glazing Tool mode change could not locate the tool in the player");
            }
        });
        context.get().setPacketHandled(true);
    }

    public static void encode(ModePacket pkt, FriendlyByteBuf buf) {
        buf.writeEnum(pkt.mode);
        buf.writeEnum(pkt.slot);
    }

    public static ModePacket decode(FriendlyByteBuf buf) {
        return new ModePacket(buf.readEnum(GlazingToolMode.class), buf.readEnum(EquipmentSlot.class));
    }
}
