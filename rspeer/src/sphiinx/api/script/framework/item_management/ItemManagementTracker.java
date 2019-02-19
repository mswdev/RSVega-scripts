package sphiinx.api.script.framework.item_management;

import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.api.game.pricechecking.PriceCheck;
import sphiinx.api.script.SPXScript;

import java.io.IOException;

public class ItemManagementTracker {

    public static final double SELL_PRICE_MODIFIER = 0.7;
    public static final int GOLD_ID = 995;

    public final SPXScript script;
    public final ItemManagement item_management;
    public final int[] items_to_sell;
    private final ItemManagementEntry[] items_to_buy;


    private long total_gold;
    private long total_sellable_value;

    public ItemManagementTracker(SPXScript script, ItemManagement item_management) {
        this.script = script;
        this.item_management = item_management;
        this.items_to_sell = item_management.itemsToSell();
        this.items_to_buy = item_management.itemsToBuy();
    }

    public void update() {
        final long inventory_gold = Inventory.getCount(true, GOLD_ID);
        final long bank_gold = script.bank_cache.get().getOrDefault(GOLD_ID, 0);
        total_gold = inventory_gold + bank_gold;

        total_sellable_value = 0;
        for (int id : items_to_sell) {
            final long inventory_amount = Inventory.getCount(id);
            final long bank_amount = script.bank_cache.get().getOrDefault(id, 0);

            try {
                total_sellable_value += (inventory_amount + bank_amount) * (PriceCheck.getOSBuddyPrice(id) * SELL_PRICE_MODIFIER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ItemManagementEntry needsToBuy() {
        for (ItemManagementEntry item_management_entry : items_to_buy) {
            if (item_management_entry.shouldBuy(getTotalValue()))
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
