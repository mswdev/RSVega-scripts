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
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Vars.get().general_data.size() > 0) {
            final FormBody.Builder form_builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : Vars.get().general_data.entrySet()) {
                form_builder.add(entry.getKey(), entry.getValue());
            }

            final RequestBody request_body = form_builder.build();
            if (AccountData.putData(AccountDataType.GENERAL, mission.main.account_manager.getAccountID(), request_body)) {
                Log.fine("[ACCOUNT CHECKER]: PUT general data.");
                Vars.get().general_data.clear();
            }
        } else if (Vars.get().osrs_data.size() > 0) {
            final FormBody.Builder form_builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : Vars.get().osrs_data.entrySet()) {
                form_builder.add(entry.getKey(), entry.getValue());
            }

            final RequestBody request_body = form_builder.build();
            if (AccountData.putData(AccountDataType.OSRS, mission.main.account_manager.getAccountID(), request_body)) {
                Log.fine("[ACCOUNT CHECKER]: PUT osrs data.");
                Vars.get().osrs_data.clear();
            }

        } else if (Game.logout())
            if (Time.sleepUntil(() -> !Game.isLoggedIn(), 2500)) {
                mission.main.account_manager.setNext();
                Vars.get().can_logout = false;
            }
    }

    @Override
    public String toString() {
        return "Logging out";
    }
}

