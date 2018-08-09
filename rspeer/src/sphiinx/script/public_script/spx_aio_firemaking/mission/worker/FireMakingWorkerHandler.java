package sphiinx.script.public_script.spx_aio_firemaking.mission.worker;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.pathfinding.region.util.CollisionFlags;
import org.rspeer.runetek.api.movement.pathfinding.region.util.Direction;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Scene;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl.GetLogs;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl.GetTinderBox;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl.LightFire;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl.WalkToLane;

import java.util.LinkedList;

public class FireMakingWorkerHandler extends WorkerHandler<FireMakingMission> {

    private final FireMakingWorker GET_LOGS;
    private final FireMakingWorker GET_TINDER_BOX;
    private final FireMakingWorker WALK_TO_LANE;
    private final FireMakingWorker LIGHT_FIRE;

    public FireMakingWorkerHandler(FireMakingMission mission) {
        super(mission);
        GET_LOGS = new GetLogs(mission);
        GET_TINDER_BOX = new GetTinderBox(mission);
        WALK_TO_LANE = new WalkToLane(mission);
        LIGHT_FIRE = new LightFire(mission);
    }

    @Override
    public Worker<FireMakingMission> decide() {
        if (!Game.isLoggedIn())
            return null;

        if (Vars.get().log_type == null)
            Vars.get().log_type = mission.getBestUsableLog(true, false);

        if (mission.search_position == null)
            mission.search_position = Players.getLocal().getPosition();

        if (Inventory.getCount(GetTinderBox.TINDERBOX) <= 0)
            return GET_TINDER_BOX;

        if (Inventory.getCount(Vars.get().log_type.getName()) <= 0)
            return GET_LOGS;

        if (mission.current_lane_start_position == null || mission.is_stuck_in_lane) {
            mission.current_lane_start_position = getBestPosition(mission.search_position, mission.search_distance, mission.lane_length, mission.minimum_score, mission.ignored_tiles);
            mission.is_stuck_in_lane = false;
            return WALK_TO_LANE;
        }

        if (Players.getLocal().getY() != mission.current_lane_start_position.getY() || Players.getLocal().getX() <= mission.current_lane_start_position.getX() - mission.lane_length) {
            mission.current_lane_start_position = getBestPosition(mission.search_position, mission.search_distance, mission.lane_length, mission.minimum_score, mission.ignored_tiles);
            return WALK_TO_LANE;
        }

        return LIGHT_FIRE;
    }

    /**
     * Checks whether a fire can be lit in the specified position. If the tile is not walkable or the tile to the west
     * is not walkable it will return false.
     *
     * @param position The position to check.
     * @return True if a fire can be lit in the specified position; false otherwise.
     */
    private boolean canLightFire(Position position) {
        final SceneObject POSITION_OBJECT = SceneObjects.getFirstAt(position);
        if (!Movement.isWalkable(position, false))
            return false;

        if (!CollisionFlags.checkWalkable(Direction.WEST, Scene.getCollisionFlag(position), Scene.getCollisionFlag(position), false))
            return false;

        return POSITION_OBJECT == null || !POSITION_OBJECT.getName().contains("Fire");
    }

    /**
     * Gets a score of the lane starting from the specified position with the length. The higher the score, the worse
     * the lane is. If the lane contains a position in which a fire cannot be lit, the score is increased by 1.
     *
     * @param position The position to start.
     * @param length   The length of the lane.
     * @return The score of the lane.
     */
    private int getLaneScore(Position position, int length) {
        final Position END_POSITION = new Position(position.getX() - length, position.getY(), position.getFloorLevel());
        int score = 0;
        for (int i = 0; i < length; i++) {
            final Position POSITION = new Position(END_POSITION.getX() + i, END_POSITION.getY(), END_POSITION.getFloorLevel());
            if (!canLightFire(POSITION))
                score++;
        }

        return score;
    }

    /**
     * Gets the best lane start position with the distance to check, the length of the lane, and the minimum score not
     * including the ignored tiles.
     *
     * @param position      The initial position to search from.
     * @param distance      The distance to search.
     * @param length        The length of the lane.
     * @param score         The minimum score to search.
     * @param ignored_tiles The tiles to ignore.
     * @return The best lane start position.
     */
    private Position getBestPosition(Position position, int distance, int length, int score, LinkedList<Position> ignored_tiles) {
        final Area SURROUNDING_AREA = Area.surrounding(position, distance);
        return SURROUNDING_AREA.getTiles().stream()
                .sorted((o1, o2) -> (int) (o1.distance(position) - o2.distance(position)))
                .filter(a -> canLightFire(a) && getLaneScore(a, length) <= score && !ignored_tiles.contains(a))
                .findFirst()
                .orElse(null);
    }
}

