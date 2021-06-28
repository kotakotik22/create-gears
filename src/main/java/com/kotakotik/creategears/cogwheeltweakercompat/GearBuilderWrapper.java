package com.kotakotik.creategears.cogwheeltweakercompat;

import com.blamejared.contenttweaker.blocks.BlockBuilder;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister(modDeps = {"contenttweaker", "cogwheeltweaker"})
@ZenCodeType.Name("mods.creategears.block.gear.GearBuilder")
public class GearBuilderWrapper {
    @ZenCodeType.Field
    public final BlockBuilder blockBuilder;
    @ZenCodeType.Field
    public final GearBuilder builder;

    @ZenCodeType.Constructor
    public GearBuilderWrapper(BlockBuilder builder) {
        this.blockBuilder = builder;
        this.builder = new GearBuilder(builder);
    }

    @ZenCodeType.Method
    public GearBuilder get() {
        return builder;
    }

    @ZenCodeType.Method
    public BlockBuilder getBlock() {
        return blockBuilder;
    }
}
