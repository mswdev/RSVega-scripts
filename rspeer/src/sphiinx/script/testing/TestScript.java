package sphiinx.script.testing;

import org.rspeer.runetek.api.Login;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.event.listeners.LoginResponseListener;
import org.rspeer.runetek.event.types.LoginResponseEvent;
import org.rspeer.script.GameAccount;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.script.events.WelcomeScreen;
import org.rspeer.ui.Log;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Script", desc = "Test Script")
public class TestScript extends Script implements LoginResponseListener {
    // [TODO - 2018-10-26]: Go through old tribot and other client apis to see what stuff is useful

    //private BankCache bank_cache;

    @Override
    public void onStart() {
        removeBlockingEvent(LoginScreen.class);
        removeBlockingEvent(WelcomeScreen.class);
        //addBlockingEvent(new BlockEventTest(this));
        //bank_cache = new BankCache(this);
        //bank_cache.start();
    }

    @Override
    public int loop() {
        Log.fine(GrandExchangeSetup.getItem().getId() != 556);
        return 150;
    }

    private void getAcc() {
        Log.info("GetAccount null: " + (getAccount() == null));
        Log.info("GetAccount valid: " + getAccount().validate());
        Log.info("GetAccount user: " + getAccount().getUsername());
        Log.info("GetAccount pass: " + getAccount().getPassword());
        Log.info("--------------------------");
    }

    @Override
    public void onStop() {
        Log.fine(getMeta().name() + " has ended.");
    }

    @Override
    public void notify(LoginResponseEvent loginResponseEvent) {
        Log.info("Exec");
        if (loginResponseEvent.getResponse() != null) {
            Log.info(loginResponseEvent.getResponse());
            Log.info(loginResponseEvent.getResponse().getCode());
        }
    }
}

