package com.kotakotik.creategears.util;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.Registrate;

public abstract class Registration {
    public final CreateRegistrate registrate;
    public final CreateRegistrate r;

    public Registration(CreateRegistrate r) {
        this.registrate = r;
        this.r = registrate;
    }

    public abstract void register();
}
