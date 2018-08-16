package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.defence.level_to_30;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

public class EquipBronzeSword extends AirOrbLevelWorker {


    public EquipBronzeSword(AirOrbLevelMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        final Item ITEM = Inventory.getFirst(GetBronzeSword.BRONZE_SWORD);
        if (ITEM == null)
            return;

        if (ITEM.click())
            Time.sleepUntil(() -> Equipment.contains("Bronze sword"), 1500);
    }

    @Override
    public String toString() {
        return "Equipping Bronze sword";
    }
}

