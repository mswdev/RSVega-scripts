package sphiinx.api.script.framework.item_management;

import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;
import sphiinx.api.game.pricechecking.PriceCheck;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.mission.Mission;

import java.io.IOException;
import java.util.function.BooleanSupplier;

public class ItemManagementEntry {

    public final int id;
    public final int quantity;
    public GoalList goals;
    public BooleanSupplier goal_override;
    public int value_needed;

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
        try {
            value_needed = PriceCheck.getOSBuddyPrice(id) * quantity;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean shouldOverride() {
        if (goals != null && goal_override != null)
            return goals.hasReachedGoals() && goal_override.getAsBoolean();

        return goals != null ? goals.hasReachedGoals() && !playerHasEntry() : goal_override != null && goal_override.getAsBoolean();
    }

    public boolean shouldBuy(long total_value, double buy_price_modifier) {
        return !mission.getScript().bank_cache.get().isEmpty() && total_value >= value_needed * buy_price_modifier && shouldOverride();
    }

    public boolean playerHasEntry() {
        boolean in_inventory = Inventory.contains(id, id + 1);
        boolean in_equipment = Equipment.contains(id);
        boolean in_bank = mission.getScript().bank_cache.get().containsKey(id);

        return in_inventory || in_equipment || in_bank;
    }
}
