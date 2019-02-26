package org.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Login;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptBlockingEvent;
import org.rspeer.script.events.LoginScreen;

public class BlockLoginEvent extends ScriptBlockingEvent {

    private final LoginScreen login_screen;

    public BlockLoginEvent(Script ctx) {
        super(ctx);
        login_screen = new LoginScreen(ctx);
    }

    @Override
    public void process() {
        if (Login.getState() == Login.STATE_AUTHENTICATOR)
            Game.getClient().setGameState(Game.STATE_CREDENTIALS_SCREEN);

        login_screen.process();
    }

    @Override
    public boolean validate() {
        return login_screen.validate();
    }
}

