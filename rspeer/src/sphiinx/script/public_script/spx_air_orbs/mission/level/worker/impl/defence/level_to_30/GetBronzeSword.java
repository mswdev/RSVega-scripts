package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.defence.level_to_30;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetBronzeSword extends GetItemFromBank {

    public static final Predicate<Item> BRONZE_SWORD = a -> a.getName().equals("Bronze sword");
    private final int AMOUNT = 1;

    public GetBronzeSword(AirOrbLevelMission mission) {
        super(mission);
        item = BRONZE_SWORD;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Bronze sword";
    }
}

