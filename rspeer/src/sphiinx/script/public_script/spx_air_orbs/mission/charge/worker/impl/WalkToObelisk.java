package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.EquipGlory;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.GetGlory;

import java.util.Arrays;

public class WalkToObelisk extends AirOrbChargeWorker {

    public static final Position TRAP_DOOR_POSITION = new Position(3097, 3468, 0);
    private final Position LADDER_POSITION = new Position(3088, 9971, 0);
    private final EquipGlory EQUIP_GLORY;
    private final TeleportToEdgeville TELEPORT_TO_EDGEVILLE;

    public WalkToObelisk(AirOrbChargeMission mission) {
        super(mission);
        EQUIP_GLORY = new EquipGlory(mission);
        TELEPORT_TO_EDGEVILLE = new TeleportToEdgeville(mission);
    }

    @Override
    public void work() {
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() >= 15)
            Movement.toggleRun(true);

        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Players.getLocal().isMoving())
            return;

        if (Players.getLocal().getPosition().getY() < 9000) {
            final SceneObject TRAP_DOOR = Arrays.stream(SceneObjects.getAt(TRAP_DOOR_POSITION)).filter(a -> a.getName().equals("Trapdoor")).findFirst().orElse(null);
            if (TRAP_DOOR != null) {
                if (TRAP_DOOR.distance() > 25)
                    Movement.walkTo(TRAP_DOOR);

                if (TRAP_DOOR.click())
                    Time.sleepUntil(() -> Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1, 1500);
            } else {
                if (Inventory.contains(GetGlory.GLORY)) {
                    EQUIP_GLORY.work();
                } else if (Equipment.isOccupied(EquipmentSlot.NECK)) {
                    TELEPORT_TO_EDGEVILLE.work();
                } else {
                    if (Movement.walkTo(TRAP_DOOR_POSITION))
                        Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
                }
            }
        } else {
            final SceneObject LADDER = SceneObjects.getFirstAt(LADDER_POSITION);
            if (LADDER != null) {
                if (LADDER.click())
                    Time.sleepUntil(() -> Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1, 1500);
            } else {
                if (Movement.walkTo(LADDER_POSITION))
                    Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
            }
        }
    }

    @Override
    public String toString() {
        return "Walking to obelisk";
    }
}

