package com.kotakotik.creategears.util;

import com.simibubi.create.AllShapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public interface ShapeUtils {
    default VoxelShape cuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Block.makeCuboidShape(x1, y1, z1, x2, y2, z2);
    }

    default ShapeBuilder shape(VoxelShape shape) {
        return new ShapeBuilder(shape);
    }
}
