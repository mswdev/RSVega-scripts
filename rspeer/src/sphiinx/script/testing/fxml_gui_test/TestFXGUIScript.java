package sphiinx.script.testing.fxml_gui_test;

import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.api.framework.ui.javafx.FXHandler;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "Test FX GUI Script", desc = "Test FX GUI Script")
public class TestFXGUIScript extends Script {

    private final TestFXGUI GUI = new TestFXGUI();
    private final FXHandler HANDLER = new FXHandler(GUI);

    @Override
    public void onStart() {
        setPaused(true);
        HANDLER.invokeGUI();
        Log.fine(getMeta().name() + " has started.");
    }

    @Override
    public int loop() {

        Log.fine("Looping");
        return 150;
    }

    @Override
    public void onStop() {
        HANDLER.close();
        Log.fine(getMeta().name() + " has ended.");
    }
}

