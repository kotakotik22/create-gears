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
//    private boolean large;
//    private boolean noTemplate;
//    @Nullable
//    private ResourceLocation topTexture;
//    @Nullable
//    private ResourceLocation legacyTexture;

    @ZenCodeType.Method("noCog")
    public GearBuilder withNoCog() {
        this.noCog = true;
        return this;
    }

    public GearBuilder(BlockBuilder blockBuilder) {
        super(blockBuilder);
    }

//    public CoTWheelBuilder toCogBuilder() {
//       CoTWheelBuilder b = new CoTWheelBuilder(blockBuilder);
//       if(hasNoTemplate()) b.noTemplate();
//       b.withLarge(isLarge());
//       if(isLegacyModel()) {
//           b.withLegacyTexture(getLegacyTexture().toString(), getTopTexture().toString());
//       }
//       return b;
//    }

    @Override
    public void build(ResourceLocation resourceLocation) {
        VanillaFactory.queueBlockForRegistration(new CTCGearBlock(this,
                new ResourceLocation(resourceLocation.getNamespace(),
                        resourceLocation.getPath())));

        if(!noCog) {
            super.build(new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath().replaceAll("gear", "cog")));
        }
    }

//    public boolean isLegacyModel() {
//        return this.topTexture != null && this.legacyTexture != null;
//    }

//    public boolean isLarge() {
//        return this.large;
//    }

//    public boolean hasNoTemplate() {
//        return this.noTemplate;
//    }

//    @Nullable
//    public ResourceLocation getTopTexture() {
//        return this.topTexture;
//    }

//    @Nullable
//    public ResourceLocation getLegacyTexture() {
//        return this.legacyTexture;
//    }

//    @ZenCodeType.Method
//    public GearBuilder withLarge(boolean large) {
//        this.large = large;
//        return this;
//    }

//    @ZenCodeType.Method
//    public GearBuilder noTemplate() {
//        this.noTemplate = true;
//        return this;
//    }

//    @ZenCodeType.Method
//    public GearBuilder withLegacyTexture(String legacyTexture, String topTexture) {
//        this.topTexture = new ResourceLocation(topTexture);
//        this.legacyTexture = new ResourceLocation(legacyTexture);
//        return this;
//    }

//    @ZenCodeType.Method
//    public GearBuilder withLegacyTexture(String legacyTexture) {
//        return this.withLegacyTexture(legacyTexture, legacyTexture + "_top");
//    }
}
