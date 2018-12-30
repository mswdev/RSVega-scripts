package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_edgeville;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.game.Wilderness;

public class TeleportToEdgeville extends Worker {

    private static final Position TELEPORT_POSITION = new Position(3252, 10143, 0);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!Equipment.isOccupied(EquipmentSlot.NECK))
            return;

        if (Wilderness.getWildernessLevel() > 30) {
            if (Movement.walkTo(TELEPORT_POSITION))
                Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
        } else {
            if (EquipmentSlot.NECK.interact(ActionOpcodes.INTERFACE_ACTION, 1))
                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
        }
    }

    @Override
    public String toString() {
        return "Teleporting to Edgeville.";
    }
}

