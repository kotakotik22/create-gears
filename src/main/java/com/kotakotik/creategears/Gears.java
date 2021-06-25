package com.kotakotik.creategears;

import com.kotakotik.creategears.blocks.GearBlock;
import com.kotakotik.creategears.regitration.GearsBlocks;
import com.kotakotik.creategears.regitration.GearsTiles;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.foundation.render.backend.instancing.InstancedTileRenderRegistry;
import com.simibubi.create.foundation.render.backend.instancing.InstancedTileRenderer;
import com.simibubi.create.repack.registrate.Registrate;
import com.simibubi.create.repack.registrate.util.OneTimeEventReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Gears.modid)
public class Gears {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String modid = "creategears";
    public static IEventBus MOD_EVENT_BUS;

    public final Registrate REGISTRATE = Registrate.create(modid);

    public static ItemGroup itemGroup = new ItemGroup(modid) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GearsBlocks.GEAR.get());
        }
    };

    public Gears() {
        // registration
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRATE.itemGroup(()->itemGroup, modid);

        new GearsBlocks(REGISTRATE).register();
        new GearsTiles(REGISTRATE).register();

        MOD_EVENT_BUS.addListener(Gears::clientInit);
    }

    // oh my god please tell me future me actually put this into another class and didnt keep it glued on to this one
    // TODO DO THIS ^^^^^^^^^^^^^^^^^^^
    public static void clientInit(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(GearsTiles.GEAR.get(), KineticTileEntityRenderer::new);
        InstancedTileRenderRegistry.instance.register(GearsTiles.GEAR.get(), SingleRotatingInstance::new);
    }
}

