package org.script.public_script.spx_account_checker.mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.script.public_script.spx_account_checker.data.Vars;
import org.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import org.script.public_script.spx_account_checker.mission.worker.impl.CheckAge;
import org.script.public_script.spx_account_checker.mission.worker.impl.CheckBank;
import org.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.Logout;
import org.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.Wait;

public class AccountCheckerWorkerHandler extends WorkerHandler {

    private final CheckAge check_age;
    private final CheckBank check_bank;
    private final Logout logout;
    private final Wait wait;

    public AccountCheckerWorkerHandler(AccountCheckerMission mission) {
        check_age = new CheckAge();
        check_bank = new CheckBank(mission);
        logout = new Logout(mission);
        wait = new Wait();
    }

    @Override
    public Worker decide() {
        if (Vars.get().can_logout && !Vars.get().check_bank && !Vars.get().check_age)
            return logout;

        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 20)
            if (Movement.toggleRun(true))
                Time.sleepUntil(Movement::isRunEnabled, 1500);

        if (Vars.get().check_age)
            return check_age;

        if (Vars.get().check_bank)
            return check_bank;

        return wait;
    }
}

