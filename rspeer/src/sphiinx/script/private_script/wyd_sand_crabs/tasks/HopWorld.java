package sphiinx.script.private_script.wyd_sand_crabs.tasks;

import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import sphiinx.script.private_script.wyd_sand_crabs.data.Vars;

public class HopWorld extends Task {

    @Override
    public boolean validate() {
        return Vars.get().needs_hop;
    }

    @Override
    public int execute() {
        if (Players.getLocal().getTargetIndex() == -1 && !Players.getLocal().isHealthBarVisible()) {
            final int WORLD_CACHE = Worlds.getCurrent();
            if (WorldHopper.randomHopInP2p())
                if (Time.sleepUntil(() -> Worlds.getCurrent() != WORLD_CACHE, 6500)) {
                    Vars.get().needs_auto_retaliate_fix = true;
                    Vars.get().needs_hop = false;
                }
        } else {
            if (Movement.walkTo(Reset.getSafePosition(Vars.get().FIGHT_TILE, 30)))
                Time.sleepUntil(() -> Players.getLocal().getTargetIndex() == -1 && !Players.getLocal().isHealthBarVisible(), 1500);
        }

        return 100;
    }

    @Override
    public String toString() {
        return "Fight position crashed; hopping worlds";
    }
}

