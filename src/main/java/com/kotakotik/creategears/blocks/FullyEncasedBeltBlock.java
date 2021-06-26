package com.kotakotik.creategears.blocks;

import com.kotakotik.creategears.regitration.GearsTiles;
import com.simibubi.create.AllTileEntities;
import com.simibubi.create.content.contraptions.relays.encased.EncasedBeltBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class FullyEncasedBeltBlock extends EncasedBeltBlock {
    public FullyEncasedBeltBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasShaftTowards(IWorldReader world, BlockPos pos, BlockState state, Direction face) {
        return false;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GearsTiles.FULLY_ENCASED_BELT.create();
    }

    public String getSuffix(String suffix) {
        if(suffix.contains("horizontal")) return "horizontal";
        if(suffix.contains("vertical")) return "vertical";
        return "horizontal"; // yes i know i know i can just remove the horizontal check above becaues its laywashfhjdhjkda ii dont care
    }
}
