package sphiinx.script.testing;

import org.rspeer.script.Script;
import org.rspeer.script.ScriptBlockingEvent;
import org.rspeer.script.events.LoginScreen;

public class BlockEventTest extends ScriptBlockingEvent {

    private final LoginScreen SCREEN;

    public BlockEventTest(Script ctx) {
        super(ctx);
        SCREEN = new LoginScreen(ctx);
    }

    @Override
    public void process() {
        SCREEN.process();
    }

    @Override
    public boolean validate() {
        return SCREEN.validate();
    }
}

