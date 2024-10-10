package tv.mongotheelder.pitg.setup;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static tv.mongotheelder.pitg.Pitg.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    private static final List<Supplier<? extends ItemLike>> PITG_TAB_ITEMS = new ArrayList<>();

    private static final RegistryObject<CreativeModeTab> PITG_TAB = TABS.register("pitg",
            () -> CreativeModeTab.builder()
                    .title(Component.literal("Pane In the Glasses"))
                    .icon(Registration.GLASS_PANE.get().asItem()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            PITG_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build());

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        PITG_TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}
