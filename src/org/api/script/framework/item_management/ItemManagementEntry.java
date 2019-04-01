package org.api.script.framework.item_management;

import org.api.game.pricechecking.PriceCheck;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.mission.Mission;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.io.IOException;
import java.util.function.BooleanSupplier;

public class ItemManagementEntry {

    private final int id;
    private final int quantity;
    private GoalList goals;
    private BooleanSupplier goal_override;
    private Mission mission;

    public ItemManagementEntry(Mission mission, int id, int quantity, GoalList goals) {
        this(mission, id, quantity, goals, null);
    }

    public ItemManagementEntry(Mission mission, int id, int quantity, BooleanSupplier goal_override) {
        this(mission, id, quantity, null, goal_override);
    }

    public ItemManagementEntry(Mission mission, int id, int quantity, GoalList goals, BooleanSupplier goal_override) {
        this.mission = mission;
        this.id = id;
        this.quantity = quantity;
        this.goals = goals;
        this.goal_override = goal_override;
    }

    /**
     * Determines whether the goal should be overridden by the goal override boolean supplier.
     *
     * @return True if the goal should be overridden; false otherwise.
     */
    private boolean shouldOverride() {
        if (goals != null && goal_override != null)
            return goals.hasReachedGoals() && goal_override.getAsBoolean();

        return goals != null ? goals.hasReachedGoals() && !playerHasEntry() : goal_override != null && goal_override.getAsBoolean();
    }

    /**
     * Determines whether the player can buy the specified entry.
     *
     * @param total_value        The total value of gold and sellables the player has taking into account the sell price
     *                           modifier.
     * @param buy_price_modifier The buy price modifier.
     * @return True if the entry can be bought; false otherwise.
     */
    boolean canBuy(long total_value, double buy_price_modifier) {
        return !mission.getScript().bank_cache.get().isEmpty() && total_value >= getValueNeeded(buy_price_modifier) && shouldOverride();
    }

    /**
     * Determines whether the player already has the entry in their inventory, bank, or equipment.
     *
     * @return True if the player has the entry; false otherwise.
     */
    private boolean playerHasEntry() {
        final boolean in_inventory = Inventory.contains(id, id + 1);
        final boolean in_equipment = Equipment.contains(id);
        final boolean in_bank = mission.getScript().bank_cache.get().containsKey(id);

        return in_inventory || in_equipment || in_bank;
    }

    /**
     * Gets the item id.
     *
     * @return The item id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the value needed to buy the item taking into consideration the price, quantity, and buy price modifier.
     *
     * @param buy_price_modifier The buy price modifier.
     * @return The value needed to buy the item; 0 otherwise.
     */
    public int getValueNeeded(double buy_price_modifier) {
        if (id == ItemManagementTracker.GOLD_ID)
            return quantity;

        try {
            return (int) (PriceCheck.getOSBuddyPrice(id) * quantity * buy_price_modifier);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Gets the quantity of item to buy.
     *
     * @return The quantity of item to buy.
     */
    public int getQuantity() {
        return quantity;
    }
}
