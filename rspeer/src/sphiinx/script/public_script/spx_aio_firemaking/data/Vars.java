package sphiinx.script.public_script.spx_aio_firemaking.data;


import org.rspeer.runetek.api.movement.position.Position;

import java.util.ArrayList;

public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public boolean progressive = true;
    public LogType log_type;
    public int lane_length = 20;
    public ArrayList<Position> lane_start_positions = new ArrayList<>();

}

