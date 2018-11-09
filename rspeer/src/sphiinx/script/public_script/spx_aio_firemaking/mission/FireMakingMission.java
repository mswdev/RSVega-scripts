package sphiinx.script.public_script.spx_aio_firemaking.mission;

import org.rspeer.runetek.api.movement.position.Position;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.GoalList;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.impl.InfiniteGoal;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FireMakingWorkerHandler;

import java.util.LinkedList;

public class FireMakingMission extends Mission {

    public static final int SEARCH_DISTANCE = 20;
    public static final int LANE_LENGTH = 27;
    public static final int MINIMUM_SCORE = 0;
    private final FireMakingWorkerHandler worker_handler;
    private final LinkedList<Position> ignored_tiles;
    private Position current_lane_start_position;
    private Position search_position;
    private boolean is_stuck_in_lane;
    private boolean should_stop;

    public FireMakingMission() {
        worker_handler = new FireMakingWorkerHandler(this);
        ignored_tiles = new LinkedList<>();
    }

    @Override
    public String getMissionName() {
        return "[SPX] AIO Firemaking";
    }

    @Override
    public String getWorkerName() {
        Worker<FireMakingMission> c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<FireMakingMission> c = worker_handler.getCurrent();
        return c == null ? "Waiting for worker" : c.toString();
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

    public Position getCurrentLaneStartPosition() {
        return current_lane_start_position;
    }

    public void setCurrentLaneStartPosition(Position current_lane_start_position) {
        this.current_lane_start_position = current_lane_start_position;
    }

    public boolean isStuckInLane() {
        return is_stuck_in_lane;
    }

    public void setIsStuckInLane(boolean is_stuck_in_lane) {
        this.is_stuck_in_lane = is_stuck_in_lane;
    }

    public void setShouldStop(boolean should_stop) {
        this.should_stop = should_stop;
    }

    public LinkedList<Position> getIgnoredTiles() {
        return ignored_tiles;
    }

    public Position getSearchPosition() {
        return search_position;
    }

    public void setSearchPosition(Position search_position) {
        this.search_position = search_position;
    }
}

