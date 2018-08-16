package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetUnpoweredOrbs extends GetItemFromBank {

    public static final Predicate<Item> UNPOWERED_ORB = a -> a.getName().equals("Unpowered orb");
    private final int AMOUNT = 0;

    public GetUnpoweredOrbs(AirOrbChargeMission mission) {
        super(mission);
        item = UNPOWERED_ORB;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Unpowered orbs";
    }
}

