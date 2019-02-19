package sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.item_management;

import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_aio_firemaking.api.game.pricechecking.PriceCheck;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.goal.GoalList;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.mission.Mission;

import java.io.IOException;
import java.util.function.BooleanSupplier;

public class ItemManagementEntry {

    public final int id;
    public final int quantity;
    public final int price;
    public GoalList goals;
    public BooleanSupplier goal_override;
    public final int value_needed;

    private Mission mission;

    public ItemManagementEntry(Mission mission, int id, int quantity, GoalList goals) throws IOException {
        this(mission, id, quantity, PriceCheck.getOSBuddyPrice(id), goals, null);
    }

    public ItemManagementEntry(Mission mission, int id, int quantity, BooleanSupplier goal_override) throws IOException {
        this(mission, id, quantity, PriceCheck.getOSBuddyPrice(id), null, goal_override);
    }

    public ItemManagementEntry(Mission mission, int id, int quantity, int price, GoalList goals) {
        this(mission, id, quantity, price, goals, null);
    }

    public ItemManagementEntry(Mission mission, int id, int quantity, int price, BooleanSupplier goal_override) {
        this(mission, id, quantity, price, null, goal_override);
    }

    public ItemManagementEntry(Mission mission, int id, int quantity, int price, GoalList goals, BooleanSupplier goal_override) {
        this.mission = mission;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.goals = goals;
        this.goal_override = goal_override;
        value_needed = price * quantity;
    }

    public boolean shouldOverride() {
        if (goals != null && goal_override != null)
            return goals.hasReachedGoals() && goal_override.getAsBoolean();

        return goals != null ? goals.hasReachedGoals() && !playerHasEntry() : goal_override != null && goal_override.getAsBoolean();
    }

    public boolean shouldBuy(long total_value) {
        return !mission.getScript().bank_cache.get().isEmpty() && total_value >= value_needed && shouldOverride();
    }

    public boolean playerHasEntry() {
        boolean in_inventory = Inventory.contains(id, id + 1);
        boolean in_equipment = Equipment.contains(id);
        boolean in_bank = mission.getScript().bank_cache.get().containsKey(id);

        return in_inventory || in_equipment || in_bank;
    }

}
