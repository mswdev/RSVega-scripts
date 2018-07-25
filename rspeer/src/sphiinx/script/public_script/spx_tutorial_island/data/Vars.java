package sphiinx.script.public_script.spx_tutorial_island.data;

public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public boolean at_start_hide_roofs = true;
    public boolean at_start_turn_off_audio = true;
    public boolean at_start_turn_up_brightness = true;
    public boolean at_start_zoom_out = true;
    public boolean at_end_drop_items;
    public boolean at_end_bank_items;
    public boolean at_end_get_iron_man_items;
    public boolean at_end_logout;
    public boolean at_end_stay_logged_in;
    public boolean at_end_walk_to_location;

}

