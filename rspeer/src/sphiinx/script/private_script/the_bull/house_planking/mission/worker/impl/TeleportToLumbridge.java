package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.private_script.the_bull.house_planking.Main;

public class TeleportToLumbridge extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
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

        // [TODO - 2018-11-28]: This is temporary.
        Main.PLANKS_CREATED += 24;
    }

    @Override
    public String toString() {
        return "Teleporting to Lumbridge";
    }
}
