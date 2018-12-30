package sphiinx.script.private_script.the_bull.house_planking.mission;

import org.rspeer.runetek.api.component.Dialog;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.private_script.the_bull.house_planking.mission.worker.HousePlankingWorkerHandler;

public class HousePlankingMission extends Mission {


    public boolean can_end;
    private final HousePlankingWorkerHandler worker_handler = new HousePlankingWorkerHandler(this);

    @Override
    public String getMissionName() {
        return "[P-SPX] House Planking";
    }

    @Override
    public String getWorkerName() {
        final Worker c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        final Worker c = worker_handler.getCurrent();
        return c == null ? "Waiting for worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return can_end;
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public int execute() {
        // [TODO - 2018-11-28]: Temporary until they fix the method to continue the chat box.
        if (Dialog.canContinue())
            Dialog.processContinue();

        worker_handler.work();
        return 150;
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

