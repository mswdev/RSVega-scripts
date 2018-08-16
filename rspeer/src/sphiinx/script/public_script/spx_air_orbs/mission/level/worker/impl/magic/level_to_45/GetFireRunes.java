package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetFireRunes extends GetItemFromBank {

    public static final Predicate<Item> FIRE_RUNE = a -> a.getName().equals("Fire rune");
    private final int AMOUNT = 0;

    public GetFireRunes(AirOrbLevelMission mission) {
        super(mission);
        item = FIRE_RUNE;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Fire runes";
    }
}

