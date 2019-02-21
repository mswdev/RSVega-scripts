package sphiinx.api.script.impl.mission.fishing_mission;

import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.fishing_mission.worker.FishingWorkerHandler;

public class Fishingmission extends Mission {

    private final FishingWorkerHandler worker_handler;
    private boolean should_stop;

    public Fishingmission(SPXScript script) {
        super(script);
        worker_handler = new FishingWorkerHandler();
    }

    @Override
    public String getMissionName() {
        return "AIO Fishing";
    }

    @Override
    public String getWorkerName() {
        Worker c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = worker_handler.getCurrent();
        return c == null ? "Waiting for worker." : c.toString();
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
        worker_handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {

    }

    @Override
    public void onMissionEnd() {

    }
}
