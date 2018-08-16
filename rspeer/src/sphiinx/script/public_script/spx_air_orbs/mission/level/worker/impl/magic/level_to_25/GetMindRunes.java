package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_25;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetMindRunes extends GetItemFromBank {

    public static final Predicate<Item> MIND_RUNE = a -> a.getName().equals("Mind rune");
    private final int AMOUNT = 0;

    public GetMindRunes(AirOrbLevelMission mission) {
        super(mission);
        item = MIND_RUNE;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Mind runes";
    }
}

