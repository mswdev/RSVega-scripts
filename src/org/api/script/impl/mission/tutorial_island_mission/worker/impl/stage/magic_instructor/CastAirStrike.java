package org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.magic_instructor;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.Predicate;

public class CastAirStrike extends Worker {

    private static final Predicate<Npc> CHICKEN = a -> a.getName().equals("Chicken") && a.getTargetIndex() == -1;
    private static final Position CAST_TILE = new Position(3139, 3091, 0);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getTargetIndex() != -1)
            return;

        final Npc NPC = Npcs.getNearest(CHICKEN);
        if (NPC == null)
            return;

        if (CAST_TILE.equals(Players.getLocal().getPosition())) {
            if (Magic.cast(Spell.Modern.WIND_STRIKE, NPC))
                Time.sleepUntil(() -> Players.getLocal().getTargetIndex() != -1, 1500);
        } else {
            if (Movement.walkTo(CAST_TILE))
                Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
        }
    }

    @Override
    public String toString() {
        return "Casting air strike.";
    }
}

