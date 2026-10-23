package team.lookingglass.elsewhere.registry.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public interface EBlockStateProperties {
    BooleanProperty WALL_HANGING = BooleanProperty.create("wall_hanging");

    Map<Direction, VoxelShape> WALL_LANTERN_SHAPES = Shapes.rotateHorizontal(Shapes.or(
            Block.box(5.0, 1.0, 5.0, 11.0, 8.0, 11.0),
            Block.box(6.0, 8.0, 6.0, 10.0, 10.0, 10.0),
            Block.box(7.0, 13.0, 5.0, 9.0, 15.0, 15.0),
            Block.box(6.0, 9.0, 15.0, 10.0, 16.0, 16.0),
            Block.box(6.0, 12.0, 4.0, 10.0, 16.0, 5.0)
    ));
}