package sphiinx.script.public_script.spx_account_checker.mission;

import org.rspeer.runetek.api.Login;
import org.rspeer.script.GameAccount;
import org.rspeer.ui.Log;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorkerHandler;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccountCheckerMission extends Mission {

    private final AccountCheckerWorkerHandler MANAGER = new AccountCheckerWorkerHandler(this);
    private final LinkedHashMap<String, String> ACCOUNT_DETAILS;
    private static Iterator<Map.Entry<String, String>> ITERATOR;
    private static SPXScript SCRIPT;
    private static  boolean can_end;

    public AccountCheckerMission(SPXScript script, LinkedHashMap<String, String> account_details) {
        SCRIPT = script;
        this.ACCOUNT_DETAILS = account_details;
        ITERATOR = account_details.entrySet().iterator();
    }

    @Override
    public String getMissionName() {
        return "Account Checker";
    }

    @Override
    public String getWorkerName() {
        Worker<AccountCheckerMission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<AccountCheckerMission> c = MANAGER.getCurrent();
        return c == null ? "Waiting for worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return can_end;
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public int execute() {
        MANAGER.work();
        return 150;
    }

    @Override
    public void onMissionStart() {
        nextAccount();
        Log.fine(ACCOUNT_DETAILS.size() + " accounts loaded.");
    }

    @Override
    public void onMissionEnd() {

    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }

    public static void nextAccount() {
        if (!ITERATOR.hasNext()) {
            can_end = true;
            return;
        }

        final Map.Entry<String, String> ENTRY = ITERATOR.next();
        final String USERNAME = ENTRY.getKey();
        final String PASSWORD = ENTRY.getValue();
        Vars.get().ACCOUNT_DATA.putIfAbsent("username", USERNAME);
        Vars.get().ACCOUNT_DATA.putIfAbsent("password", PASSWORD);
        Login.enterCredentials(USERNAME, PASSWORD);
        System.out.println(ENTRY.getKey() + " | " + ENTRY.getValue());
        SCRIPT.setAccount(new GameAccount(USERNAME, PASSWORD));
    }
}

