package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.EquipGlory;

public class TeleportToEdgeville extends AirOrbChargeWorker {

    private final EquipGlory equip_glory;

    public TeleportToEdgeville(AirOrbChargeMission mission) {
        super(mission);
        equip_glory = new EquipGlory(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Equipment.isOccupied(EquipmentSlot.NECK)) {
            if (EquipmentSlot.NECK.interact(ActionOpcodes.INTERFACE_ACTION, 1))
                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
        } else {
            equip_glory.work();
        }
    }

    @Override
    public String toString() {
        return "Teleporting to Edgeville";
    }
}

