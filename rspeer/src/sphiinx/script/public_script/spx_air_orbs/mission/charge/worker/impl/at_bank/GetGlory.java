package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetGlory extends GetItemFromBank {

    public static final Predicate<Item> GLORY = a -> a.getName().contains("glory(");
    private static final int AMOUNT = 1;

    public GetGlory(AirOrbChargeMission mission) {
        super(mission);
        item = GLORY;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Amulet of glory";
    }
}

