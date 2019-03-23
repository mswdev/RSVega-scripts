package org.api.script.impl.mission.tutorial_island_mission;

import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.goal.impl.InfiniteGoal;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.data.args.Args;
import org.api.script.impl.mission.tutorial_island_mission.worker.TutorialIslandWorkerHandler;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.script.GameAccount;

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

        //Temporary until rspeer forces fixed mode upon login.
        if (Game.getClientPreferences().getResizable() == 2) {
            final InterfaceComponent fixed_mode_component = Interfaces.getFirst(a -> a.isVisible() && a.containsAction("Fixed mode"));
            if (fixed_mode_component != null)
                if (fixed_mode_component.click())
                    Time.sleepUntil(() -> Game.getClientPreferences().getResizable() == 1, 2500);
        }

        //Temporary until the rspeer continue dialog is fixed.
        if (Dialog.canContinue()) {
            Dialog.processContinue();
            Game.getClient().fireScriptEvent(299, 1, 1);
        }


        worker_handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {
        script.setAccount(new GameAccount(username, password));
    }

    public void setShouldEnd(boolean should_stop) {
        this.should_stop = should_stop;
    }

    public Args getArgs() {
        return args;
    }

    public String getUsername() {
        return username;
    }
}

