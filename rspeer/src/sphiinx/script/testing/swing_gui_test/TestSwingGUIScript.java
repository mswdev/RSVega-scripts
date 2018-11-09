package sphiinx.script.testing.swing_gui_test;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.ui.swingui.GUIBuilder;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Swing GUI Script", desc = "Test Swing GUI Script")
public class TestSwingGUIScript extends Script {

    private GUIBuilder spx_gui_handler;

    @Override
    public void onStart() {
        spx_gui_handler = new GUIBuilder(new TestSwingGUI());
        spx_gui_handler.invokeGUI();

        Log.fine(getMeta().name() + " has started.");
    }

    @Override
    public int loop() {
        while (spx_gui_handler.getGUI().getFrame().isVisible()) {
            Time.sleep(100);
        }

        Log.fine("Looping");
        return 100;
    }

    @Override
    public void onStop() {
        spx_gui_handler.getGUI().getFrame().dispose();
        Log.fine(getMeta().name() + " has ended.");
    }
}

