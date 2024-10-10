package tv.mongotheelder.pitg.setup;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface IProxy {

    Level getClientWorld();

    Player getPlayer(Supplier<NetworkEvent.Context> context);
}
