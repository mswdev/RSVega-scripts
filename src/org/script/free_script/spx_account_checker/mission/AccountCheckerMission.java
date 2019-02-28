package org.script.free_script.spx_account_checker.mission;

import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.script.free_script.spx_account_checker.Main;
import org.script.free_script.spx_account_checker.mission.worker.AccountCheckerWorkerHandler;

public class AccountCheckerMission extends Mission {

    public final Main main;
    private final AccountCheckerWorkerHandler handler = new AccountCheckerWorkerHandler(this);

    public AccountCheckerMission(SPXScript script, Main main) {
        super(script);
        this.main = main;
    }

    @Override
    public String getMissionName() {
        return "Account Checker";
    }

    @Override
    public String getWorkerName() {
        Worker c = handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = handler.getCurrent();
        return c == null ? "Waiting for worker." : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean shouldEnd() {
        return false;
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public int execute() {
        handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {
    }

    @Override
    public void onMissionEnd() {

    }
}

