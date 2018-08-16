package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.GetItemFromBank;

import java.util.function.Predicate;

public class GetStamina extends GetItemFromBank {

    public static final Predicate<Item> STAMINA = a -> a.getName().contains("Stamina");
    private final int AMOUNT = 1;
    public static boolean has_stamina = true;

    public GetStamina(AirOrbChargeMission mission) {
        super(mission);
        item = STAMINA;
        amount = AMOUNT;
    }

    @Override
    public String toString() {
        return "Getting Stamina potion";
    }
}

