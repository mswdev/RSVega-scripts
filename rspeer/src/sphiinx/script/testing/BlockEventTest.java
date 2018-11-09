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
        System.out.println("Processing");
        SCREEN.process();
        System.out.println("Processed");
    }

    @Override
    public boolean validate() {
        return SCREEN.validate();
    }
}

