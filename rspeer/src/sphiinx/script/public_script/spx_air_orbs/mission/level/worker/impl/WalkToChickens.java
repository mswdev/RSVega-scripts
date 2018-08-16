package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

public class WalkToChickens extends AirOrbLevelWorker {

    private final Position CHICKENS_POSITION = new Position(3233, 3293, 0);

    public WalkToChickens(AirOrbLevelMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Movement.walkTo(CHICKENS_POSITION))
            Time.sleepUntil(() -> CHICKENS_POSITION.distance() <= 10, 1500);
    }

    @Override
    public String toString() {
        return "Walking to chickens";
    }
}

