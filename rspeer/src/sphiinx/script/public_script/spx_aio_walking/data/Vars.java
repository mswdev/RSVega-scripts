package sphiinx.script.public_script.spx_aio_walking.data;


import org.rspeer.runetek.adapter.Positionable;


public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public Positionable walk_location;

}

