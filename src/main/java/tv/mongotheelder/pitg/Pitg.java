package tv.mongotheelder.pitg;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.setup.*;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("pitg")
public class Pitg {
    public static final String MODID = "pitg";
    public static final String GLAZING_TOOL_MODE_KEY = "glazing_tool_mode";
    public static final ResourceLocation NETWORK_CHANNEL = new ResourceLocation("pitg:glazing_tool");

    public static final String[] COLORS = {"", "white", "red", "blue", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "brown", "green", "black"};
    private static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    @SuppressWarnings("removal")
    public Pitg() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        // [TODO] Should split all registrations into categories.
        Registration.init();
        CreativeTab.TABS.register(bus);

        bus.addListener(ModSetup::init);
        bus.addListener(ClientSetup::init);
    }
}
