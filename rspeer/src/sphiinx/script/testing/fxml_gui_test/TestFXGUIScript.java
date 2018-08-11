package sphiinx.script.testing.fxml_gui_test;

import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.api.framework.ui.javafx.FXGUIBuilder;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "Test FX GUI Script", desc = "Test FX GUI Script")
public class TestFXGUIScript extends Script {

    private static FXGUIBuilder FX_GUI_BUILDER = new FXGUIBuilder(new TestFXGUI());

    @Override
    public void onStart() {
        Log.fine(getMeta().name() + " has started.");
        FX_GUI_BUILDER.invokeGUI();
    }

    @Override
    public int loop() {
        if (FX_GUI_BUILDER.isInvoked())
            return 150;


        Log.fine("Looping");
        return 150;
    }

    @Override
    public void onStop() {
        FX_GUI_BUILDER.close();
        Log.fine(getMeta().name() + " has ended.");
    }
}

