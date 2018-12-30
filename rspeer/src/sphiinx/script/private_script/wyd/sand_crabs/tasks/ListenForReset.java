package sphiinx.script.private_script.wyd.sand_crabs.tasks;

import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import sphiinx.script.private_script.wyd.sand_crabs.data.Vars;

public class ListenForReset extends Task {

    private long START_TIME;

    @Override
    public boolean validate() {
        if (!Vars.get().needs_reset && !Players.getLocal().isMoving() && Players.getLocal().getPosition().equals(Vars.get().FIGHT_TILE) && Players.getLocal().getTargetIndex() == -1 && !Players.getLocal().isHealthBarVisible())
            return true;

        START_TIME = System.currentTimeMillis();
        return false;
    }

    @Override
    public int execute() {
        if (START_TIME <= 0)
            START_TIME = System.currentTimeMillis();

        if ((System.currentTimeMillis() - START_TIME) / 1000 >= 8) {
            Vars.get().needs_reset = true;
            START_TIME = 0;
        }

        return 100;
    }

    @Override
    public String toString() {
        long time = (System.currentTimeMillis() - START_TIME) / 1000;
        if ((System.currentTimeMillis() - START_TIME) / 1000 > 5)
            time = 5;

        return "Listening for reset; reset time: " + time;
    }
}

