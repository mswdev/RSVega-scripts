package sphiinx.script.public_script.spx_aio_walking.data;


public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}

