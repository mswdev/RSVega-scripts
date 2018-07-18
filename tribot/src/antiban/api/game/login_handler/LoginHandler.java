package antiban.api.game.login_handler;

import org.tribot.api.Clicking;
import org.tribot.api.input.Keyboard;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Login;
import org.tribot.api2007.Screen;
import org.tribot.api2007.types.*;
import antiban.api.game.login_handler.enums.LoginButton;
import antiban.api.game.login_handler.enums.LoginState;
import antiban.api.util.Random;

import java.awt.*;


public class LoginHandler {

    /**
     * Determines whether login screen is at the welcome state.
     *
     * @return True if the login screen is at the welcome state; false otherwise.
     */
    public static boolean welcomeState() {
        return Screen.getColorAt(LoginState.WELCOME.getPoint()).equals(LoginState.WELCOME.getColor());
    }

    /**
     * Determines whether login screen is at the world select state.
     *
     * @return True if the login screen is at the world select state; false otherwise.
     */
    public static boolean worldSelectState() {
        return Screen.getColorAt(LoginState.WORLD_SELECT.getPoint()).equals(LoginState.WORLD_SELECT.getColor());
    }

    /**
     * Determines whether login screen is at the incorrect password state.
     *
     * @return True if the login screen is at the incorrect password state; false otherwise.
     */
    public static boolean incorrectPasswordState() {
        return Screen.getColorAt(LoginState.INCORRECT_PASSWORD.getPoint()).equals(LoginState.INCORRECT_PASSWORD.getColor());
    }

    /**
     * Determines whether login screen is at the login form state.
     *
     * @return True if the login screen is at the login form state; false otherwise.
     */
    public static boolean loginFormState() {
        return (Screen.getColorAt(LoginState.LOGIN_FORM.getPoint()).equals(LoginState.LOGIN_FORM.getColor()) || Login.getLoginResponse().contains(LoginState.LOGIN_FORM.getStatus())) && !worldSelectState() && !welcomeState() && !loadingState() && !membersOnlyState() && !bannedState() && !authenticatorState() && !lockedState() && !billingState() && !displayNameState();
    }

    /**
     * Determines whether login screen is at the loading state.
     *
     * @return True if the login screen is at the loading state; false otherwise.
     */
    public static boolean loadingState() {
        return (Screen.getColorAt(LoginState.LOADING.getPoint()).equals(LoginState.LOADING.getColor()) || Login.getLoginResponse().contains(LoginState.LOADING.getStatus())) && !incorrectPasswordState() && !membersOnlyState() && !bannedState() && !authenticatorState() && !lockedState() && !billingState() && !displayNameState();
    }

    /**
     * Determines whether login screen is at the members only state.
     *
     * @return True if the login screen is at the members only state; false otherwise.
     */
    public static boolean membersOnlyState() {
        return Login.getLoginResponse().contains(LoginState.MEMBERS_ONLY.getStatus()) && !welcomeState() && !worldSelectState();
    }

    /**
     * Determines whether login screen is at the banned state.
     *
     * @return True if the login screen is at the banned state; false otherwise.
     */
    public static boolean bannedState() {
        return Login.getLoginResponse().contains(LoginState.BANNED.getStatus()) && !welcomeState() && !worldSelectState();
    }

    /**
     * Determines whether login screen is at the authenticator state.
     *
     * @return True if the login screen is at the authenticator state; false otherwise.
     */
    public static boolean authenticatorState() {
        return Login.getLoginResponse().contains(LoginState.AUTHENTICATOR.getStatus()) && !welcomeState() && !worldSelectState();
    }

    /**
     * Determines whether login screen is at the locked state.
     *
     * @return True if the login screen is at the locked state; false otherwise.
     */
    public static boolean lockedState() {
        return Login.getLoginResponse().contains(LoginState.LOCKED.getStatus()) && !welcomeState() && !worldSelectState();
    }

    /**
     * Determines whether login screen is at the billing state.
     *
     * @return True if the login screen is at the billing state; false otherwise.
     */
    public static boolean billingState() {
        return Login.getLoginResponse().contains(LoginState.BILLING.getStatus()) && !welcomeState() && !worldSelectState();
    }

    /**
     * Determines whether login screen is at the display name state.
     *
     * @return True if the login screen is at the display name state; false otherwise.
     */
    public static boolean displayNameState() {
        return Login.getLoginResponse().contains(LoginState.DISPLAY_NAME.getStatus()) && !welcomeState() && !worldSelectState();
    }

    /**
     * Logs into the account using the specified credentials from the login form state.
     *
     * @param username  The username of the account.
     * @param password  The password of the account.
     * @param mouse_hop True is mouse hopping should be used; false otherwise.
     * @return True if the login was successful; false otherwise.
     */
    public static boolean login(String username, String password, boolean mouse_hop) {
        if (username == null || password == null) {
            return false;
        }

        if (!loginFormState()) {
            return false;
        }

        if (click(LoginButton.USERNAME_FIELD.getRectangle(), mouse_hop)) {
            Keyboard.typeSend(username);
        }

        if (click(LoginButton.PASSWORD_FIELD.getRectangle(), mouse_hop)) {
            Keyboard.typeSend(password);
        }

        return click(LoginButton.LOGIN.getRectangle(), mouse_hop);
    }

    /**
     * Sets the login screen to the welcome state.
     *
     * @param mouse_hop True is mouse hopping should be used; false otherwise.
     */
    public static void setWelcomeState(boolean mouse_hop) {
        if (incorrectPasswordState()) {
            if (click(LoginButton.TRY_AGAIN.getRectangle(), mouse_hop)) {
                setWelcomeState(mouse_hop);
            }
        } else if (worldSelectState()) {
            click(LoginButton.WORLD_SELECT_CANCEL.getRectangle(), mouse_hop);
        } else if (loginFormState() || membersOnlyState() || bannedState() || authenticatorState() || lockedState() || billingState() || displayNameState()) {
            click(LoginButton.CANCEL.getRectangle(), mouse_hop);
        }
    }

    /**
     * Sets the login screen to the login state from the welcome state.
     *
     * @param mouse_hop True is mouse hopping should be used; false otherwise.
     */
    public static void setLoginState(boolean mouse_hop) {
        if (!welcomeState()) {
            return;
        }

        click(LoginButton.EXISTING_USER.getRectangle(), mouse_hop);
    }

    /**
     * Clicks the specified rectangle.
     *
     * @param rectangle The rectangle to click.
     * @param mouse_hop True is mouse hopping should be used; false otherwise.
     * @return True if the rectangle was clicked; false otherwise.
     */
    private static boolean click(Rectangle rectangle, boolean mouse_hop) {
        if (mouse_hop) {
            Mouse.hop(Random.getPointInShape(rectangle));
            Mouse.click(1);
            return true;
        }

        final RSItem TEMP = new RSItem(0, 0, 0, RSItem.TYPE.OTHER);
        TEMP.setArea(rectangle);
        return Clicking.click(TEMP);
    }

}
