package com.kotakotik.creategears.util;

import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.function.BiFunction;

// copy of create's AllShapes.Builder class except public
public class ShapeBuilder implements ShapeUtils { // wow thanks create, i love that you privated your builder!
    VoxelShape shape;

    public ShapeBuilder(VoxelShape shape) {
        this.shape = shape;
    }

    public ShapeBuilder add(VoxelShape shape) {
        this.shape = VoxelShapes.or(this.shape, shape);
        return this;
    }

    public ShapeBuilder add(double x1, double y1, double z1, double x2, double y2, double z2) {
        return this.add(cuboid(x1, y1, z1, x2, y2, z2));
    }

    public ShapeBuilder erase(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.shape = VoxelShapes.combineAndSimplify(this.shape, cuboid(x1, y1, z1, x2, y2, z2), IBooleanFunction.ONLY_FIRST);
        return this;
    }

    public VoxelShape build() {
        return this.shape;
    }

    public VoxelShaper build(BiFunction<VoxelShape, Direction, VoxelShaper> factory, Direction direction) {
        return (VoxelShaper)factory.apply(this.shape, direction);
    }

    public VoxelShaper build(BiFunction<VoxelShape, Direction.Axis, VoxelShaper> factory, Direction.Axis axis) {
        return (VoxelShaper)factory.apply(this.shape, axis);
    }

    public VoxelShaper forDirectional(Direction direction) {
        return this.build(VoxelShaper::forDirectional, direction);
    }

    public VoxelShaper forAxis() {
        return this.build(VoxelShaper::forAxis, Direction.Axis.Y);
    }

    public VoxelShaper forHorizontalAxis() {
        return this.build(VoxelShaper::forHorizontalAxis, Direction.Axis.Z);
    }

    public VoxelShaper forHorizontal(Direction direction) {
        return this.build(VoxelShaper::forHorizontal, direction);
    }

    public VoxelShaper forDirectional() {
        return this.forDirectional(Direction.UP);
    }
}
