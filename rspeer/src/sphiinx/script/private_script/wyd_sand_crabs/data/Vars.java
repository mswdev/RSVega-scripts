package sphiinx.script.private_script.wyd_sand_crabs.data;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.Predicate;

public class Vars {

    public static Vars vars;
    public Position FIGHT_TILE;
    public Predicate<Player> PLAYER = a -> a.getPosition().distance(Vars.get().FIGHT_TILE) <= 2 && !a.getName().equals(Players.getLocal().getName());
    public boolean needs_reset;
    public boolean needs_hop;
    public boolean needs_auto_retaliate_fix = true;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}

