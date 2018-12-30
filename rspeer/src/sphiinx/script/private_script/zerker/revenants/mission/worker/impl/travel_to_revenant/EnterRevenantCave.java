package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_revenant;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.script.framework.worker.Worker;

import java.util.function.Predicate;

public class EnterRevenantCave extends Worker {

    public static final Area REVENANT_AREA = Area.rectangular(3227, 10187, 3259, 10136);
    private static final Predicate<SceneObject> CAVERN_ENTRANCE = a -> a.getName().equals("Cavern");
    private static final Position CAVERN_ENTRANCE_POSITION = new Position(3124, 3831, 0);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (REVENANT_AREA.getCenter().distance() > 200) {
            final SceneObject cavern = SceneObjects.getNearest(CAVERN_ENTRANCE);
            if (cavern != null) {
                if (cavern.click())
                    Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
            } else {
                if (Movement.walkTo(CAVERN_ENTRANCE_POSITION))
                    Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
            }
        } else {
            if (Movement.walkTo(REVENANT_AREA.getCenter()))
                Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
        }
    }

    @Override
    public String toString() {
        return "Entering revenant cave.";
    }
}

