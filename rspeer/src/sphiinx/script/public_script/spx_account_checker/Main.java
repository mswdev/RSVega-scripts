package sphiinx.script.public_script.spx_account_checker;

import okhttp3.FormBody;
import okhttp3.RequestBody;
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
import sphiinx.api.client.screenshot.Screenshot;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.SPXScript;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.http.AccountData;
import sphiinx.script.public_script.spx_account_checker.http.AccountDataType;
import sphiinx.script.public_script.spx_account_checker.http.AccountManager;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.BlockLoginEvent;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.BlockWelcomeScreenEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Account Checker", desc = "")
public class Main extends SPXScript implements LoginResponseListener, GameStateListener {

    public final AccountManager account_manager = new AccountManager(this);

    @Override
    public void onStart() {
        Game.getClient().setLoginWorldSelectorOpen(true);
        Game.getClient().setWorld(Worlds.get(a -> !a.isMembers()));

        account_manager.setNext();

        removeBlockingEvent(WelcomeScreen.class);
        removeBlockingEvent(LoginScreen.class);
        addBlockingEvent(new BlockWelcomeScreenEvent(this));
        addBlockingEvent(new BlockLoginEvent(this));

        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new AccountCheckerMission(this));
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

        Vars.get().general_data.put("last_check", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Vars.get().general_data.put("is_invalid", "1");
        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.ACCOUNT_DISABLED)
            Vars.get().general_data.put("is_disabled", "1");

        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.ACCOUNT_LOCKED)
            Vars.get().general_data.put("is_locked", "1");

        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.ENTER_AUTH)
            Vars.get().general_data.put("is_auth", "1");

        if (loginResponseEvent.getResponse() == LoginResponseEvent.Response.UNSUCCESSFUL_ACCOUNT_LOGIN_ATTEMPT)
            Vars.get().general_data.put("is_billing", "1");

        final FormBody.Builder form_builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : Vars.get().general_data.entrySet()) {
            form_builder.add(entry.getKey(), entry.getValue());
        }

        final RequestBody request_body = form_builder.build();
        if (AccountData.putData(AccountDataType.GENERAL, account_manager.getAccountID(), request_body))
            Log.fine("[ACCOUNT CHECKER]: PUT general data.");

        Vars.get().general_data.clear();
        account_manager.setNext();
    }

    @Override
    public void notify(GameStateEvent gameStateEvent) {
        /*if (gameStateEvent.getNew() == 30)
            account_manager.setNext();*/
    }
}
