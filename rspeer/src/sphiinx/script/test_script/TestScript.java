package sphiinx.script.test_script;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;


@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "Test Script", desc = "Test Script")
public class TestScript extends Script {

    @Override
    public int loop() {
        //System.out.println("Viewing: " + Interfaces.isViewingChat());
        //System.out.println("Options: " + Interfaces.isViewingChatOptions());
        //System.out.println("CanCont: " + Interfaces.canContinue());
        //System.out.println("isDialogOpen: " + Interfaces.isDialogOpen());
        //System.out.println("Process: " + Interfaces.isDialogProcessing());
        //System.out.println("Is Animating: " + Players.getLocal().isAnimating());
        //System.out.println("Animation: " + Players.getLocal().getAnimation());
        //System.out.println("Frame: " + Players.getLocal().getAnimationFrame());
        //setAccount(new GameAccount("Yes", "Okay"));
        Movement.walkTo(new Position(3209, 3218, 2));
        System.out.println("Looping");
        return 200;
    }

    @Override
    public void onStop() {
        Log.fine("Script Ended");
        super.onStop();
    }

}

