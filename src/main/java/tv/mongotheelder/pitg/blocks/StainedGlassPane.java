package tv.mongotheelder.pitg.blocks;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

public class StainedGlassPane extends GlassPane {
    private final DyeColor color;

    public StainedGlassPane(DyeColor colorIn, Block.Properties properties) {
        super(properties);
        this.color = colorIn;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, Boolean.FALSE)
                .setValue(EAST, Boolean.FALSE)
                .setValue(SOUTH, Boolean.FALSE)
                .setValue(WEST, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE));
    }
}
