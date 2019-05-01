package org.script.free_script.spx_account_checker.mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.script.free_script.spx_account_checker.data.Vars;
import org.script.free_script.spx_account_checker.mission.AccountCheckerMission;
import org.script.free_script.spx_account_checker.mission.worker.impl.CheckAge;
import org.script.free_script.spx_account_checker.mission.worker.impl.CheckBank;
import org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount.Logout;
import org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount.Wait;

public class AccountCheckerWorkerHandler extends WorkerHandler {

    private final CheckAge checkAge;
    private final CheckBank checkBank;
    private final Logout logout;
    private final Wait wait;

    public AccountCheckerWorkerHandler(AccountCheckerMission mission) {
        checkAge = new CheckAge();
        checkBank = new CheckBank(mission);
        logout = new Logout(mission);
        wait = new Wait();
    }

    @Override
    public Worker decide() {
        if (Vars.get().canLogout && !Vars.get().checkBank && !Vars.get().checkAge)
            return logout;

        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 20)
            if (Movement.toggleRun(true))
                Time.sleepUntil(Movement::isRunEnabled, 1500);

        if (Vars.get().checkAge)
            return checkAge;

        if (Vars.get().checkBank)
            return checkBank;

        return wait;
    }
}

