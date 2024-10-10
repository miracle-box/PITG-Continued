package tv.mongotheelder.pitg.setup;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerProxy implements IProxy {

    @Override
    public Level getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public Player getPlayer(Supplier<NetworkEvent.Context> context) {
        return context.get().getSender();
    }
}
