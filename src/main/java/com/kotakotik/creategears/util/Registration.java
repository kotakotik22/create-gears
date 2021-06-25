package com.kotakotik.creategears.util;

import com.simibubi.create.repack.registrate.Registrate;

public abstract class Registration {
    public final Registrate registrate;
    public final Registrate r;

    public Registration(Registrate r) {
        this.registrate = r;
        this.r = registrate;
    }

    public abstract void register();
}
