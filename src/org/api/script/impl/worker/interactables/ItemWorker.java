package org.api.script.impl.worker.interactables;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.Predicate;

public class ItemWorker extends Worker {

    private final Predicate<Item> item_predicate;
    private final Predicate<String> action_predicate;
    private final WithdrawWorker withdraw_worker;

    public ItemWorker(Predicate<Item> item_predicate) {
        this(item_predicate, a -> true, null);
    }

    public ItemWorker(Predicate<Item> item_predicate, Predicate<String> action_predicate) {
        this(item_predicate, action_predicate, null);
    }

    public ItemWorker(Predicate<Item> item_predicate, WithdrawWorker withdraw_worker) {
        this(item_predicate, a -> true, withdraw_worker);
    }

    public ItemWorker(Predicate<Item> item_predicate, Predicate<String> action_predicate, WithdrawWorker withdraw_worker) {
        this.item_predicate = item_predicate;
        this.action_predicate = action_predicate;
        this.withdraw_worker = withdraw_worker;
    }

    @Override
    public boolean needsRepeat() {
        return withdraw_worker != null && withdraw_worker.needsRepeat();
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final Item item = Inventory.getFirst(item_predicate);
        if (item == null) {
            if (withdraw_worker == null)
                return;

            withdraw_worker.work();
            return;
        }

        final int equipment_cache = Equipment.getItems().length;
        if (item.interact(action_predicate))
            Time.sleepUntil(() -> equipment_cache != Equipment.getItems().length || Players.getLocal().getAnimation() != -1 || Inventory.getSelectedItem() != null, 1500);
    }

    public boolean itemNotFound() {
        return withdraw_worker.itemNotFound();
    }

    @Override
    public String toString() {
        return "Executing item worker.";
    }
}

