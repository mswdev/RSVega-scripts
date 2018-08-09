package sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorker;

public class Logout extends AccountCheckerWorker {


    public Logout(AccountCheckerMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Game.logout())
            if (Time.sleepUntil(() -> !Game.isLoggedIn(), 2500)) {
                Vars.get().ACCOUNT_DATA.forEach((k, v) -> System.out.println(k + ", " + v));
                Vars.get().ACCOUNT_DATA.clear();
                Vars.get().can_logout = false;
                AccountCheckerMission.nextAccount();
            }
    }

    @Override
    public String toString() {
        return "Logging out";
    }
}

