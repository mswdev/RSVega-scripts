package sphiinx.script.test_script;

import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.util.ArrayList;


@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "Test Script", desc = "Test Script")
public class TestScript extends Script {



    @Override
    public int loop() {
        //EMPTY = 6
        //FORGOT PASS = 5
        //AUTH = 4
        //INAVLID = 3
        //CREDENTIALS = 2
        //LEGACY = 1
        //MAIN MENU = 0
        //System.out.println("Lines: " + Arrays.toString(Login.getResponseLines()));
        //System.out.println("States: " + Login.getState());




        return 100;
    }

    @Override
    public void onStop() {
        Log.fine("Script Ended");
        super.onStop();
    }

}

