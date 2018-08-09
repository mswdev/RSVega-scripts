package sphiinx.script.private_script.wyd_sand_crabs.tasks;

import org.rspeer.script.Script;
import org.rspeer.script.ScriptBlockingEvent;
import org.rspeer.script.events.LoginScreen;
import sphiinx.script.private_script.wyd_sand_crabs.data.Vars;

public class LoginBlock extends ScriptBlockingEvent {

    private final LoginScreen LOGIN_SCREEN;

    public LoginBlock(Script ctx) {
        super(ctx);
        LOGIN_SCREEN = new LoginScreen(ctx);
    }

    @Override
    public void process() {
        Vars.get().needs_auto_retaliate_fix = true;
        LOGIN_SCREEN.process();
    }

    @Override
    public boolean validate() {
        return LOGIN_SCREEN.validate();
    }
}

