package sphiinx.script.public_script.spx_tutorial_island.data;

import org.rspeer.runetek.api.movement.position.Position;

import java.util.ArrayList;

public class Vars {

    public static Vars vars;
    public boolean at_start_hide_roofs;
    public boolean at_start_turn_off_audio;
    public boolean at_start_turn_up_brightness;
    public boolean at_start_zoom_out;
    public boolean at_end_drop_items;
    public boolean at_end_bank_items;
    public boolean at_end_get_iron_man_items;
    public boolean at_end_logout;
    public boolean at_end_stay_logged_in;
    public boolean at_end_walk_to_location;
    public boolean load_accounts_from_list;
    public Position at_end_walk_position;
    //TODO Remove this soon, only using it to show accs that completed tutorial island.
    public ArrayList<String> TEMP = new ArrayList<>();

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}

