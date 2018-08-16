package sphiinx.script.public_script.spx_aio_walking.data;

public class Vars {
    private static Vars instance = new Vars();

    public static Vars get() {
        return instance;
    }

    private Vars() {
    }

    public Location location;
}
