package org.api.script.impl.mission.firemaking_mission.worker;

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
import org.api.game.skills.firemaking.FiremakingUtil;
import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.firemaking_mission.FireMakingMission;
import org.api.script.impl.mission.firemaking_mission.worker.impl.LightFire;
import org.api.script.impl.mission.firemaking_mission.worker.impl.WalkToLane;
import org.api.script.impl.mission.firemaking_mission.worker.impl.WithdrawLogs;
import org.api.script.impl.mission.firemaking_mission.worker.impl.WithdrawTinderBox;
import org.script.public_script.spx_aio_firemaking.Main;

import java.util.LinkedList;

public class FireMakingWorkerHandler extends WorkerHandler {

    private final WithdrawLogs get_logs;
    private final WithdrawTinderBox get_tinder_box;
    private final WalkToLane walk_to_lane;
    private final LightFire light_fire;
    private final FireMakingMission mission;

    public FireMakingWorkerHandler(FireMakingMission mission) {
        this.mission = mission;
        get_logs = new WithdrawLogs(mission);
        get_tinder_box = new WithdrawTinderBox();
        walk_to_lane = new WalkToLane(mission);
        light_fire = new LightFire(mission);
    }

    @Override
    public Worker decide() {
        if (!Game.isLoggedIn())
            return null;

        if (Main.ARGS.log_type == null)
            Main.ARGS.log_type = FiremakingUtil.getAppropriateOwnedLogs();

        if (mission.getSearchPosition() == null)
            mission.setSearchPosition(Players.getLocal().getPosition());

        if (Inventory.getCount(WithdrawTinderBox.TINDERBOX) <= 0)
            return get_tinder_box;

        if (Inventory.getCount(Main.ARGS.log_type.getName()) <= 0)
            return get_logs;

        if (mission.getCurrentLaneStartPosition() == null || mission.isStuckInLane()) {
            mission.setCurrentLaneStartPosition(getBestPosition(mission.getSearchPosition(), FireMakingMission.SEARCH_DISTANCE, FireMakingMission.LANE_LENGTH, FireMakingMission.MINIMUM_SCORE, mission.getIgnoredTiles()));
            mission.setIsStuckInLane(false);
            return walk_to_lane;
        }

        if (Players.getLocal().getY() != mission.getCurrentLaneStartPosition().getY() /*|| Players.getLocal().getX() <= mission.getCurrentLaneStartPosition().getX() - FireMakingMission.LANE_LENGTH*/) {
            mission.setCurrentLaneStartPosition(getBestPosition(mission.getSearchPosition(), FireMakingMission.SEARCH_DISTANCE, FireMakingMission.LANE_LENGTH, FireMakingMission.MINIMUM_SCORE, mission.getIgnoredTiles()));
            return walk_to_lane;
        }

        return light_fire;
    }

    /**
     * Checks whether a fire can be lit in the specified position. If the tile is not walkable or the tile to the west
     * is not walkable it will return false.
     *
     * @param position The position to check.
     * @return True if a fire can be lit in the specified position; false otherwise.
     */
    private boolean canLightFire(Position position) {
        final SceneObject position_object = SceneObjects.getFirstAt(position);
        if (!Movement.isWalkable(position, false))
            return false;

        if (!CollisionFlags.checkWalkable(Direction.WEST, Scene.getCollisionFlag(position), Scene.getCollisionFlag(position), false))
            return false;

        return position_object == null || !position_object.getName().contains("Fire");
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
        final Position end_position = new Position(position.getX() - length, position.getY(), position.getFloorLevel());
        int score = 0;
        for (int i = 0; i < length; i++) {
            final Position pos = new Position(end_position.getX() + i, end_position.getY(), end_position.getFloorLevel());
            if (!canLightFire(pos))
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
        final Area surrounding_area = Area.surrounding(position, distance);
        return surrounding_area.getTiles().stream()
                .sorted((o1, o2) -> (int) (o1.distance(position) - o2.distance(position)))
                .filter(a -> canLightFire(a) && getLaneScore(a, length) <= score && !ignored_tiles.contains(a))
                .findFirst()
                .orElse(null);
    }
}

