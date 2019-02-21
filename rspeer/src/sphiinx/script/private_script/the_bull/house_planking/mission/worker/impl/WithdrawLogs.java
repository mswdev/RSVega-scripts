package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.the_bull.house_planking.Main;
import sphiinx.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

public class WithdrawLogs extends Worker {


    private static final WithdrawWorker WITHDRAW_LOGS = new WithdrawWorker(a -> a.getName().equals(Main.ARGS.LOG_TYPE1.getName()), 0, Bank.WithdrawMode.NOTE);
    private final HousePlankingMission mission;

    public WithdrawLogs(HousePlankingMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        WITHDRAW_LOGS.work();
    }

    @Override
    public String toString() {
        return "Withdrawing noted logs.";
    }
}

