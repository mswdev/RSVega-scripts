package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

public class EquipStaffOfAir extends AirOrbLevelWorker {


    public EquipStaffOfAir(AirOrbLevelMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        final Item ITEM = Inventory.getFirst(GetStaffOfAir.STAFF_OF_AIR);
        if (ITEM == null)
            return;

        if (ITEM.click())
            Time.sleepUntil(() -> Equipment.contains("Staff of air"), 1500);
    }

    @Override
    public String toString() {
        return "Equipping Staff of air";
    }
}

