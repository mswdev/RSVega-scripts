package sphiinx.script.public_script.spx_tutorial_island.mission.impl;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Login;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.script.GameAccount;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.Map;

public class TutorialComplete extends TI_Worker {

    public TutorialComplete(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Login.getState() == Login.STATE_ENTER_CREDENTIALS) {
            Login.getProperties().clear();
            final Map.Entry<String, String> ENTRY = mission.ITERATOR.next();
            Log.fine("[ACCOUNT LOADED]: [Username: " + ENTRY.getKey() + " | Password: " + ENTRY.getValue() + "]");
            mission.SPX_SCRIPT.setAccount(new GameAccount(ENTRY.getKey(), ENTRY.getValue()));
        } else {
            if (Game.logout())
                Time.sleepUntil(() -> Login.getState() == Login.STATE_ENTER_CREDENTIALS, 1500);
        }
    }

    @Override
    public String toString() {
        return "[GENERAL]: Completed tutorial island";
    }
}

