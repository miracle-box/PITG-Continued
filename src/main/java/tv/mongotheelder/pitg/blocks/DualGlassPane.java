package tv.mongotheelder.pitg.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DualGlassPane extends GlassPane {
    public DualGlassPane(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecated")
    protected VoxelShape[] makeShapes(double paneWidth, double paneThickness, double paneHeight) {
        VoxelShape south = Block.box(0.0D, 0.0D, paneWidth-paneThickness, paneWidth, paneHeight, paneWidth);
        VoxelShape west = Block.box(0.0D, 0.0D, 0.0D, paneThickness, paneHeight, paneWidth);
        VoxelShape north = Block.box(0.0D, 0.0D, 0.0D, paneWidth, paneHeight, paneThickness);
        VoxelShape east = Block.box(paneWidth-paneThickness, 0.0D, 0.0D, paneWidth, paneHeight, paneWidth);
        VoxelShape empty = Shapes.empty();
        VoxelShape[] shapes = new VoxelShape[]{
                empty, // 0000
                Shapes.or(north, south), // 0001
                Shapes.or(east, west), // 0010
                Shapes.or(north, east, south, west), // 0011
                Shapes.or(north, south), // 0100
                empty, // 0101
                Shapes.or(north, east, south, west), // 0110
                empty, // 0111
                Shapes.or(east, west), // 1000
                Shapes.or(north, east, south, west), // 1001
                empty, // 1010
                empty, // 1011
                Shapes.or(north, east, south, west), // 1100
                empty, // 1101
                empty, // 1110
                empty // 1111
        };
        return shapes;
    }
}
