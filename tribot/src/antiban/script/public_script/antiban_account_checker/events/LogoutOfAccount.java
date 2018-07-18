package antiban.script.public_script.antiban_account_checker.events;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Game;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Login;
import antiban.api.game.timing.Waiting;
import antiban.api.util.Random;
import antiban.priority_framework.PriorityEvent;

import java.awt.*;

public class LogoutOfAccount implements PriorityEvent {

    private final Rectangle LOGOUT_TAB = new Rectangle(628, 466, 28, 30);
    private final Rectangle LOGOUT_BUTTON = new Rectangle(575, 420, 135, 25);

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean valid() {
        return Login.getLoginState() == Login.STATE.INGAME || Game.getGameState() == 30;
    }

    @Override
    public void execute() {
        if (GameTab.TABS.LOGOUT.isOpen()) {
            Mouse.hop(Random.getPointInShape(LOGOUT_BUTTON));
            Mouse.click(1);
            Waiting.waitForCondition(() -> Login.getLoginState() == Login.STATE.LOGINSCREEN || Game.getGameState() != 30, General.random(1000, 1200));
        } else {
            Mouse.hop(Random.getPointInShape(LOGOUT_TAB));
            Mouse.click(1);
            Waiting.waitForCondition(GameTab.TABS.LOGOUT::isOpen, General.random(1000, 1200));
        }
    }

}
