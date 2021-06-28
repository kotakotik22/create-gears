package com.kotakotik.creategears;

import com.kotakotik.creategears.cogwheeltweakercompat.CTCRegistration;
import com.kotakotik.creategears.regitration.GearsBlocks;
import com.kotakotik.creategears.regitration.GearsPonder;
import com.kotakotik.creategears.regitration.GearsTiles;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.render.backend.instancing.InstancedTileRenderRegistry;
import com.tterrag.registrate.util.OneTimeEventReceiver;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Gears.modid)
public class Gears {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String modid = "creategears";
    public static IEventBus MOD_EVENT_BUS;

    public final CreateRegistrate REGISTRATE = CreateRegistrate.lazy(modid).get();

    public static boolean isCogwheelTweakerLoaded;

    public static ItemGroup itemGroup = new ItemGroup(modid) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GearsBlocks.GEAR.get());
        }
    };

    public Gears() {
        isCogwheelTweakerLoaded = ModList.get().isLoaded("cogwheeltweaker");

        // events
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        if(isCogwheelTweakerLoaded) {
            CTCRegistration r = new CTCRegistration();
            MOD_EVENT_BUS.register(r);
            r.r();
        }

        // registration

//        GearsConfig.register();
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GearsConfig.CONFIG, "gears.toml");

        REGISTRATE.itemGroup(()->itemGroup, "Create Gears");
        new GearsBlocks(REGISTRATE).register();
        new GearsTiles(REGISTRATE).register();

        OneTimeEventReceiver.addListener(MOD_EVENT_BUS, FMLClientSetupEvent.class, ($) -> {
            GearsPonder.register();
        });
    }
}

