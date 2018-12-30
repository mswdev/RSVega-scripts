package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;

public class CastVarrockTeleport extends Worker {


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

        if (Magic.cast(Spell.Modern.VARROCK_TELEPORT))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
    }

    @Override
    public String toString() {
        return "Casting Varrock teleport.";
    }
}

