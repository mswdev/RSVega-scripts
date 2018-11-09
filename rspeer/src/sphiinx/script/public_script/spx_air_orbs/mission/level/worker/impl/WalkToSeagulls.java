package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

public class WalkToSeagulls extends AirOrbLevelWorker {

    private final Position seagulls_position = new Position(3028, 3235, 0);

    public WalkToSeagulls(AirOrbLevelMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Movement.walkTo(seagulls_position))
            Time.sleepUntil(() -> seagulls_position.distance() <= 10, 1500);
    }

    @Override
    public String toString() {
        return "Walking to Seagulls";
    }
}

