package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

public class TeleportToHouse extends Worker {

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (!Tabs.isOpen(Tab.MAGIC))
            if (Tabs.open(Tab.MAGIC))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.MAGIC), 1500);

        if (Magic.cast(Spell.Modern.TELEPORT_TO_HOUSE))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 && SceneObjects.getNearest("Portal") != null, 3500);

    }

    @Override
    public String toString() {
        return "Teleporting to house";
    }
}

