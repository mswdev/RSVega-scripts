package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetStaffOfAir extends GetItemFromBank {

    public static final Predicate<Item> STAFF_OF_AIR = a -> a.getName().equals("Staff of air");
    private final int AMOUNT = 1;

    public GetStaffOfAir(AirOrbLevelMission mission) {
        super(mission);
        item = STAFF_OF_AIR;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Staff of air";
    }
}

