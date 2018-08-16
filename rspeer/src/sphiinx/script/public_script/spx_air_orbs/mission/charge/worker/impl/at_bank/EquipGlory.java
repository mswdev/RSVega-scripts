package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

public class EquipGlory extends AirOrbChargeWorker {


    public EquipGlory(AirOrbChargeMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        final Item ITEM = Inventory.getFirst(GetGlory.GLORY);
        if (ITEM == null)
            return;

        if (ITEM.click())
            Time.sleepUntil(() -> !Inventory.contains(GetGlory.GLORY), 1500);
    }

    @Override
    public String toString() {
        return "Equipping Amulet of glory";
    }
}

