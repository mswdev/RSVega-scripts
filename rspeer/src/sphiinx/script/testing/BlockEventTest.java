package sphiinx.script.testing;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Login;
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
        System.out.println("VALIDATING");
        SCREEN.process();
        System.out.println("PROCESSED");
        System.out.println("Game: " + Game.getState());
        System.out.println("Login: " + Login.getState());
        System.out.println("Loggedin: " + Game.isLoggedIn());
        System.out.println("------------------------");
    }

    @Override
    public boolean validate() {
        return SCREEN.validate();
    }
}

