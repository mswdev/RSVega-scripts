package sphiinx.script.testing.swing_gui_test;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.api.framework.ui.swing.GUIHandler;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "Test Swing GUI Script", desc = "Test Swing GUI Script")
public class TestSwingGUIScript extends Script {

    private GUIHandler spx_gui_handler;

    @Override
    public void onStart() {
        spx_gui_handler = new GUIHandler(new TestSwingGUI());
        spx_gui_handler.invokeGUI();

        Log.fine(getMeta().name() + " has started.");
    }

    @Override
    public int loop() {
        while (spx_gui_handler.getGUI().getFrame().isVisible()) {
            Time.sleep(150);
        }

        Log.fine("Looping");
        return 150;
    }

    @Override
    public void onStop() {
        spx_gui_handler.getGUI().getFrame().dispose();
        Log.fine(getMeta().name() + " has ended.");
    }
}

