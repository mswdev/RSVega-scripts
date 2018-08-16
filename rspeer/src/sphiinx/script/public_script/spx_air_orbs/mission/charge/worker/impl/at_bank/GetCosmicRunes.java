package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetCosmicRunes extends GetItemFromBank {

    public static final Predicate<Item> COSMIC_RUNE = a -> a.getName().equals("Cosmic rune");
    private static final int AMOUNT = 81;

    public GetCosmicRunes(AirOrbChargeMission mission) {
        super(mission);
        item = COSMIC_RUNE;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Cosmic runes";
    }
}

