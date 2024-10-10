package tv.mongotheelder.pitg.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientProxy implements IProxy {

    @Override
    public Level getClientWorld() {
        return Minecraft.getInstance().level;
    }

    @Override
    public Player getPlayer(Supplier<NetworkEvent.Context> context) {
        return Minecraft.getInstance().player;
    }
}
