package sphiinx.script.testing;

import org.rspeer.runetek.api.component.tab.Combat;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.awt.*;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "Test Script", desc = "Test Script")
public class TestScript extends Script implements RenderListener {

    private final Position END = new Position(3088, 9971, 0);
    private final Position START = new Position(3097, 9816, 0);
    private Path PATH;

    @Override
    public void onStart() {
        Log.fine(getMeta().name() + " has started.");
    }


    @Override
    public int loop() {
        System.out.println(Combat.getSelectedStyle());
        return 150;
    }

    @Override
    public void onStop() {
        Log.fine(getMeta().name() + " has ended.");
        super.onStop();
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();

    }
}

