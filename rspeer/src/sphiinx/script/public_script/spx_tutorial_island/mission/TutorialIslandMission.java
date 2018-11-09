package sphiinx.script.public_script.spx_tutorial_island.mission;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.GameAccount;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.GoalList;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.impl.InfiniteGoal;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXScript;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TutorialIslandWorkerHandler;

public class TutorialIslandMission extends Mission {

    public static final int TUTORIAL_ISLAND_VARP = 281;
    private final TutorialIslandWorkerHandler worker_handler;
    private final SPXScript script;
    private final String username;
    private final String password;
    private boolean should_stop;

    public TutorialIslandMission(SPXScript script, String username, String password) {
        this.script = script;
        this.username = username;
        this.password = password;
        worker_handler = new TutorialIslandWorkerHandler(this);
    }


    @Override
    public String getMissionName() {
        return "[SPX] Tutorial Island";
    }

    @Override
    public String getWorkerName() {
        Worker<TutorialIslandMission> c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<TutorialIslandMission> c = worker_handler.getCurrent();
        return c == null ? "Loading next available worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return should_stop;
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public int execute() {
        if (!Game.isLoggedIn())
            return 100;

        // [TODO - 2018-11-01]: Temporary until the client is forced fixed mode.
        if (Game.getClientPreferences().getResizable() == 2) {
            final InterfaceComponent comp = Interfaces.getComponent(261, 33);
            if (comp != null)
                comp.interact(ActionOpcodes.INTERFACE_ACTION, 1);
        }

        // [TODO - 2018-11-05]: Temporary until the rspeer continue dialog is fixed.
        if (Dialog.canContinue())
            Game.getClient().fireScriptEvent(299, 1, 1);

        // [TODO - 2018-11-05]: Temporary until the movement api supports run energy.
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 10)
            Movement.toggleRun(true);

        worker_handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {
        script.setAccount(new GameAccount(username, password));
    }

    @Override
    public void onMissionEnd() {
    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }

    public SPXScript getScript() {
        return script;
    }

    public void setShouldStop(boolean should_stop) {
        this.should_stop = should_stop;
    }
}

