package org.api.script.framework.item_management;

import org.api.game.pricechecking.PriceCheck;
import org.api.script.SPXScript;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.io.IOException;

public class ItemManagementTracker {

    public static final int GOLD_ID = 995;
    public final SPXScript script;
    private final ItemManagement item_management;
    private long total_gold;
    private long total_sellable_gold;

    public ItemManagementTracker(SPXScript script, ItemManagement item_management) {
        this.script = script;
        this.item_management = item_management;
    }

    /**
     * Updates the item management tracker. Updates the total gold the player has in their inventory and bank in
     * addition to the total sellable gold in terms of the sellable items.
     * <p>
     * This also takes into account the buy price modifier and sell price modifier.
     */
    public void update() {
        final long inventory_gold = Inventory.getCount(true, GOLD_ID);
        final long bank_gold = script.bank_cache.get().getOrDefault(GOLD_ID, 0);
        total_gold = inventory_gold + bank_gold;

        total_sellable_gold = 0;
        for (int id : item_management.itemsToSell()) {
            final long inventory_amount = Inventory.getCount(id);
            final long bank_amount = script.bank_cache.get().getOrDefault(id, 0);

            try {
                total_sellable_gold += (inventory_amount + bank_amount) * (PriceCheck.getOSBuddyPrice(id) * item_management.sellPriceModifier());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Determines which item management entry should be bought based on the total value we have and the buy price
     * modifier.
     *
     * @return The item management entry that should be bought.
     */
    public ItemManagementEntry shouldBuy() {
        for (ItemManagementEntry item_management_entry : item_management.itemsToBuy()) {
            if (item_management_entry.canBuy(getTotalValue(), item_management.buyPriceModifier()))
                return item_management_entry;
        }

        return null;
    }

    /**
     * Gets the total value the player has. This is the total gold combined with the total sellable gold the player
     * has.
     *
     * @return The total value the player has.
     */
    private long getTotalValue() {
        return total_gold + total_sellable_gold;
    }

    /**
     * Gets the total gold the player has in their inventory and bank.
     *
     * @return The total gold.
     */
    public long getTotalGold() {
        return total_gold;
    }

    /**
     * Gets the item management.
     *
     * @return The item management.
     */
    public ItemManagement getItemManagement() {
        return item_management;
    }
}
