package sphiinx.script.public_script.spx_account_checker.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.CheckAge;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.CheckBank;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.Logout;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.Wait;

public class AccountCheckerWorkerHandler extends WorkerHandler<AccountCheckerMission> {

    private final CheckAge CHECK_AGE;
    private final CheckBank CHECK_BANK;
    private final Logout LOGOUT;
    private final Wait WAIT;

    public AccountCheckerWorkerHandler(AccountCheckerMission mission) {
        super(mission);
        CHECK_AGE = new CheckAge(mission);
        CHECK_BANK = new CheckBank(mission);
        LOGOUT = new Logout(mission);
        WAIT = new Wait(mission);
    }

    @Override
    public Worker<AccountCheckerMission> decide() {
        if (Vars.get().can_logout)
            return LOGOUT;

        if (Vars.get().check_bank)
            return CHECK_BANK;

        if (Vars.get().check_age)
            return CHECK_AGE;

        return WAIT;
    }
}

