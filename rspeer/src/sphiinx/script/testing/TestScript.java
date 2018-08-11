package sphiinx.script.testing;

import org.rspeer.runetek.event.listeners.LoginMessageListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.LoginMessageEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.awt.*;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "Test Script", desc = "Test Script")
public class TestScript extends Script implements RenderListener, LoginMessageListener {

    @Override
    public void onStart() {
        Log.fine(getMeta().name() + " has started.");
    }

    @Override
    public int loop() {
        Log.fine("Is running");
        return 150;
    }

    @Override
    public void onStop() {
        Log.fine(getMeta().name() + " has ended.");
        super.onStop();
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();

    }

    @Override
    public void notify(LoginMessageEvent loginMessageEvent) {

    }
}

