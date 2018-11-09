package sphiinx.script.testing;

import org.rspeer.runetek.event.listeners.LoginResponseListener;
import org.rspeer.runetek.event.types.LoginResponseEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.script.events.WelcomeScreen;
import org.rspeer.ui.Log;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Script", desc = "Test Script")
public class TestScript extends Script implements LoginResponseListener {
    // [TODO - 2018-10-26]: Go through old tribot and other client apis to see what stuff is useful

    @Override
    public void onStart() {
        removeBlockingEvent(LoginScreen.class);
        removeBlockingEvent(WelcomeScreen.class);
        //addBlockingEvent(new BlockEventTest(this));
        Log.fine(getMeta().name() + " has started.");
    }

    @Override
    public int loop() {
        return 150;
    }

    @Override
    public void onStop() {
        Log.fine(getMeta().name() + " has ended.");
    }

    @Override
    public void notify(LoginResponseEvent loginResponseEvent) {
        System.out.println("Exec");
        if (loginResponseEvent.getResponse() != null) {
            System.out.println(loginResponseEvent.getResponse());
            System.out.println(loginResponseEvent.getResponse().getCode());
        }
    }
}

