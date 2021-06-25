package com.kotakotik.creategears.regitration;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.content.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GearsPonder {
    public static void register() {
        // i gotta go sleep
        // but what if i forget what i was doing when i wake up
        // nah better finish this


        // please kill me

        // restarting minecraft is so tedious
        // i just wanna sleep

        PonderRegistry.forComponents(GearsBlocks.GEAR, GearsBlocks.LARGE_GEAR)
                .addStoryBoard("cog/small", KineticsScenes::cogAsRelay)
                .addStoryBoard("cog/large", KineticsScenes::largeCogAsRelay);

        PonderRegistry.tags.forTag(PonderTag.KINETIC_RELAYS)
                .add(GearsBlocks.GEAR)
                .add(GearsBlocks.LARGE_GEAR);
    }
}
