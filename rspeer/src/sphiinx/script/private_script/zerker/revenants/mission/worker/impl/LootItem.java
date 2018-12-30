package sphiinx.script.private_script.zerker.revenants.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.game.pricechecking.PriceCheck;
import sphiinx.script.private_script.zerker.revenants.Main;

public class LootItem extends Worker {

    private boolean needs_bank;

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
            if (PriceCheck.getRSPrice(pickable.getId()) < Main.ARGS.MIN_LOOT_VALUE)
                continue;

            final int inventory_cache = Inventory.getItems().length;
            if (pickable.click())
                Time.sleepUntil(() -> Inventory.getItems().length != inventory_cache, 3500);
        }

        final Item[] inventory_items = Inventory.getItems();
        if (inventory_items.length <= 0)
            return;

        int inventory_value = 0;
        for (Item item : inventory_items) {
            if (item.getName().equals("Shark"))
                continue;

            inventory_value += PriceCheck.getRSPrice(item.getId());
        }

        if (inventory_value >= Main.ARGS.MIN_LOOT_VALUE)
            needs_bank = true;

    }

    @Override
    public String toString() {
        return "Looting item.";
    }

    public boolean needsBank() {
        return needs_bank;
    }

    public void setNeedsBank(boolean needs_bank) {
        this.needs_bank = needs_bank;
    }
}

