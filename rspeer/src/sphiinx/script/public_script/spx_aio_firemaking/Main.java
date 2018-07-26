package sphiinx.script.public_script.spx_aio_firemaking;

import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.runetek.event.listeners.MouseInputListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_aio_firemaking.data.LogType;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FM_Mission;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.FIREMAKING, name = "SPX AIO Firemaking", desc = "AIO Firemaking")
public class Main extends SPXScript implements MouseInputListener, RenderListener {

    @Override
    public void onStart() {
        setPaused(true);
        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        LinkedList<Mission> generated = new LinkedList<>();
        generated.add(new FM_Mission());
        return generated;
    }

    @Override
    public void loadScriptArgs() {
        super.loadScriptArgs();
        for (LogType LOG : LogType.values()) {
            if (!LOG.getName().equalsIgnoreCase(getArgs()))
                continue;

            Vars.get().log_type = LOG;
            Vars.get().progressive = false;
        }
    }





    /**
     * //TODO REMOVE THIS AN ADD A GUI; THIS IS TEMPORARY SO I CAN RELEASE THE SCRIPT.
     * //TODO REMOVE THIS AN ADD A GUI; THIS IS TEMPORARY SO I CAN RELEASE THE SCRIPT.
     * //TODO REMOVE THIS AN ADD A GUI; THIS IS TEMPORARY SO I CAN RELEASE THE SCRIPT.
     * //TODO REMOVE THIS AN ADD A GUI; THIS IS TEMPORARY SO I CAN RELEASE THE SCRIPT.
     * //TODO REMOVE THIS AN ADD A GUI; THIS IS TEMPORARY SO I CAN RELEASE THE SCRIPT.
     * //TODO REMOVE THIS AN ADD A GUI; THIS IS TEMPORARY SO I CAN RELEASE THE SCRIPT.
     * */
    private final Rectangle RECT1 = new Rectangle(6, 350, 90, 29);
    private final Rectangle RECT2 = new Rectangle(101, 350, 90, 29);

    @Override
    public void notify(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == 1) {
            if (RECT1.contains(mouseEvent.getPoint())) {
                Vars.get().lane_start_positions.add(Players.getLocal().getPosition());
            }

            if (RECT2.contains(mouseEvent.getPoint())) {
                if (Vars.get().lane_start_positions.size() <= 0) {
                    Log.severe("You must set at least one lane position with the buttons on screen.");
                    Log.severe("You must set at least one lane position with the buttons on screen.");
                    Log.severe("You must set at least one lane position with the buttons on screen.");
                    Log.severe("These buttons are temporary until a GUI is added.");
                    Log.severe("These buttons are temporary until a GUI is added.");
                    Log.severe("These buttons are temporary until a GUI is added.");
                } else {
                    setPaused(false);
                }
            }
        }
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();
        G.fillRect(RECT1.x, RECT1.y, RECT1.width, RECT1.height);
        G.fillRect(RECT2.x, RECT2.y, RECT2.width, RECT2.height);
        G.setFont(new Font("Arial", 1, 16));
        G.setColor(Color.BLACK);
        G.drawString("Set Tile", 24, 370);
        G.drawString("Start", 125, 370);

        if (Vars.get().lane_start_positions.size() > 0) {
            for (Position position : Vars.get().lane_start_positions) {
                G.setColor(Color.RED);
                G.drawPolygon(Projection.getTileShape(position));
                G.setColor(new Color(255, 255, 255, 25));
                G.fillPolygon(Projection.getTileShape(position));
            }
        }
    }
}

