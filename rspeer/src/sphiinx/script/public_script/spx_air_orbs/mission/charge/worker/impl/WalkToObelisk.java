package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.Wilderness;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.EquipGlory;

import java.util.Arrays;

public class WalkToObelisk extends AirOrbChargeWorker {

    public final Position trap_door_position = new Position(3097, 3468, 0);
    private final Position ladder_position = new Position(3088, 9971, 0);
    private final EquipGlory equip_glory;
    private final TeleportToEdgeville teleport_to_edgeville;

    public WalkToObelisk(AirOrbChargeMission mission) {
        super(mission);
        equip_glory = new EquipGlory(mission);
        teleport_to_edgeville = new TeleportToEdgeville(mission);
    }

    @Override
    public void work() {
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() >= 15)
            Movement.toggleRun(true);

        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Players.getLocal().getPosition().getY() < 9000) {
            final SceneObject trap_door = Arrays.stream(SceneObjects.getAt(trap_door_position)).filter(a -> a.getName().equals("Trapdoor")).findFirst().orElse(null);
            if (trap_door == null)
                return;

            if (trap_door.distance() > 25)
                Movement.walkTo(trap_door);

            if (trap_door.click())
                Time.sleepUntil(() -> Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1, 1500);
        } else {
            final SceneObject ladder = SceneObjects.getFirstAt(ladder_position);
            if (ladder != null) {
                if (ladder.click())
                    Time.sleepUntil(() -> Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1, 1500);
            } else {
                if (Wilderness.hasWildernessWarning())
                    if (Wilderness.enterWilderness())
                        Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);

                if (Movement.walkTo(ladder_position))
                    Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
            }
        }
    }

    @Override
    public String toString() {
        return "Walking to Obelisk of air";
    }
}

