package sphiinx.script.testing;

import org.rspeer.runetek.api.commons.Time;
import sphiinx.api.framework.ui.SPXScriptGUIHandler;

public class SwingGUIExample {


    public static void main(String[] args) {
        final SPXScriptGUIHandler SPX_GUI_HANDLER = new SPXScriptGUIHandler(new SwingGUIEx());
        SPX_GUI_HANDLER.initialize();
        while (SPX_GUI_HANDLER.getGUI().getFrame().isVisible()) {
            Time.sleep(150);
        }

        System.out.println("DONE");
    }
}
