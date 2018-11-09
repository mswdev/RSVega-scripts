package sphiinx.script.private_script.zerker.revenants.mission.worker.impl;

import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.pricechecking.PriceCheck;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.io.IOException;

public class LootItem extends Worker<RevenantMission> {

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final Pickable[] pickables = Pickables.getLoaded();
        if (pickables.length <= 0)
            return;

        for (Pickable pickable : pickables) {
            try {
                if (PriceCheck.getOSBuddyPrice(pickable.getId()) < Main.getParsedArgs().MIN_LOOT_VALUE)
                    continue;
            } catch (IOException e) {
                e.printStackTrace();
            }

            final int inventory_cache = Inventory.getItems().length;
            if (pickable.click())
                Time.sleepUntil(() -> Inventory.getItems().length != inventory_cache, 3500);
        }
    }

    @Override
    public String toString() {
        return "Looting item";
    }
}

