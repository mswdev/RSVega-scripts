package sphiinx.script.private_script.wyd_sand_crabs.tasks;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import sphiinx.script.private_script.wyd_sand_crabs.data.Vars;

public class ListenForCrash extends Task {

    private long START_TIME;

    @Override
    public boolean validate() {
        final Player CRASH_PLAYER = Players.getNearest(Vars.get().PLAYER);
        if (CRASH_PLAYER == null)
            START_TIME = System.currentTimeMillis();

        return !Vars.get().needs_hop && CRASH_PLAYER != null;
    }

    @Override
    public int execute() {
        if (START_TIME <= 0)
            START_TIME = System.currentTimeMillis();

        if ((System.currentTimeMillis() - START_TIME) / 1000 >= 2) {
            Vars.get().needs_hop = true;
            START_TIME = 0;
        }

        return 150;
    }

    @Override
    public String toString() {
        if (START_TIME > 0)
            return "Listening for crash; crash time: " + (System.currentTimeMillis() - START_TIME) / 1000;

        return null;
    }
}

