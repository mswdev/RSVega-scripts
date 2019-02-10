package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.worker.banking;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;

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

    public WithdrawWorker(Predicate<Item> item) {
        this(item, Bank.WithdrawMode.ITEM, 1);
    }

    public WithdrawWorker(Predicate<Item> item, int amount) {
        this(item, Bank.WithdrawMode.ITEM, amount);
    }

    public WithdrawWorker(Predicate<Item> item, Bank.WithdrawMode withdraw_mode) {
        this(item, withdraw_mode, 1);
    }

    public WithdrawWorker(Predicate<Item> item, Bank.WithdrawMode withdraw_mode, int amount) {
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

        final BooleanSupplier boolean_supplier = () -> Inventory.contains(item);
        if (amount == 0) {
            if (!Bank.withdrawAll(item))
                setItemNotFound();
            Time.sleepUntil(boolean_supplier, 1500);
            return;
        }

        if (!Bank.withdraw(item, amount))
            setItemNotFound();
        Time.sleepUntil(boolean_supplier, 1500);
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

