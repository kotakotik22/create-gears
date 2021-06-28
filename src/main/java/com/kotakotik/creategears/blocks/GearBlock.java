package com.kotakotik.creategears.blocks;

import com.kotakotik.creategears.Gears;
import com.kotakotik.creategears.regitration.GearsTiles;
import com.kotakotik.creategears.util.GenericUtils;
import com.kotakotik.creategears.util.ShapeBuilder;
import com.kotakotik.creategears.util.ShapeUtils;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.foundation.utility.VoxelShaper;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.nullness.NonnullType;
import net.minecraft.block.BlockState;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class GearBlock extends CogWheelBlock implements ShapeUtils, GenericUtils {
    public VoxelShape shape = cuboid(2.0D, 6.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    public VoxelShaper shaper = new ShapeBuilder(shape).forAxis();

    public VoxelShape shapeLarge = cuboid(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D);
    public VoxelShaper shaperLarge = new ShapeBuilder(shapeLarge).forAxis();

    public GearBlock(boolean large, Properties p_i48440_1_) {
        super(large, p_i48440_1_);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return (isLargeCog() ? shaperLarge : shaper).get(state.get(AXIS));
    }

    @Override
    public boolean hasShaftTowards(IWorldReader world, BlockPos pos, BlockState state, Direction face) {
        return false; // well that was easy
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GearsTiles.GEAR.create();
    }

    public void toCogwheelRecipe(CogWheelBlock cogwheel, RegistrateRecipeProvider prov) {
        ShapelessRecipeBuilder.shapelessRecipe(cogwheel)
                .addIngredient(this).addIngredient(AllItems.ANDESITE_ALLOY.get())
                .addCriterion("has_gear", RegistrateRecipeProvider.hasItem(this))
                .build(prov, modLoc((cogwheel.isLargeCog() ? "large_" : "") + "gear_to_cogwheel"));
    }

    public void fromCogwheelRecipe(CogWheelBlock cogwheel, RegistrateRecipeProvider prov) {
        ShapelessRecipeBuilder.shapelessRecipe(this)
                .addIngredient(cogwheel)
                .addCriterion("has_cogwheel", RegistrateRecipeProvider.hasItem(cogwheel))
                .build(prov, modLoc((cogwheel.isLargeCog() ? "large_" : "") + "gear_from_cogwheel"));
    }
}
