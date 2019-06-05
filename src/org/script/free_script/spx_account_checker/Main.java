package org.script.free_script.spx_account_checker;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.api.client.screenshot.Screenshot;
import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.event.listeners.GameStateListener;
import org.rspeer.runetek.event.listeners.LoginResponseListener;
import org.rspeer.runetek.event.types.GameStateEvent;
import org.rspeer.runetek.event.types.LoginResponseEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.script.events.WelcomeScreen;
import org.rspeer.ui.Log;
import org.script.free_script.spx_account_checker.data.Vars;
import org.script.free_script.spx_account_checker.http.AccountData;
import org.script.free_script.spx_account_checker.http.AccountDataType;
import org.script.free_script.spx_account_checker.http.AccountManager;
import org.script.free_script.spx_account_checker.mission.AccountCheckerMission;
import org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount.BlockLoginEvent;
import org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount.BlockWelcomeScreenEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Account Checker", desc = "[SPX] Account Checker")
public class Main extends SPXScript implements LoginResponseListener, GameStateListener {

    public final AccountManager accountManager = new AccountManager(this);

    @Override
    public void onStart() {
        Game.getClient().setLoginWorldSelectorOpen(true);
        Game.getClient().setWorld(Worlds.get(a -> !a.isMembers()));

        accountManager.setNext();

        removeBlockingEvent(WelcomeScreen.class);
        removeBlockingEvent(LoginScreen.class);
        addBlockingEvent(new BlockWelcomeScreenEvent(this));
        addBlockingEvent(new BlockLoginEvent(this));

        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new AccountCheckerMission(this, this));
        return missions;
    }

    @Override
    public void notify(LoginResponseEvent loginResponseEvent) {
        Log.severe("[ACCOUNT CHECKER]: Login Failed");
        if (loginResponseEvent.getResponse() != null && (loginResponseEvent.getResponse() != LoginResponseEvent.Response.ACCOUNT_DISABLED && loginResponseEvent.getResponse() != LoginResponseEvent.Response.ACCOUNT_LOCKED && loginResponseEvent.getResponse() != LoginResponseEvent.Response.ENTER_AUTH && loginResponseEvent.getResponse() != LoginResponseEvent.Response.UNSUCCESSFUL_ACCOUNT_LOGIN_ATTEMPT)) {
            Log.severe("UNKNOWN RESPONSE TYPE, TAKING PICTURE AND STOPPING SCRIPT");
            Screenshot.add("Unknown Response Type");
            setStopping(true);
        }

        Vars.get().generalData.put("last_check", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Vars.get().generalData.put("is_invalid", "1");
        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.ACCOUNT_DISABLED)
            Vars.get().generalData.put("is_disabled", "1");

        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.ACCOUNT_LOCKED)
            Vars.get().generalData.put("is_locked", "1");

        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.ENTER_AUTH)
            Vars.get().generalData.put("is_auth", "1");

        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.UNSUCCESSFUL_ACCOUNT_LOGIN_ATTEMPT)
            Vars.get().generalData.put("is_billing", "1");

        final FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : Vars.get().generalData.entrySet()) {
            formBuilder.add(entry.getKey(), entry.getValue());
        }

        final RequestBody requestBody = formBuilder.build();
        if (AccountData.putData(AccountDataType.GENERAL, accountManager.getAccountId(), requestBody))
            Log.fine("[ACCOUNT CHECKER]: PUT general data.");

        Vars.get().generalData.clear();
        accountManager.setNext();
    }

    @Override
    public void notify(GameStateEvent gameStateEvent) {
        /*if (gameStateEvent.getNew() == 30)
            accountManager.setNext();*/
    }
}
