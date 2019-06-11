package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.*;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.script.private_script.the_bull.house_planking.Main;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

public class TeleportToLumbridge extends Worker {

    private HousePlankingMission mission;

    public TeleportToLumbridge(HousePlankingMission mission) {
        this.mission = mission;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (!Tabs.isOpen(Tab.MAGIC))
            if (Tabs.open(Tab.MAGIC))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.MAGIC), 1500);

        if (Magic.cast(Spell.Modern.LUMBRIDGE_TELEPORT))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 && SceneObjects.getNearest("Bank chest") != null, 3500);

        Main.PLANKS_CREATED += 24;
        if (Inventory.getCount(mission.getLogType().getNotedItemId()) <= 0)
            mission.shouldEnd = true;
    }

    @Override
    public String toString() {
        return "Teleporting to Lumbridge";
    }
}
