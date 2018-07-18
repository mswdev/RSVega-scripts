package antiban.api.game.login_handler.enums;

import java.awt.*;

public enum LoginButton {

    NEW_USER(new Rectangle(234, 274, 137, 33)),
    EXISTING_USER(new Rectangle(394, 274, 136, 32)),
    USERNAME_FIELD(new Rectangle(310, 241, 145, 13)),
    PASSWORD_FIELD(new Rectangle(340, 256, 118, 13)),
    WORLD_SELECT(new Rectangle(6, 465, 97, 29)),
    WORLD_SELECT_CANCEL(new Rectangle(710, 4, 47, 13)),
    LOGIN(new Rectangle(233, 304, 138, 33)),
    CANCEL(new Rectangle(394, 305, 136, 32)),
    TRY_AGAIN(new Rectangle(313, 258, 136, 34)),
    CONTINUE(new Rectangle(234, 303, 136, 34));


    public final Rectangle RECTANGLE;

    LoginButton(Rectangle rectangle) {
        this.RECTANGLE = rectangle;
    }

    public Rectangle getRectangle() {
        return RECTANGLE;
    }

}
