package sphiinx.script.private_script.zerker.revenants.mission;

import org.rspeer.runetek.api.movement.Movement;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.mission.worker.RevenantWorkerHandler;

public class RevenantMission extends Mission {

    private final RevenantWorkerHandler worker_handler = new RevenantWorkerHandler(this);
    public boolean can_end;

    @Override
    public String getMissionName() {
        return "[P-SPX] Revenants";
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
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 10)
            Movement.toggleRun(true);

        worker_handler.work();
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

