package antiban.script.public_script.antiban_account_checker;

import org.tribot.api2007.*;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;
import antiban.api.game.login_handler.LoginHandler;
import antiban.api.game.pricechecking.PriceChecking;
import antiban.script.public_script.antiban_account_checker.data.Vars;

public class DataCollection {

    private static final int MAIN_SCREEN_INTERFACE_ID = 378;

    private static final int PLAY_SCREEN_INTERFACE_MEMBER_CHILD_ID = 74;

    private static final int PLAY_SCREEN_INTERFACE_BANK_PIN_CHILD_ID = 70;

    private static final int PLAY_SCREEN_INTERFACE_LAST_SIGNED_IN_CHILD_ID = 57;


    private static final int COINS_ID = 995;

    public static String getUsername() {
        return Vars.get().account_details.get(0).split(":")[0];
    }

    public static String getPassword() {
        return Vars.get().account_details.get(0).split(":")[1];
    }

    public static String getBankWorth() {
        int total = 0;
        for (RSItem item : Banking.getAll()) {
            if (item.getID() == COINS_ID) {
                total += item.getStack();
                continue;
            }

            total += PriceChecking.getRSPrice(item.getID()) * item.getStack();
        }

        return total > 0 ? String.valueOf(total) : null;
    }

    public static String getInventoryWorth() {
        int total = 0;
        for (RSItem item : Inventory.getAll()) {
            if (item.getID() == COINS_ID) {
                total += item.getStack();
                continue;
            }

            total += PriceChecking.getRSPrice(item.getID()) * item.getStack();
        }

        return total > 0 ? String.valueOf(total) : null;
    }

    public static String getEquipmentWorth() {
        int total = 0;
        for (RSItem item : Equipment.getItems()) {
            final int PRICE = PriceChecking.getRSPrice(item.getID());
            total += PRICE * item.getStack();
        }

        return total > 0 ? String.valueOf(total) : null;
    }

    public static String lastSignIn() {
        final RSInterface PLAY_SCREEN = Interfaces.get(MAIN_SCREEN_INTERFACE_ID);
        if (PLAY_SCREEN == null)
            return null;

        final RSInterface SIGN_IN_DATE = PLAY_SCREEN.getChild(PLAY_SCREEN_INTERFACE_LAST_SIGNED_IN_CHILD_ID);
        if (SIGN_IN_DATE == null)
            return null;

        String unformatted_date = SIGN_IN_DATE.getText().replace("You last logged in <col=ff0000>", "");
        return unformatted_date.replace("</col>.", "");
    }

    public static int hasBankPin() {
        final RSInterface PLAY_SCREEN = Interfaces.get(MAIN_SCREEN_INTERFACE_ID);
        if (PLAY_SCREEN == null)
            return 0;

        final RSInterface BANK_PIN = PLAY_SCREEN.getChild(PLAY_SCREEN_INTERFACE_BANK_PIN_CHILD_ID);
        if (BANK_PIN == null)
            return 0;

        return BANK_PIN.getText().contains("You do not have a Bank PIN.") ? 0 : 1;
    }

    public static int isMembers() {
        final RSInterface PLAY_SCREEN = Interfaces.get(MAIN_SCREEN_INTERFACE_ID);
        if (PLAY_SCREEN == null)
            return 0;

        final RSInterface MEMBERS = PLAY_SCREEN.getChild(PLAY_SCREEN_INTERFACE_MEMBER_CHILD_ID);
        if (MEMBERS == null)
            return 0;

        return MEMBERS.getText().contains("You are not a member.") ? 0 : 1;
    }

    public static int isTutorial() {
        return lastSignIn() == null ? 1 : 0;
    }

    public static int isBanned() {
        return LoginHandler.bannedState() ? 1 : 0;
    }

    public static int hasAuthenticator() {
        return LoginHandler.authenticatorState() ? 1 : 0;
    }


    public static int isLocked() {
        return LoginHandler.lockedState() ? 1 : 0;
    }

    public static int isInvalid() {
        return LoginHandler.incorrectPasswordState() ? 1 : 0;
    }

}
