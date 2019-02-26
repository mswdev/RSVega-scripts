package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.api.component.Bank;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

public class WithdrawLogs extends Worker {


    private final HousePlankingMission mission;
    private WithdrawWorker withdraw_logtype;

    public WithdrawLogs(HousePlankingMission mission) {
        this.mission = mission;
        this.withdraw_logtype = new WithdrawWorker(a -> a.getName().equals(mission.getLogType().getName()), 0, Bank.WithdrawMode.NOTE);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        withdraw_logtype.work();
    }

    @Override
    public String toString() {
        return "Withdrawing noted logs.";
    }
}

