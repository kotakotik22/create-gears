package com.kotakotik.creategears.util;

import com.kotakotik.creategears.Gears;
import net.minecraft.util.ResourceLocation;

public interface GenericUtils {
    default ResourceLocation modLoc(String path) {
        return new ResourceLocation(Gears.modid, path);
    }
}
