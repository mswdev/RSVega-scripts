package sphiinx.script.public_script.spx_account_checker.mission.worker;

import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;

public abstract class AccountCheckerWorker extends Worker<AccountCheckerMission> {


    public AccountCheckerWorker(AccountCheckerMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

