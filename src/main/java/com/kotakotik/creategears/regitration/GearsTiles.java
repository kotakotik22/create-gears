package com.kotakotik.creategears.regitration;

import com.kotakotik.creategears.Gears;
import com.kotakotik.creategears.util.Registration;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.simibubi.create.foundation.render.backend.instancing.IRendererFactory;
import com.simibubi.create.foundation.render.backend.instancing.InstancedTileRenderRegistry;
import com.simibubi.create.repack.registrate.Registrate;
import com.simibubi.create.repack.registrate.util.OneTimeEventReceiver;
import com.simibubi.create.repack.registrate.util.entry.TileEntityEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid= Gears.modid, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class GearsTiles extends Registration {
    public static TileEntityEntry<SimpleKineticTileEntity> GEAR;

    public GearsTiles(Registrate r) {
        super(r);
    }

    @Override
    public void register() {
        GEAR = r.tileEntity("gear", SimpleKineticTileEntity::new)
                .validBlock(GearsBlocks.GEAR)
                .register();
    }
}
