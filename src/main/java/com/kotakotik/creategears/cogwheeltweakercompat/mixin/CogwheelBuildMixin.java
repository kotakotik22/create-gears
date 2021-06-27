package com.kotakotik.creategears.cogwheeltweakercompat.mixin;

import com.blamejared.contenttweaker.VanillaFactory;
import com.kotakotik.creategears.cogwheeltweakercompat.CTCGearBlock;
import mod.grimmauld.cogwheeltweaker.cogwheel.CoTWheelBuilder;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(CoTWheelBuilder.class)
public abstract class CogwheelBuildMixin {
    @Inject(at = @At(value = "RETURN"), method = {"build"}, remap = false, cancellable = true)
    public void build(ResourceLocation resourceLocation, CallbackInfo ci) {
        VanillaFactory.queueBlockForRegistration(new CTCGearBlock((CoTWheelBuilder) (Object) this, new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath().replaceAll("cog", "gear"))));
    }
}
