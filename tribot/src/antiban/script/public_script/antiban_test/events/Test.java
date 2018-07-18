package antiban.script.public_script.antiban_test.events;


import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSInterface;
import antiban.priority_framework.PriorityEvent;

public class Test implements PriorityEvent {

    private static final int TUTORIAL_ISLAND_INTERFACE_ID = 371;

    private static final int TUTORIAL_ISLAND_CHILD_ID = 22;

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean valid() {
        return true;
    }

    @Override
    public void execute() {
        General.println(isTutorial());
    }

    public static int isTutorial() {
        final RSInterface TUTORIAL_SCREEN = Interfaces.get(TUTORIAL_ISLAND_INTERFACE_ID);
        if (TUTORIAL_SCREEN == null)
            return 0;

        final RSInterface TUTORIAL = TUTORIAL_SCREEN.getChild(TUTORIAL_ISLAND_CHILD_ID);
        if (TUTORIAL == null)
            return 0;

        return TUTORIAL.getText().contains("Tutorial Island Progress") && (Login.getLoginState() == Login.STATE.INGAME || Game.getGameState() == 30) ? 1 : 0;
    }


}
