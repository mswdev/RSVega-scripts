package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.magic_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class CastAirStrike extends TIWorker {

    private final Predicate<Npc> CHICKEN = a -> a.getName().equals("Chicken");
    private final Position CAST_TILE = new Position(3139, 3091, 0);

    public CastAirStrike(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
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
            Movement.walkTo(CAST_TILE);
        }
    }

    @Override
    public String toString() {
        return "[MAGIC INSTRUCTOR]: Casting air strike";
    }
}

