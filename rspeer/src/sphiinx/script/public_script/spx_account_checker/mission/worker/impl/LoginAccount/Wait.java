package sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount;

import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorker;

public class Wait extends AccountCheckerWorker {


    public Wait(AccountCheckerMission mission) {
        super(mission);
    }

    @Override
    public void work() {
    }

    @Override
    public String toString() {
        return "Waiting";
    }
}

