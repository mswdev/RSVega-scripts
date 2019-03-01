package org.api.script.impl.mission.item_management_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.item_management_mission.ItemManagementMission;
import org.api.script.impl.worker.banking.OpenBankWorker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.ui.Log;

public class WithdrawSellablesWorker extends Worker {

    private ItemManagementMission mission;
    private OpenBankWorker open_bank_worker = new OpenBankWorker();
    private WithdrawWorker withdraw_worker;


    public WithdrawSellablesWorker(ItemManagementMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!Bank.isOpen()) {
            open_bank_worker.work();
            return;
        }

        if (Bank.getWithdrawMode() != Bank.WithdrawMode.NOTE) {
            Bank.setWithdrawMode(Bank.WithdrawMode.NOTE);
            return;
        }

        int withdraw_count = 0;
        for (int item_id : mission.items_to_sell) {
            withdraw_worker = new WithdrawWorker(a -> a.getId() == item_id, 0, Bank.WithdrawMode.NOTE);
            withdraw_worker.work();

            if (withdraw_worker.itemNotFound())
                withdraw_count++;
        }

        if (withdraw_count == mission.items_to_sell.length) {
            Log.severe("You do not have any items to sell in your bank.");
            mission.should_end = true;
            return;
        }

        mission.has_withdrawn_sellables = true;
    }

    @Override
    public String toString() {
        return "Executing item management withdraw sellables worker.";
    }
}
