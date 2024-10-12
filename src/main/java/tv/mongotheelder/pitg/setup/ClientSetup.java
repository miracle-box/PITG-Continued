package tv.mongotheelder.pitg.setup;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.items.GlazingTool;
import tv.mongotheelder.pitg.items.GlazingToolMode;

@Mod.EventBusSubscriber(modid = Pitg.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASS_PANE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(Registration.RED_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.WHITE_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.BLUE_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.ORANGE_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.MAGENTA_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.YELLOW_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.LIME_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.PINK_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GRAY_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.CYAN_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.PURPLE_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.BROWN_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GREEN_STAINED_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.BLACK_STAINED_GLASS_PANE.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(Registration.DUAL_GLASS_PANE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(Registration.RED_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.WHITE_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.BLUE_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.ORANGE_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.MAGENTA_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.LIGHT_BLUE_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.YELLOW_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.LIME_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.PINK_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GRAY_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.LIGHT_GRAY_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.CYAN_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.PURPLE_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.BROWN_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GREEN_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.BLACK_STAINED_DUAL_GLASS_PANE.get(), RenderType.translucent());

        event.enqueueWork(()-> ItemProperties.register(Registration.GLAZING_TOOL_ITEM.get(), new ResourceLocation("pitg:unbreaking"),
                    (itemStack, world, livingEntity, id) -> {
                        if (livingEntity != null) {
                            boolean flag = livingEntity.getMainHandItem() == itemStack;

                            var item = livingEntity.getMainHandItem().getItem();
                            if (item instanceof GlazingTool) {
                                return flag && ((GlazingTool) item).getMode(itemStack) == GlazingToolMode.UNBREAKABLE ? 1.0f : 0.0f;
                            }
                        }
                        return 0.0F;
                    }));

    }
}
