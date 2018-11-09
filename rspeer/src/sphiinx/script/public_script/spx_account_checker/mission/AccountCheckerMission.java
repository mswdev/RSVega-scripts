package sphiinx.script.public_script.spx_account_checker.mission;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.GoalList;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_account_checker.Main;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorkerHandler;

public class AccountCheckerMission extends Mission {

    private final AccountCheckerWorkerHandler handler = new AccountCheckerWorkerHandler(this);
    public final Main main;

    public AccountCheckerMission(Main main) {
        this.main = main;
    }

    @Override
    public String getMissionName() {
        return "Account Checker";
    }

    @Override
    public String getWorkerName() {
        Worker<AccountCheckerMission> c = handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<AccountCheckerMission> c = handler.getCurrent();
        return c == null ? "Waiting for worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
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

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }
}

