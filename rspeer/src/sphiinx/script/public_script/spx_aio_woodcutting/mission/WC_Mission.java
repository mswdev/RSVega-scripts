package sphiinx.script.public_script.spx_aio_woodcutting.mission;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.GoalList;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.impl.InfiniteGoal;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_aio_woodcutting.mission.worker.WCWorkerManager;

public class WC_Mission extends Mission {

    public final WCWorkerManager MANAGER = new WCWorkerManager(this);

    @Override
    public boolean canEnd() {
        return goals.hasReachedGoals();
    }

    @Override
    public String getMissionName() {
        return "SPX AIO Woodcutter";
    }

    @Override
    public String getWorkerName() {
        return null;
    }

    @Override
    public String getWorkerString() {
        return null;
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return false;
    }

    @Override
    public String getEndMessage() {
        return "Ended";
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public String[] getMissionPaint() {
        return null;
    }

    @Override
    public void resetPaint() {

    }

    @Override
    public int execute() {
        MANAGER.work();
        return 600;
    }

    @Override
    public void onMissionStart() {

    }

    @Override
    public void onMissionEnd() {

    }
}

