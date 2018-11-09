package sphiinx.script.private_script.wyd_sand_crabs.tasks;

import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import sphiinx.script.private_script.wyd_sand_crabs.data.Vars;

public class Reset extends Task {


    static Position getSafePosition(Position start_position, int distance) {
        final Area SURROUNDING_AREA = Area.surrounding(start_position, distance);
        return SURROUNDING_AREA.getTiles().stream()
                .sorted((o1, o2) -> (int) (o2.distance(start_position) - o1.distance(start_position)))
                .filter(Positionable::isPositionWalkable)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean validate() {
        return Vars.get().needs_reset;
    }

    @Override
    public int execute() {
        if (!Movement.isRunEnabled())
            if (Movement.toggleRun(true))
                Time.sleepUntil(Movement::isRunEnabled, 1500);

        final Position RESET_POSITION = getSafePosition(Vars.get().FIGHT_TILE, 30);
        if (RESET_POSITION.distance() > 3)
            Movement.walkTo(RESET_POSITION);

        if (Time.sleepUntil(() -> Players.getLocal().getTargetIndex() == -1 && RESET_POSITION.distance() <= 3, 1500)) {
            Vars.get().needs_reset = false;
        }

        return 100;
    }

    @Override
    public String toString() {
        return "Fight position needs reset; resetting";
    }
}

