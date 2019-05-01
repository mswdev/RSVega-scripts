package org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Login;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptBlockingEvent;
import org.rspeer.script.events.LoginScreen;

public class BlockLoginEvent extends ScriptBlockingEvent {

    private final LoginScreen loginScreen;

    public BlockLoginEvent(Script ctx) {
        super(ctx);
        loginScreen = new LoginScreen(ctx);
    }

    @Override
    public void process() {
        if (Login.getState() == Login.STATE_AUTHENTICATOR)
            Game.getClient().setGameState(Game.STATE_CREDENTIALS_SCREEN);

        loginScreen.process();
    }

    @Override
    public boolean validate() {
        return loginScreen.validate();
    }
}

