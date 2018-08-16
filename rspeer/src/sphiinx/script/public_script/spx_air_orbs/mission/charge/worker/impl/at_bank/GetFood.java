package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetFood extends GetItemFromBank {

    public static final Predicate<Item> FOOD = a -> a.getName().equals("Jug of wine") || a.getName().equals("Tuna");
    static final int AMOUNT = 1;

    public GetFood(AirOrbChargeMission mission) {
        super(mission);
        item = FOOD;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting food";
    }
}

