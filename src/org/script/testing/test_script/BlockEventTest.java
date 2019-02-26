package org.script.testing.test_script;

import org.rspeer.script.Script;
import org.rspeer.script.ScriptBlockingEvent;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.ui.Log;

public class BlockEventTest extends ScriptBlockingEvent {

    private final LoginScreen SCREEN;

    public BlockEventTest(Script ctx) {
        super(ctx);
        SCREEN = new LoginScreen(ctx);
    }

    @Override
    public void process() {
        Log.info("Processing");
        SCREEN.process();
        Log.info("Processed");
    }

    @Override
    public boolean validate() {
        return SCREEN.validate();
    }
}

