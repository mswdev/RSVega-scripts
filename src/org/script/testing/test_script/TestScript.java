package org.script.testing.test_script;

import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.input.Mouse;
import org.rspeer.runetek.api.input.menu.ContextMenu;
import org.rspeer.runetek.api.input.menu.interaction.InteractDriver;
import org.rspeer.runetek.api.input.menu.interaction.MenuAction;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.event.EventMediator;
import org.rspeer.runetek.providers.RSClient;
import org.rspeer.runetek.providers.RSEntity;
import org.rspeer.runetek.providers.RSEntityMarker;
import org.rspeer.runetek.providers.RSTile;
import org.rspeer.runetek.providers.annotations.ClientInvoked;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Script", desc = "Test Script")
public class TestScript extends Script {

    @Override
    public void onStart() {
        Log.fine("[SPX] Test Script has started.");
    }
    private static final Supplier CLICK_POINT_SUPPLIER = () -> {
        return new Point(Random.mid(1, 516), Random.mid(1, 338));
    };
    private static final Point SESSION_POINT;

    static {
        SESSION_POINT = (Point)CLICK_POINT_SUPPLIER.get();
    }

    @Override
    public int loop() {
        /*Npc npc = Npcs.getNearest("Banker");
        Log.fine(npc.getIndex());
        Log.fine(npc.getName());
        Game.getClient().setForcingInteraction(true);
        Game.getClient().setForcedAction(new MenuAction("Bank", "Banker", 11, 2987, 0, 0));
        Point sessionPoint = SESSION_POINT;
        Mouse.click(sessionPoint.x, sessionPoint.y);
        //interact(npc, () -> new MenuAction("Bank", "Banker", 11, 2987, 0, 0));*/
        Arrays.stream(SceneObjects.getAt(Players.getLocal().getPosition())).filter(a -> a.getActions().length > 0).forEach(a -> {
            Log.fine("SceneObject: Name: " + a.getName() + " | SceneObject: ID " + a.getId() + " | Interactable: " + a.isPositionInteractable() + " | Walkable: " + a.isPositionWalkable() + " | Actions: " + Arrays.toString(a.getActions()) + " | Raw Actions: " + Arrays.toString(a.getRawActions()) + " | Position: " + a.getPosition().toString() + " | Type: " + a.getType() + " | UID" + a.getUid() + " | Player Index: " + Game.getClient().getPlayerIndex());
            if (a.interact(a.getActions()[0])) {
                Time.sleep(500);
                Log.severe("INTERACTING");
            } else {
                Time.sleep(500);
                Log.severe("NOT INTERACTING");
            }
        });

        int x = Players.getLocal().getPosition().getX() - Game.getClient().getBaseX();
        int y = Players.getLocal().getPosition().getY()- Game.getClient().getBaseY();
        int f = Players.getLocal().getPosition().toScene().getFloorLevel();
        RSTile tile = Game.getClient().getSceneGraph().getTiles()[f][x][y];

        for (RSEntityMarker marker : tile.getMarkers()) {
            if (marker == null)
                continue;

            Log.fine(marker.getEntity());
            Log.fine(marker.getEntity() == Game.getClient().getPlayer());
            Log.fine(Arrays.asList(marker.getEntity().getClass().getInterfaces()));
        }
        return 150;
    }

    private boolean interact(Interactable interactable, Supplier supplier) {
        if (ContextMenu.isOpen()) {
            ContextMenu.close();
            return false;
        } else {
            MenuAction menuAction;
            if ((menuAction = (MenuAction)supplier.get()) != null) {
                Point sessionPoint = SESSION_POINT;
                RSClient gameClient = Game.getClient();
                gameClient.setForcingInteraction(true);
                Game.getClient().setForcedAction(menuAction);
                Game.getClient();
                Mouse.click(sessionPoint.x, sessionPoint.y);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void onStop() {
        Log.fine("[SPX] Test Script has started.");
    }
}


