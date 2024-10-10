package tv.mongotheelder.pitg.items;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mongotheelder.pitg.Pitg;
import tv.mongotheelder.pitg.blocks.GlassPane;
import tv.mongotheelder.pitg.networking.ModePacket;
import tv.mongotheelder.pitg.networking.PacketHandler;
import tv.mongotheelder.pitg.setup.Config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GlazingTool extends Item {
    private static final Logger LOGGER = LogManager.getLogger();

    public GlazingTool(Item.Properties properties) {
        super(properties);

        // https://docs.minecraftforge.net/en/1.20.x/resources/client/models/itemproperties/#adding-properties-to-items
        // This should be done *client only*, seems we need to investigate.
        ItemProperties.register(this, new ResourceLocation("pitg:unbreaking"),
                (itemStack, world, livingEntity, id) -> {
                    if (livingEntity != null) {
                        boolean flag = livingEntity.getMainHandItem() == itemStack;
                        if (livingEntity.getMainHandItem().getItem() instanceof GlazingTool) {
                            return flag && getMode(itemStack) == GlazingToolMode.UNBREAKABLE ? 1.0f : 0.0f;
                        }
                    }
                    return 0.0F;
                });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (Config.ENABLE_PANE_BREAK.get() || Config.ENABLE_UNBREAKABLE.get()) {
            tooltip.add(Component.literal("Mode: " + getMode(stack).getTitle()));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return (Config.ENABLE_PANE_BREAK.get() && getMode(stack) == GlazingToolMode.BREAK) || stack.isEnchanted();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();

        if (!world.isClientSide) {
            Player player = context.getPlayer();
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block instanceof GlassPane) {
                if (Config.ENABLE_PANE_BREAK.get() && getMode(itemstack) == GlazingToolMode.BREAK) {
                    if (player != null && player.isCrouching()) {
                        world.removeBlock(blockpos, false);
                        Block.popResource(world, blockpos, block.getCloneItemStack(blockstate, null, world, blockpos, player));
                    }

                } else if (Config.ENABLE_UNBREAKABLE.get() && getMode(itemstack) == GlazingToolMode.UNBREAKABLE) {
                    world.setBlockAndUpdate(blockpos, blockstate.setValue(GlassPane.UNBREAKABLE, !blockstate.getValue(GlassPane.UNBREAKABLE)));
                } else {
                    // If player is crouching, rotate the pane keeping the facing style consistent (i.e. N->E, SW->NW, etc)
                    // Note: COUNTERCLOCKWISE_90 is being used to pass player crouch context and doesn't represent direction (all rotations are CLOCKWISE_90)
                    world.setBlockAndUpdate(blockpos, block.rotate(blockstate, world, blockpos, player != null && player.isCrouching() ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_90));
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (!worldIn.isClientSide()) {
            ItemStack stack = playerIn.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!stack.isEmpty() && stack.getItem() instanceof GlazingTool) {
                GlazingToolMode mode = advanceMode(stack);
                ((GlazingTool) (stack.getItem())).setMode(stack, mode);
                PacketHandler.sendToPlayer(new ModePacket(mode, EquipmentSlot.MAINHAND), (ServerPlayer) playerIn);
            } else {
                LOGGER.error("GlazingTool::onItemRightClick was triggered for an illegal item: " + stack);
            }
        }
        return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
    }

    public GlazingToolMode getMode(ItemStack stack) {
        return hasValidTag(stack) ? GlazingToolMode.byIndex(stack.getTag().getInt(Pitg.GLAZING_TOOL_MODE_KEY)) : GlazingToolMode.ROTATE;
    }

    public void setMode(ItemStack stack, GlazingToolMode mode) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(Pitg.GLAZING_TOOL_MODE_KEY, mode.getIndex());
        stack.setTag(tag);
    }

    public GlazingToolMode advanceMode(@Nonnull ItemStack stack) {
        GlazingToolMode mode = getMode(stack);
        GlazingToolMode newMode = getMode(stack).advanceMode();
        if (mode != newMode) {
            setMode(stack, newMode);
        }
        return getMode(stack);
    }

    private boolean hasValidTag(ItemStack stack) {
        if (stack == null || stack.getTag() == null) return false;
        CompoundTag tag = stack.getOrCreateTag();
        return tag.contains(Pitg.GLAZING_TOOL_MODE_KEY, Tag.TAG_INT);
    }
}
