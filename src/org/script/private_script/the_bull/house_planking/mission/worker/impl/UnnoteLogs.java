package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

public class UnnoteLogs extends Worker {

    private HousePlankingMission mission;

    public UnnoteLogs(HousePlankingMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final SceneObject bankChest = SceneObjects.getNearest(a -> a.getName().equals("Bank chest"));
        if (bankChest == null)
            return;

        if (!Dialog.isOpen())
            if (Inventory.use(a -> a.getName().equals(mission.getLogType().getName()), bankChest))
                Time.sleepUntil(Dialog::isOpen, 1500);

        final int inventoryCache = Inventory.getCount();
        if (Dialog.process(a -> a.equals("Yes"))) {
            Time.sleepUntil(() -> Inventory.getCount() != inventoryCache, 1500);
            Log.severe("Sleeping 900ms to ensure butler has returned from the bank...");
            Time.sleep(900);
        }
    }

    @Override
    public String toString() {
        return "Unnoting logs";
    }
}

