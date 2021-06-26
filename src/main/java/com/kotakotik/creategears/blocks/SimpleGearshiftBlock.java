package com.kotakotik.creategears.blocks;

import com.kotakotik.creategears.regitration.GearsTiles;
import com.simibubi.create.content.contraptions.relays.encased.GearshiftBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SimpleGearshiftBlock extends GearshiftBlock {
    public SimpleGearshiftBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(POWERED, true));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(POWERED, true);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        // ignore redstone stuff
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GearsTiles.SIMPLE_GEARSHIFT.create();
    }
}
