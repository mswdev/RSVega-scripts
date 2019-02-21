package sphiinx.api.script.impl.mission.item_management_mission.worker.impl;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.ui.Log;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.item_management_mission.ItemManagementMission;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;

public class WithdrawSellablesWorker extends Worker {

    private ItemManagementMission mission;
    private WithdrawWorker withdraw_worker;


    public WithdrawSellablesWorker(ItemManagementMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return withdraw_worker.needsRepeat();
    }

    @Override
    public void work() {
        int withdraw_count = 0;
        for (int item_id : mission.items_to_sell) {
            withdraw_worker = new WithdrawWorker(a -> a.getId() == item_id, 0, Bank.WithdrawMode.NOTE);
            withdraw_worker.work();

            if (withdraw_worker.itemNotFound()) {
                withdraw_count++;
            }
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