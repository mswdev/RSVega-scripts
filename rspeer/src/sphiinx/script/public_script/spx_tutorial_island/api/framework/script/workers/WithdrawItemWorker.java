package sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class WithdrawItemWorker<mission extends Mission> extends Worker<mission> {

    private final Predicate<Item> item;
    private final Bank.WithdrawMode withdraw_mode;
    private final int amount;
    private final OpenBankWorker open_bank;
    private boolean item_not_found;

    public WithdrawItemWorker(Predicate<Item> item) {
        this(item, Bank.WithdrawMode.ITEM, 1);
    }

    public WithdrawItemWorker(Predicate<Item> item, int amount) {
        this(item, Bank.WithdrawMode.ITEM, amount);
    }

    public WithdrawItemWorker(Predicate<Item> item, Bank.WithdrawMode withdraw_mode) {
        this(item, withdraw_mode, 1);
    }

    public WithdrawItemWorker(Predicate<Item> item, Bank.WithdrawMode withdraw_mode, int amount) {
        this.item = item;
        this.withdraw_mode = withdraw_mode;
        this.amount = amount;
        open_bank = new OpenBankWorker(false);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        item_not_found = false;
        if (Bank.isOpen()) {
            if (Bank.getWithdrawMode() != withdraw_mode)
                if (Bank.setWithdrawMode(withdraw_mode))
                    Time.sleepUntil(() -> Bank.getWithdrawMode() == withdraw_mode, 1500);

            if (Bank.contains(item) && Bank.getCount(item) >= amount) {
                if ((Bank.getFirst(item).isStackable() && !Inventory.contains(item) && Inventory.isFull()) || (!Bank.getFirst(item).isStackable() && (amount > Inventory.getFreeSlots()) || Inventory.isFull())) {
                    // [TODO - 2018-10-26]: Add a deposit item worker then allow the ability to pass an instance of the deposit worker to this.
                    if (Bank.depositInventory())
                        Time.sleepUntil(() -> Inventory.getFreeSlots() == 28, 1500);
                } else {
                    final int cache = Inventory.getItems().length;
                    final BooleanSupplier cache_supplier = () -> Inventory.getItems().length != cache;
                    if (amount == 0) {
                        if (Bank.withdrawAll(item))
                            Time.sleepUntil(cache_supplier, 1500);
                    } else {
                        if (Bank.withdraw(item, amount))
                            Time.sleepUntil(cache_supplier, 1500);
                    }
                }
            } else {
                Log.severe("You do not have the required items in your bank.");
                item_not_found = true;
            }
        } else {
            open_bank.work();
        }
    }

    public boolean itemNotFound() {
        return item_not_found;
    }

    @Override
    public String toString() {
        return "Executing bank withdraw worker";
    }
}

