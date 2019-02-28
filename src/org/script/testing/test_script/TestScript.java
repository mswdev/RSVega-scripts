package org.script.testing.test_script;

import org.api.game.Wilderness;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.LoginResponseListener;
import org.rspeer.runetek.event.types.LoginResponseEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.script.events.WelcomeScreen;
import org.rspeer.ui.Log;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Script", desc = "Test Script")
public class TestScript extends Script {

    @Override
    public void onStart() {
        removeBlockingEvent(LoginScreen.class);
        removeBlockingEvent(WelcomeScreen.class);
        //addBlockingEvent(new BlockEventTest(this));
    }

    @Override
    public int loop() {
        return 150;
    }

    @Override
    public void onStop() {
        Log.fine(getMeta().name() + " has ended.");
    }

}

