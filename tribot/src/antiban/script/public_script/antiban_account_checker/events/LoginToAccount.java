package antiban.script.public_script.antiban_account_checker.events;

import org.tribot.api.General;
import org.tribot.api2007.Game;
import org.tribot.api2007.Login;
import antiban.api.game.login_handler.LoginHandler;
import antiban.api.game.timing.Waiting;
import antiban.api.util.Logging;
import antiban.priority_framework.PriorityEvent;
import antiban.script.public_script.antiban_account_checker.data.Vars;

public class LoginToAccount implements PriorityEvent {

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean valid() {
        return Login.getLoginState() == Login.STATE.LOGINSCREEN || Game.getGameState() != 30;
    }

    @Override
    public void execute() {
        final String USERNAME = Vars.get().account_details.get(0).split(":")[0];
        final String PASSWORD = Vars.get().account_details.get(0).split(":")[1];

        if (LoginHandler.loginFormState()) {
            if (LoginHandler.login(USERNAME, PASSWORD, true)) {
                if (!Waiting.waitForCondition(() -> Login.getLoginState() == Login.STATE.INGAME || Game.getGameState() == 30, General.random(3200, 3500))) {
                    Logging.status("Failed login.");
                }
                Vars.get().collect_general_data = true;
            }
        } else {
            if (LoginHandler.welcomeState()) {
                LoginHandler.setLoginState(true);
            } else {
                LoginHandler.setWelcomeState(true);
            }
        }
    }
}
