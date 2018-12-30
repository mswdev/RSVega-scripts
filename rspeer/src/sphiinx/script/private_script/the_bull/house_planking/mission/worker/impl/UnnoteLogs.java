package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.private_script.the_bull.house_planking.Main;

public class UnnoteLogs extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final SceneObject bank_chest = SceneObjects.getNearest(a -> a.getName().equals("Bank chest"));
        if (bank_chest == null)
            return;

        if (!Dialog.isOpen())
            if (Inventory.use(a -> a.getName().equals(Main.ARGS.LOG_TYPE.getName()), bank_chest))
                Time.sleepUntil(Dialog::isOpen, 1500);

        final int inventory_cache = Inventory.getItems().length;
        if (Dialog.process(a -> a.equals("Yes"))) {
            Time.sleepUntil(() -> Inventory.getItems().length != inventory_cache, 1500);
            Log.severe("Sleeping 450ms to ensure butler has returned from the bank...");
            Time.sleep(450);
        }
    }

    @Override
    public String toString() {
        return "Unnoting logs";
    }
}

