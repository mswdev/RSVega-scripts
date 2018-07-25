package sphiinx.script.public_script.spx_aio_walking.mission;

import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.ui.Log;
import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.goal.impl.InfiniteGoal;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.W_WorkerManager;

public class WalkMission extends Mission {

    public Positionable WALK_POSITION = new Position(3165, 3471, 0);

    public final W_WorkerManager MANAGER = new W_WorkerManager(this);

    @Override
    public boolean canEnd() {
        return goals.hasReachedGoals();
    }

    @Override
    public String getMissionName() {
        return "SPX AIO Walking";
    }

    @Override
    public String getWorkerName() {
        Worker<WalkMission> c = MANAGER.getCurrent();
        return c == null ? "Starting up..." : c.toString();
    }

    @Override
    public String getWorkerString() {
        return null;
    }

    @Override
    public String getEndMessage() {
        return "Reached destination!";
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
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
        Log.info("Mission started");
    }

    @Override
    public void onMissionEnd() {

    }
}

