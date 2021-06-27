package com.kotakotik.creategears.cogwheeltweakercompat;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticBlockModel;
import com.simibubi.create.foundation.render.backend.instancing.InstancedTileRenderRegistry;
import mod.grimmauld.cogwheeltweaker.CogwheelTweaker;
import mod.grimmauld.cogwheeltweaker.ObjectHolders;
import mod.grimmauld.cogwheeltweaker.cogwheel.CoTWheelBlock;
import mod.grimmauld.cogwheeltweaker.cogwheel.CoTWheelTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CTCRegistration {
    public void r() {
    }

//    @SubscribeEvent
//    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
//        event.getRegistry().register(TileEntityType.Builder.create(CoTWheelTileEntity::new,
//                CoTWheelTileEntity.validBlocks.toArray(new Block[0])).build(null).setRegistryName("cotgear"));
//    }


//    @OnlyIn(Dist.CLIENT)
//    @SubscribeEvent
//    public static void clientInit(FMLClientSetupEvent event) {
//        ClientRegistry.bindTileEntityRenderer(ObjectHolders.COTWHEEL_TILE, KineticTileEntityRenderer::new);
//        InstancedTileRenderRegistry.instance.register(ObjectHolders.COTWHEEL_TILE, SingleRotatingInstance::new);
//    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onModelBake(ModelBakeEvent event) {
        CoTWheelTileEntity.validBlocks.
                forEach(block -> CreateClient.getCustomBlockModels().register(block.delegate, BracketedKineticBlockModel::new));
    }
}
