package org.api.script.impl.worker.banking;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.DepositBox;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.function.Predicate;

public class DepositWorker extends Worker {

    private final Predicate<Item> item_predicate;
    private final int amount;
    private final int inventory_cache;
    private final OpenBankWorker open_bank_worker;

    public DepositWorker() {
        this(null, 0);
    }

    public DepositWorker(Predicate<Item> item_predicate) {
        this(item_predicate, 0);
    }

    public DepositWorker(Predicate<Item> item_predicate, int amount) {
        this.item_predicate = item_predicate;
        this.amount = amount;
        inventory_cache = Inventory.getCount(true);
        open_bank_worker = new OpenBankWorker();
    }

    @Override
    public boolean needsRepeat() {
        return Inventory.getCount(true) == inventory_cache && !Inventory.isEmpty();
    }

    @Override
    public void work() {
        if (!Bank.isOpen() && !DepositBox.isOpen()) {
            open_bank_worker.work();
            return;
        }

        if (Bank.isOpen()) {
            if (item_predicate == null) {
                Bank.depositInventory();
                return;
            }

            if (amount == 0) {
                Bank.depositAll(item_predicate);
                return;
            }

            Bank.deposit(item_predicate, amount);
        }

        if (item_predicate == null) {
            DepositBox.depositInventory();
            return;
        }

        if (amount == 0) {
            DepositBox.depositAll(item_predicate);
            return;
        }

        DepositBox.deposit(item_predicate, true, amount);
    }

    @Override
    public String toString() {
        return "Executing deposit worker.";
    }
}

