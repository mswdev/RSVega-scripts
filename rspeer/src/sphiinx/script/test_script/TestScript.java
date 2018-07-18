package sphiinx.script.test_script;

import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;


@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "Test Script", desc = "Test Script")
public class TestScript extends Script {

    @Override
    public int loop() {
        return 200;
    }

    @Override
    public void onStop() {
        Log.fine("Script Ended");
        super.onStop();
    }

}

