package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

public class DrinkStamina extends AirOrbChargeWorker {


    public DrinkStamina(AirOrbChargeMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        final Item ITEM = Inventory.getFirst(GetStamina.STAMINA);
        if (ITEM == null)
            return;

        if (ITEM.click()) {
            //todo Check stamina varp
        }
    }

    @Override
    public String toString() {
        return "Drinking Stamina Potion";
    }
}

