package sphiinx.script.private_script.wyd_sand_crabs.tasks;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import sphiinx.script.private_script.wyd_sand_crabs.data.Vars;

public class WalkToFightTile extends Task {

    @Override
    public boolean validate() {
        if (Vars.get().needs_reset || Vars.get().needs_hop)
            return false;

        if (Vars.get().FIGHT_TILE == null)
            return true;

        final Player CRASH_PLAYER = Players.getNearest(Vars.get().PLAYER);
        return CRASH_PLAYER == null && !Players.getLocal().getPosition().equals(Vars.get().FIGHT_TILE);
    }

    @Override
    public int execute() {
        if (Vars.get().FIGHT_TILE == null) {
            Vars.get().FIGHT_TILE = Players.getLocal().getPosition();
        } else {
            if (Movement.walkTo(Vars.get().FIGHT_TILE))
                Time.sleepUntil(() -> Vars.get().FIGHT_TILE == Players.getLocal().getPosition(), 1500);
        }

        return 100;
    }

    @Override
    public String toString() {
        return "Walking to fight tile";
    }
}

