package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FM_Mission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FM_Worker;

import java.util.ArrayList;

public class WalkToLane extends FM_Worker {

    public WalkToLane(FM_Mission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (needsLane(mission.current_lane_position, Vars.get().lane_length)) {
            mission.current_lane_position = getLane(Vars.get().lane_start_positions, Vars.get().lane_length);
        } else {
            Movement.walkTo(mission.current_lane_position);
        }
    }

    private boolean needsLane(Position lane_start, int length) {
        if (mission.current_lane_position == null)
            return true;

        final Position TILE_CHECK = new Position(Players.getLocal().getX() - 1, lane_start.getY(), lane_start.getFloorLevel());
        final Position LANE_END = new Position(lane_start.getX() - length, lane_start.getY(), lane_start.getFloorLevel());
        return laneContainsFire(TILE_CHECK, (int) LANE_END.distance());
    }

    private boolean laneContainsFire(Position lane_start, int length) {
        for (int i = 0; i < length; i++) {
            final Position TILE_CHECK = new Position(lane_start.getX() - i, lane_start.getY(), lane_start.getFloorLevel());
            final SceneObject FIRE_POSITION = SceneObjects.getFirstAt(TILE_CHECK);
            if (FIRE_POSITION != null && FIRE_POSITION.getName().equals("Fire"))
                return true;
        }

        return false;
    }

    private Position getLane(ArrayList<Position> lanes, int length) {
        for (Position lane_start : lanes) {
            if (laneContainsFire(lane_start, length))
                continue;

            return lane_start;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Waiting to lane";
    }
}

