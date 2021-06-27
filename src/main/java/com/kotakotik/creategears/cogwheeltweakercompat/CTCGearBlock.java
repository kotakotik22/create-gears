package com.kotakotik.creategears.cogwheeltweakercompat;

import com.blamejared.contenttweaker.api.resources.*;
import com.kotakotik.creategears.blocks.GearBlock;
import com.kotakotik.creategears.regitration.GearsBlocks;
import mod.grimmauld.cogwheeltweaker.cogwheel.CoTWheelBlock;
import mod.grimmauld.cogwheeltweaker.cogwheel.CoTWheelBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

public class CTCGearBlock extends CoTWheelBlock {
    GearBlock g() {
        return (isLargeCog() ? GearsBlocks.LARGE_GEAR : GearsBlocks.GEAR).get();
    }

    public CTCGearBlock(CoTWheelBuilder builder, ResourceLocation location) {
        super(builder, location);
        this.myBuilder = builder;
    }

    public final CoTWheelBuilder myBuilder; // MY BUILDER >:(

    @Override
    public boolean hasShaftTowards(IWorldReader world, BlockPos pos, BlockState state, Direction face) {
        return g().hasShaftTowards(world, pos, state, face);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return g().getShape(state, worldIn, pos, context);
    }

    @Nonnull
    @Override
    public Collection<WriteableResource> getResourcePackResources() {
        Collection<WriteableResource> out = new ArrayList();
        if (this.myBuilder.hasNoTemplate()) {
            return out;
        } else {
            ResourceLocation location = this.getRegistryNameNonNull();
            if (!this.myBuilder.isLegacyModel()) {
                out.add(WriteableResourceImage.noImage(ImageType.BLOCK, location));
            }

            if (this.myBuilder.isLegacyModel()) {
                out.add((new WriteableResourceTemplate(ResourceType.ASSETS, location, "models", "block")).withTemplate(ResourceType.ASSETS, new ResourceLocation("cogwheeltweaker", this.isLargeCog() ? "models/block/block_legacy_large_cogwheel" : "models/block/block_legacy_cogwheel")).setProperty("NAMESPACE", this.myBuilder.getLegacyTexture().getNamespace()).setProperty("PATH", this.myBuilder.getLegacyTexture().getPath()).setProperty("NAMESPACE_TOP", this.myBuilder.getTopTexture().getNamespace()).setProperty("PATH_TOP", this.myBuilder.getTopTexture().getPath()));
            } else {
                out.add((new WriteableResourceTemplate(ResourceType.ASSETS, location, "models", "block")).withTemplate(ResourceType.ASSETS, new ResourceLocation("creategears", this.isLargeCog() ? "models/block/large_gear" : "models/block/gear")).setLocationProperty(location));
            }

//            out.add((new WriteableResourceTemplate(ResourceType.ASSETS, location, "models", "item")).withTemplate(ResourceType.ASSETS, new ResourceLocation("creategears", this.isLargeCog() ? "models/block/large_gear" : "models/block/gear")).setLocationProperty(location));

            out.add((new WriteableResourceTemplate(ResourceType.ASSETS, location, "blockstates")).withTemplate(ResourceType.ASSETS, new ResourceLocation("cogwheeltweaker", "blockstates/block_cogwheel")).setLocationProperty(location));
            return out;
        }
    }
}
