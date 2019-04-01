package org.api.script.impl.worker.banking;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class WithdrawWorker extends Worker {

    private final Predicate<Item> item;
    private final Bank.WithdrawMode withdraw_mode;
    private final int amount;
    private final int inventory_cache;
    private final OpenBankWorker open_bank_worker;
    private final DepositWorker deposit_worker;
    private boolean item_not_found;

    // [TODO - 3/7/2019]: Temporary until contains bug is fixed.
    private boolean has_checked_twice;

    public WithdrawWorker(Predicate<Item> item) {
        this(item, 1, Bank.WithdrawMode.ITEM);
    }

    public WithdrawWorker(Predicate<Item> item, int amount) {
        this(item, amount, Bank.WithdrawMode.ITEM);
    }

    public WithdrawWorker(Predicate<Item> item, Bank.WithdrawMode withdraw_mode) {
        this(item, 1, withdraw_mode);
    }

    public WithdrawWorker(Predicate<Item> item, int amount, Bank.WithdrawMode withdraw_mode) {
        this.item = item;
        this.withdraw_mode = withdraw_mode;
        this.amount = amount;
        inventory_cache = Inventory.getCount(true);
        open_bank_worker = new OpenBankWorker(false);
        this.deposit_worker = new DepositWorker();
    }

    @Override
    public boolean needsRepeat() {
        return deposit_worker.needsRepeat() || open_bank_worker.needsRepeat() || Inventory.getCount(true) == inventory_cache;
    }

    @Override
    public void work() {
        item_not_found = false;
        if (!Bank.isOpen()) {
            open_bank_worker.work();
            return;
        }

        if (Bank.getWithdrawMode() != withdraw_mode) {
            Bank.setWithdrawMode(withdraw_mode);
            return;
        }

        if (Inventory.isFull()) {
            if (!Bank.getFirst(item).isStackable()) {
                deposit_worker.work();
                return;
            }

            if (!Inventory.contains(item)) {
                deposit_worker.work();
                return;
            }
        }

        if (!Bank.contains(item)) {
            setItemNotFound();
            return;
        }

        final int withdraw_cache = Inventory.getCount(true);
        BooleanSupplier boolean_supplier = () -> withdraw_cache != Inventory.getCount(true);
        if (amount == 0) {
            if (Bank.withdrawAll(item))
                Time.sleepUntil(boolean_supplier, 2500);
            else
                setItemNotFound();
            return;
        }

        if (Bank.withdraw(item, amount))
            Time.sleepUntil(boolean_supplier, 2500);
        else
            setItemNotFound();
    }

    private void setItemNotFound() {
        Log.severe("You do not have the required items in your bank.");
        item_not_found = true;
    }

    public boolean itemNotFound() {
        return item_not_found;
    }

    @Override
    public String toString() {
        return "Executing withdraw worker.";
    }
}

