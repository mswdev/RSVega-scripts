package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.defence.level_to_30;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

import java.util.function.Predicate;

public class EquipBronzeSword extends AirOrbLevelWorker {

    public static final String ITEM_NAME = "Bronze sword";
    private final Predicate<Item> item = a -> a.getName().equals(ITEM_NAME);
    private final ItemActionWorker equip_bronze_sword;

    public EquipBronzeSword(AirOrbLevelMission mission) {
        super(mission);
        equip_bronze_sword = new ItemActionWorker<>(item);
    }

    @Override
    public void work() {
        equip_bronze_sword.work();
        mission.can_end = equip_bronze_sword.itemNotFound();
    }

    @Override
    public String toString() {
        return "Equipping Bronze sword";
    }
}

