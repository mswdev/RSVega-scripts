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
import java.util.logging.Level;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "Test Script", desc = "Test Script")
public class TestScript extends Script implements RenderListener, LoginMessageListener {

    @Override
    public void onStart() {
    }


    @Override
    public int loop() {
        return 150000;
    }

    @Override
    public void onStop() {
        Log.log(Level.WARNING, "Info", "[DEBUG]: SwingGUIExample script stopped");
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

