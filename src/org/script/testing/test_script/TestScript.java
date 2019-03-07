package org.script.testing.test_script;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.script.events.WelcomeScreen;
import org.rspeer.ui.Log;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Script", desc = "Test Script")
public class TestScript extends Script {

    @Override
    public void onStart() {
        Log.fine("[SPX] Test Script has started.");
    }

    @Override
    public int loop() {
        return 150;
    }

    @Override
    public void onStop() {
        Log.fine("[SPX] Test Script has started.");
    }
}


