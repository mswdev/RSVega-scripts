package sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptBlockingEvent;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;

public class BlockLoginEvent extends ScriptBlockingEvent {

    private final LoginScreen LOGIN_SCREEN;

    public BlockLoginEvent(Script ctx) {
        super(ctx);
        LOGIN_SCREEN = new LoginScreen(ctx);
    }

    @Override
    public void process() {
        LOGIN_SCREEN.process();
        if (!Time.sleepUntil(Game::isLoggedIn, 2500)) {
            Vars.get().ACCOUNT_DATA.forEach((k, v) -> System.out.println(k + ", " + v));
            Vars.get().ACCOUNT_DATA.clear();
            Log.severe("Login failed; moving to next account.");
            AccountCheckerMission.nextAccount();
        }
    }

    @Override
    public boolean validate() {
        return LOGIN_SCREEN.validate();
    }
}

