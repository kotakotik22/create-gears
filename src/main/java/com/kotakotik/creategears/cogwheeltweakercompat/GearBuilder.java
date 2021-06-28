package com.kotakotik.creategears.cogwheeltweakercompat;

import com.blamejared.contenttweaker.VanillaFactory;
import com.blamejared.contenttweaker.api.blocks.BlockTypeBuilder;
import com.blamejared.contenttweaker.blocks.BlockBuilder;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import mod.grimmauld.cogwheeltweaker.cogwheel.CoTWheelBuilder;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nullable;

@ZenRegister(modDeps = {"contenttweaker", "cogwheeltweaker"})
@ZenCodeType.Name("mods.creategears.block.gear.GearBuilder")
@SuppressWarnings("unused")
public class GearBuilder extends CoTWheelBuilder {
    private boolean noCog = false;

    @ZenCodeType.Method("noCog")
    public GearBuilder withNoCog() {
        this.noCog = true;
        return this;
    }

    public GearBuilder(BlockBuilder blockBuilder) {
        super(blockBuilder);
    }

    @Override
    public void build(ResourceLocation resourceLocation) {
        VanillaFactory.queueBlockForRegistration(new CTCGearBlock(this,
                new ResourceLocation(resourceLocation.getNamespace(),
                        resourceLocation.getPath())));

        if(!noCog) {
            super.build(new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath().replaceAll("gear", "cog")));
        }
    }
}
