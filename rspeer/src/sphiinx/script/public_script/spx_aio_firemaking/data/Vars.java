package sphiinx.script.public_script.spx_aio_firemaking.data;


public class Vars {

    public static Vars vars;

    public static void reset() {
        vars = null;
    }

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public boolean is_progressive = true;
    public LogType log_type;

}

