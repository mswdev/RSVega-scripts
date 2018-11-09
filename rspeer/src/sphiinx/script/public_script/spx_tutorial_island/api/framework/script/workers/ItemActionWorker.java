package sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;

import java.util.function.Predicate;

public class ItemActionWorker<mission extends Mission> extends Worker<mission> {

    private final Predicate<Item> item;
    private final Predicate<String> action;
    private final WithdrawItemWorker withdraw_item_from_bank;

    public ItemActionWorker(Predicate<Item> item) {
        this(item, a -> true, 1);
    }

    public ItemActionWorker(Predicate<Item> item, int amount) {
        this(item, a -> true, amount);
    }

    public ItemActionWorker(Predicate<Item> item, Predicate<String> action) {
        this(item, action, 1);
    }

    public ItemActionWorker(Predicate<Item> item, Predicate<String> action, int amount) {
        this.item = item;
        this.action = action;
        withdraw_item_from_bank = new WithdrawItemWorker(this.item, Bank.WithdrawMode.ITEM, amount);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final Item inv_item = Inventory.getFirst(item);
        if (inv_item != null) {
            if (inv_item.interact(action))
                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || !Inventory.contains(item), 1500);
        } else {
            withdraw_item_from_bank.work();
        }
    }

    public boolean itemNotFound() {
        return withdraw_item_from_bank.itemNotFound();
    }

    @Override
    public String toString() {
        return "Executing item action worker";
    }
}

