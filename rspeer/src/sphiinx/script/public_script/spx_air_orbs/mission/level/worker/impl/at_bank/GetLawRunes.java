package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetLawRunes extends GetItemFromBank {

    public static final Predicate<Item> LAW_RUNE = a -> a.getName().equals("Law rune");
    private final int AMOUNT = 0;

    public GetLawRunes(AirOrbLevelMission mission) {
        super(mission);
        item = LAW_RUNE;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Law runes";
    }
}

