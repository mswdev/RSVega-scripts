package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.GetUnpoweredOrbs;

import java.util.function.Predicate;

public class ChargeOrbs extends AirOrbChargeWorker {

    public static final Predicate<Item> AIR_ORB = a -> a.getName().equals("Air orb");
    public static final Predicate<SceneObject> OBELISK = a -> a.getName().equals("Obelisk of Air");

    public ChargeOrbs(AirOrbChargeMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        final SceneObject OBJECT = SceneObjects.getNearest(OBELISK);
        if (OBJECT == null)
            return;

        if (Production.isOpen()) {
            final int CACHE = Inventory.getCount(GetUnpoweredOrbs.UNPOWERED_ORB);
            Production.initiate();
            Time.sleepUntil(() -> Inventory.getCount(GetUnpoweredOrbs.UNPOWERED_ORB) != CACHE, 3500);
            Time.sleep(200);
        } else {
            Magic.interact(Spell.Modern.CHARGE_AIR_ORB, "Cast");
            OBJECT.interact("Cast");
            Time.sleepUntil(Production::isOpen, 2000);
        }
    }

    @Override
    public String toString() {
        return "Charging Unpowered orbs";
    }
}

