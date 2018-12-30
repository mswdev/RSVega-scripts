package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_66;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;

public class CastCamelotTeleport extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Magic.cast(Spell.Modern.CAMELOT_TELEPORT))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
    }

    @Override
    public String toString() {
        return "Casting Camelot teleport.";
    }
}

