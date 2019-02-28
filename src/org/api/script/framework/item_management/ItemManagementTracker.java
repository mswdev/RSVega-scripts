package org.api.script.framework.item_management;

import org.api.game.pricechecking.PriceCheck;
import org.api.script.SPXScript;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.io.IOException;

public class ItemManagementTracker {

    public static final int GOLD_ID = 995;
    public final SPXScript script;
    public final ItemManagement item_management;
    private long total_gold;
    private long total_sellable_value;

    public ItemManagementTracker(SPXScript script, ItemManagement item_management) {
        this.script = script;
        this.item_management = item_management;
    }

    public void update() {
        final long inventory_gold = Inventory.getCount(true, GOLD_ID);
        final long bank_gold = script.bank_cache.get().getOrDefault(GOLD_ID, 0);
        total_gold = inventory_gold + bank_gold;

        total_sellable_value = 0;
        for (int id : item_management.itemsToSell()) {
            final long inventory_amount = Inventory.getCount(id);
            final long bank_amount = script.bank_cache.get().getOrDefault(id, 0);

            try {
                total_sellable_value += (inventory_amount + bank_amount) * (PriceCheck.getOSBuddyPrice(id) * item_management.sellPriceModifier());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ItemManagementEntry needsToBuy() {
        for (ItemManagementEntry item_management_entry : item_management.itemsToBuy()) {
            if (item_management_entry.shouldBuy(getTotalValue(), item_management.buyPriceModifier()))
                return item_management_entry;
        }

        return null;
    }

    public long getTotalGold() {
        return total_gold;
    }

    public long getTotalValue() {
        return total_gold + total_sellable_value;
    }
}
