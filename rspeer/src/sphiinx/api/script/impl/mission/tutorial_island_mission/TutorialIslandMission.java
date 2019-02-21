package sphiinx.api.script.impl.mission.tutorial_island_mission;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.GameAccount;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.tutorial_island_mission.data.args.Args;
import sphiinx.api.script.impl.mission.tutorial_island_mission.worker.TutorialIslandWorkerHandler;

public class TutorialIslandMission extends Mission {

    public static final int TUTORIAL_ISLAND_VARP = 281;

    private final Args args;
    private final String username;
    private final String password;
    private final TutorialIslandWorkerHandler worker_handler;
    private boolean should_stop;


    public TutorialIslandMission(SPXScript script, Args args, String username, String password) {
        super(script);
        this.args = args;
        this.username = username;
        this.password = password;
        worker_handler = new TutorialIslandWorkerHandler(this);
    }


    @Override
    public String getMissionName() {
        return "Tutorial Island";
    }

    @Override
    public String getWorkerName() {
        Worker c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = worker_handler.getCurrent();
        return c == null ? "Loading next available worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean shouldEnd() {
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

    public void setShouldStop(boolean should_stop) {
        this.should_stop = should_stop;
    }

    public Args getArgs() {
        return args;
    }

    public String getUsername() {
        return username;
    }
}

