package org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.ui.Log;
import org.script.free_script.spx_account_checker.data.Vars;
import org.script.free_script.spx_account_checker.http.AccountData;
import org.script.free_script.spx_account_checker.http.AccountDataType;
import org.script.free_script.spx_account_checker.mission.AccountCheckerMission;

import java.util.Map;


public class Logout extends Worker {

    private final AccountCheckerMission mission;

    public Logout(AccountCheckerMission mission) {
        this.mission = mission;
    }

    @Override
    public void work() {
        if (Vars.get().generalData.size() > 0) {
            final FormBody.Builder formBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : Vars.get().generalData.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }

            final RequestBody requestBody = formBuilder.build();
            if (AccountData.putData(AccountDataType.GENERAL, mission.main.accountManager.getAccountId(), requestBody)) {
                Log.fine("[ACCOUNT CHECKER]: PUT general data.");
                Vars.get().generalData.clear();
            }
        } else if (Vars.get().osrsData.size() > 0) {
            final FormBody.Builder formBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : Vars.get().osrsData.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }

            final RequestBody requestBody = formBuilder.build();
            if (AccountData.putData(AccountDataType.OSRS, mission.main.accountManager.getAccountId(), requestBody)) {
                Log.fine("[ACCOUNT CHECKER]: PUT osrs data.");
                Vars.get().osrsData.clear();
            }

        } else if (Game.logout())
            if (Time.sleepUntil(() -> !Game.isLoggedIn(), 2500)) {
                mission.main.accountManager.setNext();
                Vars.get().canLogout = false;
            }
    }

    @Override
    public String toString() {
        return "Logging out";
    }
}

