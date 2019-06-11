package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.api.component.Bank;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

public class WithdrawLogs extends Worker {

    private WithdrawWorker withdrawLogType;

    public WithdrawLogs(HousePlankingMission mission) {
        this.withdrawLogType = new WithdrawWorker(mission, a -> a.getName().equals(mission.getLogType().getName()), 0, Bank.WithdrawMode.NOTE);
    }

    @Override
    public void work() {
        withdrawLogType.work();
    }

    @Override
    public String toString() {
        return "Withdrawing noted logs.";
    }
}

